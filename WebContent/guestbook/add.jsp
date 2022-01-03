<%@page import="guestbook.GuestBookDTO"%>
<%@page import="guestbook.GuestBookDAOImpl"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
GuestBookDTO dto = new GuestBookDTO();

dto.setName(request.getParameter("name"));
dto.setPassword(request.getParameter("password"));
dto.setContent(request.getParameter("content"));

GuestBookDAOImpl dao = new GuestBookDAOImpl(application);

int result = dao.insert(dto);

dao.close();

response.sendRedirect("list.jsp");
%>
