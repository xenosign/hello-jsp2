package dao;

import dto.BookDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

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