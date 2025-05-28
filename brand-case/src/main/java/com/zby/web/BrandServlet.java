package com.zby.web;

import com.alibaba.fastjson.JSON;
import com.zby.pojo.Brand;
import com.zby.service.BrandService;
import com.zby.service.impl.BrandServiceImpl;
import com.zby.util.PageBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/brand/*")
public class BrandServlet  extends BaseServlet{
    private BrandService brandService=new BrandServiceImpl();
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Brand> brands = brandService.selectAll();
        //转JSON
        String s = JSON.toJSONString(brands);
        //写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(s);
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取删除的ids[2,34,56,6]
        BufferedReader br=request.getReader();
        String s = br.readLine();
        //将字符串转对象
        int[]  ids= JSON.parseObject(s, int[].class);
        //调用添加方法
        brandService.deleteByIds(ids);
        //响应
        response.getWriter().write("success");
    }
    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收页码的当前页和每页的条数 url?page=3&size=5
        String _page = request.getParameter("page");
        String _size = request.getParameter("size");
        int page=Integer.parseInt(_page);
        int size=Integer.parseInt(_size);
        PageBean<Brand> brandPageBean = brandService.selectByPage(page, size);

        //转json
        String s = JSON.toJSONString(brandPageBean);
        //写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(s);


    }

    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收页码的当前页和每页的条数 url?page=3&size=5
        String _page = request.getParameter("page");
        String _size = request.getParameter("size");
        int page=Integer.parseInt(_page);
        int size=Integer.parseInt(_size);
        //获取请求的条件 brand
        //接收品牌数据
        BufferedReader br=request.getReader();
        String s = br.readLine();
        System.out.println(s);
        //将字符串转对象
        Brand brand = JSON.parseObject(s, Brand.class);
        System.out.println(brand+"----------------------");
        System.out.println(brand.getCompanyName().length());

        PageBean<Brand> brandPageBean = brandService.selectByPageAndCondition(page,size,brand);

        //转json
        String str = JSON.toJSONString(brandPageBean);
        //写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(str);
    }


    }
