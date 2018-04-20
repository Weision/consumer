package com.wxx.cache;

import java.util.HashMap;
import java.util.Map;

public final class StaticMapCache {

	public static Map<String, String> weixxMap = new HashMap<String, String>();

	public static GetCatche getCatche = new GetCatche();
	public static String str;

	static {
		str = new String("this is String");
	}

	private static class GetCatche {

		public GetCatche() {
			weixxMap.put("1", "2");
			weixxMap.put("3", "4");
			weixxMap.put("5", "6");
		}

	}

}
