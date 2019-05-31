package com.action.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.action.CrudAction;
import com.model.system.SysDict;
import com.service.system.DictService;

/**  
 *  系统字典管理功能
 * @author:wangzhen
 * @version:V1.0
 * 2018年2月25日  
 */
@Controller
@RequestMapping("/system/dict")
public class DictAction extends CrudAction<DictService,SysDict>{

	@Override
	public void init(ModelAndView mv) {
		
	}

	@Override
	public void handleListData() {
		pageUtil.setDataDict(3, "dataStruct");
	}
	

}
