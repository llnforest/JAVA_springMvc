package com.model.system;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Repository;

/**
 * SysConfig entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_config")
@Repository
public class SysConfig extends com.model.BaseModel implements
		java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String configId;
	private String configCode;
	private String configValue;
	private String configName;
	private String remark;
	private String units;
	private Integer configOrder;

	// Constructors

	/** default constructor */
	public SysConfig() {
	}

	/** full constructor */
	public SysConfig(String configCode, String configValue, String configName,
			String remark,Integer configOrder) {
		this.configCode = configCode;
		this.configValue = configValue;
		this.configName = configName;
		this.remark = remark;
		this.configOrder = configOrder;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "CONFIG_ID", unique = true, nullable = false, length = 32)
	public String getConfigId() {
		return this.configId;
	}

	public void setConfigId(String configId) {
		this.configId = configId;
	}

	@Column(name = "CONFIG_CODE", length = 40)
	public String getConfigCode() {
		return this.configCode;
	}

	public void setConfigCode(String configCode) {
		this.configCode = configCode;
	}

	@Column(name = "CONFIG_VALUE", length = 32)
	public String getConfigValue() {
		return this.configValue;
	}

	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}

	@Column(name = "CONFIG_NAME", length = 40)
	public String getConfigName() {
		return this.configName;
	}

	public void setConfigName(String configName) {
		this.configName = configName;
	}

	@Column(name = "REMARK", length = 1000)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Column(name = "CONFIG_ORDER", precision = 8, scale = 0)
	public Integer getConfigOrder() {
		return configOrder;
	}

	public void setConfigOrder(Integer configOrder) {
		this.configOrder = configOrder;
	}

	@Column(name = "UNITS", length = 40)
	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	
}