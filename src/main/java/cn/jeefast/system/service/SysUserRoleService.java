package cn.jeefast.system.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

import cn.jeefast.system.entity.SysUserRole;

/**
 * <p>
 * 用户与角色对应关系 服务类
 * </p>
 */
public interface SysUserRoleService extends IService<SysUserRole> {
	void saveOrUpdate(Long userId, List<Long> roleIdList);
	
	/**
	 * 根据用户ID，获取角色ID列表
	 */
	List<Long> queryRoleIdList(Long userId);
	
	void deleteByUserId(Long userId);
}
