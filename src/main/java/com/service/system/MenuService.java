package com.service.system;

import java.util.List;

import org.springframework.ui.ModelMap;




import com.common.response.ResponseModel;
import com.common.tree.Node;
import com.service.BaseService;



/**  
 *  
 * @author:wangzhen
 * @version:V1.0
 * 2017年4月27日  
 */
public interface MenuService extends BaseService{
	//删除菜单
	public ResponseModel deleteMenu(String menuId);
	
	/**
	 * 获取菜单树形结构
	 * 2019年2月18日
	 * @param map
	 * @return
	 * @author:Lynn
	 */
	public List<Node> getTreeData(ModelMap map);

}
