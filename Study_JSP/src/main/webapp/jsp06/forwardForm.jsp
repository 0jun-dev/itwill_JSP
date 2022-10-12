<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>forwordForm.jsp - 포워딩 연습</h1>
	<form action="forwardPro.jsp" method="get">
		<!-- 
		폼에서 입력받는 데이터 외의 별도의 데이터를 함께 전달해야 하는 경우
		<input type="hidden" name="파라미터명" value="파라미터값"> 태그 사용
		-->
		<input type="hidden" name="jumin" value="950731-111111">
		
		<h3>아이디 : <input type="text" name="id"></h3>
		<h3>패스워드 : <input type="password" name="passwd"></h3>
		<input type="submit" value="전송">
	</form>
</body>
</html>