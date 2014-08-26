package cdp.es;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cdp.comm.CdpUserObj;

public class CdpEsReq extends HttpServlet {
	
	private static final long serialVersionUID = 6136860156967845591L;
	
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("application/json; charset=UTF-8");
		PrintWriter out = res.getWriter();
		HttpSession session = req.getSession();
		CdpUserObj userObjNav = (CdpUserObj) session.getAttribute("CdpUserObj");
		
		if(userObjNav == null){
			out.println("로그인 후 이용해 주시기 바랍니다.");
		} else {
			String url = "";
			StringBuffer requestURL = req.getRequestURL();
			String queryString = req.getQueryString();
			if (queryString == null) {
				url = requestURL.toString();
			} else {
				url = requestURL.append('?').append(queryString).toString();
			}
			url = url.replaceFirst(":8080", "");
			url = url.replaceFirst("/es/", ":9200/");
			url = url.replaceFirst("/Es/", ":9200/");
//			System.out.println("url (" + req.getMethod() + "): " + url);
			
			res.setHeader("Access-Control-Allow-Origin", "*");
			res.setHeader("Access-Control-Allow-Methods", "OPTIONS, HEAD, GET, POST, PUT, DELETE");
			res.setHeader("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, Content-Length");
			
			if (req.getMethod().equals("OPTIONS")) {
				this.doEsPost(req, res, "OPTIONS");
//				System.out.println("OPTIONS 명령은 허가된 사용자만 가능합니다.");
			} else if (req.getMethod().equals("HEAD")) {
				this.doEsPost(req, res, "HEAD");
//				System.out.println("HEAD 명령은 허가된 사용자만 가능합니다.");
			} else if (req.getMethod().equals("PUT")) {
				this.doEsPost(req, res, "PUT");
//				System.out.println("PUT 명령은 허가된 사용자만 가능합니다.");
			} else if (req.getMethod().equals("DELETE")) {
				this.doEsPost(req, res, "DELETE");
//				System.out.println("DELETE 명령은 허가된 사용자만 가능합니다.");
			} else if (req.getMethod().equals("POST")) {
				this.doEsPost(req, res);
			} else {
				this.doGet(req, res);
			} 
		}
	}
	
	/**
	 * GET 방식 명령어 처리
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String url = "";
		StringBuffer data = new StringBuffer();
		BufferedReader bufferR;
		bufferR = req.getReader();

		CdpEsRes esr = CdpEsRes.getInstance();

		StringBuffer requestURL = req.getRequestURL();
		String queryString = req.getQueryString();
		if (queryString == null) {
			url = requestURL.toString().toLowerCase();
		} else {
			url = requestURL.append('?').append(queryString).toString();
		}
		url = url.replaceFirst(":8080", "");
		url = url.replaceFirst("/es/", ":9200/");
		url = url.replaceFirst("/Es/", ":9200/");
		
		String message = "";
		while ((message = bufferR.readLine()) != null) {
			data.append(message);
		}

		PrintWriter out = res.getWriter();
		out.print(esr.getEsGet(url, req.getMethod()));
	}
	
	/**
	 * POST 방식 명령어 처리.
	 */
	protected void doEsPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		doEsPost(req, res, "POST");
	}
	protected void doEsPost(HttpServletRequest req, HttpServletResponse res, String method) throws IOException {
		String url = "";
		StringBuffer data = new StringBuffer();
		BufferedReader bufferR;
		bufferR = req.getReader();

		CdpEsRes esr = CdpEsRes.getInstance();

		StringBuffer requestURL = req.getRequestURL();
		String queryString = req.getQueryString();
		if (queryString == null) {
			url = requestURL.toString().toLowerCase();
		} else {
			url = requestURL.append('?').append(queryString).toString();
		}
		url = url.replaceFirst(":8080", "");
		url = url.replaceFirst("/es/", ":9200/");
		url = url.replaceFirst("/Es/", ":9200/");
		
		String message = "";
		while ((message = bufferR.readLine()) != null) {
			data.append(message);
		}
		
		PrintWriter out = res.getWriter();
		out.print(esr.getEsPost(url, data.toString(), method));
	}
	
}
