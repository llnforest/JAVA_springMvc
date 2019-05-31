package com.service.system;

import java.util.List;

import com.model.SessionUser;
import com.model.system.SysUser;
import com.service.BaseService;



/**  
 *  
 * @author:wangzhen
 * @version:V1.0
 * 2017年4月27日  
 */
public interface LoginService extends BaseService{
	
	/**
	 * 用户登录接口
	 * 2017年4月27日  
	 * @param loginName
	 * @param passWord
	 * @return
	 * author:wangzhen
	 */
	public SessionUser login(String loginName,String passWord);
	
	public List<SysUser> getUserList(Object... para);
	
	/**
	 * 用户登录根据用户名获取用户id
	 * 2019年2月22日
	 * @param loginName
	 * @return
	 * @author:Lynn
	 */
	public SysUser getLoginUser(String loginName);

}
