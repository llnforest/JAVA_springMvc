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

import com.common.utils.Const;
import com.common.utils.Search;
import com.service.impl.BasePkNumManager;
import com.service.w2.KcxxService;

@Service
public class KcxxManager extends BasePkNumManager implements KcxxService {
	@Override
	public Map<String, Object> getSqlMap(ModelMap map){
		Map<String, Object> sqlMap = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("select obj.id,obj.kch as 考车编号,obj.cph as 车牌号,obj.kcmc as 考车型号,obj.kscx as 考试车型,obj.xmxh as 项目序号,obj.zt as 考车状态 ");
		hql.append("from W2Kcxx obj  where 1=1  ");
		
		Map<String, Object> where = Search.where(hql, map);
		hql = (StringBuffer) where.get("hql");
		hql = Search.getOrder(hql,"obj.kch");
		sqlMap.put(Const.PAGE_SQL, hql.toString());
		sqlMap.put(Const.SQL_PARA, where.get("para"));
		return sqlMap;
		
	}
}
