package com.service.system;

import java.util.Map;

import com.model.SessionUser;
import com.service.BaseService;



/**  
 * 系统中提供的日志处理服务
 * 1.保存日志接口
 * 2.设置日志操作结果接口
 * 3.安全日志存储接口
 * @author:Lynn
 * 2019年2月18日
 */
public interface LogService extends BaseService{
	/**
	 * 添加日志
	 * 2019年2月13日
	 * @param url
	 * @param clientMap
	 * @param menuId
	 * @param userId
	 * @param userName
	 * @param appName
	 * @return
	 * @author:Lynn
	 */
	public String addLog(String url,Map<String, String> clientMap,String menuId,String userId,String userName,String appName,String operateResult,String remark,String httpData);
	
	/**
	 * 设置日志操作结果——失败
	 * 2019年2月13日
	 * @param id
	 * @author:Lynn
	 */
	public void setFailLog(String id);
	
	/**
	 * 设置日志操作结果——失败
	 * 2019年2月13日
	 * @param id
	 * @param remark
	 * @author:Lynn
	 */
	public void setRemarkLog(String id,String remark);
	
	/**
	 * 添加安全日志
	 * 2019年2月15日
	 * @param operateName
	 * @param operateType
	 * @param operateResult
	 * @param appName
	 * @param appUrl
	 * @param remark
	 * @param logLevel
	 * @param userId
	 * @param userName
	 * @param clientMap
	 * @author:Lynn
	 */
	public String addSafeLog(String operateName, String operateType, String operateResult, String appName, String appUrl, String remark,String logLevel,String userId,String userName,Map<String, String> clientMap,String httpData);

}
