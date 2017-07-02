package com.kowa.app.sessionutils;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName="myFilter",urlPatterns="/*")
public class MyFiter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		//这里填写你允许进行跨域的主机ip
		((HttpServletResponse)response).setHeader("Access-Control-Allow-Origin", "*");
		//允许的访问方法
		((HttpServletResponse)response).setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, PATCH");
		ContextHolder.setHttpRequest((HttpServletRequest) request);
		ContextHolder.setHttpResponse((HttpServletResponse) response);
		ContextHolder.setSession(((HttpServletRequest) request).getSession());
		chain.doFilter(request, response);
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
