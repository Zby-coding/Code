package cn.jeefast.system.controller;


import cn.jeefast.common.base.BaseController;
import cn.jeefast.common.tokenization.TokenizationService;
import cn.jeefast.common.utils.R;
import cn.jeefast.common.validator.Assert;
import cn.jeefast.system.entity.*;
import cn.jeefast.system.service.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/sysIndexQd")
public class SysIndexQdController extends BaseController {

    @Value("${server.port}")
    private String serverport;

    @Value("${server.context-path}")
    private String servercontextpath;

    @Autowired
    private SysZnhdService sysZnhdService;

    @Autowired
    private cn.jeefast.common.tokenization.TokenizationService tokenizationService;
    @Autowired
    private SysUserTokenService sysUserTokenService;

    @Autowired
    private SysUserService sysUserService;


    @Autowired
    private TMaterialFileService tMaterialFileService;

    @Autowired
    private SysShiyanService sysShiyanService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysKemuService sysKemuService;


    @Value("${upload.flespath}")
    public String flespath;


    /**
     * 获取用户信息
     */
    @RequestMapping("/getUser")
    public R getUser(@RequestBody Map<String, Object> tjarray) throws UnknownHostException {
        System.out.println("tjarraytjarray" + tjarray);

        SysUserToken sysUserToken = sysUserTokenService.selectOne(new EntityWrapper<SysUserToken>().eq("token", tjarray.get("token") + ""));
        SysUser user = sysUserService.selectById(sysUserToken.getUserId());

        /**
         * 设置头像信息
         */
        List<TMaterialFile> tMaterialFileList = tMaterialFileService.selectList(new EntityWrapper<TMaterialFile>().eq("parentid", user.getUserId()));
//        SysUserToken sysUserToken = sysUserTokenService.selectOne(new EntityWrapper<SysUserToken>().eq("user_id", getUserId()));
        System.out.println("tMaterialFileListtMaterialFileList" + tMaterialFileList);
        InetAddress address = InetAddress.getLocalHost();
        user.setPhotopath(tMaterialFileList != null && tMaterialFileList.size() > 0 ? "http://" + address.getHostAddress() + ":" + serverport + "/" + servercontextpath + "/upload/" + tMaterialFileList.get(0).getSfilename() + "?token=" + sysUserToken.getToken() : "img/usermm.jpg");
        System.out.println("useruseruseruser" + user);


        //获取用户所属的角色列表
        List<Long> roleIdList = sysUserRoleService.queryRoleIdList(user.getUserId());
        user.setRoleIdList(roleIdList);

        //获取附件列表
        List<TMaterialFile> tMaterialFiles = tMaterialFileService.selectList(new EntityWrapper<TMaterialFile>().eq("parentid", user.getUserId()));
        List<Map<String, Object>> mapList = new ArrayList<>();
        if (!tMaterialFiles.isEmpty()) {
            for (TMaterialFile tMaterialFile : tMaterialFiles) {
                Map<String, Object> map = new HashMap<>();
                map.put("id", tMaterialFile.getId());
                map.put("filePath", tMaterialFile.getSfilename());
                map.put("fileName", tMaterialFile.getSaccessoryname());
                mapList.add(map);
            }
        }
        JSONArray json = (JSONArray) JSONArray.toJSON(mapList);
        user.setFiles(json);

        return R.ok().put("sysuser", user);

    }

    /**
     * 头像更新
     */
    @RequestMapping("/userSaveFile")
    public R userSaveFile(@RequestBody JSONObject param) {
        System.out.println("paramparamparam" + JSON.toJSONString(param));
        String userid = param.getString("userid");
        JSONArray allFiles = param.getJSONArray("allFiles");
        System.out.println("useriduseriduserid" + userid);
        System.out.println("allFilesallFilesallFiles" + JSON.toJSONString(allFiles));
        List<TMaterialFile> tMaterialFiles = tMaterialFileService.selectList(new EntityWrapper<TMaterialFile>().eq("parentid", userid));
        if (tMaterialFiles.size() > 0) {
            for (int i = 0; i < tMaterialFiles.size(); i++) {
                tMaterialFileService.deleteById(tMaterialFiles.get(i).getId());
            }
        }
        if (allFiles.size() > 0) {
            for (int i = 0; i < allFiles.size(); i++) {
                Map map = (Map) allFiles.get(i);
                TMaterialFile tMaterialFile = tMaterialFileService.selectById(map.get("id").toString());
                tMaterialFile.setParentid(userid);
                tMaterialFileService.updateById(tMaterialFile);
            }
        }
        return R.ok();
    }

    /**
     * 个人信息更新
     */
    @RequestMapping("/updateuser")
    public R updateuser(@RequestBody SysUser sysUser) {
        System.out.println("sysUsersysUser" + sysUser);
        sysUserService.updateById(sysUser);
        return R.ok();

    }

    /**
     * 个人更改密码
     */
    @RequestMapping("/updatepassword")
    public R updatepassword(@RequestBody JSONObject tjarray) {
        String token = tjarray.getString("token");
        SysUserToken sysUserToken = sysUserTokenService.selectOne(new EntityWrapper<SysUserToken>().eq("token", token));
        SysUser user = sysUserService.selectById(sysUserToken.getUserId());


        String oldpassword = tjarray.getString("oldpassword");
        String newPassword = tjarray.getString("password");


        Assert.isBlank(newPassword, "新密码不为能空");

        //sha256加密
        oldpassword = new Sha256Hash(oldpassword, user.getSalt()).toHex();
        //sha256加密
        newPassword = new Sha256Hash(newPassword, user.getSalt()).toHex();
        if (!oldpassword.equals(user.getPassword())) {
            return R.error("原密码不正确无法修改密码");
        }

//        SysUser user = new SysUser();
        user.setUserId(user.getUserId());
        user.setPassword(newPassword);
        //更新密码
        sysUserService.updateById(user);
        return R.ok();

    }

    /**
     * 视频資源列表
     */
    @RequestMapping("/getShiyanListAll")
    public R getShiyanListAll(@RequestBody Map<String, Object> tjarray) throws UnknownHostException {
        System.out.println("tjarraytjarraytjarray" + tjarray);
        Wrapper<SysShiyan> wrapper = new EntityWrapper<SysShiyan>();
//        if(tjarray.get("type") != null){
//            wrapper.eq("type",tjarray.get("type"));
//        }
        wrapper.orderBy(true, "updatetime", false);
        List<SysShiyan> foodList = sysShiyanService.selectList(wrapper);
        if (foodList.size() > 0) {
            for (int i = 0; i < foodList.size(); i++) {
                SysShiyan sysShiyan = foodList.get(i);
                if (sysShiyan.getType() != null) {
                    SysKemu sysKemu = sysKemuService.selectById(sysShiyan.getType());
                    if (sysKemu != null) {
                        sysShiyan.setTypename(sysKemu.getName());
                    }
                }
            }
        }

        System.out.println("foodListfoodListfoodList" + foodList);
        return R.ok().put("foodList", foodList);

    }

    /**
     * 视频資源详情
     */
    @RequestMapping("/getShiyan")
    public R getShiygetShiyananListAll(@RequestBody Map<String, Object> tjarray) throws UnknownHostException {
        if (tjarray.get("id") == null) {
            return R.error("请选择要查看的视频資源信息");
        }
        String id = tjarray.get("id") + "";
        System.out.println("ididididididididididididid" + id);
        SysShiyan sysShiyan = sysShiyanService.selectById(id);
        List<TMaterialFile> tMaterialFiles = tMaterialFileService.selectList(new EntityWrapper<TMaterialFile>().eq("parentid", sysShiyan.getId()));

        System.out.println("tMaterialFilestMaterialFiles" + tMaterialFiles);
        if (tMaterialFiles.size() > 0) {
            InetAddress address = InetAddress.getLocalHost();
            sysShiyan.setVideo("http://" + address.getHostAddress() + ":" + serverport + "/" + servercontextpath + "/upload/" + tMaterialFiles.get(0).getSfilename());
            System.out.println("ssssssssssssssssssss" + sysShiyan);
        }


        System.out.println("sysShiyansysShiyansysShiyan" + sysShiyan);
        return R.ok().put("food", sysShiyan);

    }


    @Autowired
    private SysYqfatieService sysYqfatieService;

    /**
     * 疫情物资领用记录
     */
    @RequestMapping("/fabu")
    public R fabu(@RequestBody JSONObject param) {
        String token = param.getString("token");
        SysUserToken sysUserToken = sysUserTokenService.selectOne(new EntityWrapper<SysUserToken>().eq("token", token));
        SysUser user = sysUserService.selectById(sysUserToken.getUserId());
        String name = param.getString("name");
        String content = param.getString("content");
        SysYqfatie sysYqfatie = new SysYqfatie();
        sysYqfatie.setName(name);
        sysYqfatie.setContent(content);
        sysYqfatie.setUpdatetime(new Date());
        sysYqfatie.setUpdateuser(user.getUsername());
        sysYqfatie.setCreatetime(new Date());
        sysYqfatie.setCreateuser(user.getUsername());
        sysYqfatieService.insert(sysYqfatie);
        return R.ok();
    }


    /**
     * 疫情物资领用记录
     */
    @RequestMapping("/getYqfatieList")
    public R getYqfatieList(@RequestBody JSONObject param) {
        List<SysYqfatie> yqfatieList = sysYqfatieService.selectList(new EntityWrapper<SysYqfatie>().orderBy(true, "updatetime", false));
        return R.ok().put("yqfatieList", yqfatieList);
    }

    /**
     * 疫情物资领用记录
     */
    @RequestMapping("/getWdYqfatieList")
    public R getWdYqfatieList(@RequestBody JSONObject param) {
        String token = param.getString("token");
        SysUserToken sysUserToken = sysUserTokenService.selectOne(new EntityWrapper<SysUserToken>().eq("token", token));
        SysUser user = sysUserService.selectById(sysUserToken.getUserId());
        List<SysYqfatie> yqfatieList = sysYqfatieService.selectList(new EntityWrapper<SysYqfatie>().eq("createuser", user.getUsername()).orderBy(true, "updatetime", false));
        return R.ok().put("yqfatieList", yqfatieList);
    }


    @Autowired
    private SysYqfthfbService sysYqfthfbService;

    /**
     * 论坛回复表
     */
    @RequestMapping("/getYqfthfbList")
    public R getYqfthfbList(@RequestBody JSONObject param) {
        String yqfatieid = param.getString("yqfatieid");
        List<SysYqfthfb> yqfthfbList = sysYqfthfbService.selectList(new EntityWrapper<SysYqfthfb>().eq("yqfatieid", yqfatieid).orderBy(true, "updatetime", false));
        return R.ok().put("yqfthfbList", yqfthfbList);
    }

    @RequestMapping("/saveYqfthfb")
    public R saveYqfthfb(@RequestBody JSONObject param) {
        String token = param.getString("token");
        SysUserToken sysUserToken = sysUserTokenService.selectOne(new EntityWrapper<SysUserToken>().eq("token", token));
        SysUser user = sysUserService.selectById(sysUserToken.getUserId());
        String content = param.getString("hfcontent");
        String hfpjid = param.getString("hfpjid");

        SysYqfthfb sysYqfthfb = new SysYqfthfb();
        sysYqfthfb.setYqfatieid(hfpjid);
        sysYqfthfb.setContent(content);
        sysYqfthfb.setUpdatetime(new Date());
        sysYqfthfb.setUpdateuser(user.getUsername());
        sysYqfthfb.setCreatetime(new Date());
        sysYqfthfb.setCreateuser(user.getUsername());
        sysYqfthfbService.insert(sysYqfthfb);
        return R.ok();
    }

    @RequestMapping("/shanchu")
    public R shanchu(@RequestBody JSONObject param) {
        String tzid = param.getString("tzid");
        sysYqfatieService.deleteById(tzid);
        return R.ok();
    }


    @RequestMapping(value = "/ceshi", method = RequestMethod.POST)
    @ResponseBody
    public R a(@RequestParam("file") MultipartFile file) throws Exception {
        System.out.println("paramparamparam" + JSON.toJSONString(file));
        System.out.println("getOriginalFilenamegetOriginalFilename" + file.getOriginalFilename());
        String dataPath = flespath;
        Map<String, String> map = new HashMap<>();
        boolean ff = false;
        // 得到上传文件的保存目录，将上传文件存放在WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
        String prefix = new SimpleDateFormat("yyyyMMdd").format(new Date());
        File dir = new File(dataPath + "/" + prefix);
        File saveFileDir = new File(dataPath);
        if (!saveFileDir.exists()) {
            // 创建目录
            saveFileDir.mkdirs();
            if (!dir.exists()) {
                dir.mkdirs();
            }
        } else {
            if (!dir.exists()) {
                dir.mkdirs();
            }
        }


        // 一次遍历所有文件
        if (file != null) {
            String fileName = file.getOriginalFilename();
            String fileid = UUID.randomUUID().toString().replace("-", "");
            //          System.out.println("fileNamefileName"+fileName);
            // 处理获取到的上传文件的文件名的路径部分，只保留文件名部分
            fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
            // 得到存文件的扩展名
            String suffix = makeFileName(fileName);
            String saveFileName = UUID.randomUUID() + suffix;
            String path = prefix + "/" + saveFileName;
            file.transferTo(new File(dir + "/" + saveFileName));
            /* 存储文件信息 */
            map = new HashMap<>();
            map.put("fileName", fileName);
            map.put("path", path);
            map.put("ids", saveFileName);
            map.put("id", fileid);
            map.put("dir", dir + "");
            map.put("originalFilename", fileName);
//            map.put("mbfklj", mbfkljToName(request.getParameter("fileList111")));

            TMaterialFile tMaterialFile = setFiles(path, dataPath + "/", map);
            map.put("sfilename", tMaterialFile.getSfilename());
            ff = true;
        }

        InetAddress address = InetAddress.getLocalHost();
        String url = "http://" + address.getHostAddress() + ":" + serverport + "/" + servercontextpath + "/upload/" + map.get("sfilename");
        System.out.println("urlurlurlurlurlurl" + url);
        return R.ok().put("url", url);
    }

    private String makeFileName(String fileName) {
        int lastIndex = fileName.lastIndexOf(".");

        if (lastIndex != -1) {
            return fileName.substring(lastIndex);
        } else {
            return "";
        }
    }

    private TMaterialFile setFiles(String sfilename, String sfilelength, Map<String, String> map) throws Exception {
        TMaterialFile file = new TMaterialFile();
        file.setId(map.get("id").toString());
        file.setSfilename(sfilename);
        file.setSfilelength(sfilelength);
        file.setSaccessoryname(map.get("originalFilename") + "");
        System.out.println("bbbbbbbbbbbbbbbb" + file);
        tMaterialFileService.insert(file);
        return file;
    }

    /**
     * 科目列表
     */
    @RequestMapping("/getKemuListAll")
    public R getKemuListAll(@RequestBody JSONObject param) {
        List<SysKemu> kemuList = sysKemuService.selectList(new EntityWrapper<>());
        return R.ok().put("kemuList", kemuList);
    }

    @RequestMapping("/getKemu")
    public R getKemu(@RequestBody JSONObject param) {
        String id = param.getString("id");
        SysKemu kemu = sysKemuService.selectById(id);
        //获取附件列表
        List<TMaterialFile> tMaterialFiles = tMaterialFileService.selectList(new EntityWrapper<TMaterialFile>().eq("parentid", kemu.getId()));
        List<Map<String, Object>> mapList = new ArrayList<>();
        if (!tMaterialFiles.isEmpty()) {
            for (TMaterialFile tMaterialFile : tMaterialFiles) {
                Map<String, Object> map = new HashMap<>();
                map.put("id", tMaterialFile.getId());
                map.put("filePath", tMaterialFile.getSfilename());
                map.put("fileName", tMaterialFile.getSaccessoryname());
                mapList.add(map);
            }

        }
        JSONArray json = (JSONArray) JSONArray.toJSON(mapList);

        kemu.setFiles(json);
        return R.ok().put("kemu", kemu);
    }


    @PostMapping("/getZnhds")
    public R getZnhds(@RequestBody JSONObject param) {
        String gjc = param.getString("gjc");
        // 获取显示数量限制，默认为20条
        Integer limit = param.getInteger("limit");
        if (limit == null || limit <= 0) {
            limit = 20; // 默认显示20条数据
        }

        // 使用新的分词服务进行分词
        List<String> keywords = tokenizationService.tokenize(gjc);
        Set<SysZnhd> znhdsSet = new HashSet<>();

        for (String keyword : keywords) {
            System.out.println("分词结果: " + keyword);
            // 使用分词服务的最小长度过滤，不需要在这里再次判断长度
            System.out.println("搜索关键词: " + keyword);
            List<SysZnhd> sysZnhds = sysZnhdService.selectList(new EntityWrapper<SysZnhd>().like("name", keyword));
            System.out.println("搜索结果: " + sysZnhds);
            znhdsSet.addAll(sysZnhds);

            // 同时搜索内容字段，提高搜索覆盖面
            List<SysZnhd> contentResults = sysZnhdService.selectList(new EntityWrapper<SysZnhd>().like("content", keyword));
            znhdsSet.addAll(contentResults);
        }

        // 转换为List以便进行排序和限制数量
        List<SysZnhd> znhds = new ArrayList<>(znhdsSet);

        // 按更新时间降序排序
        Collections.sort(znhds, new Comparator<SysZnhd>() {
            @Override
            public int compare(SysZnhd o1, SysZnhd o2) {
                if (o1.getUpdatetime() == null || o2.getUpdatetime() == null) {
                    return 0;
                }
                return o2.getUpdatetime().compareTo(o1.getUpdatetime());
            }
        });

        // 限制返回的数据量
        if (znhds.size() > limit) {
            znhds = znhds.subList(0, limit);
        }

        return R.ok().put("znhdList", znhds).put("total", znhdsSet.size());
    }

    @RequestMapping("/getZnhd")
    public R getZnhd(@RequestBody JSONObject param) {
        String id = param.getString("id");
        SysZnhd znhd = sysZnhdService.selectById(id);
        return R.ok().put("znhd", znhd);
    }


    @PostMapping("/getZnhdListAll")
    public R getZnhdListAll(@RequestBody Map<String, Object> params) {
        try {
            // 显式使用MyBatis-Plus的查询条件（空条件查询所有记录）
            List<SysZnhd> znhdList = sysZnhdService.selectList(new EntityWrapper<>());
            return R.ok().put("znhdList", znhdList);
        } catch (Exception e) {
            e.printStackTrace();
            // 返回具体异常信息便于定位问题
            return R.error("获取 znhd 列表失败：" + e.getMessage());
        }
    }
}






