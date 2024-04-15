<%--
  Created by IntelliJ IDEA.
  User: a
  Date: 2024-04-15
  Time: 오전 10:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Session Login Page</title>
</head>
<body>
    <%
        if(session.getAttribute("id") != null) response.sendRedirect("loginSessionOk.jsp");
    %>

    <h1>Session Login Page</h1>
    <form action="loginSession" method="post">
        ID: <input type="text" name="id" /><br>
        PW: <input type="password" name="pw" /><br>
        <input type="submit" value="login">
    </form>
</body>
</html>
