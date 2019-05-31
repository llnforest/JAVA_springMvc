package com.model.w2;
// default package

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Repository;

/**
 * AbstractW2Queuing entity provides the base persistence definition of the
 * W2Queuing entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "w2_queuing")
@Repository
public class W2Queuing extends com.model.BaseModel implements
		java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String ksbh;
	private String xm;
	private String zjhm;
	private String zkzh;
	private String kscx;
	private String ykxms;
	private String zkxms;
	private Short bdxh;
	private String kszt;
	private Byte kscs;
	private Byte ykcs;
	private String kcbh;
	private String wcxms;
	private Short kscj;
	private Timestamp kssj;
	private String cdxmbh;
	private String sfyz;
	private String kslx;
	private String djc;
	private String jxdm;
	private Timestamp ksrq;
	private String ksxm;
	private String zt;
	private Timestamp sqsj;
	private String jxmc;
	private String qxlx;
	private String kcxh;
	private String flag;
	private String jszt;
	private String kg;
	private String lsh;
	private String zkxmdm;
	private String kgname;
	private String finger;
	private String sqks;
	private String kchp;
	private String sfyk;
	private String zsfhg;
	private BigDecimal zcs;
	private Short score;
	private String kfxm;
	private String wcxm;
	private String kcbs;
	private String fieldid;
	private String kg2;
	private Long kscc;
	private BigDecimal sign;
	private String signcontent;
	private String ksyy;
	private String jbr;
	private String glbm;
	private String yycs;
	private String bcyykscs;
	private Boolean RLine;
	private String linecode;
	private Timestamp xmkssj;
	private Boolean ILock;
	private String SSafe;
	private String SSafeZjhm;
	private String message;

	// Constructors

	/** default constructor */
	public W2Queuing() {
	}

	/** full constructor */
	public W2Queuing(String ksbh, String xm, String zjhm, String zkzh,
			String kscx, String ykxms, String zkxms, Short bdxh, String kszt,
			Byte kscs, Byte ykcs, String kcbh, String wcxms, Short kscj,
			Timestamp kssj, String cdxmbh, String sfyz, String kslx, String djc,
			String jxdm, Timestamp ksrq, String ksxm, String zt,
			Timestamp sqsj, String jxmc, String qxlx, String kcxh, String flag,
			String jszt, String kg, String lsh, String zkxmdm, String kgname,
			String finger, String sqks, String kchp, String sfyk, String zsfhg,
			BigDecimal zcs, Short score, String kfxm, String wcxm, String kcbs,
			String fieldid, String kg2, Long kscc, BigDecimal sign,
			String signcontent, String ksyy, String jbr, String glbm,
			String yycs, String bcyykscs, Boolean RLine, String linecode,
			Timestamp xmkssj, Boolean ILock, String SSafe, String SSafeZjhm,
			String message) {
		this.ksbh = ksbh;
		this.xm = xm;
		this.zjhm = zjhm;
		this.zkzh = zkzh;
		this.kscx = kscx;
		this.ykxms = ykxms;
		this.zkxms = zkxms;
		this.bdxh = bdxh;
		this.kszt = kszt;
		this.kscs = kscs;
		this.ykcs = ykcs;
		this.kcbh = kcbh;
		this.wcxms = wcxms;
		this.kscj = kscj;
		this.kssj = kssj;
		this.cdxmbh = cdxmbh;
		this.sfyz = sfyz;
		this.kslx = kslx;
		this.djc = djc;
		this.jxdm = jxdm;
		this.ksrq = ksrq;
		this.ksxm = ksxm;
		this.zt = zt;
		this.sqsj = sqsj;
		this.jxmc = jxmc;
		this.qxlx = qxlx;
		this.kcxh = kcxh;
		this.flag = flag;
		this.jszt = jszt;
		this.kg = kg;
		this.lsh = lsh;
		this.zkxmdm = zkxmdm;
		this.kgname = kgname;
		this.finger = finger;
		this.sqks = sqks;
		this.kchp = kchp;
		this.sfyk = sfyk;
		this.zsfhg = zsfhg;
		this.zcs = zcs;
		this.score = score;
		this.kfxm = kfxm;
		this.wcxm = wcxm;
		this.kcbs = kcbs;
		this.fieldid = fieldid;
		this.kg2 = kg2;
		this.kscc = kscc;
		this.sign = sign;
		this.signcontent = signcontent;
		this.ksyy = ksyy;
		this.jbr = jbr;
		this.glbm = glbm;
		this.yycs = yycs;
		this.bcyykscs = bcyykscs;
		this.RLine = RLine;
		this.linecode = linecode;
		this.xmkssj = xmkssj;
		this.ILock = ILock;
		this.SSafe = SSafe;
		this.SSafeZjhm = SSafeZjhm;
		this.message = message;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, precision = 8, scale = 0)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "KSBH", length = 12)
	public String getKsbh() {
		return this.ksbh;
	}

	public void setKsbh(String ksbh) {
		this.ksbh = ksbh;
	}

	@Column(name = "XM", length = 200)
	public String getXm() {
		return this.xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	@Column(name = "ZJHM", length = 18)
	public String getZjhm() {
		return this.zjhm;
	}

	public void setZjhm(String zjhm) {
		this.zjhm = zjhm;
	}

	@Column(name = "ZKZH", length = 20)
	public String getZkzh() {
		return this.zkzh;
	}

	public void setZkzh(String zkzh) {
		this.zkzh = zkzh;
	}

	@Column(name = "KSCX", length = 4)
	public String getKscx() {
		return this.kscx;
	}

	public void setKscx(String kscx) {
		this.kscx = kscx;
	}

	@Column(name = "YKXMS", length = 400)
	public String getYkxms() {
		return this.ykxms;
	}

	public void setYkxms(String ykxms) {
		this.ykxms = ykxms;
	}

	@Column(name = "ZKXMS", length = 100)
	public String getZkxms() {
		return this.zkxms;
	}

	public void setZkxms(String zkxms) {
		this.zkxms = zkxms;
	}

	@Column(name = "BDXH", precision = 3, scale = 0)
	public Short getBdxh() {
		return this.bdxh;
	}

	public void setBdxh(Short bdxh) {
		this.bdxh = bdxh;
	}

	@Column(name = "KSZT", length = 1)
	public String getKszt() {
		return this.kszt;
	}

	public void setKszt(String kszt) {
		this.kszt = kszt;
	}

	@Column(name = "KSCS", precision = 2, scale = 0)
	public Byte getKscs() {
		return this.kscs;
	}

	public void setKscs(Byte kscs) {
		this.kscs = kscs;
	}

	@Column(name = "YKCS", precision = 2, scale = 0)
	public Byte getYkcs() {
		return this.ykcs;
	}

	public void setYkcs(Byte ykcs) {
		this.ykcs = ykcs;
	}

	@Column(name = "KCBH", length = 3)
	public String getKcbh() {
		return this.kcbh;
	}

	public void setKcbh(String kcbh) {
		this.kcbh = kcbh;
	}

	@Column(name = "WCXMS", length = 400)
	public String getWcxms() {
		return this.wcxms;
	}

	public void setWcxms(String wcxms) {
		this.wcxms = wcxms;
	}

	@Column(name = "KSCJ", precision = 3, scale = 0)
	public Short getKscj() {
		return this.kscj;
	}

	public void setKscj(Short kscj) {
		this.kscj = kscj;
	}

	@Column(name = "KSSJ", length = 7)
	public Timestamp getKssj() {
		return this.kssj;
	}

	public void setKssj(Timestamp kssj) {
		this.kssj = kssj;
	}

	@Column(name = "CDXMBH", length = 10)
	public String getCdxmbh() {
		return this.cdxmbh;
	}

	public void setCdxmbh(String cdxmbh) {
		this.cdxmbh = cdxmbh;
	}

	@Column(name = "SFYZ", length = 1)
	public String getSfyz() {
		return this.sfyz;
	}

	public void setSfyz(String sfyz) {
		this.sfyz = sfyz;
	}

	@Column(name = "KSLX", length = 1)
	public String getKslx() {
		return this.kslx;
	}

	public void setKslx(String kslx) {
		this.kslx = kslx;
	}

	@Column(name = "DJC", precision = 2, scale = 0)
	public String getDjc() {
		return this.djc;
	}

	public void setDjc(String djc) {
		this.djc = djc;
	}

	@Column(name = "JXDM", length = 10)
	public String getJxdm() {
		return this.jxdm;
	}

	public void setJxdm(String jxdm) {
		this.jxdm = jxdm;
	}

	@Column(name = "KSRQ", length = 7)
	public Timestamp getKsrq() {
		return this.ksrq;
	}

	public void setKsrq(Timestamp ksrq) {
		this.ksrq = ksrq;
	}

	@Column(name = "KSXM", length = 200)
	public String getKsxm() {
		return this.ksxm;
	}

	public void setKsxm(String ksxm) {
		this.ksxm = ksxm;
	}

	@Column(name = "ZT", length = 1)
	public String getZt() {
		return this.zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	@Column(name = "SQSJ", length = 7)
	public Timestamp getSqsj() {
		return this.sqsj;
	}

	public void setSqsj(Timestamp sqsj) {
		this.sqsj = sqsj;
	}

	@Column(name = "JXMC", length = 200)
	public String getJxmc() {
		return this.jxmc;
	}

	public void setJxmc(String jxmc) {
		this.jxmc = jxmc;
	}

	@Column(name = "QXLX", length = 1)
	public String getQxlx() {
		return this.qxlx;
	}

	public void setQxlx(String qxlx) {
		this.qxlx = qxlx;
	}

	@Column(name = "KCXH", length = 16)
	public String getKcxh() {
		return this.kcxh;
	}

	public void setKcxh(String kcxh) {
		this.kcxh = kcxh;
	}

	@Column(name = "FLAG", length = 1)
	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Column(name = "JSZT", length = 1)
	public String getJszt() {
		return this.jszt;
	}

	public void setJszt(String jszt) {
		this.jszt = jszt;
	}

	@Column(name = "KG", length = 18)
	public String getKg() {
		return this.kg;
	}

	public void setKg(String kg) {
		this.kg = kg;
	}

	@Column(name = "LSH", length = 20)
	public String getLsh() {
		return this.lsh;
	}

	public void setLsh(String lsh) {
		this.lsh = lsh;
	}

	@Column(name = "ZKXMDM", length = 6)
	public String getZkxmdm() {
		return this.zkxmdm;
	}

	public void setZkxmdm(String zkxmdm) {
		this.zkxmdm = zkxmdm;
	}

	@Column(name = "KGNAME", length = 32)
	public String getKgname() {
		return this.kgname;
	}

	public void setKgname(String kgname) {
		this.kgname = kgname;
	}

	@Column(name = "FINGER", length = 100)
	public String getFinger() {
		return this.finger;
	}

	public void setFinger(String finger) {
		this.finger = finger;
	}

	@Column(name = "SQKS", length = 1)
	public String getSqks() {
		return this.sqks;
	}

	public void setSqks(String sqks) {
		this.sqks = sqks;
	}

	@Column(name = "KCHP", length = 20)
	public String getKchp() {
		return this.kchp;
	}

	public void setKchp(String kchp) {
		this.kchp = kchp;
	}

	@Column(name = "SFYK", length = 1)
	public String getSfyk() {
		return this.sfyk;
	}

	public void setSfyk(String sfyk) {
		this.sfyk = sfyk;
	}

	@Column(name = "ZSFHG", length = 1)
	public String getZsfhg() {
		return this.zsfhg;
	}

	public void setZsfhg(String zsfhg) {
		this.zsfhg = zsfhg;
	}

	@Column(name = "ZCS", precision = 22, scale = 0)
	public BigDecimal getZcs() {
		return this.zcs;
	}

	public void setZcs(BigDecimal zcs) {
		this.zcs = zcs;
	}

	@Column(name = "SCORE", precision = 4, scale = 0)
	public Short getScore() {
		return this.score;
	}

	public void setScore(Short score) {
		this.score = score;
	}

	@Column(name = "KFXM", length = 20)
	public String getKfxm() {
		return this.kfxm;
	}

	public void setKfxm(String kfxm) {
		this.kfxm = kfxm;
	}

	@Column(name = "WCXM", length = 100)
	public String getWcxm() {
		return this.wcxm;
	}

	public void setWcxm(String wcxm) {
		this.wcxm = wcxm;
	}

	@Column(name = "KCBS", length = 20)
	public String getKcbs() {
		return this.kcbs;
	}

	public void setKcbs(String kcbs) {
		this.kcbs = kcbs;
	}

	@Column(name = "FIELDID", length = 10)
	public String getFieldid() {
		return this.fieldid;
	}

	public void setFieldid(String fieldid) {
		this.fieldid = fieldid;
	}

	@Column(name = "KG2", length = 100)
	public String getKg2() {
		return this.kg2;
	}

	public void setKg2(String kg2) {
		this.kg2 = kg2;
	}

	@Column(name = "KSCC", precision = 18, scale = 0)
	public Long getKscc() {
		return this.kscc;
	}

	public void setKscc(Long kscc) {
		this.kscc = kscc;
	}

	@Column(name = "SIGN", precision = 22, scale = 0)
	public BigDecimal getSign() {
		return this.sign;
	}

	public void setSign(BigDecimal sign) {
		this.sign = sign;
	}

	@Column(name = "SIGNCONTENT", length = 1000)
	public String getSigncontent() {
		return this.signcontent;
	}

	public void setSigncontent(String signcontent) {
		this.signcontent = signcontent;
	}

	@Column(name = "KSYY", length = 20)
	public String getKsyy() {
		return this.ksyy;
	}

	public void setKsyy(String ksyy) {
		this.ksyy = ksyy;
	}

	@Column(name = "JBR", length = 20)
	public String getJbr() {
		return this.jbr;
	}

	public void setJbr(String jbr) {
		this.jbr = jbr;
	}

	@Column(name = "GLBM", length = 20)
	public String getGlbm() {
		return this.glbm;
	}

	public void setGlbm(String glbm) {
		this.glbm = glbm;
	}

	@Column(name = "YYCS", length = 20)
	public String getYycs() {
		return this.yycs;
	}

	public void setYycs(String yycs) {
		this.yycs = yycs;
	}

	@Column(name = "BCYYKSCS", length = 20)
	public String getBcyykscs() {
		return this.bcyykscs;
	}

	public void setBcyykscs(String bcyykscs) {
		this.bcyykscs = bcyykscs;
	}

	@Column(name = "R_LINE", precision = 1, scale = 0)
	public Boolean getRLine() {
		return this.RLine;
	}

	public void setRLine(Boolean RLine) {
		this.RLine = RLine;
	}

	@Column(name = "LINECODE", length = 100)
	public String getLinecode() {
		return this.linecode;
	}

	public void setLinecode(String linecode) {
		this.linecode = linecode;
	}

	@Column(name = "XMKSSJ", length = 7)
	public Timestamp getXmkssj() {
		return this.xmkssj;
	}

	public void setXmkssj(Timestamp xmkssj) {
		this.xmkssj = xmkssj;
	}

	@Column(name = "I_LOCK", precision = 1, scale = 0)
	public Boolean getILock() {
		return this.ILock;
	}

	public void setILock(Boolean ILock) {
		this.ILock = ILock;
	}

	@Column(name = "S_SAFE", length = 20)
	public String getSSafe() {
		return this.SSafe;
	}

	public void setSSafe(String SSafe) {
		this.SSafe = SSafe;
	}

	@Column(name = "S_SAFE_ZJHM", length = 50)
	public String getSSafeZjhm() {
		return this.SSafeZjhm;
	}

	public void setSSafeZjhm(String SSafeZjhm) {
		this.SSafeZjhm = SSafeZjhm;
	}

	@Column(name = "MESSAGE", length = 4000)
	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}