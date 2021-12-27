<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@page import="java.sql.Connection" %>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException"%>

<%!
	Connection conn=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	int cnt=0;
	String url="jdbc:oracle:thin:@192.168.3.52:1521:xe";
	String user="hr";
	String pass="hr";

	String sql="select e.employee_id, e.first_name, " 
			+ "to_char(e.hire_date,'yyyy-mm-dd') hire_date, "
			+ "d.department_name,mng.first_name manager, e.salary "
			+ "from employees e, employees mng, departments d "
			+ "where e.department_id = d.department_id "
			+ "and e.manager_id = mng.employee_id";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table {
 border-collapse: collapse
}
</style>
</head>
<body>
<table width="800" border="1">
	<tr>
		<th>번호</th>
		<th>이름</th>
		<th>날짜</th>
		<th>부서이름</th>
		<th>매니저이름</th>
		<th>월급</th>
	</tr>
	<%
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection(url,user,pass);
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				cnt++;
				out.print("<tr>");
				out.print("<td>"+rs.getInt("employee_id")+"</td>");
				out.print("<td>"+rs.getString("first_name")+"</td>");
				out.print("<td>"+rs.getString("hire_date")+"</td>");
				out.print("<td>"+rs.getString("department_name")+"</td>");
				out.print("<td>"+rs.getString("manager")+"</td>");
				out.print("<td>"+rs.getInt("salary")+"</td>");
				out.print("</tr>");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	%>	
</table>
	<% 
		out.println("<div>"+cnt+"건 조회"+"</div>");
		cnt=0;
	%>
</body>
</html>