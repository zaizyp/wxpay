package com.ping.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {
	
	//后台管理界面url
	@Value("${inter.admin.url}")
	private String ADMIN_URL_SUFFIX;
	//前台登录url后缀suffix
	@Value("${inter.login.url}")
	private String LOGIN_URL_SUFFIX;
	//前台登录url
	@Value("${mail.login.url}")
	private String LOGIN_URL;
	//微信登录url
	@Value("${WX.LOGIN.URL}")
	private String WX_LOGIN_URL;
	//微信公众号APPID
	@Value("${WX.APPID}")
	private String WX_APPID;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//获取请求地址
		String url = request.getRequestURI();
		//请求登录地址直接返回
		if(url.indexOf(LOGIN_URL_SUFFIX)>=0){
			return true;
		}
		HttpSession session = request.getSession();
		
		//后台请求地址
		if(url.indexOf(ADMIN_URL_SUFFIX)>=0){
			//判断后台登录状态
			if(session.getAttribute("admin_user_id") == null){
				//设置后台登录用户默认为admin
				session.setAttribute("admin_user_id", "1L");
//				return false;
			}
			return true;
		}
		
		//前台请求地址判断登录状态
		if(session.getAttribute("user_id") == null){
//			默认设置用户id为4
			session.setAttribute("user_id", 4L);
			session.setAttribute("openid", "orajBwVMxNr7IjFjKv-C1_56iOAI");
			
/*			String loginUrl = WX_LOGIN_URL+"?appid="+WX_APPID+"&redirect_uri="+LOGIN_URL+
					"&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect";
			response.sendRedirect(loginUrl);
			return false;*/
		}
			
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
