package com.service.system.impl;

import java.util.HashMap;
import java.util.Map;



import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.common.response.ResponseModel;
import com.common.utils.Const;
import com.common.utils.SearchUtil;
import com.model.system.SysRole;
import com.model.w2.W2Kcxx;
import com.service.impl.BaseManager;
import com.service.system.RoleService;

/**  
 *  
 * @author:wangzhen
 * @version:V1.0
 * 2017年4月26日  
 */
@Service
public class RoleManager extends BaseManager implements RoleService{
	
	@Override
	public Map<String, Object> getSqlMap(ModelMap map) {
		//获取查询hql
		StringBuffer hql = getSelectHql(SysRole.class.getName());
		//查询工具类
		SearchUtil searchUtil = new SearchUtil(hql);
		searchUtil.setHql(hql);
		searchUtil.where(map);
		searchUtil.setOrder("obj.roleOrder");
		Map<String, Object> sqlMap = new HashMap<String, Object>();
		sqlMap.put(Const.PAGE_SQL, searchUtil.hql.toString());
		sqlMap.put(Const.SQL_PARA, searchUtil.para);
		return sqlMap;
	}

	@Override
	public ResponseModel deleteRole(String roleId) {
		try {
			//删除角色菜单（联合表）
			baseDao.executeHql("delete from SysRoleMenu obj where obj.sysRole.roleId = ?", roleId);
			//删除用户角色（联合表）
			baseDao.executeHql("delete from SysUserRole obj where obj.sysRole.roleId = ?", roleId);
			//删除角色
			this.deleteModelById(SysRole.class, roleId);
			return new ResponseModel();
		} catch (Exception e) {
			return new ResponseModel(Const.CRUD_ERROR,e.getMessage());
		}
	}


}
