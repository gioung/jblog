package com.cafe24.jblog.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.cafe24.config.web.FileuploadConfig;
import com.cafe24.config.web.MVCConfig;
import com.cafe24.config.web.SecurityConfig;
//jblog 
@Configuration
@EnableWebMvc
@ComponentScan({"com.cafe24.jblog.controller","com.cafe24.jblog.controller.api"})
@Import({MVCConfig.class,SecurityConfig.class,FileuploadConfig.class})
public class WebConfig extends WebMvcConfigurerAdapter{

	 @Override
	   public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
	       configurer.enable();
	   }
}
