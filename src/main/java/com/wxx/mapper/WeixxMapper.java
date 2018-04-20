package com.wxx.mapper;

import java.util.List;
import java.util.Map;

import com.wxx.entity.Article;
import com.wxx.entity.Weixx;

/*
 * 这里面的方法名必须与 weixxMapper.xml中sql的id一致
 */
public interface WeixxMapper {
	

	
	public Weixx selectWeixxById(String id);

	public Weixx selectWeixxByIu(String id, String username);

	/**
	 * Map也不需要使用@Param注解 
	 * 在xml中直接写map中的key即可
	 */
	public Weixx selectWeixxByMap(Map<String, String> map);

	public List<Weixx> selectWeixxs(String userName);

	public void addWeixx(Weixx weixx);

	public void updateWeixx(Weixx weixx);

	public void deleteWeixx(String id);

	/**
	 * Article 实体类中包含Weixx实体类 
	 * 但在数据库中是两个表
	 * 在xml中resultMap使用assocation标签可以返回两个表的并集
	 */
	public List<Article> getWeixxArticles(String id);

	/**
	 * 在xml中resultMap使用assocation标签的另一种用法 
	 * 引用weixx表的resultMap
	 */
	public List<Article> getWeixxArticles2(String id);

}
