package com.cafe24.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.repository.vo.BlogVo;
import com.cafe24.jblog.repository.vo.UserVo;

@Repository
public class BlogDao {

	@Autowired
	private SqlSession sqlSession;
	
	public boolean insert(String id) {
		int count=sqlSession.insert("blog.insert",id);
		return (1==count);
	}

	public BlogVo selectBlog(String blogId) {
		return sqlSession.selectOne("blog.select", blogId);
	}

	public boolean update(BlogVo blogVo) {
		int count = sqlSession.update("blog.update",blogVo);
		return (count==1);
	}
}
