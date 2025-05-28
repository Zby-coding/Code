package com.zby.usercenter_backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zby.usercenter_backend.model.domain.User;

import javax.servlet.http.HttpServletRequest;

/**
* @author 14026
*/
public interface UserService extends IService<User> {
    long userRegister(String userAccount,String userPassword,String checkPassword,String planetCode);
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);
    User getSaftyUser(User originUser);
    int userLogout(HttpServletRequest request);

}
