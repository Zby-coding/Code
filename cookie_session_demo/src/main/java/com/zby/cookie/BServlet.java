package com.zby.cookie;

import javax.servlet.http.Cookie;
import java.net.URLDecoder;

@javax.servlet.annotation.WebServlet("/BServlet")
public class BServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        doGet(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if("username".equals(cookie.getName())){
                String value = cookie.getValue();
                String decode = URLDecoder.decode(value, "UTF-8");
                System.out.println(cookie.getName() + ":" + decode);
                break;
            }
        }

    }
}