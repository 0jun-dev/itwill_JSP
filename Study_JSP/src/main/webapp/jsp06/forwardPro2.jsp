<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>forwardPro2.jsp</h1>
	<%
	// forwardForm.jsp 페이지에서 폼 파라미터로 전달받은 데이터
	String id = request.getParameter("id");
	String passwd = request.getParameter("passwd");
	
	// forwardForm.jsp 페이지에서 input type="hidden" 속성으로 전달받은 데이터
	String jumin = request.getParameter("jumin");
	
	// forwardPro.jsp 페이지에서 jsp:param 태그로 전달받은 데이터 가져오기 = 방식 동일
	String param1 = request.getParameter("paramValue1");
	String param2 = request.getParameter("paramValue2");
	%>
	<h3>아이디 : <%=id %></h3>
	<h3>패스워드 : <%=passwd %></h3>
	<h3>주민번호 : <%=jumin %></h3>
	
	<!-- forward 액션태그 내의 param 태그로 전달받은 파라미터 출력 -->
	<h3>paramValue1 : <%=param1 %></h3>
	<h3>paramValue2 : <%=param2 %></h3>
	
</body>
</html>