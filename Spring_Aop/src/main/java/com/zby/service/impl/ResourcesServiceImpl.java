package com.zby.service.impl;

import com.zby.dao.ResourcesDao;
import com.zby.service.ResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ResourcesServiceImpl implements ResourcesService {
    @Autowired
    private ResourcesDao resourcesDao;
    public boolean openURL(String url, String password) {

        // 参数空校验（新增）
        if (url == null || password == null) {
            throw new IllegalArgumentException("URL和密码不能为null");
        }

        // 立即发现问题所在
        if (resourcesDao == null) {
            throw new IllegalStateException("系统错误：资源库未初始化");
        }
        return resourcesDao.readResources(url,password);
    }
}
