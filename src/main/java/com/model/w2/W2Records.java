package com.model.w2;
// default package

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Repository;

/**
 * AbstractW2Records entity provides the base persistence definition of the
 * W2Records entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "w2_records")
@Repository
public class W2Records extends com.model.BaseModel implements
		java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String ksbh;
	private String kscj1;
	private Timestamp ksrq1;
	private Timestamp kssj1;
	private Timestamp jssj1;
	private String scf1;
	private String kscj2;
	private Timestamp ksrq2;
	private Timestamp kssj2;
	private Timestamp jssj2;
	private String scf2;
	private Short kscs;
	private String kfxx1;
	private String kcbh;
	private String kscc;
	private String czy;
	private String ksjg;
	private String sjjyw;
	private String kszt;
	private String sfwp;
	private String sfsp;
	private String zt;
	private String ksxm;
	private String sqbs;
	private Timestamp sqrq;
	private String sqyy;
	private String sqr;
	private String spr;
	private Timestamp sprq;
	private String sqr1;
	private String sfqk;
	private String kfxx2;
	private String xm;
	private String zjzp;
	private String jbzp;
	private Short jgfs1;
	private Short jgfs2;
	private Byte dycs;
	private String sfprint;
	private String flag;
	private String ksy1;
	private String ksy2;
	private String kcxh;
	private Timestamp ksrq;
	private String kfss1;
	private String kfss2;
	private String sszp;
	private String zkzh;
	private String kscx;
	private String zjhm;
	private String ksyy;
	private String jxdm;
	private String lsh;
	private String kchp;
	private String jbr;
	private String glbm;
	private String bcyykscs;
	private String jkSszpstatus;
	private String jxmc;
	private String sfyk;
	private Boolean line;
	private String SZjmc;
	private String SXmhz;
	private String ykrq;
	private Byte yycs;
	private Boolean ykflag;
	private Boolean ykdjc;
	private String SSafe;
	private String SSafeZjhm;

	// Constructors

	/** default constructor */
	public W2Records() {
	}

	/** minimal constructor */
	public W2Records(String ksbh) {
		this.ksbh = ksbh;
	}

	/** full constructor */
	public W2Records(String ksbh, String kscj1, Timestamp ksrq1,
			Timestamp kssj1, Timestamp jssj1, String scf1, String kscj2,
			Timestamp ksrq2, Timestamp kssj2, Timestamp jssj2, String scf2,
			Short kscs, String kfxx1, String kcbh, String kscc, String czy,
			String ksjg, String sjjyw, String kszt, String sfwp, String sfsp,
			String zt, String ksxm, String sqbs, Timestamp sqrq, String sqyy,
			String sqr, String spr, Timestamp sprq, String sqr1, String sfqk,
			String kfxx2, String xm, String zjzp, String jbzp, Short jgfs1,
			Short jgfs2, Byte dycs, String sfprint, String flag, String ksy1,
			String ksy2, String kcxh, Timestamp ksrq, String kfss1,
			String kfss2, String sszp, String zkzh, String kscx, String zjhm,
			String ksyy, String jxdm, String lsh, String kchp, String jbr,
			String glbm, String bcyykscs, String jkSszpstatus, String jxmc,
			String sfyk, Boolean line, String SZjmc, String SXmhz, String ykrq,
			Byte yycs, Boolean ykflag, Boolean ykdjc, String SSafe,
			String SSafeZjhm) {
		this.ksbh = ksbh;
		this.kscj1 = kscj1;
		this.ksrq1 = ksrq1;
		this.kssj1 = kssj1;
		this.jssj1 = jssj1;
		this.scf1 = scf1;
		this.kscj2 = kscj2;
		this.ksrq2 = ksrq2;
		this.kssj2 = kssj2;
		this.jssj2 = jssj2;
		this.scf2 = scf2;
		this.kscs = kscs;
		this.kfxx1 = kfxx1;
		this.kcbh = kcbh;
		this.kscc = kscc;
		this.czy = czy;
		this.ksjg = ksjg;
		this.sjjyw = sjjyw;
		this.kszt = kszt;
		this.sfwp = sfwp;
		this.sfsp = sfsp;
		this.zt = zt;
		this.ksxm = ksxm;
		this.sqbs = sqbs;
		this.sqrq = sqrq;
		this.sqyy = sqyy;
		this.sqr = sqr;
		this.spr = spr;
		this.sprq = sprq;
		this.sqr1 = sqr1;
		this.sfqk = sfqk;
		this.kfxx2 = kfxx2;
		this.xm = xm;
		this.zjzp = zjzp;
		this.jbzp = jbzp;
		this.jgfs1 = jgfs1;
		this.jgfs2 = jgfs2;
		this.dycs = dycs;
		this.sfprint = sfprint;
		this.flag = flag;
		this.ksy1 = ksy1;
		this.ksy2 = ksy2;
		this.kcxh = kcxh;
		this.ksrq = ksrq;
		this.kfss1 = kfss1;
		this.kfss2 = kfss2;
		this.sszp = sszp;
		this.zkzh = zkzh;
		this.kscx = kscx;
		this.zjhm = zjhm;
		this.ksyy = ksyy;
		this.jxdm = jxdm;
		this.lsh = lsh;
		this.kchp = kchp;
		this.jbr = jbr;
		this.glbm = glbm;
		this.bcyykscs = bcyykscs;
		this.jkSszpstatus = jkSszpstatus;
		this.jxmc = jxmc;
		this.sfyk = sfyk;
		this.line = line;
		this.SZjmc = SZjmc;
		this.SXmhz = SXmhz;
		this.ykrq = ykrq;
		this.yycs = yycs;
		this.ykflag = ykflag;
		this.ykdjc = ykdjc;
		this.SSafe = SSafe;
		this.SSafeZjhm = SSafeZjhm;
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

	@Column(name = "KSBH", nullable = false, length = 12)
	public String getKsbh() {
		return this.ksbh;
	}

	public void setKsbh(String ksbh) {
		this.ksbh = ksbh;
	}

	@Column(name = "KSCJ1", length = 10)
	public String getKscj1() {
		return this.kscj1;
	}

	public void setKscj1(String kscj1) {
		this.kscj1 = kscj1;
	}

	@Column(name = "KSRQ1", length = 7)
	public Timestamp getKsrq1() {
		return this.ksrq1;
	}

	public void setKsrq1(Timestamp ksrq1) {
		this.ksrq1 = ksrq1;
	}

	@Column(name = "KSSJ1", length = 7)
	public Timestamp getKssj1() {
		return this.kssj1;
	}

	public void setKssj1(Timestamp kssj1) {
		this.kssj1 = kssj1;
	}

	@Column(name = "JSSJ1", length = 7)
	public Timestamp getJssj1() {
		return this.jssj1;
	}

	public void setJssj1(Timestamp jssj1) {
		this.jssj1 = jssj1;
	}

	@Column(name = "SCF1", length = 1)
	public String getScf1() {
		return this.scf1;
	}

	public void setScf1(String scf1) {
		this.scf1 = scf1;
	}

	@Column(name = "KSCJ2", length = 10)
	public String getKscj2() {
		return this.kscj2;
	}

	public void setKscj2(String kscj2) {
		this.kscj2 = kscj2;
	}

	@Column(name = "KSRQ2", length = 7)
	public Timestamp getKsrq2() {
		return this.ksrq2;
	}

	public void setKsrq2(Timestamp ksrq2) {
		this.ksrq2 = ksrq2;
	}

	@Column(name = "KSSJ2", length = 7)
	public Timestamp getKssj2() {
		return this.kssj2;
	}

	public void setKssj2(Timestamp kssj2) {
		this.kssj2 = kssj2;
	}

	@Column(name = "JSSJ2", length = 7)
	public Timestamp getJssj2() {
		return this.jssj2;
	}

	public void setJssj2(Timestamp jssj2) {
		this.jssj2 = jssj2;
	}

	@Column(name = "SCF2", length = 1)
	public String getScf2() {
		return this.scf2;
	}

	public void setScf2(String scf2) {
		this.scf2 = scf2;
	}

	@Column(name = "KSCS", precision = 3, scale = 0)
	public Short getKscs() {
		return this.kscs;
	}

	public void setKscs(Short kscs) {
		this.kscs = kscs;
	}

	@Column(name = "KFXX1", length = 50)
	public String getKfxx1() {
		return this.kfxx1;
	}

	public void setKfxx1(String kfxx1) {
		this.kfxx1 = kfxx1;
	}

	@Column(name = "KCBH", length = 3)
	public String getKcbh() {
		return this.kcbh;
	}

	public void setKcbh(String kcbh) {
		this.kcbh = kcbh;
	}

	@Column(name = "KSCC", length = 6)
	public String getKscc() {
		return this.kscc;
	}

	public void setKscc(String kscc) {
		this.kscc = kscc;
	}

	@Column(name = "CZY", length = 20)
	public String getCzy() {
		return this.czy;
	}

	public void setCzy(String czy) {
		this.czy = czy;
	}

	@Column(name = "KSJG", length = 1)
	public String getKsjg() {
		return this.ksjg;
	}

	public void setKsjg(String ksjg) {
		this.ksjg = ksjg;
	}

	@Column(name = "SJJYW", length = 100)
	public String getSjjyw() {
		return this.sjjyw;
	}

	public void setSjjyw(String sjjyw) {
		this.sjjyw = sjjyw;
	}

	@Column(name = "KSZT", length = 1)
	public String getKszt() {
		return this.kszt;
	}

	public void setKszt(String kszt) {
		this.kszt = kszt;
	}

	@Column(name = "SFWP", length = 1)
	public String getSfwp() {
		return this.sfwp;
	}

	public void setSfwp(String sfwp) {
		this.sfwp = sfwp;
	}

	@Column(name = "SFSP", length = 1)
	public String getSfsp() {
		return this.sfsp;
	}

	public void setSfsp(String sfsp) {
		this.sfsp = sfsp;
	}

	@Column(name = "ZT", length = 1)
	public String getZt() {
		return this.zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	@Column(name = "KSXM", length = 200)
	public String getKsxm() {
		return this.ksxm;
	}

	public void setKsxm(String ksxm) {
		this.ksxm = ksxm;
	}

	@Column(name = "SQBS", length = 1)
	public String getSqbs() {
		return this.sqbs;
	}

	public void setSqbs(String sqbs) {
		this.sqbs = sqbs;
	}

	@Column(name = "SQRQ", length = 7)
	public Timestamp getSqrq() {
		return this.sqrq;
	}

	public void setSqrq(Timestamp sqrq) {
		this.sqrq = sqrq;
	}

	@Column(name = "SQYY", length = 100)
	public String getSqyy() {
		return this.sqyy;
	}

	public void setSqyy(String sqyy) {
		this.sqyy = sqyy;
	}

	@Column(name = "SQR", length = 32)
	public String getSqr() {
		return this.sqr;
	}

	public void setSqr(String sqr) {
		this.sqr = sqr;
	}

	@Column(name = "SPR", length = 32)
	public String getSpr() {
		return this.spr;
	}

	public void setSpr(String spr) {
		this.spr = spr;
	}

	@Column(name = "SPRQ", length = 7)
	public Timestamp getSprq() {
		return this.sprq;
	}

	public void setSprq(Timestamp sprq) {
		this.sprq = sprq;
	}

	@Column(name = "SQR1", length = 32)
	public String getSqr1() {
		return this.sqr1;
	}

	public void setSqr1(String sqr1) {
		this.sqr1 = sqr1;
	}

	@Column(name = "SFQK", length = 1)
	public String getSfqk() {
		return this.sfqk;
	}

	public void setSfqk(String sfqk) {
		this.sfqk = sfqk;
	}

	@Column(name = "KFXX2", length = 50)
	public String getKfxx2() {
		return this.kfxx2;
	}

	public void setKfxx2(String kfxx2) {
		this.kfxx2 = kfxx2;
	}

	@Column(name = "XM", length = 200)
	public String getXm() {
		return this.xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	@Column(name = "ZJZP", length = 100)
	public String getZjzp() {
		return this.zjzp;
	}

	public void setZjzp(String zjzp) {
		this.zjzp = zjzp;
	}

	@Column(name = "JBZP", length = 100)
	public String getJbzp() {
		return this.jbzp;
	}

	public void setJbzp(String jbzp) {
		this.jbzp = jbzp;
	}

	@Column(name = "JGFS1", precision = 3, scale = 0)
	public Short getJgfs1() {
		return this.jgfs1;
	}

	public void setJgfs1(Short jgfs1) {
		this.jgfs1 = jgfs1;
	}

	@Column(name = "JGFS2", precision = 3, scale = 0)
	public Short getJgfs2() {
		return this.jgfs2;
	}

	public void setJgfs2(Short jgfs2) {
		this.jgfs2 = jgfs2;
	}

	@Column(name = "DYCS", precision = 2, scale = 0)
	public Byte getDycs() {
		return this.dycs;
	}

	public void setDycs(Byte dycs) {
		this.dycs = dycs;
	}

	@Column(name = "SFPRINT", length = 1)
	public String getSfprint() {
		return this.sfprint;
	}

	public void setSfprint(String sfprint) {
		this.sfprint = sfprint;
	}

	@Column(name = "FLAG", length = 1)
	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Column(name = "KSY1", length = 32)
	public String getKsy1() {
		return this.ksy1;
	}

	public void setKsy1(String ksy1) {
		this.ksy1 = ksy1;
	}

	@Column(name = "KSY2", length = 32)
	public String getKsy2() {
		return this.ksy2;
	}

	public void setKsy2(String ksy2) {
		this.ksy2 = ksy2;
	}

	@Column(name = "KCXH", length = 32)
	public String getKcxh() {
		return this.kcxh;
	}

	public void setKcxh(String kcxh) {
		this.kcxh = kcxh;
	}

	@Column(name = "KSRQ", length = 7)
	public Timestamp getKsrq() {
		return this.ksrq;
	}

	public void setKsrq(Timestamp ksrq) {
		this.ksrq = ksrq;
	}

	@Column(name = "KFSS1", length = 100)
	public String getKfss1() {
		return this.kfss1;
	}

	public void setKfss1(String kfss1) {
		this.kfss1 = kfss1;
	}

	@Column(name = "KFSS2", length = 100)
	public String getKfss2() {
		return this.kfss2;
	}

	public void setKfss2(String kfss2) {
		this.kfss2 = kfss2;
	}

	@Column(name = "SSZP", length = 100)
	public String getSszp() {
		return this.sszp;
	}

	public void setSszp(String sszp) {
		this.sszp = sszp;
	}

	@Column(name = "ZKZH", length = 20)
	public String getZkzh() {
		return this.zkzh;
	}

	public void setZkzh(String zkzh) {
		this.zkzh = zkzh;
	}

	@Column(name = "KSCX", length = 20)
	public String getKscx() {
		return this.kscx;
	}

	public void setKscx(String kscx) {
		this.kscx = kscx;
	}

	@Column(name = "ZJHM", length = 18)
	public String getZjhm() {
		return this.zjhm;
	}

	public void setZjhm(String zjhm) {
		this.zjhm = zjhm;
	}

	@Column(name = "KSYY", length = 2)
	public String getKsyy() {
		return this.ksyy;
	}

	public void setKsyy(String ksyy) {
		this.ksyy = ksyy;
	}

	@Column(name = "JXDM", length = 30)
	public String getJxdm() {
		return this.jxdm;
	}

	public void setJxdm(String jxdm) {
		this.jxdm = jxdm;
	}

	@Column(name = "LSH", length = 20)
	public String getLsh() {
		return this.lsh;
	}

	public void setLsh(String lsh) {
		this.lsh = lsh;
	}

	@Column(name = "KCHP", length = 20)
	public String getKchp() {
		return this.kchp;
	}

	public void setKchp(String kchp) {
		this.kchp = kchp;
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

	@Column(name = "BCYYKSCS", length = 20)
	public String getBcyykscs() {
		return this.bcyykscs;
	}

	public void setBcyykscs(String bcyykscs) {
		this.bcyykscs = bcyykscs;
	}

	@Column(name = "JK_SSZPSTATUS", length = 2)
	public String getJkSszpstatus() {
		return this.jkSszpstatus;
	}

	public void setJkSszpstatus(String jkSszpstatus) {
		this.jkSszpstatus = jkSszpstatus;
	}

	@Column(name = "JXMC", length = 200)
	public String getJxmc() {
		return this.jxmc;
	}

	public void setJxmc(String jxmc) {
		this.jxmc = jxmc;
	}

	@Column(name = "SFYK", length = 1)
	public String getSfyk() {
		return this.sfyk;
	}

	public void setSfyk(String sfyk) {
		this.sfyk = sfyk;
	}

	@Column(name = "LINE", precision = 1, scale = 0)
	public Boolean getLine() {
		return this.line;
	}

	public void setLine(Boolean line) {
		this.line = line;
	}

	@Column(name = "S_ZJMC", length = 100)
	public String getSZjmc() {
		return this.SZjmc;
	}

	public void setSZjmc(String SZjmc) {
		this.SZjmc = SZjmc;
	}

	@Column(name = "S_XMHZ", length = 50)
	public String getSXmhz() {
		return this.SXmhz;
	}

	public void setSXmhz(String SXmhz) {
		this.SXmhz = SXmhz;
	}

	@Column(name = "YKRQ", length = 100)
	public String getYkrq() {
		return this.ykrq;
	}

	public void setYkrq(String ykrq) {
		this.ykrq = ykrq;
	}

	@Column(name = "YYCS", precision = 2, scale = 0)
	public Byte getYycs() {
		return this.yycs;
	}

	public void setYycs(Byte yycs) {
		this.yycs = yycs;
	}

	@Column(name = "YKFLAG", precision = 1, scale = 0)
	public Boolean getYkflag() {
		return this.ykflag;
	}

	public void setYkflag(Boolean ykflag) {
		this.ykflag = ykflag;
	}

	@Column(name = "YKDJC", precision = 1, scale = 0)
	public Boolean getYkdjc() {
		return this.ykdjc;
	}

	public void setYkdjc(Boolean ykdjc) {
		this.ykdjc = ykdjc;
	}

	@Column(name = "S_SAFE", length = 20)
	public String getSSafe() {
		return this.SSafe;
	}

	public void setSSafe(String SSafe) {
		this.SSafe = SSafe;
	}

	@Column(name = "S_SAFE_ZJHM", length = 20)
	public String getSSafeZjhm() {
		return this.SSafeZjhm;
	}

	public void setSSafeZjhm(String SSafeZjhm) {
		this.SSafeZjhm = SSafeZjhm;
	}

}