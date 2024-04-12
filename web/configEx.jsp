<%--
  Created by IntelliJ IDEA.
  User: a
  Date: 2024-04-12
  Time: 오후 4:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="errorPage.jsp" %>
<html>
<head>
    <title>Title</title>
</head>
<style>
    p {
        color: red;
    }
</style>
<body>
    <%!
        String adminId;
        String adminPw;

        String imgDir;
        String testServerIP;

        String connetedIP;

        String str;
    %>

    <%
        adminId = config.getInitParameter("adminId");
        adminPw = config.getInitParameter("adminPw");

        imgDir = application.getInitParameter("imgDir");
        testServerIP = application.getInitParameter("testServerIP");

        application.setAttribute("connetedIP", "127.10.10.10");
        connetedIP = (String) application.getAttribute("connetedIP");

        // Err 발생 부분
        str.toString();
    %>

    <p>adminId : <%= adminId %></p>
    <p>adminPw : <%= adminPw %></p>

    <p>imgDir : <%= imgDir %></p>
    <p>testServerIP : <%= testServerIP %></p>

    <p>connetedIP : <%= connetedIP %></p>
</body>
</html>
