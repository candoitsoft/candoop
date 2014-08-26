<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CandoitSoft</title>
<!-- 공통 라이브러리 시작 -->
<%@include file="/common/header.jsp"%>
<!-- 공통 라이브러리 끝 -->
<script>
function resizeTopIframe() {
	var iframe_content = $("#kibanaFrame").contents().find('body');
	var dynheight = iframe_content.height();
    document.getElementById("kibanaFrame").height = parseInt(dynheight) + 10;
}
//주기적으로 iframe 리사이징.
setInterval('resizeTopIframe()', 500);

</script>
</head>
<body>
<!-- 상단 네비게이션 메뉴 시작 -->	
<%@include file="/common/navbar.jsp"%>
<!-- 상단 네비게이션 메뉴 끝 -->

<!-- 키바나 iframe 시작 -->
<iframe id="kibanaFrame" src="/kibana" width="100%" scrolling="no" ></iframe>
<!-- 키바나 iframe 끝 -->

<!-- 하단 푸터 시작 -->
<%@include file="/common/footer.jsp"%>
<!-- 하단 푸터 끝 -->
</body>
</html>