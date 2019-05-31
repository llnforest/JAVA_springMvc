package com.action.system;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.action.CrudAction;
import com.common.page.PageUtil;
import com.common.spring.BeanHelper;
import com.common.utils.StringUtil;
import com.model.BaseModel;
import com.model.system.SysDict;
import com.model.system.SysDictValue;
import com.service.system.DictService;
import com.service.system.DictValueService;

/**
 * 字典参数管理
 * @author Lynn
 * 2019年1月10日
 */
@Controller
@RequestMapping("/system/dictvalue")
public class DictValueAction extends CrudAction<DictValueService, SysDictValue>{

	@Override
	public void handleListData(){
		pageUtil.setColsTemplet(4, "#colorTpl");
	}
	
	@RequestMapping(value="/showValue",method=RequestMethod.GET)
	public ModelAndView showValue(){
		String url = StringUtil.convertUrl(request.getRequestURI(), StringUtil.convertNull(request.getQueryString()));
		return new ModelAndView("redirect:"+url.replace("showValue", "list"));
	}
	
	/**
	 * 该方法用于显示字典参数添加修改页面
	 * 1.当接收不到id值时为新增界面
	 * 2.当接收到id值时为修改界面
	 * 2018年12月25日
	 * @param id 接收实体对象的主键id值
	 * @return
	 * author:Lynn
	 */
	@SuppressWarnings("all")
	@RequestMapping(value="/detail",method=RequestMethod.GET)
	public ModelAndView detail(@RequestParam(value="id", required=false, defaultValue="") String id){
		//获取到主键值，则加载对象
//		if(StringUtils.isNotEmpty(id)){
//			 model = (SysDictValue) service.loadModel(model.getClass(), id);
//		}
//		ModelAndView mv = this.getModelAndView(model);
		ModelAndView mv = super.detail(id);
		DictService dictService = (DictService)BeanHelper.getBean("dictManager");
		List<SysDict> dictList = (List<SysDict>)dictService.getDictList();
		mv.addObject("dictList",dictList);
		return mv; 
	}
}
