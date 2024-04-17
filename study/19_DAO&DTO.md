# DAO 와 DTO

- DAO(Data Access Object) : DB 에 접속 하는 객체
- DTO(Data Transfer Object) : DB 의 데이터를 자바에서 사용하는 클래스 인스턴스로 변환한 객체
- 보통 별도의 package 로 나누어서 운영

## DAO(Data Access Object)

- DB 에 접속하는 과정 및 해당 DB 에서 필요한 기능을 정의한 클래스
- 생성자로 Driver 로드를 수행하여 미리 드라이버를 불러옴
- 특정 기능의 경우 DB 데이터를 반환하는 형태로 진행 되므로 return 값으로 DB 의 데이터 전달

```java
public class BookDao {
	String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
	String server = "localhost";
	String database = "test";
	String username = "root";
	String password = "1234";
	String dbURL = "jdbc:mysql://" + server + "/" + database + "?useSSL=false";

	public BookDao() {
		try {
			Class.forName(MYSQL_DRIVER);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<BookDto> select() {
		ArrayList<BookDto> list = new ArrayList<BookDto>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(dbURL, username, password);
			String sql = "select * from book";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int bookId = rs.getInt("book_id");
				String bookName = rs.getString("book_name");
				String bookLoc = rs.getString("book_loc");

				BookDto book = new BookDto(bookId, bookName, bookLoc);
				list.add(book);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}
}
```

## DTO(Data Transfer Object)

- DB 에서 받을 데이터를 객체 형태로 표현한 클래스
- DAO 에서 DTO 타입의 데이터로 받아와서 사용
- DAO 데이터를 받앗 DTO 의 생성자를 통해 배열 형태로 생성
- DAO 에서 특정 데이터가 필요할 경우 DTO 의 Getter 로 값에 접근

```java
public class BookDto {
    int bookId;
	String bookName;
	String bookLoc;

    // 생성자 선언
	public BookDto(int bookId, String bookName, String bookLoc) {
		this.bookId = bookId;
		this.bookName = bookName;
		this.bookLoc = bookLoc;
	}

	public int getBookId() {
		return bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public String getBookLoc() {
		return bookLoc;
	}	
}
```

## 실제 사용 코드

- DB 접속 및, 데이터 변환 등의 작업을 DAO, DTO 클래스에서 처리 하므로 비지니스 로직 코드가 짧아지는 효과

```java
import dao.BookDao;
import dto.BookDto;

@WebServlet("/bookSearch")
public class BookSearch extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		BookDao bookDao = new BookDao();
		ArrayList<BookDto> list = bookDao.select();

		for (BookDto bookDto : list) {
			out.println("bookId: " + bookDto.getBookId() + " bookName: " + bookDto.getBookName() + " bookLoc: " + bookDto.getBookLoc() + "<br>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
```