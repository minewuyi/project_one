<%--
  Created by IntelliJ IDEA.
  User: minew
  Date: 2019/8/10
  Time: 8:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆界面</title>
    <script>
        function check() {
            var username = document.getElementById("username").value;
            var password = document.getElementById("password").value;
            if (username == "") {
                alert("用户名不能为空");
                return false;
            } else if (password == "") {
                alert("密码不能为空");
                return false;
            }
            return true;
        }
    </script>
    <style type="text/css">
        body {
            color: #555;
        }

        .login-box {
            width: 450px;
            border: 1px solid #ccc;
            margin: 0 auto;
            margin-top: 150px;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 2px 2px 5px #ccc;
        }

        .login-title {
            text-align: center;
        }

        .login-row {
            padding: 10px 0px 10px 0px;
        }

        .login-button { /* 按钮美化 */
            width: 250px; /* 宽度 */
            height: 40px; /* 高度 */
            border-width: 0px; /* 边框宽度 */
            border-radius: 3px; /* 边框半径 */
            background: #1E90FF; /* 背景颜色 */
            cursor: pointer; /* 鼠标移入按钮范围时出现手势 */
            outline: none; /* 不显示轮廓线 */
            font-family: Microsoft YaHei; /* 设置字体 */
            color: white; /* 字体颜色 */
            font-size: 17px; /* 字体大小 */
        }

        .login-text {
            width: 80%;
            border: 1px solid #ccc;
            height: 30px;
            padding-left: 10px;
        }
    </style>
</head>
<body>
<div class="login-box">
    <h1 class="login-title">欢迎登录</h1>
    <form action="loginServlet" method="post">
        <div class="login-row">
            账号：<input type="text" class="login-text" name="username" id="username"/>
        </div>
        <div class="login-row">
            密码：<input type="password" class="login-text" name="password" id="password"/>
        </div>
        <div align="center">
            <button type="submit" onclick="return check()" class="login-button" name="opr" value="login">登录</button>
        </div>
    </form>
</div>
</body>
</html>
