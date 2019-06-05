package com.cafe24.jblog.config;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import com.cafe24.security.AuthAdminInterceptor;
import com.cafe24.security.AuthLoginInterceptor;
import com.cafe24.security.AuthLogoutInterceptor;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
//jblog 
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{

	@Override
	// 가상 url mapping 해주는 놈
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//System.out.println("addResourceHandlers 실행");
		registry.addResourceHandler("/images/**").addResourceLocations("file:/jblog-uploads/");

	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	@Override		 
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
			converters.add(mappingJackson2HttpMessageConverter());
			converters.add(stringHttpMessageConverter());
	}
	
	//Message Converter
	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder()
				.indentOutput(true)
				.dateFormat(new SimpleDateFormat("yyyy-MM-dd"))
				.modulesToInstall(new ParameterNamesModule());
		
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(builder.build());
		converter.setSupportedMediaTypes(Arrays.asList(new MediaType("application","json",Charset.forName("utf-8"))));
		
		return converter;
	}
	
	@Bean
	public StringHttpMessageConverter stringHttpMessageConverter() {
		StringHttpMessageConverter converter = new StringHttpMessageConverter();
		converter.setSupportedMediaTypes(Arrays.asList(new MediaType("text","html",Charset.forName("utf-8"))));
		
		return converter;
	}
	
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
