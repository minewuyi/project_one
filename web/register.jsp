<%--
  Created by IntelliJ IDEA.
  User: minew
  Date: 2019/8/12
  Time: 8:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <script>
        function check() {
            var username = document.getElementById("username").value;
            var password = document.getElementById("password").value;
            var passwords = document.getElementById("passwords").value;
            if (username == "") {
                alert("用户名不能为空");
                return false;
            } else if (password == "") {
                alert("密码不能为空");
                return false;
            }else if (password!==passwords){
                alert("两次密码输入不一致")
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
            margin-top: 100px;
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
    <h1 class="login-title"><a href="login.jsp">登录</a> | <a href="register.jsp">注册</a></h1>
    <form action="loginServlet" method="post">
        <div class="login-row">
            账号：<input type="text" class="login-text" name="username" id="username"/>
        </div>
        <div class="login-row">
            密码：<input type="password" class="login-text" name="password" id="password"/>
        </div>
        <div class="login-row">
            密码：<input type="password" class="login-text" name="passwords" id="passwords"/>
        </div>
        <div class="login-row">
            性别:&nbsp;&nbsp;
            <select name="sex" id="sex" class="login-text">
                <option>保密</option>
                <option>男</option>
                <option>女</option>
            </select>
        </div>
        <div class="login-row">
            爱好：<input type="text" class="login-text" name="hobby" id="hobby"/>
        </div>
        <div class="login-row">
            电话：<input type="tel" class="login-text" name="tel" id="tel"/>
        </div>
        <div class="login-row">
            邮箱：<input type="email" class="login-text" name="email" id="email"/>
        </div>
        <div class="login-row">
            地址：<input type="text" class="login-text" name="addrs" id="addrs"/>
            <input type="hidden" name="flag" value="true">
        </div>
        <div align="center">
            <button type="submit" onclick="return check()" class="login-button" name="opr" value="register">注册</button>
        </div>
    </form>
</div>
</body>
</html>
