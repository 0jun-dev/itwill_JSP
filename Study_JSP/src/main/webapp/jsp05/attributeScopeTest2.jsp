<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>attributeScopeTest2.jsp</h1>
	
	<h3>pageContext 객체 값 : <%=pageContext.getAttribute("pageValue") %></h3>	
	<h3>request 객체 값 : <%=request.getAttribute("requestValue") %> </h3>	
	<h3>session 객체 값 : <%=session.getAttribute("sessionValue") %> </h3>	
	<h3>application 객체 값 : <%=application.getAttribute("applicationValue") %> </h3>
	
</body>
</html>