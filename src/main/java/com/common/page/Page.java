package com.common.page;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;

import com.common.log.SysLogger;

/**  
 * 分页查询返回的实体对象
 * @author:wangzhen
 * @version:V1.0
 * 2017年6月21日  
 */
public class Page {
	
	protected SysLogger logger = new SysLogger(this);
	
	/**
	 * 当前页码，默认为1
	 */
	protected int page = 1;
	
	/**
	 * 每页显示数据条数，默认为10
	 */
	protected int limit = 10;
	
	/**
	 * 列表查询数据总行数
	 */
	protected long rowSize = 0;
	
	/**
	 * 列表查询数据总页数
	 */
	protected int allPage = 0;
	
	/**
	 * sql/hql查询返回的列表集合
	 */
	protected List<?> data = Collections.emptyList();
	
	/**
	 * sql/hql查询返回的表头数组
	 */
	protected String[] header ;
	
	/**
	 * 表头转成json格式字符串
	 */
	protected String headerJson ;
	
	/**
	 * 数据列表其它参数json格式字符串
	 */
	protected String options ;

	/**
	 * 是否显示查询重置按钮
	 */
	protected boolean showQueryBut = true;
	
	/**
	 * 是否显示导出按钮
	 */
	protected boolean showExportBut = true;
	
	public Page(){
		
	}
	
	public Page(int page){
		this.setPage(page);
	}
	
	public Page(int page,int limit){
		this.setPage(page);
		this.setLimit(limit);
	}
	
	public Page(ModelAndView mv){
		try {
			Object limit = mv.getModelMap().get("limit");
			Object page = mv.getModelMap().get("page");
			if(limit!=null&&StringUtils.isNotEmpty(limit.toString())&&Integer.parseInt(limit.toString())>0)
				this.setLimit(Integer.parseInt(limit.toString()));
			if(page!=null&&StringUtils.isNotEmpty(page.toString())&&Integer.parseInt(page.toString())>0)
				this.setPage(Integer.parseInt(page.toString()));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			logger.error("类型转换错误！", e);
		}
		
	}

	/**
	 * 根据page和limit计算当前页第一条记录在总结果集中的位置,序号从0开始
	 * hibernate的firstResult的序号从0开始
	 * 2017年6月21日
	 * @return
	 * author:wangzhen
	 */
	public int getFirstResult() {
		return ((page - 1) * limit);
	}

	/**
	 * 设置当前页码，小于1时自动调整为1，大于最大页码时自动调整为最大页
	 * 2017年6月21日
	 * @return
	 * author:wangzhen
	 */
	public int getPage() {
		if (this.page == 0)
		    this.page = 1;
		if (this.page > getAllPage())
		    this.page = getAllPage();
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public long getRowSize() {
		return rowSize;
	}

	public void setRowSize(long rowSize) {
		this.rowSize = rowSize;
	}

	/**
	 * 根据数据总行数和每页条数计算总页数
	 * 2017年6月21日
	 * @return
	 * author:wangzhen
	 */
	public int getAllPage() {
		if (getRowSize() % getLimit() == 0)
		    allPage = (int) (getRowSize() / this.getLimit());
		else
		    allPage = (int) (getRowSize() / this.getLimit() + 1);
		return allPage;
	}

	public void setAllPage(int allPage) {
		this.allPage = allPage;
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}
	
	
	public String[] getHeader() {
		return header;
	}

	public void setHeader(String[] header) {
		this.header = header;
	}
	
	public void setHeader(Integer index,String colName){
		this.header[index] = colName;
	}
	
	public void insertHeader(Integer index,String col){
		int j = 0;
		String[] newHeader = new String[header.length + 1];
		for(int i=0;i < header.length;i++){
			if(i == index){
				newHeader[j] = col;
				j++;
			}
			newHeader[j] = header[i];
			j ++;
		}
		this.header = newHeader;
	}

	public String getHeaderJson() {
		return headerJson;
	}

	public void setHeaderJson(String headerJson) {
		this.headerJson = headerJson;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
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

	/**
	 * 该方法用于将分页sql/hql转换为查询总行数的sql/hql语句
	 * 2017年6月22日
	 * @param sql
	 * @return
	 * author:wangzhen
	 */
	public static String getCountSql(String sql){
		//截取第一个from之后的语句
		sql = "from " + StringUtils.substringAfter(sql, "from");
		return "select count(*) as count " + sql;
	}
	

}
