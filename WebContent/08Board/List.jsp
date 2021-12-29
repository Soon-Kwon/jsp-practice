<%@page import="model1.board.BoardDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="model1.board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
// DAO를 생성해 DB에 연결
BoardDAO dao=new BoardDAO(application);
// 사용자가 입력한 검색 조건을 Map에 지정
Map<String,Object> param=new HashMap<>();

String searchField=request.getParameter("searchField");
String searchWord=request.getParameter("searchWord");
if(searchWord!=null){
	param.put("searchField",searchField);
	param.put("searchWord",searchWord)	;
}
// VIEW와 CONTROLLER 역할을 동시에 하고 있는 것이다.
int totalCount=dao.selectCount(param);
List<BoardDTO> boardLists=dao.selectList(param);
dao.close();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../Common/Link.jsp"/>
	<h2>목록보기</h2>
	<!-- 검색폼 -->
	<form method="get">
	<table border="1" width="90%">
	<tr>
		<td align="center">
			<select name="searchField">
				<option value="title">제목</option>
				<option value="content">내용</option>
			</select>
			<input type="text" name="searchWord"/>
			<input type="submit" value="검색하기" />
		</td>
	</tr>
	</table>
	</form>
	<!-- 게시물 목록 테이블(표) -->
	<table border="1" width="90%">
		<tr>
			<th width="10%">번호</th>
			<th width="50%">제목</th>
			<th width="15%">작성자</th>
			<th width="10%">조회수</th>
			<th width="15%">작성일</th>
		</tr>
		<!-- 목록의 내용 -->
		<%
		if(boardLists.isEmpty()){
			// 게시물이 하나도 없을 때
		%>
		<tr>
			<td colspan="5" align="center">
				등록된 게시물이 없습니다. 
			</td>
		</tr>
		<%
		} else{   // 게시물이 있을 때
			int virtualNum=0; // 화면상에서의 게시물 번호 
			for(BoardDTO dto:boardLists){
				virtualNum=totalCount--;				
		%>
		<tr align="center">
			<td><%=virtualNum %></td><!-- 게시물 번호 -->
			<td align="left">
				<a href="View.jsp?num=<%=dto.getNum() %>"><%=dto.getTitle() %></a>
			</td>
			<td align="center"><%=dto.getId() %></td>
			<td align="center"><%=dto.getVisitcount() %></td>
			<td align="center"><%=dto.getPostdate() %></td>
		</tr>
		<%
			}
		}
		%>
	</table>
	<!-- 목록 하단의 [글쓰기 버튼] -->
	<table border="1" width="90%">
		<tr align="left">
			<td>
				<button type="button" onclick="location.href='Write.jsp';">
				글쓰기
				</button>
			</td>
		</tr>
	</table>
</body>
</html>