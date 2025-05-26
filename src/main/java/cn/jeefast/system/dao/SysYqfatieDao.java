package cn.jeefast.system.dao;

import cn.jeefast.system.entity.SysYqfatie;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;
import java.util.Map;

/**
 * <p>
  * 发帖信息表 Mapper 接口
 * </p>
 */
public interface SysYqfatieDao extends BaseMapper<SysYqfatie> {
    List<SysYqfatie> queryPageList(Page<SysYqfatie> page, Map<String, Object> map);

    int deleteBatch(Object[] id);
}