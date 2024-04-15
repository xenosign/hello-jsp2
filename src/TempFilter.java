import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

public class TempFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("------ Filter init() ------");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("------ Filter doFilter() ------");

		request.setCharacterEncoding("UTF-8");

		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		System.out.println("------ Filter destroy() ------");
		Filter.super.destroy();
	}
}
