/**
 * 
 */
package com.tapi.tcs.vc.schema.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 合同模型
 * 
 * @author Lincoln
 * 
 */
@Entity
@Table(name = "Contract")
@SequenceGenerator(name = "SEQ_CONTRACT", sequenceName = "SEQ_CONTRACT", allocationSize = 1)
public class Contract implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5383229243622238033L;

	/** 合同标识 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CONTRACT")
	private Long id;

	/** 合同号 */
	private String contractNo;

	/** 合同名 */
	private String contractName;

	/** 合同期限 */
	private Date contractDate;

	/** 所属机构 */
	private String deptCode;

	/** 合同扫描件 */
	@Lob
	private byte[] contractCopy;

	/** 对应印刷厂信息 */
	@ManyToOne(cascade = { CascadeType.ALL }, targetEntity = Printing.class)
	@JoinColumn(name = "PRINTINGID", nullable = true)
	private Printing printing;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the contractNo
	 */
	public String getContractNo() {
		return contractNo;
	}

	/**
	 * @param contractNo
	 *            the contractNo to set
	 */
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	/**
	 * @return the contractName
	 */
	public String getContractName() {
		return contractName;
	}

	/**
	 * @param contractName
	 *            the contractName to set
	 */
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	/**
	 * @return the contractDate
	 */
	public Date getContractDate() {
		return contractDate;
	}

	/**
	 * @param contractDate
	 *            the contractDate to set
	 */
	public void setContractDate(Date contractDate) {
		this.contractDate = contractDate;
	}

	/**
	 * @return the deptCode
	 */
	public String getDeptCode() {
		return deptCode;
	}

	/**
	 * @param deptCode
	 *            the deptCode to set
	 */
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	/**
	 * @return the contractCopy
	 */
	public byte[] getContractCopy() {
		return contractCopy;
	}

	/**
	 * @param contractCopy
	 *            the contractCopy to set
	 */
	public void setContractCopy(byte[] contractCopy) {
		this.contractCopy = contractCopy;
	}

	/**
	 * @return the printing
	 */
	public Printing getPrinting() {
		return printing;
	}

	/**
	 * @param printing
	 *            the printing to set
	 */
	public void setPrinting(Printing printing) {
		this.printing = printing;
	}

}
