package dao;

import dto.BookDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BookDaoJDBC {
    String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
    String server = "localhost";
    String database = "test";
    String username = "root";
    String password = "1234";
    String dbURL = "jdbc:mysql://" + server + "/" + database + "?useSSL=false";

	public BookDaoJDBC() {
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