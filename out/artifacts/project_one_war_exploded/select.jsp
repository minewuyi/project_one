<%--
  Created by IntelliJ IDEA.
  User: minew
  Date: 2019/8/15
  Time: 15:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查询界面</title>
 <link href="css/one.css" type="text/css">
</head>
<body>
<h1>你好，用户${user.userName}</h1>
<form action="/baseServlet" method="post">
    <table class="hovertable" align="center" border="1">
        <tr>
            <td width="10%">商品编号</td>
            <td width="30%"><input type="text" name="id"></td>
        </tr>
        <tr>
            <td>商品名字</td>
            <td><input type="text" name="goodsInfoName"></td>
        </tr>
        <tr>
            <td>商品价格</td>
            <td><input type="text" name="goodsInfoPrice"></td>
        </tr>
        <tr>
            <td>商品存货</td>
            <td><input type="text" name="goodsStock"></td>
        </tr>
        <tr>
            <td>创建人id</td>
            <td><input type="text" name="created"></td>
        </tr>
        <tr>
            <td>状态</td>
            <td>
                <select name="flag">
                    <option value="true">激活</option>
                    <option value="false">禁用</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>操作</td>
            <td>
                <button type="submit" name="opr" value="select">查询</button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
