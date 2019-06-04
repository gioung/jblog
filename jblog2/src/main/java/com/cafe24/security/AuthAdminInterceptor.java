package com.cafe24.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cafe24.jblog.repository.vo.UserVo;

public class AuthAdminInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//현재 세션이 존재하는가?
		//System.out.println("AuthAdminInterceptor 실행");
		HttpSession session = request.getSession();
		if(session==null || session.getAttribute("authUser")==null) {
			response.sendRedirect(request.getContextPath());
			return false;
		}
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		//현재 세션 아이디와 url 아이디값이 같은지 확인한다.
		String uri = request.getRequestURI();
		String[] tokens = uri.split("/");
		String blogId = tokens[2];
		//System.out.println(blogId);
		//System.out.println("blogId = "+blogId);
		
		//같지 않으면 false
		if(!(blogId.equals(authUser.getId()))) {
			response.sendRedirect(request.getContextPath());
			return false;}
		
		return true;
	}

	
}
