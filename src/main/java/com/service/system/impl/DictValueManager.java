package com.service.system.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.common.utils.Const;
import com.common.utils.Search;
import com.service.impl.BaseManager;
import com.service.system.DictValueService;

/**  
 *  
 * @author:Lynn
 * @version:V1.0
 * 2018年12月24日  
 */
@Service
public class DictValueManager extends BaseManager implements DictValueService{
	@Override
	public Map<String, Object> getSqlMap(ModelMap map){
		Map<String, Object> sqlMap = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("select obj.valId,obj.sysDict.dictName as 字典名称,obj.valName as 字典参数,obj.valCode as 字典值,obj.valColor as 字体颜色,obj.valOrder as 参数排序,obj.remark as 参数备注   ");
		hql.append("from SysDictValue obj left join obj.sysDict dict where 1=1  ");
		
		if(!map.containsKey("obj.sysDict.dictId") && map.get("dictId") != null && StringUtils.isNotEmpty(map.get("dictId").toString())){
			map.put("obj.sysDict.dictId", map.get("dictId").toString());
		}
		Map<String, Object> where = (Map<String, Object>) Search.where(hql, map);
		hql = (StringBuffer) where.get("hql");
		hql = Search.getOrder(hql,"dict.dictOrder asc,obj.valOrder asc");
		sqlMap.put(Const.PAGE_SQL, hql.toString());
		sqlMap.put(Const.SQL_PARA, where.get("para"));
		return sqlMap;
		
	}
	
}
