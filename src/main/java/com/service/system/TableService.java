/**
 *
 * ConfigService.java
 * 2019年2月19日
 * author:Lynn
 */
package com.service.system;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import com.model.BaseModel;
import com.model.system.SysTable;
import com.service.BaseService;

public interface TableService extends BaseService{
	/**
	 * 获字段取备注map
	 * 2019年6月28日
	 * @param String
	 * @return
	 * @author:Lynn
	 */
	public Map<String, Map<String,String>> getFieldRemark(String tableName);
	
	public List<SysTable> getQueryList(String tableName);
	
	public Map<String, Map<Integer, String>> getColumnList(String tableName);
	
	/**
	 * 根据字段名称通过反射获取字段对象
	 * 2019年7月19日
	 * @param clazz
	 * @param column
	 * @return
	 * @author:Lynn
	 */
	public Field getTableField(Class<? extends BaseModel> clazz,String column);
	
	
}