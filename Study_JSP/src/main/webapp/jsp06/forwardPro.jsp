<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>fowardPro.jsp</h1>
	<%
	// 이전 페이지에서 POST 방식으로 전달받은 파라미터 중 한글 데이터가 포함될 경우 깨지므로
	// request.setCharacterEncoding("UTF-8"); 코드를 사용하여 한글 처리 필요
	// 또한, jsp:forward 액션태그의 jsp:param 태그를 통해 파라미터 전달 시에도
	// 전달할 데이터를 지정하는 시점에 한글 처리가 되어있어야한다!
	// => 데이터를 처리하는 forwardPro2.jsp 가 아닌 데이터를 생성하는 forwardPro.jsp 페이지에서
	//    한글 처리를 수행해야한다!
	request.setCharacterEncoding("UTF-8");
	
	// 폼 파라미터로 전달받은 데이터(아이디, 패스워드) 가져와서 변수에 저장 후 출력
	String id = request.getParameter("id");
	String passwd = request.getParameter("passwd");
	
	// input type="hidden" 속성으로 전달받은 파라미터("jumin")도 동일한 방식으로 가져올 수 있음
	String jumin = request.getParameter("jumin");
	
	// forword 액션태그에 포함시켜 전달할 데이터를 변수에 저장
	int age = 28;
	%>
	<h3>아이디 : <%=id %></h3>
	<h3>패스워드 : <%=passwd %></h3>
	<h3>주민번호 : <%=jumin %></h3>
	<!-- ====================================================== -->
	<!-- forword 액션 태그를 사용하여 "forwordPro2.jsp 포워딩 작업 수행 -->
<%-- 	<jsp:forward page="forwardPro2.jsp"></jsp:forward> --%>
	<!-- forward 액션태그로 포워딩 시 데이터를 추가하여 포워딩 작업 수행도 가능 -->
	<!-- jsp:forward 태그 사이에 jsp:param 태그를 사용(name 속성에 이름, value 속성에 값 지정) -->
	<!-- 일반적인 데이터는 value 속성의 문자열 형태로 전달하며 -->
	<!-- 자바 변수 데이터는 value 속성에 표현식을 결합하여 전달 -->
	<!-- 또한, 반드시 jsp:forward 태그의 끝 태그를 별도로 작성해야함 -->
	<jsp:forward page="forwardPro2.jsp">
		<jsp:param name="paramValue1" value="forward 액션태그의 param 태그 데이터"/>
		<jsp:param name="paramValue2" value="<%=age %>"/>
	</jsp:forward>
</body>
</html>