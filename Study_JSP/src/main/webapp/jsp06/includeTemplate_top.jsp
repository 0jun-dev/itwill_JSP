<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	String id = (String)session.getAttribute("sId");
	%>
	<h4>top.jsp</h4>
	<div align="right">
		<h5>
			<% if(id == null) {%>
			<a href="#">로그인</a> |
			<a href="#">회원가입</a>
			<%} else{%>
			<%=id%>님 | <a href="sessionTest3_logout.jsp">로그아웃</a>
			<%}%>				
		</h5>
	</div>
	<div align="center">
		<a href="">상단메뉴1</a>&nbsp;&nbsp;&nbsp;
		<a href="">상단메뉴2</a>&nbsp;&nbsp;&nbsp;
		<a href="">상단메뉴3</a>&nbsp;&nbsp;&nbsp;
		<a href="">상단메뉴4</a>&nbsp;&nbsp;&nbsp;
		<a href="">상단메뉴5</a>&nbsp;&nbsp;&nbsp;
	</div>
</body>
</html>