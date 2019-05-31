package com.model.w2;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Repository;

/**
 * W2Ksxmkfdm entity provides the base persistence definition of the
 * W2Ksxmkfdm entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "w2_ksxmkfdm")
@Repository
public class W2Ksxmkfdm extends com.model.BaseModel implements
		java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer ksxmdm;
	private String gakfdm;
	private String gakfmc;
	private String kfmc;
	private Integer kf;
	private String ksxm;
	private String kskm;
	private String SBeizhu2;
	private String ksxmxh;

	// Constructors

	/** default constructor */
	public W2Ksxmkfdm() {
	}

	/** full constructor */
	public W2Ksxmkfdm(Integer ksxmdm, String gakfdm, String gakfmc,
			String kfmc, Integer kf, String ksxm, String kskm, String SBeizhu2,
			String ksxmxh) {
		this.ksxmdm = ksxmdm;
		this.gakfdm = gakfdm;
		this.gakfmc = gakfmc;
		this.kfmc = kfmc;
		this.kf = kf;
		this.ksxm = ksxm;
		this.kskm = kskm;
		this.SBeizhu2 = SBeizhu2;
		this.ksxmxh = ksxmxh;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, precision = 6, scale = 0)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "KSXMDM", precision = 6, scale = 0)
	public Integer getKsxmdm() {
		return this.ksxmdm;
	}

	public void setKsxmdm(Integer ksxmdm) {
		this.ksxmdm = ksxmdm;
	}

	@Column(name = "GAKFDM", length = 5)
	public String getGakfdm() {
		return this.gakfdm;
	}

	public void setGakfdm(String gakfdm) {
		this.gakfdm = gakfdm;
	}

	@Column(name = "GAKFMC", length = 200)
	public String getGakfmc() {
		return this.gakfmc;
	}

	public void setGakfmc(String gakfmc) {
		this.gakfmc = gakfmc;
	}

	@Column(name = "KFMC", length = 200)
	public String getKfmc() {
		return this.kfmc;
	}

	public void setKfmc(String kfmc) {
		this.kfmc = kfmc;
	}

	@Column(name = "KF", precision = 6, scale = 0)
	public Integer getKf() {
		return this.kf;
	}

	public void setKf(Integer kf) {
		this.kf = kf;
	}

	@Column(name = "KSXM", length = 3)
	public String getKsxm() {
		return this.ksxm;
	}

	public void setKsxm(String ksxm) {
		this.ksxm = ksxm;
	}

	@Column(name = "KSKM", length = 1)
	public String getKskm() {
		return this.kskm;
	}

	public void setKskm(String kskm) {
		this.kskm = kskm;
	}

	@Column(name = "S_BEIZHU2", length = 100)
	public String getSBeizhu2() {
		return this.SBeizhu2;
	}

	public void setSBeizhu2(String SBeizhu2) {
		this.SBeizhu2 = SBeizhu2;
	}

	@Column(name = "KSXMXH", length = 100)
	public String getKsxmxh() {
		return this.ksxmxh;
	}

	public void setKsxmxh(String ksxmxh) {
		this.ksxmxh = ksxmxh;
	}

}