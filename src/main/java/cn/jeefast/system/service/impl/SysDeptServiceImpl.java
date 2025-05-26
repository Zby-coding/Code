package cn.jeefast.system.service.impl;

import com.qiniu.util.StringUtils;

import cn.jeefast.common.annotation.DataFilter;
import cn.jeefast.system.dao.SysDeptDao;
import cn.jeefast.system.entity.SysDept;
import cn.jeefast.system.service.SysDeptService;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 班级管理 服务实现类
 * </p>
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptDao, SysDept> implements SysDeptService {

	@Autowired
	private SysDeptDao sysDeptDao;
	
	@Override
	@DataFilter(tableAlias = "d", user = false)
	public List<SysDept> queryList(Map<String, Object> map) {
		return sysDeptDao.queryList(map);
	}
	@Override
	public List<Long> queryDetpIdList(Long parentId) {
		return sysDeptDao.queryDetpIdList(parentId);
	}
	
	@Override
	public String getSubDeptIdList(Long deptId){
		//班级及子班级ID列表
		List<Long> deptIdList = new ArrayList<>();

		//获取子班级ID
		List<Long> subIdList = queryDetpIdList(deptId);
		getDeptTreeList(subIdList, deptIdList);

		//添加本班级
		deptIdList.add(deptId);

		String deptFilter = StringUtils.join(deptIdList, ",");
		return deptFilter;
	}

	/**
	 * 递归
	 */
	private void getDeptTreeList(List<Long> subIdList, List<Long> deptIdList){
		for(Long deptId : subIdList){
			List<Long> list = queryDetpIdList(deptId);
			if(list.size() > 0){
				getDeptTreeList(list, deptIdList);
			}

			deptIdList.add(deptId);
		}
	}
	
}
