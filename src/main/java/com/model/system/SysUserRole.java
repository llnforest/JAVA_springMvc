package com.model.system;
// default package

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Repository;

/**
 * SysUserRole entity provides the base persistence definition of the
 * SysUserRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_user_role")
@Repository
public class SysUserRole extends com.model.BaseModel implements java.io.Serializable {

	// Fields

	private String userRoleId;
	private SysUser sysUser;
	private SysRole sysRole;

	// Constructors

	/** default constructor */
	public SysUserRole() {
	}

	/** full constructor */
	public SysUserRole(SysUser sysUser, SysRole sysRole) {
		this.sysUser = sysUser;
		this.sysRole = sysRole;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "USER_ROLE_ID", unique = true, nullable = false, length = 32)
	public String getUserRoleId() {
		return this.userRoleId;
	}

	public void setUserRoleId(String userRoleId) {
		this.userRoleId = userRoleId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", unique = true)
	public SysUser getSysUser() {
		return this.sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ROLE_ID", unique = true)
	public SysRole getSysRole() {
		return this.sysRole;
	}

	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
	}
}