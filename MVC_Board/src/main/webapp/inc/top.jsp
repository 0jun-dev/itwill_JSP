<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
    <c:choose>
    	<c:when test="${not empty sessionScope.sId}">
			<div id="member_area">
				<a href="./">Home</a>||<a href="MemberLoginForm.me">${sessionScope.sId } 님</a>||<a href="MemberLogout.me">Logout</a>
			</div>
    	</c:when>
    	<c:otherwise>
    		<div id="member_area">
				<a href="./">Home</a>||<a href="MemberLoginForm.me">Login</a>||<a href="MemberJoinForm.me">Join</a>
			</div>
    	</c:otherwise>
    </c:choose>
    
