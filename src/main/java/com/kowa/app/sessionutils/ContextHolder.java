package com.kowa.app.sessionutils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kowa.app.po.UserPo;

public class ContextHolder {
	private static ThreadLocal<HttpSession> SessionThreadLocalHolder = new ThreadLocal<HttpSession>();
	private static ThreadLocal<HttpServletRequest> HttpRequestThreadLocalHolder = new ThreadLocal<HttpServletRequest>();
	private static ThreadLocal<HttpServletResponse> HttpResponseThreadLocalHolder = new ThreadLocal<HttpServletResponse>();
public static void setHttpRequest(HttpServletRequest request){
		
		HttpRequestThreadLocalHolder.set(request);
	}
public static void setHttpResponse(HttpServletResponse response){
	HttpResponseThreadLocalHolder.set(response);	
}
public static HttpSession getSession() {
	if (SessionThreadLocalHolder.get() == null) {
		HttpServletRequest httpRequest = getHttpRequest();
		SessionThreadLocalHolder.set( (httpRequest != null ) ? httpRequest.getSession() : null );
	}else{
//		if(logger.isDebugEnabled())
//			logger.debug(" webSessionContext not null and return ...");
	}
	return SessionThreadLocalHolder.get();
}
public static void setSession(HttpSession session) { 
	SessionThreadLocalHolder.set(session);
}

public static HttpServletRequest getHttpRequest(){
	return  HttpRequestThreadLocalHolder.get();
}
public static HttpServletResponse getHttpResponse(){
	
	return HttpResponseThreadLocalHolder.get();
}

/**
 * 获取当前登录的会员
 * @return 如果没有登录返回null
 */
public static UserPo getCurrentMember(){

	HttpSession sessonContext = ContextHolder.getSession();
	if(sessonContext!=null){
		return (UserPo) sessonContext.getAttribute("memberkey");
	}
	return null;
}

public static void clearCurrentMember(){
	HttpSession sessonContext = ContextHolder.getSession();
	sessonContext.removeAttribute("memberkey");
}

public static void clearSesstion(){
	getSession().setMaxInactiveInterval(0);
}

public static void setCurrentMember(UserPo user){
	getSession().setAttribute("memberkey", user);
}

public static void setCurrentMember(UserPo user,int time){
	getSession().setMaxInactiveInterval(time);
	getSession().setAttribute("memberkey", user);
}
public static void updataCurrentMember(UserPo user){
	getSession().setAttribute("memberkey", user);
}
}
