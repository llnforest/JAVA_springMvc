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
public interface RoleMenuService extends BaseService{
	//根据等级排序
	public List<HashMap<String, Object>> getRoleMenuByLevel(String roleId); 
	
	//更改角色对应菜单
	public ResponseModel updateRoleMenu(String roleId,String[] newRoleMenu);
	

}
