package com.dao;

import java.io.InputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.criterion.Criterion;

import com.common.page.Page;

/**
 * 封装的数据持久化层基类
 * 定义了一些列的数据访问接口
 * @author wangzhen
 * @version:V1.0
 * @param <T> DAO操作的对象类型
 * @param <ID> 主键类型
 * 2017年4月27日  
 */
public interface BaseDao<T,ID extends Serializable> {

	/**
	 * 该方法用于根据传入的id加载出当前（T）类型的对象
	 * 2017年4月28日
     * @param clazz 
	 * @param id 
	 * @return 主键为id的T对象
	 * author:wangzhen
	 */
	public T load(Class<T> clazz,ID id);
	
	/**
	 * 该方法用于持久化传入的对象
	 * 2017年4月27日
	 * @param model 
	 * author:wangzhen
	 */
	public ID save(T model) throws Exception;
	
	/**
	 * 该方法用于修改传入的对象
	 * 2017年4月27日
	 * @param model
	 * author:wangzhen
	 */
	public void update(T model) throws Exception;
	
	/**
	 * 该方法用于新增或修改传入的对象
	 * 有主键就执行更新，如果没有主键就执行插入
	 * 2017年4月28日
	 * @param id
	 * author:wangzhen
	 */
	public void saveOrUpdate(T model) throws Exception;
	
	/**
	 * 该方法用于删除传入的对象
	 * 2017年4月27日
	 * @param model
	 * author:wangzhen
	 */
	public void delete(T model) throws Exception;
	
	/**
	 * 该方法用于判断是否包含
	 * 2017年4月28日
	 * @param model
	 * @return
	 * author:wangzhen
	 */
	public boolean contains(T model) throws Exception;
	
	
	/**
	 * 将hibernate缓存中数据提交道数据库
	 * 2017年4月28日
	 * author:wangzhen
	 */
	public void flush() throws Exception;
	
	/**
	 * 清空hibernate中缓存
	 * 2017年4月28日
	 * author:wangzhen
	 */
	public void clear() throws Exception;
	
	/**
	 * 该方法用于执行hql命令语句
	 * 2017年4月28日
	 * @param hql
	 * @param para
	 * @throws Exception
	 * author:wangzhen
	 */
	public int executeHql(String hql,Object...para) throws Exception;
	/**
	 * 该方法用于执行sql命令语句
	 * 2017年4月28日
	 * @param sql
	 * @param para
	 * @throws Exception
	 * author:wangzhen
	 */
	public int executeSql(String sql,Object...para) throws Exception;
	/**
	 * 该方法用于查询传入的hql语句并返回一个List列表
	 * 2017年4月28日
	 * @param hql
	 * @param para
	 * @throws Exception
	 * author:wangzhen
	 */
	public List<?> getListByHql(String hql,Object...para) throws Exception;
	/**
	 * 该方法用于查询传入的sql语句并返回一个List列表
	 * 2017年4月28日
	 * @param sql
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
	 * 该方法用于查询传入的sql语句并根据分页参数返回Page对象，其中包含查询到的分页List列表
	 * 2017年4月28日
	 * @param sql
	 * @param para
	 * @throws Exception
	 * author:wangzhen
	 */
	public Page getPageListBySql(String sql,Page page,Object...para) throws Exception;
	
	/**
	 * 该方法用于查询传入的hql语句并根据分页参数返回Page对象，其中包含查询到的分页List列表
	 * 2017年4月28日
	 * @param sql
	 * @param para
	 * @throws Exception
	 * author:wangzhen
	 */
	public Page getPageListByHql(String hql,Page page,Object...para) throws Exception;
	

	/**
	 * 该方法用于获取数据库实体对象主键名称，如USER对象的主键userId
	 * 2017年6月22日
	 * @param clazz
	 * @return
	 * @throws Exception
	 * author:wangzhen
	 */
	public String getIdName(Class<T> clazz) throws Exception;
	

	/**
	 * 按Criteria查询对象列表
	 * 2017年6月22日
	 * @param clazz
	 * @param criterions 数量可变的Criterion
	 * @return
	 * @throws Exception
	 * author:wangzhen
	 */
	public List<T> getModelList(Class<T> clazz, final Criterion... criterions) throws Exception;
	
	/**
	 * 按Criteria查询唯一对象
	 * 2017年6月22日
	 * @param clazz
	 * @param criterions 按Criteria查询对象列表
	 * @return
	 * @throws Exception
	 * author:wangzhen
	 */
	public T getUniqueModel(Class<T> clazz, final Criterion... criterions) throws Exception;
	
	

	public int batchExecute() throws Exception;
	
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
	public List<?> getListWithLimitByHql(String hql,String start,String offset,Object...para) throws Exception;
	
	public InputStream getStream(ID id) throws SQLException;
	
}
