/**
 *
 * KcxxManager.java
 * 2019年3月7日
 * author:Lynn
 */
package com.service.w2.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.common.utils.Const;
import com.common.utils.SearchUtil;
import com.model.w2.W2Queuing;
import com.service.impl.BaseManager;
import com.service.w2.QueuingService;

@Service
public class QueuingManager extends BaseManager implements QueuingService {
	@Override
	public Map<String, Object> getSqlMap(ModelMap map){
		//获取查询hql
		StringBuffer hql = getSelectHql(W2Queuing.class.getName());
		//查询工具类
		SearchUtil searchUtil = new SearchUtil(hql);
		searchUtil.setHql(hql);
		searchUtil.where(map);
		searchUtil.setOrder("obj.ksbh");
		Map<String, Object> sqlMap = new HashMap<String, Object>();
		sqlMap.put(Const.PAGE_SQL, searchUtil.hql.toString());
		sqlMap.put(Const.SQL_PARA, searchUtil.para);
		return sqlMap;
		
	}

	@Override
	public String getProjectNames(ArrayList<String> projectArr) {
		if(projectArr.size() == 0 || projectArr == null) return "";
		String projectIds = "\'"+StringUtils.join(projectArr,"\',\'")+"\'";
		List<?> list = super.getListByHql("select obj.valName from SysDictValue obj where obj.sysDict.dictCode = ? and obj.valCode in ("+projectIds+")  order by valOrder asc", new Object[]{"examProject"});
		if(CollectionUtils.isNotEmpty(list)){
			StringBuffer strBuffer = new StringBuffer();
			for(int i = 0;i < list.size();i++){
				strBuffer.append(list.get(i)+",");
			}
			return strBuffer.deleteCharAt(strBuffer.length()-1).toString();
		}
		return "";
	}
}
