package com.action;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.common.log.SysLogger;
import com.common.utils.Const;
import com.common.utils.ConvertUtil;
import com.common.utils.StringUtil;
import com.model.BaseModel;
import com.model.SessionUser;
import com.service.BasePkNumService;
import com.service.BaseService;

/**  
 * 封装的controller层基类
 * @author:wangzhen
 * @version:V1.0
 * 2017年8月24日  
 */
public class BasePkNumAction<T extends BasePkNumService,M extends BaseModel> {

	@Autowired
	protected HttpServletRequest request;
	
	@Autowired
	protected HttpServletResponse response;
	
	
	
	@Autowired
	protected T service;
	@Autowired
	protected M model;
	
	protected Map<String, Object> parasMap = new HashMap<String, Object>();
	
	protected SysLogger logger = new SysLogger(this);
	

//	public T getService() {
//		return service;
//	}
//
//	public void setService(T service) {
//		this.service = service;
//	}

	public M getModel() {
		return model;
	}

	public void setModel(M model) {
		this.model = model;
	}
	
	public Map<String, Object> getParasMap() {
		return parasMap;
	}

	public void setParasMap(Map<String, Object> parasMap) {
		this.parasMap = parasMap;
	}

	/**
	 * 获取上下文中session
	 * 2017年8月24日
	 * @return
	 * author:wangzhen
	 */
	protected HttpSession getSession() { 
		HttpSession session = null;
		try {
			session = request.getSession(); 
		} catch (Exception e) {} 
		return session; 
	} 
	
	
	/**
	 * 获取当前登录用户session对象信息
	 * 2017年9月10日
	 * @param request
	 * @return
	 * author:wangzhen
	 */
	public SessionUser getSessionUser(HttpServletRequest request) {
		return (SessionUser)request.getSession().getAttribute(Const.SESSION_USER);
	}
	
	
	/**
	 * 获取ModelAndView对象
	 * 并将request参数设置到mv中
	 * 2017年9月11日
	 * @return
	 * author:wangzhen
	 */
	public ModelAndView getModelAndView(){
		ModelAndView mv = new ModelAndView();
		String queryString = StringUtil.convertNull(request.getQueryString());
		mv.addObject("servletURI", StringUtil.convertUrl(request.getRequestURI(), queryString));
		//将request中的参数设置到ModelAndView中
		mv.addAllObjects(getRequestParamsMap());
		this.init(mv);
		return mv;
	}
	
	/**
	 * 获取ModelAndView对象
	 * 1.并将request参数设置到mv中
	 * 2.将model设置到mv中
	 * 2017年9月11日
	 * @param model
	 * @return
	 * author:wangzhen
	 */
	public ModelAndView getModelAndView(BaseModel model){
		ModelAndView mv = getModelAndView();
		//将model中的参数设置到ModelAndView中
		mv.addAllObjects(ConvertUtil.object2Map(model));
		return mv;
	}
	
	/**
	 * 供子类在获取ModelAndView时初始化一些个性化信息
	 * 预留
	 * 2017年9月11日
	 * author:wangzhen
	 */
	public void init(ModelAndView mv){
		
	}

	/**
	 * 对列表页面数据进行处理
	 * 2019年2月12日
	 * @author:Lynn
	 */
	public void handleListData(){
		
	}

	/**
	 * 获取日志处理对象
	 * 2016年3月7日
	 * @return
	 * author:wangZhen
	 */
	public SysLogger getLogger() {
		return logger;
	}
	
	/**
	 * 将request中的参数转为map集合
	 * 2017年9月11日
	 * @return
	 * author:wangzhen
	 */
	@SuppressWarnings("rawtypes")
	protected Map<String, Object> getRequestParamsMap() {
		Map<String, Object> requestParamsMap = new HashMap<String, Object>();
		Enumeration paramNames = request.getParameterNames();
		while (paramNames != null && paramNames.hasMoreElements()) {
			Object paramName = paramNames.nextElement();
			if (paramName != null) {
				Object paramValue = request.getParameterValues(paramName.toString());
				requestParamsMap.put(paramName.toString(), ((String[])paramValue)[0]);
			}
		}
		return requestParamsMap;
	}

	
}
