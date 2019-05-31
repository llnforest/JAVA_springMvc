package com.service.system.impl;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.common.utils.Const;
import com.common.utils.DateUtil;
import com.common.utils.Search;
import com.model.system.SysLog;
import com.model.system.SysMenu;
import com.service.impl.BaseManager;
import com.service.system.LogService;

/**  
 * 
 * @author:wangzhen
 * @version:V1.0
 * 2017年4月26日  
 */
@Service
public class LogManager extends BaseManager implements LogService{

	@Override
	public Map<String, Object> getSqlMap(ModelMap map) {
		Map<String, Object> sqlMap = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append(" select obj.logId ,obj.userName as 操作人员,obj.operateName as 操作类别,obj.operateType as 类型,obj.operateResult as 结果,obj.appUrl as 访问地址");
		hql.append(" ,obj.clientName as 机器名称,obj.clientIp as IP地址,obj.remark as 备注,obj.createTime as 操作时间 ");
		hql.append(" from SysLog obj where 1=1 ");
		
		map.put("obj.logLevel", "3");
		Map<String, Object> where = (Map<String, Object>) Search.where(hql, map);//处理
		hql = (StringBuffer) where.get("hql");//获取hql
		hql = Search.getOrder(hql,"obj.createTime desc");//排序
		sqlMap.put(Const.PAGE_SQL, hql.toString());
		sqlMap.put(Const.SQL_PARA, where.get("para"));
		return sqlMap;
	}

	
	@Override
	public String addLog(String url, Map<String, String> clientMap, String menuId,
			String userId,String userName,String appName,String operateResult,String remark,String httpData) {
		SysMenu sysMenu = (SysMenu) loadModel(SysMenu.class, menuId);
		String operateName,operateType;
		if(sysMenu.getMenuType().equals("B")){//是按钮
			operateName = sysMenu.getParent().getMenuTitle();
			operateType = sysMenu.getMenuTitle();
		}else{//非按钮
			operateName = sysMenu.getMenuTitle();
			operateType = "查看";
		}
		if(sysMenu.getLogLevel().equals("1")) return null;
		SysLog sysLog = new SysLog(userId,userName,operateName, operateType, operateResult, appName, url, clientMap.get("clientOs"), clientMap.get("clientIp"), clientMap.get("clientBrowser"), remark,clientMap.get("clientMac"),sysMenu.getLogLevel(),clientMap.get("clientName"),httpData);
		try {
			return saveModel(sysLog);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public void setFailLog(String id) {
		executeHql("update SysLog obj set obj.operateResult = 2 where obj.logId = ?", id);
	}

	@Override
	public String addSafeLog(String operateName, String operateType,
			String operateResult, String appName, String appUrl,
			String remark, String logLevel,String userId,String userName, Map<String, String> clientMap,String httpData) {
		SysLog sysLog = new SysLog(userId,userName,operateName, operateType, operateResult, appName, appUrl, clientMap.get("clientOs"), clientMap.get("clientIp"), clientMap.get("clientBrowser"), remark,clientMap.get("clientMac"),logLevel,clientMap.get("clientName"),httpData);
		try {
			return saveModel(sysLog);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	


}
