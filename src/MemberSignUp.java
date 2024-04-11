import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/mSignUp")
public class MemberSignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;       
    
    public MemberSignUp() {
        super(); 
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		System.out.println("---- doGet() ----");
		
		String m_name = request.getParameter("m_name");
		String m_password = request.getParameter("m_password");
		String m_gender = request.getParameter("m_gender");
		String[] m_hobbys = request.getParameterValues("m_hobby");
		String m_residence = request.getParameter("m_residence");
		
		System.out.println("m_name : " + m_name);
		System.out.println("m_password : " + m_password);
		System.out.println("m_gender : " + m_gender);
		System.out.println("m_hobby : " + Arrays.toString(m_hobbys));
		System.out.println("m_residence : " + m_residence);
		
		// 백엔드 엔지니어 입장에서 모든 Prameter 가 잘 들어오는지 확인하기 위한 구문
		Enumeration<String> names = request.getParameterNames();
		while(names.hasMoreElements()) {
			String name = (String) names.nextElement();
			System.out.println("name : " + name);
		}
		
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("---- doPost() ----");
		doGet(request, response);
	}

}
