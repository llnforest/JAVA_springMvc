package com.model.system;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Repository;

/**
 * SysRoleMenu entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_role_menu")
@Repository
public class SysRoleMenu extends com.model.BaseModel implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields
	private String roleMenuId;
	private SysRole sysRole;
	private SysMenu sysMenu;

	// Constructors

	/** default constructor */
	public SysRoleMenu() {
	}

	/** full constructor */
	public SysRoleMenu(SysRole sysRole, SysMenu sysMenu) {
		this.sysRole = sysRole;
		this.sysMenu = sysMenu;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ROLE_MENU_ID", nullable = false, length = 32)
	public String getRoleMenuId() {
		return this.roleMenuId;
	}

	public void setRoleMenuId(String roleMenuId) {
		this.roleMenuId = roleMenuId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ROLE_ID", unique = true)
	public SysRole getSysRole() {
		return this.sysRole;
	}

	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MENU_ID", unique = true)
	public SysMenu getSysMenu() {
		return this.sysMenu;
	}

	public void setSysMenu(SysMenu sysMenu) {
		this.sysMenu = sysMenu;
	}

}