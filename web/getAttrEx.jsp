<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%!
        String connetedIP;
    %>

    <%
        <%--        왜 안되는지 아시는분? --%>
        connetedIP = (String) application.getAttribute("connetedIP");
    %>

    <p>connetedIP : <%= connetedIP %></p>
</body>
</html>
