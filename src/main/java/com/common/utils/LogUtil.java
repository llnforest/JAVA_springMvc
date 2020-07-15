package com.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.common.spring.BeanHelper;
import com.model.BaseModel;
import com.model.SessionUser;
import com.service.BaseService;
import com.service.system.LogService;
import com.service.system.TableService;

/**
 * 日志工具类
 * @author Lynn
 * 2019年2月15日
 */
public class LogUtil {
	
	private static LogService logService = (LogService)BeanHelper.getBean("logManager");
	
	
	/**
	 * 添加日志
	 * 2019年2月15日
	 * @param request
	 * @param menuId
	 * @author:Lynn
	 */
	public static void addLog(HttpServletRequest request,String menuId){
		if(StringUtils.isNotEmpty(menuId)){
			String url = StringUtil.convertUrl(request.getRequestURI(), request.getQueryString());
			HttpSession session = request.getSession();
			SessionUser sessionUser = (SessionUser) session.getAttribute(Const.SESSION_USER);
			//获取客户端信息
			Map<String, String> clientMap = ClientInfo.getClientInfo(request);
			String httpData = RequestUtil.getParamer(request);
			//添加日志默认成功
			String logId = logService.addLog(url, clientMap, menuId, sessionUser.getUserId(),sessionUser.getUserName(), AppConfig.getAppName(),"1","",httpData);
			if(StringUtils.isNotEmpty(logId)) session.setAttribute(Const.SESSION_LOG_ID, logId);
		}
	}
	
	
	/**
	 * 重置日志操作结果
	 * 2019年2月15日
	 * @param request
	 * @author:Lynn
	 */
	public static void setFailLog(HttpServletRequest request){
		String logId = (String) request.getSession().getAttribute(Const.SESSION_LOG_ID);
		if(StringUtils.isNotEmpty(logId)) logService.setFailLog(logId);
	}
	
	/**
	 * 设置日志备注
	 * 2019年2月15日
	 * @param request
	 * @param remark
	 * @author:Lynn
	 */
	public static void setRemarkLog(HttpServletRequest request,String remark){
		String logId = (String) request.getSession().getAttribute(Const.SESSION_LOG_ID);
		if(StringUtils.isNotEmpty(logId)) logService.setRemarkLog(logId,remark);
	}
	
	/**
	 * 添加安全日志
	 * 2019年2月15日
	 * @param request
	 * @param operateName
	 * @param operateType
	 * @param remark
	 * @param operateResult
	 * @author:Lynn
	 */
	public static void addSafeLog(HttpServletRequest request,String operateName,String operateType,String remark,String operateResult){
		String url = StringUtil.convertUrl(request.getRequestURI(), request.getQueryString());
		HttpSession session = request.getSession();
		//获取客户端信息
		Map<String, String> clientMap = ClientInfo.getClientInfo(request);
		SessionUser sessionUser = (SessionUser) session.getAttribute(Const.SESSION_USER);
		String userId = sessionUser == null ? null : sessionUser.getUserId();
		String userName = sessionUser == null ? null : sessionUser.getUserName();
		String logLevel = "4";
		String httpData = RequestUtil.getParamer(request);
		String logId = logService.addSafeLog(operateName, operateType, operateResult, AppConfig.getAppName(), url, remark, logLevel,userId,userName, clientMap,httpData);
		if(StringUtils.isNotEmpty(logId)) session.setAttribute(Const.SESSION_LOG_ID, logId);
	}
	
	/**
	 * 添加安全日志（简略版）
	 * 2019年2月22日
	 * @param request
	 * @param operateName
	 * @param operateType
	 * @author:Lynn
	 */
	public static void addSafeLog(HttpServletRequest request,String operateName,String operateType){
		addSafeLog(request, operateName, operateType,"",Const.LOG_SUCCESS);
	}
	
	/**
	 * 添加登录失败安全日志
	 * 2019年2月22日
	 * @param request
	 * @param remark
	 * @param userId
	 * @param userName
	 * @author:Lynn
	 */
	public static void addLoginFailLog(HttpServletRequest request,String remark,String userId,String userName){
		String url = StringUtil.convertUrl(request.getRequestURI(), request.getQueryString());
		//获取客户端信息
		Map<String, String> clientMap = ClientInfo.getClientInfo(request);

		String logLevel = "4";
		String httpData = RequestUtil.getParamer(request);
		logService.addSafeLog("后台登录", Const.LOG_LOGIN_TYPE, Const.LOG_FAIL, AppConfig.getAppName(), url, remark, logLevel,userId,userName, clientMap,httpData);
		
	}
	
	
	/**
	 * 修改接口备注
	 * 2019年6月26日
	 * @param request
	 * @param newModel
	 * @author:Lynn
	 */
	public static void remarkUpdateLog(HttpServletRequest request,BaseModel newModel){
		String logId = (String) request.getSession().getAttribute(Const.SESSION_LOG_ID);
		if(StringUtils.isEmpty(logId)) return;
		
		BaseModel model = getOldModel(newModel);
		Class<? extends BaseModel> clazz = newModel.getClass();
		
		
		//从日志JSON数据中取出json字符串   转map
//		Map<String, Map<String, String>> map = (Map<String, Map<String, String>>) JSONObject.fromObject(JsonLog.get(clazz.getName()));
		//从table信息表中获取对应关系
		TableService tableService = BeanHelper.getBean("tableManager");
		Map<String, Map<String, String>> map = tableService.getFieldRemark(clazz.getName());
		if(map.isEmpty()) return;
		Map<String,String> head = new HashMap<String, String>();
		Map<String,String> body = new HashMap<String, String>();
		//判断头部和内容key是否存在
		if(map.containsKey("head")) head = map.get("head");
		if(map.containsKey("body")) body = map.get("body");
		
		String new_str,old_str,field_name,para;
		StringBuffer remarkBuffer = new StringBuffer();
		StringBuffer headBuffer = new StringBuffer();
		
//		通过反射获取字段信息
		Field[] fields = clazz.getDeclaredFields();
		for(Field field:fields){
			
			field_name = field.getName(); 
			try {
				//头部固定内容  取新值
				if(head.containsKey(field_name)){
					field.setAccessible(true);
					new_str = String.valueOf(field.get(newModel));
					para = head.get(field_name);
					new_str = map.get("dict").containsKey(field_name)?DictUtil.getDictName(map.get("dict").get(field_name), new_str):new_str;
					headBuffer.append(para+"【"+new_str+"】，");
					field.setAccessible(false);
				}
				//比较内容
				if(body.containsKey(field_name)){
					field.setAccessible(true);
					new_str = String.valueOf(field.get(newModel));
					new_str = map.get("dict").containsKey(field_name)?DictUtil.getDictName(map.get("dict").get(field_name), new_str):new_str;
					old_str = String.valueOf(field.get(model));
					old_str = map.get("dict").containsKey(field_name)?DictUtil.getDictName(map.get("dict").get(field_name), old_str):old_str;
					new_str = !new_str.equals("null") ?new_str:"";
					old_str = !old_str.equals("null") ?old_str:"";
					if(!new_str.equals(old_str)){
						para = body.get(field_name);
						remarkBuffer.append(para+"【"+old_str+"】改为【"+new_str+"】，");
					}
					field.setAccessible(false);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		String remark = headBuffer.toString() + (remarkBuffer.length() > 0 ? remarkBuffer.substring(0, remarkBuffer.length()-1).toString():"");
		setRemarkLog(request, remark);
	}
	
	/**
	 * 添加接口备注
	 * 2019年6月26日
	 * @param request
	 * @param newModel
	 * @author:Lynn
	 */
	public static void remarkAddLog(HttpServletRequest request,BaseModel newModel){
		String logId = (String) request.getSession().getAttribute(Const.SESSION_LOG_ID);
		if(StringUtils.isEmpty(logId)) return;
	
		Class<? extends BaseModel> clazz = newModel.getClass();
		
		//从日志JSON数据中取出json字符串   转map
//		Map<String, Map<String, String>> map = (Map<String, Map<String, String>>) JSONObject.fromObject(JsonLog.get(clazz.getName()));
		//从table信息表中获取对应关系
		TableService tableService = BeanHelper.getBean("tableManager");
		Map<String, Map<String, String>> map = tableService.getFieldRemark(clazz.getName());
		if(map.isEmpty()) return;
		Map<String,String> body = new HashMap<String, String>();
		//判断内容key是否存在
		if(map.containsKey("body")){
			//比较字段信息
			body = map.get("body");
		}
		
		String new_str,field_name,para;
		StringBuffer remarkBuffer = new StringBuffer();
		
//		通过反射获取字段信息
		Field[] fields = clazz.getDeclaredFields();
		for(Field field:fields){
			
			field_name = field.getName(); 
			try {
				//比较内容
				if(body.containsKey(field_name)){
					field.setAccessible(true);
					new_str = String.valueOf(field.get(newModel));
					if(StringUtils.isNotEmpty(new_str) && !new_str.equals("null")){
						new_str = map.get("dict").containsKey(field_name)?DictUtil.getDictName(map.get("dict").get(field_name), new_str):new_str;
						para = body.get(field_name);
						remarkBuffer.append(para+"【"+new_str+"】，");
					}
					field.setAccessible(false);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		String remark = (remarkBuffer.length() > 0 ? remarkBuffer.substring(0, remarkBuffer.length()-1).toString():"");
		setRemarkLog(request, remark);
	}
	
	
	/**
	 * 删除接口备注
	 * 2019年6月26日
	 * @param request
	 * @param newModel
	 * @author:Lynn
	 */
	public static void remarkDelLog(HttpServletRequest request,Class <? extends BaseModel> clazz,Object id){
		String logId = (String) request.getSession().getAttribute(Const.SESSION_LOG_ID);
		if(StringUtils.isEmpty(logId)) return;
		BaseModel model;
		BaseService baseService = BeanHelper.getBean("service");
		if(id instanceof Integer){
			model = baseService.loadModel(clazz,(Integer) id);
		}else{
			model = baseService.loadModel(clazz, (String) id);
		}
	
		//从日志JSON数据中取出json字符串   转map
//		Map<String, Map<String, String>> map = (Map<String, Map<String, String>>) JSONObject.fromObject(JsonLog.get(clazz.getName()));
		//从table信息表中获取对应关系
		TableService tableService = BeanHelper.getBean("tableManager");
		Map<String, Map<String, String>> map = tableService.getFieldRemark(clazz.getName());
		if(map.isEmpty()) return;
		Map<String,String> body = new HashMap<String, String>();
		if(map.containsKey("body")){
			//比较字段信息
			body = map.get("body");
		}
		
		String old_str,field_name,para;
		StringBuffer remarkBuffer = new StringBuffer();
		
//		通过反射获取字段信息
		Field[] fields = clazz.getDeclaredFields();
		for(Field field:fields){
			
			field_name = field.getName(); 
			try {
				//比较内容
				if(body.containsKey(field_name)){
					field.setAccessible(true);
					old_str = String.valueOf(field.get(model));
					if(StringUtils.isNotEmpty(old_str) && !old_str.equals("null")){
						para = body.get(field_name);
						old_str = map.get("dict").containsKey(field_name)?DictUtil.getDictName(map.get("dict").get(field_name), old_str):old_str;
						remarkBuffer.append(para+"【"+old_str+"】，");
					}
					field.setAccessible(false);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		String remark = (remarkBuffer.length() > 0 ? remarkBuffer.substring(0, remarkBuffer.length()-1).toString():"");
		setRemarkLog(request, remark);
	}
	/**
	 * 获得操作之前的对象
	 * 2019年6月26日
	 * @param baseModel
	 * @return
	 * @author:Lynn
	 */
	private static BaseModel getOldModel(BaseModel baseModel){
		BaseModel model = null;
		Class <? extends BaseModel> clazz = baseModel.getClass();
		Method[] methods = clazz.getDeclaredMethods();
		for(Method method:methods){
			if(method.isAnnotationPresent(Id.class)){
				try {
					BaseService baseService = BeanHelper.getBean("service");
					if(method.getReturnType() == Integer.class){
						model = baseService.loadModel(clazz,(Integer)method.invoke(baseModel));
					}else{
						model = baseService.loadModel(clazz, (String) method.invoke(baseModel));
					}
					break;
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return model;
	}
	
}
