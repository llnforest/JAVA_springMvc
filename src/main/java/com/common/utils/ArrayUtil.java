/**
 *
 * ArrayUtil.java
 * 2019年1月22日
 * author:Lynn
 */
package com.common.utils;

/**
 * 系统数据工具类
 * @author lynn
 * @version 1.0
 */
public class ArrayUtil {
	
	/**
	 * 判断数组是否为空
	 * 2019年1月22日
	 * @param arr
	 * @return
	 * @author:Lynn
	 */
	public static boolean isEmpty(Object[] arr){
		if(arr == null || (arr != null && arr.length == 0)){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 判断数组是否不为空
	 * 2019年1月22日
	 * @param arr
	 * @return
	 * @author:Lynn
	 */
	public static boolean isNotEmpty(Object[] arr){
		if(arr != null || (arr == null && arr.length != 0)){
			return true;
		}else{
			return false;
		}
	}
}
