<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>reqestPro4.jsp - 로그인 처리</h1>
	<%
	// 폼파라미터(아이디, 패스워드) 가져와서 변수에 저장
	String id = request.getParameter("id");
	String passwd = request.getParameter("passwd");
	%>
	<h3>아이디 : <%=id %> </h3>
	<h3>패스워드 : <%=passwd %>  </h3>
	
	<%
// 	if(id.equals("admin") && passwd.equals("1234")) {
// 		out.println("로그인 성공!");
// 	} else {
// 		out.println("로그인 실패!");
// 	}
	%>
	
	<%-- 로그인 성공시 requestPro4_responsResult1.jsp 로 이동 --%>
	<%-- 로그인 실패시 requestPro4_responsResult2.jsp 로 이동 --%>
	<%
	if(id.equals("admin") && passwd.equals("1234")) {
		%>
		<script>
// 		location.href ="requestPro4_responseResult1.jsp";
		</script>
		<% 
	} else {
		%>
		<script>
// 		location.href ="requestPro4_responseResult2.jsp";
		</script>
		<% 
	}
	%>
	
	<%
	if(id.equals("admin") && passwd.equals("1234")) {
		response.sendRedirect("requestPro4_responseResult1.jsp");
	} else {
		response.sendRedirect("requestPro4_responseResult2.jsp");
	}
	%>
	
</body>
</html>