# JSP 내장 객체

## config 객체

- web.xml 에서 선언한 데이터를 getInitParameter() 를 통해 JSP 파일에서 가져와서 사용하는 객체
- 지정 된 특정 파일에서만 사용 가능

- xml 에 파라미터 데이터 지정

```xml
<web-app>
<servlet>
    <servlet-name>configEx</servlet-name>
    <jsp-file>/configEx.jsp</jsp-file>
    <init-param>
        <param-name>adminId</param-name>
        <param-value>admin</param-value>
    </init-param>
    <init-param>
        <param-name>adminPw</param-name>
        <param-value>1234</param-value>
    </init-param>
</servlet>
<servlet-mapping>
    <servlet-name>configEx</servlet-name>
    <url-pattern>/configEx.jsp</url-pattern>
</servlet-mapping>
</web-app>
```

- 지정된 jsp 파일에서 꺼내서 사용

```html
<body>
    <%!
        String adminId;
        String adminPw;
    %>

    <%
        adminId = config.getInitParameter("adminId");
        adminPw = config.getInitParameter("adminPw");
    %>

    <p>adminId : <%= adminId %></p>
    <p>adminPw : <%= adminPw %></p>

</body>
```

## application 객체

- 해당 서버 전체에서 접근이 가능한 데이터
- <context-param> 으로 선언하여 사용

```xml
<web-app>
    <context-param>
        <param-name>imgDir</param-name>
        <param-value>/upload/img</param-value>
    </context-param>
    <context-param>
        <param-name>testServerIP</param-name>
        <param-value>127.0.0.1</param-value>
    </context-param>
</web-app>
```

- jsp 에서 가져오기

```html
<body>
    <%!       
        String imgDir;
        String testServerIP;
    %>

    <%
        imgDir = application.getInitParameter("imgDir");
        testServerIP = application.getInitParameter("testServerIP");
    %>

    <p>imgDir : <%= imgDir %></p>
    <p>testServerIP : <%= testServerIP %></p>

</body>
```

### application.setAttribute & application.getAttribute

- xml 문서가 아닌 jsp 파일 내부에서 application 에서 공통으로 사용 가능한 값을 설정하고 꺼내 쓰는 법
- setAttribute 로 설정, getAttribute 꺼내기
- 꺼낼 때는 반드시 (String) 형변환이 필요하다

```html
<%
    application.setAttribute("connetedIP", "127.10.10.10");
    connetedIP = (String) application.getAttribute("connetedIP");
%>
```

## out 객체

- our.print("") 를 사용해서 html 에 문자열을 출력 가능

## exception 객체

- 페이지에서 에러가 발생 했을 경우 에러 처리를 해주는 객체
- 페이지 상단에 페이지 지시자 <%@ %> 를 사용해서 예외 발생 시 연결할 페이지 설정 필요

```html
<%@ page errorPage="errorPage.jsp" %>
```

- 에러가 발생하면 받는 페이지 코드
- 에러 페이지임을 페이지 지시자로 표기 <%@ page isErrorPage="true" %>
- exception 객체로 부터 에러 메세지를 받아서 출력

```html
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
```
