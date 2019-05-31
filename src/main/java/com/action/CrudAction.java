package com.action;

import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.common.page.Page;
import com.common.page.PageDataTrans;
import com.common.page.PageUtil;
import com.common.response.ResponseModel;
import com.common.utils.Const;
import com.model.BaseModel;
import com.service.BaseService;


/**  
 * 1.使用泛型注入service层和model层类,通过@Autowired按类型依赖注入到CRUD属性中
 * 2.当或者M按类型匹配到多个实例时，则按照变量名称查找been注入
 * 3.@Autowired 时可以结合@Qualifier("XXX")来指定最终注入的实例
 * @author:wangzhen
 * @version:V1.0
 * 2017年8月24日  
 */
public class CrudAction<T extends BaseService,M extends BaseModel> extends BaseAction<T,M>{
	
	@Autowired
	protected PageUtil pageUtil;
	
	/**
	 * 该方法用于处理页面的首页请求，本方法提供一个缺省实现
	 * 2017年9月22日
	 * @return
	 * author:wangzhen
	 */
	@RequestMapping(value="**/index",method=RequestMethod.GET)
	public ModelAndView index(){
		ModelAndView mv = this.getModelAndView();
		return mv; 
	}
	
	/**
	 * 该方法用于显示页面分页列表，本方法提供一个缺省实现
	 * 在无特殊业务逻辑时可以直接使用本方法
	 * 2017年9月11日
	 * @return
	 * @throws Exception
	 * author:wangzhen
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/list")
	public ModelAndView list(){
		ModelAndView mv = this.getModelAndView();
		init(mv);
		//获取列表页面显示数据sql/hql语句
		Map sqlMap = (Map)parasMap.get(Const.SQL_MAP);
		if(MapUtils.isEmpty(sqlMap)){
			//未设置分页sql，则使用默认方法中提供的sql
			sqlMap = service.getSqlMap(mv.getModelMap());
		}
		//sql中存放参数的数组
		Object[] sqlPara = null;
		String pageSql = (String)sqlMap.get(Const.PAGE_SQL);
		if(CollectionUtils.isNotEmpty((List)sqlMap.get(Const.SQL_PARA))){
			sqlPara = ((List)sqlMap.get(Const.SQL_PARA)).toArray();
		}
		//获取分页信息（当前显示页码和页面显示数据大小）
		Page page = new Page(mv);
		
		try {
			page = service.getPageListByHql(pageSql, page,sqlPara);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取分页数据出错，出错语句："+pageSql, e);
		}
		//转换列表数据
		handleListData();
		page = PageDataTrans.TransPageCols(page, pageUtil);
		mv.addObject(page);
		service.getUserAuth(getSessionUser(request).getUserId(),(Set<String>) request.getSession().getAttribute(Const.SESSION_AUTH),mv);
		return mv;
	}
	
	
	
	/**
	 * 该方法用于显示页面分页列表，本方法提供一个缺省实现
	 * 在无特殊业务逻辑时可以直接使用本方法
	 * 2017年9月11日
	 * @return
	 * @throws Exception
	 * author:wangzhen
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/listData")
 	@ResponseBody
	public JSONObject listData(){
		ModelAndView mv = this.getModelAndView();
		init(mv);
		//获取列表页面显示数据sql/hql语句
		Map sqlMap = (Map)parasMap.get(Const.SQL_MAP);
		if(MapUtils.isEmpty(sqlMap)){
			//未设置分页sql，则使用默认方法中提供的sql
			sqlMap = service.getSqlMap(mv.getModelMap());
		}
		//sql中存放参数的数组
		Object[] sqlPara = null;
		String pageSql = (String)sqlMap.get(Const.PAGE_SQL);
		if(CollectionUtils.isNotEmpty((List)sqlMap.get(Const.SQL_PARA))){
			sqlPara = ((List)sqlMap.get(Const.SQL_PARA)).toArray();
		}
		//获取分页信息（当前显示页码和页面显示数据大小）
		Page page = new Page(mv);
		try {
			//判断是否使用原生sql进行查询
			if(sqlMap.get(Const.USE_SQL)!=null && (boolean)sqlMap.get(Const.USE_SQL)){
				page = service.getPageListBySql(pageSql,page,sqlPara);
			}else{
				page = service.getPageListByHql(pageSql,page,sqlPara);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取分页数据出错，出错语句："+pageSql, e);
		}
		//转换列表数据
		handleListData();
		return PageDataTrans.transData(page,pageUtil);	
	}
	
	
	/**
	 * 该方法用于显示业务表单界面
	 * 1.当接收不到id值时为新增界面
	 * 2.当接收到id值时为修改界面
	 * 在无特殊业务逻辑时可以直接使用本方法
	 * 2017年10月18日
	 * @param id 接收实体对象的主键id值
	 * @return
	 * author:wangzhen
	 */
	@SuppressWarnings("all")
	@RequestMapping(value="/detail",method=RequestMethod.GET)
	public ModelAndView detail(@RequestParam(value="id", required=false, defaultValue="") String id){
		//获取到主键值，则加载对象
		if(StringUtils.isNotEmpty(id)){
			 model = (M) service.loadModel(model.getClass(), id);
		}
		ModelAndView mv = this.getModelAndView(model);
		return mv; 
	}

	/**
	 * 该方法用于处理页面的新增请求，本方法提供一个缺省实现
	 * 在无特殊业务逻辑时保存单表数据可以直接使用本方法
	 * 2017年9月22日
	 * @param m
	 * @return
	 * author:wangzhen
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
    @ResponseBody
	public ResponseModel save(M m){
		try {
			service.beforeSaveModel(m);
			service.saveModel(m);
			service.afterSaveModel(m);
			return new ResponseModel(this.getModelAndView(m)); 
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("保存"+m.getClass().getName()+"对象出错！");
			return new ResponseModel(Const.CRUD_ERROR,e.getMessage());
		}
	}
	
	/**
	 * 该方法用于处理页面的修改请求，本方法提供一个缺省实现
	 * 在无特殊业务逻辑时修改单表数据可以直接使用本方法
	 * 2017年9月22日
	 * @param m
	 * @return
	 * author:wangzhen
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public ResponseModel update(M m){
		try {
			service.beforeUpdateModel(m);
			service.updateModel(m);
			service.afterUpdateModel(m);
			ModelAndView mv = this.getModelAndView();
			mv.clear();
			return new ResponseModel(this.getModelAndView(m)); 
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("修改"+m.getClass().getName()+"对象出错！");
			return new ResponseModel(Const.CRUD_ERROR,e.getMessage());
		}
	}
	
	/**
	 * 该方法用于处理页面的删除请求，本方法提供一个缺省实现
	 * 在无特殊业务逻辑时删除持久化数据可以直接使用本方法
	 * 2017年9月22日
	 * @param m
	 * @return
	 * author:wangzhen
	 */
	@RequestMapping(value="/delete/{id}",method=RequestMethod.POST)
    @ResponseBody
	public ResponseModel delete(@PathVariable(value="id") String id){
		try {
			if(service.beforeDeleteModel(id)){
				service.deleteModelById(model.getClass(), id);
				service.afterDeleteModel(id);
				return new ResponseModel();
			}
			return new ResponseModel(Const.CRUD_ERROR,"删除失败");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除"+model.getClass().getName()+"对象出错！");
			return new ResponseModel(Const.CRUD_ERROR,e.getMessage());
		}
	}
	
	/**
	 * 该方法用于设置数据状态dataFlag的启用/禁用状态
	 * 2017年9月22日
	 * @param id
	 * @return
	 * @author:wangzhen
	 */
	@SuppressWarnings("all")
	@RequestMapping(value="/setDataFlag",method=RequestMethod.POST)
	@ResponseBody
	public ResponseModel setDataFlag(@RequestParam(value="id", required=false, defaultValue="") String id){
		try {
			ModelAndView mv = this.getModelAndView(model);
			String flag = mv.getModel().get("flag").toString();
			//获取到主键值，则加载对象
			if(StringUtils.isNotEmpty(id)){
				 model = (M) service.loadModel(model.getClass(), id);
				 if("true".equals(flag)){
					 model.setDataFlag("Y");
				 }else{
					 model.setDataFlag(null);
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
	
	/**
	 * 该方法用于显示标签（选项卡）页，本方法提供一个缺省跳转请求
	 * 2017年8月24日
	 * @return
	 * author:wangzhen
	 */
	@RequestMapping("/tab")
	public ModelAndView tab(){
		ModelAndView mv = this.getModelAndView();
		init(mv);
		return mv;
	}
	

	/**
	 * 该方法用于转发请求，本方法提供一个缺省跳转请求
	 * 2017年8月25日
	 * @param forward 转发地址
	 * @return
	 * author:wangzhen
	 */
	@RequestMapping("/forward")
	public ModelAndView forword(@RequestParam(value="forward", required=true) String forward){
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("forward:"+forward);
		return mv;
	}
	
	/**
	 * 该方法用于处理页面的树形结构请求，本方法提供一个缺省实现
	 * 2018年5月22日
	 * @return
	 * author:wangzhen
	 */
	@RequestMapping("/tree")
	public ModelAndView tree(){
		ModelAndView mv = this.getModelAndView();
		
		mv.addObject("abb", "abbssdsds");
		request.setAttribute("bbb", "sss");
		mv.setViewName("forward:"+request.getRequestURI().replace("tree", "list"));
		System.out.println(mv.getModel().get("abb"));
		return mv;
	}
	

}
