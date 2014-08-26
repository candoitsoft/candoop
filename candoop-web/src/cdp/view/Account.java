package cdp.view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Account extends HttpServlet {
	
	private static final long serialVersionUID = -4906478200802549696L;

	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("text/html; charset=UTF-8");
		String uri = req.getRequestURI();
		try{
			if(uri.indexOf("Login") > 0){
				req.getRequestDispatcher("account/login.jsp").include(req, res);
			} else if(uri.indexOf("Signin") > 0){
				req.getRequestDispatcher("account/signin.jsp").include(req, res);
			}
		} catch(ServletException e){
			e.printStackTrace();
		}
	}

}
