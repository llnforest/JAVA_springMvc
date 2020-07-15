/**
 *
 * TableUtil.java
 * 2019年7月3日
 * author:Lynn
 */
package com.common.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder.In;

import com.common.spring.BeanHelper;
import com.model.system.SysDictValue;
import com.model.system.SysTable;
import com.service.system.TableService;


public class TableUtil {
	private static TableService tableService = BeanHelper.getBean("tableManager");
	private static Map<String, List<SysTable>> tableSearchMap = new HashMap<String, List<SysTable>>();
	private static Map<String, Map<Integer,String>> tableDictMap = new HashMap<String, Map<Integer,String>>();
	private static Map<String, Map<Integer,String>> tableWidthMap = new HashMap<String, Map<Integer,String>>();
	private static Map<String, Map<Integer,String>> tableStyleMap = new HashMap<String, Map<Integer,String>>();
	private static Map<String, Map<Integer,String>> tableJsfuncMap = new HashMap<String, Map<Integer,String>>();
	private static Map<String, Map<Integer,String>> tableTplMap = new HashMap<String, Map<Integer,String>>();
	private static Map<String, Map<Integer,Boolean>> tableHideMap = new HashMap<String, Map<Integer,Boolean>>();
	private static Map<String, Map<Integer,String>> tableInputMap = new HashMap<String, Map<Integer,String>>();
	private static Map<String, String> tableSelectMap = new HashMap<String, String>();
	
	
	
	/**
	 * 根据实体对象名称获取高能模块的查询区域list
	 * 2019年7月3日
	 * @param tableName
	 * @return
	 * @author:Lynn
	 */
	public static List<SysTable> getSearchArea(String tableName){
		if(!tableSearchMap.containsKey(tableName)){
			tableSearchMap.put(tableName,tableService.getQueryList(tableName));
		}
		return tableSearchMap.get(tableName);
		
	}
	
	/**
	 * 根据实体对象名称获取高能模块的列表展示区域的列list
	 * 2019年7月3日
	 * @param tableName
	 * @return
	 * @author:Lynn
	 */
	public static void getColumnArea(String tableName){
		Map<String, Map<Integer, String>> map = tableService.getColumnList(tableName);
		tableWidthMap.put(tableName, map.get("widthMap"));
		tableDictMap.put(tableName, map.get("dictMap"));
		tableStyleMap.put(tableName, map.get("styleMap"));
		tableJsfuncMap.put(tableName, map.get("jsfuncMap"));
		tableTplMap.put(tableName, map.get("tplMap"));
		tableInputMap.put(tableName, map.get("inputMap"));
		
		tableSelectMap.put(tableName, map.get("selectMap").get(0));
		Map<Integer, Boolean> hideMap = new HashMap<Integer, Boolean>();
		if(map.get("hideMap") != null && !map.get("hideMap").isEmpty()){
			for(Map.Entry<Integer, String> entry: map.get("hideMap").entrySet()){
				hideMap.put(entry.getKey(), true);
			}
		}
		tableHideMap.put(tableName, hideMap);
		
	}
	
	/**
	 * 获取查询字段的字典map
	 * 2019年7月3日
	 * @param tableName
	 * @return
	 * @author:Lynn
	 */
	public static Map<Integer,String> getDictMap(String tableName){
		if(!tableDictMap.containsKey(tableName)){
			getColumnArea(tableName);
		}
		return tableDictMap.get(tableName);
	}
	
	/**
	 * 获取查询字段的宽度map
	 * 2019年7月3日
	 * @param tableName
	 * @return
	 * @author:Lynn
	 */
	public static Map<Integer,String> getWidthMap(String tableName){
		if(!tableWidthMap.containsKey(tableName)){
			getColumnArea(tableName);
		}
		return tableWidthMap.get(tableName);
	}
	
	/**
	 * 获取查询字段的宽度map
	 * 2019年7月3日
	 * @param tableName
	 * @return
	 * @author:Lynn
	 */
	public static Map<Integer,String> getJsfuncMap(String tableName){
		if(!tableJsfuncMap.containsKey(tableName)){
			getColumnArea(tableName);
		}
		return tableJsfuncMap.get(tableName);
	}
	/**
	 * 获取查询字段的宽度map
	 * 2019年7月3日
	 * @param tableName
	 * @return
	 * @author:Lynn
	 */
	public static Map<Integer,String> getStyleMap(String tableName){
		if(!tableStyleMap.containsKey(tableName)){
			getColumnArea(tableName);
		}
		return tableStyleMap.get(tableName);
	}
	/**
	 * 获取查询字段的宽度map
	 * 2019年7月3日
	 * @param tableName
	 * @return
	 * @author:Lynn
	 */
	public static Map<Integer,String> getTplMap(String tableName){
		if(!tableTplMap.containsKey(tableName)){
			getColumnArea(tableName);
		}
		return tableTplMap.get(tableName);
	}
	
	/**
	 * 获取查询字段的是否隐藏map
	 * 2019年7月3日
	 * @param tableName
	 * @return
	 * @author:Lynn
	 */
	public static Map<Integer,Boolean> getHideMap(String tableName){
		if(!tableHideMap.containsKey(tableName)){
			getColumnArea(tableName);
		}
		return tableHideMap.get(tableName);
	}
	
	/**
	 * 获取查询字段是否可修改的map
	 * 2019年7月3日
	 * @param tableName
	 * @return
	 * @author:Lynn
	 */
	public static Map<Integer,String> getInputMap(String tableName){
		if(!tableInputMap.containsKey(tableName)){
			getColumnArea(tableName);
		}
		return tableInputMap.get(tableName);
	}
	
	/**
	 * 获取查询字段字符串
	 * 2019年7月3日
	 * @param tableName
	 * @return
	 * @author:Lynn
	 */
	public static String getSelect(String tableName){
		if(!tableSelectMap.containsKey(tableName)){
			getColumnArea(tableName);
		}
		return tableSelectMap.get(tableName);
	}
	
	/**
	 * 清空静态变量(全部清除)
	 * 2019年7月3日
	 * @author:Lynn
	 */
	public static void clearTableCache(){
		tableSearchMap = new HashMap<String, List<SysTable>>();
		tableDictMap = new HashMap<String, Map<Integer,String>>();
		tableWidthMap = new HashMap<String, Map<Integer,String>>();
		tableJsfuncMap = new HashMap<String, Map<Integer,String>>();
		tableTplMap = new HashMap<String, Map<Integer,String>>();
		tableStyleMap = new HashMap<String, Map<Integer,String>>();
		tableSelectMap = new HashMap<String, String>();
		tableHideMap = new HashMap<String, Map<Integer,Boolean>>();
		tableInputMap = new HashMap<String, Map<Integer,String>>();
	}
	
	/**
	 * 清空静态变量(按表名称清除)
	 * 2019年7月3日
	 * @author:Lynn
	 */
	public static void clearTableCache(String tableName){
		tableSearchMap.remove(tableName);
		tableDictMap.remove(tableName);
		tableWidthMap.remove(tableName);
		tableJsfuncMap.remove(tableName);
		tableTplMap.remove(tableName);
		tableStyleMap.remove(tableName);
		tableSelectMap.remove(tableName);
		tableHideMap.remove(tableName);
		tableInputMap.remove(tableName);
	}
	
}
