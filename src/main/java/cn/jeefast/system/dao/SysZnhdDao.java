package cn.jeefast.system.dao;

import cn.jeefast.system.entity.SysZnhd;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
  * 智能回答管理 Mapper 接口
 * </p>
 *
 */
public interface SysZnhdDao extends BaseMapper<SysZnhd> {
    List<SysZnhd> queryPageList(Page<SysZnhd> page, Map<String, Object> map);
    int deleteBatch(Object[] id);
    // 在接口中添加
    List<SysZnhd> selectZnHdList(@Param("page") Page<SysZnhd> page, @Param("params") Map<String, Object> params);

}