package com.common.interceptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.common.log.SysLogger;
import com.common.page.Page;
import com.common.spring.BeanHelper;
import com.common.utils.Const;
import com.common.utils.EhcacheUtil;
import com.model.BaseModel;
import com.model.SessionUser;
import com.service.system.ConfigService;
import com.service.system.IndexService;
import com.service.system.LoginService;
import com.service.system.impl.LoginManager;

/**
 * 登录认证拦截器
 * @author:wangzhen
 * @version:V1.0 
 * 2017年8月31日
 */
public class LoginHandlerInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private LoginService service;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		SysLogger logger = new SysLogger(this);
		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute(Const.SESSION_USER);
		System.out.println("uri:"+request.getRequestURI());
		
		//不做拦截的url
		if(request.getRequestURI().endsWith("/demo") || request.getRequestURI().endsWith(Const.CANCEL_URL)){
			return true;
		}
		
		HttpSession session = request.getSession();
		session.setAttribute(Const.SESSION_OPERATE_TIME, System.currentTimeMillis());
		//如果当前uri是登录请求，则不拦截
		if(request.getRequestURI().endsWith(Const.LOGIN_URL)){
			//如果当前有sessionUser信息，则登录地址重定向到首页
			if(sessionUser!=null){
				response.sendRedirect(Const.INDEX_URL);
			}
			return true;
		}
		

		
		if(sessionUser == null){
			//重新定义sessionUser
			sessionUser = service.login("admin","lil.1234");
			sessionUser.setUserConfig(service.initUserConfig(sessionUser.getUserId()));
			//保存用户信息session
			session.setAttribute(Const.SESSION_USER, sessionUser);
			IndexService indexService = (IndexService)BeanHelper.getBean("indexManager");
			//保存权限菜单session
			Set<String> menuSet = indexService.getAuthMenu(sessionUser.getUserId());
			session.setAttribute(Const.SESSION_AUTH, menuSet);
			return true;
		}
		
		//不是登录地址，若获取到当前登录用户，则放行
		if(sessionUser!=null){
			String uri = request.getRequestURI();
			if(uri.endsWith(Const.INDEX_URL) || uri.endsWith(Const.CONSOLE_URL) || uri.endsWith("/topMenu") || uri.indexOf("/leftMenu/") != -1 || uri.endsWith(Const.LOGOUT_URL) || uri.endsWith(Const.CANCEL_URL)) return true; 
			else if(sessionUser.getUser().getState().equals("1")) response.sendRedirect(Const.CONSOLE_URL);
			return true;
		}
		
		//正式使用时
//		if(sessionUser!=null && EhcacheUtil.get(sessionUser.getUserId()) != null){
//	 		session.setAttribute(Const.SESSION_OPERATE_TIME, System.currentTimeMillis());
//			return true;
//		}else if(sessionUser!=null && EhcacheUtil.get(sessionUser.getUserId()) == null){
//			//登录已过期
//			response.sendRedirect(Const.LOGOUT_URL);
//			return false;
//		}else{
//			//未获取到sessionUser信息，则跳转到登录页面
//			response.sendRedirect(Const.LOGIN_URL);
//		    return false;
//		}
		
		
		
		// TODO 携带token认证的如何处理？
		
		// TODO 单一客户端登录的如何处理？
		
		//未获取到sessionUser信息，则跳转到登录页面
		response.sendRedirect(Const.LOGIN_URL);
	    return false;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
			
		
	}

}
