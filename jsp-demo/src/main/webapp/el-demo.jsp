<%@ page import="com.zby.pojo.Brand" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: 14026
  Date: 2025/3/11
  Time: 11:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
  List<Brand> brands = new ArrayList<Brand>();
  brands.add(new Brand(1, "三只松鼠", "三只松鼠", 100, "三只松鼠，好吃不上火", 1));
  brands.add(new Brand(2, "优衣库", "优衣库", 200, "优衣库，服适人生", 0));
  brands.add(new Brand(3, "小米", "小米科技有限公司", 1000, "为发烧而生", 1));
  request.setAttribute("brands", brands);
%>
${brands}
</body>
</html>
