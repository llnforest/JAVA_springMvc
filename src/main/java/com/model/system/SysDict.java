package com.model.system;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Repository;

/**
 * SysDict entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_dict")
@Repository
public class SysDict extends com.model.BaseModel implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	private String dictId;
	private String dictName;
	private String dictCode;
	private String dictType;
	private Integer dictOrder;
	private String remark;
	private Set<SysDictValue> sysDictValues = new HashSet<SysDictValue>(0);

	// Constructors

	/** default constructor */
	public SysDict() {
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "DICT_ID", nullable = false, length = 32)
	public String getDictId() {
		return this.dictId;
	}

	public void setDictId(String dictId) {
		this.dictId = dictId;
	}

	@Column(name = "DICT_NAME", length = 60)
	public String getDictName() {
		return this.dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	@Column(name = "DICT_CODE", length = 40)
	public String getDictCode() {
		return this.dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}

	@Column(name = "DICT_TYPE", length = 40)
	public String getDictType() {
		return this.dictType;
	}

	public void setDictType(String dictType) {
		this.dictType = dictType;
	}

	@Column(name = "DICT_ORDER", precision = 8, scale = 0)
	public Integer getDictOrder() {
		return this.dictOrder;
	}

	public void setDictOrder(Integer dictOrder) {
		this.dictOrder = dictOrder;
	}

	@Column(name = "REMARK", length = 1000)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysDict")
	public Set<SysDictValue> getSysDictValues() {
		return this.sysDictValues;
	}

	public void setSysDictValues(Set<SysDictValue> sysDictValues) {
		this.sysDictValues = sysDictValues;
	}

}