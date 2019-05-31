package com.service.system.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.common.response.ResponseModel;
import com.common.tree.Node;
import com.common.utils.Const;
import com.common.utils.Search;
import com.model.BaseModel;
import com.model.system.SysMenu;
import com.model.system.SysUser;
import com.service.impl.BaseManager;
import com.service.system.MenuService;

/**  
 *  
 * @author:wangzhen
 * @version:V1.0
 * 2017年4月26日  
 */
@Service
public class MenuManager extends BaseManager implements MenuService{
	
	@Override
	public Map<String, Object> getSqlMap(ModelMap map) {
		Map<String, Object> sqlMap = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append(" select obj.menuId ,obj.menuIcon as 图标,obj.menuTitle as 菜单名称,  ");
		hql.append(" obj.parent.menuTitle as 上级菜单, ");
		hql.append(" obj.businessType as 业务类别,obj.menuType as 菜单类型,obj.logLevel as 日志级别,obj.menuUrl as 菜单地址 ,obj.menuOrder as 菜单排序");
		hql.append(" from SysMenu obj left join obj.parent where 1=1");
		
		Map<String,Object> where = Search.where(hql, map);
		hql = (StringBuffer)where.get("hql");
		List<Object> para = (List<Object>) where.get("para");
		if(map.get("parentId")!=null && StringUtils.isNotEmpty(map.get("parentId").toString())){
			hql.append(" and (obj.parent.menuId = ? or obj.menuId = ?) ");
			para.add(map.get("parentId").toString());
			para.add(map.get("parentId").toString());
		}
		
		hql = Search.getOrder(hql,"obj.menuOrder");
		
		
		sqlMap.put(Const.PAGE_SQL, hql.toString());
		sqlMap.put(Const.SQL_PARA, para);
		return sqlMap;
	}

	@Override
	public void beforeSaveModel(BaseModel baseModel) throws Exception {
		
		SysMenu menu = (SysMenu)baseModel;
		if(StringUtils.isEmpty(menu.getParentId())){
			menu.setParentId(null);
		}
		super.beforeSaveModel(baseModel);
	}

	@Override
	public void beforeUpdateModel(BaseModel baseModel) throws Exception {
		SysMenu menu = (SysMenu)baseModel;
		if(StringUtils.isEmpty(menu.getParentId())){
			menu.setParentId(null);
		}
		super.beforeSaveModel(baseModel);
	}

	@Override
	public ResponseModel deleteMenu(String menuId) {
		try {
			//删除角色菜单（联合表）
			baseDao.executeHql("delete from SysRoleMenu obj where obj.sysMenu.menuId = ?", menuId);
			//删除角色
			this.deleteModelById(SysMenu.class, menuId);
			return new ResponseModel();
		} catch (Exception e) {
			return new ResponseModel(Const.CRUD_ERROR,e.getMessage());
		}
	}

	@Override
	public List<Node> getTreeData(ModelMap map) {
		String hql = "from SysMenu obj where obj.parent.menuId is null or obj.parent.menuId = '' order by obj.menuOrder asc";
		List<Node> datalList = new ArrayList<Node>();
		List<?> rootMenuList = this.getListByHql(hql);
		if(CollectionUtils.isNotEmpty(rootMenuList)){
			for(int i = 0; i < rootMenuList.size(); i++){
				SysMenu menu = (SysMenu)rootMenuList.get(i);
				Node node = new Node();
				node.setId(menu.getMenuId());
				node.setTitle(menu.getMenuTitle());
				node.setParentId(StringUtils.isNotEmpty(menu.getParentId())?menu.getParentId():"0");
				node = loadNode(menu,node);
				datalList.add(node);
			}
		}
		return datalList;
	}
	
	/**
	 * 获取节点树形全部数据
	 * 2019年2月18日
	 * @param menu
	 * @param node
	 * @return
	 * @author:Lynn
	 */
	public Node loadNode(SysMenu menu,Node node){
		if(CollectionUtils.isNotEmpty(menu.getChildren())){
			List<Node> childrenNode = new ArrayList<Node>();
			for(SysMenu children:menu.getChildren()){
				Node cNode = new Node();
				cNode.setId(children.getMenuId());
				cNode.setTitle(children.getMenuTitle());
				cNode.setParentId(children.getParentId());
				cNode = loadNode(children, cNode);
				childrenNode.add(cNode);
			}
			node.setChildren(childrenNode);
		}
		return node;
	}
	

	
	

}
