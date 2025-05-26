package cn.jeefast.system.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import cn.jeefast.system.entity.SysRoleDept;

/**
 * <p>
  * 角色与班级对应关系 Mapper 接口
 * </p>
 */
public interface SysRoleDeptDao extends BaseMapper<SysRoleDept> {

	/**
	 * 根据角色ID，获取班级ID列表
	 */
	List<Long> queryDeptIdList(Long roleId);
	
	void save(Map<String, Object> map);
	
	void deleteByRoleId(Object roleId);
}