package com.zby.oldController;

import com.alibaba.fastjson.JSON;
import com.zby.pojo.Brand;
import com.zby.service.BrandService;
import com.zby.service.impl.BrandServiceImpl;

import java.io.BufferedReader;

// @javax.servlet.annotation.WebServlet("/addBrandServlet")
public class AddBrandServlet extends javax.servlet.http.HttpServlet {
    private BrandService brandService = new BrandServiceImpl();
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        doGet(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        // 接收表单数据
        BufferedReader br = request.getReader();
        String s = br.readLine();
        Brand brand = JSON.parseObject(s,Brand.class);
        brandService.add(brand);
        // 设置响应数据
        response.getWriter().write("success");
    }
}
