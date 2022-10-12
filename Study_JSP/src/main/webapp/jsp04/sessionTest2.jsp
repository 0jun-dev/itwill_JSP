<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

</script>
</head>
<body>
	<h1>sessionTest2.jsp</h1>
	<!-- 
	하이퍼링크를 사용하여 "세션값 생성"링크 클릭시 "sessionTest2_set.jsp" 페이지로 포워딩,
	"세션값 삭제" 링크 클릭시 "sessionTest2_remove.jsp" 페이지로 포워딩
	 -->
	 <a href="sessionTest2_set.jsp">세션값 생성</a>
	 <a href="sessionTest2_remove.jsp">세션값 삭제</a>
	 
	 <!-- 버튼타입과 자바스크립트를 이용하여 같은 동작기능 생성 -->
	 <input type="button" value="세션값 생성" onclick="location.href='sessionTest2_set.jsp'">
	 <input type="button" value="세션값 삭제" onclick="location.href='sessionTest2_remove.jsp'">
	 
</body>
</html>