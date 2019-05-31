/**
 *
 * MapUtil.java
 * 2019年1月18日
 * author:Lynn
 */
package com.common.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class MapUtil {
	
	/**
	 * 判断map中的key键是否存在并且是否为空
	 * 2019年1月18日
	 * @param map
	 * @param key
	 * @return
	 * @author:Lynn
	 */
	public static boolean isNotBlankKey(HashMap<?,?> map,String key){
		if(map.containsKey(key) && StringUtils.isNotEmpty(map.get(key).toString())){
			return true;
		}else{
			return false;
		}
	}
	
	public static String mapToString(Map<String,Object> map){
		if(map.isEmpty()) return null;
		StringBuffer string  = new StringBuffer();
		for(String key:map.keySet()){
			string.append(key+"="+String.valueOf(map.get(key))+"&");
		}
		return string.toString();
	}
}
