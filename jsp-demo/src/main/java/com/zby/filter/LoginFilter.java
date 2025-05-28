package com.zby.filter;

import com.zby.pojo.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@javax.servlet.annotation.WebFilter("/*")
public class LoginFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        // 需要被执行的url
        String[] urls = {"/css/", "/imgs/", "/loginServlet", "/registerServlet", "/selectAllServlet","/CheckCodeServlet","/register.jsp","/login.jsp"};
        // 获取当前请求
        String requestURL = req.getRequestURL().toString();
        // 循环判断当前请求是否包含以上数组
        for (String url : urls) {
            //包含
            if(requestURL.contains(url)){
                // 放行
                chain.doFilter(request, response);
                return; // 一下代码逻辑不执行
            }
        }
        // 通过session对象判断用户是否注册
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            // 已经注册过了，放行
            chain.doFilter(request, response);
        } else {
            req.setAttribute("login_msg", "未登录");
            req.getRequestDispatcher("/login.jsp").forward(request, response);
        }

    }
}
