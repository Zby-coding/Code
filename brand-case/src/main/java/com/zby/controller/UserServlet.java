package com.zby.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet{
    public void selectAll(HttpServletRequest request, HttpServletResponse response){
        System.out.println("user...selectAll");

    }
    public void add(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("user...add");

    }

}
