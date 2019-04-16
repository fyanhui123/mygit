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
 * 外围出单对账表
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
@Table(name = "VC_BIS_DOC_NUM_ACCT")
@SequenceGenerator(name = "SEQ_VC_BIS_DOC_NUM_ACCT", sequenceName = "SEQ_VC_BIS_DOC_NUM_ACCT", allocationSize = 1)

public class VcBisDocNumAcct implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**外围出单对账流水*/
	private Long id;
	/**导入时间*/
	private Date acctDate;
	/**单证类型代码*/
	private String docVerCode;
	/**流水号*/
	private String	docNum;
	/**机构代码*/
	private String orgCode;
	/**业务号*/
	private String	businessNo;
	/**单证状态*/
	private String	docStatus;
	/**是否对账成功*/
	private String	acctStatus;
	/**创建人*/
	private String createdBy;
	/**创建时间*/
	private Date dateCreated;
	/**修改人*/
	private String updatedBy;
	/**修改时间*/
	private Date dateUpdated;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VC_BIS_DOC_NUM_ACCT")
	@Column(name = "ID_BIS_DOC_NUM_ACCT")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="DOC_VER_CODE")
	public String getDocVerCode() {
		return docVerCode;
	}
	public void setDocVerCode(String docVerCode) {
		this.docVerCode = docVerCode;
	}
	
	@Column(name="ACCT_DATE")
	public Date getAcctDate() {
		return acctDate;
	}
	public void setAcctDate(Date acctDate) {
		this.acctDate = acctDate;
	}
	
	@Column(name="DOC_NUM")
	public String getDocNum() {
		return docNum;
	}
	public void setDocNum(String docNum) {
		this.docNum = docNum;
	}
	
	@Column(name="ORG_CODE")
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	
	@Column(name="BUSINESS_NO")
	public String getBusinessNo() {
		return businessNo;
	}
	public void setBusinessNo(String businessNo) {
		this.businessNo = businessNo;
	}
	
	@Column(name="DOC_STATUS")
	public String getDocStatus() {
		return docStatus;
	}
	public void setDocStatus(String docStatus) {
		this.docStatus = docStatus;
	}
	
	@Column(name="ACCT_STATUS")
	public String getAcctStatus() {
		return acctStatus;
	}
	public void setAcctStatus(String acctStatus) {
		this.acctStatus = acctStatus;
	}
	
	@Column(name="CREATED_BY")
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	@Column(name="DATE_CREATED")
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	@Column(name="UPDATED_BY")
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	@Column(name="DATE_UPDATED")
	public Date getDateUpdated() {
		return dateUpdated;
	}
	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}
	
}
