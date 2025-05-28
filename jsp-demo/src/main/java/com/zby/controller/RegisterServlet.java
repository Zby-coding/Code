package com.zby.controller;

import com.zby.pojo.User;
import com.zby.service.UserService;

import javax.servlet.http.HttpSession;

@javax.servlet.annotation.WebServlet("/registerServlet")
public class RegisterServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        doGet(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        // 获取session中的验证码
        HttpSession session = request.getSession();
        String checkCode = (String) session.getAttribute("checkCode");
        // 拿到用户输入的验证码
        String checkCode1 = request.getParameter("checkCode");
        if(!(checkCode.equalsIgnoreCase(checkCode1))){
            request.setAttribute("register_msg","验证码有误");
            request.getRequestDispatcher("/register.jsp").forward(request,response);
            return;
        }
        // 获取用户名
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserService userService = new UserService();
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        boolean register = userService.register(user);
        if (register) {
            request.setAttribute("register_msg", "注册成功，请登录");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } else {
            request.setAttribute("register_msg", "用户名已经存在");
            request.getRequestDispatcher("/register.jsp").forward(request, response);

        }

    }
}