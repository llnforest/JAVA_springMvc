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
import com.common.utils.SearchUtil;
import com.model.system.SysLog;
import com.model.system.SysMenu;
import com.model.system.SysRole;
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
		//获取查询hql
		StringBuffer hql = getSelectHql(SysLog.class.getName());
		map.put("obj.logLevel", "3");
		//查询工具类
		SearchUtil searchUtil = new SearchUtil(hql);
		searchUtil.setHql(hql);
		searchUtil.where(map);
		searchUtil.setOrder("obj.createTime desc");
		Map<String, Object> sqlMap = new HashMap<String, Object>();
		sqlMap.put(Const.PAGE_SQL, searchUtil.hql.toString());
		sqlMap.put(Const.SQL_PARA, searchUtil.para);
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
	public void setRemarkLog(String id,String remark) {
		executeHql("update SysLog obj set obj.remark = ? where obj.logId = ?", new Object[]{remark,id});
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
