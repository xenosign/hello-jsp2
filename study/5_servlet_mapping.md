# 서블렛 매핑

## web.xml 웹 설정 파일을 통한 매핑

- src > main > webapp > WEB-INF > web.xml 파일에 설정 적용
- <servlet> 태그로 설정 하고 <servlet-mapping> 으로 호출한 클래스를 매핑한다
-

```html
  <servlet>
  	<servlet-name>HelloServlet</servlet-name>
  	<servlet-class>com.hello.HelloServle/t</servlet-class>
  </servlet>
  <servlet-mapping>
  	<servlet-name>HelloServlet</servlet-name>
  	<url-pattern>/HS</url-pattern>
  </servlet-mapping>
```

## 어노테이션을 활용한 매핑

- @WebServlet 어노테이션을 매핑하려는 서블렛 코드에 추가하여 주소를 직접 매핑한다

```java
import javax.servlet.annotation.WebServlet;

// 어노테이션에 주소 값을 설정
@WebServlet("/HS1")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
}
```