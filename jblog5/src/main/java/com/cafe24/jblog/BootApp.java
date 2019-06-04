package com.cafe24.jblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@SpringBootApplication = @Configuration + @EnableAutoConfiguration + @ComponentScan
public class BootApp {
	
	public BootApp() {
		System.out.println("BootApp 실행");
	}
	public static void main(String[] args) {
		SpringApplication.run(BootApp.class, args);
	}
}
