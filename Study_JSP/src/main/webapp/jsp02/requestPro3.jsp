<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>requestForm3.jsp - 학생 정보 입력</h1>
		<form action="requestPro3.jsp" method="post">
		<table>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" required="required"></td>
			</tr>
			<tr>
				<td>나이</td>
				<td><input type="text" name="age" required="required"></td>
			</tr>
			<tr>
				<td>성별</td>
				<td>
					<input type="radio" name="gender" value="male" required="required">남
					<input type="radio" name="gender" value="female" required="required">여
				</td>
			</tr>
			<tr>
				<td>학년</td>
				<td>
					<select name="grade">
						<option value="1">1학년</option>
						<option value="2">2학년</option>
						<option value="3">3학년</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>취미</td>
				<td>
					<input type="checkbox" name="hobby" value="독서">독서
					<input type="checkbox" name="hobby" value="운동">운동
					<input type="checkbox" name="hobby" value="게임">게임
				</td>
			</tr>
			<tr>
				<!-- submit 버튼("전송") 생성 => 클릭 시 requestPro3.jsp 페이지로 이동함 -->
				<td colspan="2"><input type="submit" value="전송"></td>
			</tr>
		</table>
	</form>
</body>
</html>