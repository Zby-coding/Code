<%@ page import="com.zby.pojo.Brand" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>brand</title>
</head>
<body>
<input type="button" value="新增"><br>
<hr>
<!--
index 从0开始
count 从1开始
last 判断该是不是最后一个
first 判断是不是第一个
-->
<table border="1" cellspacing="0" width="800">
    <tr>
        <th>序号</th>
        <th>品牌名称</th>
        <th>企业名称</th>
        <th>排序</th>
        <th>品牌介绍</th>
        <th>状态</th>
        <th>操作</th>

    </tr>
    <c:forEach items="${brand}" var="brand2" varStatus="status">
        <tr align="center">
            <td>${status.count}</td>
            <td>${brand2.brandName}</td>
            <td>${brand2.companyName}</td>
            <td>${brand2.ordered}</td>
            <td>${brand2.description}</td>
            <td>${brand2.status==0?"禁用":"启用"}</td>
            <td><a href="oneServlet?id=${brand2.id}">修改</a> <a href="#">删除</a></td>
        </tr>
    </c:forEach>
</table>
<%=
"我是brand.jsp"
%>

</body>
</html>




