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
    <title>Title</title>
</head>
<body>

    <%
        Cookie[] cookies = request.getCookies();
        out.println("cookies : " + cookies);

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("id")) {
                    response.sendRedirect("loginOk.jsp");
                }
            }
        }
    %>

    <form action="loginCon" method="post">
        ID: <input type="text" name="id" /><br>
        PW: <input type="password" name="pw" /><br>
        <input type="submit" value="login">
    </form>
</body>
</html>
