package com.model.system;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Repository;

/**
 * SysLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_log")
@Repository
public class SysLog extends com.model.BaseModel implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields

	private String logId;
	private String operateName;
	private String operateType;
	private String operateResult;
	private String appName;
	private String appUrl;
	private String clientOs;
	private String clientIp;
	private String clientBrowser;
	private String clientMac;
	private String logLevel;
	private String remark;
	private String clientName;
	private String userName;
	private String httpData;

	// Constructors

	/** default constructor */
	public SysLog() {
	}
	
	public SysLog(String createId,String userName,String operateName, String operateType, String operateResult, String appName, String appUrl, String clientOs,String clientIp, String clientBrowser, String remark,String clientMac,String logLevel,String clientName,String httpData) {
		super();
		super.createId = createId;
		this.userName = userName;
		this.operateName = operateName;
		this.operateType = operateType;
		this.operateResult = operateResult;
		this.appName = appName;
		this.appUrl = appUrl;
		this.clientOs = clientOs;
		this.clientIp = clientIp;
		this.clientBrowser = clientBrowser;
		this.remark = remark;
		this.clientMac = clientMac;
		this.logLevel = logLevel;
		this.clientName = clientName;
		this.setHttpData(httpData);
	}



	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "LOG_ID", nullable = false, length = 32)
	public String getLogId() {
		return this.logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	@Column(name = "OPERATE_NAME", length = 60)
	public String getOperateName() {
		return this.operateName;
	}

	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}

	@Column(name = "OPERATE_TYPE", length = 40)
	public String getOperateType() {
		return this.operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	@Column(name = "OPERATE_RESULT", length = 40)
	public String getOperateResult() {
		return this.operateResult;
	}

	public void setOperateResult(String operateResult) {
		this.operateResult = operateResult;
	}

	@Column(name = "APP_NAME", length = 60)
	public String getAppName() {
		return this.appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	@Column(name = "APP_URL", length = 100)
	public String getAppUrl() {
		return this.appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

	@Column(name = "CLIENT_OS", length = 60)
	public String getClientOs() {
		return this.clientOs;
	}

	public void setClientOs(String clientOs) {
		this.clientOs = clientOs;
	}

	@Column(name = "CLIENT_IP", length = 40)
	public String getClientIp() {
		return this.clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	@Column(name = "CLIENT_BROWSER", length = 60)
	public String getClientBrowser() {
		return this.clientBrowser;
	}

	public void setClientBrowser(String clientBrowser) {
		this.clientBrowser = clientBrowser;
	}

	@Column(name = "REMARK", length = 1000)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Column(name = "CLIENT_MAC", length = 1000)
	public String getClientMac() {
		return clientMac;
	}

	public void setClientMac(String clientMac) {
		this.clientMac = clientMac;
	}
	
	@Column(name = "LOG_LEVEL", length = 1000)
	public String getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(String logLevel) {
		this.logLevel = logLevel;
	}
	
	@Column(name = "CLIENT_NAME", length = 40)
	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	@Column(name = "USER_NAME", length = 40)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "HTTP_DATA", length = 1000)
	public String getHttpData() {
		return httpData;
	}

	public void setHttpData(String httpData) {
		this.httpData = httpData;
	}

}