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
import com.common.response.ResponseModel;
import com.common.tree.Node;
import com.common.utils.Const;
import com.model.system.SysMenu;
import com.service.system.MenuService;

/**  
 *  系统菜单管理功能
 * @author:wangzhen
 * @version:V1.0
 * 2018年2月25日  
 */
@Controller
@RequestMapping("/system/menu")
public class MenuAction extends CrudAction<MenuService,SysMenu>{

	@Override
	public void init(ModelAndView mv) {
		
	}
	
	@Override
	public void handleListData() {
		pageUtil.setShowNumbers(true);
		pageUtil.setColsWidth(1, "80");
		pageUtil.setDataDict(4, "businessType");
		pageUtil.setColsWidth(9, "250");
		pageUtil.setColsWidth(5, "120");
		pageUtil.setColsWidth(6, "120");
		pageUtil.setDataDict(5, "menuType");
		pageUtil.setDataDict(6, "logLevel");
		pageUtil.setColsEdit(8,"input");
	};
	
	//删除菜单
	@RequestMapping(value="/delete/{id}",method=RequestMethod.POST)
    @ResponseBody
	public ResponseModel delete(@PathVariable(value="id") String id){
		return service.deleteMenu(id);
	}
	
	/**
	 * 获取菜单树形数据
	 * 2019年2月18日
	 * @return
	 * @author:Lynn
	 */
	@RequestMapping(value="/treeData")
	@ResponseBody
	public ResponseModel treeData(){
		ModelAndView mv = this.getModelAndView();
		init(mv);
		List<Node> dataList = service.getTreeData(mv.getModelMap());
		ResponseModel rModel = new ResponseModel(dataList);
		return rModel;
	}
	
	/**
	 * 修改排序
	 * 2019年3月4日
	 * @param id
	 * @param data
	 * @return
	 * @author:Lynn
	 */
	@RequestMapping(value="/editOrder",method=RequestMethod.POST)
	@ResponseBody
	public ResponseModel editOrder(@RequestParam(value="id", required=true, defaultValue="") String id,@RequestParam(value="data", required=true, defaultValue="") Integer data){
		
		try {
			service.executeHql("update SysMenu set menuOrder = ? where menuId = ?", new Object[]{data,id});
			return new ResponseModel();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseModel(Const.CRUD_ERROR,e.getMessage());
		}
	}

}
