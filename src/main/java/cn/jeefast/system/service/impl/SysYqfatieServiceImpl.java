package cn.jeefast.system.service.impl;

import cn.jeefast.system.dao.SysYqfatieDao;
import cn.jeefast.system.entity.SysYqfatie;
import cn.jeefast.system.service.SysYqfatieService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 发帖信息表 服务实现类
 * </p>
 */
@Service
public class SysYqfatieServiceImpl extends ServiceImpl<SysYqfatieDao, SysYqfatie> implements SysYqfatieService {

    @Autowired
    private SysYqfatieDao dao;

    @Override
    public Page<SysYqfatie> queryPageList(Page<SysYqfatie> page, Map<String, Object> map) {
        page.setRecords(dao.queryPageList(page, map));
        return page;
    }

    @Override
    public void deleteBatch(String[] ids) {
        dao.deleteBatch(ids);
    }
}
