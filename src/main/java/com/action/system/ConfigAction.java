package com.action.system;

import oracle.net.aso.m;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.action.CrudAction;
import com.common.response.ResponseModel;
import com.model.system.SysConfig;
import com.service.system.ConfigService;

/**
 * 配置管理
 * @author Lynn
 * 2019年2月19日
 */
@Controller
@RequestMapping("/system/config")
public class ConfigAction extends CrudAction<ConfigService,SysConfig>{

	@Override
	public void handleListData() {
		pageUtil.setColsEdit(3, "input");
		pageUtil.setColsWidth(1, "220");
		pageUtil.setColsWidth(2, "180");
		pageUtil.setColsWidth(3, "100");
		pageUtil.setColsWidth(4, "80");
		pageUtil.setColsWidth(6, "80");
		pageUtil.setColsWidth(7, "150");
	}
	
	@RequestMapping(value="/editValue",method=RequestMethod.POST)
	@ResponseBody
	public ResponseModel editValue(@RequestParam(value="id", required=true, defaultValue="") String id,@RequestParam(value="data", required=true, defaultValue="") String data){
		model = (SysConfig)service.loadModel(model.getClass(), id);
		model.setConfigValue(data);
		return super.update(model);
	}
}
