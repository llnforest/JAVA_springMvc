/**
 *
 * Search.java
 * 2019年1月18日
 * author:Lynn
 */
package com.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.ui.ModelMap;



public class Search {
	
	public static Map<String, Object> where(StringBuffer hql,ModelMap map){
		Map<String, Object> where = new HashMap<String, Object>();
		List<Object> para = new ArrayList<Object>();;
		
		if(MapUtils.isNotEmpty(map)){
			String key,value;
			for (Map.Entry<String, Object> entry:map.entrySet()) {
				key = entry.getKey();
				if(!key.contains(".")) continue;
				
				value = String.valueOf(entry.getValue());
				if(StringUtils.isEmpty(value)) continue;
				
				if(!key.contains("_")){
					
					hql.append(" and " + key + " = ? ");
					para.add(value);
				}else{
					String[] keyArr = key.split("_");
					key = keyArr[0];
					String operate = keyArr[1];
					if(keyArr.length < 3){
						switch (operate) {
							case "like":
								hql.append(" and " + key + " like ?");
								para.add("%" + value + "%");
								break;
							case "not like":
								hql.append(" and " + key + " not like ?");
								para.add("%" + value + "%");
								break;
							case "in":
								hql.append(" and " + key + " in ?");
								para.add("(" + value + ")");
								break;
							case "not in":
								hql.append(" and " + key + " not in ?");
								para.add("(" + value + ")");
								break;
								
								
							default:
								hql.append(" and " + key + " " + operate + " ? ");
								para.add(value);
								break;
						}
						
					}else{
						if(keyArr[2].equals("date")){
							hql.append(" and " + key + " " + operate + " to_date(?,'yyyy-mm-dd hh24:mi:ss')");
							para.add(value);
						}else if(keyArr[2].equals("time")){
							hql.append(" and " + key + " " + operate + " ? ");
							para.add(DateUtil.getDate(value));
						}
					}
				}
			}
		}
		where.put("hql", hql);
		where.put("para", para);
		return where;
	}
	
	/**
	 * 获取分组group
	 * 2019年1月22日
	 * @param hql
	 * @param group
	 * @return
	 * @author:Lynn
	 */
	public static StringBuffer getGroup(StringBuffer hql,String group){
		if(StringUtils.isNotEmpty(group)){
			hql.append(" group by " + group + " ");
		}
		return hql;
	}
	
	/**
	 * 获取排序order
	 * 2019年1月18日
	 * @param hql
	 * @param order
	 * @return
	 * @author:Lynn
	 */
	public static StringBuffer getOrder(StringBuffer hql,String ...order){
		if(order != null){
			for(int i=0;i<order.length;i++){
				if(i == 0){
					hql.append(" order by " + order[i] + ",");
				}else{
					hql.append(order[i] + ",");
				}
			}
			hql = hql.deleteCharAt(hql.length()-1);
		}else{
			hql.append(" order by obj.orderby ");
		}
		return hql;
	}
	

	/**
	 * 获取排序order
	 * 2019年1月18日
	 * @param hql
	 * @param order
	 * @return
	 * @author:Lynn
	 */
	public static StringBuffer getOrder(StringBuffer hql){
		hql.append(" order by obj.orderby ");
		return hql;
	}
}
