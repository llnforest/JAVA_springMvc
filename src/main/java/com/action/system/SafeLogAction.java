package com.action.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.action.CrudAction;
import com.model.system.SysLog;
import com.service.system.LogService;
import com.service.system.SafeLogService;

/**
 * 安全日志管理
 * @author Lynn
 * 2019年2月19日
 */
@Controller
@RequestMapping("/system/safelog")
public class SafeLogAction extends CrudAction<SafeLogService,SysLog>{

	@Override
	public void handleListData() {
//		pageUtil.setColsWidth(8, "80");
		pageUtil.setDataDict(4, "operateResult");
	}
}
