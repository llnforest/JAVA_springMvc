package com.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**  
 * 该对象是所有数据库实体对象的基类
 * Repository 不定义名称则默认使用类名（首字母小写）作为been名称
 * @Scope 对有状态的对象设置属性prototype，每次都创建一个新的对象，已经通过过滤器统一设置
 * @author:wangzhen
 * @version:V1.0
 * 2017年4月27日  
 */
@Repository("model")
@Scope("prototype")
@MappedSuperclass
public class BaseModel  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	/**
	 * 预留数据状态，根据业务不同需要可以标识数据逻辑删除、数据同步状态等
	 */
	protected String dataFlag;
	/**
	 * 创建人,理论上每张表都需要记录创建人
	 */
	protected String createId;
	/**
	 * 创建时间,理论上每张表都需要记录创建时间
	 */
	protected Date createTime;
	/**
	 * 最后修改人,理论上每张表都需要记录最后修改人
	 */
	protected String modifyId;
	/**
	 * 最后修改时间,理论上每张表都需要记录最后修改时间
	 */
	protected Date modifyTime;
	
	
	public BaseModel() {
	}
	
	public BaseModel(String dataFlag, String createId, Date createTime,String modifyId, Date modifyTime) {
		this.dataFlag = dataFlag;
		this.createId = createId;
		this.createTime = createTime;
		this.modifyId = modifyId;
		this.modifyTime = modifyTime;
	}
	
	
	@Column(name = "DATA_FLAG", length = 40)
	public String getDataFlag() {
		return dataFlag;
	}
	public void setDataFlag(String dataFlag) {
		this.dataFlag = dataFlag;
	}
	@Column(name = "CREATE_ID", length = 32)
	public String getCreateId() {
		return createId;
	}
	public void setCreateId(String createId) {
		this.createId = createId;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME",updatable=false)
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Column(name = "MODIFY_ID", length = 32)
	public String getModifyId() {
		return modifyId;
	}
	public void setModifyId(String modifyId) {
		this.modifyId = modifyId;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MODIFY_TIME")
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	

}
