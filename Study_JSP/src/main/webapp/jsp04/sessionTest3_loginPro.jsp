<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>로그인 처리</h1>
	<%
	// 폼파라미터(아이디, 패스워드) 가져와서 변수에 저장
	String id = request.getParameter("id");
	String passwd = request.getParameter("passwd");
	%>
	<h3>아이디 : <%=id %> </h3>
	<h3>패스워드 : <%=passwd %>  </h3>
	
	<%
	/*
	[ 자바 코드를 사용하여 아이디와 패스워드 판별 ]
	아이디가 "admin" 이고, 패스워드가 "1234" 일 경우
	자바스크립트를 사용하여 "로그인 성공!" 출력하고
	아니면, "로그인 실패!" 출력 후 이전페이지로 돌아가기
	----------------------------------------------------
	주의! 자바 코드 내에서 문자열 데이터 비교 시
	동등비교연산자(==) 대신 String 객체의 equals() 메서드 사용해야한다!
	
	< 기본 문법 > 
	if(문자열데이터.equals(비교할문자열)) {}
	=> 문자열이 동일한지 판별하여 boolean 타입 결과 리턴(true : 같음, false : 다름) 
	*/
	if(id.equals("admin") && passwd.equals("1234")) {
		// session 객체에 로그인 성공한 아이디를 "sId" 라는 속성명으로 저장하기
		session.setAttribute("sId", id);
		// sessionTest3_main.jsp 페이지로 포워딩(리다이렉트 방식)
		response.sendRedirect("sessionTest3_main.jsp");
	} else {
		%>
		<script type="text/javascript">
		alert("로그인 실패!");
		history.back();
		</script>
		<% 
	}
			
	%>
	
	
</body>
</html>