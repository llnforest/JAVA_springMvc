package com.model.system;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Repository;

/**
 * SysRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_role")
@Repository
public class SysRole extends com.model.BaseModel implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields
	private String roleId;
	private String roleName;
	private String roleGroup;
	private Integer roleOrder;
	private String remark;

	// Constructors

	/** default constructor */
	public SysRole() {
	}


	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ROLE_ID", nullable = false, length = 32)
	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Column(name = "ROLE_NAME", length = 60)
	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name = "ROLE_GROUP", length = 60)
	public String getRoleGroup() {
		return this.roleGroup;
	}

	public void setRoleGroup(String roleGroup) {
		this.roleGroup = roleGroup;
	}

	@Column(name = "ROLE_ORDER", precision = 8, scale = 0)
	public Integer getRoleOrder() {
		return this.roleOrder;
	}

	public void setRoleOrder(Integer roleOrder) {
		this.roleOrder = roleOrder;
	}

	@Column(name = "REMARK", length = 1000)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}