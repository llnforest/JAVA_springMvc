package com.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.common.log.SysLogger;

/**
 * 记录时间长的请求（慢请求）的日志 该拦截器要放到拦截器链的第一位
 * 
 * @author:wangzhen
 * @version:V1.0 2017年8月31日
 */
public class TimeHandlerInterceptor extends HandlerInterceptorAdapter {
	// 默认阈值5秒
	private  int defaultTime  = 5000;
	protected SysLogger timeLogger = new SysLogger("timeLogger");

	long startTime = 0;
	long endTime = 0;

	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		// 1、记录开始时间
		startTime = System.currentTimeMillis();
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object handler, Exception ex)throws Exception {
		// 2、记录结束时间
		endTime = System.currentTimeMillis();
		// 3、计算消耗时间
		long consumeTime = endTime - startTime;
		if (consumeTime > 5000) {
			//输出警告（请求时间过长，请优化）日志！
			// TODO 
			timeLogger.warn(request.getRequestURL().toString()+"###"+ request.getHeader("referer")+"###"+ request.getRemoteAddr() +"###"+ consumeTime);
		}
	}

	public int getDefaultTime() {
		return defaultTime;
	}
	public void setDefaultTime(int defaultTime) {
		this.defaultTime = defaultTime;
	}
}
