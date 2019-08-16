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
    <link href="css/one.css" type="text/css">
    <script>
        function preview(file) {
            var prevDiv = document.getElementById('preview');
            if (file.files && file.files[0]) {
                var reader = new FileReader();
                reader.onload = function (evt) {
                    // prevDiv.innerHTML = '<img width="100%" height="100%" id="qw_img" src="' + evt.target.result + '" />';
                    $('.preview').attr('src', evt.target.result);
                }
                reader.readAsDataURL(file.files[0]);
            } else {
                // prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.
                // Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
                $('.preview').attr('src', file.value);
            }
            17
        }

        18 </script>
</head>
<body>
<h1>你好，用户${user.userName}</h1>
<form action="/baseServlet?opr=add" method="post" enctype="multipart/form-data">
    <table class="hovertable" align="center" border="1">
        <tr>
            <th>商品编号</th>
            <th>${good.id}</th>
        </tr>
        <tr>
            <td>商品名字</td>
            <td><input type="text" name="goodsInfoName" value="${good.goodsInfoName}" required></td>
        </tr>
        <tr>
            <td>商品图片</td>
            <td>
                <img src="" alt="" width="80" style="margin-left: 20px" id="id_img">
                <input type="file" name="goodsInfoPic" accept="image/*" id="id_myfile">${file}
            </td>

        </tr>
        <tr>
            <td>商品价格</td>
            <td><input type="text" name="goodsInfoPrice" required></td>
        </tr>
        <tr>
            <td>商品描述</td>
            <td><input type="text" name="goodsInfoDescription"></td>
        </tr>
        <tr>
            <td>商品存货</td>
            <td><input type="text" name="goodsStock" required></td>
        </tr>
        <tr>
            <td>创建人id</td>
            <td><input type="text" name="created" value="${user.id}" readonly="readonly"></td>
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
