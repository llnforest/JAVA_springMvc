package com.service.system.impl;


import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.common.utils.Const;
import com.common.utils.Search;
import com.service.impl.BaseManager;
import com.service.system.SafeLogService;

/**  
 * 
 * @author:Lynn
 * @version:V1.0
 * 2017年4月26日  
 */
@Service
public class SafeLogManager extends BaseManager implements SafeLogService{

	@Override
	public Map<String, Object> getSqlMap(ModelMap map) {
		Map<String, Object> sqlMap = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append(" select obj.logId ,obj.userName as 操作人员,obj.operateName as 操作类别,obj.operateType as 类型,obj.operateResult as 结果,obj.appUrl as 访问地址");
		hql.append(" ,obj.clientName as 机器名称,obj.clientIp as IP地址,obj.remark as 备注,obj.createTime as 操作时间 ");
		hql.append(" from SysLog obj where 1=1 ");
		
		map.put("obj.logLevel", "4");
		Map<String, Object> where = (Map<String, Object>) Search.where(hql, map);//处理
		hql = (StringBuffer) where.get("hql");//获取hql
		hql = Search.getOrder(hql,"obj.createTime desc");//排序
		sqlMap.put(Const.PAGE_SQL, hql.toString());
		sqlMap.put(Const.SQL_PARA, where.get("para"));
		return sqlMap;
	}

}
