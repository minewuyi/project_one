<%--
  Created by IntelliJ IDEA.
  User: minew
  Date: 2019/8/12
  Time: 18:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商品详情</title>
    <link href="css/one.css" type="text/css">
</head>
<body >
<h1>你好，用户${user.userName}</h1>
<table class="hovertable" align="center" border="1">
    <tr>
        <td >商品编号</td>
        <td >${good.id}</td>
    </tr>
    <tr>
        <td>商品名字</td>
        <td>${good.goodsInfoName}</td>
    </tr>
    <tr>
        <td>商品图片</td>
        <td><img src="/upload/${good.goodsInfoPic}" width="150px" height="150px"></td>
    </tr>
    <tr>
        <td>商品价格</td>
        <td>${good.goodsInfoPrice}</td>
    </tr>
    <tr>
        <td>商品描述</td>
        <td>${good.goodsInfoDescription}</td>
    </tr>
    <tr>
        <td>商品存货</td>
        <td>${good.goodsStock}</td>
    </tr>
    <tr>
        <td>创建人</td>
        <td>${good.createdName}</td>
    </tr>
    <tr>
        <td>创建日期</td>
        <td>${good.createdDate}</td>
    </tr>
    <tr>
        <td>状态</td>
        <td>${good.flag}</td>
    </tr>
    <tr>
        <td>操作</td>
        <td>
            <a href="/baseServlet?opr=delete&id=${good.id}" onclick="return confirm('确定删除吗？')">删除</a>
            <a href="/baseServlet?opr=update&id=${good.id}">修改</a>
        </td>
    </tr>
</table>
</body>
</html>
