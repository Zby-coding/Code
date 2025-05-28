package com.zby.session;
/*
* session会话跟踪技术是基于cookie的
* 原理：
* 1.客户端第一次访问服务器的时候，服务器会为这个客户端创建一个session对象，并把这个session对象保存到服务器的内存中
* 2.当客户端再次访问服务器的时候，服务器会根据客户端的cookie信息，找到对应的session对象，并把这个session对象返回给客户端
* 3.当客户端关闭的时候，服务器会销毁这个session对象
* 4.session会话跟踪技术基于cookie实现，所以session会话跟踪技术依赖于cookie
*
* session
* 钝化: 服务器正常关闭后，session对象会自动保存到硬盘里，并生成一个session文件
* 活化: 服务器重新启动后，session文件会被重新加载，并自动销毁此session文件
*
* */
import javax.servlet.http.HttpSession;

@javax.servlet.annotation.WebServlet("/SessionAServlet")
public class SessionAServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        doGet(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        // 获取session
        HttpSession session = request.getSession();
        // 设置session属性
        session.setAttribute("name", "赵博渊");
        // 查看SessionAServlet的内存地址
        System.out.println(session);
    }
}