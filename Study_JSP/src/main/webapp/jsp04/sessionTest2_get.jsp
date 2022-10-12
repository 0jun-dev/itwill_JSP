<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>sessionTest2_get.jsp</h1>
	<h3>세션값 1 : <%=session.getAttribute("sessionValue1")%></h3>
	<h3>세션값 2 : <%=session.getAttribute("sessionValue2")%></h3>
	<input type="button" value="돌아가기" onclick="location.href='sessionTest2.jsp'">
</body>
</html>