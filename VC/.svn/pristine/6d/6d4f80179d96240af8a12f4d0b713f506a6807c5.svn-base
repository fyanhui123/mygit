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
 * The persistent class for the VC_RECYCLE database table.
 * 
 */
@Entity
@Table(name="VC_RECYCLE")
@SequenceGenerator(name = "SEQ_VC_RECYCLE", sequenceName = "SEQ_VC_RECYCLE", allocationSize = 1)
public class VcRecycle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 619138546200803755L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VC_RECYCLE")
	@Column(name="ID_VC_RECYCLE")
	private Long idVcRecycle;

	@Column(name="CONFIRM_OPR_CODE")
	private String confirmOprCode;

	@Column(name="CONFIRM_ORG_CODE")
	private String confirmOrgCode;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CONFIRM_TIME")
	private Date confirmTime;

	@Column(name="MODIFY_OPR_CODE")
	private String modifyOprCode;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="MODIFY_TIME")
	private Date modifyTime;

	@Column(name="RECYCLE_CODE")
	private String recycleCode;

	@Column(name="RECYCLE_OPR_CODE")
	private String recycleOprCode;

	@Column(name="RECYCLE_ORG_CODE")
	private String recycleOrgCode;

	@Column(name="RECYCLE_REASON")
	private String recycleReason;

	@Column(name="RECYCLE_STATUS")
	private String recycleStatus;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="RECYCLE_TIME")
	private Date recycleTime;

	//bi-directional many-to-one association to VcRecycleDet
	@OneToMany(mappedBy="vcRecycle",fetch=FetchType.EAGER)
	private List<VcRecycleDet> vcRecycleDets;
	
	@Column(name="CREATED_BY")
	private String createdBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_CREATED")
	private Date dateCreated;
	
	@Column(name="UPDATED_BY")
	private String updatedBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_UPDATED")
	private Date dateUpdated;
	
	public VcRecycle() {
	}

	public Long getIdVcRecycle() {
		return this.idVcRecycle;
	}

	public void setIdVcRecycle(Long idVcRecycle) {
		this.idVcRecycle = idVcRecycle;
	}

	public String getConfirmOprCode() {
		return this.confirmOprCode;
	}

	public void setConfirmOprCode(String confirmOprCode) {
		this.confirmOprCode = confirmOprCode;
	}

	public String getConfirmOrgCode() {
		return this.confirmOrgCode;
	}

	public void setConfirmOrgCode(String confirmOrgCode) {
		this.confirmOrgCode = confirmOrgCode;
	}

	public Date getConfirmTime() {
		return this.confirmTime;
	}

	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
	}

	public String getModifyOprCode() {
		return this.modifyOprCode;
	}

	public void setModifyOprCode(String modifyOprCode) {
		this.modifyOprCode = modifyOprCode;
	}

	public Date getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getRecycleCode() {
		return this.recycleCode;
	}

	public void setRecycleCode(String recycleCode) {
		this.recycleCode = recycleCode;
	}

	public String getRecycleOprCode() {
		return this.recycleOprCode;
	}

	public void setRecycleOprCode(String recycleOprCode) {
		this.recycleOprCode = recycleOprCode;
	}

	public String getRecycleOrgCode() {
		return this.recycleOrgCode;
	}

	public void setRecycleOrgCode(String recycleOrgCode) {
		this.recycleOrgCode = recycleOrgCode;
	}

	public String getRecycleReason() {
		return this.recycleReason;
	}

	public void setRecycleReason(String recycleReason) {
		this.recycleReason = recycleReason;
	}

	public String getRecycleStatus() {
		return this.recycleStatus;
	}

	public void setRecycleStatus(String recycleStatus) {
		this.recycleStatus = recycleStatus;
	}

	public Date getRecycleTime() {
		return this.recycleTime;
	}

	public void setRecycleTime(Date recycleTime) {
		this.recycleTime = recycleTime;
	}

	public List<VcRecycleDet> getVcRecycleDets() {
		return this.vcRecycleDets;
	}

	public void setVcRecycleDets(List<VcRecycleDet> vcRecycleDets) {
		this.vcRecycleDets = vcRecycleDets;
	}

	public VcRecycleDet addVcRecycleDet(VcRecycleDet vcRecycleDet) {
		getVcRecycleDets().add(vcRecycleDet);
		vcRecycleDet.setVcRecycle(this);

		return vcRecycleDet;
	}

	public VcRecycleDet removeVcRecycleDet(VcRecycleDet vcRecycleDet) {
		getVcRecycleDets().remove(vcRecycleDet);
		vcRecycleDet.setVcRecycle(null);

		return vcRecycleDet;
	}
	
	@Transient
	private String recycleOprName;
	
	@Transient
	private String recycleOrgName;
	
	@Transient
	private String confirmOrgName;
	
	@Transient
	private String confirmOprName;
	
	@Transient
	private String disagreeReason;
	
	@Transient	
	private String recycleStatusZh;
	

	public String getRecycleStatusZh() {
		return recycleStatusZh;
	}

	public void setRecycleStatusZh(String recycleStatusZh) {
		this.recycleStatusZh = recycleStatusZh;
	}

	public String getRecycleOprName() {
		return recycleOprName;
	}

	public void setRecycleOprName(String recycleOprName) {
		this.recycleOprName = recycleOprName;
	}

	public String getRecycleOrgName() {
		return recycleOrgName;
	}

	public void setRecycleOrgName(String recycleOrgName) {
		this.recycleOrgName = recycleOrgName;
	}

	public String getConfirmOrgName() {
		return confirmOrgName;
	}

	public void setConfirmOrgName(String confirmOrgName) {
		this.confirmOrgName = confirmOrgName;
	}

	public String getConfirmOprName() {
		return confirmOprName;
	}

	public void setConfirmOprName(String confirmOprName) {
		this.confirmOprName = confirmOprName;
	}

	public String getDisagreeReason() {
		return disagreeReason;
	}

	public void setDisagreeReason(String disagreeReason) {
		this.disagreeReason = disagreeReason;
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