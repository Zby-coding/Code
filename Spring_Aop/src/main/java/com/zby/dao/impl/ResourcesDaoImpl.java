package com.zby.dao.impl;

import com.zby.dao.ResourcesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ResourcesDaoImpl implements ResourcesDao {
    @Autowired
    private ResourcesDao resourcesDao;
    public boolean readResources(String url, String password) {

        //模拟校验
        return password.equals("root");
    }
}
