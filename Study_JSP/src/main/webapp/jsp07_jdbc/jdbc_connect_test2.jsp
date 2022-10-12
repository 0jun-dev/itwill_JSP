<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>jdbc_connect_test2.jsp</h1>
	<%
	// JDBC 작업 4단계
	// 1단계. JDBC 드라이버 로드
	Class.forName("com.mysql.cj.jdbc.Driver");
	%>
	<hr>
	<%
	// 2단계. DB 연결
	// => DriverManager 클래스의 static 메서드 getConnection() 메서드를 호출하여 DB 연결 수행
	//    파라미터 : DB 접속에 필요한 URL, DB 계정명, DB 패스워드
	DriverManager.getConnection("jdbc:mysql://localhost:3306/study_jsp5", "root", "1234");
	
	%>
	<h1>DB 연결 성공!</h1>
	<!-- 2단계까지는 DB 제품별로 정보가 달라지는 부분 -->
	<!-- 3단계부터는 실제 DB 내의 SQL 구문을 실행하므로 공통 작업에 해당함 -->
	
</body>
</html>