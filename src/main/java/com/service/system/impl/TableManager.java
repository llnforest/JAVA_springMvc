package com.service.system.impl;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.common.utils.Const;
import com.common.utils.DictUtil;
import com.common.utils.SearchUtil;
import com.model.BaseModel;
import com.model.system.SysDictValue;
import com.model.system.SysTable;
import com.service.impl.BaseManager;
import com.service.system.TableService;

/**  
 *  
 * @author:Lynn
 * @version:V1.0
 * 2018年12月24日  
 */
@Service
public class TableManager extends BaseManager implements TableService{
	@Override
	public Map<String, Object> getSqlMap(ModelMap map){
		//查询工具类
		StringBuffer hql = getSelectHql(SysTable.class.getName());
		
		SearchUtil searchUtil = new SearchUtil(hql);
		searchUtil.setHql(hql);
		searchUtil.where(map);
		searchUtil.setOrder("obj.isList desc,obj.listOrder asc");
		Map<String, Object> sqlMap = new HashMap<String, Object>();
		sqlMap.put(Const.PAGE_SQL, searchUtil.hql.toString());
		sqlMap.put(Const.SQL_PARA, searchUtil.para);
		return sqlMap;
	}
	
	@Override
	public Map<String, Map<String, String>> getFieldRemark(String tableName){
		Map<String, Map<String, String>> map = new HashMap<String, Map<String,String>>();
		Map<String, String>  head = new HashMap<String, String>();
		Map<String, String>  body = new HashMap<String, String>();
		Map<String, String>  dict = new HashMap<String, String>();
		
		List<?> list = this.getListByHql("from SysTable where tableName = ? and isLog = 1 order by logOrder asc", tableName);
		
		for(int i=0;i<list.size();i++){
			SysTable sysTable = (SysTable)list.get(i);
			if(sysTable.getIsLog() == 1) body.put(sysTable.getColumnName(), sysTable.getRemarkName());
			if(sysTable.getIsFixedLog() == 1) head.put(sysTable.getColumnName(), sysTable.getRemarkName());
			if(StringUtils.isNotEmpty(sysTable.getDictCode())) dict.put(sysTable.getColumnName(), sysTable.getDictCode());
		}
		map.put("head", head);
		map.put("body", body);
		map.put("dict", dict);
		return map;
	}

	@Override
	public List<SysTable> getQueryList(String tableName) {
		List<SysTable> list = (List<SysTable>) this.getListByHql("from SysTable where tableName = ? and isSearch = 1 order by searchOrder asc", tableName);
		return list;
	}

	@Override
	public Map<String, Map<Integer, String>> getColumnList(String tableName) {
		Map<String, Map<Integer, String>> map = new HashMap<String, Map<Integer, String>>();
		List<?> list = this.getListByHql("from SysTable where tableName = ? and isList = 1 order by listOrder asc", tableName);
		Map<Integer, String> widthMap = new HashMap<Integer, String>();
		Map<Integer, String> dictMap = new HashMap<Integer, String>();
		Map<Integer, String> selectMap = new HashMap<Integer, String>();
		Map<Integer, String> tplMap = new HashMap<Integer, String>();
		Map<Integer, String> styleMap = new HashMap<Integer, String>();
		Map<Integer, String> jsfuncMap = new HashMap<Integer, String>();
		Map<Integer, String> hideMap = new HashMap<Integer, String>();
		Map<Integer, String> inputMap = new HashMap<Integer, String>();
		StringBuffer selectBuffer = new StringBuffer();
		String col = "";
		for(int i=0;i<list.size();i++){
			SysTable sysTable = (SysTable)list.get(i);
			if(StringUtils.isNotEmpty(sysTable.getListWidth())) widthMap.put(new Long(sysTable.getListOrder()).intValue(), sysTable.getListWidth());
			if(StringUtils.isNotEmpty(sysTable.getDictCode())) dictMap.put(new Long(sysTable.getListOrder()).intValue(), sysTable.getDictCode());
			if(StringUtils.isNotEmpty(sysTable.getListJsfunc())) jsfuncMap.put(new Long(sysTable.getListOrder()).intValue(), sysTable.getListJsfunc());
			if(StringUtils.isNotEmpty(sysTable.getListStyle())) styleMap.put(new Long(sysTable.getListOrder()).intValue(), sysTable.getListStyle());
			if(StringUtils.isNotEmpty(sysTable.getListTpl())) tplMap.put(new Long(sysTable.getListOrder()).intValue(), sysTable.getListTpl());
			if(new Long(sysTable.getListHide()).intValue() == 1) hideMap.put(new Long(sysTable.getListOrder()).intValue(), "true");
			if(new Long(sysTable.getListInput()).intValue() == 1 && StringUtils.isEmpty(sysTable.getDictCode()) && StringUtils.isEmpty(sysTable.getListTpl())) inputMap.put(new Long(sysTable.getListOrder()).intValue(), "input");
			if(new Long(sysTable.getListSwitch()).intValue() == 1 && new Long(sysTable.getListInput()).intValue() == 0 && StringUtils.isEmpty(sysTable.getListTpl())){
				String defaultText = "是|否";
				if(StringUtils.isNotEmpty(sysTable.getDictCode()) && !sysTable.getDictCode().equals("isTrue")){
					List<SysDictValue> dictValueList = DictUtil.getDictList(sysTable.getDictCode());
					if(dictValueList.size() >= 2) defaultText = dictValueList.get(0).getValName() + "|" + dictValueList.get(1).getValName();
				}
				col = "col"+sysTable.getListOrder();
				tplMap.put(new Long(sysTable.getListOrder()).intValue(), "<div>{{ tplSwitch(d,d."+col+",\""+col+"\",\""+defaultText+"\")}}</div>");
			}
			
			selectBuffer.append((StringUtils.isNotEmpty(sysTable.getListName())?sysTable.getListName():sysTable.getColumnName()) + " as " + sysTable.getRemarkName() + ",");
		}
		if(list.size() > 0){
			selectMap.put(0," "+selectBuffer.substring(0, selectBuffer.length()-1).toString()+" ");
		}else{
			selectMap.put(0, "");
		}
		
		
		map.put("widthMap", widthMap);
		map.put("dictMap", dictMap);
		map.put("selectMap", selectMap);
		map.put("tplMap", tplMap);
		map.put("styleMap", styleMap);
		map.put("jsfuncMap", jsfuncMap);
		map.put("hideMap", hideMap);
		map.put("inputMap", inputMap);
		return map;
	}

	@Override
	public Field getTableField(Class<? extends BaseModel> clazz, String column) {
		Long listOrder = Long.valueOf(column.substring(3));
		//获取字段名称
		SysTable sysTable = (SysTable) getUniqueByHql("from SysTable where tableName = ? and listOrder = ?", new Object[]{clazz.getName(),listOrder});
		if(sysTable == null) return null;
		String columnName = sysTable.getColumnName();
		 
		//反射获取字段
		Field field;
		try{
			field = clazz.getDeclaredField(columnName);
		}catch(NoSuchFieldException e){
			Class<?> parentClass = clazz.getSuperclass();
			try{
				field = parentClass.getDeclaredField(columnName);
			}catch(NoSuchFieldException ne){
				return null;
			}
		}
		return field;
	}

	
}
