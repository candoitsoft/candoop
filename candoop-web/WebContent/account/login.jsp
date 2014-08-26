<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html">
<html>
<head>
<title>캔두잇소프트</title>
<!-- 공통 라이브러리 시작 -->
<%@include file="/common/header.jsp"%>
<!-- 공통 라이브러리 끝 -->
<link rel="stylesheet" href="/resource/css/signin.css">
<script>
function login(){
	var frm = document.loginFrm;
	frm.method="POST";
	frm.action="/Confirm";
	frm.submit();
}
</script>
</head>
<body class="body-login">
<div class="container">
	
	<form class="form-login" role="form" name="loginFrm" action="javascript:login();">
		<h2 class="form-login-heading">환영합니다!</h2>
		<input type="email" name="loginEmail" class="form-control" placeholder="아이디" required="required">
		<input type="password" name="loginPw" class="form-control" placeholder="비밀번호" required="required">
		<button class="btn btn-lg btn-primary btn-block" type="submit">로그인</button>
		<button class="btn btn-lg btn-success btn-block" type="button" onclick="location.href='/Signin'">회원가입</button>
		<input type="hidden" name="cmd" value="login" />
		<input type="hidden" name="toUrl" value="/Main" />
	</form>

</div>

<!-- 하단 푸터 시작 -->
<%@include file="/common/footer.jsp"%>
<!-- 하단 푸터 끝 -->
</body>
</html>