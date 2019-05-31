package com.service.system;

import com.common.response.ResponseModel;
import com.service.BaseService;



/**  
 *  
 * @author:wangzhen
 * @version:V1.0
 * 2017年4月27日  
 */
public interface UserService extends BaseService{

	/**
	 * 该方法用于保存用户个性化配置，如自定义快捷菜单、系统皮肤等
	 * 2018年8月8日
	 * @param userId 
	 * @param type 个性化类型
	 * @param val 个性化值
	 * @param isUnique 是否唯一性配置如：系统皮肤为唯一性配置，快捷菜单则可以对应多条记录
	 * @return
	 * author:wangzhen
	 */
	public boolean saveUserConfig(String userId,String type,String val,boolean isUnique);
	
	/**
	 * 获取角色列表信息
	 * 2019年1月21日
	 * @param userId
	 * @return
	 * @author:Lynn
	 */
	public ResponseModel getRolesData(String userId);
	
	/**
	 * 根据用户id获取角色名称:字符串ids
	 * 2019年1月23日
	 * @param userId
	 * @return
	 * @author:Lynn
	 */
	public String getRoleNames(String userId);
	
}
