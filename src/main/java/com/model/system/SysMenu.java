package com.model.system;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OrderBy;
import org.springframework.stereotype.Repository;

/**
 * SysMenu entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_menu")
@Repository
public class SysMenu extends com.model.BaseModel implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private String menuId;
	private String parentId;
	private SysMenu parent;
	private String menuCode;
	private String menuTitle;
	private String menuIcon;
	private Integer menuOrder;
	private String menuUrl;
	private String menuType;
	private String buttonType;
	private String buttonFunction;
	private String buttonCss;
	private String logLevel;
	private String remark;
	private String businessType;

	private Set<SysMenu> children = new HashSet<SysMenu>();

	// Constructors
	/** default constructor */
	public SysMenu() {
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "MENU_ID", nullable = false, length = 32)
	public String getMenuId() {
		return this.menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	
	@Column(name = "PARENT_ID", length = 32)
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@ManyToOne
	@JoinColumn(name = "PARENT_ID", insertable = false, updatable = false)
	public SysMenu getParent() {
		return this.parent;
	}

	public void setParent(SysMenu parent) {
		this.parent = parent;
	}

	@Column(name = "MENU_CODE", length = 40)
	public String getMenuCode() {
		return this.menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	@Column(name = "MENU_TITLE", length = 60)
	public String getMenuTitle() {
		return this.menuTitle;
	}

	public void setMenuTitle(String menuTitle) {
		this.menuTitle = menuTitle;
	}

	@Column(name = "MENU_ICON", length = 200)
	public String getMenuIcon() {
		return this.menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	@Column(name = "MENU_ORDER", precision = 8, scale = 0)
	public Integer getMenuOrder() {
		return this.menuOrder;
	}

	public void setMenuOrder(Integer menuOrder) {
		this.menuOrder = menuOrder;
	}

	@Column(name = "MENU_URL", length = 200)
	public String getMenuUrl() {
		return this.menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	@Column(name = "MENU_TYPE", length = 40)
	public String getMenuType() {
		return this.menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	@Column(name = "BUTTON_TYPE", length = 40)
	public String getButtonType() {
		return this.buttonType;
	}

	public void setButtonType(String buttonType) {
		this.buttonType = buttonType;
	}

	@Column(name = "BUTTON_FUNCTION", length = 100)
	public String getButtonFunction() {
		return this.buttonFunction;
	}

	public void setButtonFunction(String buttonFunction) {
		this.buttonFunction = buttonFunction;
	}
	
	@Column(name = "BUTTON_CSS", length = 200)
	public String getButtonCss() {
		return buttonCss;
	}
	
	public void setButtonCss(String buttonCss) {
		this.buttonCss = buttonCss;
	}

	@Column(name = "LOG_LEVEL", length = 40)
	public String getLogLevel() {
		return this.logLevel;
	}

	public void setLogLevel(String logLevel) {
		this.logLevel = logLevel;
	}

	@Column(name = "REMARK", length = 1000)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Column(name = "BUSINESS_TYPE", length = 10)
	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	@OneToMany(mappedBy="parent",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@OrderBy(clause="menuOrder asc")
	public Set<SysMenu> getChildren() {
		return children;
	}

	public void setChildren(Set<SysMenu> children) {
		this.children = children;
	}
	


}