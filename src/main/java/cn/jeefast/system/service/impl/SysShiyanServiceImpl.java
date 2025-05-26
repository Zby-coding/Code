package cn.jeefast.system.service.impl;

import cn.jeefast.system.dao.SysShiyanDao;
import cn.jeefast.system.entity.SysShiyan;
import cn.jeefast.system.service.SysShiyanService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 视频資源信息表 服务实现类
 * </p>
 */
@Service
public class SysShiyanServiceImpl extends ServiceImpl<SysShiyanDao, SysShiyan> implements SysShiyanService {

    @Autowired
    private SysShiyanDao sysShiyanDao;

    @Override
    public Page<SysShiyan> queryPageList(Page<SysShiyan> page, Map<String, Object> map) {
        page.setRecords(sysShiyanDao.queryPageList(page, map));
        return page;
    }

    @Override
    public void deleteBatch(String[] shiyanIds) {
        sysShiyanDao.deleteBatch(shiyanIds);
    }

}
