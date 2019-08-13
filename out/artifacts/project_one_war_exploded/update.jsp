<%--
  Created by IntelliJ IDEA.
  User: minew
  Date: 2019/8/12
  Time: 20:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>更新商品</title>
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
<form action="/baseServlet">
<table class="hovertable" align="center" border="1">
    <tr>
        <th width="10%">商品编号</th>
        <th width="30%">${good.id} <input type="hidden" name="id" value="${good.id}"></th>
    </tr>
    <tr>
        <td>商品名字</td>
        <td><input type="text" name="goodsInfoName" value="${good.goodsInfoName}"></td>
    </tr>
    <tr>
        <td>商品图片</td>
        <td>${good.goodsInfoPic}</td>

    </tr>
    <tr>
        <td>商品价格</td>
        <td><input type="text" name="goodsInfoPrice" value="${good.goodsInfoPrice}"></td>
    </tr>
    <tr>
        <td>商品描述</td>
        <td><input type="text" name="goodsInfoDescription" value="${good.goodsInfoDescription}" ></td>
    </tr>
    <tr>
        <td>商品存货</td>
        <td><input type="text"name="goodsStock" value="${good.goodsStock}" ></td>
    </tr>
    <tr>
        <td>创建人</td>
        <td> <input type="text" name="created" value="${good.created}" readonly ="readonly"></td>
    </tr>
    <tr>
        <td>状态</td>
        <td><input type="text" name="flag" value="${good.flag}" readonly ="readonly"></td>
    </tr>
    <tr>
        <td>操作</td>
        <td><button type="submit" name="opr"  value="doUpdate" onclick="return confirm('确定更新吗？')">更新</button>
        </td>
    </tr>
</table>
</form>
</body>
</html>
