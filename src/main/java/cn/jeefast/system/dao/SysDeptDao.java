package cn.jeefast.system.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import cn.jeefast.system.entity.SysDept;

/**
 * <p>
  * 班级管理 Mapper 接口
 * </p>
 */
public interface SysDeptDao extends BaseMapper<SysDept> {
	
	List<SysDept> queryList(Map<String, Object> map);
	
	/**
     * 查询子班级ID列表
     * @param parentId  上级班级ID
     */
    List<Long> queryDetpIdList(Long parentId);

}