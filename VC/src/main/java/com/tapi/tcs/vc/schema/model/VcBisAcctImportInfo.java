package com.tapi.tcs.vc.schema.model;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 外围出单导入信息表
 * <p>
 * Date 2015-02-04
 * </p>
 * <p>
 * Module：银保对账
 * </p>
 * <p>
 * Description：
 * </p>
 * <p>
 * Remark：实体类，不允许修改
 * </p>
 * @author zhxiao3
 * @version 1.0
 */
@Entity
@Table(name = "VC_BIS_ACCT_IMPORT_INFO")
@SequenceGenerator(name = "SEQ_VC_BIS_ACCT_IMPORT_INFO", sequenceName = "SEQ_VC_BIS_ACCT_IMPORT_INFO", allocationSize = 1)

public class VcBisAcctImportInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**外围出单文件导入流水*/
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VC_BIS_ACCT_IMPORT_INFO")
	@Column(name = "ID_BIS_ACCT_IMPORT_INFO")
	private Long id;
	
	/**导入时间*/
	@Column(name="ACCT_DATE")
	private Date acctDate;
	
	/**导入文件名*/
	@Column(name="FILE_NAME")
	private String fileName;
	
	/**导入数量*/
	@Column(name="COUNT_NUM")
	private Long	countNum;
	
	/**创建人*/
	@Column(name="CREATED_BY")
	private String createdBy;
	
	/**创建时间*/
	@Column(name="DATE_CREATED")
	private Date dateCreated;
	
	/**修改人*/
	@Column(name="UPDATED_BY")
	private String updatedBy;
	
	/**修改时间*/
	@Column(name="DATE_UPDATED")
	private Date dateUpdated;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getAcctDate() {
		return acctDate;
	}

	public void setAcctDate(Date acctDate) {
		this.acctDate = acctDate;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Long getCountNum() {
		return countNum;
	}

	public void setCountNum(Long countNum) {
		this.countNum = countNum;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}
	


	
}
