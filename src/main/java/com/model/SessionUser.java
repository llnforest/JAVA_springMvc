package com.model;


import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.model.system.SysUser;

/**  
 *  该类用于存储用户登录成功后的基本信息
 *  以及与用户相关的关联信息
 * @author:wangzhen
 * @version:V1.0
 * 2017年9月5日  
 */
@Repository
@Scope("prototype")
public class SessionUser {

	/**
	 * 用户实体对象
	 */
	protected SysUser user;
	
	
	/**
	 * 用户配置信息
	 */
	protected Map<String, Object> userConfig = new HashMap<String, Object>();
	
	
	public SysUser getUser() {
		return user;
	}

	public void setUser(SysUser user) {
		this.user = user;
	}
	
	/**
	 * 获取当前登录用户的用户id
	 * 2017年9月5日
	 * @return
	 * author:wangzhen
	 */
	public String getUserId()
	{
		return user.getUserId();
	}
	
	/**
	 * 获取当前登录用户的名称
	 * 2017年9月5日
	 * @return
	 * author:wangzhen
	 */
	public String getUserName(){
		return user.getUserName();
	}
	
	/**
	 * 获取当前登录用户的登录名
	 * 2017年9月5日
	 * @return
	 * author:wangzhen
	 */
	public String getLoginName(){
		return user.getLoginName();
	}

	/**
	 * 获取用户配置信息
	 * 2018年9月8日
	 * @return
	 * author:wangzhen
	 */
	public Map<String, Object> getUserConfig() {
		return userConfig;
	}

	public void setUserConfig(Map<String, Object> userConfig) {
		this.userConfig = userConfig;
	}
	

	
	

	
	
	
}
