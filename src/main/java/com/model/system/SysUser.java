package com.model.system;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.fileupload.util.LimitedInputStream;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Repository;


/**
 * SysUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="sys_user")
@Repository
public class SysUser extends com.model.BaseModel implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
    // Fields    
	 private String userId;
     private String UserNo;
     private String userName;
     private String loginName;
     private String loginPwd;
     private String phone;
     private String state;
     private Integer userOrder;
     private String remark;
     private String userCardId;
     private String userType;
     private Date userExpDate;
     private Date pwdExpDate;
     private String limitStart;
     private String limitEnd;
     private String ip;
     private String sCode;



    // Constructors
	/** default constructor */
    public SysUser() {
    }

   
    // Property accessors
    @GenericGenerator(name="generator", strategy="uuid.hex")
    @Id 
    @GeneratedValue(generator="generator")
    @Column(name="USER_ID", nullable=false, length=32,unique=true)

    public String getUserId() {
        return this.userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    
    @Column(name="USER_NAME", length=60)
    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    @Column(name="LOGIN_NAME", length=40,unique=true)
    public String getLoginName() {
        return this.loginName;
    }
    
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
    
    @Column(name="LOGIN_PWD", length=40)
    public String getLoginPwd() {
        return this.loginPwd;
    }
    
    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }
    
    @Column(name="PHONE", length=40)
    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    @Column(name="STATE", length=40)
    public String getState() {
        return this.state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    
    @Column(name="USER_ORDER", precision=8, scale=0)
    public Integer getUserOrder() {
        return this.userOrder;
    }
    
    public void setUserOrder(Integer userOrder) {
        this.userOrder = userOrder;
    }
    
    @Column(name="REMARK", length=1000)
    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    @Column(name="USER_NO", length=20, unique=true)
    public String getUserNo() {
		return UserNo;
	}
	public void setUserNo(String userNo) {
		UserNo = userNo;
	}


    @Column(name="USER_CARD_ID", length=20,unique=true)	
	public String getUserCardId() {
		return userCardId;
	}

	public void setUserCardId(String userCardId) {
		this.userCardId = userCardId;
	}


	@Column(name="USER_TYPE", length=40)
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="USER_EXP_DATE", length=40)
	public Date getUserExpDate() {
		return userExpDate;
	}

	public void setUserExpDate(Date userExpDate) {
		this.userExpDate = userExpDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="PWD_EXP_DATE", length=40)
	public Date getPwdExpDate() {
		return pwdExpDate;
	}

	public void setPwdExpDate(Date pwdExpDate) {
		this.pwdExpDate = pwdExpDate;
	}


	@Column(name="LIMIT_START", length=40)
	public String getLimitStart() {
		return limitStart;
	}

	public void setLimitStart(String limitStart) {
		this.limitStart = limitStart;
	}


	@Column(name="LIMIT_END", length=40)
	public String getLimitEnd() {
		return limitEnd;
	}

	public void setLimitEnd(String limitEnd) {
		this.limitEnd = limitEnd;
	}

	@Column(name="IP", length=100)
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Column(name="S_CODE", length=1000)
	public String getsCode() {
		return sCode;
	}


	public void setsCode(String sCode) {
		this.sCode = sCode;
	}


}