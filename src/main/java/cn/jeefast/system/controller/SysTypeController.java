package cn.jeefast.system.controller;


import cn.jeefast.common.annotation.Log;
import cn.jeefast.common.utils.Query;
import cn.jeefast.common.utils.R;
import cn.jeefast.common.validator.ValidatorUtils;
import cn.jeefast.system.entity.SysType;
import cn.jeefast.system.entity.TMaterialFile;
import cn.jeefast.system.service.SysTypeService;
import cn.jeefast.system.service.TMaterialFileService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
 * 类型表 前端控制器
 * </p>
 *
 */
@RestController
@RequestMapping("/sysType")
public class SysTypeController extends BaseController {

    @Autowired
    private SysTypeService sysTypeService;


    /**
     * 类型表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:type:list")
    public R list(@RequestParam Map<String, Object> params) throws UnknownHostException {
        //查询列表数据
        Query query = new Query(params);
        Page<SysType> pageUtil = new Page<SysType>(query.getPage(), query.getLimit());
        Page<SysType> page = sysTypeService.queryPageList(pageUtil, query);
        return R.ok().put("page", page);
    }

    /**
     * 类型表信息
     */
    @RequestMapping("/info/{typeId}")
    @RequiresPermissions("sys:type:info")
    public R info(@PathVariable("typeId") String typeId) {
        SysType type = sysTypeService.selectById(typeId);
        return R.ok().put("type", type);
    }

    /**
     * 类型表信息
     */
    @RequestMapping("/getTypes")
    public R getTypes(@RequestBody JSONObject param) {
        String iswzyc = param.getString("iswzyc");
        Wrapper wrapper = new EntityWrapper<>();
        if(iswzyc != null){
            wrapper.eq("iswzyc",iswzyc);
        }
        List<SysType> types = sysTypeService.selectList(wrapper);
        System.out.println("typestypes"+JSONArray.toJSONString(types));
        return R.ok().put("types", types);
    }

    /**
     * 保存类型表
     */
    @Log("保存类型表")
    @RequestMapping("/save")
    @RequiresPermissions("sys:type:save")
    public R save(@RequestBody SysType type) {
        ValidatorUtils.validateEntity(type);
        type.setCreatetime(new Date());
        type.setCreateuser(getUser().getUsername());
        type.setUpdatetime(new Date());
        type.setUpdateuser(getUser().getUsername());
        sysTypeService.insert(type);
        return R.ok();
    }

    /**
     * 修改类型表
     */
    @Log("修改类型表")
    @RequestMapping("/update")
    @RequiresPermissions("sys:type:update")
    public R update(@RequestBody SysType type) {
        ValidatorUtils.validateEntity(type);
        type.setUpdatetime(new Date());
        type.setUpdateuser(getUser().getUsername());
        sysTypeService.updateById(type);
        return R.ok();
    }

    /**
     * 删除类型表
     */
    @Log("删除类型表")
    @RequestMapping("/delete")
    @RequiresPermissions("sys:type:delete")
    public R delete(@RequestBody String[] typeIds) {
        sysTypeService.deleteBatch(typeIds);
        return R.ok();
    }
}
