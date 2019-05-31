package com.service.system;

import com.common.response.ResponseModel;
import com.service.BaseService;



/**  
 *  
 * @author:wangzhen
 * @version:V1.0
 * 2017年4月27日  
 */
public interface RoleService extends BaseService{
	
	//删除角色
	public ResponseModel deleteRole(String roleId);

}
