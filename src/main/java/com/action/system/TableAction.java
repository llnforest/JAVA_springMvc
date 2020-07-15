package com.action.system;

import java.util.List;

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
import com.common.tree.Node;
import com.common.utils.Const;
import com.model.system.SysMenu;
import com.model.system.SysTable;
import com.service.system.MenuService;
import com.service.system.TableService;

/**  
 *  功能字段管理功能
 * @author:wangzhen
 * @version:V1.0
 * 2018年2月25日  
 */
@Controller
@RequestMapping("/system/table")
public class TableAction extends CrudAction<TableService,SysTable>{

	@Override
	public void init(ModelAndView mv) {
		
	}
	
	@Override
	public void handleList(Page page) {
		super.handleList(page);
		pageUtil.setFixedNumbers(true);
		pageUtil.setColsFixed(1, "left");
	}
	
	@Override
	public void handleListData(){
		super.handleListData();
	}
	

}
