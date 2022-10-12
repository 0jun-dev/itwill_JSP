<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//response 객체의 sendRedirect() 메서드를 사용하요 responseTest2.jsp 페이지로 포워딩(= 이동)
response.sendRedirect("responseTest2.jsp");
// => 단, 이 코드가 실행되면 즉시 지정된 페이지로 이동하므로
//    현재 파일 의 주소는 볼 수가 없다!
%>
