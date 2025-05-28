package com.zby.controller;

import com.zby.pojo.Brand;
import com.zby.service.BrandService;

import java.nio.charset.StandardCharsets;

@javax.servlet.annotation.WebServlet("/updateServlet")
public class UpdateServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        doGet(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        Brand brand = new Brand();
        BrandService brandService = new BrandService();
        brand.setId(Integer.parseInt(request.getParameter("id")));
        String brandName = new String(request.getParameter("brandName").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        brand.setBrandName(brandName);
        String companyName = new String(request.getParameter("companyName").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        brand.setCompanyName(companyName);
        brand.setOrdered(Integer.parseInt(request.getParameter("ordered")));
        String description = new String(request.getParameter("description").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        brand.setDescription(description);
        brand.setStatus(Integer.parseInt(request.getParameter("status")));
        brandService.update(brand);
        request.getRequestDispatcher("/allServlet").forward(request, response);
    }
}