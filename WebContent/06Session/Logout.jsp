<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 방법1 
session.removeAttribute("UserId");
session.removeAttribute("UserName");
// 방법2
session.invalidate();
// 속성 삭제 후 페이지 이동
response.sendRedirect("LoginForm.jsp");
%>