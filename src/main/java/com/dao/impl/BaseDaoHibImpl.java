package com.dao.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.resource.cci.Record;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.jdbc.Work;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.common.log.SysLogger;
import com.common.page.Page;
import com.common.spring.BeanHelper;
import com.common.tld.getDictTag;
import com.dao.BaseDao;
import com.model.w2.W2Flowzp;

/**  
 * baseDao接口的hibernate实现
 * 通过Springle的@Transactional注解开启事务
 * @author:wangzhen
 * @version:V1.0
 * 2017年4月27日  
 */
@Repository("baseDao")
@Transactional
public class BaseDaoHibImpl<T,ID extends Serializable> implements BaseDao<T,ID>{

	/**
	 * hibernate4以后，spring取消了HibernateTemplate
	 * 所以不需要继承HibernateDaoSupport
	 * 获取session直接使用hibernate4原生api
	 */
	@Autowired
	protected SessionFactory sessionFactory;

	/**
	 * 获取session对象
	 * 需要开启事务，才能得到CurrentSession
	 * CurrentSession在动作执行完后会自动关闭
	 * 2017年4月28日
	 * @return
	 * author:wangzhen
	 */
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public T load(Class<T> clazz,ID id) {
		Assert.notNull(id, "id不能为空！");
		T t = (T) getSession().get(clazz,id);
		return t;
	}

	@Override
	@SuppressWarnings("unchecked")
	public ID save(T model) throws Exception {
		Assert.notNull(model, "model不能为空！");
		return (ID) getSession().save(model);
	}
	@Override
	public void saveOrUpdate(T model) throws Exception {
		Assert.notNull(model, "model不能为空！");
		getSession().saveOrUpdate(model);
	}
	@Override
	public void update(T model) throws Exception {
		Assert.notNull(model, "model不能为空！");
		getSession().update(model);
	}
	@Override
	public void delete(T model) throws Exception {
		Assert.notNull(model, "model不能为空！");
		getSession().delete(model);
	}
	@Override
	public boolean contains(T model) throws Exception {
		Assert.notNull(model, "model不能为空！");
		return getSession().contains(model);
	}
	
	@Override
	public void flush() throws Exception {
		 getSession().flush();
	}
	
	@Override
	public void clear() throws Exception {
		 getSession().clear();
	}

	@Override
	public int executeHql(String hql, Object... para) throws Exception {
		Assert.notNull(hql, "hql不能为空！");
		Query query = getSession().createQuery(hql);
		query = setQueryParameter(query,para);
        return query.executeUpdate();
	}

	@Override
	public int executeSql(String sql, Object... para) throws Exception {
		Assert.notNull(sql, "sql不能为空！");
		Query query = this.getSession().createSQLQuery(sql);
		query = setQueryParameter(query,para);
        return query.executeUpdate();
	}

	@Override
	public List<?> getListByHql(String hql, Object... para) throws Exception {
		Assert.notNull(hql, "hql不能为空！");
		Query query = this.getSession().createQuery(hql);
		query = setQueryParameter(query,para);
		return query.list();
	}

	@Override
	public List<?> getListBySql(String sql, Object... para) throws Exception {
		Assert.notNull(sql, "sql不能为空！");
		Query query = this.getSession().createSQLQuery(sql);
		query = setQueryParameter(query,para);
		return query.list();
	}
	
	@Override
	public Object getUniqueByHql(String hql, Object... para) throws Exception {
		Assert.notNull(hql, "hql不能为空！");
		Query query = this.getSession().createQuery(hql);
		query = setQueryParameter(query,para);
		return  query.uniqueResult();
	}
	
	

	@Override
	public Page getPageListBySql(String sql, Page page, Object... para) throws Exception {
		Assert.notNull(sql, "sql不能为空！");
		Query query = this.getSession().createSQLQuery(sql);
		Assert.notNull(null,"----------");
		query = setQueryParameter(query,para);
        List<?> list = getListBySql(Page.getCountSql(sql), para);
		page.setRowSize(CollectionUtils.isNotEmpty(list)?((BigDecimal)list.get(0)).longValue():0);
        query = setPageParameter(query, page);
        List<?> pageResult = query.list();
		page.setData(pageResult);
		return page;
	}

	@Override
	public Page getPageListByHql(String hql, Page page, Object... para) throws Exception {
		Assert.notNull(hql, "hql不能为空！");
		Query query = this.getSession().createQuery(hql);
		query = setQueryParameter(query, para);
		if(hql.contains("group by") || hql.contains("GROUP BY")){
			page.setRowSize((long)query.list().size());
		}else{
			page.setRowSize((long)getUniqueByHql(Page.getCountSql(hql), para));
		}
        query = setPageParameter(query, page);
        List<?> pageResult = query.list();
        
        String[] header = query.getReturnAliases();
		page.setData(pageResult);
		page.setHeader(header);
		return page;
	}

	@Override
	public String getIdName(Class<T> clazz) throws Exception {
		ClassMetadata meta = sessionFactory.getClassMetadata(clazz);
		return meta.getIdentifierPropertyName();
	}
	

	@SuppressWarnings("all")
	public List<T> getModelList(Class<T> clazz, final Criterion... criterions) throws Exception {
		return createpCriteria(clazz, criterions).list();
	}
	
	@Override
	@SuppressWarnings("all")
	public T getUniqueModel(Class<T> clazz, Criterion... criterions) throws Exception {
		return (T)createpCriteria(clazz, criterions).uniqueResult();
	}
	
	
	
	
	/**
	 * 设置分页参数（数据在结果集的开始位置和每页条数）到Query对象
	 * 辅助函数
	 * 2017年6月21日
	 * @param query
	 * @param page
	 * @return
	 * author:wangzhen
	 */
	protected Query setPageParameter(final Query query, final Page page) {
		query.setFirstResult(page.getFirstResult());
		query.setMaxResults(page.getLimit());
		return query;	
	}
	
	
	/**
	 * 该方法用于设置query的查询参数
	 * 2017年8月16日
	 * @param query
	 * @param para 可变长度参数
	 * @return
	 * author:wangzhen
	 */
	protected Query setQueryParameter(Query query,Object... para ){
		 if (para != null){
	        for (int i = 0; i < para.length; i++){
	            query.setParameter(i, para[i]);
	        }
	     }
		 return query;
	}

	/**
	 * 设置query的limit参数
	 * 2019年2月22日
	 * @param query
	 * @param start
	 * @param offset
	 * @return
	 * @author:Lynn
	 */
	protected Query setLimitParameter(Query query,String start,String offset){
		int min = 0,record = 0;
		if(StringUtils.isNotEmpty(start)){
			min = Integer.parseInt(start);
		}
		if(StringUtils.isNotEmpty(offset)){
			record = Integer.parseInt(offset);
		}
		query.setFirstResult(min);
		query.setMaxResults(min+record);
		return query;
	}
	
	/**
	 * 该方法用于获取Criteria查询对象，并设置查询条件
	 * 2017年6月22日
	 * @param clazz
	 * @param criterions 数量可变的Criterion
	 * @return
	 * author:wangzhen
	 */
	@SuppressWarnings("all")
	protected Criteria createpCriteria(Class clazz,Criterion... criterions ){
		Criteria criteria = getSession().createCriteria(clazz);
		for (Criterion criterion :criterions) {
			criteria.add(criterion);
		}
		return criteria;
	}

	@Override
	public int batchExecute() throws Exception {
		// TODO Auto-generated method stub
	
		Session session = getSession();
		session.doWork(new Work() {
			
			@Override
			public void execute(Connection connection) throws SQLException {
				// TODO Auto-generated method stub
				
			}
		});
		return 0;
	}

	@Override
	public List<?> getListWithLimitByHql(String hql, String start,
			String offset, Object... para) throws Exception {
		Assert.notNull(hql, "hql不能为空！");
		Query query = this.getSession().createQuery(hql);
		query = setQueryParameter(query, para);
		query = setLimitParameter(query, start, offset);
        return query.list();
	}

	
	@SuppressWarnings("all")
	@Override
	public InputStream getStream(ID id) throws SQLException{
		SessionFactory sessionFactory = BeanHelper.getBean("sessionFactory");
		W2Flowzp flowzp = (W2Flowzp) getSession().get(W2Flowzp.class, id);
		
//		if(flowzp != null){
//			try {
//				return flowzp.getImg().getBinaryStream();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
		return null;
		
	}
}
