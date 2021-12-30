<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메일 리스트</title>
</head>
<body>
	<h2>메일 리스트에 가입되었습니다.</h2>
	<p>입력한 정보 내역입니다.</p>
	
	<c:forEach items="${lists }" var="emaillist">
		<table border="1" cellpadding="5" cellspacing="2">
		<tr>
			<td align=right width="110">Last name: </td>
			<td width="170">${emaillist.lastName }</td>
		</tr>
		<tr>
			<td align=right width="110">First name: </td>
			<td width="170">${emaillist.firstName }</td>
		</tr>
		<tr>
			<td align=right width="110">Email address: </td>
			<td width="170">${emaillist.email }</td>
		</tr>
		</table>
	</c:forEach>
	<br>
	<p>
		<a href="form.jsp">추가메일 등록</a>
	</p>
	<br>
</body>
</html>