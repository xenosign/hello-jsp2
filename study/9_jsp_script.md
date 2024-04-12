# JSP 주요 스크립트

## 선언 태그

- <%! %> 로 사용
- Java 에서 멤버 변수와 메서드를 선언하는 내용만을 담는다
- 실제로 Java 코드를 적용하는 부분은 다른 태그를 사용

```
<%!
     int num = 10;
     String str = "jsp";
     ArrayList<String> list = new ArrayList<>();

     public void method() {
          System.out.println("---- jspMethod() ----");
     }
%>
```

## 스크립트 태그

- <% %> 로 사용, 느낌표(!) 가 없다
- JSP 페이지에 Java 코드를 넣기 위한 태그

```html
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
```

## 표현식 태그

- Java 의 변수 및 메서드의 반환값을 출력하는 태그

```html
<p>num is <%= num %></p>
```

## 지시어

- <%@ %> 사용

### 페이지 설정 

- <%@ page %> 서버에서 jsp 페이지를 처리하는 방법에 대한 정의
- 라이브러리 임포트 시에도 사용

```
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
```

### include
 
- 특정 파일 include 시 사용, 헤더나 푸터 파일 통으로 불러올 때!

```
<% include file="header.jsp" %>
```

```html

```

### 외부 라이브러리 가져오기

```
<% taglib uri="라이브러리주소" prefix="c" %>
```