package com.zby.controller;

import com.zby.pojo.Brand;
import com.zby.service.BrandService;

import javax.servlet.http.HttpServlet;

@javax.servlet.annotation.WebServlet("/oneServlet")
public class OneServlet extends HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        BrandService brandService = new BrandService();
        Brand brand2 = brandService.selectBrandById(id);
        request.setAttribute("brand",brand2);
        request.getRequestDispatcher("/oneBrand.jsp").forward(request,response);


    }
}