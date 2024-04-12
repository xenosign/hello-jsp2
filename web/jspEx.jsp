<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%@ include file="header.jsp" %>
    <%!
        int num = 10;
        String str = "jsp";
        ArrayList<String> list = new ArrayList<>();

        public void method() {

        }
    %>

    <%
        if(num > 0) {
    %>
        <p>num 은 0 보다 큽니다</p>
    <%
        } else {
    %>
        <p>num 은 0 보다 작아요</p>
    <%
        }
    %>

    <p>num is <%= num %></p>

    <%@ include file="footer.jsp" %>
</body>
</html>
