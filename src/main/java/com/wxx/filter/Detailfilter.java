package com.wxx.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Detailfilter implements Filter {

	public void destroy() {
		System.out.println("++++++++++destroy()++++>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>++++++");
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hr = (HttpServletRequest) req;
		String contextPath = hr.getContextPath();
		String servletPath = hr.getServletPath();
		String aaa = hr.getServerName();
		Object local = hr.getLocale();
		@SuppressWarnings("unused")
		Cookie[] cookies = hr.getCookies();
		String usrid = hr.getParameter("userid");
		HttpSession session = hr.getSession();

		System.out.println(">>>>contextPath>>>>" + contextPath
				+ ">>>>servletPath>>>>" + servletPath + ">>>>serverName>>>>" + aaa
				+ ">>>>local>>>>" + local + ">>>>usrid>>>>" + usrid
				+ ">>>>session>>>>" + session.toString());
		// String usrid = req.getParameter("userid");
		String scheme = req.getScheme();
		String getLocalName = req.getLocalName();
		System.out.println(">>>>usrid>>>>" + usrid + ">>>>scheme>>>>" + scheme
				+ ">>>>getLocalName>>>>" + getLocalName);
		chain.doFilter(req, res);
	}

	public void init(FilterConfig arg0) throws ServletException {
		int a = 1;
		System.out.println("++++++++++init()++++++++++"+a);
	}

}
