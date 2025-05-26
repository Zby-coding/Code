package cn.jeefast.system.service.impl;

import cn.jeefast.system.dao.SysKemuDao;
import cn.jeefast.system.entity.SysKemu;
import cn.jeefast.system.service.SysKemuService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 科目管理 服务实现类
 * </p>
 *
 */
@Service
public class SysKemuServiceImpl extends ServiceImpl<SysKemuDao, SysKemu> implements SysKemuService {
    @Autowired
    private SysKemuDao sysKemuDao;

    @Override
    public Page<SysKemu> queryPageList(Page<SysKemu> page, Map<String, Object> map) {
        page.setRecords(sysKemuDao.queryPageList(page, map));
        return page;
    }

    @Override
    public void deleteBatch(String[] ids) {
        sysKemuDao.deleteBatch(ids);
    }
}
