<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>jdbc_connect_test3.jsp</h1>
	<%
	// JDBC 작업 4단계.
	// 0단계. DB 연결에 필요한 정보 문자열 4가지를 변수에 별도로 저장
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/study_jsp5";
	String user = "root";
	String password = "1234";
	
	// 1단계. JDBC 드라이버 로드
	Class.forName("com.mysql.cj.jdbc.Driver");
	
	// 2단계. DB 연결
	// => 연결 성공 시 java.sql.Connection 타입 객체 리턴됨
	Connection con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/study_jsp5", "root", "1234");
	
	%>
	<h1>DB 연결 성공!</h1>
	<h3>Connection 객체 정보 : <%=con %></h3>
	<hr>
	
	<%
	// 3단계부터는 DB 제품과 관계없이 공통적으로 수행하는 코드(SQL 구문이 달라질 수는 있음)
	// 3단계. SQL 구문 작성 및 전달
	// 1) SQL 구문 작성
// 	String sql = "CREATE TABLE student (idx INT, name VARCHAR(10))";
	
	// student 테이블 생성
// 	String sql = "CREATE TABLE student (idx INT, name VARCHAR(10))";
	
	// student 테이블 삭제
// 	String sql = "DROP TABLE student";
	
	// student 테이블의 레코드 추가 (번호, 이름)
	String sql = "INSERT  INTO student VALUES(1, '홍길동')";
	
	// 2) Connection 객체(변수 con)의 prepareStatement() 메서드를 호출하여 SQL 구문 전달
	//    => 파라미터 : SQL 구문(String 타입 변수 sql)  리턴타입 : java.sql.PreparedStatement
	PreparedStatement pstmt = con.prepareStatement(sql);
	%>
	<h3>sql 구문 전달 완료!</h3>
	<hr>
	<%
	// 4단계. SQL 구문 실행 및 결과 처리
	// PreparedStatement 객체의 executeXXX() 메서드를 호출하여 전달한 SQL 구문 실행
	// => 주의! Statement 객체의 executeUpdate(String sql) 호출이 아님! (파라미터 없음)
	int count = pstmt.executeUpdate();
	%>
	<h3>sql 구문 실행 완료! = <%=count %></h3>
	
	
</body>
</html>