package com.zby.reggie.filter;


import com.alibaba.fastjson.JSON;
import com.zby.reggie.common.BaseContext;
import com.zby.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

//          1、获取本次请求的URI
        String requestURI = request.getRequestURI();
        log.info("拦截的请求 {}", requestURI);
////        2、判断本次请求是否需要处理
        //定义不需要处理的请求   /employee/login/a.html
        String[] urls = {
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**",
                "/common/**",
                "/user/sendMsg",
                "/user/login",
                "/setmeal"
        };
        //3.如果不需要处理 直接放行
        if (check(urls, requestURI)) {

            log.info("本次请求{}不需要处理", requestURI);
            filterChain.doFilter(request, response);
            return;
        }

////        4、判断登录状态，如果已登录，则直接放行
        if (request.getSession().getAttribute("empId") != null) {//登录
            log.info("用户已经登陆 用户id为：{}", request.getSession().getAttribute("empId"));
            long id = Thread.currentThread().getId();
            log.info("线程id: {}", id);

            Long uid = (Long) request.getSession().getAttribute("empId");
            BaseContext.setCurrentId(uid);
            filterChain.doFilter(request, response);
            return;
        }
        // 前端登录
        if (request.getSession().getAttribute("user") != null) {//登录
            log.info("用户已经登陆 用户id为：{}", request.getSession().getAttribute("empId"));
            long id = Thread.currentThread().getId();
            log.info("线程id: {}", id);

            Long userId = (Long) request.getSession().getAttribute("user");
            BaseContext.setCurrentId(userId);
            filterChain.doFilter(request, response);
            return;
        }
////        5、如果未登录则返回未登录结果
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return;

    }

    public boolean check(String[] urls, String requestURI) {
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURI);
            if (match) {
                return true;
            }
        }
        return false;
    }
}
