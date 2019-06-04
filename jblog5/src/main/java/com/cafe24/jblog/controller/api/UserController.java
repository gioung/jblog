package com.cafe24.jblog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.jblog.dto.JSONResult;
import com.cafe24.jblog.service.UserService;



@Controller
@RequestMapping("/user/api")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping("/checkid")
	public JSONResult checkId(@RequestParam(value="id",required=true,defaultValue="")String id) {
		System.out.println("id check 실행 , id="+id);
		if(!id.contains("@")) {
			return JSONResult.success(true);
		}
		
		Boolean exist = userService.existId(id);
		JSONResult	result = JSONResult.success(exist);
		
		
/*		Map<String,Object> map = new HashMap<>();
		map.put("result", "success");
		map.put("data", exist);*/
		//map.put("message","......")
		
		//return map;
		return result;
	}
}
