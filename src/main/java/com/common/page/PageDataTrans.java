package com.common.page;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.common.delegate.EventHandler;
import com.common.log.SysLogger;
import com.common.tld.getDictTag;
import com.common.utils.DictUtil;
import com.common.utils.StringUtil;

/**  
 *  该类用于对page中数据进行格式转换
 *  最终生成前端UI需要的数据格式
 * @author:wangzhen
 * @version:V1.0
 * 2018年5月23日  
 */
public class PageDataTrans {

	/**
	 * 将数据库查询出来的表头cols数据转换成前端ui适配的json格式
	 * 2018年3月12日
	 * @param page
	 * @param pageUtil 页面工具类
	 * author:wangzhen
	 */
	public static Page TransPageCols(Page page,PageUtil pageUtil){
		//是否需要重置表头数据
		if(!pageUtil.getInsetColMap().isEmpty()){
			for (Map.Entry<Integer, Map<String, Object>> entry: pageUtil.getInsetColMap().entrySet()) {
				if((boolean)entry.getValue().get("isInsert")) page.insertHeader(entry.getKey(), String.valueOf(entry.getValue().get("colName")));
				else page.setHeader(entry.getKey(), String.valueOf(entry.getValue().get("colName")));
			}
		}
		//转换表头数据
		 String cols = "[[";
		if(!ArrayUtils.isEmpty(page.getHeader())){
			//设置排序号
			if(pageUtil.isShowNumbers()){
				cols +="{type:'numbers'";
				if(pageUtil.isFixedNumbers()){
					cols +=",fixed:'left'";
				}
				cols +="},";
			}
			//设置多选框
			if(pageUtil.isShowCheckbox()){
				cols +="{type:'checkbox'";
				if(pageUtil.isAllchecked()){
					cols +=",LAY_CHECKED:'true'";
				}
				if(pageUtil.isFixedCheckbox()){
					cols +=",fixed:'left'";
				}
				cols +="},";
			}
			//设置单选
			if(pageUtil.isShowRadio()){
				cols +="{type:'radio'";
				if(pageUtil.isFixedRadioBox()) cols +=",fixed:'left'";
				cols +="},";
			}
			for (int i =pageUtil.getBegin() ; i < page.getHeader().length; i++) {
				cols += "{field:'col"+i+"',title:'"+page.getHeader()[i]+"'";
				//设置单元格点击事件名
				if(StringUtils.isNotEmpty(pageUtil.getColsEvent(i))){
					cols +=",event:'"+pageUtil.getColsEvent(i)+"'";
				}
				//设置cols宽度
				if(StringUtils.isNotEmpty(pageUtil.getColsWidth(i))){
					cols +=",width:"+pageUtil.getColsWidth(i);
				}
				//设置cols最小宽度
				if(pageUtil.getColsMinWidth(i)!=null&&pageUtil.getColsMinWidth(i)!=0){
					cols +=",minWidth:"+pageUtil.getColsMinWidth(i);
				}
				//设置固定列
				if(StringUtils.isNotEmpty(pageUtil.getColsFixed(i))){
					cols +=",fixed:'"+pageUtil.getColsFixed(i)+"'";
				}
				//设置排序
				if(pageUtil.isColsSort()){
					cols +=",sort:true";
				}else{
					if(!ArrayUtils.isEmpty(pageUtil.getSortCols())&&ArrayUtils.contains(pageUtil.getSortCols(), i)){
						cols +=",sort:true";
					}
				}
				//设置是否隐藏
				if(pageUtil.getColsHide(i)){
					cols +=",hide:true";
				}
				//设置是否拖拽列宽，默认是允许的
				if(!ArrayUtils.isEmpty(pageUtil.getUnresize())&&ArrayUtils.contains(pageUtil.getUnresize(), i)){
					cols +=",unresize:true";
				}
				//设置单元格编辑类型
				if(StringUtils.isNotEmpty(pageUtil.getColsEdit(i))){
					cols +=",edit:'"+pageUtil.getColsEdit(i)+"'";
				}
				//设置单元格样式
				if(StringUtils.isNotEmpty(pageUtil.getColsStyle(i))){
					cols +=",style:'"+pageUtil.getColsStyle(i)+"'";
				}
				//设置单元格排列方式
				if(StringUtils.isNotEmpty(pageUtil.getAlign())){
					cols +=",align:'"+pageUtil.getAlign()+"'";
				}
				//设置单元格模板
				if(StringUtils.isNotEmpty(pageUtil.getColsTemplet(i))){
					cols +=",templet:'"+pageUtil.getColsTemplet(i)+"'";
				}
				cols += "}";
				if(i<page.getHeader().length-1){
					cols +=",";
				}
			}
			//设置工具条
			if(pageUtil.isToolbar()){
				cols +=",{fixed:'right',title:'操作', align:'"+pageUtil.getAlign()+"',toolbar:'#"+pageUtil.getToolbarId()+"'";
				if(StringUtils.isNotEmpty(pageUtil.getColsWidth(page.getHeader().length))){
					cols +=",width:"+pageUtil.getColsWidth(page.getHeader().length);
				}
				cols +="}";
			}
			
		}
		cols += "]]";
		
		//如果启用多级表头，则采用自定义的表头数据
		if(pageUtil.isMultistage()){
			cols = pageUtil.getMultistageHeader();
		}
		
		page.setHeaderJson(cols);
		
		
		//数据表格方法渲染其余参数
		String options = "";
		
		if(pageUtil.getWidth()>0){
			options += ",width:"+pageUtil.getWidth();
		}		
		if(pageUtil.getCellMinWidth()>0){
			options += ",cellMinWidth:"+pageUtil.getCellMinWidth();
		}
		if(StringUtils.isNotEmpty(pageUtil.getHeight())){
			options += ",height:"+pageUtil.getHeight();
		}
		if(StringUtils.isNotEmpty(pageUtil.getText())){
			options += ",text:"+pageUtil.getText();
		}
		if(StringUtils.isNotEmpty(pageUtil.getInitSortAsc())){
			options += ",initSort:"+pageUtil.getInitSortAsc();
		}
		if(StringUtils.isNotEmpty(pageUtil.getInitSortDesc())){
			options += ",initSort:"+pageUtil.getInitSortDesc();
		}
		if(StringUtils.isNotEmpty(pageUtil.getTableId())){
			options += ",id:'"+pageUtil.getTableId()+"'";
		}
		if(StringUtils.isNotEmpty(pageUtil.getSkin())){
			options += ",skin:'"+pageUtil.getSkin()+"'";
		}
		if(StringUtils.isNotEmpty(pageUtil.getSize())){
			options += ",size:'"+pageUtil.getSize()+"'";
		}
		if(pageUtil.isEven()){
			options += ",even:"+pageUtil.isEven()+"";
		}
		if(pageUtil.isShowPage()){
			options += ",page:"+pageUtil.isShowPage()+"";
		}

		page.setOptions(options);
		page.setShowQueryBut(pageUtil.isShowQueryBut());
		page.setShowExportBut(pageUtil.isShowExportBut());
		return page;
	}
	
	
	/**
	 * 将数据库查询出来的列表数据转换成前端ui适配的json格式
	 * 2018年3月12日
	 * @param page
	 * @param pageUtil 页面工具类
	 * @param dictMap 系统字典数据
	 * author:wangzhen
	 */
	public static JSONObject transData(Page page,PageUtil pageUtil){
		String dataJson = "{\"code\":0,\"msg\":\"\",\"count\":"+page.getRowSize()+",\"data\":[";
		List<?> data = page.getData();
		if(CollectionUtils.isNotEmpty(data)){
			for (int i = 0; i < data.size(); i++) {
				Object[] rowList = (Object[]) data.get(i);
				if(!ArrayUtils.isEmpty(rowList)){
					Map<Integer, Map<String, Object>> newColMap = pageUtil.getInsetColMap();
					dataJson +="{";
					int col = 0;
					int totalLength = rowList.length + newColMap.size();
					for (int k = 0; k < rowList.length; k++) {
						//判断是否有新插入的列
						if(!newColMap.isEmpty() && newColMap.containsKey(k)){
							//重置委托函数参数
							String indexString = String.valueOf(newColMap.get(k).get("indexName"));
							String[] IndexArr = indexString.split(",");
							Object[] args = new Object[IndexArr.length];
							for(int d=0;d<IndexArr.length;d++){
								args[d] = rowList[Integer.valueOf(IndexArr[d])];
							}
							//获取每一列数据
							String td = EventHandler.addEvent(newColMap.get(k).get("clazzName"), String.valueOf(newColMap.get(k).get("funcName")), args);
							dataJson = transJson(pageUtil, dataJson, td, col, totalLength);
							col ++;
							if(!(Boolean)newColMap.get(k).get("isInsert")) continue;
							
						}
						//获取每一列数据
						String td = StringUtil.convertNull(rowList[k]);
						dataJson = transJson(pageUtil, dataJson, td, col, totalLength);
						col ++;
						
					}
					dataJson +="}";
				}
				if(i<data.size()-1){
					dataJson +=",";
				}
			}
		}
		dataJson +="]}";
		JSONObject jsonObject = JSONObject.fromObject(dataJson);
		return jsonObject;
	}
	
	/**
	 * 拼接json数据
	 * 2019年1月23日
	 * @param pageUtil
	 * @param dataJson
	 * @param td
	 * @param col
	 * @param totalLength
	 * @return
	 * @author:Lynn
	 */
	private static String transJson(PageUtil pageUtil,String dataJson,String td,Integer col,Integer totalLength){
		//判断是否映射编码
		if(StringUtils.isNotEmpty(pageUtil.getDataDict(col))){
			//获取编码对应的数据
			td = DictUtil.getDictNameColor(pageUtil.getDataDict(col), td);
		}
		td = StringUtil.getJsEvalFunc(pageUtil.getJsFuncColMap(col), td);
		dataJson += "\"col"+col+"\""+":"+"\""+td+"\"";
		if(col < totalLength - 1){
			dataJson +=",";
		}
		return dataJson;
	}
	
	
}
