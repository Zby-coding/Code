package com.zby.controller;

import com.alibaba.fastjson.JSON;
import com.zby.pojo.Brand;
import com.zby.service.BrandService;
import com.zby.service.impl.BrandServiceImpl;
import com.zby.util.PageBean;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet {
    private BrandService brandService = new BrandServiceImpl();

    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Brand> brands = brandService.selectAll();
        // 转成json对象
        String jsonString = JSON.toJSONString(brands);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 接收表单数据
        BufferedReader br = request.getReader();
        String s = br.readLine();
        Brand brand = JSON.parseObject(s, Brand.class);
        brandService.add(brand);
        // 设置响应数据
        response.getWriter().write("success");

    }

    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader br = request.getReader();
        String s = br.readLine();
        int[] ids = JSON.parseObject(s, int[].class);
        brandService.deleteByIds(ids);
        response.getWriter().write("success");

    }

    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取当前页数
        String _page = request.getParameter("page");
        // 转int型
        int page = Integer.parseInt(_page);
        // 获取当前页的条数
        String _size = request.getParameter("size");
        // 转int型
        int size = Integer.parseInt(_size);
        PageBean pageBean = brandService.selectByPage(page, size);
        // 转成json对象
        String json = JSON.toJSONString(pageBean);
        // 防中文乱码
        response.setContentType("text/json;charset=utf-8");
        // 回显到页面上
        response.getWriter().write(json);
    }
    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取当前页数
        String _page = request.getParameter("page");
        // 转int型
        int page = Integer.parseInt(_page);
        // 获取当前页的条数
        String _size = request.getParameter("size");
        // 转int型
        int size = Integer.parseInt(_size);
        // 拿到用户输入的数据
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        // 转成对象
        Brand brand = JSON.parseObject(s, Brand.class);
        PageBean pageBean = brandService.selectByPageAndCondition(page, size,brand);
        // 转成json对象
        String json = JSON.toJSONString(pageBean);
        // 防中文乱码
        response.setContentType("text/json;charset=utf-8");
        // 回显到页面上
        response.getWriter().write(json);
    }


}
