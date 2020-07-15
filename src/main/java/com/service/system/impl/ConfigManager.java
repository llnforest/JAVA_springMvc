package com.service.system.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.common.utils.Const;
import com.common.utils.SearchUtil;
import com.model.system.SysConfig;
import com.model.system.SysLog;
import com.service.impl.BaseManager;
import com.service.system.ConfigService;

/**  
 *  
 * @author:Lynn
 * @version:V1.0
 * 2018年12月24日  
 */
@Service
public class ConfigManager extends BaseManager implements ConfigService{
	@Override
	public Map<String, Object> getSqlMap(ModelMap map){
		//获取查询hql
		StringBuffer hql = getSelectHql(SysConfig.class.getName());
		//查询工具类
		SearchUtil searchUtil = new SearchUtil(hql);
		searchUtil.setHql(hql);
		searchUtil.where(map);
		searchUtil.setOrder("obj.configOrder");
		Map<String, Object> sqlMap = new HashMap<String, Object>();
		sqlMap.put(Const.PAGE_SQL, searchUtil.hql.toString());
		sqlMap.put(Const.SQL_PARA, searchUtil.para);
		return sqlMap;
		
	}

	@Override
	public String getValueByCode(String code) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("configCode", code);
		SysConfig sysConfig = (SysConfig)getUniqueByMap(SysConfig.class, map);
		if(sysConfig != null){
			return sysConfig.getConfigValue();
		}
		return null;
	}
	
}
