package com.action.w2;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.tagext.FunctionInfo;
import javax.xml.soap.Detail;

import oracle.net.aso.m;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.action.CrudAction;
import com.common.page.Page;
import com.common.response.ResponseModel;
import com.common.utils.Const;
import com.common.utils.DictUtil;
import com.model.system.SysDictValue;
import com.model.w2.W2Kcxx;
import com.model.w2.W2Queuing;
import com.service.w2.KcxxService;
import com.service.w2.QueuingService;

/**
 * 考车信息
 * @author Lynn
 * 2019年3月8日
 */
@Controller
@RequestMapping("/w2/queuing")
public class QueuingAction extends CrudAction<QueuingService,W2Queuing>{

	@Override
	public void handleList(Page page) {
		super.handleList(page);
	}
	
	@Override
	public void handleListData(){
		super.handleListData();
		pageUtil.setColsEditCol(10, this, "getUndoProject","9,10");
		
	}
	
	/**
	 * 获取未完成的项目名称（字符串list）
	 * 2019年3月15日
	 * @param allProject
	 * @param doProject
	 * @return
	 * @author:Lynn
	 */
	public String getUndoProject(String allProject,String doProject){
		ArrayList<String> allArrayList = new ArrayList<String>(Arrays.asList(allProject.split(",")));
		if(StringUtils.isNotEmpty(doProject)){
			ArrayList<String> doArrayList = new ArrayList<String>(Arrays.asList(doProject.split(",")));
			allArrayList.removeAll(doArrayList);
		}
		return service.getProjectNames(allArrayList);
		
	}
	
	@Override
	@RequestMapping(value={"/detail","/detailByUpdateStatus"},method=RequestMethod.GET)
	public ModelAndView detail(@RequestParam(value="id", required=true, defaultValue="0") String id){
		//获取到主键值，则加载对象
		int pk = Integer.parseInt(id);
		if(pk != 0){
			 model = (W2Queuing) service.loadModel(model.getClass(), pk);
			 model.setWcxm(this.getUndoProject(model.getKsxm(), model.getWcxm()));
		}
		ModelAndView mv = this.getModelAndView(model);
		return mv; 
	}
	
	@RequestMapping(value="/updateStatus",method=RequestMethod.POST)
	@ResponseBody
	public ResponseModel updateStatus(){
		ModelAndView mv = this.getModelAndView();
		HashMap<String, Object> mp = mv.getModelMap();
		if(StringUtils.isNotEmpty(String.valueOf(mp.get("id")))){
			model = (W2Queuing)service.loadModel(model.getClass(), Integer.parseInt(String.valueOf(mp.get("id"))));
			if(StringUtils.isNotEmpty(String.valueOf(mp.get("ksxm")))) model.setKsxm(String.valueOf(mp.get("ksxm")));
			model.setDjc(String.valueOf(mp.get("djc")));
			model.setZt(String.valueOf(mp.get("zt")));
			return super.update(model);
		}
		return new ResponseModel(Const.PARA_ERROR,Const.ERROR_PARAMS);
	}
	
	@RequestMapping(value="/getProject")
	@ResponseBody
	public ResponseModel getProject(@RequestParam(value="pids",required=false,defaultValue="") String pids){
		ResponseModel rModel = new ResponseModel();
		List<Object> data = new ArrayList<Object>();
		List<String> pidList= Arrays.asList(pids.split(","));
		logger.info(pidList); 
		logger.info(pidList.contains("10")); 
		try {
			List<?> list = service.getListByHql("from SysDictValue where sysDict.dictCode = ? order by valOrder", new Object[]{"examProject"});
			if(CollectionUtils.isNotEmpty(list)){
				for(int i=0; i<list.size(); i++){
					Map<String, Object> map = new HashMap<String, Object>(); 
					SysDictValue dictValue = (SysDictValue)list.get(i);
					map.put("name", dictValue.getValName());
					map.put("value", dictValue.getValCode());
					map.put("selected", pidList.contains(dictValue.getValCode()));
					data.add(map);
				}
				logger.info(data);
				rModel.setData(data);
			}
		} catch (Exception e) {
			rModel.setCodeAndDesc(Const.ERROR_CODE, Const.OPERATE_ERROR);
			e.printStackTrace();
		}
		return rModel;
	}
	
	
	
	
	
	
	
}
