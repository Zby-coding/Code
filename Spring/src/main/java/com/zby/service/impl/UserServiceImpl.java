package com.zby.service.impl;

import com.zby.dao.BrandDao;
import com.zby.dao.UserDao;
import com.zby.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao userDao;
    private BrandDao brandDao;


    @Override
    public void addUser() {
        System.out.println(UserServiceImpl.class.getName() + ":" + "添加用户...");
        userDao.addUser();
        brandDao.addBrand();
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setBrandDao(BrandDao brandDao) {
        this.brandDao = brandDao;
    }



}
