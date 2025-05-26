package cn.jeefast.system.dao;

import cn.jeefast.system.entity.SysShiyan;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;
import java.util.Map;

/**
 * <p>
  * 视频資源信息表 Mapper 接口
 * </p>
 */
public interface SysShiyanDao extends BaseMapper<SysShiyan> {
    List<SysShiyan> queryPageList(Page<SysShiyan> page, Map<String, Object> map);

    int deleteBatch(Object[] id);
}