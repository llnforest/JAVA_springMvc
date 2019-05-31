package com.common.spring;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;


/**  
 * 将context设置到BeanHelper静态变量中
 * 1.可以从ApplicationContext中直接获取bean
 * 2.可以从ServletContext中获取ContextParam参数值
 * @author:wangzhen
 * @version:V1.0
 * 2017年9月6日  
 */
public class SpringContextLoaderListener extends ContextLoaderListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
		BeanHelper.setServletContext(event.getServletContext());
		BeanHelper.setApplicationContext(super.getCurrentWebApplicationContext());
		//BeanHelper.setApplicationContext(WebApplicationContextUtils.getWebApplicationContext(event.getServletContext()));
	}
	
}
