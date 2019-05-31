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
 * SysDictValue entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_dict_value")
@Repository
public class SysDictValue extends com.model.BaseModel implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private String valId;
	private SysDict sysDict;
	private String valName;
	private String valCode;
	private Integer valOrder;
	private String remark;
	private String valColor;

	// Constructors

	/** default constructor */
	public SysDictValue() {
	}


	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "VAL_ID", nullable = false, length = 32)
	public String getValId() {
		return this.valId;
	}

	public void setValId(String valId) {
		this.valId = valId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DICT_ID", unique = true)
	public SysDict getSysDict() {
		return this.sysDict;
	}

	public void setSysDict(SysDict sysDict) {
		this.sysDict = sysDict;
	}

	@Column(name = "VAL_NAME", length = 60)
	public String getValName() {
		return this.valName;
	}

	public void setValName(String valName) {
		this.valName = valName;
	}

	@Column(name = "VAL_CODE", length = 40)
	public String getValCode() {
		return this.valCode;
	}

	public void setValCode(String valCode) {
		this.valCode = valCode;
	}

	@Column(name = "VAL_ORDER", precision = 8, scale = 0)
	public Integer getValOrder() {
		return this.valOrder;
	}

	public void setValOrder(Integer valOrder) {
		this.valOrder = valOrder;
	}

	@Column(name = "REMARK", length = 1000)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "VAL_COLOR", length = 1000)
	public String getValColor() {
		return valColor;
	}

	public void setValColor(String valColor) {
		this.valColor = valColor;
	}


}