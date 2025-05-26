package cn.jeefast.system.service;

import cn.jeefast.system.entity.SysYqfatie;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.Map;

/**
 * <p>
 * 发帖信息表 服务类
 * </p>
 */
public interface SysYqfatieService extends IService<SysYqfatie> {
    Page<SysYqfatie> queryPageList(Page<SysYqfatie> page, Map<String, Object> map);
    void deleteBatch(String[] ids);
}
