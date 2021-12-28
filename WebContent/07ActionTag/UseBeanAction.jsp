<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head><title>액션 태그 - UseBean</title></head>
<body>
    <h3>액션 태그로 폼값 한 번에 받기</h3>
    <jsp:useBean id="person" class="common.Person" />  
    
	<!--자바빈의 프라퍼티와 HTML의 파라미터 명이 다르다면 

<jsp:setProperty name="person" property="name" param="blablaName"/>
다음과 같은 액션태그 맵핑을 반복해줘야한다. 

따라서 만약 자바빈의 프라퍼티 와 HTML 파라미터 명이 같다면 다음과 같이 줄여서 사용 할 수 있다.
<jsp:setProperty property="*" name="person" />

결국 원리는 request에서 getParameter해서 얻은 값들을 setProperty의 property와 매핑하는 것  
 -->    
    <jsp:setProperty property="*" name="person" />  
    <ul>
        <li>이름 : <jsp:getProperty name="person" property="name" /></li>  
        <li>나이 : <jsp:getProperty name="person" property="age" /></li> 
    </ul>
</body>
</html>