package com.cafe24.config.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import com.cafe24.security.AuthAdminInterceptor;
import com.cafe24.security.AuthLoginInterceptor;
import com.cafe24.security.AuthLogoutInterceptor;


@Configuration
public class SecurityConfig extends WebMvcConfigurerAdapter {

	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		
		
		registry.addInterceptor(authLoginInterceotor()).addPathPatterns("/user/auth");
		
		registry.addInterceptor(authLogoutInterceotor()).addPathPatterns("/user/logout");
		
		registry
        .addInterceptor(authAdminInterceptor())
        .addPathPatterns("*/admin/**");
		
    }
	
	@Bean
	public AuthLoginInterceptor authLoginInterceotor() {
		return new AuthLoginInterceptor();
		
	}
	@Bean
	public AuthLogoutInterceptor authLogoutInterceotor() {
		return new AuthLogoutInterceptor();
		
	}
	
	@Bean
	public AuthAdminInterceptor authAdminInterceptor() {
		return new AuthAdminInterceptor();
	}
	
}
