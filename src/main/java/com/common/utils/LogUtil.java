package com.common.utils;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.common.spring.BeanHelper;
import com.model.SessionUser;
import com.service.system.LogService;

/**
 * 日志工具类
 * @author Lynn
 * 2019年2月15日
 */
public class LogUtil {
	
	private static LogService logService = (LogService)BeanHelper.getBean("logManager");
	
	
	/**
	 * 添加日志
	 * 2019年2月15日
	 * @param request
	 * @param menuId
	 * @author:Lynn
	 */
	public static void addLog(HttpServletRequest request,String menuId){
		if(StringUtils.isNotEmpty(menuId)){
			String url = StringUtil.convertUrl(request.getRequestURI(), request.getQueryString());
			HttpSession session = request.getSession();
			SessionUser sessionUser = (SessionUser) session.getAttribute(Const.SESSION_USER);
			//获取客户端信息
			Map<String, String> clientMap = ClientInfo.getClientInfo(request);
			String httpData = RequestUtil.getParamer(request);
			//添加日志默认成功
			String logId = logService.addLog(url, clientMap, menuId, sessionUser.getUserId(),sessionUser.getUserName(), AppConfig.getAppName(),"1","",httpData);
			if(StringUtils.isNotEmpty(logId)) session.setAttribute(Const.SESSION_LOG_ID, logId);
		}
	}
	
	
	/**
	 * 重置日志操作结果
	 * 2019年2月15日
	 * @param request
	 * @author:Lynn
	 */
	public static void setFailLog(HttpServletRequest request){
		String logId = (String) request.getSession().getAttribute(Const.SESSION_LOG_ID);
		if(StringUtils.isNotEmpty(logId)) logService.setFailLog(logId);
	}
	
	/**
	 * 添加安全日志
	 * 2019年2月15日
	 * @param request
	 * @param operateName
	 * @param operateType
	 * @param remark
	 * @param operateResult
	 * @author:Lynn
	 */
	public static void addSafeLog(HttpServletRequest request,String operateName,String operateType,String remark,String operateResult){
		String url = StringUtil.convertUrl(request.getRequestURI(), request.getQueryString());
		HttpSession session = request.getSession();
		//获取客户端信息
		Map<String, String> clientMap = ClientInfo.getClientInfo(request);
		SessionUser sessionUser = (SessionUser) session.getAttribute(Const.SESSION_USER);
		String userId = sessionUser == null ? null : sessionUser.getUserId();
		String userName = sessionUser == null ? null : sessionUser.getUserName();
		String logLevel = "4";
		String httpData = RequestUtil.getParamer(request);
		String logId = logService.addSafeLog(operateName, operateType, operateResult, AppConfig.getAppName(), url, remark, logLevel,userId,userName, clientMap,httpData);
		if(StringUtils.isNotEmpty(logId)) session.setAttribute(Const.SESSION_LOG_ID, logId);
	}
	
	/**
	 * 添加安全日志（简略版）
	 * 2019年2月22日
	 * @param request
	 * @param operateName
	 * @param operateType
	 * @author:Lynn
	 */
	public static void addSafeLog(HttpServletRequest request,String operateName,String operateType){
		addSafeLog(request, operateName, operateType,"",Const.LOG_SUCCESS);
	}
	
	/**
	 * 添加登录失败安全日志
	 * 2019年2月22日
	 * @param request
	 * @param remark
	 * @param userId
	 * @param userName
	 * @author:Lynn
	 */
	public static void addLoginFailLog(HttpServletRequest request,String remark,String userId,String userName){
		String url = StringUtil.convertUrl(request.getRequestURI(), request.getQueryString());
		//获取客户端信息
		Map<String, String> clientMap = ClientInfo.getClientInfo(request);

		String logLevel = "4";
		String httpData = RequestUtil.getParamer(request);
		logService.addSafeLog("后台登录", Const.LOG_LOGIN_TYPE, Const.LOG_FAIL, AppConfig.getAppName(), url, remark, logLevel,userId,userName, clientMap,httpData);
		
	}
	
}
