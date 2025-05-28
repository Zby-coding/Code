package com.zby.dao.impl;

import com.zby.dao.UserDao;

public class UserDaoImpl implements UserDao {

    @Override
    public void addUser() {
        System.out.println(UserDaoImpl.class.getName() + ":"+ "添加用户");
    }


}
