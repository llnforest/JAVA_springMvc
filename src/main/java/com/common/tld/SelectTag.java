package com.common.tld;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.common.utils.DictUtil;
import com.model.system.SysDictValue;

/**  
 * JSP自定义标签（下拉框标签）
 * 1.可以传入下拉对象json格式的数组
 * 2.可以传入sql查询出下拉框数据
 * 3.可以传入字典数据生成下拉框数据
 * @author:wangzhen
 * @version:V1.0
 * 2018年5月18日  
 */
public class SelectTag extends BodyTagSupport {
	private static final long serialVersionUID = 1L;

	private String name = "";//属性名称
	private String code = "";//下拉框字典编码
	private String lable = "";//下拉框lable
	private String value = "";//当前选中值
	private String isDefault = "true";//默认选择项
	private String defaultText = "请选择";//默认选择项
	private String inline = "block";//行内单选框(即样式不占满全行)
	private String style = "";//其它内联样式
	private String sql = "";//sql语句查询出下拉字典
	private String laySearch = "false";
	
//	private String multiple = "false";//开启多选/搜索模式/样式 TODO 
	
	/* 标签初始方法 */
	@Override
    public int doStartTag() throws JspException{
        return super.doStartTag();
    }
	
	@Override
	public int doEndTag() throws JspException {
		try {
			this.pageContext.getOut().println(getHtmlTag());
//			this.pageContext.getOut().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.doEndTag();
	}
	
	/**
	 * 自定义下拉框html
	 * 2018年5月18日
	 * @return
	 * author:wangzhen
	 */
	public String getHtmlTag(){
		List<SysDictValue> dictList = null;
		//sql语句生成下拉数据
		if(!StringUtils.isEmpty(sql)){
			dictList = DictUtil.getDictListByHql(sql);
		}else{
			dictList = DictUtil.getDictList(code);
		}
		String html = "";
		if(StringUtils.isNotEmpty(lable)){
			html += "<label class=\"layui-form-label\">"+lable+"</label>";
		}
		if(inline.equals("true")){
			inline = "inline";
		}
		String styleCss = "";
		if(StringUtils.isNotEmpty(style)){
			styleCss = "style=\""+style+"\"";
		}
		String search = "";
		if(laySearch.equals("true")){
			search = "lay-search";
		}
		html += "<div class=\"layui-input-"+inline+"\" "+styleCss+">";
		html += "<select name=\""+name+"\" lay-filter=\""+name+"\" "+search+">";
		if(isDefault.equals("true")){
			html +="<option value=\"\">"+defaultText+"</option>";	
		}
		if(CollectionUtils.isNotEmpty(dictList)){
			for (SysDictValue sysDictValue : dictList) {
				String selected = sysDictValue.getValCode().equals(value)?" selected":"";
				html +="<option value=\""+sysDictValue.getValCode()+"\" "+selected+">"+sysDictValue.getValName()+"</option>";
			}
		}
		html += "</select>";
		html += "</div>";
		return html;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getLable() {
		return lable;
	}
	public void setLable(String lable) {
		this.lable = lable;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
	public String getDefaultText() {
		return defaultText;
	}
	public void setDefaultText(String defaultText) {
		this.defaultText = defaultText;
	}
	public String getInline() {
		return inline;
	}
	public void setInline(String inline) {
		this.inline = inline;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	
	public String getLaySearch() {
		return laySearch;
	}

	public void setLaySearch(String laySearch) {
		this.laySearch = laySearch;
	}


}
