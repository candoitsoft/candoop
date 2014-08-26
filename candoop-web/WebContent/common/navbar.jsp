<%@page import="cdp.comm.CdpUserObj"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
CdpUserObj userObjNav = (CdpUserObj) session.getAttribute("CdpUserObj");

String toUrl = request.getRequestURI();
if (toUrl == null || toUrl.equals("")){ toUrl = "/"; }
String pageName = "";
if (toUrl.indexOf("Admin") > 0){
	pageName = "Admin";
} else if (toUrl.indexOf("Dashboard") > 0){
	pageName = "Dashboard";
} else if (toUrl.indexOf("Report") > 0){
	pageName = "Report";
} else if (toUrl.indexOf("Logdata") > 0){
	pageName = "Logdata";
} else if (toUrl.indexOf("Metadata") > 0){
	pageName = "Metadata";
}
%>
<script>
function logout() {
	if(confirm("로그아웃 하시겠습니까?")){
		var frm = document.getElementById("navFrm");
		frm.cmd.value="logout";
		frm.action="/Confirm";
		frm.submit();
	}
}
</script>
<nav class="navbar navbar-inverse" role="navigation">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
			<span class="glyphicon glyphicon-list"></span>
		</button>
		<a class="navbar-brand" href="/">
			<span class="text-white">Candoit</span><span class="text-red">Soft</span>
		</a>
	</div>
	
	<div class="collapse navbar-collapse navbar-ex1-collapse">
		<ul class="nav navbar-nav">
			<li id="navLiDashboard" <%if("Dashboard".equals(pageName)){ out.print("class=\"active\"");} %>><a href="/Dashboard"><i class="glyphicon glyphicon-stats"></i> 대시보드</a></li>
			<li id="navLiReport" <%if("Report".equals(pageName)){ out.print("class=\"active\"");} %>><a href="/Report"><i class="glyphicon glyphicon-file"></i> 보고서</a></li>
			<li id="navLiLog" <%if("Logdata".equals(pageName)){ out.print("class=\"active\"");} %>><a href="/Logdata"><i class="glyphicon glyphicon-list"></i> 로그데이타</a></li>
			<li id="navLiMeta" <%if("Metadata".equals(pageName)){ out.print("class=\"active\"");} %>><a href="/Metadata"><i class="glyphicon glyphicon-search"></i> 메타데이타</a></li>
<% if("ADMIN".equals(userObjNav.getUsrType())) { %>
			<li id="navLiAdmin" <%if("Admin".equals(pageName)){ out.print("class=\"active\"");} %>><a href="/Admin"><i class="glyphicon glyphicon-wrench"></i> 관리자메뉴</a></li>
<% } %>
		</ul>
		
		<form id="navFrm" name="navFrm" class="navbar-form navbar-right btn-group" role="search">
			<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
				<i class="glyphicon glyphicon-cog"></i>
				<span class="caret"></span>
			</button>
			<ul class="dropdown-menu" role="menu">
				<li><a href="/Modify">사용자정보 변경</a></li>
				<li class="divider"></li>
				<li><a href="javascript:logout();">로그아웃</a></li>
			</ul>
			<input type="hidden" id="cmd" name="cmd" />
			<input type="hidden" id="toUrl" name="toUrl" value="/" />
		</form>
		<ul class="nav navbar-nav navbar-right">
			<li><a><span class="text-white"><%=userObjNav.getNicname()%> 님</span></a></li>
		</ul>			
				
	</div>
</nav>