package cn.jeefast.system.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.jeefast.common.base.BaseController;
import cn.jeefast.common.utils.Constant;
import cn.jeefast.common.utils.R;
import cn.jeefast.system.entity.SysDept;
import cn.jeefast.system.service.SysDeptService;

import java.util.HashMap;
import java.util.List;


/**
 * 班级管理
 */
@RestController
@RequestMapping("/sys/dept")
public class SysDeptController extends BaseController {
	@Autowired
	private SysDeptService sysDeptService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	//@RequiresPermissions("sys:dept:list")
	public List<SysDept> list(){
		List<SysDept> deptList = sysDeptService.queryList(new HashMap<>());

		return deptList;
	}

	/**
	 * 选择班级(添加、修改菜单)
	 */
	@RequestMapping("/select")
	//@RequiresPermissions("sys:dept:select")
	public R select(){
		List<SysDept> deptList = sysDeptService.queryList(new HashMap<>());

		//添加一级班级
		if(getUserId() == Constant.SUPER_ADMIN){
			SysDept root = new SysDept();
			root.setDeptId(0L);
			root.setName("一级班级");
			root.setParentId(-1L);
			root.setOpen(true);
			deptList.add(root);
		}

		return R.ok().put("deptList", deptList);
	}

	/**
	 * 上级班级Id(管理员则为0)
	 */
	@RequestMapping("/info")
	//@RequiresPermissions("sys:dept:list")
	public R info(){
		long deptId = 0;
		if(getUserId() != Constant.SUPER_ADMIN){
			SysDept dept = sysDeptService.selectById(getDeptId());
			deptId = dept.getParentId();
		}

		return R.ok().put("deptId", deptId);
	}
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{deptId}")
	//@RequiresPermissions("sys:dept:info")
	public R info(@PathVariable("deptId") Long deptId){
		SysDept dept = sysDeptService.selectById(deptId);
		
		return R.ok().put("dept", dept);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	//@RequiresPermissions("sys:dept:save")
	public R save(@RequestBody SysDept dept){
		sysDeptService.insert(dept);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	//@RequiresPermissions("sys:dept:update")
	public R update(@RequestBody SysDept dept){
		sysDeptService.updateById(dept);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	//@RequiresPermissions("sys:dept:delete")
	public R delete(long deptId){
		//判断是否有子班级
		List<Long> deptList = sysDeptService.queryDetpIdList(deptId);
		if(deptList.size() > 0){
			return R.error("请先删除子班级");
		}
        //逻辑删除
		SysDept dept = new SysDept();
		dept.setDeptId(deptId);
		dept.setDelFlag(-1);
		sysDeptService.updateById(dept);
		//物理删除
		//sysDeptService.delete(deptId);
		
		return R.ok();
	}
	
}
