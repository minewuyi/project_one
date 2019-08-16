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
    <link href="css/one.css" type="text/css">
</head>
<body>
<h1>你好，用户${user.userName}</h1>
<form action="/baseServlet?opr=doUpdate" method="post" enctype="multipart/form-data">
    <table class="hovertable" align="center" border="1">
        <tr>
            <td>商品编号</td>
            <td>${good.id}<input type="hidden" name="id" value="${good.id}"></td>
        </tr>
        <tr>
            <td>商品名字</td>
            <td><input type="text" name="goodsInfoName" value="${good.goodsInfoName}" required></td>
        </tr>
        <tr>
            <td>商品图片</td>
            <td>
                <img src="/upload/${good.goodsInfoPic}" width="150px" height="150px"><br>
                <input type="file" name="goodsInfoPic" accept="image/*">
            </td>
        </tr>
        <tr>
            <td>商品价格</td>
            <td><input type="text" name="goodsInfoPrice" value="${good.goodsInfoPrice}" required></td>
        </tr>
        <tr>
            <td>商品描述</td>
            <td><input type="text" name="goodsInfoDescription" value="${good.goodsInfoDescription}"></td>
        </tr>
        <tr>
            <td>商品存货</td>
            <td><input type="text" name="goodsStock" value="${good.goodsStock}" required></td>
        </tr>
        <tr>
            <td>创建人</td>
            <td><input type="text" name="created" value="${good.created}" readonly="readonly"></td>
        </tr>
        <tr>
            <td>状态</td>
            <td>
                <select name="flag" value="${good.flag}">
                    <option value="true">激活</option>
                    <option value="false">禁用</option>
                </select>
                <input type="hidden" name="old" value="${good.goodsInfoPic}">
            </td>
        </tr>
        <tr>
            <td>操作</td>
            <td>
                <button type="submit" onclick="return confirm('确定更新吗？')">更新</button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
