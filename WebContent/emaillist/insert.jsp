<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="emaillist.EmaillistDTO"%>
<%@page import="emaillist.EmaillistDAOImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%

String ln = request.getParameter("ln");
String fn = request.getParameter("fn");
String email = request.getParameter("email");
EmaillistDAOImpl dao = new EmaillistDAOImpl(application);
int result = dao.insert(ln, fn, email);

String redirect = request.getParameter("redirect");
if(redirect == "y" || redirect.equals("y")){
	List<EmaillistDTO> lists = dao.getList();
	request.setAttribute("lists", lists);
	request.getRequestDispatcher("list.jsp").forward(request, response);
}

if(result == 1){
	List<EmaillistDTO> lists = dao.getList();
	request.setAttribute("lists", lists);
	request.getRequestDispatcher("list.jsp").forward(request, response);
}else{
	System.out.println("오류 발생");
}
dao.close();
%>
