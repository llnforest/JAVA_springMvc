package com.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**  
 *  
 * @author:wangzhen
 * @version:V1.0
 * 2017年9月7日  
 */
public class CookieUtil {
	
	/**
	 * 添加一个cookie信息
	 * 2017年9月7日
	 * @param response
	 * @param name cookie名称
	 * @param value  cookie值
	 * @param maxAge cookie生命周期，单位秒 为0时为临时cookie，浏览器关闭即失效
	 * author:wangzhen
	 */
	public static void addCookie(HttpServletResponse response,String name,String value,int maxAge){
		try {
			value = URLEncoder.encode(value, "utf-8");
			Cookie cookie = new Cookie(name,value);
		    cookie.setPath("/");
		    if(maxAge>0)  cookie.setMaxAge(maxAge);
		    response.addCookie(cookie);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 添加一个默认生命周期为1个月的cookie信息
	 * 2017年9月7日
	 * @param response
	 * @param name
	 * @param value
	 * author:wangzhen
	 */
	public static void addCookie(HttpServletResponse response,String name,String value){
		try {
			value = URLEncoder.encode(value, "utf-8");
			Cookie cookie = new Cookie(name,value);
		    cookie.setPath("/");
		    cookie.setMaxAge(60*60*24*30);
		    response.addCookie(cookie);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 根据名称获取cookie
	 * 2017年9月7日
	 * @param request
	 * @param name
	 * @return
	 * author:wangzhen
	 */
	public static Cookie getCookieByName(HttpServletRequest request,String name){
	    Map<String,Cookie> cookieMap = ReadCookieMap(request);
	    if(cookieMap.containsKey(name)){
	        Cookie cookie = (Cookie)cookieMap.get(name);
	        return cookie;
	    }else{
	        return null;
	    }   
	}
	/**
	 * 根据名称获取cookie值
	 * 2017年9月7日
	 * @param request
	 * @param name
	 * @return
	 * author:wangzhen
	 */
	public static String getCookieValueByName(HttpServletRequest request,String name){
		try {
			Cookie cookie =  getCookieByName(request,name);
	        if(cookie!=null){
	        	String value = URLDecoder.decode(cookie.getValue(),"utf-8");
	        	return value;
	        }else{
	        	return null;
	        }
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 修改给定名称的cookie值
	 * 2017年9月7日
	 * @param request
	 * @param response
	 * @param name
	 * @param value
	 * author:wangzhen
	 */
	public static void editCookie(HttpServletRequest request,HttpServletResponse response,String name,String value){
		try {
			value = URLEncoder.encode(value, "utf-8");
			Cookie cookie =  getCookieByName(request,name);
			if(cookie!=null){
				cookie.setValue(value);
			    response.addCookie(cookie);
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 删除给定名称的cookie
	 * 2017年9月7日
	 * @param request
	 * @param response
	 * @param name
	 * author:wangzhen
	 */
	public static void deleteCookie(HttpServletRequest request,HttpServletResponse response,String name){
		Cookie cookie =  getCookieByName(request,name);
		if(cookie!=null){
			cookie.setValue(null);
			cookie.setMaxAge(0);//立即销毁cookie
		    response.addCookie(cookie);
		}

	}
	
	/**
	 * 将cookie封装到Map中
	 * 2017年9月7日
	 * @param request
	 * @return
	 * author:wangzhen
	 */
	@SuppressWarnings("all")
	private static Map<String,Cookie> ReadCookieMap(HttpServletRequest request){  
	    Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
	    Cookie[] cookies = request.getCookies();
	    if(null!=cookies){
	        for(Cookie cookie : cookies){
	            cookieMap.put(cookie.getName(), cookie);
	        }
	    }
	    return cookieMap;
	}

}
