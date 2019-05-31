package com.model.w2;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Repository;

import com.model.BaseModel;

/**
 * W2Kcxx entity provides the base persistence definition of the W2Kcxx
 * entity. @author MyEclipse Persistence Tools
 */

@Entity
@Table(name = "w2_kcxx")
@Repository
public class W2Kcxx extends BaseModel  implements
		java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields
	private Integer id;
	private String cph;
	private String kscx;
	private String czip;
	private String zxip;
	private String zt;
	private String kch;
	private String ksbh;
	private String kcmc;
	private String cuser;
	private String cpwd;
	private String xmxh;
	private String zuser;
	private String zpwd;
	private String mdzt;
	private Short mdrs;
	private String zc;
	private String lxbs;
	private String msg;
	private String fieldid;
	private String fieldname;
	private Byte fieldstatus;
	private String signcheck;
	private String fourip;
	private String fouruser;
	private String fourpwd;
	private String allline;
	private String curline;
	private Integer line;
	private String SStarttime;
	private String SEndtime;

	// Constructors

	/** default constructor */
	public W2Kcxx() {
	}

	/** minimal constructor */
	public W2Kcxx(String cph, String kscx, String czip) {
		this.cph = cph;
		this.kscx = kscx;
		this.czip = czip;
	}

	/** full constructor */
	public W2Kcxx(String cph, String kscx, String czip, String zxip,
			String zt, String kch, String ksbh, String kcmc, String cuser,
			String cpwd, String xmxh, String zuser, String zpwd, String mdzt,
			Short mdrs, String zc, String lxbs, String msg, String fieldid,
			String fieldname, Byte fieldstatus, String signcheck,
			String fourip, String fouruser, String fourpwd, String allline,
			String curline, Integer line, String SStarttime, String SEndtime) {
		this.cph = cph;
		this.kscx = kscx;
		this.czip = czip;
		this.zxip = zxip;
		this.zt = zt;
		this.kch = kch;
		this.ksbh = ksbh;
		this.kcmc = kcmc;
		this.cuser = cuser;
		this.cpwd = cpwd;
		this.xmxh = xmxh;
		this.zuser = zuser;
		this.zpwd = zpwd;
		this.mdzt = mdzt;
		this.mdrs = mdrs;
		this.zc = zc;
		this.lxbs = lxbs;
		this.msg = msg;
		this.fieldid = fieldid;
		this.fieldname = fieldname;
		this.fieldstatus = fieldstatus;
		this.signcheck = signcheck;
		this.fourip = fourip;
		this.fouruser = fouruser;
		this.fourpwd = fourpwd;
		this.allline = allline;
		this.curline = curline;
		this.line = line;
		this.SStarttime = SStarttime;
		this.SEndtime = SEndtime;
	}

	// Property accessors
//	@GenericGenerator(name = "generator", strategy = "uuid.hex")
//	@GeneratedValue(generator = "generator")
	@Id
	@SequenceGenerator(name="SEQ_KCXX_ID",sequenceName="SEQ_KCXX_ID",allocationSize=1,initialValue=1)
	@GeneratedValue(generator = "SEQ_KCXX_ID",strategy = GenerationType.SEQUENCE)
	@Column(name = "ID", unique = true, nullable = false, length = 6)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "CPH", nullable = false, length = 20)
	public String getCph() {
		return this.cph;
	}

	public void setCph(String cph) {
		this.cph = cph;
	}

	@Column(name = "KSCX", nullable = false, length = 20)
	public String getKscx() {
		return this.kscx;
	}

	public void setKscx(String kscx) {
		this.kscx = kscx;
	}

	@Column(name = "CZIP", nullable = false, length = 32)
	public String getCzip() {
		return this.czip;
	}

	public void setCzip(String czip) {
		this.czip = czip;
	}

	@Column(name = "ZXIP", length = 32)
	public String getZxip() {
		return this.zxip;
	}

	public void setZxip(String zxip) {
		this.zxip = zxip;
	}

	@Column(name = "ZT", length = 1)
	public String getZt() {
		return this.zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	@Column(name = "KCH", length = 4)
	public String getKch() {
		return this.kch;
	}

	public void setKch(String kch) {
		this.kch = kch;
	}

	@Column(name = "KSBH", length = 12)
	public String getKsbh() {
		return this.ksbh;
	}

	public void setKsbh(String ksbh) {
		this.ksbh = ksbh;
	}

	@Column(name = "KCMC", length = 20)
	public String getKcmc() {
		return this.kcmc;
	}

	public void setKcmc(String kcmc) {
		this.kcmc = kcmc;
	}

	@Column(name = "CUSER", length = 5)
	public String getCuser() {
		return this.cuser;
	}

	public void setCuser(String cuser) {
		this.cuser = cuser;
	}

	@Column(name = "CPWD", length = 50)
	public String getCpwd() {
		return this.cpwd;
	}

	public void setCpwd(String cpwd) {
		this.cpwd = cpwd;
	}

	@Column(name = "XMXH", length = 100)
	public String getXmxh() {
		return this.xmxh;
	}

	public void setXmxh(String xmxh) {
		this.xmxh = xmxh;
	}

	@Column(name = "ZUSER", length = 5)
	public String getZuser() {
		return this.zuser;
	}

	public void setZuser(String zuser) {
		this.zuser = zuser;
	}

	@Column(name = "ZPWD", length = 50)
	public String getZpwd() {
		return this.zpwd;
	}

	public void setZpwd(String zpwd) {
		this.zpwd = zpwd;
	}

	@Column(name = "MDZT", length = 1)
	public String getMdzt() {
		return this.mdzt;
	}

	public void setMdzt(String mdzt) {
		this.mdzt = mdzt;
	}

	@Column(name = "MDRS", precision = 4, scale = 0)
	public Short getMdrs() {
		return this.mdrs;
	}

	public void setMdrs(Short mdrs) {
		this.mdrs = mdrs;
	}

	@Column(name = "ZC", length = 1)
	public String getZc() {
		return this.zc;
	}

	public void setZc(String zc) {
		this.zc = zc;
	}

	@Column(name = "LXBS", length = 1)
	public String getLxbs() {
		return this.lxbs;
	}

	public void setLxbs(String lxbs) {
		this.lxbs = lxbs;
	}

	@Column(name = "MSG", length = 100)
	public String getMsg() {
		return this.msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Column(name = "FIELDID", length = 200)
	public String getFieldid() {
		return this.fieldid;
	}

	public void setFieldid(String fieldid) {
		this.fieldid = fieldid;
	}

	@Column(name = "FIELDNAME", length = 200)
	public String getFieldname() {
		return this.fieldname;
	}

	public void setFieldname(String fieldname) {
		this.fieldname = fieldname;
	}

	@Column(name = "FIELDSTATUS", precision = 2, scale = 0)
	public Byte getFieldstatus() {
		return this.fieldstatus;
	}

	public void setFieldstatus(Byte fieldstatus) {
		this.fieldstatus = fieldstatus;
	}

	@Column(name = "SIGNCHECK", length = 100)
	public String getSigncheck() {
		return this.signcheck;
	}

	public void setSigncheck(String signcheck) {
		this.signcheck = signcheck;
	}

	@Column(name = "FOURIP", length = 32)
	public String getFourip() {
		return this.fourip;
	}

	public void setFourip(String fourip) {
		this.fourip = fourip;
	}

	@Column(name = "FOURUSER", length = 5)
	public String getFouruser() {
		return this.fouruser;
	}

	public void setFouruser(String fouruser) {
		this.fouruser = fouruser;
	}

	@Column(name = "FOURPWD", length = 50)
	public String getFourpwd() {
		return this.fourpwd;
	}

	public void setFourpwd(String fourpwd) {
		this.fourpwd = fourpwd;
	}

	@Column(name = "ALLLINE", length = 200)
	public String getAllline() {
		return this.allline;
	}

	public void setAllline(String allline) {
		this.allline = allline;
	}

	@Column(name = "CURLINE", length = 100)
	public String getCurline() {
		return this.curline;
	}

	public void setCurline(String curline) {
		this.curline = curline;
	}

	@Column(name = "LINE", precision = 1, scale = 0)
	public Integer getLine() {
		return this.line;
	}

	public void setLine(Integer line) {
		this.line = line;
	}

	@Column(name = "S_STARTTIME", length = 100)
	public String getSStarttime() {
		return this.SStarttime;
	}

	public void setSStarttime(String SStarttime) {
		this.SStarttime = SStarttime;
	}

	@Column(name = "S_ENDTIME", length = 100)
	public String getSEndtime() {
		return this.SEndtime;
	}

	public void setSEndtime(String SEndtime) {
		this.SEndtime = SEndtime;
	}

}