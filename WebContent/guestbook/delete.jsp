<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="guestbook.GuestBookDAOImpl"%>
<%
int no = Integer.parseInt(request.getParameter("no"));
String password = request.getParameter("password");
GuestBookDAOImpl dao = new GuestBookDAOImpl(application);
int result = dao.delete(no, password);
dao.close();
request.setAttribute("result", result);
%>
<html>
<head>
</head>
<body>
<script>
	if(${result} > 0){
		alert("삭제성공");
		window.location.href = "list.jsp";
	} else{
		alert("삭제실패")
		window.location.href = "list.jsp";
	}
</script>
</body>
</html>