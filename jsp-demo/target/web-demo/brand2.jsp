<%@ page import="com.zby.pojo.Brand" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<hr>
<!--
JSP缺点:
    1.书写麻烦
    2.不易阅读
    3.学习成本高
    4.不易于团队协作，前端不懂Java代码，后端不能精通前端技术
    5.磁盘占用空间比较大：jsp自动生成Java源代码，源代码自动编译成字节码文件，占用内存空间
    6.不易调试
-->
<a href="addBrand.html"><input type="button" value="新增"></a>
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
    <%
        List<Brand> brands = new ArrayList<Brand>();
        Brand brand = null;
        brands.add(new Brand(1, "三只松鼠", "三只松鼠", 100, "三只松鼠，好吃不上火", 1));
        brands.add(new Brand(2, "优衣库", "优衣库", 200, "优衣库，服适人生", 0));
        brands.add(new Brand(3, "小米", "小米科技有限公司", 1000, "为发烧而生", 1));
    %>
    <%
        for (int i = 0; i < brands.size(); i++) {
            brand = brands.get(i);
    %>
    <tr align="center">
        <td><%=brand.getId()%></td>
        <td><%=brand.getBrandName()%></td>
        <td><%=brand.getCompanyName()%></td>
        <td><%=brand.getOrdered()%></td>
        <td><%=brand.getDescription()%></td>
        <%
            if (brand.getStatus() == 0){
        %>
        <td>禁用</td>
        <%
        }else{

        %>
        <td>启用</td>
        <%
            }
        %>
        <td><a href="#">修改</a> <a href="#">删除</a></td>
    </tr>
    <%
        }
    %>
    <%=
    "我是brand2.jsp页面"
    %>

</table>

</body>
</html>