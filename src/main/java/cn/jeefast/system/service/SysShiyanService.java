package cn.jeefast.system.service;

import cn.jeefast.system.entity.SysShiyan;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.Map;

/**
 * <p>
 * 视频資源信息表 服务类
 * </p>
 */
public interface SysShiyanService extends IService<SysShiyan> {
    Page<SysShiyan> queryPageList(Page<SysShiyan> page, Map<String, Object> map);
    void deleteBatch(String[] shiyanIds);
}
