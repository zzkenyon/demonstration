<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/3/26 0026
  Time: 20:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1> 欢迎登录 </h1>
<form action="/loginUser" method="post">
    <input type="text" name="username"> <br>
    <input type="password" name="password"> <br>
    <input type="submit" value="提交">
</form>
</body>
</html>
