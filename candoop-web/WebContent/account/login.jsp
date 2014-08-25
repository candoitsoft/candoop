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
	
}
</script>
</head>
<body>
<div class="container">
	
	<form class="form-signin" role="form" submit="javascript:login();">
		<h2 class="form-signin-heading">환영합니다!</h2>
		<input type="text" class="form-control" placeholder="아이디" required="" autofocus="">
		<input type="password" class="form-control" placeholder="비밀번호" required="">
<!-- 
		<div class="checkbox">
			<label>
				<input type="checkbox" value="remember-me"> 자동 로그인
			</label>
		</div>
-->
		<button class="btn btn-lg btn-primary btn-block" type="submit">로그인</button>
		<button class="btn btn-lg btn-success btn-block" type="button" onclick="location.href='/Signin'">회원가입</button>
	</form>

</div>

<!-- 하단 푸터 시작 -->
<%@include file="/common/footer.jsp"%>
<!-- 하단 푸터 끝 -->
</body>
</html>