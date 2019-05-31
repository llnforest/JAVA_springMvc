package com.model.system;
// default package


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
 * SysBlank entity provides the base persistence definition of
 * the SysUserLoginBlank entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="sys_blank")
@Repository
public class SysBlank extends com.model.BaseModel implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	// Fields
	private String blankId;
	private String blankType;
	private String clientMac;
	private SysUser sysUser;

	// Constructors

	/** default constructor */
	public SysBlank() {
	}

	/** full constructor */
	public SysBlank(SysUser sysUser, String blankType, 
			String clientMac,String dataFlag) {
		this.sysUser = sysUser;
		this.blankType = blankType;
		this.clientMac = clientMac;
		super.dataFlag = dataFlag;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "BLANK_ID", unique = true, nullable = false, length = 32)
	public String getBlankId() {
		return this.blankId;
	}

	public void setBlankId(String blankId) {
		this.blankId = blankId;
	}

	@Column(name = "BLANK_TYPE", length = 5)
	public String getBlankType() {
		return this.blankType;
	}

	public void setBlankType(String blankType) {
		this.blankType = blankType;
	}

	@Column(name = "CLIENT_MAC", length = 40)
	public String getClientMac() {
		return this.clientMac;
	}

	public void setClientMac(String clientMac) {
		this.clientMac = clientMac;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	public SysUser getSysUser() {
		return this.sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

}