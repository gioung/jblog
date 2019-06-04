<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<% request.setAttribute("newline", "\n"); %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<div id="header">
			<c:import url="/WEB-INF/views/includes/blog_title.jsp" />	
			<c:import url="/WEB-INF/views/includes/blog_header.jsp" />	
		</div>
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<h4>${presentPost.title }</h4>
					<p>
						${fn:replace(presentPost.contents,newline,"<br>") }
					<p>
				</div>
				<hr>
				<ul class="blog-list">
				<!-- 넘버링해야함  -->
				<c:forEach var="postVo" items="${postList }" varStatus="status">
					<li><a href="${pageContext.request.contextPath}/${blogVo.id }/${postVo.category_id }/${status.index}"> ${postVo.title }</a> 
					  <c:if test="${authUser ne null }">
					  <span><a href="${pageContext.servletContext.contextPath }/${authUser.id }/admin/write/delete/${postVo.no }"><b>삭제</b></a></span>
					  <span><a href="${pageContext.servletContext.contextPath }/${authUser.id }/admin/write/update/${postVo.no }"><b>수정</b></a></span>
					  </c:if>
					  <span>${postVo.reg_date }</span></li>
				</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
			<c:choose>
				<c:when test="${blogVo.logo eq 'spring-logo.jpg'} ">
					<img src="${pageContext.request.contextPath}/assets/images/${blogVo.logo }">
				</c:when>
				<c:otherwise>
					<img src="${pageContext.request.contextPath}${blogVo.logo }">
				</c:otherwise>
			</c:choose>
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
			<c:forEach var="categoryVo" items="${categoryList }" varStatus="status">
				<li><a href="${pageContext.request.contextPath}/${categoryVo.blog_id }/${categoryVo.id }">${categoryVo.name}</a></li>
			</c:forEach>
			</ul>
		</div>
		
		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2016
			</p>
		</div>
	</div>
</body>
</html>