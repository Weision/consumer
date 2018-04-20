package com.wxx.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wxx.entity.Article;
import com.wxx.entity.Weixx;
import com.wxx.mapper.WeixxMapper;
import com.wxx.service.WeixxService;

@Service(value = "weixxServiceImpl")
public class WeixxServiceImpl implements WeixxService {

	@Resource
	WeixxMapper weixxMapper;

	public Weixx getC1Serv(String id) {
		return weixxMapper.selectWeixxById(id);
	}

	public Weixx getC1Serv(String id, String username) {
		return weixxMapper.selectWeixxByIu(id, username);
	}

	public Weixx getC1Serv(Map<String, String> map) {
		return weixxMapper.selectWeixxByMap(map);
	}

	public List<Article> getArticles(String id) {
		return weixxMapper.getWeixxArticles(id);
	}

	public List<Article> getArticles2(String id) {
		return weixxMapper.getWeixxArticles2(id);
	}

	public void add(Weixx weixx) {
		weixxMapper.addWeixx(weixx);
	}

	public void update(Weixx weixx) {
		weixxMapper.updateWeixx(weixx);
	}

	public void delete(String id) {
		weixxMapper.deleteWeixx(id);
	}
}
