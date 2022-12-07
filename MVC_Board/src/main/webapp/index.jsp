<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<!--  외부 css 가져오기 -->
<link href="css/default.css" rel="stylesheet" type="text/css">
<body>
	<header>
		<!--  로그인, 조인 링크 표시 영역 -->
		<jsp:include page="inc/top.jsp" ></jsp:include>
	</header>
	<article>
		<!--  본문 표시 영역 -->
		<h1>MVC 게시판</h1>
		<h3><a href="BoardWriteForm.bo">글쓰기</a></h3>
		<h3><a href="BoardList.bo">글목록</a></h3>
	</article>
</body>
</html>