/**
 *
 * ConfigService.java
 * 2019年2月19日
 * author:Lynn
 */
package com.service.system;

import org.springframework.ui.ModelMap;

import com.common.response.ResponseModel;
import com.service.BaseService;

public interface BlankService extends BaseService{
	/**
	 * 批量解锁
	 * 2019年2月22日
	 * @param map
	 * @return
	 * @author:Lynn
	 */
	public ResponseModel batchUnlock(ModelMap map);
	
	/**
	 * 加入黑名单
	 * 2019年2月22日
	 * @param blankType
	 * @param name
	 * @author:Lynn
	 */
	public void addBlank(String blankType,String name);
	
	/**
	 * 判断黑名单是否允许登录
	 * 2019年2月22日
	 * @param blankType
	 * @param name
	 * @author:Lynn
	 */
	public Boolean isCanLogin(String blankType,String name);
	
	/**
	 * 自动解锁
	 * 2019年2月25日
	 * @author:Lynn
	 */
	public void autoUnLock();
}