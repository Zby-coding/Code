package com.zby.web.old;

import com.alibaba.fastjson.JSON;
import com.zby.pojo.Brand;
import com.zby.service.BrandService;
import com.zby.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

//@WebServlet("/addBrandServlet")// /brand/*   brand/add  add(Brand brand) brand/selectAll  selectAll()
public class AddBrandServlet extends HttpServlet {
    private BrandService brandService=new BrandServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //接收品牌数据
        BufferedReader br=request.getReader();
        String s = br.readLine();
        //将字符串转对象
        Brand brand = JSON.parseObject(s, Brand.class);
        //调用方法
        brandService.add(brand);
        //设置响应数据
        response.getWriter().write("success");

    }
}
