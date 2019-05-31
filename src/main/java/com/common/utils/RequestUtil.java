/**
 *
 * RequestUtil.java
 * 2019年2月27日
 * author:Lynn
 */
package com.common.utils;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;



public class RequestUtil {
	/**
	 * 获取参数
	 * 2019年2月27日
	 * @param request
	 * @return
	 * @author:Lynn
	 */
	public static String getParamer(HttpServletRequest request){
		if(request.getMethod().equals("GET")){
			return request.getQueryString();
		}
		StringBuilder sb = new StringBuilder();
		try{
			Map<String, String[]> map = request.getParameterMap();
			for(String key:map.keySet()){
				sb.append(key+"="+map.get(key)[0]).append("&");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		String str = null;
		if(sb.length() > 0){
			str = sb.deleteCharAt(sb.length()-1).toString();
		}
		return str;
	}
}
