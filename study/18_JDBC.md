# JDBC

## MySQL JDBC 연동

- https://velog.io/@yiseull/IntelliJ-MySQL-%EC%97%B0%EB%8F%99
- 서블렛에서 MYSQL 커넥터 연결 에러 해결
  - https://steady-benny.medium.com/how-to-handle-jdbc-exception-32be64e1d831
- Loading class 'com.mysql.jdbc.Driver'. This is deprecated. The new driver class is 'com.mysql.cj.jdbc.Driver'. 에러 해결 
  - https://6161990src.tistory.com/76
  - Driver 클래스명을 com.mysql.jdbc.Driver 에서 com.mysql.cj.jdbc.Driver 로 변경

## JDBC 연결

```java
@WebServlet("/newBook")
public class NewBook extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 서버 접속 정보 설정
		String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
		String server = "localhost";
		String database = "test";
		String username = "root";
		String password = "1234";
		String dbURL = "jdbc:mysql://" + server + "/" + database + "?useSSL=false";

        // 접속 및 SQL 쿼리 스테이트 먼트 변수 선언
		Connection conn = null;
		Statement stmt = null;
        
		try {
			// 라이브러리 가져오기
			Class.forName(MYSQL_DRIVER);

			// DB 연결
			conn = DriverManager.getConnection(dbURL, username, password);
			System.out.println("정상적으로 연결 되었습니다.");
			stmt = conn.createStatement();

			// 쿼리 수행
			String SQL = "INSERT INTO book(book_name, book_loc) VALUES ('" + bookName + "', '" + bookLoc + "')";
			int result = stmt.executeUpdate(SQL);

			if(result == 1) {
				out.println("INSERT Success ^-^");
			} else {
				out.println("INSERT Fail T_T");
			};
		} catch (Exception e) {
            e.printStackTrace();
        } finally {
			try {
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
```

### DB 조회 코드

```java
@WebServlet("/showBook")
public class ShowBook extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";

		String server = "localhost";
		String database = "test";
		String username = "root";
		String password = "1234";
		String dbURL = "jdbc:mysql://" + server + "/" + database + "?useSSL=false";

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			Class.forName(MYSQL_DRIVER);

			conn = DriverManager.getConnection(dbURL, username, password);
			stmt = conn.createStatement();

			String sql = "select * from book";
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int book_id = rs.getInt("book_id");
				String book_name = rs.getString("book_name");
				String book_loc = rs.getString("book_loc");

				out.print("book_id : " + book_id);
				out.print(", book_name : " + book_name);
				out.print(", book_loc : " + book_loc + "</br>");
			}
		} catch (Exception e) {
            e.printStackTrace();
        } finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
```

### DB 수정 코드

- PreparedStatement 적용
- PreparedStatement 를 이용해서 SQL 구문의 뼈대만 잡아놓고, 각각의 값은 상황에 맞게 삽입
- SQL 문에 동적 부분을 ? 로 작성하고, ? 의 순서에 맞게 setString 으로 데이터를 삽입하여 DB 에 전달 가능!

- Pres

```java
@WebServlet("/modifyBook")
public class ModifyBook extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";

		String server = "localhost";
		String database = "test";
		String username = "root";
		String password = "1234";
		String dbURL = "jdbc:mysql://" + server + "/" + database + "?useSSL=false";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(MYSQL_DRIVER);
			conn = DriverManager.getConnection(dbURL, username, password);

            // PreparedStatement 적용
			String SQL = "UPDATE book SET book_loc = ? WHERE book_id = ?";
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, request.getParameter("book_loc"));
			pstmt.setString(2, request.getParameter("book_id"));

			int result = pstmt.executeUpdate();

			if(result == 1) {
				out.print("UPDATE success!!");
			} else {
				out.print("UPDATE fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}


    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
```
