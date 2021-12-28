<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%!
	Connection conn=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	String url="jdbc:oracle:thin:@192.168.3.52:1521:xe";
	String user="webdb";
	String pass="webdb";

	String sql="insert into author values(seq_author_id.nextval,?,?)";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert</title>
</head>
<body>
	<%
	request.setCharacterEncoding("UTF-8");
	String author_name=request.getParameter("author_name");
	String author_desc=request.getParameter("author_desc");
	
	try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn=DriverManager.getConnection(url,user,pass);
		pstmt=conn.prepareStatement(sql);
		
		pstmt.setString(1,author_name);
		pstmt.setString(2,author_desc);
		pstmt.executeUpdate();
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		try{
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	%>
</body>
</html>