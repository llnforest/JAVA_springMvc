package com.action.w2;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.xml.soap.Detail;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.action.CrudAction;
import com.action.CrudPkNumAction;
import com.common.response.ResponseModel;
import com.common.utils.Const;
import com.model.w2.W2Kcxx;
import com.service.w2.KcxxService;

/**
 * 考车信息
 * @author Lynn
 * 2019年3月8日
 */
@Controller
@RequestMapping("/w2/kcxx")
public class KcxxAction extends CrudPkNumAction<KcxxService,W2Kcxx>{

	@Override
	public void handleListData() {
		pageUtil.setColsWidth(5, "400");
		pageUtil.setColsTemplet(6, "#statusTpl");
	}
	
	/**
	 * 修改车辆状态
	 */
	@Override
	@RequestMapping(value="/setDataFlag",method=RequestMethod.POST)
	@ResponseBody
	public ResponseModel setDataFlag(@RequestParam(value="id", required=true, defaultValue="0") Integer id){
		try {
			ModelAndView mv = this.getModelAndView(model);
			String flag = mv.getModel().get("flag").toString();
			//获取到主键值，则加载对象
			if(id != 0){
				 model = (W2Kcxx) service.loadModel(model.getClass(), id);
				 if("true".equals(flag)){
					 model.setZt("1");
				 }else{
					 model.setZt("0");
				 }
				 model.setModifyId(getSessionUser(request).getUserId());
				 service.updateModel(model);
				 return new ResponseModel();
			}else{
				return new ResponseModel(Const.PARA_ERROR,Const.FAIL_NO_VALUE);
			}
		}catch(Exception e) {
			e.printStackTrace();
			logger.error("设置数据状态时出错！");
			return new ResponseModel(Const.CRUD_ERROR,e.getMessage());
		}
	}
	
	@Override
	@RequestMapping(value="/detail",method=RequestMethod.GET)
	public ModelAndView detail(@RequestParam(value="id",required=true,defaultValue="0") Integer id){
		Map<String,Object> mp = new ModelMap();
		if(StringUtils.isNotEmpty(String.valueOf(id))){
			model = (W2Kcxx)service.loadModel(model.getClass(), id);
			//车载视频
			String czip = model.getCzip();
			ArrayList<String> czList = new ArrayList<String>(Arrays.asList(czip.split(",")));
			model.setCzip(czList.get(0));
			mp.put("czport", czList.get(1));
			czList.remove(0);
			czList.remove(0);
			mp.put("czchannel",StringUtils.join(czList.toArray(),','));
			//中心视频
			String zxip = model.getZxip();
			ArrayList<String> zxList = new ArrayList<String>(Arrays.asList(zxip.split(",")));
			model.setZxip(zxList.get(0));
			mp.put("zxport",zxList.get(1));
			zxList.remove(0);
			zxList.remove(0);
			mp.put("zxchannel",StringUtils.join(zxList.toArray(),','));
			//四合一视频
			String fourip = model.getFourip();
			ArrayList<String> fourList = new ArrayList<String>(Arrays.asList(fourip.split(",")));
			model.setFourip(fourList.get(0));
			mp.put("fourport",fourList.get(1));
			fourList.remove(0);
			fourList.remove(0);
			mp.put("fourchannel",StringUtils.join(fourList.toArray(),','));
		}
		ModelAndView mv = this.getModelAndView(model);
		mv.addAllObjects(mp);
		return mv;
	}
	
	/**
	 * 保存
	 */
	@Override
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public ResponseModel save(W2Kcxx m){
		ModelAndView mv = this.getModelAndView();
		Map<String,?> mp = mv.getModelMap();
		m.setCzip(String.valueOf(mp.get("czip"))+","+String.valueOf(mp.get("czport"))+","+String.valueOf(mp.get("czchannel")));
		m.setZxip(String.valueOf(mp.get("zxip"))+","+String.valueOf(mp.get("zxport"))+","+String.valueOf(mp.get("zxchannel")));
		m.setFourip(String.valueOf(mp.get("fourip"))+","+String.valueOf(mp.get("fourport"))+","+String.valueOf(mp.get("fourchannel")));
		return super.save(m);
	}
	
	/**
	 * 修改
	 */
	@Override
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public ResponseModel update(W2Kcxx m){
		ModelAndView mv = this.getModelAndView();
		Map<String,?> mp = mv.getModelMap();
		m.setCzip(String.valueOf(mp.get("czip"))+","+String.valueOf(mp.get("czport"))+","+String.valueOf(mp.get("czchannel")));
		m.setZxip(String.valueOf(mp.get("zxip"))+","+String.valueOf(mp.get("zxport"))+","+String.valueOf(mp.get("zxchannel")));
		m.setFourip(String.valueOf(mp.get("fourip"))+","+String.valueOf(mp.get("fourport"))+","+String.valueOf(mp.get("fourchannel")));
		return super.update(m);
	}
	
}
