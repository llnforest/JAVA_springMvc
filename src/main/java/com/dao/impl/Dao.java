/**
 *
 * Dao.java
 * 2019年1月10日
 * author:Lynn
 */
package com.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.common.log.SysLogger;
import com.common.spring.BeanHelper;
import com.common.utils.ConvertUtil;


public  class Dao {
	
	public static Builder model(String modelname){
		return new Builder(modelname);
	}
	
	public static class Builder{
		
		protected SysLogger logger = new SysLogger(this);
		
		private String model;
		
		private Boolean transaction = false;
		
		private Session session;
		
		private Transaction tx;
		
		private String hql = "";
		
		
		private String field = "";
		
		private String where;
		
		private String order = "";
		
		private String set = "";
		
		public Builder(){};
		
		/**
		 * model名称
		 * @param String model
		 * @return
		 */
		public Builder(String model){
			this.model = model;
		}
		
		/**
		 * 开启session和事务
		 */
		private Session getSession(){
			if(session == null){
				SessionFactory sessionFactory = BeanHelper.getBean("sessionFactory");
				logger.info(sessionFactory);
				session = sessionFactory.openSession();
				tx = session.beginTransaction();
			}
			logger.info(session);
			return session;
		}
		
		/**
		 * 关闭session 和提交事务
		 */
		public void commit(){
			tx.commit();
			session.close();
		}
		
		
		/**
		 * 事务控制
		 * @param Boolean transaction
		 * @return
		 */
		public Builder transaction(Boolean transaction){
			this.transaction = transaction;
			return this;
		}
		
		/**
		 * 回滚
		 */
		public void rollback(){
			tx.rollback();
			session.close();
		}
		
		/**
		 * 判断条件
		 * @param String where
		 * @return
		 */
		public Builder where(String where){
			this.where = where;
			return this;
		}
		
		/**
		 * 查询字段
		 * @param String field
		 * @return
		 */
		public Builder field(String field)
		{	
			this.field = field;
			return this;
		}
		
		/**
		 * 排序字段
		 * @param String order
		 * @return
		 */
		public Builder order(String order)
		{	
			this.order = order;
			return this;
		}
		
		/**
		 * set修改
		 * @param String set
		 * @return
		 */
		public Builder set(String set)
		{	
			this.set = set;
			return this;
		}
				
		/**
		 * 查找单个model
		 * @return
		 */
		@SuppressWarnings("unchecked")
		public Map<String, Object> find(){
			this.getFindHql();
			try {
				Query query = getSession().createQuery(hql);
				List<Object> list = query.list();
				if(CollectionUtils.isNotEmpty(list)){
					Map<String, Object> result = ConvertUtil.object2Map(list.get(0));
					if(!transaction) commit();
					return result;
				}
			} catch (Exception e) {
				logger.info(e);
				e.printStackTrace();
			}
			return null;
			
		}
		
		/**
		 * 查找多个model
		 * @return
		 */
		@SuppressWarnings("unchecked")
		public ArrayList<Map<String, Object>> findAll(){
			this.getFindHql();
			try {
				Query query = getSession().createQuery(hql);
				List<Object> list = query.list();
				if(!transaction) commit();
				if(CollectionUtils.isNotEmpty(list)){
					return ConvertUtil.List2Array(list);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		/**
		 * 删除
		 * @return
		 */
		public Boolean delete(){
			this.getDeleteHql();
			try {
				Query query = getSession().createQuery(hql);
				query.executeUpdate();
				if(!transaction) commit();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}
		
		/**
		 * 修改
		 * @return
		 */
		public Boolean update(){
			this.getUpdateHql();
			try {
				Query query = getSession().createQuery(hql);
				query.executeUpdate();
				if(!transaction) commit();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
			
		}
		
		
		/**
		 * 获取查找hql
		 */
		private void getFindHql(){
			if(StringUtils.isNotEmpty(this.field)){
				hql = "select " + this.field;
			}
			this.getHql();
			if(StringUtils.isNotEmpty(this.order)){
				hql += " order by "+this.order;
			}
		}
		
		/**
		 * 获取删除hql
		 */
		private void getDeleteHql(){
			hql = "delete";
			this.getHql();
		}
		
		/**
		 * 获取修改hql
		 */
		private void getUpdateHql(){
			hql = "update " + this.model + " set " + set;
			if(StringUtils.isNotEmpty(this.where)){
				hql += " where " + this.where + " and 1=1";
			}else{
				hql += " where 1 = 1";
			}
		}
		
		//
		/**
		 * 获取hql
		 */
		private void getHql(){
			logger.info(hql);
			hql += " from " + this.model;
			if(StringUtils.isNotEmpty(this.where)){
				hql += " where " + this.where + " and 1=1";
			}else{
				hql += " where 1 = 1";
			}
		}

		
	}
}
