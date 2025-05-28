package com.zby.filter;
/*
* 若配置了多个过滤器，则按过滤器名字的字符串
* 的自然排序来执行这多个过滤器
* */

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
@WebFilter("/*")
public class FilterDemo1 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("1.执行了过滤器1");
        // 放行
        chain.doFilter(request, response);
        System.out.println("3.执行了过滤器1");
    }

    @Override
    public void destroy() {

    }
}
