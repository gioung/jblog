package com.cafe24.jblog.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cafe24.jblog.repository.vo.UserVo;
import com.cafe24.jblog.service.UserService;

@Controller
@RequestMapping("/user")
public class UserContorller {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/join")
	public String join(@ModelAttribute UserVo userVo) {
		return "user/join";
	}
	
	@RequestMapping(value="/joinsuccess",method=RequestMethod.POST)
	public String join(@Valid @ModelAttribute UserVo userVo,
			BindingResult bindingResult,
			Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute(bindingResult.getModel());
			return "user/join";
		}
		boolean count=userService.join(userVo);
		
		if(count==false) {
			model.addAttribute("userVo", userVo);
			return "user/join";
		}
		
		return "user/joinsuccess";
		
	}
	
	@RequestMapping(value="/login")
	public String login() {
		return "user/login";
	}
	
	@RequestMapping(value="/auth" , method=RequestMethod.POST)
	public String login(@ModelAttribute UserVo userVo,HttpSession session
			,Model model) {
		//인터셉터에 의해 로그인 실패시 실행 안함.
		
		//로그인이 성공이면
		//main으로 redirect
		return "redirect:/";
		
	}
	
	@RequestMapping(value="/logout")
	public String logout(HttpSession session) {
		//인터셉터에 의해 세션객체 삭제.
		
		return "redirect:/";
	}
	
	
}
