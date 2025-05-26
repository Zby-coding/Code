package cn.jeefast.system.dao;

import cn.jeefast.system.entity.SysYqfthfb;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;
import java.util.Map;

/**
 * <p>
  * 疫情发帖回复表 Mapper 接口
 * </p>
 */
public interface SysYqfthfbDao extends BaseMapper<SysYqfthfb> {
    List<SysYqfthfb> queryPageList(Page<SysYqfthfb> page, Map<String, Object> map);

    int deleteBatch(Object[] id);
}