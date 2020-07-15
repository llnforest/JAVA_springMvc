package com.action.system;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.action.BaseAction;
import com.common.utils.EhcacheUtil;
import com.common.utils.RequestUtil;
import com.model.BaseModel;
import com.service.BaseService;

/**
 * 定时脚本
 * @author Lynn
 * 2019年2月26日
 */
@Controller
@RequestMapping("/system/timer")
public class TimerAction  extends BaseAction<BaseService,BaseModel>{
	 	
	@RequestMapping("/put")
 	@ResponseBody
    public String put(){
		return RequestUtil.getParamer(request);
    }
	 
	@RequestMapping("/get")
 	@ResponseBody
    public Long get(){
//		System.out.println((long)EhcacheUtil.get("id").getObjectValue());
//		System.out.println((long)EhcacheUtil.get("id").getLastAccessTime());
		System.out.println((long)EhcacheUtil.get("id").getNextToLastAccessTime());
		System.out.println((long)EhcacheUtil.get("id").getLastUpdateTime());
		System.out.println((long)EhcacheUtil.get("id").getExpirationTime());
		System.out.println((long)EhcacheUtil.get("id").getCreationTime());
		System.out.println(String.valueOf(System.currentTimeMillis()));
 		return ((long)EhcacheUtil.get("id").getValue()) + 20000;
    }
	 	
	 	

}
