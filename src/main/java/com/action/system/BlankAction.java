package com.action.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.action.CrudAction;
import com.common.page.Page;
import com.common.response.ResponseModel;
import com.model.system.SysBlank;
import com.service.system.BlankService;

/**
 * 配置管理
 * @author Lynn
 * 2019年2月19日
 */
@Controller
@RequestMapping("/system/blank")
public class BlankAction extends CrudAction<BlankService,SysBlank>{

	
	@Override
	public void handleList(Page page) {
		super.handleList(page);
//		pageUtil.setColsTemplet(4, "#statusTpl");
		pageUtil.setShowNumbers(false);
		pageUtil.setShowCheckbox(true);
		pageUtil.setToolbarId("listBarWith");
	}
	
	@Override
	public void handleListData(){
		super.handleListData();
	}
	
	
	@RequestMapping("/list")
	public ModelAndView list(){
		service.autoUnLock();
		return super.list();
	}
	/**
	 * 批量解锁黑名单
	 * 2019年2月20日
	 * @return
	 * @author:Lynn
	 */
	
	@RequestMapping("/batchUnlock")
 	@ResponseBody
	public ResponseModel batchUnlock(){
		ModelAndView mv = this.getModelAndView();
		mv.addObject("modifyId",getSessionUser(request).getUserId());
		ResponseModel rModel = service.batchUnlock(mv.getModelMap());
		return rModel;
	}
}
