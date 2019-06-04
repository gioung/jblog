package com.cafe24.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.jblog.repository.CategoryDao;
import com.cafe24.jblog.repository.PostDao;
import com.cafe24.jblog.repository.vo.PostVo;

@Service
public class PostService {
	
	@Autowired
	private PostDao postDao;
	
	public boolean write(PostVo postVo) {
		return postDao.write(postVo);
	}
	
	public List<PostVo> getPostList(int category_id){
		return postDao.getPostList(category_id);
	}
	
	public PostVo getPost(int post_No) {
		return postDao.getPost(post_No);
	}
	
	public boolean updatePost(PostVo postVo) {
		return postDao.updatePost(postVo);
	}

	public boolean deletePost(int post_No) {
		return postDao.deletePost(post_No);
		
	}
}
