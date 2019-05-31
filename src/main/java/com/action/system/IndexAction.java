package com.action.system;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.action.BaseAction;
import com.common.response.ResponseModel;
import com.common.utils.AppConfig;
import com.common.utils.Const;
import com.common.utils.DateUtil;
import com.model.BaseModel;
import com.model.SessionUser;
import com.model.system.SysLog;
import com.model.system.SysUser;
import com.service.system.IndexService;

/**  
 * 系统首页请求页面
 * 可以在该action初始化系统参数，用户参数如：主题、皮肤等
 * @author:wangzhen
 * @version:V1.0
 * 2017年9月5日  
 */
@Controller
public class IndexAction extends BaseAction<IndexService,BaseModel>{
	
	    /**
	     * 系统首页控制方法
	     * 2018年5月7日
	     * @return
	     * author:wangzhen
	     */
	 	@RequestMapping(Const.INDEX_URL)
	    public ModelAndView index(){
	 		ModelAndView mv = new ModelAndView();
	 		//获取系统名称，用于页面title显示
	 		mv.addObject("appName",AppConfig.getAppName());
	 		mv.setViewName("/index/index");
	 		return mv;
	    }
	 	
	 	
	 	/**
	 	 * 获取当前登录用户一级菜单权限数据
	 	 * 2018年5月9日
	 	 * @return
	 	 * author:wangzhen
	 	 */
	 	@SuppressWarnings("unchecked")
		@RequestMapping("/index/topMenu")
	 	@ResponseBody
	    public JSONArray topMenu(){
	 		Set<String> menuSet = (Set<String>) request.getSession().getAttribute(Const.SESSION_AUTH);
	 		String topMenuStr = service.getTopMenuData(getSessionUser(request).getUserId(),menuSet);
	 		JSONArray topMenuJson = JSONArray.fromObject(topMenuStr);
	 		return topMenuJson;
	    }

	 	/**
	 	 * 根据一级菜单标识获取当前用户二级菜单权限数据
	 	 * 2018年5月9日
	 	 * @param menuId 一级菜单ID
	 	 * @return
	 	 * author:wangzhen
	 	 */
	 	@SuppressWarnings("unchecked")
	 	@RequestMapping("/index/leftMenu/{menuId}")
	 	@ResponseBody
	    public JSONArray leftMenu(@PathVariable(value="menuId") String menuId){
	 		HttpSession session = request.getSession();
	 		String leftMenuStr = (String)session.getAttribute(menuId);
	 		if(StringUtils.isEmpty(leftMenuStr)){
	 			Set<String> menuSet = (Set<String>) request.getSession().getAttribute(Const.SESSION_AUTH);
	 			leftMenuStr = service.getLeftMenuData(getSessionUser(request).getUserId(),menuId,menuSet);
	 			session.setAttribute(menuId, leftMenuStr);
	 		}
	 		JSONArray leftMenuJson = JSONArray.fromObject(leftMenuStr);
	 		return leftMenuJson;
	 		
	    }
	 	
	    /**
	     * 系统首页控制方法
	     * 2018年5月7日
	     * @return
	     * author:wangzhen
	     * @throws Exception 
	     */
	 	@SuppressWarnings("unchecked")
		@RequestMapping("/index/console")
	    public ModelAndView console(){
	 		SessionUser sessionUser = getSessionUser(request);
	 		List<?> list = service.getListWithLimitByHql("from SysLog obj where operateResult = ? and appUrl = ? order by obj.createTime desc", "0", "2", new Object[]{"1",Const.LOGIN_URL});
	 		SysUser sysUser = sessionUser.getUser();
	 		ModelAndView mv = this.getModelAndView(sysUser);
	 		//获取系统名称，用于页面title显示
	 		mv.addObject("appName",AppConfig.getAppName());
	 		long pwdMills = (long)sysUser.getPwdExpDate().getTime();
	 		long userMills = (long)sysUser.getUserExpDate().getTime();
	 		long nowMills = (long)System.currentTimeMillis() - 24*3600*1000;
	 		int userDate = (int)((userMills - nowMills)/(24*3600*1000));
	 		int pwdDate = (int)((pwdMills - nowMills)/(24*3600*1000));
	 		mv.addObject("userDate",userDate);
	 		mv.addObject("pwdDate",pwdDate);
	 		SysLog firstTime = null,secondTime = null;
	 		firstTime = (SysLog)list.get(0);
	 		List<SysLog> failList = null;
	 		if(list.size() == 2){
	 			//存在上次登录
	 			secondTime = (SysLog)list.get(1);
				try {
					failList = (List<SysLog>)service.getListByHql("from SysLog obj where operateResult = ? and appUrl = ? and obj.createTime > ? order by obj.createTime desc", new Object[]{"2",Const.LOGIN_URL,secondTime.getCreateTime()});
				} catch (Exception e) {
					e.printStackTrace();
				}
	 		}
	 		mv.addObject("failList",failList);
	 		mv.addObject("firstTime",firstTime);
	 		mv.addObject("secondTime",secondTime);
	 		mv.setViewName("/index/console");
	 		return mv;
	    }


	 	/**
	 	 * 上传文件并获取文件地址
	 	 * 2018年9月19日
	 	 * @return
	 	 * author:wangzhen
	 	 */
	 	@RequestMapping("/upload")
	 	@ResponseBody
	    public ResponseModel upload(@RequestParam("file") MultipartFile uploadFile){
	 		Map<String, Object> map = new HashMap<String, Object>();
	 		if(uploadFile==null||uploadFile.getSize()==0){
	 			ResponseModel responseModel = new ResponseModel("-1","未获取到文件！");
	 			return responseModel;
	 		}
	 		//文件名称
	 		String filename = uploadFile.getOriginalFilename();
	 		//取到上下文目录下名称为“file”文件夹的绝对路径
	 		String rootPath = request.getSession().getServletContext().getRealPath("/");
	 		//按照业务、文件类型、时间创建分级目录，目前以年月区分
	 		String busiPath = "/file/" + DateUtil.getNowDateStr("yyyyMM")+"/";
	 		//文件类型校验待完成? TODO
	 		//文件元数据获取和存储? TODO
	 		
	 		File filepath = new File(rootPath,busiPath+filename);
	        //判断路径是否存在
	        if(!filepath.getParentFile().exists()){
	             filepath.getParentFile().mkdirs();
	        }
	 		
	 		try {
	 			//转存附件
				uploadFile.transferTo(filepath);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	 		map.put("filepath", busiPath+filename);
	 		map.put("filename", filename);
	 		ResponseModel responseModel = new ResponseModel(map);
	 		return responseModel;
	    }

}
