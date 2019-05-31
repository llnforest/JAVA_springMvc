package com.model.system;

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
 * SysUserConfig entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_user_config")
@Repository
public class SysUserConfig extends com.model.BaseModel implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	// Fields
	private String configId;
	private SysUser sysUser;
	private String configType;
	private String configValue;

	// Constructors

	/** default constructor */
	public SysUserConfig() {
	}

	
	
	
	public SysUserConfig(SysUser sysUser, String configType, String configValue) {
		super();
		this.sysUser = sysUser;
		this.configType = configType;
		this.configValue = configValue;
	}




	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "CONFIG_ID", nullable = false, length = 32)
	public String getConfigId() {
		return this.configId;
	}

	public void setConfigId(String configId) {
		this.configId = configId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", unique = true)
	public SysUser getSysUser() {
		return this.sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	@Column(name = "CONFIG_TYPE", length = 40)
	public String getConfigType() {
		return this.configType;
	}

	public void setConfigType(String configType) {
		this.configType = configType;
	}

	@Column(name = "CONFIG_VALUE", length = 32)
	public String getConfigValue() {
		return this.configValue;
	}

	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}
}