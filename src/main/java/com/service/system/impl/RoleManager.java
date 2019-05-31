package com.service.system.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.Query;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.common.response.ResponseModel;
import com.common.utils.Const;
import com.dao.BaseDao;
import com.model.system.SysRole;
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
		Map<String, Object> sqlMap = new HashMap<String, Object>();
		List<Object> para = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer();
		hql.append(" select obj.roleId ,obj.roleName as 角色名称,obj.roleGroup as 角色归属,obj.remark as 备注说明 ");
		hql.append(" from SysRole obj where 1=1 ");
		if(map.get("roleName")!=null&&StringUtils.isNotEmpty(map.get("roleName").toString())){
			hql.append(" and obj.roleName like ? ");
			para.add("%"+map.get("roleName").toString()+"%");
		}
		if(map.get("roleGroup")!=null&&StringUtils.isNotEmpty(map.get("roleGroup").toString())){
			hql.append(" and obj.roleGroup like ? ");
			para.add("%"+map.get("roleGroup").toString()+"%");
		}
		hql.append(" order by obj.roleOrder asc  ");
		sqlMap.put(Const.PAGE_SQL, hql.toString());
		sqlMap.put(Const.SQL_PARA, para);
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
