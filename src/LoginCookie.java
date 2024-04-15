import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/loginCookie")
public class LoginCookie extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		String id = request.getParameter("id");
		String pw = request.getParameter("pw");

		out.println("id : " + id);
		out.println("pw : " + pw);

		Cookie[] cookies = request.getCookies();
		Cookie cookie = null;

		for(Cookie c : cookies) {
			System.out.println("c.getName() " + c.getName() + " c.getValue() " + c.getValue());

			if (c.getName().equals("id")) {
				cookie = c;
			}
		}

		if (cookie == null) {
			System.out.println("cookie is null");
			cookie = new Cookie("id", id);
		}

		response.addCookie(cookie);
		cookie.setMaxAge(60 * 60);

		response.sendRedirect("loginCookieOk.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
