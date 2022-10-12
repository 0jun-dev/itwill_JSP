<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>includeForm.jsp</h1>
	<hr>
	<!-- includePro.jsp 페이지를 include 액션태그로 포함시키기 -->
<%-- 	<jsp:include page="includePro.jsp"/> --%>

	<!-- includePro.jsp 페이지 포함 시 파라미터 전달(이름 : "paramValue", 값 : "Parameter Value") -->
	<jsp:include page="includePro.jsp">
		<jsp:param value="parameter Value" name="paramValue"/>
	</jsp:include>
	<hr>
	
</body>
</html>