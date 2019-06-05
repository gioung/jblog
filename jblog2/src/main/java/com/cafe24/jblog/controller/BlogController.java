package com.cafe24.jblog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.jblog.repository.vo.BlogVo;
import com.cafe24.jblog.repository.vo.CategoryVo;
import com.cafe24.jblog.repository.vo.PostVo;
import com.cafe24.jblog.service.BlogService;
import com.cafe24.jblog.service.CategoryService;
import com.cafe24.jblog.service.FileuploadService;
import com.cafe24.jblog.service.PostService;

@Controller
@RequestMapping("/{blogId:(?!assets|images|search).*}")
public class BlogController {
	
	@Autowired
	BlogService blogService;
	@Autowired
	FileuploadService fileuploadService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	PostService postService;
	
	@RequestMapping(value= {"","/{categoryId:(?!admin).*}","/{categoryId:(?!admin).*}/{postNo}"})
	public String main(@PathVariable("blogId")String blogId,
			@PathVariable("categoryId")Optional<Integer> categoryId,
			@PathVariable("postNo")Optional<Integer> postNo,
			Model model) {
		
		
		if(postNo.isEmpty() || postNo.get()<0) {
			postNo=Optional.ofNullable(0);
		}
		
		
		BlogVo blogVo=blogService.selectBlog(blogId);
		if(blogVo==null) {
			return "redirect:/";
		}
		model.addAttribute("blogVo", blogVo);
		
		//CategoryVo 가져오기
		List<CategoryVo> categoryList=categoryService.getCategoryList(blogId);
		model.addAttribute("categoryList", categoryList);
		
		//CategoryId 검사 없으면 첫번째값
		if(categoryId.isEmpty() || categoryId.get()<0) {
			categoryId=Optional.ofNullable(categoryList.get(0).getId());
		}
		
		//PostVo
		List<PostVo> postList = postService.getPostList(categoryId.get());
		model.addAttribute("postList",postList);
		System.out.println("postList = "+postList);
		
		//PostNo
		if(postList.size()>0) {
		PostVo presentPost = postList.get(postNo.get());
		model.addAttribute("presentPost", presentPost);
		}
		
		return "blog/blog-main";
	}
	
	@RequestMapping("/admin/basic")
	public String admin(@PathVariable("blogId")String blogId,Model model) {
		//인터셉터를 이용하여 현재 user와 관리자의 아이디가 같은지 확인
		//같다면은 컨트롤러 실행
		BlogVo blogVo=blogService.selectBlog(blogId);
		model.addAttribute("blogVo", blogVo);
		
		return "blog/blog-admin-basic";
	}
	
	@RequestMapping(value="/admin/basic/update" , method=RequestMethod.POST)
	public String blogUpdate(
			@RequestParam(value="title" , required=true , defaultValue="")String title,
			@RequestParam(value="logo-file")MultipartFile multipartFile,
			@PathVariable("blogId")String id) {
		
		//file저장하기 
		String url = fileuploadService.restore(multipartFile); 
		System.out.println("이미지 url = "+url);
		//Vo객체 설정
		BlogVo blogVo = new BlogVo();
		blogVo.setId(id);
		blogVo.setTitle(title);
		blogVo.setLogo(url);
		
		//db 업데이트하기
		blogService.update(blogVo);
		
		//redirect : blog/{blogId}
		return "redirect:/"+blogVo.getId();
		
	}
	
	@RequestMapping("/admin/category")
	public String showCategory(@PathVariable("blogId")String blogId,
			@RequestParam(value="result" , required=false)String result,
			Model model) {
		BlogVo blogVo=blogService.selectBlog(blogId);
		List<CategoryVo> categoryList = categoryService.getCategoryList(blogId);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("blogVo", blogVo);
		if("fail".equals(result))
			model.addAttribute("result",result);
		
		return "blog/blog-admin-category";
	}
	
	@RequestMapping(value="/admin/category" , method=RequestMethod.POST)
	public String addCategory(@PathVariable("blogId")String blogId,
			@ModelAttribute CategoryVo categoryVo, 
			Model model) {
		categoryVo.setBlog_id(blogId);
		categoryService.addCategory(categoryVo);
		
		return "redirect:/"+blogId+"/admin/category";
	}
	
	//카테고리 삭제
	@RequestMapping(value={"/admin/category/delete/{categoryId}"})
	public String deleteCategory(@PathVariable("categoryId")int categoryId,
			@PathVariable("blogId")String blogId,
			Model model) {
		System.out.println("deleteCategory 실행");
		List<PostVo> postList = postService.getPostList(categoryId);
		if(postList.size()==0) 
			categoryService.deleteCategory(categoryId);
		else 
			model.addAttribute("result", "fail");
		return "redirect:/"+blogId+"/admin/category";
	}
	
	//포스트 글 작성
	@RequestMapping(value="/admin/write",method=RequestMethod.GET)
	public String writePost(@PathVariable("blogId")String blogId,
			Model model) {
		BlogVo blogVo=blogService.selectBlog(blogId);
		model.addAttribute("blogVo", blogVo);
		List<CategoryVo> categoryList = categoryService.getCategoryList(blogId);
		model.addAttribute("categoryList", categoryList);
		
		return "blog/blog-admin-write";
	}
	
	@RequestMapping(value="/admin/write",method=RequestMethod.POST)
	public String writePost(@ModelAttribute PostVo postVo,
			@RequestParam("category")int category_id,
			@PathVariable("blogId")String blogId) {
		
		postVo.setCategory_id(category_id);
		System.out.println("category_id = "+category_id);
		postService.write(postVo);
		
		
		return "redirect:/"+blogId;
	}
	
	@RequestMapping(value="/admin/write/update/{postNo}",method=RequestMethod.GET)
	public String updatePost(@PathVariable("blogId")String blogId,
			@PathVariable("postNo")int postNo,
			Model model) {
		
		BlogVo blogVo = blogService.selectBlog(blogId);
		model.addAttribute("blogVo", blogVo);
		
		List<CategoryVo> categoryList = categoryService.getCategoryList(blogId);
		model.addAttribute("categoryList", categoryList);
		
		PostVo selected_postVo = postService.getPost(postNo);
		if(selected_postVo==null)
			return "redirect:"+blogId+"/admin/write";
		model.addAttribute("selected_postVo", selected_postVo);
		
		return "blog/blog-admin-write-update";
	}
	
	@RequestMapping(value="/admin/write/update/{postNo}",method=RequestMethod.POST)
	public String updatePost(@ModelAttribute PostVo postVo,
			@PathVariable("blogId")String blogId,
			@PathVariable("postNo")int postNo) {
		
		postVo.setNo(postNo);
		postService.updatePost(postVo);
		
		return "redirect:/"+blogId;
	}
	
	@RequestMapping(value="/admin/write/delete/{postNo}",method=RequestMethod.GET)
	public String deletePost(@PathVariable("blogId")String blogId,
			@PathVariable("postNo")int postNo,
			Model model) {
		PostVo postVo = postService.getPost(postNo);
		postService.deletePost(postNo);
		
		return "redirect:/"+blogId;
	}
	
}
