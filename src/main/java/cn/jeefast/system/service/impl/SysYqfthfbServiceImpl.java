package cn.jeefast.system.service.impl;

import cn.jeefast.system.dao.SysYqfatieDao;
import cn.jeefast.system.entity.SysYqfatie;
import cn.jeefast.system.entity.SysYqfthfb;
import cn.jeefast.system.dao.SysYqfthfbDao;
import cn.jeefast.system.service.SysYqfthfbService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 疫情发帖回复表 服务实现类
 * </p>
 */
@Service
public class SysYqfthfbServiceImpl extends ServiceImpl<SysYqfthfbDao, SysYqfthfb> implements SysYqfthfbService {

    @Autowired
    private SysYqfthfbDao dao;

    @Override
    public Page<SysYqfthfb> queryPageList(Page<SysYqfthfb> page, Map<String, Object> map) {
        page.setRecords(dao.queryPageList(page, map));
        return page;
    }

    @Override
    public void deleteBatch(String[] ids) {
        dao.deleteBatch(ids);
    }
}
