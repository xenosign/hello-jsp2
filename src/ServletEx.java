import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/se")
public class ServletEx extends HttpServlet {
	private static final long serialVersionUID = 1L;       
    
    public ServletEx() {
        super(); 
    }
	
    // servlet 실행이 되는 시점
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		System.out.println(" --------- doGet() --------- ");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		doGet(request, response);
	}
	
	@PostConstruct	
	public void postConsrtuct() {
		System.out.println(" --------- postConsrtuct() --------- ");
	}
	
	// servlet 생성
	@Override
	public void init() throws ServletException {
		System.out.println(" --------- init() --------- ");
	}
	
	// servlet 종료
	@Override
	public void destroy() {
		System.out.println(" --------- destroy() --------- ");
	}
	
	@PreDestroy
	public void preDestroy() {
		System.out.println(" --------- preDestroy() --------- ");
	}

}
