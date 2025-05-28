package com.zby.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 用来被其他servlet继承  实现请求值分发到指定方法
 */

public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取请求路径
        StringBuffer url = req.getRequestURL();//http://localhost:8080/brand-case/brand/selectAll
        //获取最后一段路径
        int index=url.lastIndexOf("/");
        String methodName = url.substring(index+1);
        //System.out.println(substring);
        //执行对应的方法
        //获取BrandServlet UserServlet中的字节码对象
        //System.out.println(this);//???
        Class<? extends BaseServlet> cls = this.getClass();

        //获取方法
        try {
            Method method = cls.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            //执行方法
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }




    }
}
