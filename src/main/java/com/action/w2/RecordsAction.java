package com.action.w2;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.action.CrudAction;
import com.common.page.Page;
import com.common.page.PageDataTrans;
import com.common.response.ResponseModel;
import com.common.utils.Const;
import com.model.w2.W2Flowzp;
import com.model.w2.W2Records;
import com.service.w2.RecordsService;

/**
 * 考车信息
 * @author Lynn
 * 2019年3月8日
 */
@Controller
@RequestMapping("/w2/records")
public class RecordsAction extends CrudAction<RecordsService,W2Records>{

//	@Override
//	public void handleList(Page page) {
////		pageUtil.setShowNumbers(false);
//		pageUtil.setShowRadio(true);
//		pageUtil.setColsWidth(new String[]{"0","140","120","180","140","150","70","70","100","100","100","120","120","120","300","80","120","120","120","300","80","120","80","120","200","320","120"});
////		pageUtil.setColsWidth(5, "400");
//		Map<Integer, String> colsStyleMap = new HashMap<Integer, String>();
//		colsStyleMap.put(12, "background-color:#a8e0b2");
//		colsStyleMap.put(13, "background-color:#a8e0b2");
//		colsStyleMap.put(14, "background-color:#a8e0b2");
//		colsStyleMap.put(15, "background-color:#a8e0b2");
//		colsStyleMap.put(16, "background-color:#a8e0b2");
//		colsStyleMap.put(18, "background-color:#e0b4d9");
//		colsStyleMap.put(19, "background-color:#e0b4d9");
//		colsStyleMap.put(20, "background-color:#e0b4d9");
//		colsStyleMap.put(21, "background-color:#e0b4d9");
//		colsStyleMap.put(17, "background-color:#e0b4d9");
//		pageUtil.setColsStyleMap(colsStyleMap);
//		pageUtil.setDataDict(5, "examResult");
//		pageUtil.setDataDict(8, "examReason");
//		pageUtil.setDataDict(22, "examPrint");
//		pageUtil.setColsJsfunc(11, "'#'.substring(0,10)");
//		pageUtil.setColsJsfunc(12, "'#'.substring(11,19)");
//		pageUtil.setColsJsfunc(13, "'#'.substring(11,19)");
//		pageUtil.setColsJsfunc(17, "'#'.substring(11,19)");
//		pageUtil.setColsJsfunc(18, "'#'.substring(11,19)");
//		pageUtil.setColsEditCol(14, this, "getWrongReason","14");
//		pageUtil.setColsEditCol(19, this, "getWrongReason","19");
//	}
	
	@Override
	public void handleList(Page page) {
		super.handleList(page);
	}
	
	@Override
	public void handleListData(){
		super.handleListData();
		pageUtil.setColsEditCol(14, this, "getWrongReason","14");
		pageUtil.setColsEditCol(19, this, "getWrongReason","19");
		
	}
	
	public String getWrongReason(String codes){
		logger.info(codes);
		String wrongReason = service.getWrongReason(codes);
		return wrongReason;
	}
	/**
	 * 查看明细
	 * 2019年3月18日
	 * @return
	 * @author:Lynn
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/flow",method=RequestMethod.GET)
	public  ModelAndView flow(@RequestParam(value="ksbh",required=true,defaultValue="0") String ksbh,@RequestParam(value="ksrq",required=true,defaultValue="0") String ksrq){
		ModelAndView mv = this.getModelAndView();
		init(mv);
		//获取列表页面显示数据sql/hql语句
		Map sqlMap = (Map)parasMap.get(Const.SQL_MAP);
		if(MapUtils.isEmpty(sqlMap)){
			//未设置分页sql，则使用默认方法中提供的sql
			sqlMap = service.getSqlMapByFlow(ksbh,ksrq);
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
		handleFlowData();
		page = PageDataTrans.TransPageCols(page, pageUtil);
		mv.addObject(page);
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
	@RequestMapping("/flowList")
 	@ResponseBody
	public JSONObject flowList(@RequestParam(value="ksbh",required=true,defaultValue="0") String ksbh,@RequestParam(value="ksrq",required=true,defaultValue="0") String ksrq){
		ModelAndView mv = this.getModelAndView();
		init(mv);
		//获取列表页面显示数据sql/hql语句
		Map sqlMap = (Map)parasMap.get(Const.SQL_MAP);
		if(MapUtils.isEmpty(sqlMap)){
			//未设置分页sql，则使用默认方法中提供的sql
			sqlMap = service.getSqlMapByFlow(ksbh, ksrq);
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
		handleFlowData();
		return PageDataTrans.transData(page,pageUtil);	
	}
	
	public void handleFlowData(){
		pageUtil.setColsWidth(new String[]{"0","70","100","180","300","86"});
		pageUtil.setShowPage(false);
		pageUtil.setColsSort(false);
		pageUtil.setToolbar(false);
		pageUtil.setColsEditCol(4,  this, "getWrongReason","4");
		pageUtil.setColsJsfunc(2, "'#'.substring(11,19)");
		pageUtil.setColsTemplet(3, "#xmTpl");
		pageUtil.setColsTemplet(5, "#imgTpl");
		pageUtil.setColsHide(6, true);
		pageUtil.setColsHide(7, true);
	}
	
	@RequestMapping(value="/imagePage",method=RequestMethod.GET)
	public ModelAndView imagePage(@RequestParam(value="id", required=true, defaultValue="0") Integer id){
		ModelAndView mv = this.getModelAndView();
		return mv; 
	}
	
	@RequestMapping(value="/showImage",method=RequestMethod.GET)
	public void showImage(@RequestParam(value="id", required=true, defaultValue="0") Integer id) throws SQLException, IOException{
		if(id != 0){
			response.setContentType("image/png");
			W2Flowzp zpModel = (W2Flowzp)service.loadModel(W2Flowzp.class, id);
			if(zpModel != null && zpModel.getImg().length > 0 && zpModel.getImg() != null){
				OutputStream output = response.getOutputStream();  
				ByteArrayInputStream in = new ByteArrayInputStream(zpModel.getImg());//获取实体类对应Byte
				int len;  
				byte[] buf = new byte[1024];  
				while ((len = in.read(buf)) != -1) {  
					output.write(buf, 0, len);  
				}  
				output.flush(); 
				output.close();
			}
		}
	}
	
	/**
	 * 获取打印的图片数据
	 * 2019年6月20日
	 * @param id
	 * @param code
	 * @return
	 * @author:Lynn
	 */
	@RequestMapping(value="/getPrintImages",method=RequestMethod.POST)
	@ResponseBody
	public ResponseModel getPrintImages(@RequestParam(value="id",required=true,defaultValue="0") Integer id,@RequestParam(value="code",required=true,defaultValue="") String code){
		this.getBarCode(code);//生成条形码
		ResponseModel rModel = new ResponseModel();
		if(id != 0){
			Map<Integer, ArrayList<Integer>> imgIdsMap = service.getPrintImages(id);
			if(imgIdsMap == null){
				rModel.setCode("1");
			}else{
				rModel.setData(imgIdsMap);
			}
			
		}else{
			rModel.setCode("1");
		}
		return rModel; 
	}
	
	/**
	 * 生成条形码
	 * 2019年6月20日
	 * @param code
	 * @author:Lynn
	 */
	public void getBarCode(String code){
		try{
			//判断文件夹是否存在
			File pathFile = new File(Const.CODE_PATH);
			if(!pathFile.exists()) pathFile.mkdir();
			
			//判断文件是否存在
			String path = Const.CODE_PATH + code + ".png";
			File file = new File(path);
			if(file.exists()) return;
			
			//生成条形码
			OutputStream ous = new FileOutputStream(file);
			if(StringUtils.isEmpty(code) || ous == null) return;
			Code128Bean bean = new Code128Bean();
			final double moduleWidth = 0.50;
			final int resolution = 150;
			bean.setModuleWidth(moduleWidth);
			bean.doQuietZone(false);
			String format = "image/png";
			BitmapCanvasProvider canvas = new BitmapCanvasProvider(ous, format, resolution, BufferedImage.TYPE_BYTE_BINARY, false, 0);
			bean.generateBarcode(canvas, code);
			canvas.finish();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	
	
}
