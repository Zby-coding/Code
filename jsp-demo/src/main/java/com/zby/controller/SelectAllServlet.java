package com.zby.controller;

import com.alibaba.fastjson.JSON;
import com.zby.pojo.Brand;
import com.zby.service.BrandService;

import java.util.List;

@javax.servlet.annotation.WebServlet("/selectAllServlet")
public class SelectAllServlet extends javax.servlet.http.HttpServlet {
    private BrandService brandService = new BrandService();
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        doGet(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        List<Brand> brands = brandService.selectAll();
        // 将集合转为json数据
        String jsonString = JSON.toJSONString(brands);
        // 转码
        response.setContentType("text/json;charset=utf-8");
        // 响应数据
        response.getWriter().write(jsonString);

    }
}
