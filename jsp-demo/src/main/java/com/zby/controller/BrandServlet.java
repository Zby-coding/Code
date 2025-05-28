package com.zby.controller;

import com.zby.pojo.Brand;
import com.zby.service.BrandService;

import java.util.ArrayList;
import java.util.List;

@javax.servlet.annotation.WebServlet("/BrandServlet")
public class BrandServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        List<Brand> brands = new ArrayList<Brand>();
        brands.add(new Brand(1, "三只松鼠", "三只松鼠", 100, "三只松鼠，好吃不上火", 1));
        brands.add(new Brand(2, "优衣库", "优衣库", 200, "优衣库，服适人生", 0));
        brands.add(new Brand(3, "小米", "小米科技有限公司", 1000, "为发烧而生", 1));
        request.setAttribute("brand3", brands);
        request.getRequestDispatcher("/brand3.jsp").forward(request, response);
    }
}