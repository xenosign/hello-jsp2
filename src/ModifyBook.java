import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

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
