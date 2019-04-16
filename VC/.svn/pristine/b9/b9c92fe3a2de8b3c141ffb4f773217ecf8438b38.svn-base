package com.tapi.tcs.vc.schema.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


/**
 * The persistent class for the VC_CANCEL database table.
 * 
 */
@Entity
@Table(name="VC_CANCEL")
@SequenceGenerator(name = "SEQ_VC_CANCEL", sequenceName = "SEQ_VC_CANCEL", allocationSize = 1)
public class VcCancel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8536427924837365497L;
	private Long idVcCancel;
	private String approveOprCode;
	private String approveOrgCode;
	private Date approveTime;
	private String cancelCode;
	private String cancelOprCode;
	private String cancelOrgCode;
	private String cancelReason;
	private String cancelStatus;
	private Date cancelTime;
	private String cancelType;
	private String modifyOprCode;
	private Date modifyTime;
	private List<VcCancelDet> vcCancelDets;
	
	private String createdBy;
	private Date dateCreated;
	
	private String updatedBy;
	private Date dateUpdated;
	
	///////////////////////////////////////
	private String cancelOrgName;
	private String cancelOprName;
	
	private String cancelStatusZh;
	private String cancelTypeZh;
	private String cancelApproveRemark;
	private String cancelApprove;
	
	@Transient
	public String getCancelApprove() {
		return cancelApprove;
	}

	public void setCancelApprove(String cancelApprove) {
		this.cancelApprove = cancelApprove;
	}


	@Transient
	public String getCancelApproveRemark() {
		return cancelApproveRemark;
	}


	public void setCancelApproveRemark(String cancelApproveRemark) {
		this.cancelApproveRemark = cancelApproveRemark;
	}


	@Transient
	public String getCancelStatusZh() {
		return cancelStatusZh;
	}

	
	public void setCancelStatusZh(String cancelStatusZh) {
		this.cancelStatusZh = cancelStatusZh;
	}

	@Transient
	public String getCancelTypeZh() {
		return cancelTypeZh;
	}


	public void setCancelTypeZh(String cancelTypeZh) {
		this.cancelTypeZh = cancelTypeZh;
	}


	@Transient
	public String getCancelOprName() {
		return cancelOprName;
	}


	public void setCancelOprName(String cancelOprName) {
		this.cancelOprName = cancelOprName;
	}


	@Transient
	public String getCancelOrgName() {
		return cancelOrgName;
	}


	public void setCancelOrgName(String cancelOrgName) {
		this.cancelOrgName = cancelOrgName;
	}


	public VcCancel() {
	}


	@Id
	@Column(name="ID_VC_CANCEL")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VC_CANCEL")
	public Long getIdVcCancel() {
		return this.idVcCancel;
	}

	public void setIdVcCancel(Long idVcCancel) {
		this.idVcCancel = idVcCancel;
	}


	@Column(name="APPROVE_OPR_CODE")
	public String getApproveOprCode() {
		return this.approveOprCode;
	}

	public void setApproveOprCode(String approveOprCode) {
		this.approveOprCode = approveOprCode;
	}


	@Column(name="APPROVE_ORG_CODE")
	public String getApproveOrgCode() {
		return this.approveOrgCode;
	}

	public void setApproveOrgCode(String approveOrgCode) {
		this.approveOrgCode = approveOrgCode;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="APPROVE_TIME")
	public Date getApproveTime() {
		return this.approveTime;
	}

	public void setApproveTime(Date approveTime) {
		this.approveTime = approveTime;
	}


	@Column(name="CANCEL_CODE")
	public String getCancelCode() {
		return this.cancelCode;
	}

	public void setCancelCode(String cancelCode) {
		this.cancelCode = cancelCode;
	}


	@Column(name="CANCEL_OPR_CODE")
	public String getCancelOprCode() {
		return this.cancelOprCode;
	}

	public void setCancelOprCode(String cancelOprCode) {
		this.cancelOprCode = cancelOprCode;
	}


	@Column(name="CANCEL_ORG_CODE")
	public String getCancelOrgCode() {
		return this.cancelOrgCode;
	}

	public void setCancelOrgCode(String cancelOrgCode) {
		this.cancelOrgCode = cancelOrgCode;
	}


	@Column(name="CANCEL_REASON")
	public String getCancelReason() {
		return this.cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}


	@Column(name="CANCEL_STATUS")
	public String getCancelStatus() {
		return this.cancelStatus;
	}

	public void setCancelStatus(String cancelStatus) {
		this.cancelStatus = cancelStatus;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CANCEL_TIME")
	public Date getCancelTime() {
		return this.cancelTime;
	}

	public void setCancelTime(Date cancelTime) {
		this.cancelTime = cancelTime;
	}


	@Column(name="CANCEL_TYPE")
	public String getCancelType() {
		return this.cancelType;
	}

	public void setCancelType(String cancelType) {
		this.cancelType = cancelType;
	}


	@Column(name="MODIFY_OPR_CODE")
	public String getModifyOprCode() {
		return this.modifyOprCode;
	}

	public void setModifyOprCode(String modifyOprCode) {
		this.modifyOprCode = modifyOprCode;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="MODIFY_TIME")
	public Date getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}


	//bi-directional many-to-one association to VcCancelDet
	@OneToMany(mappedBy="vcCancel",fetch=FetchType.LAZY)
	public List<VcCancelDet> getVcCancelDets() {
		return this.vcCancelDets;
	}

	public void setVcCancelDets(List<VcCancelDet> vcCancelDets) {
		this.vcCancelDets = vcCancelDets;
	}

	public VcCancelDet addVcCancelDet(VcCancelDet vcCancelDet) {
		getVcCancelDets().add(vcCancelDet);
		vcCancelDet.setVcCancel(this);

		return vcCancelDet;
	}

	public VcCancelDet removeVcCancelDet(VcCancelDet vcCancelDet) {
		getVcCancelDets().remove(vcCancelDet);
		vcCancelDet.setVcCancel(null);

		return vcCancelDet;
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

}