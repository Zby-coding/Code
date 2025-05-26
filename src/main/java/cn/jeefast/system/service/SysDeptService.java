package cn.jeefast.system.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;

import cn.jeefast.system.entity.SysDept;

/**
 * <p>
 * 班级管理 服务类
 * </p>
 */
public interface SysDeptService extends IService<SysDept> {
	List<SysDept> queryList(Map<String, Object> map);
	
	/**
	 * 查询子班级ID列表
	 * @param parentId  上级班级ID
	 */
	public List<Long> queryDetpIdList(Long parentId);
	
	/**
	 * 获取子班级ID(包含本班级ID)，用于数据过滤
	 */
	String getSubDeptIdList(Long deptId);
}
