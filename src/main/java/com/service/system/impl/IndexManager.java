package com.service.system.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.model.system.SysMenu;
import com.model.system.SysRoleMenu;
import com.model.system.SysUserRole;
import com.service.impl.BaseManager;
import com.service.system.IndexService;

/**  
 *  
 * @author:wangzhen
 * @version:V1.0
 * 2017年4月26日  
 */
@Service
public class IndexManager extends BaseManager implements IndexService{
	
	@Override
	public String getTopMenuData(String userId,Set<String> menuSet) {
		if(menuSet == null) return "[]";
		String hql = "select obj.menuId,obj.menuIcon,obj.menuTitle,obj.menuUrl  from SysMenu obj where obj.parent.menuId is null  and obj.menuType = 'M' order by obj.menuOrder asc";
		List topMenuList =  this.getListByHql(hql);
		String topMenuJson = "";
		topMenuJson +="[";
		if(CollectionUtils.isNotEmpty(topMenuList)){
			for (int i = 0; i < topMenuList.size(); i++) {
				Object[] menu = (Object[]) topMenuList.get(i);
				if(menuSet.contains(menu[0])){
					topMenuJson += "{\"menuId\":\""+menu[0]+"\", \"title\":\""+menu[2]+"\",\"icon\":\""+menu[1]+"\",\"url\":\""+menu[3]+"\"}";
					if(i<topMenuList.size()-1){
						topMenuJson +=",";
					}
				}
			}
		}
		topMenuJson +="]";
		return topMenuJson;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String getLeftMenuData(String userId, String menuId,Set<String> menuSet) {
		if(menuSet == null) return "[]";
		String hql = "select obj.menuId,obj.menuIcon,obj.menuTitle,obj.menuUrl from SysMenu obj where obj.parent.menuId = '"+menuId+"' and obj.menuType = 'M'  order by obj.menuOrder asc";
		List topMenuList =  this.getListByHql(hql);
		String topMenuJson = "";
		topMenuJson +="[";
		if(CollectionUtils.isNotEmpty(topMenuList)){
			for (int i = 0; i < topMenuList.size(); i++) {
				Object[] menu = (Object[]) topMenuList.get(i);
				if(menuSet.contains(menu[0])){
					topMenuJson += "{\"menuId\":\""+menu[0]+"\", \"title\":\""+menu[2]+"\",\"icon\":\""+menu[1]+"\",\"url\":\""+menu[3]+"\"";
					//左侧二级菜单
					String subHqlString = "select obj.menuId,obj.menuIcon,obj.menuTitle,obj.menuUrl from SysMenu obj where obj.parent.menuId = '"+menu[0]+"' and obj.menuType = 'M'  order by obj.menuOrder asc";
					List subMenuList =  this.getListByHql(subHqlString);
					if(CollectionUtils.isNotEmpty(subMenuList)){
						topMenuJson += ",\"children\":[";
						for(int j = 0;j<subMenuList.size();j++){
							Object[] sub = (Object[])subMenuList.get(j);
							topMenuJson += "{\"menuId\":\""+sub[0]+"\", \"title\":\""+sub[2]+"\",\"icon\":\""+sub[1]+"\",\"url\":\""+sub[3]+"\"}";
							if(j<subMenuList.size()-1){
								topMenuJson +=",";
							}
						}
						topMenuJson +="]";
					}
					topMenuJson += "}";
					if(i<topMenuList.size()-1){
						topMenuJson +=",";
					}
				}
			}
		}
		topMenuJson +="]";
		return topMenuJson;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<String> getAuthMenu(String userId) {
		List<SysUserRole> roleList = (List<SysUserRole>) this.getListByHql("from SysUserRole where sysUser.userId = ?", userId);
		Set<String> menuSet = new TreeSet<String>();
		if(CollectionUtils.isNotEmpty(roleList)){
			for (SysUserRole sysUserRole : roleList) {
				String roleId = sysUserRole.getSysRole().getRoleId();
				List<SysRoleMenu> menuList = (List<SysRoleMenu>) this.getListByHql("from SysRoleMenu obj where obj.sysRole.roleId = ?", roleId);
				for (SysRoleMenu sysRoleMenu : menuList) {
					menuSet.add(sysRoleMenu.getSysMenu().getMenuId());
				}
			}
		}
		return menuSet;
	}

//	@SuppressWarnings("unchecked")
//	@Override
//	public Boolean isInAuth(String uri,Set<String> menuSet) {
//		String[] uriArr = uri.split("/");
//		if(!ArrayUtils.isEmpty(uriArr)){
//			String endAction = uriArr[uriArr.length -1];
//			//判断是否是list结尾
//			Boolean isMenu = true;
//			log.info(endAction);
//			if(!endAction.equals("list")){
//				StringBuffer uriBuffer = new StringBuffer();
//				uriArr[uriArr.length -1] = "list";
//				for(int i = 0;i < uriArr.length;i++){
//					uriBuffer.append(uriArr[i]);
//					uriBuffer.append("/");
//				}
//				uri = uriBuffer.substring(0,uriBuffer.length()-1).toString();
//				isMenu = false;
//			}
//			List<SysMenu> menus = (List<SysMenu>) this.getListByHql("from SysMenu obj where obj.menuUrl = ? ", uri);
//			log.info(isMenu);
//			log.info(menus.size());
//			if(CollectionUtils.isNotEmpty(menus)){
//				for (SysMenu sysMenu : menus) {
//					if(isMenu){
//						if(!menuSet.contains(sysMenu.getMenuId())) return false;
//					}else{
//						SysMenu menu = (SysMenu) this.getUniqueByHql("from SysMenu obj where obj.parentId = ? and obj.buttonFunction = ?", new Object[]{sysMenu.getMenuId(),endAction});
//						if(menu != null && !menuSet.contains(menu.getMenuId())){
//							return false;
//						}
//					}
//				}
//			}
//		}
//		return true;
//	}

}
