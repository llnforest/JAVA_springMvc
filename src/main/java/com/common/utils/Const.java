package com.common.utils;
/**  
 *  
 * @author:wangzhen
 * @version:V1.0
 * 2017年9月5日  
 */
public class Const {
	
	/**
	 * session中的用户key
	 */
	public static final String SESSION_USER = "sessionUser";	
	/**
	 * session中的字典Map
	 */
	public static final String SESSION_DICT_MAP = "dictMap";	
	/**
	 * session中的验证码key
	 */
	public static final String SESSION_CAPTCHA = "captcha";
	/**
	 * session中的角色权限
	 */
	public static final String SESSION_AUTH = "auth";
	/**
	 * session中会话操作间隔有效时间（分钟）
	 */
	public static final String SESSION_OPERATE_TIME = "operateTime";
	/**
	 * session中的客户端信息
	 */
	public static final String SESSION_CLIENT = "client";
	/**
	 * session中的拦截器纪录的日志id
	 */
	public static final String SESSION_LOG_ID = "logID";
	/**
	 * 首页地址
	 */
	public static final String INDEX_URL = "/index";
	/**
	 * 首页信息地址
	 */
	public static final String CONSOLE_URL = "/index/console";
	/**
	 * 登录地址
	 */
	public static final String LOGIN_URL = "/login";	
	/**
	 * 退出地址
	 */
	public static final String LOGOUT_URL = "/logout";	
	/**
	 * 自动退出地址
	 */
	public static final String CANCEL_URL = "/cancelLogin";	
	
	
	
	
	
	
	/**************************登录错误信息提醒*****************************/
	
	/**
	 * 登录信息提醒key
	 */
	public static final String LOGIN_TIPS = "tipsMsg";	
	
	/**
	 * 用户名为空提醒信息
	 */
	public static final String NO_LOGINNAME_MSG = "请您输入用户名！";	
	
	/**
	 * 密码为空提醒信息
	 */
	public static final String NO_PASSWORD_MSG = "请您输入密码！";	
	
	/**
	 * 密码为空提醒信息
	 */
	public static final String NO_CAPTCHA_MSG = "请您输入验证码！";	
	
	/**
	 * 验证码错误提醒信息
	 */
	public static final String ERROR_CAPTCHA_MSG = "您输入的验证码有误！";	
	
	/**
	 * 登录失败提醒信息
	 */
	public static final String LIMIT_LOGIN_MSG = "您登录失败次数过多，请稍后再试！";	
	
	/**
	 * 登录失败提醒信息
	 */
	public static final String OTHER_LOGIN_MSG = "该账号正在其他地方登录，请稍后再试！";	
	
	/**
	 * 登录失败提醒信息
	 */
	public static final String FAIL_LOGIN_MSG = "您输入的帐号或密码有误！";	
	
	/**
	 * 未取到值提醒信息
	 */
	public static final String FAIL_NO_VALUE = "未取到有效参数值！";	
	/**
	 * 参数错误
	 */
	public static final String ERROR_PARAMS = "参数错误！";	
	/**
	 * 账号禁用
	 */
	public static final String LOGIN_STATUS_ERROR = "您的账号已被禁用！";	
	/**
	 * 账号过期
	 */
	public static final String LOGIN_USER_LIMIT_ERROR = "您的账号已过有效期！";	
	/**
	 * 密码过期
	 */
	public static final String LOGIN_PWD_LIMIT_ERROR = "您的密码已过有效期！";	
	/**
	 * 未取到值提醒信息
	 */
	public static final String LOGIN_DAY_LIMIE_ERROR = "非登录时间段内登录！";	
	/**
	 * ip限制
	 */
	public static final String LOGIN_IP_LIMIE_ERROR = "您的IP不在允许登录范围内！";	
	/**
	 * 校验码限制
	 */
	public static final String LOGIN_SCODE_ERROR = "用户信息校验失败！";	
	/**
	 * ip限制
	 */
	public static final String NEWPASSWORD_SAME = "新密码和原密码一致，修改失败！";	
	
	/**
	 * cookie中保存的登录用户信息
	 */
	public static final String COOKIE_LOGIN_USER = "COOKIE_LOGIN_USER";	
	
	
	/****************操作结果***********************/
	/**
	 * 返回提示：网络异常
	 */
	public static final String OPERATE_ERROR = "网络异常";
	/**
	 * 返回提示：success
	 */
	public static final String SUCCESS_TEXT = "SUCCESS";
	
	/****************安全日志操作类型*******************/
	/**
	 * 登录日志操作类型
	 */
	public static final String LOG_LOGIN_TYPE = "登录";	
	/**
	 * 注销日志操作类型
	 */
	public static final String LOG_LOGOUT_TYPE = "注销";	
	/**
	 * 日志操作成功
	 */
	public static final String LOG_SUCCESS = "1";	
	/**
	 * 日志操作失败
	 */
	public static final String LOG_FAIL = "2";	
	
	/**************************列表常量*****************************/
	/**
	 * 分页列表sql集合
	 */
	public static final String SQL_MAP = "sqlMap";	
	/**
	 * 分页列表sql语句
	 */
	public static final String PAGE_SQL = "pageSql";	
	/**
	 * 分页列表sql语句 指定的参数
	 */
	public static final String SQL_PARA = "sqlPara";	
	/**
	 * 分页列表是使用sql语句还是hql语句，默认使用hql语句
	 */
	public static final String USE_SQL = "useSql";	
	/**
	 * 列表中获取到的列表按钮权限
	 */
	public static final String LIST_AUTH_BUTS = "listButs";	
	/**
	 * 列表中获取到的列表按钮权限
	 */
	public static final String LIST_AUTH_TABS = "listTabs";	
	/**
	 * 列表中获取到的行列表按钮权限
	 */
	public static final String BAR_AUTH_BUTS = "barButs";	
	/**
	 * 用于自定义查询按钮的uri
	 */
	public static final String CUSTOM_AUTH_URI = "customAuthURI";	
	
	
	/**************************接口状态常量*****************************/
	/**
	 * 成功
	 */
	public static final String SUCCESS_CODE = "0";	
	/**
	 * 失败
	 */
	public static final String ERROR_CODE = "1";	
	/**
	 * 签名验证错误
	 */
	public static final String SIGN_ERROR = "100";	
	/**
	 * 参数错误状态码
	 */
	public static final String PARA_ERROR = "200";	
	/**
	 * 第三方登录
	 */
	public static final String WECHART_LOGIN = "300";	
	/**
	 * 手机已注册（针对注册）
	 */
	public static final String PHONE_EXIST = "301";	
	/**
	 * 手机未注册（针对登录）
	 */
	public static final String PHONE_NOT_EXIST = "302";	
	/**
	 * 验证码错误
	 */
	public static final String PHONECODE_RROR = "303";	
	
	/**
	 * 账户被冻结
	 */
	public static final String ACCUNT_LOCK = "304";	
	
	/**
	 * 业务错误
	 */
	public static final String BUSI_ERROR = "400";
	/**
	 * 基准的crud操作错误状态码
	 */
	public static final String CRUD_ERROR = "500";	
	
	
	
	/**************************用户个性化参数名称常量*****************************/
	
	/**
	 * 系统主题顶部颜色
	 */
	public static final String SKIN_TOP_COLOR = "topColor";	
	/**
	 * 系统主题左侧颜色
	 */
	public static final String SKIN_LEFT_COLOR = "leftColor";	

}
