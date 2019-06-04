<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script
	src="${pageContext.servletContext.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
	$(function() {
		$('#blog-id').change(function() {
			$('#btn-check').show();
			$('#img-check').hide();
		});
		$('#btn-check')
				.click(
						function() {
							var id = $('#blog-id').val();
							console.log(id);
							if (id == '') {
								return;
							}
							/*ajax 통신 */
							/*get방식은 url에 post방식은 data에 데이터를 넣는다.*/
							
									$.ajax({
										url : "${pageContext.servletContext.contextPath }/user/api/checkid?id="
												+ id,
										type : "get",
										dataType : "json",
										data : "",
										success : function(response) {
											if (response.result != "success") {
												console.error(response);
												return;
											}
											if (response.data == true) {
												alert('이미 존재하는 이메일이거나 이메일 형식에 맞지 않습니다.');
												$('#blog-id').focus();
												$('#blog-id').val();
												return;

											}
											
											$('#btn-check').hide();
											$('#img-check').show();
										},
										error : function(xhr, error) {
											console.error("error");
										}
									}); /* ajax라는 객체가 있다 , 설정값이 많음. */

							console.log(id);
						});
	});
</script>
</head>
<body>
	<div class="center-content">
		<h1 class="logo">JBlog</h1>
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<form:form class="join-form" modelAttribute="userVo" id="join-form" name="join-form" method="POST" action="${pageContext.request.contextPath}/user/joinsuccess">
			<!-- <label class="block-label" for="name">이름</label> -->
			<form:label class="block-label" path="name">이름</form:label>
			<form:input path="name" />
			<p style="font-weight:bold; color:#f00; text-align:left; padding:0; margin:0">
			<form:errors path="name" />
			</p>
			<%-- <input id="name"name="name" type="text" value="${vo.name }" required> --%>
			
			<%-- <label class="block-label" for="blog-id">아이디</label>
			<input id="blog-id" name="id" type="text" value="${vo.id }" required>  --%>
			<form:label class="block-label" path="id">아이디</form:label>
			<form:input id="blog-id" path="id"/><input id="btn-check" type="button" value="id 중복체크">
			<img id="img-check" style="display: none;" src="${pageContext.request.contextPath}/assets/images/check.png">
			<p style="font-weight:bold; color:#f00; text-align:left; padding:0; margin:0">
			<form:errors path="id" />
			</p>
			
			
			<form:label class="block-label" path="password">패스워드</form:label>
			<form:password path="password"/>
			<p style="font-weight:bold; color:#f00; text-align:left; padding:0; margin:0">
			<form:errors path="password" />
			</p>
			<%-- <label class="block-label" for="password">패스워드</label>
			<input id="password" name="password" type="password" value="${vo.password }" required/> --%>

			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y" required>
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input type="submit" value="가입하기">

		</form:form>
	</div>
</body>
</html>
