package com.service.system;

import java.util.ArrayList;
import java.util.Set;

import com.service.BaseService;



/**  
 *  系统首页service层
 * @author:wangzhen
 * @version:V1.0
 * 2017年4月27日  
 */
public interface IndexService extends BaseService{
	/**
	 * 获取当前用户所拥有的一级菜单即topMenu数据
	 * 2018年5月8日
	 * @param userId 当前登录用户
	 * author:wangzhen
	 */
	public String getTopMenuData(String userId,Set<String> menuSet);
	
	/**
	 * 根据一级菜单标识获取当前用户二级菜单权限数据
	 * 2018年5月9日
	 * @param userId
	 * @param menuId
	 * @return
	 * author:wangzhen
	 */
	public String getLeftMenuData(String userId,String menuId,Set<String> menuSet);
	
	//获取权限菜单
	public Set<String> getAuthMenu(String userId);
	
//	public Boolean isInAuth(String uri,Set<String> menuSet);

}
