<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<ul>
		<c:if test="${empty authUser }">
		<li><a href="${pageContext.servletContext.contextPath }/user/login">로그인</a></li>
		</c:if>
		<c:if test="${not empty authUser }">
		 <li><b>로그인 : ${authUser.name }</b></li>
		<li><a href="${pageContext.servletContext.contextPath }/user/logout">로그아웃</a></li>
		</c:if>
		<c:if test="${blogVo.id eq authUser.id }">
		<li><a href="${pageContext.servletContext.contextPath }/${authUser.id }/admin/basic">블로그 관리</a></li>
		</c:if>
	</ul>
</body>
</html>