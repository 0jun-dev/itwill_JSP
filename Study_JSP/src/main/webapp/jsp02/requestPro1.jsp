<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>requestPro1.jsp</title>
	<h1>requestPro1.jsp</h1>
	<h1>request 객체 처리 페이지</h1>
	<%
	/*
	requestForm1.jsp 페이지에서 submit 버튼 클릭 시
	form 태그 내의 데이터(= 폼 파라미터)가 request 객체에 모두 저장되고
	action 속성에 지정된 페이지로 이동(= 페이지를 요청)하면서 데이터 전달함
	=> 요청 관련 모든 정보는 request 객체가 관리(= JSP 가 자동으로 생성하는 객체 = 내장 객체)
	   따라서, request.메서드명() 형태로 request 객체의 메서드 호출하여 객체 다루기 가능
	=> 요청받은 request 객체에 저장된 폼 파라미터 데이터를 가져오는 방법
	   1) request.getParameter("파라미터명"); // 단일 파라미터 데이터 가져오기 = String 리턴
	   2) request.getParameterValues("파라미터명"); // 복수 항목 파라미터 가져오기 
	      = String[] 리턴(주로, checkbox 처럼 하나의 이름으로 복수개의 파라미터 전달할 경우 사용)
	=> 주의! 지정된 파라미터가 존재하지 않을 경우(지정한 이름이 없을 경우) null 값이 리턴되고,
	   파라미터는 있으나 데이터가 없는 경우에는 널스트링("") 값이 리턴됨
	*/
	request.setCharacterEncoding("UTF-8");
	// => 단, 반드시 파라미터값을 가져오는 코드(겟파라미터) 보다 먼저 수행되어야 함
	//-------------------------------------------------------------------------------
	// 1. 파라미터 중 파라미터명(name 속성 값)이 "name"인 값을 가져와서 String 타입 strName에 저장
	String strName = request.getParameter("name");
	// 스크립틀릿 내에서 웹브라우저의 데이터를 출력할 경우 out.print 또는 out.println 사용
// 	out.println("이름 : " + strName + "<br>");
	
	// 2. 파라미터 중 파라미터명이 age인 값을 가져와서 String 타입 strAge 저장 후 출력
	String strAge = request.getParameter("age");
// 	out.println("나이 : " + strAge + "<br>");
	
	// 3. 파라미터 중 파라미터명이 gender인 값을 가져와서 String 타입 strGender 저장 후 출력
	String strGender = request.getParameter("gender");
// 	out.println("성별 : " + strGender + "<br>");
	
	// 4. 파라미터 중 파라미터명이 hobby인 값을 가져와서 String 타입 strHobby 저장 후 출력
// 	String strHobby = request.getParameter.("hobby");
// 	out.println("성별 : " + strHobby + "<br>");
	// 주의! 복수개의 데이터가 하나이 파라미터명으로 전달되는 경우 (체크박스)
	// getParameter() 메서드를 사용하면 하나의 데이터 만 가져올 수 있음
	// 따라서, getParameter() 메서드 대신 getParameterValues() 메서드를 호출하여
	// 복수개의 동일한 이름의 파라미터를 String[] 타입으로 리턴받아 처리해야함
	String[] strHobbies= request.getParameterValues("hobby");
// 	out.println("취미 : " + strHobbies + "<br>");
	// => 주의! 체크박스 항목을 하나도 체크하지 않을 겨우 하비 파라미터가 없으므로
	//    strHobbies 배열(변수)에는 null 값이 저장된다!
	%>

		<table border="1">
			<tr>
				<th>이름</th>
				<td><%= strName %></td>
			</tr>
			<tr>
				<th>나이</th>
				<td><%= strAge %></td>
			</tr>
			<tr>
				<th>성별</th>
				<td>
				<%= strGender %>
				</td>
			</tr>
			<tr>
				<th>취미</th>
				<td>
<%-- 					<%= strHobbies[0] %> <%= strHobbies[1] %> <%= strHobbies[2] %> --%>
					<%
// 					for(int i= 0; i < strHobbies.length; i++) {
// 						out.println(strHobbies[i] = " ");
// 					}
					%>
<%-- 					<%if(strHobbies == null) { %> 서버가 실행 --%>
						<script type="text/javascript"> <%-- html 페이지가 실행 --%>
// 						alert("취미선택 필수!")
// 						history.back();
						</script>
<%-- 					<%} %> --%>

					<%
					// 만약 strHobbies 가 null 이면 "없음"으로 출력, 하나라도 존재할경우 반복문 출력
					if(strHobbies == null) {
						out.println("없음");
					} else{
					for(int i= 0; i < strHobbies.length; i++) {
						out.println(strHobbies[i] = " ");
					  }
					}
					%>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="button" value="뒤로가기" onclick="history.back()" ></td>
			</tr>
		</table>
</head>
<body>

</body>
</html>