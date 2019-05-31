package com.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;


import java.util.Set;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import com.common.page.Page;
import com.model.BaseModel;

/**  
 *  
 * @author:wangzhen
 * @version:V1.0
 * 2017年4月27日  
 */
public interface BasePkNumService {
	 /**
	  * 执行保存方法前会执行该方法
	  * 2017年4月27日
	  * @param baseModel
	  * author:wangzhen
	  */
	 public void beforeSaveModel(BaseModel baseModel)throws Exception;
	 /**
	  * 该方法用于持久化传入的对象
	  * 2017年4月27日
	  * @param baseModel 新增的实体
	  * author:wangzhen
	  */
	 public Integer saveModel(BaseModel baseModel)throws Exception;
	 /**
	  * 执行保存方法后会执行该方法
	  * 2017年4月27日
	  * @param baseModel
	  * author:wangzhen
	  */
	 public void afterSaveModel(BaseModel baseModel)throws Exception;
	 
	 /**
	  * 执行修改方法前会执行该方法
	  * 2017年4月27日
	  * @param baseModel
	  * author:wangzhen
	  */
	 public void beforeUpdateModel(BaseModel baseModel)throws Exception;
	 
	 /**
	  * 该方法用于修改传入的对象
	  * 2017年4月27日
	  * @param baseModel 更新的实体
	  * author:wangzhen
	  */
	 public void updateModel(BaseModel baseModel)throws Exception;
	 
	 /**
	  * 执行修改方法后会执行该方法
	  * 2017年4月27日
	  * @param baseModel
	  * author:wangzhen
	  */
	 public void afterUpdateModel(BaseModel baseModel)throws Exception;
	 
	 /**
	  * 执行删除方法之前执行该方法
	  * 2019年1月23日
	  * @param id
	  * @author:Lynn
	  */
	 public boolean beforeDeleteModel(Integer id) throws Exception;
	 
	 /**
	  * 该方法用于删除传入的对象
	  * 2017年4月27日
	  * @param baseModel 删除的实体
	  * author:wangzhen
	  */
	 public void deleteModel(BaseModel baseModel) throws Exception;
	 
	 /**
	  * 执行删除方法之后执行该方法
	  * 2019年1月23日
	  * @param id
	  * @author:Lynn
	  */
	 public void afterDeleteModel(Integer id) throws Exception;
	 
	 /**
	  * 该方法用于根据传入的对象calss和主键id删除相应的对象
	  * 2017年6月22日
	  * @param clazz
	  * @param id
	  * author:wangzhen
	  */
	 public void deleteModelById(Class<? extends BaseModel> clazz,Integer id) throws Exception;
	 
	 /**
	  * 该方法用于根据传入的对象calss和主键id数组删除相应的对象
	  * 2017年6月22日
	  * @param clazz
	  * @param id
	  * author:wangzhen
	  */
	 public void deleteModelByIds(Class<? extends BaseModel> clazz,Integer[] ids);
	 
	 /**
	 * 该方法用于根据传入的id加载出当前（T）类型的对象
	 * 2017年4月28日
	 * @param id
	 * author:wangzhen
	 */
	 public BaseModel loadModel(Class<? extends BaseModel> clazz,Integer id);
	 
	 
	 /**
	  * 该方法用户获取传入类型的全部对象列表
	  * 2017年6月22日
	  * @param clazz
	  * author:wangzhen
	  */
	 public List<? extends BaseModel> getAllModel(Class<? extends BaseModel> clazz);
	 
	 /**
	  * 该方法是根据传入属性map键值对的获取当前类型的对象列表
	  * 2017年6月22日
	  * @param clazz
	  * @param map
	  * @return
	  * author:wangzhen
	  */
	 public List<? extends BaseModel> getModelByMap(Class<? extends BaseModel> clazz,Map<String,Object> map);
	 /**
      * 该方法用于根据传入属性的获取当前类型的对象列表
	  * 匹配方式为相等
	  * 2017年6月22日
	  * @param clazz
	  * @param propertyName
	  * @param propertyValue
	  * @return
	  * author:wangzhen
	  */
	 public List<? extends BaseModel> getModelByProperty(Class<? extends BaseModel> clazz, String propertyName,Object propertyValue);
	 
	 /**
	  * 该方法用于根据传入的主键数组获取相应的对象列表
	  * 2017年6月22日
	  * @param clazz
	  * @param ids
	  * @return
	  * @throws Exception
	  * author:wangzhen
	  */
	 public List<BaseModel> getModelByIds(Class<? extends BaseModel> clazz,Integer[] ids) throws Exception;
	 /**
	  * 该方法用于新增或修改传入的对象
	  * 有主键就执行更新(数据库中有)，如果没有主键就执行插入
	  * 2017年4月28日
	  * @param baseModel
	  * author:wangzhen
	  */
	 public void saveOrUpdateModel(BaseModel baseModel);
	 
	 /**
	  * 该方法用于判断是否包含
	  * 2017年4月28日
	  * @param baseModel
	  * @return
	  * author:wangzhen
	  */

	 public boolean containsModel(BaseModel baseModel);
	 
	/**
	 * 该方法用于执行hql命令语句
	 * 2017年4月28日
	 * @param hql
	 * @param para
	 * @throws Exception
	 * author:wangzhen
	 */
	public void executeHql(String hql,Object...para) throws Exception;
	
	/**
	 * 该方法用于执行sql命令语句
	 * 2017年4月28日
	 * @param sql
	 * @param para
	 * @throws Exception
	 * author:wangzhen
	 */
	public void executeSql(String sql,Object...para) throws Exception;
	
	
	/**
	 * 该方法用于执行传入的hql语句，并返回实体对象列表
	 * 2017年4月28日
	 * @param hql
	 * @param para
	 * @throws Exception
	 * author:wangzhen
	 */
	public List<?> getListByHql(String hql,Object...para) throws Exception;
	
	/**
	 * 该方法用于执行传入的sql语句，并返回查询结果列表
	 * 2017年4月28日
	 * @param hql
	 * @param para
	 * @throws Exception
	 * author:wangzhen
	 */
	public List<?> getListBySql(String sql,Object...para) throws Exception;
	
	/**
	 * 该方法用于对传入hql进行查询，并返回唯一对象
	 * 当能够确定数据库中根据查询条件只会返回唯一结果时使用
	 * 2017年6月21日
	 * @param hql
	 * @param para
	 * @return
	 * @throws Exception
	 * author:wangzhen
	 */
	public Object getUniqueByHql(String hql,Object...para) throws Exception;
	
	 /**
	  * 该方法用于根据传入的对象属性和属性值获取唯一对象
	  * 属性和值匹配方式为相等
	  * 2017年6月22日
	  * @param clazz
	  * @param propertyName
	  * @param propertyValue
	  * @return
	  * author:wangzhen
	  */
	 public BaseModel getUniqueByProperty(Class<? extends BaseModel> clazz, String propertyName,Object propertyValue);
	 
	 /**
	  * 该方法是根据传入属性map键值对的获取唯一对象
	  * 2017年6月22日
	  * @param clazz
	  * @param map
	  * @return
	  * author:wangzhen
	  */
	 public BaseModel getUniqueByMap(Class<? extends BaseModel> clazz,Map<String,Object> map);
	 
	 /**
		 * 该方法用于执行传入的sql语句，并返回第一个查询结果
		 * 2017年4月28日
		 * @param hql
		 * @param para
		 * @throws Exception
		 * author:wangzhen
		 */
	 public BaseModel getOneBySql(String sql,Object...para) throws Exception;
	 
	 /**
		 * 该方法用于执行传入的hql语句，并返回第一个实体对象
		 * 2017年4月28日
		 * @param hql
		 * @param para
		 * @throws Exception
		 * author:wangzhen
		 */
	 public BaseModel getOneByHql(String hql,Object...para) throws Exception;
	 
	 /**
	  * 该方法用于根据传入的对象属性和属性值获取唯一对象
	  * 属性和值匹配方式为相等
	  * 2017年6月22日
	  * @param clazz
	  * @param propertyName
	  * @param propertyValue
	  * @return
	  * author:wangzhen
	  */
	 public BaseModel getOneByProperty(Class<? extends BaseModel> clazz, String propertyName,Object propertyValue);
	 
	 /**
	  * 该方法是根据传入属性map键值对的获取唯一对象
	  * 2017年6月22日
	  * @param clazz
	  * @param map
	  * @return
	  * author:wangzhen
	  */
	 public BaseModel getOneByMap(Class<? extends BaseModel> clazz,Map<String,Object> map);
	 
	 /**
	  * 该方法用于查询传入的sql语句并根据分页参数返回Page对象，其中包含查询到的分页List列表
	  * 2017年4月28日
	  * @param sql
	  * @param page
	  * @param para
	  * @return
	  * @throws Exception
	  * author:wangzhen
	  */
	public Page getPageListBySql(String sql,Page page,Object...para) throws Exception;
	
	/**
	 * 该方法用于查询传入的hql语句并根据分页参数返回Page对象，其中包含查询到的分页List列表
	 * 2017年4月28日
	 * @param hql
	 * @param page
	 * @param para
	 * @return
	 * @throws Exception
	 * author:wangzhen
	 */
	public Page getPageListByHql(String hql,Page page,Object...para) throws Exception;
	
	/**
	 * crud程序显示列表时默认调用的sql集合
	 * 集合中包括sql语句以及sql语句的参数
	 * 2017年9月11日
	 * @return
	 * author:wangzhen
	 */
	public Map<String, Object> getSqlMap(ModelMap map);
	
	/**
	 * 该方法用于获取当前登录用户拥有某功能的权限数据，如新增、编辑、删除等
	 * 2018年5月31日
	 * @param userId
	 * @param menuSet
	 * @param mv
	 * @return
	 * author:wangzhen
	 */
	public ModelAndView getUserAuth(String userId,Set<String> menuSet,ModelAndView mv);
	
	/**
	 * 该方法用户获取当前登录用户配置参数、个性化设置等信息，如：系统主题皮肤等
	 * 2018年9月8日
	 * @param userId
	 * @return
	 * author:wangzhen
	 */
	public Map<String, Object> initUserConfig(String userId);
	
	/**
	 * 判断是否在权限中
	 * 2019年1月29日
	 * @param uri
	 * @param menuSet
	 * @return
	 * @author:Lynn
	 */
	public Map<String, String> isInAuth(String uri,Set<String> menuSet);
	
	/**
	 * 该方法用于查询传入的hql语句并返回一个List列表,并启用limit限制
	 * 2019年2月22日
	 * @param hql
	 * @param start
	 * @param offset
	 * @param para
	 * @return
	 * @throws Exception
	 * @author:Lynn
	 */
	public List<?> getListWithLimitByHql(String hql,String start,String offset,Object...para);
	
	public InputStream getInputStream(Integer id);
}
