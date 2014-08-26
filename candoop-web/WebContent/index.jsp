<%@page import="cdp.comm.CdpUserObj"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
CdpUserObj userObj = (CdpUserObj) session.getAttribute("CdpUserObj");
if (userObj == null) {
	//로그인 오류시 login.jsp 페이지로 이동.
	response.sendRedirect("/Login");
} else {
	response.sendRedirect("/Main");
}
%>
