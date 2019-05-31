/**
 *
 * ConfigService.java
 * 2019年2月19日
 * author:Lynn
 */
package com.service.system;

import com.service.BaseService;

public interface ConfigService extends BaseService{
	/**
	 * 根据配置名称获取配置值
	 * 2019年2月22日
	 * @param name
	 * @return
	 * @author:Lynn
	 */
	public String getValueByCode(String code);
}
