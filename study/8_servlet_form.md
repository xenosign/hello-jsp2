# Form 데이터 처리

## get, post

- doGet : GET 방식 정보를 받는 메서드
- goPost : POST 방식 정보를 받는 메서드
- 생략시 get 으로 날아간다

## request 에서 제공하는 메서드

### request.getParameter("key 이름");

- 파라미터로 넘어오는 값 1개만 가져오는 메서드, 매개변수로 key 이름을 넘기면 해당 key 에 매칭하는 값 1개를 받아 온다

### getParameterValues("key 이름");

- 파라미터로 넘오는 값 여러개를 배열로 반환하는 메서드
- 배열은 Arrays.toString(배열); 로 출력한다

## 모든 파라미터 값을 확인하고 싶을 때

```java
// 백엔드 엔지니어 입장에서 모든 Prameter 가 잘 들어오는지 확인하기 위한 구문
Enumeration<String> names = request.getParameterNames();
while(names.hasMoreElements()) {
	String name = (String) names.nextElement();
	System.out.println("name : " + name);
}
```


https://jin-container.tistory.com/2