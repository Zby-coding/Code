package cn.jeefast.system.service.impl;

import cn.jeefast.system.dao.SysTypeDao;
import cn.jeefast.system.entity.SysType;
import cn.jeefast.system.service.SysTypeService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 类型表 服务实现类
 * </p>
 *
 */
@Service
public class SysTypeServiceImpl extends ServiceImpl<SysTypeDao, SysType> implements SysTypeService {

    @Autowired
    private SysTypeDao sysTypeDao;

    @Override
    public Page<SysType> queryPageList(Page<SysType> page, Map<String, Object> map) {
        page.setRecords(sysTypeDao.queryPageList(page, map));
        return page;
    }

    @Override
    public void deleteBatch(String[] ids) {
        sysTypeDao.deleteBatch(ids);
    }

}
