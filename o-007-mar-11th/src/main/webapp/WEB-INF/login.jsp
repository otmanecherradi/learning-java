<%--
  Created by IntelliJ IDEA.
  User: otmane
  Date: 3/11/23
  Time: 10:46 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
</head>
<body>

<div>
  ${error}
</div>

<form action="login" method="post">
  <label>
    Username
    <input type="text" name="username">
  </label>
  <br>
  <label>
    Password
    <input type="password" name="password">
  </label>
  <br>

  <button type="submit">Login</button>
</form>
</body>
</html>
