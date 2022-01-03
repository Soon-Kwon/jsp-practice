<%@page import="guestbook.GuestBookDTO"%>
<%@page import="java.util.List"%>
<%@page import="guestbook.GuestBookDAOImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% 
GuestBookDAOImpl dao = new GuestBookDAOImpl(application);
List<GuestBookDTO> lists = dao.getList();
request.setAttribute("lists", lists);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>방명록</title>
</head>
<body>
	<form action="add.jsp" method="post">
	<table border=1 width=500>
		<tr>
			<td>이름</td><td><input type="text" name="name"></td>
			<td>비밀번호</td><td><input type="password" name="password"></td>
		</tr>
		<tr>
			<td colspan=4><textarea name="content" cols=60 rows=5></textarea></td>
		</tr>
		<tr>
			<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
		</tr>
	</table>
	</form>
	<br/>
	<c:forEach items="${lists }" var="guestlist">
	<table width=510 border=1>
		<tr>
			<td>${guestlist.no }</td>
			<td>${guestlist.name }</td>
			<td>${guestlist.regDate }</td>
			<td><a href="deleteform.jsp?no=${guestlist.no }">삭제</a></td>
		</tr>
		<tr>
			<td colspan=4>${guestlist.content }</td>
		</tr>
	</table>
        <br/>
	</c:forEach>
</body>
</html>