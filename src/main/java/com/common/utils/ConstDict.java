package com.common.utils;
/**  
 *  该类用于对通用的一些字典进行代码及维护配置
 *  如：数据状态、是否 等常用的字典在业务代码编写过程中会被多处作为业务逻辑判断的依据
 *  如：sql语句、if判断等（isAudit = "1"）  针对此类通用的字典在此类中维护
 *  避免代码中出现过多的字典值判断，为后续的维护造成干扰，也方便了后续字典值的调整
 * @author:wangzhen
 * @version:V1.0
 * 2018年5月10日  
 */
public class ConstDict {
	
	/**
	 * 数据状态：启用：Y
	 */
	public static final String DATA_FLAG_Y = "Y";		
	/**
	 * 数据状态：禁用：N
	 */
	public static final String DATA_FLAG_N = "N";			
	/**
	 * 登录类型：登录：1
	 */
	public static final String LOGIN_TYPE_LOGIN = "1";	
	/**
	 * 登录类型：退出：0
	 */
	public static final String LOGIN_TYPE_LOGOUT = "0";	
	/**
	 * 登录结果：成功：1
	 */
	public static final String LOGIN_RESULT_SUCCES = "1";	
	/**
	 * 登录结果：失败：0
	 */
	public static final String LOGIN_RESULT_FAILED = "0";	
	

}
