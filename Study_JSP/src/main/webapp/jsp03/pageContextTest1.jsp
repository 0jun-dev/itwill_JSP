<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 내장객체 response 를 사용하여 pageContextTest2.jsp 페이지로 이동(=포워딩)
// response.sendRedirect("pageContextTest2.jsp");
// => pageContextTest2.jsp 페이지로 새로운 요청이 발생하며
//	  지정된 페이지로 포워딩 시 웹브라우저 주소표시줄 주소가 pageContextTest2.jsp 로 변경
// => 이처럼, 새로운 요청에 의해 포워딩 시 주소표시줄의 주소가 변경되는 포워딩 방식을
//	  리다이렉트 방식의 포워딩이라고 한다.
// ----------------------------------------------------------------------------------------
// 또 다른 내장객체인 pageContext 객체의 forward() 메서드를 호출하여 동일한 페이지로 포워딩
pageContext.forward("pageContextTest2.jsp");
// => pageContextTest1.jsp 에서 pageContextTest2.jsp 페이지로 포워딩 요청이 발생하여
//    지정된
//    기존 주소인
//    (포워딩 후 주소 : )
//    (단, )
// => 이처럼,
//    포워딩 방식을
// ----------------------------------------------------------------------------------------
// 위의
// 지정한
// 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!--  위쪽의 포워딩 작업 코드로 인해 실행되지 못하는 코드들.... -->
	<h1>pageContextTest1.jsp</h1>
		<script type="text/javascript">
		alert("확인");
		</script>
</body>
</html>