package com.cafe24.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.jblog.repository.BlogDao;
import com.cafe24.jblog.repository.vo.BlogVo;

@Service
public class BlogService {
	
	@Autowired
	private BlogDao blogDao;

	public BlogVo selectBlog(String blogId) {
		return blogDao.selectBlog(blogId);
		
	}

	public boolean update(BlogVo blogVo) {
		return blogDao.update(blogVo);
		
	}
	
	
}
