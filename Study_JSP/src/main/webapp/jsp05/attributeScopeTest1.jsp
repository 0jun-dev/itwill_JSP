<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>영역 객체의 속성 및 범위</h1>
	<%
	// 4대 영역 : page, request, session, application
	// 4대 영역 객체 : pageContext, request, session, application
	// pageContext 객체 : 현재 페이지에서만 유효(페이지 이동 시 객체 제거됨)
	// request 객체 : 요청에 대한 응답까지 유효(새로운 요청 발생 시 객체 제거됨)
	// session 객체 : 세션 유지 조건까지 유효(세션 제거 조건 발생 시 객체 제거됨)
	// application 객체 : 서버 유지 시까지 유효(서버 중지 시 객체 제거됨)
	// 각 영역 객체에 속성값 저장 => setAttribute(String name, Object value)
	pageContext.setAttribute("pageValue", "pageContext value");
	request.setAttribute("requestValue", "request value");
	session.setAttribute("sessionValue", "session value");
	application.setAttribute("applicationValue", "application value");
	%>
	
	<!-- 영역 객체에 속성값을 저장한 페이지에서 저장된 값 확인하기 -->
	<!-- getAttribute(String name) -->
	<h3>pageContext 객체 값 : <%=pageContext.getAttribute("pageValue")%></h3>s
	<h3>request 객체 값 : <%=request.getAttribute("requestValue")%></h3>
	<h3>session 객체 값 : <%=session.getAttribute("sessionValue")%></h3>
	<h3>application 객체 값 : <%=application.getAttribute("applicationValue")%></h3>
	
	<%
	// attributeScopeTest2.jsp 페이지로 이동(= 포워딩)하는 방법
	// 1. response 객체의 sendRedirect() 메서드를 호출하여 이동 : Redirect 방식 포워딩
// 	response.sendRedirect("attributeScopeTest2.jsp");
	// => pageContext, request 객체 : 데이터 없음(null)
	// => session, application 객체 : 데이터 있음
	// => 기존 요청과 다른 새로운 요청이 발생하여 기존 request 객체가 제거되고
	//    새로운 request 객체가 생성되므로 기존에 request 객체에 저장된 정보가 제거됨
	//    따라서, null 값이 출력됨
	// => 또한, 새로운 요청 주소로 웹브라우저 주소 표시줄의 주소(URL) 변경됨
	
	// 2. pageContext 객체의 forward() 메서드를 호출하여 이동 : Dispatch 방식 포워딩
	pageContext.forward("attributeScopeTest2.jsp");
	// => pageContext 객체 : 데이터 없음(null)
	// => request, session, application 객체 : 데이터 있음
	// => 기존 요청이 유지된 채로 페이지만 이동하므로 기존 request 객체가 유지됨
	//    따라서, request 객체에 저장되어 있던 속성에 여전히 접근 가능함
	// => 또한, 주소 표시줄의 주소(URL) 가 기존 주소 그대로 유지됨(변경되지 않음)
	%>
</body>
</html>