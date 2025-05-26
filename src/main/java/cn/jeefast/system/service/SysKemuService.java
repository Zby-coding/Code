package cn.jeefast.system.service;

import cn.jeefast.system.entity.SysKemu;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.Map;

/**
 * <p>
 * 科目管理 服务类
 * </p>
 *
 */
public interface SysKemuService extends IService<SysKemu> {
    Page<SysKemu> queryPageList(Page<SysKemu> page, Map<String, Object> map);
    void deleteBatch(String[] ids);
}
