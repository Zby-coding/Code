package cn.jeefast.system.dao;

import cn.jeefast.system.entity.SysKemu;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;
import java.util.Map;

/**
 * <p>
  * 科目管理 Mapper 接口
 * </p>
 *
 */
public interface SysKemuDao extends BaseMapper<SysKemu> {
    List<SysKemu> queryPageList(Page<SysKemu> page, Map<String, Object> map);

    int deleteBatch(Object[] id);
}