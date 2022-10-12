<%@page import="java.util.Calendar"%>
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
	Calendar c = Calendar.getInstance();
	int hour = c.get(Calendar.HOUR);
	int min = c.get(Calendar.MINUTE);
	int sec = c.get(Calendar.SECOND);
	%>
	<h1>test1.jsp</h1>
	<h3>현재 시각은 <%=hour %>시 <%=min %>분 <%=sec %>초 입니다.</h3>
	<!-- 
	JSP 동작순서
	1. 클라이언트가 웹브라우저에서 http://localhost:8080/Study_JSP/jsp01/text1.jsp
	주소 요청
	2. 요청을 받은 웹서버가 웹 애플리케이션 서버로 JSP 부분 처리(= 자바 코드 해석 및 실행)요청
	3. 웹 애플리케이션 서버에서 처리된 자바코드(JSP)에 대한 응답을 웹서버로 전송
	4. 웹 애플리케이션 서버로부터 웹서버가 응답을 받아 사용자에게 응답할 데이터를 생성 및 전송
	5. 웹서버로 부터 응답을 전달받은 클라이언트(웹브라우저)가 HTML 코드를 실행하여 화면 표시
	
	 -->
  	 <% %> : 스크립틀릿, JSP 코드 : 자바문장을 그대로 표현하는 블럭
	 
  	 <%! %> : 선언식 :  int i = 0; 같은 무엇인가 선언할때 사용하는 선언문
	 
  	 <%= %> : 표현식 : body 에서 jsp코드 사용시 ' = ' 을 붙여 사용. ex) "현재 시각은 " + <%= hour %>
	 
	 <!--
	 <!-- --!> : HTML주석 : 이 주석은 웹브라우저 소스보기를 통해 확인이 가능합니다. 
	 -->
	 
  	 <%-- 	 
  	 <%-- --%> JSP 주석 : 홈페이지 소스보기에서 조차 볼 수 없고, 본 파일을 열어야만 확인
  	 --%>
  	 
  	 
  	 
  	 
	 
	 
	
	
</body>
</html>