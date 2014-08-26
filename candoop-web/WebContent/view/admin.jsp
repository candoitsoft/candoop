<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String adminTab = request.getParameter("adminTab");
if(adminTab == null || "".equals(adminTab)){
	adminTab = "project";
}

%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>어드민</title>
<!-- 공통 라이브러리 시작 -->
<%@include file="/common/header.jsp"%>
<!-- 공통 라이브러리 끝 -->
</head>
<body>
<!-- 상단 네비게이션 메뉴 시작 -->	
<%@include file="/common/navbar.jsp"%>
<!-- 상단 네비게이션 메뉴 끝 -->
<div class="container">

	<ul id="adminTab" class="nav nav-tabs">
		<li <%if("project".equals(adminTab)){out.print("class='active'");} %> ><a href="#prjTab" data-toggle="tab">프로젝트 관리</a></li>
		<li <%if("user".equals(adminTab)){out.print("class='active'");} %> ><a href="#usrTab" data-toggle="tab">사용자 관리</a></li>
		<li <%if("group".equals(adminTab)){out.print("class='active'");} %> ><a href="#grpTab" data-toggle="tab">그룹 관리</a></li>
	</ul>
	<div id="adminTabContent" class="tab-content">

<!-- 프로젝트 관리 탭 시작 -->
<div class="tab-pane fade <%if("project".equals(adminTab)){out.print("active");} %> in" id="prjTab">
<div class="col-lg-3">
<h3>프로젝트 관리</h3>
<p class="text-muted">프로젝트 등록 및 수정</p>

<div class="row">
	<div class="col-xs-6">
		<button class="btn btn-success btn-block">신규 등록</button>
	</div>
	<div class="col-xs-6">
		<button class="btn btn-warning btn-block">공유 요청</button>
	</div>
</div>
<br>
<div class="list-group">
	<a href="#" class="list-group-item active">프로젝트 1</a>
	<a href="#" class="list-group-item">프로젝트 2</a>
	<a href="#" class="list-group-item">프로젝트 3</a>
</div>

</div>
<div class="col-lg-9">
	<h4>사용자</h4>
	
	<h4>공유 그룹</h4>
	
</div>

</div>
<!-- 프로젝트 관리 탭 끝 -->

<!-- 사용자 관리 탭 시작 -->
<div class="tab-pane fade <%if("user".equals(adminTab)){out.print("active");} %> in" id="usrTab">
</div>
<!-- 사용자 관리 탭 끝 -->

<!-- 그룹 관리 탭 시작 -->
<div class="tab-pane fade <%if("group".equals(adminTab)){out.print("active");} %> in" id="grpTab">
</div>
<!-- 그룹 관리 탭 끝 -->

	</div>
</div>
<!-- 하단 푸터 시작 -->
<%@include file="/common/footer.jsp"%>
<!-- 하단 푸터 끝 -->
</body>
</html>