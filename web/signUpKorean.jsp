<%--&lt;%&ndash; jsp 페이지에서도 설정 가능 &ndash;%&gt;--%>
<%--<% request.setCharacterEncoding("UTF-8"); %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>signUpKorean</title>
</head>
<body>
    <%
        String name = request.getParameter("name");
        String nickname = request.getParameter("nickname");
    %>

    <h1>이름 : <%= name %></h1>
    <h1>별명 : <%= nickname %></h1>
</body>
</html>
