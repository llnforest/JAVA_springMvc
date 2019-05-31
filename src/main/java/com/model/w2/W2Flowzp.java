package com.model.w2;
// default package

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.ws.rs.ext.ParamConverter.Lazy;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Repository;

/**
 * W2FlowzpId entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "w2_flowzp")
@Repository
@Lazy
public class W2Flowzp extends com.model.BaseModel implements
		java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Integer flowid;
	private byte[] img;
	private String imgpath;
	private Timestamp imgtime;
	private Timestamp addtime;
	private String xyh;
	private String zjhm;
	private String ksxm;
	private String ykrq;
	private String ksbh;

	// Constructors

	/** default constructor */
	public W2Flowzp() {
	}

	/** minimal constructor */
	public W2Flowzp(Integer flowid, byte[] img, String imgpath,
			Timestamp imgtime) {
		this.flowid = flowid;
		this.img = img;
		this.imgpath = imgpath;
		this.imgtime = imgtime;
	}

	/** full constructor */
	public W2Flowzp(Integer flowid, byte[] img, String imgpath,
			Timestamp imgtime, Timestamp addtime, String xyh, String zjhm,
			String ksxm, String ykrq, String ksbh) {
		this.flowid = flowid;
		this.img = img;
		this.imgpath = imgpath;
		this.imgtime = imgtime;
		this.addtime = addtime;
		this.xyh = xyh;
		this.zjhm = zjhm;
		this.ksxm = ksxm;
		this.ykrq = ykrq;
		this.ksbh = ksbh;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "FLOWID", nullable = false, precision = 8, scale = 0)
	public Integer getFlowid() {
		return this.flowid;
	}

	public void setFlowid(Integer flowid) {
		this.flowid = flowid;
	}

	@Column(name = "IMG", nullable = false)
	public byte[] getImg() {
		return this.img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}
	
	@Column(name = "IMGPATH", nullable = false, length = 200)
	public String getImgpath() {
		return this.imgpath;
	}

	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}

	@Column(name = "IMGTIME", nullable = false, length = 7)
	public Timestamp getImgtime() {
		return this.imgtime;
	}

	public void setImgtime(Timestamp imgtime) {
		this.imgtime = imgtime;
	}

	@Column(name = "ADDTIME", length = 7)
	public Timestamp getAddtime() {
		return this.addtime;
	}

	public void setAddtime(Timestamp addtime) {
		this.addtime = addtime;
	}

	@Column(name = "XYH", length = 6)
	public String getXyh() {
		return this.xyh;
	}

	public void setXyh(String xyh) {
		this.xyh = xyh;
	}

	@Column(name = "ZJHM", length = 18)
	public String getZjhm() {
		return this.zjhm;
	}

	public void setZjhm(String zjhm) {
		this.zjhm = zjhm;
	}

	@Column(name = "KSXM", length = 10)
	public String getKsxm() {
		return this.ksxm;
	}

	public void setKsxm(String ksxm) {
		this.ksxm = ksxm;
	}

	@Column(name = "YKRQ", length = 100)
	public String getYkrq() {
		return this.ykrq;
	}

	public void setYkrq(String ykrq) {
		this.ykrq = ykrq;
	}

	@Column(name = "KSBH", length = 12)
	public String getKsbh() {
		return this.ksbh;
	}

	public void setKsbh(String ksbh) {
		this.ksbh = ksbh;
	}
}