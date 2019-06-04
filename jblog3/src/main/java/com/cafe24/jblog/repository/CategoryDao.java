package com.cafe24.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.repository.vo.CategoryVo;

@Repository
public class CategoryDao {

	@Autowired
	private SqlSession sqlSession;
	
	public boolean insert(String id) {
		int count=sqlSession.insert("category.insert",id);
		return (1==count);
	}
	
	public boolean addCategory(CategoryVo categoryVo) {
		int count = sqlSession.insert("category.addCategory",categoryVo );
		return (count==1);
	}
	
	public List<CategoryVo> getCategoryList(String id){
		return sqlSession.selectList("category.getCategoryList", id);
	}
	
	
	public void updatePostNum(int category_id) {
	
		sqlSession.update("category.updatePostNum",category_id);
	}
	
	public void updateDelPostNum(int category_id) {
		sqlSession.delete("category.deletePostNum", category_id);
	}
	
	public boolean deleteCategory(int categoryId) {
		int count = sqlSession.delete("category.deleteCategory", categoryId);
		return (count==1);
	}

	
	
	
	
	
	
}
