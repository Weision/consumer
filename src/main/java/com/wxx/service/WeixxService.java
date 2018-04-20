package com.wxx.service;

import java.util.List;
import java.util.Map;

import com.wxx.entity.Article;
import com.wxx.entity.Weixx;

public interface WeixxService {

	public Weixx getC1Serv(String id);

	public Weixx getC1Serv(String id, String username);

	public Weixx getC1Serv(Map<String, String> map);

	public List<Article> getArticles(String id);

	public List<Article> getArticles2(String id);

	public void add(Weixx weixx);

	public void update(Weixx weixx);

	public void delete(String id);

}
