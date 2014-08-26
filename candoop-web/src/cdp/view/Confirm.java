package cdp.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cdp.comm.CdpMsg;
import cdp.comm.CdpUserObj;
import cdp.dao.AcDao;

public class Confirm extends HttpServlet {
	
	private static final long serialVersionUID = 424840240129550483L;

	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		
		String cmd = req.getParameter("cmd");
		String toUrl = req.getParameter("toUrl");
		HttpSession session = req.getSession();
		
		AcDao dao = AcDao.getInstance();
		int result = 0;
		CdpUserObj userObj = null;
		
		if(cmd == null || toUrl == null || "".equals(toUrl)){
			out.println(CdpMsg.approachError());
		} else {
			if("insertAcGrp".equals(cmd)){
				//신규 그룹 등록
				String grpId = req.getParameter("grpId");
				String grpName = req.getParameter("grpName");
				String grpText = req.getParameter("grpText");
				String grpDeptId = req.getParameter("grpDept");
				int daoRes = dao.insertGrp(grpId, grpName, grpText, grpDeptId);
				if(daoRes > 0){
					res.sendRedirect(toUrl);
				} else {
					out.println(CdpMsg.saveError());
				}
			} else if("signin".equals(cmd)){
				//신규 회원 가입
				userObj = dao.getUserObj(req);
				if(userObj != null && userObj.getEmail() != null && !"".equals(userObj.getEmail())){
					result = dao.insertUserObj(userObj);
				}
				if (result > 0) {
					session.setAttribute("CdpUserObj", dao.getUserObj(userObj.getEmail()));
					res.sendRedirect(toUrl);
				} else {
					out.println(CdpMsg.saveError());
				}
			} else if("login".equals(cmd)){
				//로그인
				String email = req.getParameter("loginEmail");
				String passwd = req.getParameter("loginPw");
				result = dao.login(email, passwd);
				
				if (result == 2) {
					session.setAttribute("CdpUserObj", dao.getUserObj(email));
					res.sendRedirect(toUrl);
				} else if (result == 1) {
					out.println(CdpMsg.error("비밀번호가 맞지 않습니다."));
				} else if (result == 0) {
					out.println(CdpMsg.error("없는 ID입니다. 다시 확인하고 로그인 하십시오."));
				} else {
					out.println(CdpMsg.error("로그인 하는 도중 오류가 발생했습니다."));
				}
			} else if("logout".equals(cmd)){
				session.removeAttribute("CdpUserObj");
				if(toUrl != null && !toUrl.equals("")){
					res.sendRedirect(toUrl);
				} else {
					out.println(CdpMsg.approachError());
				}
			}
			
		}
	}
}
