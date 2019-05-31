package com.common.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.cxf.common.util.Base64OutputStream;
import org.aspectj.apache.bcel.generic.InvokeDynamic;

import sun.misc.BASE64Encoder;

import com.common.log.SysLogger;
import com.sun.mail.util.BASE64EncoderStream;

/**
 * 字符串辅助工具类
 * 
 * @author:wangzhen
 * 2017年3月27日
 */
public class StringUtil {

	/**
	 * 获取32位guid 
	 * 2016-3-11
	 * @return UUID
	 * author:wangZhen
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * 该方法用于将传入字符串按照指定的分隔符，
	 * 将该字符串转换成数组
	 * 2017年9月7日
	 * @param str
	 * @param split 注意特殊符号需要转义，如：$ 要传入\\$
	 * @return
	 * author:wangzhen
	 */
	public static List<String> string2List(String str, String split) {
		if(StringUtils.isEmpty(str))
			return null;
	    String[] arrary = str.split(split);
	    List<String> list = Arrays.asList(arrary);
	    return list;
	}

	/**
	 * 该方法用于匹配目标字符串与是否与规则字符串相匹配 大多用于验证当前uri与web.xml配置的uri的匹配 
	 * 2017年3月27日
	 * @param pattern 规则字符串（可以含* 如：*.jsp,application*.xml等）
	 * @param source 目标字符串 （验证是否符合上述格式）
	 * @return 
	 * author:wangzhen
	 */
	public static boolean uriMatches(String pattern, String source) {
		if ((pattern == null) || (source == null)) {
			return false;
		}
		pattern = pattern.trim();
		source = source.trim();
		if (pattern.endsWith("*")) {
			int length = pattern.length() - 1;
			if ((source.length() >= length)
					&& (pattern.substring(0, length).equals(source.substring(0,
							length)))) {
				return true;
			}
		} else if (pattern.startsWith("*")) {
			int length = pattern.length() - 1;
			if ((source.length() >= length)
					&& (source.endsWith(pattern.substring(1)))) {
				return true;
			}
		} else if (pattern.contains("*")) {
			int start = pattern.indexOf("*");
			int end = pattern.lastIndexOf("*");
			if ((source.startsWith(pattern.substring(0, start)))
					&& (source.endsWith(pattern.substring(end + 1)))) {
				return true;
			}

		} else if (pattern.equals(source)) {
			return true;
		}

		return false;
	}
	
	/**
	 * 对传入的对象进行空值判断，将空对象转换为空字符串
	 * 2018年3月13日
	 * @param obj
	 * @return
	 * author:wangzhen
	 */
	public static String convertNull(Object obj){
    	if(obj == null || obj.toString().trim().toLowerCase().equals("null")){
    		return "";
    	}else{
    		return obj.toString();
    	}
	}
	
	/**
	 * 该方法用于根据传入的url和参数串获取正确请求地址
	 * 2018年9月16日
	 * @param url
	 * @param paraString
	 * @return
	 * author:wangzhen
	 */
	public static String convertUrl(String url,String paraString){
		if(StringUtils.isNotEmpty(paraString)){
			if(url.indexOf("?")==-1){
				url += "?"+paraString;
			}else {
				url += "&"+paraString;
			}
		}
    	return url;
	}
	
	
	/**
	 * 去除一个url的参数部分
	 * 2018年9月16日
	 * @param url
	 * @return
	 * author:wangzhen
	 */
	public static String romoveUrlPara(String url){
			if(url.indexOf("?")==-1){
				return url;
			}else {
				url = url.substring(0, url.indexOf("?"));
				return url;
			}
	}
	/**
	 * 根据传入的filepath获取filename
	 * 2018年9月20日
	 * @param filepath
	 * @return
	 * author:wangzhen
	 */
	public static String getFileName(String filepath){
		if(filepath.lastIndexOf("/")==-1){
			return filepath;
		}else {
			filepath = filepath.substring(filepath.lastIndexOf("/")+1);
			return filepath;
		}
	}
	
	/**
	 * 使用js方法eval触发js方法字符串
	 * 2019年1月18日
	 * @param funcName js方法名
	 * @param param 替换值
	 * @return
	 * @author:Lynn
	 */
	public static String getJsEvalFunc(String funcName,String param){
		if(StringUtils.isNotEmpty(funcName) && StringUtils.isNotEmpty(param)){
			ScriptEngineManager manager = new ScriptEngineManager();
			ScriptEngine engine = manager.getEngineByName("js");
			String function = funcName.replace("#", param);
			try {
				return String.valueOf(engine.eval(function));
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return param;
			}
		}else{
			return param;
		}
		
	}


}
