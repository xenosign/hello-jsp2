package dto;

import javax.servlet.annotation.WebServlet;
import java.util.ArrayList;

public class BookDto {
    int bookId;
	String bookName;
	String bookLoc;

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