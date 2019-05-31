package com.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;

import net.sf.cglib.beans.BeanMap;


/**  
 * 类型互转工具类
 * @author:wangzhen
 * @version:V1.0
 * 2017年9月22日  
 */
public class ConvertUtil {
	
	/**
	 * 将实体对象转换成Map
	 * 2017年9月22日
	 * @param bean
	 * @return
	 * author:wangzhen
	 */
	public static Map<String, Object> object2Map(Object model){
		Map<String, Object> map = new HashMap<String, Object>();  
	    if (model != null) {  
	        BeanMap beanMap = BeanMap.create(model);  
	        for (Object key : beanMap.keySet()) { 
	        	if(beanMap.get(key)!=null)
	            map.put(key.toString(), beanMap.get(key));  
	        }             
	    }  
	    return map;  
	}
	
	
	/**
	 * 将实体对象转list 转成arrayList
	 * 2019年1月10日
	 * @param list
	 * @return
	 */
	public static ArrayList<Map<String, Object>> List2Array(List<Object> list){
		ArrayList<Map<String, Object>> arrayList = new ArrayList<Map<String,Object>>();
		if(CollectionUtils.isNotEmpty(list)){
			for (Object object : list) {
				Map<String, Object> map = object2Map(object);
				arrayList.add(map);
			}
		}
		return arrayList;
		
	}

}
