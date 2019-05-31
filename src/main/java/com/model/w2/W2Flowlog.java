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

@Entity
@Table(name = "w2_flowlog")
@Repository
public class W2Flowlog extends com.model.BaseModel implements
		java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String ksbh;
	private String ksxm;
	private Byte kszt;
	private Timestamp kssj;
	private Short kskf;
	private String kfdm;
	private String sbbh;
	private Integer kscs;
	private String scbj;
	private String msg;
	private String kcbh;
	private String zp;
	private String zpbs;
	private String zjhm;
	private String xyh;
	private String gadm;
	private String icode;
	private String imessage;
	private String lsh;
	private String sfyk;
	private String xtlb;
	private String jkid;
	private String upxml;
	private String upstatus;
	private String upret;
	private String upjpgxml;
	private String jkjpgid;
	private Boolean zpga;
	private String kskm;
	private String kchp;
	private Short kscj;
	private String xm;
	private String ksy1;
	private String ksy2;
	private String ksysfzhm1;
	private String ksysfzhm2;
	private Timestamp addtime;
	private String jkUpstatus;
	private String jkJpgupstatus;
	private String judgeid;
	private String xmmc;
	private Timestamp curtime;
	private Boolean IYekao;
	private Timestamp uptime;
	private Boolean bigzt;
	private String ykrq;

	// Constructors

	/** default constructor */
	public W2Flowlog() {
	}

	/** full constructor */
	public W2Flowlog(String ksbh, String ksxm, Byte kszt,
			Timestamp kssj, Short kskf, String kfdm, String sbbh, Integer kscs,
			String scbj, String msg, String kcbh, String zp, String zpbs,
			String zjhm, String xyh, String gadm, String icode,
			String imessage, String lsh, String sfyk, String xtlb, String jkid,
			String upxml, String upstatus, String upret, String upjpgxml,
			String jkjpgid, Boolean zpga, String kskm, String kchp, Short kscj,
			String xm, String ksy1, String ksy2, String ksysfzhm1,
			String ksysfzhm2, Timestamp addtime, String jkUpstatus,
			String jkJpgupstatus, String judgeid, String xmmc,
			Timestamp curtime, Boolean IYekao, Timestamp uptime, Boolean bigzt,
			String ykrq) {
		this.ksbh = ksbh;
		this.ksxm = ksxm;
		this.kszt = kszt;
		this.kssj = kssj;
		this.kskf = kskf;
		this.kfdm = kfdm;
		this.sbbh = sbbh;
		this.kscs = kscs;
		this.scbj = scbj;
		this.msg = msg;
		this.kcbh = kcbh;
		this.zp = zp;
		this.zpbs = zpbs;
		this.zjhm = zjhm;
		this.xyh = xyh;
		this.gadm = gadm;
		this.icode = icode;
		this.imessage = imessage;
		this.lsh = lsh;
		this.sfyk = sfyk;
		this.xtlb = xtlb;
		this.jkid = jkid;
		this.upxml = upxml;
		this.upstatus = upstatus;
		this.upret = upret;
		this.upjpgxml = upjpgxml;
		this.jkjpgid = jkjpgid;
		this.zpga = zpga;
		this.kskm = kskm;
		this.kchp = kchp;
		this.kscj = kscj;
		this.xm = xm;
		this.ksy1 = ksy1;
		this.ksy2 = ksy2;
		this.ksysfzhm1 = ksysfzhm1;
		this.ksysfzhm2 = ksysfzhm2;
		this.addtime = addtime;
		this.jkUpstatus = jkUpstatus;
		this.jkJpgupstatus = jkJpgupstatus;
		this.judgeid = judgeid;
		this.xmmc = xmmc;
		this.curtime = curtime;
		this.IYekao = IYekao;
		this.uptime = uptime;
		this.bigzt = bigzt;
		this.ykrq = ykrq;
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

	@Column(name = "KSXM", length = 10)
	public String getKsxm() {
		return this.ksxm;
	}

	public void setKsxm(String ksxm) {
		this.ksxm = ksxm;
	}

	@Column(name = "KSZT", precision = 2, scale = 0)
	public Byte getKszt() {
		return this.kszt;
	}

	public void setKszt(Byte kszt) {
		this.kszt = kszt;
	}

	@Column(name = "KSSJ", length = 7)
	public Timestamp getKssj() {
		return this.kssj;
	}

	public void setKssj(Timestamp kssj) {
		this.kssj = kssj;
	}

	@Column(name = "KSKF", precision = 3, scale = 0)
	public Short getKskf() {
		return this.kskf;
	}

	public void setKskf(Short kskf) {
		this.kskf = kskf;
	}

	@Column(name = "KFDM", length = 10)
	public String getKfdm() {
		return this.kfdm;
	}

	public void setKfdm(String kfdm) {
		this.kfdm = kfdm;
	}

	@Column(name = "SBBH", length = 10)
	public String getSbbh() {
		return this.sbbh;
	}

	public void setSbbh(String sbbh) {
		this.sbbh = sbbh;
	}

	@Column(name = "KSCS", precision = 1, scale = 0)
	public Integer getKscs() {
		return this.kscs;
	}

	public void setKscs(Integer kscs) {
		this.kscs = kscs;
	}

	@Column(name = "SCBJ", length = 1)
	public String getScbj() {
		return this.scbj;
	}

	public void setScbj(String scbj) {
		this.scbj = scbj;
	}

	@Column(name = "MSG", length = 200)
	public String getMsg() {
		return this.msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Column(name = "KCBH", length = 4)
	public String getKcbh() {
		return this.kcbh;
	}

	public void setKcbh(String kcbh) {
		this.kcbh = kcbh;
	}

	@Column(name = "ZP", length = 100)
	public String getZp() {
		return this.zp;
	}

	public void setZp(String zp) {
		this.zp = zp;
	}

	@Column(name = "ZPBS", length = 1)
	public String getZpbs() {
		return this.zpbs;
	}

	public void setZpbs(String zpbs) {
		this.zpbs = zpbs;
	}

	@Column(name = "ZJHM", length = 18)
	public String getZjhm() {
		return this.zjhm;
	}

	public void setZjhm(String zjhm) {
		this.zjhm = zjhm;
	}

	@Column(name = "XYH", length = 6)
	public String getXyh() {
		return this.xyh;
	}

	public void setXyh(String xyh) {
		this.xyh = xyh;
	}

	@Column(name = "GADM", length = 5)
	public String getGadm() {
		return this.gadm;
	}

	public void setGadm(String gadm) {
		this.gadm = gadm;
	}

	@Column(name = "ICODE", length = 20)
	public String getIcode() {
		return this.icode;
	}

	public void setIcode(String icode) {
		this.icode = icode;
	}

	@Column(name = "IMESSAGE", length = 1500)
	public String getImessage() {
		return this.imessage;
	}

	public void setImessage(String imessage) {
		this.imessage = imessage;
	}

	@Column(name = "LSH", length = 20)
	public String getLsh() {
		return this.lsh;
	}

	public void setLsh(String lsh) {
		this.lsh = lsh;
	}

	@Column(name = "SFYK", length = 1)
	public String getSfyk() {
		return this.sfyk;
	}

	public void setSfyk(String sfyk) {
		this.sfyk = sfyk;
	}

	@Column(name = "XTLB", length = 100)
	public String getXtlb() {
		return this.xtlb;
	}

	public void setXtlb(String xtlb) {
		this.xtlb = xtlb;
	}

	@Column(name = "JKID", length = 100)
	public String getJkid() {
		return this.jkid;
	}

	public void setJkid(String jkid) {
		this.jkid = jkid;
	}

	@Column(name = "UPXML", length = 4000)
	public String getUpxml() {
		return this.upxml;
	}

	public void setUpxml(String upxml) {
		this.upxml = upxml;
	}

	@Column(name = "UPSTATUS", length = 200)
	public String getUpstatus() {
		return this.upstatus;
	}

	public void setUpstatus(String upstatus) {
		this.upstatus = upstatus;
	}

	@Column(name = "UPRET", length = 2000)
	public String getUpret() {
		return this.upret;
	}

	public void setUpret(String upret) {
		this.upret = upret;
	}

	@Column(name = "UPJPGXML", length = 4000)
	public String getUpjpgxml() {
		return this.upjpgxml;
	}

	public void setUpjpgxml(String upjpgxml) {
		this.upjpgxml = upjpgxml;
	}

	@Column(name = "JKJPGID", length = 100)
	public String getJkjpgid() {
		return this.jkjpgid;
	}

	public void setJkjpgid(String jkjpgid) {
		this.jkjpgid = jkjpgid;
	}

	@Column(name = "ZPGA", precision = 1, scale = 0)
	public Boolean getZpga() {
		return this.zpga;
	}

	public void setZpga(Boolean zpga) {
		this.zpga = zpga;
	}

	@Column(name = "KSKM", length = 10)
	public String getKskm() {
		return this.kskm;
	}

	public void setKskm(String kskm) {
		this.kskm = kskm;
	}

	@Column(name = "KCHP", length = 20)
	public String getKchp() {
		return this.kchp;
	}

	public void setKchp(String kchp) {
		this.kchp = kchp;
	}

	@Column(name = "KSCJ", precision = 3, scale = 0)
	public Short getKscj() {
		return this.kscj;
	}

	public void setKscj(Short kscj) {
		this.kscj = kscj;
	}

	@Column(name = "XM", length = 200)
	public String getXm() {
		return this.xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	@Column(name = "KSY1", length = 200)
	public String getKsy1() {
		return this.ksy1;
	}

	public void setKsy1(String ksy1) {
		this.ksy1 = ksy1;
	}

	@Column(name = "KSY2", length = 200)
	public String getKsy2() {
		return this.ksy2;
	}

	public void setKsy2(String ksy2) {
		this.ksy2 = ksy2;
	}

	@Column(name = "KSYSFZHM1", length = 200)
	public String getKsysfzhm1() {
		return this.ksysfzhm1;
	}

	public void setKsysfzhm1(String ksysfzhm1) {
		this.ksysfzhm1 = ksysfzhm1;
	}

	@Column(name = "KSYSFZHM2", length = 200)
	public String getKsysfzhm2() {
		return this.ksysfzhm2;
	}

	public void setKsysfzhm2(String ksysfzhm2) {
		this.ksysfzhm2 = ksysfzhm2;
	}

	@Column(name = "ADDTIME", length = 7)
	public Timestamp getAddtime() {
		return this.addtime;
	}

	public void setAddtime(Timestamp addtime) {
		this.addtime = addtime;
	}

	@Column(name = "JK_UPSTATUS", length = 2)
	public String getJkUpstatus() {
		return this.jkUpstatus;
	}

	public void setJkUpstatus(String jkUpstatus) {
		this.jkUpstatus = jkUpstatus;
	}

	@Column(name = "JK_JPGUPSTATUS", length = 2)
	public String getJkJpgupstatus() {
		return this.jkJpgupstatus;
	}

	public void setJkJpgupstatus(String jkJpgupstatus) {
		this.jkJpgupstatus = jkJpgupstatus;
	}

	@Column(name = "JUDGEID", length = 32)
	public String getJudgeid() {
		return this.judgeid;
	}

	public void setJudgeid(String judgeid) {
		this.judgeid = judgeid;
	}

	@Column(name = "XMMC", length = 100)
	public String getXmmc() {
		return this.xmmc;
	}

	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}

	@Column(name = "CURTIME", length = 7)
	public Timestamp getCurtime() {
		return this.curtime;
	}

	public void setCurtime(Timestamp curtime) {
		this.curtime = curtime;
	}

	@Column(name = "I_YEKAO", precision = 1, scale = 0)
	public Boolean getIYekao() {
		return this.IYekao;
	}

	public void setIYekao(Boolean IYekao) {
		this.IYekao = IYekao;
	}

	@Column(name = "UPTIME", length = 7)
	public Timestamp getUptime() {
		return this.uptime;
	}

	public void setUptime(Timestamp uptime) {
		this.uptime = uptime;
	}

	@Column(name = "BIGZT", precision = 1, scale = 0)
	public Boolean getBigzt() {
		return this.bigzt;
	}

	public void setBigzt(Boolean bigzt) {
		this.bigzt = bigzt;
	}

	@Column(name = "YKRQ", length = 100)
	public String getYkrq() {
		return this.ykrq;
	}

	public void setYkrq(String ykrq) {
		this.ykrq = ykrq;
	}

}