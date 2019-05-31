package com.common.utils;

import java.io.IOException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig.Feature;

import com.common.log.SysLogger;


/**  
 *  Json工具类
 * @author:wangzhen
 * @version:V1.0
 * 2017年9月7日  
 */
public class JsonUtil {
	
	 private final static ObjectMapper mapper = new ObjectMapper();
	 private final static SysLogger log = new SysLogger(JsonUtil.class);
	
	    static {
	    	mapper.configure(Feature.INDENT_OUTPUT, Boolean.TRUE);
	    	mapper.configure(Feature.FAIL_ON_EMPTY_BEANS, Boolean.FALSE);
	    }
	
	/**
	 * 将传入的对象转换为json格式字符串
	 * 2017年9月8日
	 * @param obj 转换对象
	 * @return
	 * author:wangzhen
	 */
	public static String object2Json(Object obj){
	    try
		{
	    	return mapper.writeValueAsString(obj);
		} catch (JsonGenerationException e)
		{
			e.printStackTrace();
			log.error("object2Json(object)", e);
		} catch (JsonMappingException e)
		{
			e.printStackTrace();
			log.error("object2Json(object)", e);
		} catch (IOException e)
		{
			e.printStackTrace();
			log.error("object2Json(object)", e);
		}
		return null;
	}
	
	

	/**
	 * 将传入的json格式字符串转换为对象
	 * 2017年9月8日
	 * @param json
	 * @param obj
	 * @return
	 * author:wangzhen
	 */
	public static <T> T json2Ojbect(String json, Class<T> clazz){
		
	    try
		{
	    	return mapper.readValue(json.getBytes("utf-8"), clazz);
		} catch (JsonParseException e)
		{
			e.printStackTrace();
			log.error("json2Ojbect(object)", e);
		} catch (JsonMappingException e)
		{
			e.printStackTrace();
			log.error("json2Ojbect(object)", e);
		} catch (IOException e)
		{
			e.printStackTrace();
			log.error("json2Ojbect(object)", e);
		}
	    return null;
	}
	
	/**
	 * 将传入的json格式字符串转换为JSONObject对象
	 * 测试用，封装意义不大，可以直接使用原生语法
	 * 2017年9月8日
	 * @param str
	 * @return
	 * author:wangzhen
	 */
	public static JSONObject str2JSONObject(String str){
		return JSONObject.fromObject(str);
	}
	
	/**
	 * 将传入的json格式字符串转换为JSONArray数组
	 * 测试用，封装意义不大，可以直接使用原生语法
	 * 2017年9月8日
	 * @param str
	 * @return
	 * author:wangzhen
	 */
	public static JSONArray str2JSONArray(String str){
		return JSONArray.fromObject(str);
	}

}
