$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sysZnhd/list',
        datatype: "json",
        colModel: [
            {label: 'ID', name: 'id', index: "id", width: 45, key: true},
            {label: '标题', name: 'name', index: "name", width: 100},
            {label: '资源类型', name: 'zylxname', index: "name", width: 100},
            {label: '更新时间', name: 'updatetime', index: "updatetime", width: 100},
            {
                label: '类型', name: 'type', width: 80,
                formatter: function (value, options, row) {
                    if (value === "1") {
                        return '<span class="label label-success">地推维护</span>';
                    } else if (value === "2") {
                        return '<span class="label label-success">数据爬取</span>';
                    } else {
                        return '<span class="label label-success">未知</span>';
                    }
                }
            },
        ],
        viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList: [10, 30, 50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "page.records",
            page: "page.current",
            total: "page.pages",
            records: "page.total"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order"
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });
});

//菜单树
var menu_ztree;
var menu_setting = {
    data: {
        simpleData: {
            enable: true,
            idKey: "menuId",
            pIdKey: "parentId",
            rootPId: -1
        },
        key: {
            url: "nourl"
        }
    },
    check: {
        enable: true,
        nocheckInherit: true
    }
};

//部门结构树
var dept_ztree;
var dept_setting = {
    data: {
        simpleData: {
            enable: true,
            idKey: "deptId",
            pIdKey: "parentId",
            rootPId: -1
        },
        key: {
            url: "nourl"
        }
    }
};
$().ready(function () {
    $('.summernote').summernote({
        height: 250,//高度
        tabsize: 2,//页面上的summernote编辑框的个数
        lang: 'zh-CN',//语言
        callbacks: {//回调函数，重写onImageUpload方法
            onImageUpload: function (files, editor, welEditable) {
                vm.sendFile(this, files[0], editor, welEditable);
            }
        }
    });
});
//数据树
var data_ztree;
var data_setting = {
    data: {
        simpleData: {
            enable: true,
            idKey: "deptId",
            pIdKey: "parentId",
            rootPId: -1
        },
        key: {
            url: "nourl"
        }
    },
    check: {
        enable: true,
        nocheckInherit: true,
        chkboxType: {"Y": "", "N": ""}
    }
};

var vm = new Vue({
    el: '#rrapp',
    data: {
        q: {
            znhdName: null
        },
        importModle: true,
        showList: true,
        showDelect: true,
        title: null,
        equipmentList: {},
        allFiles: [],
        zylxs: [],
        jys: [{"id": "1", "name": "是"}, {"id": "2", "name": "否"}],
        sfzsyms: [{"id": "1", "name": "是"}, {"id": "2", "name": "否"}, {"id": "3", "name": "未知"}],
        types: [{"id": "1", "name": "地推"}, {"id": "2", "name": "爬虫"}],
        jkzts: [{"id": "1", "name": "健康"}, {"id": "2", "name": "一般"}, {"id": "3", "name": "病危"}],
        znhd: {
            id: '',
            qcmc: '',
            bh: '',
            username: '',
            createdate: '',
            enddate: '',
            equipmentIdList: [],
            equipmentId: '',
            znhdchild: [],
            deptName: [],
            deptid: '',
            files: [],
        }
    },
    methods: {
        deleteFile: function (id) {
            if (id == null) {
                alert("请选择要删除的文件!");
                return;
            }
            vm.deleteFles = {"id": id};
            confirm('确定要删除该记录？', function () {
                $.ajax({
                    type: "POST",
                    url: baseURL + "tMaterialFile/deleteByFileId",
                    contentType: "application/json",
                    data: JSON.stringify(vm.deleteFles),
                    success: function (r) {
                        if (r.code == 0) {
                            alert('文件删除成功', function () {
                                vm.reload();
                            });
                        } else {
                            alert(r.msg);
                        }
                    }
                });
            });
        },
        download: function (id) {
            console.log("id)id)id)id)" + id)
            $.get(baseURL + "tMaterialFile/ishSingleFile/" + id, function (r) {
                if (r.code == 0) {
                    if (r.fileName != '无下载文件' && r.fileName != '文件不存在') {
                        var url = baseURL + "tMaterialFile/downFile?id=" + id + "&token=" + token;
                        window.location.href = url;
                    } else {
                        alert(r.fileName)
                    }
                }
            });
        },
        saveFile: function () {
            var value = document.querySelectorAll('*[name="abc"]')
            $("#box").val(value);
            $("#myModalPreachData").modal('hide');
        },
        openPreachData: function () {
            $("#myModalPreachData").modal('show');
        },
        shutdowPreach: function () {
            $("#myModalPreachData").modal('hide');
        },
        sendFile: function (val, files, editor, welEditable) {
            data = new FormData();
            data.append("files", files);
            $.ajax({
                data: data,
                dataType: 'json',
                type: "POST",
                url: baseURL + "tMaterialFile/ajaxUploadFile",
                cache: false,
                contentType: false,
                processData: false,
                responseType: "json",
                success: function (r) {
                    console.log("successsuccesssuccess")
                    $(val).summernote('editor.insertImage', r.path);
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log("errorerrorerror")
                    alert(XMLHttpRequest.status);
                    alert(XMLHttpRequest.readyState);
                    alert(textStatus);
                }
            });
        },
        importFile: function () {
            if ($("#fileList").val() == null || $("#fileList").val() == "") {
                alert("请选择具体附件再上传!");
                return;
            }
            var form = document.getElementById('upload');
            $.ajax({
                url: baseURL + "tMaterialFile/importPsot",
                type: 'post',
                data: new FormData(form),
                processData: false,
                contentType: false,
                dataType: "json",
                success: function (r) {
                    console.log(JSON.stringify(r))
                    if (r.msg == 'false') {
                        alert('您不具备上传该密级条件');
                        return;
                    }
                    if (r.msg == 'false1') {
                        alert('密标程序未启动');
                        return;
                    }
                    var obj = new Object();
                    $("#fileList").val("");
                    obj['id'] = r.id;
                    obj['filePath'] = r.path;
                    obj['fileName'] = r.fileName;
                    obj['mbfklj'] = r.mbfklj;
                    vm.allFiles.push(obj);
                    vm.znhd.files = vm.allFiles;
                    alert("导入成功！");
                },
                error: function () {
                    alert("导入失败！");
                }
            })
        },
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.equipmentList = {};
            vm.title = "新增";
            vm.znhd = {deptName: null, deptId: null};
            $('#content_sn').summernote('code', null);
            vm.getZylxs()
            vm.getDept();
        },
        update: function () {
            var id = getSelectedRow();
            if (id == null) {
                return;
            }
            vm.getZylxs()
            vm.showList = false;
            vm.title = "修改";
            vm.getZnhd(id)
        },
        sjpq: function () {
            var param = {}

            $.ajax({
                type: "POST",
                url: baseURL + "sysZnhd/sjpq",
                contentType: "application/json",
                data: JSON.stringify(param),
                success: function (r) {
                    if (r.code == 0) {
                        alert('爬虫执行成功', function () {
                            vm.reload();
                        });
                    } else {
                        alert(r.msg);
                    }
                }
            });
        },
        del: function () {
            var ids = getSelectedRows();
            if (ids == null) {
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                $.ajax({
                    type: "POST",
                    url: baseURL + "sysZnhd/delete",
                    contentType: "application/json",
                    data: JSON.stringify(ids),
                    success: function (r) {
                        if (r.code == 0) {
                            alert('操作成功', function () {
                                vm.reload();
                            });
                        } else {
                            alert(r.msg);
                        }
                    }
                });
            });
        },
        getZnhd: function (znhdId) {


            $.get(baseURL + "sysZnhd/info/" + znhdId, function (r) {
                vm.znhd = r.znhd;
                $('#content_sn').summernote('code', vm.znhd.content);
                vm.allFiles = r.znhd.files;
                vm.getDept();
            });
        },
        getZylxs: function () {

            var param = {}
            $.ajax({
                type: "POST",
                url: baseURL + "sysType/getTypes",
                contentType: "application/json",
                data: JSON.stringify(param),
                success: function (r) {
                    if (r.code == 0) {
                        vm.zylxs = r.types;
                    } else {
                        alert(r.msg);
                    }
                }
            });

        },

        saveOrUpdate: function () {
            var content_sn = $("#content_sn").summernote('code');
            $("#content").val(content_sn);
            //vm.message.notifyContent=html_encode(content_sn);  //采用js中直接转义方式,调用公用的转义函数encode
            vm.znhd.content = content_sn;                 //采用过滤器的方式,在数据提交时进行转义
            var url = vm.znhd.id == null ? "sysZnhd/save" : "sysZnhd/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.znhd),
                success: function (r) {
                    if (r.code === 0) {
                        alert('操作成功', function () {
                            vm.reload();
                        });
                    } else {
                        alert(r.msg);
                    }
                }
            });
        },
        getMenuTree: function (id) {
            //加载菜单树
            $.get(baseURL + "sys/menu/list", function (r) {
                menu_ztree = $.fn.zTree.init($("#menuTree"), menu_setting, r);
                //展开所有节点
                menu_ztree.expandAll(true);

                if (id != null) {
                    vm.getZnhd(id);
                }
            });
        },
        getDataTree: function (id) {
            //加载菜单树
            $.get(baseURL + "sys/dept/list", function (r) {
                data_ztree = $.fn.zTree.init($("#dataTree"), data_setting, r);
                //展开所有节点
                data_ztree.expandAll(true);
            });
        },

        getDept: function () {
            //加载部门树
            $.get(baseURL + "sys/dept/list", function (r) {
                dept_ztree = $.fn.zTree.init($("#deptTree"), dept_setting, r);
                var node = dept_ztree.getNodeByParam("deptId", vm.znhd.deptid);
                if (node != null) {
                    dept_ztree.selectNode(node);

                    vm.znhd.deptName = node.name;
                }
            })
        },
        deptTree: function () {
            layer.open({
                type: 1,
                offset: '50px',
                skin: 'layui-layer-molv',
                title: "选择部门",
                area: ['300px', '450px'],
                shade: 0,
                shadeClose: false,
                content: jQuery("#deptLayer"),
                btn: ['确定', '取消'],
                btn1: function (index) {
                    var node = dept_ztree.getSelectedNodes();
                    //选择上级部门
                    vm.znhd.deptid = node[0].deptId;
                    vm.znhd.deptName = node[0].name;

                    layer.close(index);
                }
            });
        },
        crawlNews: function () {
            return request({
                url: '/sys/znhd/crawlNews',
                method: 'get',
            });
        },
        fetchZnhdList: function () {
            return request({
                url: '/sys/znhd/list',
                method: 'get',
                params,
            });
        },
        fetchZnhd: function (id) {
            return request({
                url: '/sys/znhd/info/' + id,
                method: 'get',
            });
        },
        pageChange: function (newPage) {
            this.page = newPage;
            this.fetchNewsList();
        },
        reload: function () {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {'name': vm.q.znhdName},
                page: page
            }).trigger("reloadGrid");
        },

    }
});
laydate.render({
    elem: '#createtime', //指定元素
    format: 'yyyy-MM-dd HH:mm:ss',
    //日期时间选择器
    type: 'datetime',
    done: function (value, date, endDate) {
        vm.znhd.createtime = value;
    }
});
laydate.render({
    elem: '#updatetime', //指定元素
    format: 'yyyy-MM-dd HH:mm:ss',
    //日期时间选择器
    type: 'datetime',
    done: function (value, date, endDate) {
        vm.znhd.updatetime = value;
    }
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        newsList: [],
        page: 1,
        limit: 10,
        total: 0
    },
    methods: {
        loadNews: function () {
            request({
                url: '/sys/znhd/listForWeb',
                method: 'get',
                params: {
                    page: this.page,
                    limit: this.limit
                }
            }).then(res => {
                this.newsList = res.page.records;
                this.total = res.page.total;
            });
        },
        // 分页切换
        pageChange: function (current) {
            this.page = current;
            this.loadNews();
        }
    },
    mounted: function () {
        this.loadNews();
    }
});