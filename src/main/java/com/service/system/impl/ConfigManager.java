package com.service.system.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.common.utils.Const;
import com.common.utils.Search;
import com.model.system.SysConfig;
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
		Map<String, Object> sqlMap = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("select obj.configId,obj.configName as 配置名称,obj.configCode as 配置编码,obj.configValue as 配置数值,obj.units as 单位,obj.remark as 备注 ,obj.configOrder as 排序  ");
		hql.append("from SysConfig obj where 1=1  ");
		
		Map<String, Object> where = Search.where(hql, map);
		hql = (StringBuffer) where.get("hql");
		hql = Search.getOrder(hql,"obj.configOrder");
		sqlMap.put(Const.PAGE_SQL, hql.toString());
		sqlMap.put(Const.SQL_PARA, where.get("para"));
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
