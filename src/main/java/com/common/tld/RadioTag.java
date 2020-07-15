package com.common.tld;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.common.utils.DictUtil;
import com.model.system.SysDictValue;

/**  
 * JSP自定义标签（单选框标签）
 * 1.可以传入下拉对象json格式的数组
 * 2.可以传入sql查询出单选框数据
 * 3.可以传入字典数据生成单选框数据
 * @author:wangzhen
 * @version:V1.0
 * 2018年5月19日  
 */
public class RadioTag extends BodyTagSupport{

	private static final long serialVersionUID = 1L;
	
	private String name = "";//属性名称  也作为filter筛选器
	private String code = "";//radio字典编码
	private String lable = "";//属性lable
	private String value = "";//当前选中值
	private String inline = "block";//行内单选框(即样式不占满全行)
	
	
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
	 * 自定义单选框html
	 * 2018年5月18日
	 * @return
	 * author:wangzhen
	 */
	public String getHtmlTag(){
		List<SysDictValue> dictList = DictUtil.getDictList(code);
		String html = "";
		if(StringUtils.isNotEmpty(lable)){
			html += "<label class=\"layui-form-label\">"+lable+"</label>";
		}
		if(inline.equals("true")){
			inline = "inline";
		}
		html += "<div class=\"layui-input-"+inline+"\">";
		if(CollectionUtils.isNotEmpty(dictList)){
			for (SysDictValue sysDictValue : dictList) {
				String checked = sysDictValue.getValCode().equals(value)?" checked":"";
				html +="<input type=\"radio\" name=\""+name+"\" lay-filter=\""+name+"\" value=\""+sysDictValue.getValCode()+"\" title=\""+sysDictValue.getValName()+"\" "+checked+" >";
			}
		}
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
	public String getInline() {
		return inline;
	}
	public void setInline(String inline) {
		this.inline = inline;
	}
	

	
}
