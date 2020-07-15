package com.service.system.impl;

import java.util.HashMap;
import java.util.Map;

import javax.swing.plaf.TableUI;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.common.utils.Const;
import com.common.utils.SearchUtil;
import com.common.utils.TableUtil;
import com.model.system.SysDictValue;
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
		StringBuffer hql = new StringBuffer();
		hql.append("select " + TableUtil.getSelect(SysDictValue.class.getName()));
		hql.append("from SysDictValue obj left join obj.sysDict dict where 1=1  ");
		
		if(!map.containsKey("obj.sysDict.dictId") && map.get("dictId") != null && StringUtils.isNotEmpty(map.get("dictId").toString())){
			map.put("obj.sysDict.dictId", map.get("dictId").toString());
		}
		SearchUtil searchUtil = new SearchUtil(hql);
		searchUtil.setHql(hql);
		searchUtil.where(map);
		searchUtil.setOrder("dict.dictOrder asc,obj.valOrder asc");
		Map<String, Object> sqlMap = new HashMap<String, Object>();
		sqlMap.put(Const.PAGE_SQL, searchUtil.hql.toString());
		sqlMap.put(Const.SQL_PARA, searchUtil.para);
		return sqlMap;
		
	}
	
}
