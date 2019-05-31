package com.action.system;




import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ehcache.Element;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.hamcrest.SelfDescribing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.action.CrudAction;
import com.common.encrypt.Encrypt;
import com.common.page.Page;
import com.common.response.ResponseModel;
import com.common.spring.BeanHelper;
import com.common.utils.AppConfig;
import com.common.utils.ClientInfo;
import com.common.utils.Const;
import com.common.utils.ConstDict;
import com.common.utils.ContextParam;
import com.common.utils.CookieUtil;
import com.common.utils.DateUtil;
import com.common.utils.DictUtil;
import com.common.utils.EhcacheUtil;
import com.common.utils.LogUtil;
import com.model.BaseModel;
import com.model.SessionUser;
import com.model.system.SysDictValue;
import com.model.system.SysUser;
import com.service.system.BlankService;
import com.service.system.ConfigService;
import com.service.system.IndexService;
import com.service.system.LogService;
import com.service.system.LoginService;
import com.service.system.impl.IndexManager;
import com.service.system.impl.LoginManager;

/**  
 * 用户登录控制层
 * @author:wangzhen
 * @version:V1.0
 * 2017年9月5日  
 */
@Controller
public class LoginAction  extends CrudAction<LoginService,BaseModel>{
		@Autowired
		private LoginService loginservice;
		/**
		 * 测试方法
		 * 
		 */
		@RequestMapping("/demo")
		@ResponseBody
		public String demo(){
			logger.info("/demo");
//			SessionUser sessionUser = loginservice.login("admin", "1234qwer");
//			logger.info(sessionUser.getUserId());
//			LoginService loginService = (LoginService)BeanHelper.getBean("loginManager");
//			SessionUser user = loginService.login("admin", "1234qwer");
//			logger.info(user.getUserId());
			List<Object> para = new ArrayList<Object>();
			
			para.add("admin");
			List<SysUser> userlist = (List<SysUser>) loginservice.getUserList(new Object[]{"admin"});
			
			
			logger.info(userlist.toString());
			
//			SysUser user = userlist.get(0);
//			logger.info(user.getUserId());
//			for(SysUser sysUser : userlist){
//				logger.info(sysUser.getUserName());
//			}
			
			return "ok";
		}
	
	    /**
	     * 用户登录操作方法
	     * 2017年9月7日
	     * @param loginName 登录名
	     * @param passWord 密码
	     * @param captcha 验证码
	     * @param remember 是否记住密码
	     * @return 
	     * author:wangzhen
	     */
	 	@RequestMapping(Const.LOGIN_URL)
	    public ModelAndView login(@RequestParam(value="loginName",required=false) String loginName,@RequestParam(value="passWord",required=false) String passWord,@RequestParam(value="captcha",required=false) String captcha,@RequestParam(value="remember",required=false,defaultValue="") String remember){
	 		ModelAndView mv = new ModelAndView();
	 		String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ request.getContextPath();
	 		request.getSession().setAttribute("basePath", basePath);
	 		mv.setViewName("/system/login/login");
	 		//获取系统名称，用于页面title显示
	 		mv.addObject("appName",AppConfig.getAppName());
	 		//认证失败后将本次的loginName再传回页面
	 		mv.addObject("loginName", loginName);
	 		//认证失败后将本次是否勾选记住密码传回页面
	 		mv.addObject("remember", remember);
	 		//1.如果未接收到用户名密码，跳转到登录页面
	 		if(StringUtils.isEmpty(loginName)&&StringUtils.isEmpty(passWord)){
	 			mv = loadInfoFromCookie(mv,request);
	 			return mv;
	 		}
	 		//2.用户名不能为空，跳转并提示
	 		if(StringUtils.isEmpty(loginName)){
	 			mv.addObject(Const.LOGIN_TIPS, Const.NO_LOGINNAME_MSG);
	 			return mv;
	 		}
	 		//3.密码不能为空
	 		if(StringUtils.isEmpty(passWord)){
	 			mv.addObject(Const.LOGIN_TIPS, Const.NO_PASSWORD_MSG);
	 			return mv;
	 		}
	 		
	 		SysUser sysUser = service.getLoginUser(loginName);
	 		
	 		//4.如果登录时启用验证码
	 		if(ContextParam.getCaptchaFlag()){
	 			String sessionCaptcha= (String)getSession().getAttribute(Const.SESSION_CAPTCHA);
	 			//验证码为空
	 			if(StringUtils.isEmpty(captcha)){
	 				mv.addObject(Const.LOGIN_TIPS, Const.NO_CAPTCHA_MSG);
		 			return mv;
	 			}
	 			//验证码错误
		 		if(StringUtils.isEmpty(sessionCaptcha)||!captcha.toUpperCase().equals(sessionCaptcha.toUpperCase())){
		 			//处理登录失败
		 			handleLoginFail(sysUser,Const.ERROR_CAPTCHA_MSG + "登录账号："+loginName,true);
		 			mv.addObject(Const.LOGIN_TIPS, Const.ERROR_CAPTCHA_MSG);
		 			return mv;
		 		}
		 		getSession().removeAttribute(Const.SESSION_CAPTCHA);
	 		}
	 		
	 		//5.判断用户或终端是否被黑名单限制登录
	 		if(!this.isCanLoginWithBlank(sysUser)){
	 			this.handleLoginFail(sysUser,"黑名单限制，登录账号："+loginName,true);;
	 			mv.addObject(Const.LOGIN_TIPS, Const.LIMIT_LOGIN_MSG);
	 			return mv;
	 		}
	 		
	 		//6.用户认证，认证成功跳转到首页面
	 		SessionUser sessionUser = service.login(loginName, passWord);
	 		if(sessionUser!=null){
	 			mv.addObject("loginName", null);
	 			mv.addObject("remember", null);
	 			mv.addObject("appName", null);
	 			//处理记住密码
	 			rememberPassword(request,response,remember,loginName,passWord);
	 			sysUser = sessionUser.getUser();
	 			//校验码一致性校验
	 			Encrypt encryptByMD5 = BeanHelper.getBean("encryptByMD5");
	 			if(!sysUser.getsCode().equals(encryptByMD5.encrypt(sysUser.getUserId()+sysUser.getLoginName()+sysUser.getLoginPwd()))){
	 				handleLoginFail(sysUser,Const.LOGIN_SCODE_ERROR + "登录账号：" + loginName,true);
	 				mv.addObject(Const.LOGIN_TIPS, "对不起！" + Const.LOGIN_SCODE_ERROR);
	 				return mv;
	 			}
	 			//判断用户信息是否允许登录
	 			Map<String, String> map = this.isCanLoginWithUser(sysUser);
	 			if(map.get("status").equals("false")){
	 				handleLoginFail(sysUser,map.get("msg") + "登录账号：" + loginName,true);
	 				mv.addObject(Const.LOGIN_TIPS, "对不起！" + map.get("msg"));
	 				return mv;
	 			}
	 			
	 			//判断登录唯一性
	 			if(!this.isCanLoginWithOther(sessionUser)){
	 				handleLoginFail(sysUser,"该账号正在其他地方登录，登录账号："+loginName,true);
	 				mv.addObject(Const.LOGIN_TIPS, Const.OTHER_LOGIN_MSG);
		 			return mv;
	 			}
	 			
	 			//初始化系统中用户配置信息
	 			sessionUser.setUserConfig(service.initUserConfig(sessionUser.getUserId()));
	 			//设置用户session信息
	 			getSession().setAttribute(Const.SESSION_USER, sessionUser);
	 			//保存权限菜单session
	 			IndexService indexService = BeanHelper.getBean("indexManager");
				Set<String> menuSet = indexService.getAuthMenu(sessionUser.getUserId());
				getSession().setAttribute(Const.SESSION_AUTH, menuSet);
	 			//记录登录成功安全日志
	 			LogUtil.addSafeLog(request, "后台登录", Const.LOG_LOGIN_TYPE);
	 			mv.setViewName("redirect:"+Const.INDEX_URL);
	 			return mv;
	 		}else {
	 			//7.认证不成功，跳转到登录页面
	 			//处理登录失败
	 			this.handleLoginFail(sysUser,Const.FAIL_LOGIN_MSG + "登录账号："+loginName,true);;
	 			mv.addObject(Const.LOGIN_TIPS, Const.FAIL_LOGIN_MSG);
	 			return mv;
			}
	 		
	    }
	 	
	 	/**
	 	 * 用户退出操作方法
	 	 * 2017年9月7日
	 	 * @return
	 	 * author:wangzhen
	 	 */
	 	@RequestMapping(Const.LOGOUT_URL)
	    public ModelAndView logout(){
	 		ModelAndView mv = new ModelAndView();
	 		String userId = getSessionUser(request).getUserId();
	 		String remark = "主动注销";
	 		if(EhcacheUtil.get(userId) == null)
	 			remark = "系统超时自动注销";
	 		//记录登出日志
	 		LogUtil.addSafeLog(request, "后台注销", Const.LOG_LOGOUT_TYPE,remark,Const.LOG_SUCCESS);
	 		//销毁缓存
	 		EhcacheUtil.remove(userId);
	 		//销毁session
	 		getSession().invalidate();
	 		DictUtil.clearDictCache();
	 		mv.setViewName("redirect:"+Const.LOGIN_URL);
	 		return mv;
	    }
	 	
	 	/**
	 	 * 系统自动注销
	 	 * 2019年2月26日
	 	 * @return
	 	 * @author:Lynn
	 	 */
	 	@RequestMapping(value=Const.CANCEL_URL,method=RequestMethod.POST)
	 	@ResponseBody
	 	public ResponseModel cancelLogin(){
	 		ConfigService configService = BeanHelper.getBean("configManager");
	 		int sessionTime = 0;
	 		String sessionTimeString = configService.getValueByCode("clearSessionTime");
	 		if(StringUtils.isNotEmpty(sessionTimeString)) sessionTime = Integer.parseInt(sessionTimeString);
	 		long expireMills = (long)request.getSession().getAttribute(Const.SESSION_OPERATE_TIME) + sessionTime*60*1000;
	 		long nowMills = (long)System.currentTimeMillis();
	 		logger.info(nowMills);
	 		logger.info(expireMills);
	 		if(nowMills > expireMills){
	 			//记录登出日志
	 			LogUtil.addSafeLog(request, "后台注销", Const.LOG_LOGOUT_TYPE,"系统超时自动注销",Const.LOG_SUCCESS);
	 			//销毁缓存
	 			EhcacheUtil.remove(getSessionUser(request).getUserId());
	 			//销毁session
	 			getSession().invalidate();
	 			DictUtil.clearDictCache();
	 			return new ResponseModel();
	 		}else{
	 			return new ResponseModel("1", "未到自动注销时间");
	 		}
	 	}
	 	/**
	 	 * 该方法用于将用户登录信息（登录名$$密码）保存到本地cookie中
	 	 * 2017年9月7日
	 	 * @param request
	 	 * @param response
	 	 * @param remember 是否勾选记住密码
	 	 * @param loginName
	 	 * @param passWord
	 	 * author:wangzhen
	 	 */
	 	public void rememberPassword(HttpServletRequest request,HttpServletResponse response,String remember,String loginName,String passWord){
	 		//勾选记住密码
	 		if(StringUtils.isNotEmpty(remember)&&remember.equals("checked")){
	 			String cookieString = "{\"loginName\":\""+loginName+"\",\"passWord\":\""+passWord+"\"}";
	 			CookieUtil.deleteCookie(request, response, Const.COOKIE_LOGIN_USER);
	 			CookieUtil.addCookie(response, Const.COOKIE_LOGIN_USER, cookieString);
	 		}else {
	 			//不勾选则将cookie删除
	 			CookieUtil.deleteCookie(request, response, Const.COOKIE_LOGIN_USER);
			}
	 	}
	 	
	 	/**
	 	 * 该方法用于从本地cookie中获取上次登录保存的用户名密码
	 	 * 设置到ModelAndView对象中，供前端界面接收
	 	 * 2017年9月7日
	 	 * @param mv 
	 	 * @param request
	 	 * @return
	 	 * author:wangzhen
	 	 */
	 	public ModelAndView loadInfoFromCookie(ModelAndView mv,HttpServletRequest request){
	 		//获取上次登录保存到cookie中的用户信息
	 		String cookieLoginUser =  CookieUtil.getCookieValueByName(request, Const.COOKIE_LOGIN_USER);
	 		JSONObject jsonObject = JSONObject.fromObject(cookieLoginUser);
	 		if(!jsonObject.isNullObject()&&jsonObject.containsKey("loginName")&&jsonObject.containsKey("passWord")){
	 			mv.addObject("loginName", jsonObject.getString("loginName"));
	 			mv.addObject("passWord", jsonObject.getString("passWord"));
	 			mv.addObject("remember", "checked");
	 		}
	 		return mv;
	 	}
	 	
	 	/**
	 	 * 处理登录失败
	 	 * 2019年2月22日
	 	 * @param sysUser
	 	 * @param msg
	 	 * @author:Lynn
	 	 */
	 	private void handleLoginFail(SysUser sysUser,String remark,Boolean isAddBlank){
	 		String userId = null;
	 		String userName = null;
	 		if(sysUser != null){
 	 			userId = sysUser.getUserId();
	 			userName = sysUser.getUserName();
	 		}
	 		//加入登录失败日志
	 		LogUtil.addLoginFailLog(request, remark, userId, userName);
	 		//判断是否加入黑名单
	 		if(isAddBlank) this.addBlank(userId);
	 	}
	 	
	 	/**
	 	 * 判断是否加入黑名单
	 	 * 2019年2月22日
	 	 * @param loginName
	 	 * @author:Lynn
	 	 */
	 	private void addBlank(String userId){
	 		BlankService blankService = BeanHelper.getBean("blankManager");
	 		if(userId != null){
	 			blankService.addBlank("1", userId);//用户加入黑名单
	 		}
	 		blankService.addBlank("2", ClientInfo.getClientMac());//客户端加入黑名单
	 	}
	 	
	 	/**
	 	 * 判断黑名单师傅允许登录
	 	 * 2019年2月22日
	 	 * @param userId
	 	 * @return
	 	 * @author:Lynn
	 	 */
	 	private Boolean isCanLoginWithBlank(SysUser sysUser){
	 		BlankService blankService = BeanHelper.getBean("blankManager");
	 		blankService.autoUnLock();
	 		if(sysUser != null){
	 			if(!blankService.isCanLogin("1", sysUser.getUserId())) return false;
	 		}
	 		return blankService.isCanLogin("2", ClientInfo.getClientMac());
	 	}
	 	
	 	/**
	 	 * 验证用户是否符合登陆要求
	 	 * 1、用户是否禁用
	 	 * 2、账户有效期
	 	 * 3、密码有效期
	 	 * 4、登陆时间段
	 	 * 2019年2月28日
	 	 * @param sysUser
	 	 * @return
	 	 * @author:Lynn
	 	 */
	 	private Map<String, String> isCanLoginWithUser(SysUser sysUser){
	 		Map<String, String> map = new HashMap<String, String>();
	 		if(sysUser.getDataFlag().equals("N")){
	 			//用户是否禁用
	 			map.put("status", "false");
	 			map.put("msg", Const.LOGIN_STATUS_ERROR);
	 			return map;
	 		}
	 		Date nowDate = new Date();
	 		Date preDate = DateUtil.getNextDate(nowDate, -1);
	 		//有效期验证
	 		if(preDate.compareTo(sysUser.getUserExpDate()) >= 0){
	 			//账户有效期
	 			map.put("status", "false");
	 			map.put("msg", Const.LOGIN_USER_LIMIT_ERROR);
	 			return map;
	 		}else if(preDate.compareTo(sysUser.getPwdExpDate()) >= 0){
	 			//密码有效期
	 			map.put("status", "false");
	 			map.put("msg", Const.LOGIN_PWD_LIMIT_ERROR);
	 			return map;
	 		}
	 		//ip地址限制
	 		String ip = ClientInfo.getClientIp(request);
	 		if(StringUtils.isNotEmpty(ip)){
	 			if(StringUtils.isEmpty(sysUser.getIp()) || sysUser.getIp().indexOf(ip) == -1){
	 				map.put("status", "false");
		 			map.put("msg", Const.LOGIN_IP_LIMIE_ERROR);
		 			return map;
	 			}
	 		}
	 		//登陆时间段验证
	 		int now = Integer.valueOf(DateUtil.getNowDateStr("HHmm"));
	 		int start = Integer.valueOf(sysUser.getLimitStart().replace(":",""));
	 		int end = Integer.valueOf(sysUser.getLimitEnd().replace(":",""));
	 		if(now >= start && now <= end){
	 			map.put("status", "true");
	 		}else{
	 			map.put("status", "false");
	 			map.put("msg", Const.LOGIN_DAY_LIMIE_ERROR);
	 		}
	 		return map;
	 	}
	 	
	 	/**
	 	 * 判断登录账号唯一性
	 	 * 2019年2月26日
	 	 * @param sessionUser
	 	 * @return
	 	 * @author:Lynn
	 	 */
	 	private Boolean isCanLoginWithOther(SessionUser sessionUser){
	 		ConfigService configService = BeanHelper.getBean("configManager");
	 		int sessionTime = 0,liveTime = 0;
	 		String sessionTimeString = configService.getValueByCode("clearSessionTime");
	 		String liveTimeString = configService.getValueByCode("loginLiveTime");
	 		if(StringUtils.isNotEmpty(sessionTimeString)) sessionTime = Integer.parseInt(sessionTimeString);
	 		if(StringUtils.isNotEmpty(liveTimeString)) liveTime = Integer.parseInt(liveTimeString);
	 		Element userElement = EhcacheUtil.get(sessionUser.getUserId());
	 		if(userElement != null){
//	 			long nowMills = (long)System.currentTimeMillis();
//	 			long expireMills = (long)userElement.getExpirationTime();
//	 			long createMills = (long)userElement.getCreationTime();
//	 			int timeout = (int)((sessionTime*60) - ((expireMills - nowMills)/1000));
//	 			int newlive = (int)((liveTime*60*60) - ((nowMills - createMills)/1000));
//	 			EhcacheUtil.expire(sessionUser.getUserId(), newlive,timeout);
	 			return false;
	 		}
	 		EhcacheUtil.put(sessionUser.getUserId(), true, liveTime*60*60, sessionTime*60);
	 		return true;
	 	}
}
