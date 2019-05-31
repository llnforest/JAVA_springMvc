/**
 *
 * RoleMenuManager.java
 * 2018年12月29日
 * author:Lynn
 */
package com.service.system.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.response.ResponseModel;
import com.common.utils.ArrayUtil;
import com.common.utils.Const;
import com.model.system.SysMenu;
import com.model.system.SysRole;
import com.model.system.SysUser;
import com.model.system.SysUserRole;
import com.model.system.SysUserRole;
import com.service.impl.BaseManager;
import com.service.system.RoleMenuService;
import com.service.system.UserRoleService;

@Service
public class UserRoleManager extends BaseManager implements UserRoleService{
	@SuppressWarnings("unchecked")
	@Override
	public String getRoleIds(String userId) {
		List<SysUserRole> userRoles = (List<SysUserRole>)this.getListByHql("from SysUserRole where sysUser.userId = ?", userId);
		String roleIds = "";
		if(CollectionUtils.isNotEmpty(userRoles)){
			ArrayList<String> roleIdsArr = new ArrayList<String>();
			for(SysUserRole userRole:userRoles){
				roleIdsArr.add(userRole.getSysRole().getRoleId());
			}
			roleIds = StringUtils.join(roleIdsArr,',');
		}
		return roleIds;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ResponseModel updateUserRole(ResponseModel rModel,String userId, String roleIds) {
		if(StringUtils.isEmpty(userId)){
			rModel.setCodeAndDesc(Const.PARA_ERROR, "参数userId为空");
			return rModel;
		}
		if(StringUtils.isEmpty(roleIds)){
			rModel.setCodeAndDesc(Const.PARA_ERROR, "参数roleIds为空");
			return rModel;
		}
		String[] newRoleArr = roleIds.split(",");
		try{
			//先查找所有角色的菜单，比较判断多余的旧菜单删除
			List<SysUserRole> userRoles = (List<SysUserRole>)this.getListByHql("from SysUserRole where sysUser.userId = ?", userId);
			List<String> oldList = new ArrayList<String>();
			List<String> roleList = Arrays.asList(newRoleArr);
			if(CollectionUtils.isNotEmpty(userRoles)){
				for(SysUserRole userRole:userRoles){
					String id = userRole.getUserRoleId();
					String roleId = userRole.getSysRole().getRoleId();
					if(!roleList.contains(roleId)){
						this.deleteModelById(SysUserRole.class, id);//删除
					}
					oldList.add(roleId);
				}
			}
			//判断该角色的新增菜单
			List<String> newList = Arrays.asList(newRoleArr);
			SysUser sysUser = (SysUser) this.loadModel(SysUser.class, userId);
			for(String roleId:newList){
				if(StringUtils.isEmpty(roleId)) continue;
				SysRole sysRole = (SysRole) this.loadModel(SysRole.class, roleId);
				if(!oldList.contains(roleId)){
					SysUserRole sysUserRole = new SysUserRole(sysUser,sysRole);
					saveModel(sysUserRole);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			rModel.setCodeAndDesc(Const.CRUD_ERROR, "用户授权角色失败："+e.getMessage());
		}
		return rModel;
		
	}
}
