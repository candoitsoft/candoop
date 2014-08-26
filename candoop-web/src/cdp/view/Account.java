package cdp.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cdp.comm.CdpMsg;

public class Account extends HttpServlet {
	
	private static final long serialVersionUID = -4906478200802549696L;

	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		String uri = req.getRequestURI();
		
		if(uri.indexOf("Login") > 0){
			req.getRequestDispatcher("account/login.jsp").include(req, res);
		} else if(uri.indexOf("Signin") > 0){
			req.getRequestDispatcher("account/signin.jsp").include(req, res);
		} else {
			out.println(CdpMsg.approachError());
		}
	}

}
