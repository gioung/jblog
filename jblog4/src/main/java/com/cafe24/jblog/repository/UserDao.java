package com.cafe24.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.repository.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	
	public boolean insert(UserVo vo) {
		int count=sqlSession.insert("user.insert",vo);
		return (1==count);
	}


	public UserVo select(String id) {
		
		return sqlSession.selectOne("user.selectId", id);
	}


	public UserVo select(UserVo userVo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("user.selectUser",userVo);
	}
}
