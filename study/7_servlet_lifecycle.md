# 서블렛 라이프 사이클

## servlet 생명주기

- @PostConstruct -> init -> service -> destroy -> @PreDestroy 의 라이프 사이클을 가진다
- 각각의 라이프사이클에 대한 내용을 Override 해서 원하는 작업을 해줄 수 있다
- 단, @PostConstruct 와 @PreDestroy 는 어노테이션으로 등록한다

```java
@WebServlet("/tsc")
public class TestServletClass extends HttpServlet {
	private static final long serialVersionUID = 1L;       
    
    public TestServletClass() {
        super(); 
    }
	
    // servlet 실행이 되는 시점
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("--------- doGet() ---------");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		doGet(request, response);
	}
	
	@PostConstruct
	public void funcPC() {
		System.out.println("--------- @PostConstruct ---------");
	}
	
	@Override
	public void init() throws ServletException {
		System.out.println("--------- init() ---------");
	}
	
	@Override
	public void destroy() {
		System.out.println("--------- destroy() ---------");
	}
	
	@PreDestroy
	public void funcPD() {
		System.out.println("--------- @PreDestroy ---------");
	}
}
```

- 위의 코드 실행 시, 아래와 같은 순서로 실행이 된다

```
--------- @PostConstruct ---------
--------- init() ---------
--------- doGet() ---------
4월 11, 2024 2:37:53 오후 org.apache.catalina.core.StandardContext reload
정보: 이름이 [/hello-jsp]인 컨텍스트를 다시 로드하는 작업이 시작되었습니다.
--------- destroy() ---------
--------- @PreDestroy ---------
```
