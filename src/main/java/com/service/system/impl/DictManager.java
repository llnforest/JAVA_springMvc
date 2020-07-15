package com.service.system.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.common.utils.Const;
import com.common.utils.SearchUtil;
import com.model.system.SysDict;
import com.model.system.SysDictValue;
import com.service.impl.BaseManager;
import com.service.system.DictService;

/**  
 *  
 * @author:wangzhen
 * @version:V1.0
 * 2017年4月26日  
 */
@Service
public class DictManager extends BaseManager implements DictService{
	
	@Override
	public Map<String, Object> getSqlMap(ModelMap map) {
		//获取查询hql
		StringBuffer hql = getSelectHql(SysDict.class.getName());
		//查询工具类
		SearchUtil searchUtil = new SearchUtil(hql);
		searchUtil.setHql(hql);
		searchUtil.where(map);
		searchUtil.setOrder("obj.dictOrder asc");
		Map<String, Object> sqlMap = new HashMap<String, Object>();
		sqlMap.put(Const.PAGE_SQL, searchUtil.hql.toString());
		sqlMap.put(Const.SQL_PARA, searchUtil.para);
		return sqlMap;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SysDictValue> getDictMap(String dictCode) {
		List<Object> para = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer();
		hql.append(" select dictVal ");
		hql.append(" from com.model.system.SysDictValue dictVal,com.model.system.SysDict obj where obj.dictId = dictVal.sysDict.dictId  ");
		if(StringUtils.isNotEmpty(dictCode)){
			hql.append(" and obj.dictCode = ? ");
			para.add(dictCode);
		}
		hql.append(" order by obj.dictOrder asc,dictVal.valOrder asc ");
		List<SysDictValue> dictData = (List<SysDictValue>)this.getListByHql(hql.toString(), para.toArray());
		return dictData;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SysDict> getDictList(Object ...para){
		List<SysDict> dictList = (List<SysDict>) this.getListByHql("from SysDict obj order by obj.dictOrder asc",para);
		return dictList;
	}

}
