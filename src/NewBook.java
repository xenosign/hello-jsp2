import dao.BookDao;
import dto.BookDto;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.print.Book;
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

		BookDto book = new BookDto(bookName, bookLoc);

		BookDao bookDao = new BookDao();

		int result = bookDao.insert(book);

		if(result == 1) {
			out.println("<h1>Book Create Success!!</h1>");
		} else {
			out.println("<h1>Book Create Fail</h1>");
		}


    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
