package com.cafe24.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.repository.vo.PostVo;

@Repository
public class PostDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public boolean write(PostVo postVo) {
		int count=sqlSession.insert("post.write", postVo);
		return (1==count);
	}
	
	public List<PostVo> getPostList(int category_id){
		return sqlSession.selectList("post.getPostList",category_id);
	}
	
	public PostVo getPost(int post_No) {
		return sqlSession.selectOne("post.getPost",post_No);
	}
	
	public boolean updatePost(PostVo postVo) {
		int count = sqlSession.update("post.updatePost", postVo);
		return (1==count);
	}
	
	public boolean deletePost(int post_No) {
		int count = sqlSession.delete("post.deletePost", post_No);
		return (1==count);
	}
	              
}
