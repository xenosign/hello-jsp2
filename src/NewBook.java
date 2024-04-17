import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

@WebServlet("/newBook")
public class NewBook extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		String bookName = request.getParameter("book_name");
		String bookLoc = request.getParameter("book_loc");

		String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
		String server = "localhost";
		String database = "test";
		String username = "root";
		String password = "1234";
		String dbURL = "jdbc:mysql://" + server + "/" + database + "?useSSL=false";

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
