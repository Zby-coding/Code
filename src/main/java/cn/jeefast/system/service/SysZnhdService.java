package cn.jeefast.system.service;

import cn.jeefast.common.utils.PageUtils;
import cn.jeefast.system.entity.SysZnhd;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 智能回答管理 服务类
 * </p>
 *
 */
public interface SysZnhdService extends IService<SysZnhd> {
    Page<SysZnhd> queryPageList(Page<SysZnhd> page, Map<String, Object> map);
    void deleteBatch(String[] znhdIds);
    void saveNewsToZnhd(List<SysZnhd> znhdList);
    PageUtils queryPage(Map<String, Object> params);
    List<SysZnhd> queryByType(String type);
    void saveOrUpdate(SysZnhd znhd);
}
