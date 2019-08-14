<%--
  Created by IntelliJ IDEA.
  User: minew
  Date: 2019/8/13
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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
    <script>

        // function changImg(e) {
        //     for (var i = 0; i < e.target.files.length; i++) {
        //         var file = e.target.files.item(i);
        //         if (!(/^image\/.*$/i.test(file.type))) {
        //             continue; //不是图片 就跳出这一次循环
        //         }
        //         //实例化FileReader API
        //         var freader = new FileReader();
        //         freader.readAsDataURL(file);
        //         freader.onload = function (e) {
        //             $("#myImg").attr("src", e.target.result);
        //         }
        //     }
        // }
    </script>
</head>
<body>
<h1>你好，用户${username}</h1>
<form action="/baseServlet?opr=add" method="post" enctype="multipart/form-data">
    <table class="hovertable" align="center" border="1">
        <tr>
            <th width="10%">商品编号</th>
            <th width="30%">${good.id}</th>
        </tr>
        <tr>
            <td>商品名字</td>
            <td><input type="text" name="goodsInfoName" value="${good.goodsInfoName}"></td>
        </tr>
        <tr>
            <td>商品图片</td>
            <td>
                <input type="file" name="goodsInfoPic" accept="image/*">${file}
            </td>

        </tr>
        <tr>
            <td>商品价格</td>
            <td><input type="text" name="goodsInfoPrice"></td>
        </tr>
        <tr>
            <td>商品描述</td>
            <td><input type="text" name="goodsInfoDescription"></td>
        </tr>
        <tr>
            <td>商品存货</td>
            <td><input type="text" name="goodsStock"></td>
        </tr>
        <tr>
            <td>创建人</td>
            <td><input type="text" name="created" value="1" readonly="readonly"></td>
        </tr>
        <tr>
            <td>状态</td>
            <td>
                <select name="flag" value="${good.flag}">
                    <option value="true">激活</option>
                    <option value="false">禁用</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>操作</td>
            <td>
                <button type="submit" name="opr" value="add" onclick="return confirm('确定添加吗？')">添加</button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
