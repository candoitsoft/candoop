package cdp.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cdp.comm.CdpMsg;
import cdp.comm.CdpUserObj;

public class View extends HttpServlet {
	
	private static final long serialVersionUID = -3451094404332141203L;

	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		String uri = req.getRequestURI();
		HttpSession session = req.getSession();
		CdpUserObj userObj = (CdpUserObj) session.getAttribute("CdpUserObj");
		
		if(userObj == null){
			out.println(CdpMsg.loginGoFirst());
		} else {
			if(uri.indexOf("Dashboard") > 0){
				req.getRequestDispatcher("view/dashboard.jsp").include(req, res);
			} else if(uri.indexOf("Report") > 0){
				req.getRequestDispatcher("view/report.jsp").include(req, res);
			} else if(uri.indexOf("Logdata") > 0){
				req.getRequestDispatcher("view/logdata.jsp").include(req, res);
			} else if(uri.indexOf("Metadata") > 0){
				req.getRequestDispatcher("view/metadata.jsp").include(req, res);
			} else if(uri.indexOf("Admin") > 0){
				if("ADMIN".equals(userObj.getUsrType())){
					req.getRequestDispatcher("view/admin.jsp").include(req, res);
				} else {
					out.print(CdpMsg.approachError());
				}
			} else {
				res.sendRedirect("/Dashboard");
			}
		}
	}

}
