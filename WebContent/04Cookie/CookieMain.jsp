<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cookie</title>
</head>
<body>
	<h2>1. 쿠키(Cookie) 설정</h2>
	<%
	Cookie cookie=new Cookie("myCookie", "맛있는 쿠키"); // 쿠키 생성
	cookie.setPath(request.getContextPath()); // 경로를 컨텍스트 루트로 설정
	%>
</body>
</html>