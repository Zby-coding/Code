package cn.jeefast.system.controller;

import cn.jeefast.common.annotation.Log;
import cn.jeefast.common.base.DeduplicationBaseController;
import cn.jeefast.common.utils.Query;
import cn.jeefast.common.utils.R;
import cn.jeefast.common.validator.ValidatorUtils;
import cn.jeefast.system.entity.SysYqfatie;
import cn.jeefast.system.service.SysYqfatieService;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.Map;

/**
 * <p>
 * 发帖信息表 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/sysYqfatie")
public class SysYqfatieController extends DeduplicationBaseController {
    
    private static final Logger logger = LoggerFactory.getLogger(SysYqfatieController.class);

    @Autowired
    private SysYqfatieService sysYqfatieService;

    /**
     * 发帖信息表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:yqfatie:list")
    public R list(@RequestParam Map<String, Object> params) throws UnknownHostException {
        //查询列表数据
        Query query = new Query(params);
        Page<SysYqfatie> pageUtil = new Page<SysYqfatie>(query.getPage(), query.getLimit());
        Page<SysYqfatie> page = sysYqfatieService.queryPageList(pageUtil, query);
        
        // 应用数据脱敏，过滤掉已经加载过的重复数据
        Page<SysYqfatie> dedupPage = applyDeduplication(page, SysYqfatie::getId);
        
        return R.ok().put("page", dedupPage);
    }

    /**
     * 发帖信息表信息
     */
    @RequestMapping("/info/{yqfatieId}")
    @RequiresPermissions("sys:yqfatie:info")
    public R info(@PathVariable("yqfatieId") String yqfatieId) {
        SysYqfatie yqfatie = sysYqfatieService.selectById(yqfatieId);
        return R.ok().put("yqfatie", yqfatie);
    }

    /**
     * 保存发帖信息表
     */
    @Log("保存发帖信息表")
    @RequestMapping("/save")
    @RequiresPermissions("sys:yqfatie:save")
    public R save(@RequestBody SysYqfatie yqfatie) {
        ValidatorUtils.validateEntity(yqfatie);
        yqfatie.setCreatetime(new Date());
        yqfatie.setCreateuser(getUser().getUsername());
        yqfatie.setUpdatetime(new Date());
        yqfatie.setUpdateuser(getUser().getUsername());
        sysYqfatieService.insert(yqfatie);
        return R.ok();
    }

    /**
     * 修改发帖信息表
     */
    @Log("修改发帖信息表")
    @RequestMapping("/update")
    @RequiresPermissions("sys:yqfatie:update")
    public R update(@RequestBody SysYqfatie yqfatie) {
        ValidatorUtils.validateEntity(yqfatie);
        yqfatie.setUpdatetime(new Date());
        yqfatie.setUpdateuser(getUser().getUsername());
        sysYqfatieService.updateById(yqfatie);
        return R.ok();
    }

    /**
     * 删除发帖信息表
     */
    @Log("删除发帖信息表")
    @RequestMapping("/delete")
    @RequiresPermissions("sys:yqfatie:delete")
    public R delete(@RequestBody String[] yqfatieIds) {
        sysYqfatieService.deleteBatch(yqfatieIds);
        return R.ok();
    }
    
    /**
     * 清除数据脱敏缓存
     */
    @Log("清除数据脱敏缓存")
    @RequestMapping("/clearDeduplicationCache")
    @RequiresPermissions("sys:yqfatie:list")
    public R resetDeduplicationCache() {
        clearDeduplicationCache();
        return R.ok("数据脱敏缓存已清除");
    }
}