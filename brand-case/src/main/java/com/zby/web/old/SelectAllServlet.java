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
import java.io.IOException;
import java.util.List;

//@WebServlet("/selectAllServlet")
public class SelectAllServlet extends HttpServlet {
    private BrandService brandService=new BrandServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Brand> brands = brandService.selectAll();
        //转JSON
        String s = JSON.toJSONString(brands);
        //写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(s);


    }
}
