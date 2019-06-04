package com.cafe24.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//세션 id가 없으면 메인화면 , 로그인 , 회원가입을 제외한 나머지 접근 불허(일단 블로그는 나중에 생각)
		HttpSession session = request.getSession();
		//세션 id가 없을경우
		if(session==null || session.getAttribute("authUser")==null) {
			response.sendRedirect(request.getContextPath());
			return false;
		}
		//세션 id가 있을경우
		return true;
		
		
	}
	
	
}
