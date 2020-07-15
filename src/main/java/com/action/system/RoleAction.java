package com.action.system;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.action.CrudAction;
import com.common.page.Page;
import com.common.response.ResponseModel;
import com.common.spring.BeanHelper;
import com.common.utils.Const;
import com.model.system.SysRole;
import com.service.system.RoleMenuService;
import com.service.system.RoleService;
import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

/**  
 *  系统角色管理功能
 * @author:Lynn
 * @version:V1.0
 * 2018年12月29日  
 */
@Controller
@RequestMapping("/system/role")
public class RoleAction extends CrudAction<RoleService,SysRole>{
	@Override
	public void init(ModelAndView mv) {
		
	};
	
	@Override
	public void handleList(Page page) {
		super.handleList(page);
	}
	
	@Override
	public void handleListData(){
		super.handleListData();
	}
	
	//权限页面
	@RequestMapping("/authTree")
	public ModelAndView authTree(@RequestParam(value="roleId",defaultValue="",required=false) String roleId){
		logger.info(roleId);
		
		ModelAndView mv = this.getModelAndView(model);
//		mv.addObject("roleMenu",roleMenuList);
		return mv;
	}
		
	//获取权限
	@RequestMapping(value = "/getAuth",method = RequestMethod.POST)
	@ResponseBody
	public ResponseModel getAuth(@RequestParam(value="roleId",defaultValue="",required=true) String roleId){
		logger.info(roleId);
		List<HashMap<String, Object>> roleMenuList = null;
		if(StringUtils.isNotEmpty(roleId)){
			model = (SysRole) service.loadModel(model.getClass(), roleId);
			RoleMenuService roleMenuService = (RoleMenuService)BeanHelper.getBean("roleMenuManager");
			roleMenuList = roleMenuService.getRoleMenuByLevel(roleId);
		}
		return new ResponseModel(roleMenuList);
	}
		
	//权限页面
	@RequestMapping("/auth")
	public ModelAndView auth(@RequestParam(value="roleId",defaultValue="",required=true) String roleId){
		logger.info(roleId);
		if(StringUtils.isNotEmpty(roleId)){
			model = (SysRole) service.loadModel(model.getClass(), roleId);
		}
		ModelAndView mv = this.getModelAndView(model);
		return mv;
	}
	
	//修改权限接口
	@RequestMapping(value= "/editauth",method = RequestMethod.POST)
	@ResponseBody
	public ResponseModel editauth(@RequestParam(value="roleId",defaultValue="",required=true) String roleId,
								  @RequestParam(value="newMenus",defaultValue="",required=true) String newMenus){
		RoleMenuService roleMenuService = (RoleMenuService)BeanHelper.getBean("roleMenuManager");
		String[] newMenusArr = newMenus.split(",");
		ResponseModel responseModel = roleMenuService.updateRoleMenu(roleId, newMenusArr);
		ModelAndView mv = this.getModelAndView();
		mv.clear();
		return responseModel;
	}
	
	//获取所有的角色列表
	@SuppressWarnings("unchecked")
	@RequestMapping(value= "/getrolelist",method = RequestMethod.POST)
	@ResponseBody
	public ResponseModel getrolelist(){
		List<SysRole> roleList = (List<SysRole>)service.getAllModel(model.getClass());
		return new ResponseModel(roleList);
	}
	
	//删除角色
	@RequestMapping(value="/delete/{id}",method=RequestMethod.POST)
    @ResponseBody
	public ResponseModel delete(@PathVariable(value="id") String id){
		return service.deleteRole(id);
	}

}
