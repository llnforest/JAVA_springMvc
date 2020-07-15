package com.action.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.action.CrudAction;
import com.common.page.Page;
import com.model.system.SysLog;
import com.service.system.LogService;

/**
 * 配置管理
 * @author Lynn
 * 2019年2月19日
 */
@Controller
@RequestMapping("/system/log")
public class LogAction extends CrudAction<LogService,SysLog>{

	@Override
	public void handleList(Page page) {
		super.handleList(page);
	}
	
	@Override
	public void handleListData(){
		super.handleListData();
		
	}
}
