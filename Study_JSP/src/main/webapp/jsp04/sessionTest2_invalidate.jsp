<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>sessionTest2_invalidate.jsp</h1>
	<%
	// 세션을 초기화 시키는 방법
	// => session.invalidate();	
	// => 특정 속성 지정이 아닌 세션 객체 자체를 제거
	session.invalidate();
	%>
	
	<h1>세션값 초기화 완료!</h1>
	<input type="button" value="세션값 확인" onclick="location.href='sessionTest2_get.jsp'">
</body>
</html>