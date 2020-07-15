package com.service.system.impl;


import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.common.utils.Const;
import com.common.utils.SearchUtil;
import com.model.system.SysLog;
import com.service.impl.BaseManager;
import com.service.system.SafeLogService;

/**  
 * 
 * @author:Lynn
 * @version:V1.0
 * 2017年4月26日  
 */
@Service
public class SafeLogManager extends BaseManager implements SafeLogService{

	@Override
	public Map<String, Object> getSqlMap(ModelMap map) {
		//获取查询hql
		StringBuffer hql = getSelectHql(SysLog.class.getName());
		map.put("obj.logLevel", "4");
		//查询工具类
		SearchUtil searchUtil = new SearchUtil(hql);
		searchUtil.setHql(hql);
		searchUtil.where(map);
		searchUtil.setOrder("obj.createTime desc");
		Map<String, Object> sqlMap = new HashMap<String, Object>();
		sqlMap.put(Const.PAGE_SQL, searchUtil.hql.toString());
		sqlMap.put(Const.SQL_PARA, searchUtil.para);
		return sqlMap;
	}

}
