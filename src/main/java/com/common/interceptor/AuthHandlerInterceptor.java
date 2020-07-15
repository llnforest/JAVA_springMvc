package com.common.interceptor;

import java.awt.datatransfer.StringSelection;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.cxf.endpoint.ClientImpl.EchoContext;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.common.log.SysLogger;
import com.common.spring.BeanHelper;
import com.common.utils.Const;
import com.common.utils.EhcacheUtil;
import com.common.utils.LogUtil;
import com.common.utils.MapUtil;
import com.common.utils.RequestUtil;
import com.model.system.SysMenu;
import com.service.BaseService;

/**
 * 
 * @author:Lynn
 * @version:V1.0 
 * 2019年1月9日
 */
public class AuthHandlerInterceptor extends HandlerInterceptorAdapter {
 
	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
			if(request.getRequestURI().endsWith(Const.LOGIN_URL) || request.getRequestURI().endsWith("/demo")|| request.getRequestURI().endsWith("/captcha")){
				return true;
			}
			SysLogger logger = new SysLogger(this);
			HttpSession session = request.getSession();
			//权限验证
			String uri = request.getRequestURI();
			Set<String> menuSet = (Set<String>) session.getAttribute(Const.SESSION_AUTH);
			BaseService baseService = (BaseService)BeanHelper.getBean("service");
//			if(1 == 1) return true;
			logger.info(uri);
			Map<String, String> auth = baseService.isInAuth(uri, menuSet);
			logger.info(auth);
			System.out.println(request.getSession().getAttribute(Const.SESSION_LOG_ID));
//			if(1 == 1) return false;
			System.out.println(auth.get("status"));
			if(auth.get("status").equals("true")){//权限通过
				System.out.println("权限验证成功");
				//判断是否存入日志
				LogUtil.addLog(request, auth.get("menuId"));
				logger.info("-------------");
				return true;
			}else{//权限不通过
				System.out.println("权限验证失败");
				if(StringUtils.isNotEmpty(auth.get("menuId"))){
					SysMenu sysMenu = (SysMenu) baseService.loadModel(SysMenu.class, auth.get("menuId"));
					String operateName,operateType;
					if(sysMenu.getMenuType().equals("B")){//是按钮
						operateName = sysMenu.getParent().getMenuTitle();
						operateType = sysMenu.getMenuTitle();
					}else{//非按钮
						operateName = sysMenu.getMenuTitle();
						operateType = "查看";
					}
					LogUtil.addSafeLog(request, operateName, operateType, "非法访问，访问限制", "2");
				}else{
					LogUtil.addSafeLog(request, "未知菜单", "非法访问", "非法访问，访问错误", "2");
				}
				response.setContentType("text/html;charset=utf-8");
				OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());
				logger.info(RequestUtil.getParamer(request));
				if(request.getMethod().equals("POST")){
					writer.write("{\"code\":\"1\",\"desc\":\"您没有操作权限\"}");
				}else{
					writer.write("<div style=\"text-align:center;margin-top:50px;\">对不起！您没有权限访问</div>");
				}
				writer.flush();
				writer.close();
				return false;
			}
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
			request.getSession().removeAttribute(Const.SESSION_LOG_ID);
			System.out.println("------------------end--------------");
	}

}
