package com.common.page;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.NumberUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


/**  
 * 分页列表个性化设置辅助工具类
 * @author:wangzhen
 * @version:V1.0
 * 2018年1月13日  
 */
@Scope("prototype")
@Component
public class PageUtil {
	
	public PageUtil() {
	}

	
	/**
	 * 设定列宽（默认自动分配）。支持填写：数字、百分比。请结合实际情况，对不同列做不同设定
	 * 如：200、30%
	 */
	Map<Integer, String> colsWidthMap = new HashMap<Integer, String>();
	/**
	 * 局部定义当前常规单元格的最小宽度（默认：60），一般用于列宽自动分配的情况。其优先级高于基础参数中的 cellMinWidth
	 */
	Map<Integer, Integer> colsMinWidthMap = new HashMap<Integer, Integer>();
	/**
	 * 固定列。可选值有：left（固定在左）、right（固定在右）。一旦设定，对应的列将会被固定在左或右，不随滚动条而滚动。 
	 * 如果是固定在左，该列必须放在表头最前面；如果是固定在右，该列必须放在表头最后面。
	 */
	Map<Integer, String> colsFixedMap = new HashMap<Integer, String>();
	/**
	 * 控制列显示隐藏，默认为显示
	 */
	Map<Integer, Boolean> colsHideMap = new HashMap<Integer, Boolean>();
	/**
	 * 设置列表是否显示序号
	 */
	protected boolean showNumbers = true;
	/**
	 * 设置是否将序号列设置为固定列
	 */
	protected boolean fixedNumbers = false;
	/**
	 * 设置列表是否显示多选框
	 */
	protected boolean showCheckbox = false;
	/**
	 * 设置列表是否显示单选
	 */
	protected boolean showRadio = false;
	/**
	 * 设置列表是否显示查询、重置按钮
	 */
	protected boolean showQueryBut = true;
	/**
	 * 设置列表是否显示查询、重置按钮
	 */
	protected boolean showExportBut = true;
	/**
	 * 设置是否将将多选框列设置为固定列
	 */
	protected boolean fixedCheckbox = false;
	/**
	 * 设置多选框初始状态是否全选，必须复选框列开启后才有效
	 */
	protected boolean allchecked = false;
	/**
	 * 设置单选框固定列
	 */
	protected boolean fixedRadioBox = false;
	/**
	 * 设置是否允许排序,字典序排列算法
	 * 1.设置为true时即默认时全开
	 * 2.设置成false时为全关
	 * 3.在false状态下也可以指定个别列开启
	 */
	protected boolean colsSort = true;
	
	

	/**
	 * 当关闭colsSort排序参数时，可以指定个别列进行排序
	 */
	protected int[] sortCols;
	/**
	 * 数据列表默认是运行拖拽列宽的，该参数用于指定不能拖拽列宽的列
	 */
	protected int[] unresize;
	
	/**
	 * 单元格编辑类型（默认不开启）目前只支持：text（输入框）
	 */
	Map<Integer, String> colsEditMap = new HashMap<Integer, String>();
	
	/**
	 * 自定义单元格点击事件名
	 */
	Map<Integer, String> colsEventMap = new HashMap<Integer, String>();
	/**
	 * 自定义单元格样式。即传入 CSS 样式
	 */
	Map<Integer, String> colsStyleMap = new HashMap<Integer, String>();
	/**
	 * 单元格排列方式。可选值有：left（默认）、center（居中）、right（居右）
	 */
	protected String align = "left";
	
	/**
	 * 启动多级复杂表头，当启用多级复杂表头时，需要自己主动传入表头内容
	 */
	protected boolean isMultistage = false;
	/**
	 * 多级表头内容，当启用多级复杂表头时，使用改字符串中数据作为列表表头
	 */
	protected String multistageHeader;
	
	/**
	 * 自定义列模板，模板遵循 laytpl语法
	 * templet 提供了三种使用方式
	 * 1.绑定模版选择器
	 * 2.函数转义
	 * 3.直接赋值模版字符
	 */
	Map<Integer, String> colsTempletMap = new HashMap<Integer, String>();
	
	/**
	 * 设定容器宽度。table容器的默认宽度是跟随它的父元素铺满，你也可以设定一个固定值，当容器中的内容超出了该宽度时，会自动出现横向滚动条。
	 */
	protected int width;
	
	/**
	 * 设定容器高度
	 * 1.默认情况。高度随数据列表而适应，表格容器不会出现纵向滚动条
	 * 2.设定一个数字，用于定义容器高度，当容器中的内容超出了该高度时，会自动出现纵向滚动条 如：height: 315
	 * 3.高度将始终铺满，无论浏览器尺寸如何。这是一个特定的语法格式，其中 full 是固定的，
	 * 而 差值 则是一个数值，这需要你来预估，比如：表格容器距离浏览器顶部和底部的距离“和”  如：height: 'full-20'
	 */
	protected String height;
	
	/**
	 * 全局定义所有常规单元格的最小宽度（默认：60），一般用于列宽自动分配的情况。
	 * 其优先级低于表头参数中的 minWidth
	 */
	protected int cellMinWidth;
	
	/**
	 * 自定义文本，如空数据时的异常提示等
	 */
	protected String text;
	
	/**
	 * 设置列表初始排序列-倒序排列
	 */
	protected String initSortDesc;
	
	/**
	 * 设置列表初始序列-正序配列
	 */
	protected String initSortAsc;

	/**
	 * 表格id
	 */
	protected String tableId = "listTable";
	
	
	/**
	 * 用于设定表格风格，若使用默认风格不设置该属性即可
	 * line （行边框风格） row （列边框风格） nob （无边框风格） 
	 */
	protected String skin;
	
	/**
	 * 用于设定表格尺寸，若使用默认尺寸不设置该属性即可
	 * sm （小尺寸） lg （大尺寸） 
	 */
	protected String size;
	
	/**
	 * 隔行换色，若不开启隔行背景，不设置该参数即可
	 */
	protected boolean even = false;

	/**
	 * 设置是否显示分页
	 */
	protected boolean showPage = true;
	
	/**
	 * 是否启用工具调列，工具条列默认的id为listBar
	 */
	protected boolean toolbar = true;
	/**
	 * toolbar默认id
	 */
	protected String toolbarId = "listBar";
	/**
	 * 设定list数据列表从第几列显示数据
	 * 从0开始计数，通常情况下第0位是主键id
	 * 所以该参数默认从第1位开始技术
	 */
	protected int begin = 1;
	
	/**
	 * 对应列使用js方法操作
	 */
	Map<Integer, String> jsFuncColMap = new HashMap<Integer, String>(); 
	
	/**
	 * 插入新的列内容
	 * 例如：{3:{colName:colName,clazzName:class,funcName:method,indexName:index,isInsert:true},4:{}}
	 * 3代表插入的列，class代表类 ，method代表调用方法
	 */
	Map<Integer, Map<String, Object>> insetColMap = new HashMap<Integer, Map<String,Object>>();
	
	/**
	 * 修改列内容
	 * 例如：{3:{colName:colName,clazzName:class,funcName:method,indexName:index},4:{}}
	 * 3代表插入的列，class代表类 ，method代表调用方法
	 */
	Map<Integer, Map<String, Object>> editColMap = new HashMap<Integer, Map<String,Object>>();
	
	/**
	 * 设定数据列表字典映射，如第3列映射为性别（1：男；0：女）
	 * 则系统会将第三列数值中1转换为‘男’；0转换成‘女’
	 */
	Map<Integer, String> dataDictMap = new HashMap<Integer, String>();
	
	/**
	 * 获取列是否隐藏
	 * 2019年2月28日
	 * @return
	 * @author:Lynn
	 */
	public Map<Integer, Boolean> getColsHideMap() {
		return colsHideMap;
	}

	/**
	 * 设置列是否隐藏
	 * 2019年2月28日
	 * @return
	 * @author:Lynn
	 */
	public void setColsHideMap(Map<Integer, Boolean> colsHideMap) {
		this.colsHideMap = colsHideMap;
	}
	
	/**
	 * 获取列是否隐藏
	 * 2019年2月28日
	 * @return
	 * @author:Lynn
	 */
	public Boolean getColsHide(Integer index) {
		if(colsHideMap.containsKey(index)){
			return colsHideMap.get(index);
		}else{
			return false;
		}
	}

	/**
	 * 设置列是否隐藏
	 * 2019年2月28日
	 * @return
	 * @author:Lynn
	 */
	public void setColsHide(Integer index,Boolean hide) {
		colsHideMap.put(index, hide);
	}

	/**
	 * 设置表头cols宽度
	 * 2018年3月13日
	 * @param index 顺序
	 * @param width 宽度值
	 * author:wangzhen
	 */
	public void setColsWidth(Integer index, String width) {
		colsWidthMap.put(index, width);
	}
	
	/**
	 * 设置表头cols宽度
	 * 2018年3月13日
	 * @param widths 宽度值数组
	 * author:wangzhen
	 */
	public void setColsWidth(String[] widths) {
		if(!ArrayUtils.isEmpty(widths)){
			for (int i = 0; i < widths.length; i++) {
				setColsWidth(i,widths[i]);
			}
		}
	}
	
	/**
	 * 获取给定列设置的宽度值
	 * 2018年3月13日
	 * @param index
	 * @return
	 * author:wangzhen
	 */
	public String getColsWidth(Integer index) {
		if(StringUtils.isNotEmpty(colsWidthMap.get(index))){
			if(colsWidthMap.get(index).contains("%")){
				return "'"+colsWidthMap.get(index)+"'";
			}else{
				return colsWidthMap.get(index);
			}
		}else{
			return "";
		}
	}
	
	/**
	 * 设置表头cols最小宽度
	 * 2018年3月13日
	 * @param index 顺序
	 * @param width 宽度值  当值为0时不生效
	 * author:wangzhen
	 */
	public void setColsMinWidth(Integer index, Integer minWidth) {
		colsMinWidthMap.put(index, minWidth);
	}
	
	/**
	 * 设置表头cols最小宽度
	 * 2018年3月13日
	 * @param widths 最小宽度值数组 数组值为0时不生效
	 * author:wangzhen
	 */
	public void setColsMinWidth(Integer[] minWidths) {
		if(!ArrayUtils.isEmpty(minWidths)){
			for (int i = 0; i < minWidths.length; i++) {
				setColsMinWidth(i,minWidths[i]);
			}
		}
	}
	
	/**
	 * 获取给定列设置的最小宽度值
	 * 2018年3月13日
	 * @param index
	 * @return
	 * author:wangzhen
	 */
	public Integer getColsMinWidth(Integer index) {
		if(colsMinWidthMap.get(index)!=null&&colsMinWidthMap.get(index)!=0){
			return colsMinWidthMap.get(index);
		}else{
			return null;
		}
	}
	
	/**
	 * 设置表头cols是否为固定列
	 * 2018年3月13日
	 * @param index 顺序
	 * @param fixed （left、right）
	 * author:wangzhen
	 */
	public void setColsFixed(Integer index, String fixed) {
		colsFixedMap.put(index, fixed);
	}
	
	/**
	 * 获取给定列设置的固定列参数值
	 * 2018年3月13日
	 * @param index
	 * @return
	 * author:wangzhen
	 */
	public String getColsFixed(Integer index) {
		if(StringUtils.isNotEmpty(colsFixedMap.get(index))){
			return colsWidthMap.get(index);
		}else{
			return "";
		}
	}
	
	/**
	 * 设置单元格编辑类型
	 * 2018年3月13日
	 * @param index 顺序
	 * @param edit 编辑类型
	 * author:wangzhen
	 */
	public void setColsEdit(Integer index, String edit) {
		colsEditMap.put(index, edit);
	}
	
	/**
	 * 设置单元格编辑类型
	 * 2018年3月13日
	 * @param edits 编辑类型 目前支持text
	 * author:wangzhen
	 */
	public void setColsEdit(String[] edits) {
		if(!ArrayUtils.isEmpty(edits)){
			for (int i = 0; i < edits.length; i++) {
				setColsEdit(i,edits[i]);
			}
		}
	}
	
	/**
	 * 获取单元格编辑类型
	 * 2018年3月13日
	 * @param index
	 * @return
	 * author:wangzhen
	 */
	public String getColsEdit(int index) {
		if(StringUtils.isNotEmpty(colsEditMap.get(index))){
			return colsEditMap.get(index);
		}else{
			return "";
		}
	}
	
	
	/**
	 * 设置单元格单元格点击事件名
	 * 2018年3月13日
	 * @param index 顺序
	 * @param event 点击事件名，任意字符
	 * author:wangzhen
	 */
	public void setColsEvent(Integer index, String event) {
		colsEventMap.put(index, event);
	}
	
	/**
	 * 设置单元格点击事件名
	 * 2018年3月13日
	 * @param edits 点击事件名
	 * author:wangzhen
	 */
	public void setColsEvent(String[] events) {
		if(!ArrayUtils.isEmpty(events)){
			for (int i = 0; i < events.length; i++) {
				setColsEvent(i,events[i]);
			}
		}
	}
	
	/**
	 * 获取单元格点击事件名
	 * 2018年3月13日
	 * @param index
	 * @return
	 * author:wangzhen
	 */
	public String getColsEvent(int index) {
		if(StringUtils.isNotEmpty(colsEventMap.get(index))){
			return colsEventMap.get(index);
		}else{
			return "";
		}
	}
	
	/**
	 * 设置每一列样式
	 * 2018年3月13日
	 * @param index 顺序
	 * @param style 列样式  如background-color: #5FB878; color: #fff;
	 * author:wangzhen
	 */
	public void setColsStyle(Integer index, String style) {
		colsStyleMap.put(index, style);
	}
	
	/**
	 * 设置列表列样式
	 * 2018年3月13日
	 * @param style 样式数组
	 * author:wangzhen
	 */
	public void setColsStyle(String[] styles) {
		if(!ArrayUtils.isEmpty(styles)){
			for (int i = 0; i < styles.length; i++) {
				setColsStyle(i,styles[i]);
			}
		}
	}
	
	/**
	 * 获取给定列设置的样式
	 * 2018年3月13日
	 * @param index
	 * @return
	 * author:wangzhen
	 */
	public String getColsStyle(Integer index) {
		if(StringUtils.isNotEmpty(colsStyleMap.get(index))){
			return colsStyleMap.get(index);
		}else{
			return "";
		}
	}
	
	
	/**
	 * 设置每一列模板
	 * 2018年3月13日
	 * @param index 顺序
	 * @param templet 三种方式
	 * author:wangzhen
	 */
	public void setColsTemplet(Integer index, String templet) {
		colsTempletMap.put(index, templet);
	}
	
	
	/**
	 * 获取给定列设置的样式
	 * 2018年3月13日
	 * @param index
	 * @return
	 * author:wangzhen
	 */
	public String getColsTemplet(Integer index) {
		if(StringUtils.isNotEmpty(colsTempletMap.get(index))){
			return colsTempletMap.get(index);
		}else{
			return "";
		}
	}
	

	/**
	 * 设置列数据字典映射
	 * 2018年3月13日
	 * @param index 顺序
	 * @param dict 字典编码值
	 * author:wangzhen
	 */
	public void setDataDict(Integer index, String dict) {
		dataDictMap.put(index, dict);
	}
	
	/**
	 * 设置列数据字典映射
	 * 2018年3月13日
	 * @param widths 字典编码数组
	 * author:wangzhen
	 */
	public void setDataDict(String[] dicts) {
		if(!ArrayUtils.isEmpty(dicts)){
			for (int i = 0; i < dicts.length; i++) {
				setDataDict(i,dicts[i]);
			}
		}
	}
	
	/**
	 * 获取给定列设置的数据字典编码
	 * 2018年3月13日
	 * @param index
	 * @return
	 * author:wangzhen
	 */
	public String getDataDict(Integer index) {
		if(StringUtils.isNotEmpty(dataDictMap.get(index))){
			return dataDictMap.get(index);
		}else{
			return "";
		}
	}
	
	/**
	 * 设置列的js方法映射
	 * 2019年1月18日
	 * @param funcs js方法名，用#代替需替换的值 ex:'#'.substring(0,10);
	 * @author:Lynn
	 */
	public void setJsFuncColMap(String[] funcs) {
		if(!ArrayUtils.isEmpty(funcs)){
			for (int i = 0; i < funcs.length; i++) {
				setJsFuncColMap(i,funcs[i]);
			}
		}
	}
	
	/**
	 * 获取列的js方法映射
	 * 2019年1月18日
	 * @param index int
	 * @author:Lynn
	 */
	public String getJsFuncColMap(Integer index) {
		if(StringUtils.isNotEmpty(jsFuncColMap.get(index))){
			return jsFuncColMap.get(index);
		}else{
			return "";
		}
	}

	
	/**
	 * 设置列的js方法映射
	 * 2019年1月18日
	 * @param index int
	 * @param funcs js方法名，用#代替需替换的值 ex:'#'.substring(0,10);
	 * @author:Lynn
	 */
	public void setJsFuncColMap(Integer index,String func) {
		this.jsFuncColMap.put(index, func);
	}

	
	/**
	 * 
	 * 2019年1月23日
	 * @return
	 * @author:Lynn
	 */
	public Map<Integer, Map<String, Object>> getInsetColMap() {
		return insetColMap;
	}
	
	/**
	 * 设置新插入的列map
	 * 2019年1月23日
	 * @param index 插入位置
	 * @param colName  插入列名称
	 * @param clazz  对象
	 * @param funcName  调用方法名
	 * @param indexName  参数，正常返回数据的位置用,号分割作为参数
	 * @author:Lynn
	 */
	public void setInsetColMap(Integer index,String colName,Object clazz,String funcName,String indexName) {
		Map<String,Object> colMap = new HashMap<String, Object>();
		colMap.put("colName", colName);
		colMap.put("clazzName", clazz);
		colMap.put("funcName", funcName);
		colMap.put("indexName", indexName);
		colMap.put("isInsert", true);
		insetColMap.put(index, colMap);
	}
	
	/**
	 * 设置新修改的列map
	 * 2019年1月23日
	 * @param index 插入位置
	 * @param colName  插入列名称
	 * @param clazz  对象
	 * @param funcName  调用方法名
	 * @param indexName  参数，正常返回数据的位置用,号分割作为参数
	 * @param isInsert  参数，boolean:修改 新增
	 * @author:Lynn
	 */
	public void setInsetColMap(Integer index,String colName,Object clazz,String funcName,String indexName,Boolean isInsert) {
		Map<String,Object> colMap = new HashMap<String, Object>();
		colMap.put("colName", colName);
		colMap.put("clazzName", clazz);
		colMap.put("funcName", funcName);
		colMap.put("indexName", indexName);
		colMap.put("isInsert", isInsert);
		insetColMap.put(index, colMap);
	}

	
	/****************set/get begin**********************/

	public Map<Integer, String> getColsWidthMap() {
		return colsWidthMap;
	}

	public void setColsWidthMap(Map<Integer, String> colsWidthMap) {
		this.colsWidthMap = colsWidthMap;
	}

	public Map<Integer, Integer> getColsMinWidthMap() {
		return colsMinWidthMap;
	}
	
	public Map<Integer, String> getColsFixedMap() {
		return colsFixedMap;
	}

	public void setColsFixedMap(Map<Integer, String> colsFixedMap) {
		this.colsFixedMap = colsFixedMap;
	}

	public void setColsMinWidthMap(Map<Integer, Integer> colsMinWidthMap) {
		this.colsMinWidthMap = colsMinWidthMap;
	}

	public boolean isShowNumbers() {
		return showNumbers;
	}

	public void setShowNumbers(boolean showNumbers) {
		this.showNumbers = showNumbers;
	}

	public boolean isShowCheckbox() {
		return showCheckbox;
	}

	public void setShowCheckbox(boolean showCheckbox) {
		this.showCheckbox = showCheckbox;
	}

	public boolean isShowRadio() {
		return showRadio;
	}

	public void setShowRadio(boolean showRadio) {
		this.showRadio = showRadio;
	}

	public boolean isFixedRadioBox() {
		return fixedRadioBox;
	}

	public void setFixedRadioBox(boolean fixedRadioBox) {
		this.fixedRadioBox = fixedRadioBox;
	}

	public boolean isShowQueryBut() {
		return showQueryBut;
	}

	public void setShowQueryBut(boolean showQueryBut) {
		this.showQueryBut = showQueryBut;
	}

	public boolean isShowExportBut() {
		return showExportBut;
	}

	public void setShowExportBut(boolean showExportBut) {
		this.showExportBut = showExportBut;
	}

	public boolean isAllchecked() {
		return allchecked;
	}

	public void setAllchecked(boolean allchecked) {
		this.allchecked = allchecked;
	}

	public boolean isFixedNumbers() {
		return fixedNumbers;
	}

	public void setFixedNumbers(boolean fixedNumbers) {
		this.fixedNumbers = fixedNumbers;
	}

	public boolean isFixedCheckbox() {
		return fixedCheckbox;
	}

	public void setFixedCheckbox(boolean fixedCheckbox) {
		this.fixedCheckbox = fixedCheckbox;
	}

	public boolean isColsSort() {
		return colsSort;
	}

	public void setColsSort(boolean colsSort) {
		this.colsSort = colsSort;
	}

	public int[] getSortCols() {
		return sortCols;
	}

	public void setSortCols(int[] sortCols) {
		this.sortCols = sortCols;
	}

	public int[] getUnresize() {
		return unresize;
	}

	public void setUnresize(int[] unresize) {
		this.unresize = unresize;
	}

	public Map<Integer, String> getColsEditMap() {
		return colsEditMap;
	}

	public void setColsEditMap(Map<Integer, String> colsEditMap) {
		this.colsEditMap = colsEditMap;
	}
	public Map<Integer, String> getColsEventMap() {
		return colsEventMap;
	}

	public void setColsEventMap(Map<Integer, String> colsEventMap) {
		this.colsEventMap = colsEventMap;
	}
	public Map<Integer, String> getColsStyleMap() {
		return colsStyleMap;
	}

	public void setColsStyleMap(Map<Integer, String> colsStyleMap) {
		this.colsStyleMap = colsStyleMap;
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public boolean isMultistage() {
		return isMultistage;
	}

	public void setMultistage(boolean isMultistage) {
		this.isMultistage = isMultistage;
	}

	public String getMultistageHeader() {
		return multistageHeader;
	}

	public void setMultistageHeader(String multistageHeader) {
		this.multistageHeader = multistageHeader;
	}

	public Map<Integer, String> getColsTempletMap() {
		return colsTempletMap;
	}

	public void setColsTempletMap(Map<Integer, String> colsTempletMap) {
		this.colsTempletMap = colsTempletMap;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public String getHeight() {
		if(StringUtils.isNotEmpty(height)&&!StringUtils.isNumeric(height)){
			return "'"+height+"'";
		}
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public int getCellMinWidth() {
		return cellMinWidth;
	}

	public void setCellMinWidth(int cellMinWidth) {
		this.cellMinWidth = cellMinWidth;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getInitSortDesc() {
		if(NumberUtils.isNumber(initSortDesc)){
			return "{field:'col"+initSortDesc+"',type:'desc'}";
		}
		return null;
	}

	public void setInitSortDesc(String initSortDesc) {
		this.initSortDesc = initSortDesc;
	}

	public String getInitSortAsc() {
		if(NumberUtils.isNumber(initSortAsc)){
			return "{field:'col"+initSortAsc+"',type:'asc'}";
		}
		return null;
		
	}

	public void setInitSortAsc(String initSortAsc) {
		this.initSortAsc = initSortAsc;
	}

	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	public String getSkin() {
		return skin;
	}

	public void setSkin(String skin) {
		this.skin = skin;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public boolean isEven() {
		return even;
	}

	public void setEven(boolean even) {
		this.even = even;
	}

	public boolean isShowPage() {
		return showPage;
	}

	public void setShowPage(boolean showPage) {
		this.showPage = showPage;
	}

	public boolean isToolbar() {
		return toolbar;
	}

	public void setToolbar(boolean toolbar) {
		this.toolbar = toolbar;
	}

	public String getToolbarId() {
		return toolbarId;
	}

	public void setToolbarId(String toolbarId) {
		this.toolbarId = toolbarId;
	}

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public Map<Integer, String> getDataDictMap() {
		return dataDictMap;
	}

	public void setDataDictMap(Map<Integer, String> dataDictMap) {
		this.dataDictMap = dataDictMap;
	}
	


	
	/****************set/get end**********************/	
	
	
	
	

	
	
	
	
	
}
