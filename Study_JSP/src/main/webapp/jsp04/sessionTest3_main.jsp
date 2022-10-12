<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 세션에 저장된 "sId" 라는 속성값을 가져와서 String 타입 변수 id 에 저장
// => 세션에 저장된  
String id = (String)session.getAttribute("sId");
// String id = session.getAttribute("sId").toString();
// out.println(id);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="right">
		<h5>
			<!--  로그인 성공(null이 아님), 실패(null)에 따른 작업 수행(= 다른 링크 표시) -->
			<!--  세션 아이디가 없을 경우 로그인, 회원가입 링크 표시 -->
			<!--  세션 아이디가 있을 경우 세션 아이디, 로그아웃 링크 표시 -->
			<% if(id == null) {%>
			<a href="sessionTest3_loginForm.jsp">로그인</a> |
			<a href="sessionTest3_joinForm.jsp">회원가입</a>
			<%} else{%>
			<%=id%>님 | <a href="sessionTest3_logout.jsp">로그아웃</a>
			<%}%>				
			
			
			
		</h5>
	</div>	
	<h1>메인화면</h1>
	
</body>
</html>