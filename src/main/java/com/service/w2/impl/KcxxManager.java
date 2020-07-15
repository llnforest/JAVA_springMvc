/**
 *
 * KcxxManager.java
 * 2019年3月7日
 * author:Lynn
 */
package com.service.w2.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.common.page.PageUtil;
import com.common.utils.Const;
import com.common.utils.SearchUtil;
import com.common.utils.TableUtil;
import com.model.w2.W2Kcxx;
import com.model.w2.W2Records;
import com.service.impl.BaseManager;
import com.service.w2.KcxxService;

@Service
public class KcxxManager extends BaseManager implements KcxxService {
	@Override
	public Map<String, Object> getSqlMap(ModelMap map){
		//获取查询hql
		StringBuffer hql = getSelectHql(W2Kcxx.class.getName());
		//查询工具类
		SearchUtil searchUtil = new SearchUtil(hql);
		searchUtil.setHql(hql);
		searchUtil.where(map);
		searchUtil.setOrder("obj.kch");
		Map<String, Object> sqlMap = new HashMap<String, Object>();
		sqlMap.put(Const.PAGE_SQL, searchUtil.hql.toString());
		sqlMap.put(Const.SQL_PARA, searchUtil.para);
		return sqlMap;
		
	}
}
