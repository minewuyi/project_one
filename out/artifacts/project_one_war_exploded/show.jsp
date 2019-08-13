<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: minew
  Date: 2019/8/12
  Time: 17:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商品一览</title>
    <style type="text/css">
        table.hovertable {
            font-family: verdana, arial, sans-serif;
            font-size: 11px;
            color: #333333;
            border-width: 1px;
            border-color: #999999;
            border-collapse: collapse;
        }

        table.hovertable th {
            background-color: #c3dde0;
            border-width: 1px;
            padding: 8px;
            border-style: solid;
            border-color: #a9c6c9;
        }

        table.hovertable tr {
            background-color: #d4e3e5;
        }

        table.hovertable td {
            border-width: 1px;
            padding: 8px;
            border-style: solid;
            border-color: #a9c6c9;
        }
    </style>
</head>
<body>
<h1>你好，用户${username}</h1>
<table border="1" align="center" style="" class="hovertable">
    <thead>
    <tr>
        <th>编号</th>
        <th>商品名</th>
        <th>价格</th>
        <th>库存</th>
        <th>状态</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:if test="${list!=null}">
        <c:forEach items="${list}" var="good" varStatus="s">
            <tr>
                <td>${good.id}</td>
                <td><a href="/baseServlet?opr=showOne&id=${good.id}">${good.goodsInfoName}</a></td>
                <td>${good.goodsInfoPrice}</td>
                <td>${good.goodsStock}</td>
                <td>${good.flag}</td>
                <td>
                    <a href="/baseServlet?opr=delete&id=${good.id}" onclick="return confirm('确定删除吗？')">删除</a>
                    <a href="/baseServlet?opr=update&id=${good.id}">修改</a>
                </td>
            </tr>
        </c:forEach>
    </c:if>
    <tr>
        <td colspan="6" align="center">
            <button type="button"><a href="add.jsp">添加</a></button>
            <button type="button" onclick="onPrompt()">查询</button>
        </td>
    </tr>
    </tbody>
</table>
</body>
</html>
