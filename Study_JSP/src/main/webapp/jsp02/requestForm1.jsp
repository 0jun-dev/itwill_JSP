<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>requestForm1.jsp - request 객체</h1>
	<!-- 
	[ form 태그 ]
	1) action 속성 : submit 동작 시 폼 태그 내의 파라미터(= 폼 파라미터)를 모두
	                 request 객체에 저장한 뒤 action 속성에 지정된 페이지(또는 파일)로 이동
	                 ex) submit 버튼 클릭 시 requestPro1.jsp 페이지로 이동
	                 => 이 때, 이동 시 request 객체도 함께 전달됨
	2) method 속성 
	   - GET 방식 : method="get" 또는 하이퍼링크(a 태그) 또는 URL 직접 입력등을 통해 이동
	   - POST 방식 : method="post" 를 지정할 경우
	-->
<!-- 	<form action="requestPro1.jsp" method="get"></form> -->
	<form action="requestPro1.jsp" method="post">
		<table border="1">
			<tr>
				<th>이름</th>
				<td><input type="text" name="name" required="required"></td>
			</tr>
			<tr>
				<th>나이</th>
				<td><input type="text" name="age" ></td>
			</tr>
			<tr>
				<th>성별</th>
				<td>
					<input type="radio" name="gender" value="male">남
					<input type="radio" name="gender" value="female">여
				</td>
			</tr>
			<tr>
				<th>취미</th>
				<td><input type="checkbox" name="hobby" value="독서">독서
						<input type="checkbox" name="hobby" value="게임">게임
						<input type="checkbox" name="hobby" value="등산">등산
				</td>
			</tr>
			<tr>
				<!-- submit 버튼 생성 => 클릭시 페이지 이동 -->
				<td colspan="2" align="center"><input type="submit" value="전송" ></td>
			</tr>
		</table>
	</form>
</body>
</html>