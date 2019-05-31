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
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.response.ResponseModel;
import com.common.utils.Const;
import com.model.system.SysMenu;
import com.model.system.SysRole;
import com.model.system.SysRoleMenu;
import com.service.impl.BaseManager;
import com.service.system.RoleMenuService;

@Service
public class RoleMenuManager extends BaseManager implements RoleMenuService{

//	@SuppressWarnings("unchecked")
//	@Override
//	public List<HashMap<String, Object>> getRoleMenuByLevel(String roleId) {
//		//获取角色菜单
//		StringBuffer rolehql = new StringBuffer();
//		rolehql.append("select obj.sysMenu.menuId from SysRoleMenu obj where obj.sysRole.roleId = ?");
//		List<String> roleMenuResult = (List<String>)this.getListByHql(rolehql.toString(), roleId);
//		ArrayList<String> menuArr = new ArrayList<String>();
//		if(CollectionUtils.isNotEmpty(roleMenuResult)){
//			for(String menu:roleMenuResult){
//				menuArr.add(menu);
//			}
//		}
//		
//		//获取菜单列表
//		List<HashMap<String, Object>> menuList = (List<HashMap<String, Object>>) this.getMenuTree(menuArr, "");
//		log.info(menuList);
//		return menuList;
//		
//	}
//	
//	
//	
//	//获得菜单列表树
//	@SuppressWarnings("unchecked")
//	private List<HashMap<String, Object>> getMenuTree(ArrayList<String> menuArr,String parentId){
//		List<SysMenu> menuList;
//		if(StringUtils.isEmpty(parentId)){
//			menuList = (List<SysMenu>)this.getListByHql("from SysMenu where parentId is null");
//		}else{
//			menuList = (List<SysMenu>)this.getListByHql("from SysMenu where parentId = ?",parentId);
//		}
//		List<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
//		if(CollectionUtils.isNotEmpty(menuList)){
//			for(SysMenu menu:menuList){
//				HashMap<String, Object> menuMap = new HashMap<String, Object>();
//				menuMap.put("name", menu.getMenuTitle());
//				menuMap.put("id", menu.getMenuId());
//				menuMap.put("check", menuArr.indexOf(menu.getMenuId()) > -1 ? 1 : 0);
//				menuMap.put("subList", getMenuTree(menuArr, menu.getMenuId()));
//				list.add(menuMap);
//			}
//		}
//		return list;
//	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HashMap<String, Object>> getRoleMenuByLevel(String roleId) {
		//获取角色菜单
		StringBuffer rolehql = new StringBuffer();
		rolehql.append("select obj.sysMenu.menuId from SysRoleMenu obj where obj.sysRole.roleId = ?");
		List<String> roleMenuList = (List<String>)this.getListByHql(rolehql.toString(), roleId);
		
		//获取菜单列表
		List<HashMap<String, Object>> menuList = (List<HashMap<String, Object>>) this.getMenuTree(roleMenuList, "");
		log.info(menuList);
		return menuList;
		
	}
	
	
	
	//获得菜单列表树
	@SuppressWarnings("unchecked")
	private List<HashMap<String, Object>> getMenuTree(List<String> menuArr,String parentId){
		List<SysMenu> menuList;
		if(StringUtils.isEmpty(parentId)){
			menuList = (List<SysMenu>)this.getListByHql("from SysMenu where parentId is null order by menuOrder asc");
		}else{
			menuList = (List<SysMenu>)this.getListByHql("from SysMenu where parentId = ? order by menuOrder asc",parentId);
		}
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		if(CollectionUtils.isNotEmpty(menuList)){
			for(SysMenu menu:menuList){
				HashMap<String, Object> menuMap = new HashMap<String, Object>();
				menuMap.put("name", menu.getMenuTitle());
				menuMap.put("value", menu.getMenuId());
				menuMap.put("checked", menuArr.indexOf(menu.getMenuId()) > -1 ? true : false);
				List<HashMap<String, Object>> obj = getMenuTree(menuArr, menu.getMenuId());
				if(CollectionUtils.isNotEmpty(obj)) menuMap.put("list",obj );
				list.add(menuMap);
			}
		}
		return list;
	}



	@SuppressWarnings("unchecked")
	@Override
	public ResponseModel updateRoleMenu(String roleId, String[] newRoleMenuArr) {
		try{
			//先查找所有角色的菜单，比较判断多余的旧菜单删除
			int delete = 0;
			int add = 0;
			List<SysRoleMenu> roleMenus = (List<SysRoleMenu>)this.getListByHql("from SysRoleMenu where sysRole.roleId = ?", roleId);
			List<String> oldList = new ArrayList<String>();
			List<String> menuList = Arrays.asList(newRoleMenuArr);
			if(CollectionUtils.isNotEmpty(roleMenus)){
				for(SysRoleMenu roleMenu:roleMenus){
					String id = roleMenu.getRoleMenuId();
					String menuId = roleMenu.getSysMenu().getMenuId();
					if(!menuList.contains(menuId)){
						this.deleteModelById(SysRoleMenu.class, id);//删除
						delete ++;
					}
					oldList.add(menuId);
				}
			}
			//判断该角色的新增菜单
			log.info(newRoleMenuArr);
			log.info(newRoleMenuArr.length);
			if(newRoleMenuArr != null || (newRoleMenuArr == null && newRoleMenuArr.length != 0)){
				List<String> newList = Arrays.asList(newRoleMenuArr);
				SysRole sysRole = (SysRole) this.loadModel(SysRole.class, roleId);
				for(String menuId:newList){
					if(StringUtils.isEmpty(menuId)) continue;
					SysMenu sysMenu = (SysMenu) this.loadModel(SysMenu.class, menuId);
					if(!oldList.contains(menuId)){
						SysRoleMenu sysRoleMenu = new SysRoleMenu(sysRole,sysMenu);
						saveModel(sysRoleMenu);
						add ++;
					}
				}
			}
			log.info(delete);
			log.info(add);
			return new ResponseModel();
		}catch(Exception e){
			e.printStackTrace();
			log.error("修改"+SysRoleMenu.class.getName()+"对象出错！");
			return new ResponseModel(Const.CRUD_ERROR,e.getMessage());
		}
		
	}
	
	
}
