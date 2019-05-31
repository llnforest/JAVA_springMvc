package com.service.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.common.response.ResponseModel;
import com.model.system.SysRoleMenu;
import com.service.BaseService;

/**  
 * 角色菜单表处理服务
 * @author:Lynn
 * @version:V1.0
 * 2017年4月27日  
 */
public interface UserRoleService extends BaseService{
	/**
	 * 获取用户的角色ids
	 * 2019年1月22日
	 * @param userId
	 * @return
	 * @author:Lynn
	 */
	public String getRoleIds(String userId); 

	/**
	 * 更改角色对应菜单
	 * 2019年1月22日
	 * @param rModel
	 * @param userId
	 * @param RoleIdsArr
	 * @return
	 * @author:Lynn
	 */
	public ResponseModel updateUserRole(ResponseModel rModel,String userId,String roleIds);
}
