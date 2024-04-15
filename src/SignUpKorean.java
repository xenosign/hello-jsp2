import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Enumeration;


@WebServlet("/signUpKorean")
public class SignUpKorean extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// post 방식 한글 설정
		request.setCharacterEncoding("UTF-8");

		response.setContentType("text/html; charset=UTF-8");

		PrintWriter out = response.getWriter();
		String name = request.getParameter("name");
		String nickname = request.getParameter("nickname");

		out.print("<h1>" + name + "</h1>");
		out.print("<h1>" + nickname + "</h1>");

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
