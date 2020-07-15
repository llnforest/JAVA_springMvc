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
 * JSP自定义标签（多选框标签）
 * 1.根据传入的主题显示不同的Checkbox框
 * @author:wangzhen
 * @version:V1.0
 * 2018年5月19日  
 */
public class CheckboxTag extends BodyTagSupport{
	
	private static final long serialVersionUID = 1L;
	
	private String name = "";//属性名称
	private String lable = "";//属性lable
	private String value = "";//当前的值
	private String checkValue = "on";//当前选中值
	private String skin = "";//皮肤
	private String disabled = "false";//皮肤
	private String title = "";//标题
	private String text = "";//开关内容
	
	
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
		String html = "";
		if(StringUtils.isNotEmpty(lable)){
			html += "<label class=\"layui-form-label\">"+lable+"</label>";
		}
		html += "<div class=\"layui-input-block\">";
		html += "<input type=\"checkbox\" name=\""+name+"\" ";
		if(StringUtils.isNotEmpty(skin)){
			html += "lay-skin=\""+skin+"\" ";
		}
		if(StringUtils.isNotEmpty(title)){
			html += "title=\""+title+"\" ";
		}
		if(StringUtils.isNotEmpty(text)){
			html += "lay-text=\""+text+"\" ";
		}
		if(StringUtils.isNotEmpty(checkValue)&&!checkValue.equals("on")){
			html += "value=\""+checkValue+"\" ";
		}
		if(disabled.equals("true")){
			html += " disabled ";
		}
		if(checkValue.equals(value)){
			html += " checked ";
		}
		html += ">";
		html += "</div>";
		return html;
	}
	
	
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getCheckValue() {
		return checkValue;
	}
	public void setCheckValue(String checkValue) {
		this.checkValue = checkValue;
	}
	public String getSkin() {
		return skin;
	}
	public void setSkin(String skin) {
		this.skin = skin;
	}
	public String getDisabled() {
		return disabled;
	}
	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
