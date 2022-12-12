<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<script>
	function logout() {
		let isLogout = confirm("로그아웃 하시겠습니까?");
		
		if(isLogout) {
			location.href = "MemberLogout.me";
		}
	}
</script>
    <c:choose>
    	<c:when test="${empty sessionScope.sId}">
    		<div id="member_area">
				<a href="./">Home</a> | <a href="MemberLoginForm.me">Login</a> | <a href="MemberJoinForm.me">Join</a>
			</div>
    	</c:when>
    	<c:otherwise>
			<div id="member_area">
				<a href="./">Home</a> | <a href="MemberLoginForm.me">${sessionScope.sId } 님</a> | <a href="javascript:logout()">Logout</a>
			
			<!-- 만약, 로그인된 세션 아이디가 admin 일 경우 관리자페이지 링크 추가(MemberList.me) -->
			<c:if test='${sessionScope.sId eq "admin"}'> 
					 | <a href="MemberList.me">관리자페이지</a>
				</div>
			</c:if>
    	</c:otherwise>
    </c:choose>
    
