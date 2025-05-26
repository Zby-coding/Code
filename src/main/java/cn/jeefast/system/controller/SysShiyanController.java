package cn.jeefast.system.controller;


import cn.jeefast.common.annotation.Log;
import cn.jeefast.common.base.BaseController;
import cn.jeefast.common.utils.Query;
import cn.jeefast.common.utils.R;
import cn.jeefast.common.validator.ValidatorUtils;
import cn.jeefast.system.entity.SysShiyan;
import cn.jeefast.system.entity.TMaterialFile;
import cn.jeefast.system.service.SysShiyanService;
import cn.jeefast.system.service.TMaterialFileService;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * <p>
 * 视频資源信息表 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/sysShiyan")
public class SysShiyanController extends BaseController {


    @Autowired
    private SysShiyanService sysShiyanService;

    @Autowired
    private TMaterialFileService tMaterialFileService;

    /**
     * 视频資源信息表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:shiyan:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        params.put("type","1");
        Query query = new Query(params);
        Page<SysShiyan> pageUtil = new Page<SysShiyan>(query.getPage(), query.getLimit());
        Page<SysShiyan> page = sysShiyanService.queryPageList(pageUtil, query);
        return R.ok().put("page", page);
    }

    /**
     * 视频資源信息表信息
     */
    @RequestMapping("/info/{shiyanId}")
    @RequiresPermissions("sys:shiyan:info")
    public R info(@PathVariable("shiyanId") String shiyanId) {
        SysShiyan shiyan = sysShiyanService.selectById(shiyanId);
        //获取附件列表
        List<TMaterialFile> tMaterialFiles = tMaterialFileService.selectList(new EntityWrapper<TMaterialFile>().eq("parentid",shiyan.getId()));
        System.out.println("tMaterialFilestMaterialFilestMaterialFiles"+tMaterialFiles);
        List<Map<String,Object>> mapList = new ArrayList<>();
        if(!tMaterialFiles.isEmpty()){
            for(TMaterialFile tMaterialFile:tMaterialFiles){
                Map<String,Object> map =new HashMap<>();
                map.put("id",tMaterialFile.getId());
                map.put("filePath",tMaterialFile.getSfilename());
                map.put("fileName",tMaterialFile.getSaccessoryname());
                mapList.add(map);
            }
        }
        JSONArray json = (JSONArray) JSONArray.toJSON(mapList);
        shiyan.setFiles(json);
        return R.ok().put("shiyan", shiyan);
    }

    /**
     * 保存视频資源信息表
     */
    @Log("保存视频資源信息表")
    @RequestMapping("/save")
    @RequiresPermissions("sys:shiyan:save")
    public R save(@RequestBody SysShiyan shiyan) {
        ValidatorUtils.validateEntity(shiyan);
        shiyan.setCreateuser(getUser().getUsername());
        shiyan.setUpdateuser(getUser().getUsername());
        shiyan.setCreatetime(new Date());
        shiyan.setUpdatetime(new Date());
        sysShiyanService.insert(shiyan);
        if(shiyan.getFiles() != null){
            tMaterialFileService.setTMaterialFilePrintId(shiyan.getFiles(),shiyan.getId());
        }
        return R.ok();
    }

    /**
     * 修改视频資源信息表
     */
    @Log("修改视频資源信息表")
    @RequestMapping("/update")
    @RequiresPermissions("sys:shiyan:update")
    public R update(@RequestBody SysShiyan shiyan) {
        ValidatorUtils.validateEntity(shiyan);
        shiyan.setUpdateuser(getUser().getUsername());
        shiyan.setUpdatetime(new Date());
        sysShiyanService.updateById(shiyan);
        if(shiyan.getFiles() != null){
            tMaterialFileService.setTMaterialFilePrintId(shiyan.getFiles(),shiyan.getId());
        }
        return R.ok();
    }

    /**
     * 删除视频資源信息表
     */
    @Log("删除视频資源信息表")
    @RequestMapping("/delete")
    @RequiresPermissions("sys:shiyan:delete")
    public R delete(@RequestBody String[] shiyanIds) {
        sysShiyanService.deleteBatch(shiyanIds);
        return R.ok();
    }
}
