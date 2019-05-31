package com.action.system;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.decorator.Delegate;
import javax.servlet.jsp.tagext.FunctionInfo;

import org.apache.commons.lang.StringUtils;
import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.action.CrudAction;
import com.common.delegate.EventHandler;
import com.common.encrypt.Encrypt;
import com.common.response.ResponseModel;
import com.common.spring.BeanHelper;
import com.common.utils.Const;
import com.common.utils.DateUtil;
import com.common.utils.DictUtil;
import com.common.utils.EhcacheUtil;
import com.common.utils.LogUtil;
import com.model.SessionUser;
import com.model.system.SysDict;
import com.model.system.SysDictValue;
import com.model.system.SysRole;
import com.model.system.SysUser;
import com.model.system.SysUserRole;
import com.service.system.DictService;
import com.service.system.UserRoleService;
import com.service.system.UserService;



/**  
 * 用户管理Controller层
 * @author:wangzhen
 * @version:V1.0
 * 2017年4月26日  
 */
@Controller
@RequestMapping("/system/user")
public class UserAction extends CrudAction<UserService,SysUser>{
	
	@Override
	public void init(ModelAndView mv) {
		mv.addObject("nowDate",DateUtil.getNowDateStr("yyyy-MM-dd"));
		
	}
	
	@Override
	public void handleListData(){
//		pageUtil.setShowNumbers(false);
//		pageUtil.setShowCheckbox(true);
		pageUtil.setShowRadio(true);
		pageUtil.setInsetColMap(3, "角色名称", this, "getRoleNames","0");
		pageUtil.setDataDict(5, "userType");
//		pageUtil.setDataDict(11, "userState");
		pageUtil.setJsFuncColMap(7, "'#'.substring(0,10)");
		pageUtil.setJsFuncColMap(8, "'#'.substring(0,10)");
		pageUtil.setColsTemplet(9, "#allowTimeTpl");
		pageUtil.setColsHide(10, true);
		pageUtil.setColsTemplet(12,"#statusTpl");
		pageUtil.setColsTemplet(11,"#limitTextTpl");
		
	}
	@RequestMapping(value="/detail",method=RequestMethod.GET)
	public ModelAndView detail(@RequestParam(value="id", required=false, defaultValue="") String id){
		ModelAndView mv = super.detail(id);
		ModelMap map = mv.getModelMap();
		if(map.containsKey("userId")){
			SysUserRole userRole = (SysUserRole)service.getUniqueByProperty(SysUserRole.class, "sysUser.userId", String.valueOf(map.get("userId")));
			mv.addObject("roleId",userRole.getSysRole().getRoleId());
		}
		return mv;
	}
	
	/**
	 * 账号延期激活、密码延期激活、用户详情
	 * 2019年3月5日
	 * @param id
	 * @return
	 * @author:Lynn
	 */
	@RequestMapping(value={"/detailByUser","/detailByPwd","/selfInfo"},method=RequestMethod.GET)
	public ModelAndView detailByUser(@RequestParam(value="id", required=false, defaultValue="") String id){
		if(StringUtils.isEmpty(id)) return this.getModelAndView(model);
		model = (SysUser) service.loadModel(model.getClass(), id);
		SysUserRole userRole = (SysUserRole)service.getUniqueByProperty(SysUserRole.class,"sysUser",model);
		SysRole role = (SysRole)service.loadModel(SysRole.class, userRole.getSysRole().getRoleId());
		ModelAndView mv = this.getModelAndView(model);
		mv.addObject("roleName", role.getRoleName());
		return mv;
	}
	
	/**
	 * 激活过期账户，设置需修改密码
	 * 2019年2月28日
	 * @param id 用户id
	 * @param date 更新账号有效期
	 * @return
	 * @author:Lynn
	 */
	@RequestMapping(value="/activeUserExpire",method=RequestMethod.POST)
	@ResponseBody
	public ResponseModel activeUserExpire(@RequestParam(value="userId", required=false, defaultValue="") String id,@RequestParam(value="userDate", required=false, defaultValue="") String date){
		if(StringUtils.isNotEmpty(id) && StringUtils.isNotEmpty(date)){
			model = (SysUser)service.loadModel(model.getClass(), id);
			if(model != null){
				Date newDate = DateUtil.getDate(date, "yyyy-MM-dd");
				if(model.getUserExpDate().compareTo(newDate) == -1){
					model.setUserExpDate(newDate);
					model.setState("1");
					model.setModifyId(getSessionUser(request).getUserId());
				}else{
					return new ResponseModel(Const.PARA_ERROR,"账号激活失败！未做任何修改");
				}
			}
			try {
				service.updateModel(model);
				return new ResponseModel(this.getModelAndView(model));
			} catch (Exception e) {
				return new ResponseModel(Const.CRUD_ERROR,e.getMessage());
			}
		}
		return new ResponseModel(Const.PARA_ERROR,Const.ERROR_PARAMS);
	}
	
	/**
	 * 延长密码有效期
	 * 2019年2月28日
	 * @param id 用户id
	 * @param date 更新密码有效期
	 * @return
	 * @author:Lynn
	 */
	@RequestMapping(value="/activePwdExpire",method=RequestMethod.POST)
	@ResponseBody
	public ResponseModel activePwdExpire(@RequestParam(value="userId", required=false, defaultValue="") String id,@RequestParam(value="pwdDate", required=false, defaultValue="") String date){
		if(StringUtils.isNotEmpty(id) && StringUtils.isNotEmpty(date)){
			model = (SysUser)service.loadModel(model.getClass(), id);
			if(model != null){
				Date newDate = DateUtil.getDate(date, "yyyy-MM-dd");
				if(model.getPwdExpDate().compareTo(newDate) == -1){
					model.setPwdExpDate(newDate);
					model.setModifyId(getSessionUser(request).getUserId());
				}else{
					return new ResponseModel(Const.PARA_ERROR,"密码延期失败！未做任何修改");
				}
			}
			try {
				service.updateModel(model);
				return new ResponseModel(this.getModelAndView(model));
			} catch (Exception e) {
				return new ResponseModel(Const.CRUD_ERROR,e.getMessage());
			}
		}
		return new ResponseModel(Const.PARA_ERROR,Const.ERROR_PARAMS);
	}
	
	/**
	 * 获取角色名称
	 * 2019年1月23日
	 * @param userId
	 * @return
	 * @author:Lynn
	 */
	public String getRoleNames(String userId){
		return service.getRoleNames(userId);
	}
	
	/**
	 * 该方法用于执行用户修改密码操作
	 * 2018年8月23日
	 * @param id 用户ID
	 * @return
	 * author:wangzhen
	 */
	@RequestMapping("/changePassword/{id}")
	@ResponseBody
	public ResponseModel changePassword(@PathVariable(value="id") String id) {
		try {
			ModelAndView mv = this.getModelAndView();
			//获取到用户ID，加载对象
			if(StringUtils.isNotEmpty(id)){
				 model = (SysUser) service.loadModel(SysUser.class, id);
				 String newPwd = mv.getModel().get("loginPwd").toString();
				 if(model.getLoginPwd().equals(newPwd)){
					 return new ResponseModel("2",Const.NEWPASSWORD_SAME);
				 }
				 model.setLoginPwd(newPwd);
				 model.setState("0");
				 Encrypt encryptByMD5 = BeanHelper.getBean("encryptByMD5");
				 model.setsCode(encryptByMD5.encrypt(id+model.getLoginName()+newPwd));
				 service.updateModel(model);
				 
				//记录退出日志
		 		LogUtil.addSafeLog(request, "后台注销", Const.LOG_LOGOUT_TYPE,"修改密码成功后注销",Const.LOG_SUCCESS);
		 		//销毁缓存
		 		EhcacheUtil.remove(id);
		 		//销毁session
		 		getSession().invalidate();
		 		DictUtil.clearDictCache();
				return new ResponseModel(Const.LOGIN_URL); 
			}else{
				return new ResponseModel(Const.CRUD_ERROR,"USER_ID NO VALUE"); 
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("修改【"+model.getUserName()+"】密码时出错！");
			return new ResponseModel(Const.CRUD_ERROR,e.getMessage());
		}
	}
	
	/**
	 * 修改皮肤
	 * 2019年3月5日
	 * @return
	 * @author:Lynn
	 */
	@RequestMapping("/setSkin")
	@ResponseBody
	public ResponseModel setSkin() {
		try {
			ModelAndView mv = this.getModelAndView();
			String userId = mv.getModel().get("userId").toString();
			String topColor = mv.getModel().get("topColor").toString();//顶部导航颜色
			String leftColor = mv.getModel().get("leftColor").toString();//左侧导航颜色
			SessionUser sessionUser = this.getSessionUser(request);
			if(StringUtils.isNotEmpty(topColor)){
				service.saveUserConfig(userId, Const.SKIN_TOP_COLOR, topColor, true);
				sessionUser.getUserConfig().put(Const.SKIN_TOP_COLOR, topColor);//设置到session中
			}
			if(StringUtils.isNotEmpty(leftColor)){
				service.saveUserConfig(userId, Const.SKIN_LEFT_COLOR, leftColor, true);
				sessionUser.getUserConfig().put(Const.SKIN_LEFT_COLOR, leftColor);//设置到session中
			}
		    return new ResponseModel(); 
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("设置主题时出错！");
			return new ResponseModel(Const.CRUD_ERROR,e.getMessage());
		}
	}

	/**
	 * 修改用户
	 * 2018年01月03日
	 * @param m
	 * @param String roleIds
	 * @return
	 * author Lynn
	 */
	@RequestMapping(value="/updateuserandrole",method=RequestMethod.POST)
	@ResponseBody
	public ResponseModel update(SysUser m,@RequestParam(value="roleIds", required=false, defaultValue="") String roleIds){
		ModelAndView mv = this.getModelAndView();
		Map<String, ?>  map = mv.getModelMap();
		//转日期格式
		m.setUserExpDate(DateUtil.getDate(String.valueOf(map.get("userDate")), "yyyy-MM-dd"));
		m.setPwdExpDate(DateUtil.getDate(String.valueOf(map.get("pwdDate")), "yyyy-MM-dd"));
		
		ResponseModel rModel = super.update(m);
		
		UserRoleService userRoleService = (UserRoleService)BeanHelper.getBean("userRoleManager");
		rModel = userRoleService.updateUserRole(rModel,m.getUserId(), roleIds);
		return rModel;
	}
	
	/**
	 * 添加用户
	 * 2018年01月03日
	 * @param m
	 * @param String roleIds
	 * @return
	 * author Lynn
	 */
	@RequestMapping(value="/saveuserandrole",method=RequestMethod.POST)
	@ResponseBody
	public ResponseModel save(SysUser m,@RequestParam(value="roleIds", required=false, defaultValue="") String roleIds){
		ModelAndView mv = this.getModelAndView();
		Map<String, ?>  map = mv.getModelMap();
		//转日期格式
		m.setUserExpDate(DateUtil.getDate(String.valueOf(map.get("userDate")), "yyyy-MM-dd"));
		m.setPwdExpDate(DateUtil.getDate(String.valueOf(map.get("pwdDate")), "yyyy-MM-dd"));
		Encrypt encryptByMD5 = BeanHelper.getBean("encryptByMD5");
		m.setLoginPwd(encryptByMD5.encrypt(m.getLoginPwd()));
		m.setsCode(encryptByMD5.encrypt(m.getUserId()+m.getLoginName()+m.getLoginPwd()));
		ResponseModel rModel = super.save(m);
		UserRoleService userRoleService = (UserRoleService)BeanHelper.getBean("userRoleManager");
		rModel = userRoleService.updateUserRole(rModel,m.getUserId(), roleIds);
		return rModel;
	}
	
	/**
	 * 获取用户角色数据
	 * 2019年1月21日
	 * @return
	 * @author:Lynn
	 */
	@RequestMapping("/getRoles")
	@ResponseBody
	public ResponseModel getRoles(){
		ModelAndView mv = this.getModelAndView();
		String id = String.valueOf(mv.getModelMap().get("userId"));
		ResponseModel rModel = service.getRolesData(id);
		return rModel;
	}
}
