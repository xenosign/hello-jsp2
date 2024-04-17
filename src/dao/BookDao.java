package dao;

import dto.BookDto;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

public class BookDao {
	// 연결 정보가 서버의 context.xml 에 존재하므로 바로 받아서 사용
	DataSource dataSource;

	public BookDao() {
		try {			
			Context context = new InitialContext();
			// context.xml 에 선언한 DB 이름을 찾아서 연결 수립
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/mysqlBook");
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
			conn = dataSource.getConnection();
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

	public int insert(BookDto book) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			conn = dataSource.getConnection();

			String SQL = "INSERT INTO book(book_name, book_loc) VALUES (?, ?)";

			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, book.getBookName());
			pstmt.setString(2, book.getBookLoc());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}


}