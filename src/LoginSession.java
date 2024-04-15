import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

@WebServlet("/loginSession")
public class LoginSession extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		String id = request.getParameter("id");
		String pw = request.getParameter("pw");

		out.println("id : " + id);
		out.println("pw : " + pw);

		HttpSession session = request.getSession();
		session.setAttribute("id", id);

		response.sendRedirect("loginSessionOk.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
