package cn.jeefast.system.controller;


import cn.jeefast.common.annotation.Log;
import cn.jeefast.common.utils.Query;
import cn.jeefast.common.utils.R;
import cn.jeefast.common.validator.ValidatorUtils;
import cn.jeefast.system.entity.SysYqfthfb;
import cn.jeefast.system.service.SysYqfthfbService;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cn.jeefast.common.base.BaseController;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.Map;

/**
 * <p>
 * 疫情发帖回复表 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/sysYqfthfb")
public class SysYqfthfbController extends BaseController {

    @Autowired
    private SysYqfthfbService sysYqfthfbService;



    /**
     * 疫情发帖回复表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:yqfthfb:list")
    public R list(@RequestParam Map<String, Object> params) throws UnknownHostException {
        //查询列表数据
        Query query = new Query(params);
        Page<SysYqfthfb> pageUtil = new Page<SysYqfthfb>(query.getPage(), query.getLimit());
        Page<SysYqfthfb> page = sysYqfthfbService.queryPageList(pageUtil, query);
        return R.ok().put("page", page);
    }

    /**
     * 疫情发帖回复表信息
     */
    @RequestMapping("/info/{yqfthfbId}")
    @RequiresPermissions("sys:yqfthfb:info")
    public R info(@PathVariable("yqfthfbId") String yqfthfbId) {
        SysYqfthfb yqfthfb = sysYqfthfbService.selectById(yqfthfbId);
        return R.ok().put("yqfthfb", yqfthfb);
    }

    /**
     * 保存疫情发帖回复表
     */
    @Log("保存疫情发帖回复表")
    @RequestMapping("/save")
    @RequiresPermissions("sys:yqfthfb:save")
    public R save(@RequestBody SysYqfthfb yqfthfb) {
        ValidatorUtils.validateEntity(yqfthfb);
        yqfthfb.setCreatetime(new Date());
        yqfthfb.setCreateuser(getUser().getUsername());
        yqfthfb.setUpdatetime(new Date());
        yqfthfb.setUpdateuser(getUser().getUsername());
        sysYqfthfbService.insert(yqfthfb);
        return R.ok();
    }

    /**
     * 修改疫情发帖回复表
     */
    @Log("修改疫情发帖回复表")
    @RequestMapping("/update")
    @RequiresPermissions("sys:yqfthfb:update")
    public R update(@RequestBody SysYqfthfb yqfthfb) {
        ValidatorUtils.validateEntity(yqfthfb);
        yqfthfb.setUpdatetime(new Date());
        yqfthfb.setUpdateuser(getUser().getUsername());
        sysYqfthfbService.updateById(yqfthfb);
        return R.ok();
    }

    /**
     * 删除疫情发帖回复表
     */
    @Log("删除疫情发帖回复表")
    @RequestMapping("/delete")
    @RequiresPermissions("sys:yqfthfb:delete")
    public R delete(@RequestBody String[] yqfthfbIds) {
        sysYqfthfbService.deleteBatch(yqfthfbIds);
        return R.ok();
    }
}
