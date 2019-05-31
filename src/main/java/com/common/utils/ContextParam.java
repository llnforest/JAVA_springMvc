package com.common.utils;

import org.apache.commons.lang.StringUtils;

import com.common.spring.BeanHelper;

/**  
 *  获取web.xml中定义的ContextParam值
 * @author:wangzhen
 * @version:V1.0
 * 2017年9月6日  
 */
public class ContextParam {
	
	/**
	 * 获取是否要启用验证码标志符
	 * 2017年9月7日
	 * @return
	 * author:wangzhen
	 */
	public static boolean getCaptchaFlag(){
		String captchaFlag  = BeanHelper.getServletContext().getInitParameter("captchaFlag");
		if(StringUtils.isNotEmpty(captchaFlag)&&captchaFlag.equals("true")){
			return true;
		}else {
			return false;
		}
	}
	
	
	/**
	 * 根据web.xml中的paramName获取paramValue值
	 * 2017年9月7日
	 * @param paramName
	 * @return
	 * author:wangzhen
	 */
	public static String getParamValue(String paramName){
		return BeanHelper.getServletContext().getInitParameter(paramName);
	}
	

}
