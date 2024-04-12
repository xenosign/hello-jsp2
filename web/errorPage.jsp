<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        response.setStatus(200);
        String msg = exception.getMessage();
    %>

    <p> error msg : <%= msg %> </p>
</body>
</html>
