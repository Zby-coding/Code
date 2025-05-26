package cn.jeefast.system.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

import cn.jeefast.system.entity.SysRoleDept;

/**
 * <p>
 * 角色与班级对应关系 服务类
 * </p>
 */
public interface SysRoleDeptService extends IService<SysRoleDept> {
	
	void saveOrUpdate(Long roleId, List<Long> deptIdList);
	/**
	 * 根据角色ID，获取班级ID列表
	 */
	List<Long> queryDeptIdList(Long roleId);
	
}
