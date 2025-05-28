<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>添加品牌2</title>
</head>
<body>
<h3>添加品牌</h3>
<form action="updateServlet" method="get">
    品牌名称：<input name="brandName" value="${brand.brandName}"><br>
    企业名称：<input name="companyName" value="${brand.companyName}"><br>
    排序：<input name="ordered" value="${brand.ordered}"><br>
    描述信息：<textarea rows="5" cols="20" name="description">${brand.description}</textarea><br>
    状态：
    <input type="radio" name="status" value="${brand.status}" <c:if test="${brand.status==0}">checked</c:if>>禁用
    <input type="radio" name="status" value="${brand.status}" <c:if test="${brand.status==1}">checked</c:if>>启用<br>
    <input type="hidden" name="id" value="${brand.id}">
    <input type="submit" value="提交">
</form>
</body>
</html>