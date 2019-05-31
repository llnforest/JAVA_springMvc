package com.model.w2;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Repository;

/**
 * W2Ksxmdm entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "w2_ksxmdm")
@Repository
public class W2Ksxmdm extends com.model.BaseModel implements
		java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String kskm;
	private String custxh;
	private Short kmtime1;
	private Short kmtime2;
	private Short kmtime3;

	// Constructors

	/** default constructor */
	public W2Ksxmdm() {
	}

	/** minimal constructor */
	public W2Ksxmdm(String id) {
		this.id = id;
	}

	/** full constructor */
	public W2Ksxmdm(String id, String name, String kskm, String custxh,
			Short kmtime1, Short kmtime2, Short kmtime3) {
		this.id = id;
		this.name = name;
		this.kskm = kskm;
		this.custxh = custxh;
		this.kmtime1 = kmtime1;
		this.kmtime2 = kmtime2;
		this.kmtime3 = kmtime3;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, precision = 6, scale = 0)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "NAME", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "KSKM", length = 1)
	public String getKskm() {
		return this.kskm;
	}

	public void setKskm(String kskm) {
		this.kskm = kskm;
	}

	@Column(name = "CUSTXH", length = 4)
	public String getCustxh() {
		return this.custxh;
	}

	public void setCustxh(String custxh) {
		this.custxh = custxh;
	}

	@Column(name = "KMTIME1", precision = 3, scale = 0)
	public Short getKmtime1() {
		return this.kmtime1;
	}

	public void setKmtime1(Short kmtime1) {
		this.kmtime1 = kmtime1;
	}

	@Column(name = "KMTIME2", precision = 3, scale = 0)
	public Short getKmtime2() {
		return this.kmtime2;
	}

	public void setKmtime2(Short kmtime2) {
		this.kmtime2 = kmtime2;
	}

	@Column(name = "KMTIME3", precision = 3, scale = 0)
	public Short getKmtime3() {
		return this.kmtime3;
	}

	public void setKmtime3(Short kmtime3) {
		this.kmtime3 = kmtime3;
	}

}