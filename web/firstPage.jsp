<%--
  Created by IntelliJ IDEA.
  User: a
  Date: 2024-04-12
  Time: 오후 3:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>This is Fisrt page!</h1>

    <%
        response.sendRedirect("secondPage.jsp");
    %>
</body>
</html>
