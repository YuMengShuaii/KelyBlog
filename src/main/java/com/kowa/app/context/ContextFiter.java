package com.kowa.app.context;
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
public class ContextFiter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//这里填写你允许进行跨域的主机ip
		((HttpServletResponse)response).setHeader("Access-Control-Allow-Origin", "*");
		//允许的访问方法
		((HttpServletResponse)response).setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, PATCH");
		//存储request
		ContextHolder.setHttpRequest((HttpServletRequest) request);
		//存储respones
		ContextHolder.setHttpResponse((HttpServletResponse) response);
		//持久化session
		ContextHolder.setSession(((HttpServletRequest) request).getSession());
		//继续迭代拦截器
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
