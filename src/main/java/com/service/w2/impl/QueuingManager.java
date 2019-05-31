/**
 *
 * KcxxManager.java
 * 2019年3月7日
 * author:Lynn
 */
package com.service.w2.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.common.utils.Const;
import com.common.utils.Search;
import com.service.impl.BasePkNumManager;
import com.service.w2.KcxxService;
import com.service.w2.QueuingService;

@Service
public class QueuingManager extends BasePkNumManager implements QueuingService {
	@Override
	public Map<String, Object> getSqlMap(ModelMap map){
		Map<String, Object> sqlMap = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("select obj.id,obj.ksbh as 考生编号,obj.xm as 考生姓名,obj.zjhm as 证件号码,obj.kscx as 车型,obj.kcbh as 车号,obj.kchp as 车牌号,obj.djc as 次数 ,obj.zt as 排队状态,obj.ksxm as 分配项目,obj.wcxm as 未完成项目,obj.kgname as 考官姓名,obj.kg as 考官证件");
		hql.append(" from W2Queuing obj  where 1=1  ");
		
		Map<String, Object> where = Search.where(hql, map);
		hql = (StringBuffer) where.get("hql");
		hql = Search.getOrder(hql,"obj.ksbh");
		sqlMap.put(Const.PAGE_SQL, hql.toString());
		sqlMap.put(Const.SQL_PARA, where.get("para"));
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
