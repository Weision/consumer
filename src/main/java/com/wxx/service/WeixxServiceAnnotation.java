package com.wxx.service;

import java.util.List;

import com.wxx.entity.Weixx;

public interface WeixxServiceAnnotation {

	// 查询单个数据
	Weixx queryWeixx(String name);

	// 查询单个数据 多个入参
	Weixx queryWeixxParams(String name,String id);

	// 查询所有数据
	List<Weixx> queryWeixxList();

	// 以单个字段为入参插入数据
	public int insert(String id, String userid, String username);

	// 以对象为入参插入数据
	public int insertWeixx(Weixx weixx);

	// 以对象为入参插入数据
	int delete(Weixx weixx);

	// 以对象为入参插入数据
	int update(Weixx weixx);

}
