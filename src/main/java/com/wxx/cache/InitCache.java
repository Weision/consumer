package com.wxx.cache;

import java.util.HashMap;
import java.util.Map;

public final class InitCache {


	private static Map<String, String> wxxMap = new HashMap<String, String>();

	private InitCache() {
		
	}

	public void setTheWxxMap() {
		wxxMap.put("id", "1");
		wxxMap.put("userId", "2");
		wxxMap.put("userName", "3");
		/*List<Weixx> wxx = weixxMapper.selectWeixxs("");
		for (Weixx wx : wxx) {
			wxxMap.put("id", wx.getId());
			wxxMap.put("userId", wx.getUserid());
			wxxMap.put("userName", wx.getUsername());
		}*/

	}

	public static Map<String, String> getWxxMap() {

		return wxxMap;
	}

}
