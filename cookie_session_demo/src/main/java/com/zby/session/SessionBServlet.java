package com.zby.session;
/*
* Cookie 和 Session 都是来完成一次会话内多次请求间数据共享的
* 区别：
* 存储位置：Cookie 是将数据存储在客户端，Session 将数据存储在服务端
* 安全性：Cookie 不安全，Session 安全
* 数据大小：Cookie 最大3KB，Session 无大小限制
* 存储时间：Cookie 可以长期存储，Session 默认30分钟
* 服务器性能：Cookie 不占服务器资源，Session 占用服务器资源
* */
import javax.servlet.http.HttpSession;

@javax.servlet.annotation.WebServlet("/SessionBServlet")
public class SessionBServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        doGet(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        // 获取session
        HttpSession session = request.getSession();
        // 获取属性值
        Object name = session.getAttribute("name");
        System.out.println(name);
        // 查看SessionBServlet的内存地址
        System.out.println(session);




    }
}