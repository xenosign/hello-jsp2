<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%!
        String name;
        String pass;
        String[] hobbies;
    %>

    <%
        name = request.getParameter("name");
        pass = request.getParameter("pass");
        hobbies = request.getParameterValues("hobby");
    %>

    <ul>
        <% if(name.length() > 1) { %>
        <ll>name : <%= name%></ll>
        <ll>pass : <%= pass%></ll>
        <ul>
            <% if(hobbies.length > 0) { %>
                <% for(int i = 0; i < hobbies.length; i++) { %>
                    <li><%= hobbies[i] %></li>
                <% } %>
            <% } %>
        </ul>
        <% } else { %>
        <li>회원 데이터가 없습니다</li>
        <% } %>
    </ul>
</body>
</html>
