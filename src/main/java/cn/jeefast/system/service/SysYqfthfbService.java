package cn.jeefast.system.service;

import cn.jeefast.system.entity.SysYqfatie;
import cn.jeefast.system.entity.SysYqfthfb;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.Map;

/**
 * <p>
 * 疫情发帖回复表 服务类
 * </p>
 */
public interface SysYqfthfbService extends IService<SysYqfthfb> {
    Page<SysYqfthfb> queryPageList(Page<SysYqfthfb> page, Map<String, Object> map);
    void deleteBatch(String[] ids);
}
