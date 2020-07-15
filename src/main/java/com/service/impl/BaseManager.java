package com.service.impl;

import java.awt.MenuShortcut;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.json.JsonObject;
import javax.persistence.Id;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Request;

import junit.framework.Test;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.common.log.SysLogger;
import com.common.page.Page;
import com.common.spring.BeanHelper;
import com.common.utils.Const;
import com.common.utils.StringUtil;
import com.common.utils.TableUtil;
import com.dao.BaseDao;
import com.model.BaseModel;
import com.model.system.SysLog;
import com.model.system.SysMenu;
import com.model.system.SysUserConfig;
import com.service.BaseService;


/**  
 *  
 * @author:wangzhen
 * @version:V1.0
 * 2017年4月27日  
 */
@Service("service")
public class BaseManager implements BaseService{
	
	protected SysLogger log = new SysLogger(this);
	@Autowired
	protected BaseDao<BaseModel, String> baseDao;
	@Autowired
	protected BaseDao<BaseModel, Integer> baseDaoInt;

	
	public BaseManager(){
		
	}
	@Override
	public void beforeSaveModel(BaseModel baseModel) throws Exception{

	}
	
	@Override
	public String saveModel(BaseModel baseModel) throws Exception{
			baseModel.setCreateTime(new Date());
			baseModel.setModifyTime(new Date());
			String id = String.valueOf(baseDao.save(baseModel));
			return id;
	}
	@Override
	public void afterSaveModel(BaseModel baseModel) throws Exception {
		
	}
	@Override
	public void beforeUpdateModel(BaseModel baseModel) throws Exception{
		
	}
	@Override
	public void updateModel(BaseModel baseModel) throws Exception{
		baseModel.setModifyTime(new Date());
		baseDao.update(baseModel);
	}
	
	@Override
	public void afterUpdateModel(BaseModel baseModel) throws Exception{
		
	}
	
	@Override
	public void deleteModel(BaseModel baseModel) throws Exception{
		baseDao.delete(baseModel);
	}
	
	@Override
	public void deleteModelById(Class<? extends BaseModel> clazz,String id) throws Exception{
			deleteModel(loadModel(clazz,id));
	}
	public void deleteModelById(Class<? extends BaseModel> clazz,Integer id) throws Exception{
		deleteModel(loadModel(clazz,id));
	}
	
	@Override
	public void deleteModelByIds(Class<? extends BaseModel> clazz,String[] ids) {
		try {
			for (String id:ids) {
				deleteModel(loadModel(clazz,id));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	@SuppressWarnings("all")
	public  BaseModel loadModel(Class clazz,String id) {
		try {
			
			return baseDao.load(clazz,id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@SuppressWarnings("all")
	public  BaseModel loadModel(Class clazz,Integer id) {
		try {
			
			return baseDaoInt.load(clazz,id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public void saveOrUpdateModel(BaseModel baseModel) {
		try {
			baseModel.setModifyTime(new Date());
			baseDao.saveOrUpdate(baseModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean containsModel(BaseModel baseModel) {
		try {
			return baseDao.contains(baseModel);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public void executeHql(String hql, Object... para) {
		try {
			 baseDao.executeHql(hql, para);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void executeSql(String sql, Object... para) {
		try {
			 baseDao.executeSql(sql, para);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public List<?> getListByHql(String hql, Object... para){
		try {
			return baseDao.getListByHql(hql, para);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public List<?> getListBySql(String sql, Object... para){
		try {
			return baseDao.getListBySql(sql, para);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public Object getUniqueByHql(String hql, Object... para){
		try {
			return baseDao.getUniqueByHql(hql, para);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	@SuppressWarnings("all")
	public BaseModel getUniqueByProperty(Class clazz, String propertyName,Object propertyValue) {
		try {
			 Criterion criterion = Restrictions.eq(propertyName, propertyValue);
			 return baseDao.getUniqueModel(clazz, criterion);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public Page getPageListBySql(String sql, Page page, Object... para){
		try {
			return baseDao.getPageListBySql(sql, page, para);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public Page getPageListByHql(String sql, Page page, Object... para){
		try {
			return baseDao.getPageListByHql(sql, page, para);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	@SuppressWarnings("all")
	public List<BaseModel> getAllModel(Class clazz) {
		try {
			 return baseDao.getModelList(clazz);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	@Override
	@SuppressWarnings("all")
	public List<? extends BaseModel> getModelByProperty(Class clazz,String propertyName, Object propertyValue) {
		try {
			 Criterion criterion = Restrictions.eq(propertyName, propertyValue);
			 return baseDao.getModelList(clazz,criterion);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	

	
	@Override
	@SuppressWarnings("all")
	public List<BaseModel> getModelByIds(Class clazz,String[] ids) {
		try {
			Criterion criterion = Restrictions.in(baseDao.getIdName(clazz), ids);
			baseDao.getModelList(clazz,criterion);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@SuppressWarnings("all")
	public List<? extends BaseModel> getModelByMap(Class clazz,Map map) {
		try {
		  Criterion[] criterions = new Criterion[map.size()];
		  int i=0;
		  for (Object key : map.keySet()) {
			  Criterion criterion = Restrictions.eq((String)key, map.get(key));
			  criterions[i]=criterion;
			  i++;
		  }
		 return baseDao.getModelList(clazz,criterions);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@SuppressWarnings("all")
	public BaseModel getUniqueByMap(Class clazz,Map map) {
		try {
		  Criterion[] criterions = new Criterion[map.size()];
		  int i=0;
		  for (Object key : map.keySet()) {
			  Criterion criterion = Restrictions.eq((String)key, map.get(key));
			  criterions[i]=criterion;
			  i++;
		  }
		 return baseDao.getUniqueModel(clazz,criterions);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return null;
		}
	}
	

	@Override
	public Map<String, Object> getSqlMap(ModelMap map){
		log.info("使用crud程序默认显示列表时，需重写该方法获取列表sql");
		return null;
	}
	@Override
	public ModelAndView getUserAuth(String userId,Set<String> menuSet, ModelAndView mv) {
		try {

			String uri = mv.getModelMap().get("servletURI").toString();
			uri = StringUtil.romoveUrlPara(uri);
			
			StringBuffer authHql = new StringBuffer();
			authHql.append(" select obj.menuId,obj.menuIcon,obj.menuTitle,obj.menuCode,obj.buttonFunction,obj.buttonType,obj.buttonCss ");
			authHql.append(" from SysMenu obj left join obj.parent ");
			authHql.append(" where obj.parent.menuUrl like '%"+uri+"%' ");
			authHql.append(" and obj.menuType='B' ");
			authHql.append(" order by obj.menuOrder asc ");
			List butsList =  this.getListByHql(authHql.toString());
			
			String butJson = "[";
			String barJson = "[";
			
			if(CollectionUtils.isNotEmpty(butsList)){
				for (int i = 0; i < butsList.size(); i++) {
					Object[] but = (Object[]) butsList.get(i);
					if(menuSet.contains(but[0])){
						if(but[5]!=null&&but[5].toString().equals("1")){
							butJson += "{";
							butJson += "id:'"+but[0]+"',icon:\""+but[1]+"\",name:'"+but[2]+"',code:'"+but[3]+"',event:'"+StringUtil.convertNull(but[4]).trim()+"'";
							butJson += "},";
							
						}else if(but[5]!=null&&but[5].toString().equals("9")){
							barJson += "{";
							barJson += "id:'"+but[0]+"',icon:\""+but[1]+"\",name:'"+but[2]+"',code:'"+but[3]+"',event:'"+StringUtil.convertNull(but[4]).trim()+"',css:'"+StringUtil.convertNull(but[6])+"'";
							barJson += "},";
						}
					}
				}
			}
			butJson += "]";
			barJson += "]";
			
			//获取tab列
			SysMenu menu = (SysMenu)getUniqueByHql("from SysMenu where menuUrl like '%"+uri+"%' and menuType = ?","T");
			JSONArray json = new JSONArray();
			if(menu != null){
				String parentId = menu.getParentId();
				List<SysMenu> menuList = (List<SysMenu>)this.getListByHql("from SysMenu where parentId = ? and menuType = ? order by menuOrder asc", new Object[]{parentId,"T"});
				if(CollectionUtils.isNotEmpty(menuList)){
					JSONObject jo = new JSONObject();
					for (SysMenu sm : menuList) {
						if(menuSet.contains(sm.getMenuId())){
							jo.put("name", sm.getMenuTitle());
							jo.put("url", sm.getMenuUrl());
							json.add(jo);
						}
					}
				}
			}
			
			JSONArray listButsJson = JSONArray.fromObject(butJson);
			JSONArray barButsJson = JSONArray.fromObject(barJson);
			mv.addObject(Const.LIST_AUTH_BUTS, listButsJson);
			mv.addObject(Const.BAR_AUTH_BUTS, barButsJson);
			mv.addObject(Const.LIST_AUTH_TABS,json.toString());
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getMessage());
			}
		
		
		return mv;
	}
	
	@Override
	@SuppressWarnings("all")
	public Map<String, Object> initUserConfig(String userId) {
	    Map<String, Object> userConfigMap = new HashMap<String, Object>();
		List<SysUserConfig> userConfigList = (List<SysUserConfig>) this.getModelByProperty(SysUserConfig.class, "userId", userId);
		if(CollectionUtils.isNotEmpty(userConfigList)){
			for (SysUserConfig userConfig : userConfigList) {
				Object val = userConfigMap.get(userConfig.getConfigType());
				if(val!=null&&StringUtils.isNotEmpty(val.toString())){
					val += "$";
				}else {
					val = "";
				}
				userConfigMap.put(userConfig.getConfigType(),val+userConfig.getConfigValue());
			}
		}
		return userConfigMap;
	}
	
	@Override
	public boolean beforeDeleteModel(String id) throws Exception {
		return true;
	}
	public boolean beforeDeleteModel(Integer id) throws Exception {
		return true;
	}
	
	@Override
	public void afterDeleteModel(String id) throws Exception {
	}
	public void afterDeleteModel(Integer id) throws Exception {
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Map<String, String> isInAuth(String uri, Set<String> menuSet) {
		Boolean isAuth = true;
		String menuId = "";
		if(!StringUtils.isEmpty(uri)){
			List<SysMenu> menuList = (List<SysMenu>) getListByHql("from SysMenu obj where obj.menuUrl = ? ", uri);
			//判断是否存在该uri的菜单
			if(CollectionUtils.isNotEmpty(menuList)){
				for(int i = 0; i < menuList.size(); i ++){
					SysMenu menu = menuList.get(i);
					menuId = menu.getMenuId();
					if(menuSet.contains(menu.getMenuId())){
						isAuth = true;
						break;
					}else{
						isAuth = false;
					}
				}
			}else{
				String endAction = uri.substring(uri.lastIndexOf("/")+1,uri.length());
				uri = uri.substring(0,uri.lastIndexOf("/"));
				List<SysMenu> parentMenus = (List<SysMenu>) getListByHql("from SysMenu obj where obj.menuUrl like  ? ", "%"+uri+"%");
				if(CollectionUtils.isNotEmpty(parentMenus)){
					if(parentMenus.size() == 1){
						SysMenu menu = parentMenus.get(0);
						menuId = menu.getMenuId();
						if(!menuSet.contains(menuId)){
							isAuth = false;
						}
					}else{
						for(int i = 0; i < parentMenus.size(); i ++){
							SysMenu menu = parentMenus.get(i);
							SysMenu sysMenu = (SysMenu) getUniqueByHql("from SysMenu obj where obj.parentId = ? and obj.buttonFunction = ? ", new Object[]{menu.getMenuId(),endAction});
							if(sysMenu != null){
								menuId = sysMenu.getMenuId();
								if(menuSet.contains(sysMenu.getMenuId())){
									isAuth = true;
									break;
								}else{
									isAuth = false;
								}
							}
						}
					}
					
				}
			}
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("status", String.valueOf(isAuth));
		map.put("menuId", menuId);
		return map;
	}
	
	@Override
	public List<?> getListWithLimitByHql(String hql, String start,
			String offset, Object... para){
		try {
			return baseDao.getListWithLimitByHql(hql, start, offset, para);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public BaseModel getOneBySql(String sql, Object... para) {
		try{
			List<?> list = baseDao.getListBySql(sql, para);
			if(CollectionUtils.isNotEmpty(list)) return (BaseModel)list.get(0);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	@Override
	public BaseModel getOneByHql(String hql, Object... para) {
		try{
			List<?> list = baseDao.getListByHql(hql, para);
			if(CollectionUtils.isNotEmpty(list)) return (BaseModel)list.get(0);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	@Override
	@SuppressWarnings("all")
	public BaseModel getOneByProperty(Class clazz,String propertyName, Object propertyValue) {
		try {
			 Criterion criterion = Restrictions.eq(propertyName, propertyValue);
			 List<? extends BaseModel> list = baseDao.getModelList(clazz,criterion);
			 if(CollectionUtils.isNotEmpty(list)) return list.get(0);
			 return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	@SuppressWarnings("all")
	public BaseModel getOneByMap(Class clazz,Map map) {
		try {
			  Criterion[] criterions = new Criterion[map.size()];
			  int i=0;
			  for (Object key : map.keySet()) {
				  Criterion criterion = Restrictions.eq((String)key, map.get(key));
				  criterions[i]=criterion;
				  i++;
			  }
			  List<? extends BaseModel> list = baseDao.getModelList(clazz,criterions);
				 if(CollectionUtils.isNotEmpty(list)) return list.get(0);
				 return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public StringBuffer getSelectHql(String tableName){
		StringBuffer hql = new StringBuffer();
		hql.append("select"+TableUtil.getSelect(tableName));
		hql.append(" from "+tableName+" obj  where 1=1  ");
		return hql;
	}
	@Override
	public Class<?> getPkType(Class<? extends BaseModel> clazz) {
		Method[] methods = clazz.getDeclaredMethods();
		for(Method method:methods){
			if(method.isAnnotationPresent(Id.class)){
				return method.getReturnType();
					
			}
		}
		return null;
	}
	
}
