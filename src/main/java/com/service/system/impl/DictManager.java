package com.service.system.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.common.utils.Const;
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
		Map<String, Object> sqlMap = new HashMap<String, Object>();
		List<Object> para = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer();
		hql.append(" select obj.dictId ,obj.dictName as 字典名称,obj.dictCode as 字典编码,obj.dictType as 字典类型,obj.dictOrder as 排序号码,obj.remark as 备注说明 ");
		hql.append(" from SysDict obj where 1=1 ");
		if(map.get("dictName")!=null&&StringUtils.isNotEmpty(map.get("dictName").toString())){
			hql.append(" and obj.dictName like ? ");
			para.add("%"+map.get("dictName").toString()+"%");
		}
		if(map.get("dictCode")!=null&&StringUtils.isNotEmpty(map.get("dictCode").toString())){
			hql.append(" and obj.dictCode like ? ");
			para.add("%"+map.get("dictCode").toString()+"%");
		}
		hql.append(" order by obj.dictOrder asc  ");
		sqlMap.put(Const.PAGE_SQL, hql.toString());
		sqlMap.put(Const.SQL_PARA, para);
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
