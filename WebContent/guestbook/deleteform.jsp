<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>비밀번호</title>
</head>
<body>
	<form method="post" action="/guestbook2/deleteGuestbook">
	<input type='hidden' name="no" value="${param.no }">
	<table>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="password"></td>
			<td><input type="submit" value=""></td>
			<td><a href="MustHaveJSP/guestbook2">목록으로 돌아가기</a></td>
		</tr>
	</table>
	</form>
</body>
</html>