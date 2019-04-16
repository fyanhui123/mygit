package com.tapi.tcs.vc.schema.model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the VC_NORMAL_VERIFICATION database table.
 * 
 */
@Entity
@Table(name="VC_NORMAL_VERIFICATION")
@SequenceGenerator(name = "SEQ_VC_NORMAL_VERIFICATION", sequenceName = "SEQ_VC_NORMAL_VERIFICATION", allocationSize = 1)
public class VcNormalVerification implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8174953162629219388L;
	private Long idVcNormalVerification;
	private String businessNo;
	private String docNum;
	private String docStatus;
	private String docVerCode;
	private String pressBatchNo;
	private String verifiedOprCode;
	private String verifiedOrgCode;
	private String verifiedReason;
	private Date verifiedTime;
	
	private String createdBy;
	private Date dateCreated;
	
	private String updatedBy;
	private Date dateUpdated;
	private String relBusinessNo;
	private String countctedInvoiceCode;
	private String counterActedInvoiceNO;

	/**核销明细*/
	private List<VcNormalVerifiedDet> vcNormalVerifiedDet = new ArrayList<VcNormalVerifiedDet>();
	
	public VcNormalVerification() {
	}


	@Id
	@Column(name="ID_VC_NORMAL_VERIFICATION")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VC_NORMAL_VERIFICATION")
	public Long getIdVcNormalVerification() {
		return this.idVcNormalVerification;
	}

	public void setIdVcNormalVerification(Long idVcNormalVerification) {
		this.idVcNormalVerification = idVcNormalVerification;
	}


	@Column(name="BUSINESS_NO")
	public String getBusinessNo() {
		return this.businessNo;
	}

	public void setBusinessNo(String businessNo) {
		this.businessNo = businessNo;
	}


	@Column(name="DOC_NUM")
	public String getDocNum() {
		return this.docNum;
	}

	public void setDocNum(String docNum) {
		this.docNum = docNum;
	}


	@Column(name="DOC_STATUS")
	public String getDocStatus() {
		return this.docStatus;
	}

	public void setDocStatus(String docStatus) {
		this.docStatus = docStatus;
	}


	@Column(name="DOC_VER_CODE")
	public String getDocVerCode() {
		return this.docVerCode;
	}

	public void setDocVerCode(String docVerCode) {
		this.docVerCode = docVerCode;
	}


	@Column(name="PRESS_BATCH_NO")
	public String getPressBatchNo() {
		return this.pressBatchNo;
	}

	public void setPressBatchNo(String pressBatchNo) {
		this.pressBatchNo = pressBatchNo;
	}


	@Column(name="VERIFIED_OPR_CODE")
	public String getVerifiedOprCode() {
		return this.verifiedOprCode;
	}

	public void setVerifiedOprCode(String verifiedOprCode) {
		this.verifiedOprCode = verifiedOprCode;
	}


	@Column(name="VERIFIED_ORG_CODE")
	public String getVerifiedOrgCode() {
		return this.verifiedOrgCode;
	}

	public void setVerifiedOrgCode(String verifiedOrgCode) {
		this.verifiedOrgCode = verifiedOrgCode;
	}


	@Column(name="VERIFIED_REASON")
	public String getVerifiedReason() {
		return this.verifiedReason;
	}

	public void setVerifiedReason(String verifiedReason) {
		this.verifiedReason = verifiedReason;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="VERIFIED_TIME")
	public Date getVerifiedTime() {
		return this.verifiedTime;
	}

	public void setVerifiedTime(Date verifiedTime) {
		this.verifiedTime = verifiedTime;
	}

	@Column(name="CREATED_BY")
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
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
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_UPDATED")
	public Date getDateUpdated() {
		return dateUpdated;
	}
	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "vcNormalVerification")
	public List<VcNormalVerifiedDet> getVcNormalVerifiedDet() {
		return vcNormalVerifiedDet;
	}

	public void setVcNormalVerifiedDet(List<VcNormalVerifiedDet> vcNormalVerifiedDet) {
		this.vcNormalVerifiedDet = vcNormalVerifiedDet;
	}
	@Column(name="REL_BUSINESS_NO")
	public String getRelBusinessNo() {
		return relBusinessNo;
	}
	public void setRelBusinessNo(String relBusinessNo) {
		this.relBusinessNo = relBusinessNo;
	}
	@Column(name="COUNTERACTEDINVOICECODE")
	public String getCountctedInvoiceCode() {
		return countctedInvoiceCode;
	}

	public void setCountctedInvoiceCode(String countctedInvoiceCode) {
		this.countctedInvoiceCode = countctedInvoiceCode;
	}
	@Column(name="COUNTERACTEDINVOICENO")
	public String getCounterActedInvoiceNO() {
		return counterActedInvoiceNO;
	}

	public void setCounterActedInvoiceNO(String counterActedInvoiceNO) {
		this.counterActedInvoiceNO = counterActedInvoiceNO;
	}
	
}