package com.cafe24.security;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerMapping;
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
		Map<String, String> pathVariables = (HashMap<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		String blogId="";
		if(pathVariables!=null) {
			blogId=pathVariables.get("blogId");
			return false;
		
		}
		//System.out.println(blogId);
		//System.out.println("blogId = "+blogId);
		
		//같지 않으면 false
		if(!(blogId.equals(authUser.getId()))) {
			response.sendRedirect(request.getContextPath());
			return false;}
		
		return true;
	}

	
}
