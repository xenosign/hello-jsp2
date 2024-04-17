import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

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
