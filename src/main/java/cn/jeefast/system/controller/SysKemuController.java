package cn.jeefast.system.controller;


import cn.jeefast.common.annotation.Log;
import cn.jeefast.common.utils.Query;
import cn.jeefast.common.utils.R;
import cn.jeefast.common.validator.ValidatorUtils;
import cn.jeefast.system.entity.SysKemu;
import cn.jeefast.system.entity.SysUser;
import cn.jeefast.system.entity.TMaterialFile;
import cn.jeefast.system.service.SysKemuService;
import cn.jeefast.system.service.SysUserService;
import cn.jeefast.system.service.TMaterialFileService;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cn.jeefast.common.base.BaseController;

import java.net.UnknownHostException;
import java.util.*;

/**
 * <p>
 * 科目管理 前端控制器
 * </p>
 *
 */
@RestController
@RequestMapping("/sysKemu")
public class SysKemuController extends BaseController {
    @Autowired
    private SysKemuService sysKemuService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private TMaterialFileService tMaterialFileService;

    /**
     * 科目管理
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:kemu:list")
    public R list(@RequestParam Map<String, Object> params) throws UnknownHostException {
        //查询列表数据
        Query query = new Query(params);
        Page<SysKemu> pageUtil = new Page<SysKemu>(query.getPage(), query.getLimit());
        Page<SysKemu> page = sysKemuService.queryPageList(pageUtil, query);
        return R.ok().put("page", page);
    }

    /**
     * 科目管理信息
     */
    @RequestMapping("/info/{kemuId}")
    @RequiresPermissions("sys:kemu:info")
    public R info(@PathVariable("kemuId") String kemuId) {
        SysKemu kemu = sysKemuService.selectById(kemuId);
        //获取附件列表
        List<TMaterialFile> tMaterialFiles = tMaterialFileService.selectList(new EntityWrapper<TMaterialFile>().eq("parentid",kemu.getId()));
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

        kemu.setFiles(json);
        return R.ok().put("kemu", kemu);
    }

    /**
     * 科目管理信息
     */
    @RequestMapping("/getKemus")
    public R getKemus() {
        List<SysKemu> kemus = sysKemuService.selectList(new EntityWrapper<>());
        return R.ok().put("kemus", kemus);
    }

    /**
     * 科目管理信息
     */
    @RequestMapping("/getTikuKemu")
    public R getTikuKemu() {
        SysUser sysUser = sysUserService.selectOne(new EntityWrapper<SysUser>().eq("username",getUser().getUsername()));
        Wrapper wrapper = new EntityWrapper<>();
//        if(sysUser.getType().equals("3")){//教师只能选择自己课程下的科目
//            wrapper.eq("jiaoshiuser",sysUser.getUsername());
//        }
        List<SysKemu> kemus = sysKemuService.selectList(wrapper);
        System.out.println("kemuskemuskemuskemus"+kemus);
        return R.ok().put("kemus", kemus);
    }


    /**
     * 保存科目管理
     */
    @Log("保存科目管理")
    @RequestMapping("/save")
    @RequiresPermissions("sys:kemu:save")
    public R save(@RequestBody SysKemu kemu) {
        ValidatorUtils.validateEntity(kemu);
        kemu.setCreatetime(new Date());
        kemu.setCreateuser(getUser().getUsername());
        kemu.setUpdatetime(new Date());
        kemu.setUpdateuser(getUser().getUsername());
        sysKemuService.insert(kemu);
        if(kemu.getFiles() != null){
            tMaterialFileService.setTMaterialFilePrintId(kemu.getFiles(),kemu.getId());
        }
        return R.ok();
    }

    /**
     * 修改科目管理
     */
    @Log("修改科目管理")
    @RequestMapping("/update")
    @RequiresPermissions("sys:kemu:update")
    public R update(@RequestBody SysKemu kemu) {
        ValidatorUtils.validateEntity(kemu);
        kemu.setUpdatetime(new Date());
        kemu.setUpdateuser(getUser().getUsername());
        sysKemuService.updateById(kemu);
        if(kemu.getFiles() != null){
            tMaterialFileService.setTMaterialFilePrintId(kemu.getFiles(),kemu.getId());
        }
        return R.ok();
    }

    /**
     * 删除科目管理
     */
    @Log("删除科目管理")
    @RequestMapping("/delete")
    @RequiresPermissions("sys:kemu:delete")
    public R delete(@RequestBody String[] kemuIds) {
        sysKemuService.deleteBatch(kemuIds);
        return R.ok();
    }
}
