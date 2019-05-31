package com.common.utils;

import java.util.ResourceBundle;

/**  
 *  
 * @author:wangzhen
 * @version:V1.0
 * 2017年11月20日  
 */
public class AppConfig {
	
	/**
	 * 资源配置文件
	 */
	static ResourceBundle rb;

   public AppConfig()
   {
   }
   static
	{
		// 装载配置文件
	   rb = ResourceBundle.getBundle("app");
	}
   
  /**
   * 获取系统名称，用于页面title
   */
   public static String getAppName()
   {
    return rb.getString("app_name");
   }

}
