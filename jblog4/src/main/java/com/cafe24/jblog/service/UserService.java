package com.cafe24.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.jblog.repository.BlogDao;
import com.cafe24.jblog.repository.CategoryDao;
import com.cafe24.jblog.repository.UserDao;
import com.cafe24.jblog.repository.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private BlogDao blogDao;
	@Autowired
	private CategoryDao categoryDao;
	
	public boolean join(UserVo vo) {
		userDao.insert(vo);
		blogDao.insert(vo.getId());
		return categoryDao.insert(vo.getId()); 
	}

	public Boolean existId(String id) {
		UserVo userVo=userDao.select(id);
		return userVo!=null;
	}

	public UserVo getUser(UserVo userVo) {
		
		return userDao.select(userVo);
	}
}
