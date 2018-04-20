package com.wxx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxx.entity.Weixx;
import com.wxx.mapper.WeixxMapperAnnotation;
import com.wxx.service.WeixxServiceAnnotation;

@Service(value = "weixxServiceAnnotationImpl")
public class WeixxServiceAnnotationImpl implements WeixxServiceAnnotation {

	@Autowired
	private WeixxMapperAnnotation wxxMapper;

	/**
	 * 单个入参
	 */
	@Override
	public Weixx queryWeixx(String name) {
		Weixx weixx = wxxMapper.query(name);
		return weixx;
	}

	/**
	 * 多个入参
	 */
	@Override
	public Weixx queryWeixxParams(String name, String id) {
		Weixx weixx = wxxMapper.queryParams(name, id);
		return weixx;
	}

	@Override
	public List<Weixx> queryWeixxList() {
		List<Weixx> list = wxxMapper.queryList();
		return list;
	}

	@Override
	public int insert(String id, String userid, String username) {
		int result = wxxMapper.insert(id, userid, username);
		return result;
	}

	@Override
	public int insertWeixx(Weixx weixx) {
		int result = wxxMapper.insertWeixx(weixx);
		return result;
	}

	@Override
	public int delete(Weixx weixx) {
		int result = wxxMapper.delete(weixx);
		return result;
	}

	@Override
	public int update(Weixx weixx) {
		int result = wxxMapper.update(weixx);
		return result;
	}

}
