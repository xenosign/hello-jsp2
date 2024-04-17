# Connection Pool

- 웹 서버의 경우 데이터 통신 마다 Connection 을 만들고 종료하는 작업을 반복해야 함
- 통신이 많아질 경우, [context.xml](..%2F..%2F..%2Ftomcat%2Fapache-tomcat-8.5.100%2Fconf%2Fcontext.xml)퍼포먼스 상의 이슈가 발생 가능
- 따라서 서버가 한가할 때 통신 관련 내용인 Connection Pool 을 만들어 놓고 Rent, Return 의 구조로 사용

## Connection Pool 설정

- Tomcat 서버 폴더 > conf 폴더의 context.xml 파일에 아래의 xml 코드 추가
- name 은 Servlet 에서 부를 때 사용하므로 잘 기억해야 함
- Datasource 클래스로 부를 것이므로 type 도 올바르게 설정 필요

```xml
<Resource
    auth="Container"
    driverClassName="com.mysql.cj.jdbc.Driver"
    url="jdbc:mysql://localhost/test?useSSL=false"
    username="root"
    password="1234"
    name="jdbc/mysqlBook"
    type="javax.sql.DataSource"
    maxActive="4"
    maxWait="10000"
/>
```

- 맥의 경우 useSSL=false 해제 필요

```xml
    <Resource
            auth="Container"
            driverClassName="com.mysql.cj.jdbc.Driver"
            url="jdbc:mysql://localhost/test"
            username="root"
            password="1234"
            name="jdbc/mysqlBook"
            type="javax.sql.DataSource"
            maxActive="4"
            maxWait="10000"
    />
```

## DAO 에 접속 세팅

- DataSource 를 이용하여 연결
- Context 클래스를 이용 서버에 등록된 DB 이름을 찾아서 바로 연결 한다
- conn = dataSource.getConnection();

```java
import javax.sql.DataSource;

public class BookDao {
	// 연결 정보가 서버의 context.xml 에 존재하므로 바로 받아서 사용
	DataSource dataSource;

	public BookDao() {
		try {
            // context.xml 에 선언한 DB 이름을 찾아서 연결 수립
			Context context = new InitialContext();	
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/mysqlBook");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    public ArrayList<BookDto> select() {       

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // dataSource 에서 바로 연결 수립 하여 사용
            conn = dataSource.getConnection();
            String sql = "select * from book";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```