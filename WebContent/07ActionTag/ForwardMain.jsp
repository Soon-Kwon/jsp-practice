<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
pageContext.setAttribute("pAttr", "김유신"); 
request.setAttribute("rAttr", "계백"); 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>액션 태그 - forward</title>
</head>
<body>
    <h2>액션 태그를 이용한 포워딩</h2> 
    <jsp:forward page="/07ActionTag/ForwardSub.jsp" /> 
    <!--  
    if (true) {
        _jspx_page_context.forward("/07ActionTag/ForwardSub.jsp");
        return;
      } 
      _jspx_page_context에 request response 등의 정보가 다 담겨있다. 
      추측인데 java 파일로 변환된 코드의 마지막에  
      finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
    코드가 있는데, 이는 return 하기 전에 finally 문을 거치면서 page 영역에 대한 
    정보를 잃게 되는게 아닌가 싶다. 
      -->
</body>
</html>