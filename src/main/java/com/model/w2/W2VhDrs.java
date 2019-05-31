package com.model.w2;
// default package

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Repository;

/**
 * W2VhDrs entity provides the base persistence definition of the
 * W2VhDrs entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "w2_vh_drs")
@Repository
public class W2VhDrs extends com.model.BaseModel implements
		java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String bh;
	private String lsh;
	private String zkzmbh;
	private String zjmc;
	private String zjhm;
	private String xm;
	private String xb;
	private Timestamp csrq;
	private Timestamp djrq;
	private Timestamp gxrq;
	private String ksyy;
	private String kscx;
	private Boolean kskm;
	private String ksxm;
	private String jxdm;
	private String kchp;
	private String ksdd;
	private Byte yycs;
	private Byte zksfhg;
	private String fzxh;
	private Timestamp ykrq;
	private Timestamp yyrq;
	private String sfyk;
	private String ksy1;
	private String ksy2;
	private String ksxms;
	private Byte bcyykscs;
	private String jbr;
	private String glbm;
	private String jxmc;

	// Constructors

	/** default constructor */
	public W2VhDrs() {
	}

	/** minimal constructor */
	public W2VhDrs(String bh, String lsh, String kscx) {
		this.bh = bh;
		this.lsh = lsh;
		this.kscx = kscx;
	}

	/** full constructor */
	public W2VhDrs(String bh, String lsh, String zkzmbh, String zjmc,
			String zjhm, String xm, String xb, Timestamp csrq, Timestamp djrq,
			Timestamp gxrq, String ksyy, String kscx, Boolean kskm,
			String ksxm, String jxdm, String kchp, String ksdd, Byte yycs,
			Byte zksfhg, String fzxh, Timestamp ykrq, Timestamp yyrq,
			String sfyk, String ksy1, String ksy2, String ksxms, Byte bcyykscs,
			String jbr, String glbm, String jxmc) {
		this.bh = bh;
		this.lsh = lsh;
		this.zkzmbh = zkzmbh;
		this.zjmc = zjmc;
		this.zjhm = zjhm;
		this.xm = xm;
		this.xb = xb;
		this.csrq = csrq;
		this.djrq = djrq;
		this.gxrq = gxrq;
		this.ksyy = ksyy;
		this.kscx = kscx;
		this.kskm = kskm;
		this.ksxm = ksxm;
		this.jxdm = jxdm;
		this.kchp = kchp;
		this.ksdd = ksdd;
		this.yycs = yycs;
		this.zksfhg = zksfhg;
		this.fzxh = fzxh;
		this.ykrq = ykrq;
		this.yyrq = yyrq;
		this.sfyk = sfyk;
		this.ksy1 = ksy1;
		this.ksy2 = ksy2;
		this.ksxms = ksxms;
		this.bcyykscs = bcyykscs;
		this.jbr = jbr;
		this.glbm = glbm;
		this.jxmc = jxmc;
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

	@Column(name = "BH", nullable = false, length = 12)
	public String getBh() {
		return this.bh;
	}

	public void setBh(String bh) {
		this.bh = bh;
	}

	@Column(name = "LSH", nullable = false, length = 20)
	public String getLsh() {
		return this.lsh;
	}

	public void setLsh(String lsh) {
		this.lsh = lsh;
	}

	@Column(name = "ZKZMBH", length = 20)
	public String getZkzmbh() {
		return this.zkzmbh;
	}

	public void setZkzmbh(String zkzmbh) {
		this.zkzmbh = zkzmbh;
	}

	@Column(name = "ZJMC", length = 20)
	public String getZjmc() {
		return this.zjmc;
	}

	public void setZjmc(String zjmc) {
		this.zjmc = zjmc;
	}

	@Column(name = "ZJHM", length = 18)
	public String getZjhm() {
		return this.zjhm;
	}

	public void setZjhm(String zjhm) {
		this.zjhm = zjhm;
	}

	@Column(name = "XM", length = 200)
	public String getXm() {
		return this.xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	@Column(name = "XB", length = 2)
	public String getXb() {
		return this.xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	@Column(name = "CSRQ", length = 7)
	public Timestamp getCsrq() {
		return this.csrq;
	}

	public void setCsrq(Timestamp csrq) {
		this.csrq = csrq;
	}

	@Column(name = "DJRQ", length = 7)
	public Timestamp getDjrq() {
		return this.djrq;
	}

	public void setDjrq(Timestamp djrq) {
		this.djrq = djrq;
	}

	@Column(name = "GXRQ", length = 7)
	public Timestamp getGxrq() {
		return this.gxrq;
	}

	public void setGxrq(Timestamp gxrq) {
		this.gxrq = gxrq;
	}

	@Column(name = "KSYY", length = 2)
	public String getKsyy() {
		return this.ksyy;
	}

	public void setKsyy(String ksyy) {
		this.ksyy = ksyy;
	}

	@Column(name = "KSCX", nullable = false, length = 2)
	public String getKscx() {
		return this.kscx;
	}

	public void setKscx(String kscx) {
		this.kscx = kscx;
	}

	@Column(name = "KSKM", precision = 1, scale = 0)
	public Boolean getKskm() {
		return this.kskm;
	}

	public void setKskm(Boolean kskm) {
		this.kskm = kskm;
	}

	@Column(name = "KSXM", length = 200)
	public String getKsxm() {
		return this.ksxm;
	}

	public void setKsxm(String ksxm) {
		this.ksxm = ksxm;
	}

	@Column(name = "JXDM", length = 30)
	public String getJxdm() {
		return this.jxdm;
	}

	public void setJxdm(String jxdm) {
		this.jxdm = jxdm;
	}

	@Column(name = "KCHP", length = 10)
	public String getKchp() {
		return this.kchp;
	}

	public void setKchp(String kchp) {
		this.kchp = kchp;
	}

	@Column(name = "KSDD", length = 10)
	public String getKsdd() {
		return this.ksdd;
	}

	public void setKsdd(String ksdd) {
		this.ksdd = ksdd;
	}

	@Column(name = "YYCS", precision = 2, scale = 0)
	public Byte getYycs() {
		return this.yycs;
	}

	public void setYycs(Byte yycs) {
		this.yycs = yycs;
	}

	@Column(name = "ZKSFHG", precision = 2, scale = 0)
	public Byte getZksfhg() {
		return this.zksfhg;
	}

	public void setZksfhg(Byte zksfhg) {
		this.zksfhg = zksfhg;
	}

	@Column(name = "FZXH", length = 30)
	public String getFzxh() {
		return this.fzxh;
	}

	public void setFzxh(String fzxh) {
		this.fzxh = fzxh;
	}

	@Column(name = "YKRQ", length = 7)
	public Timestamp getYkrq() {
		return this.ykrq;
	}

	public void setYkrq(Timestamp ykrq) {
		this.ykrq = ykrq;
	}

	@Column(name = "YYRQ", length = 7)
	public Timestamp getYyrq() {
		return this.yyrq;
	}

	public void setYyrq(Timestamp yyrq) {
		this.yyrq = yyrq;
	}

	@Column(name = "SFYK", length = 1)
	public String getSfyk() {
		return this.sfyk;
	}

	public void setSfyk(String sfyk) {
		this.sfyk = sfyk;
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

	@Column(name = "KSXMS", length = 300)
	public String getKsxms() {
		return this.ksxms;
	}

	public void setKsxms(String ksxms) {
		this.ksxms = ksxms;
	}

	@Column(name = "BCYYKSCS", precision = 2, scale = 0)
	public Byte getBcyykscs() {
		return this.bcyykscs;
	}

	public void setBcyykscs(Byte bcyykscs) {
		this.bcyykscs = bcyykscs;
	}

	@Column(name = "JBR", length = 100)
	public String getJbr() {
		return this.jbr;
	}

	public void setJbr(String jbr) {
		this.jbr = jbr;
	}

	@Column(name = "GLBM", length = 100)
	public String getGlbm() {
		return this.glbm;
	}

	public void setGlbm(String glbm) {
		this.glbm = glbm;
	}

	@Column(name = "JXMC", length = 200)
	public String getJxmc() {
		return this.jxmc;
	}

	public void setJxmc(String jxmc) {
		this.jxmc = jxmc;
	}

}