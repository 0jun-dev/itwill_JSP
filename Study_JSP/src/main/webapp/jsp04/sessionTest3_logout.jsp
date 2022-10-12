<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 로그아웃 처리
// => 세션초기화를 통해 저장된 세션 아이디 를 포함한 세션 전체제거(세션 아이디만 제거도 가능)
session.invalidate();

// 메인페이지로 포워딩
response.sendRedirect("sessionTest3_main.jsp");
%>
