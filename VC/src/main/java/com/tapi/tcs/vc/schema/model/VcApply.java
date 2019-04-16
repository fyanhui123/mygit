/**
 * 
 */
package com.tapi.tcs.vc.schema.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

/**
 * @author hanmiao.diao
 *
 */
@Entity
@Table(name="VC_APPLY", uniqueConstraints = {@UniqueConstraint(columnNames={"APPLY_CODE"})})
@SequenceGenerator(name = "SEQ_VC_APPLY", sequenceName = "SEQ_VC_APPLY", allocationSize = 1)
public class VcApply implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -108054637760035084L;
	private Long id;
	private String applyCode;
	private String provideOrgCode;
	private String provideOprCode;
	private Date   provideTime;
	private String applyOprCode;
	private String applyOrgCode;
	private Date applyTime;
	private String applyReason;
	private String modifyOprCode;
	private Date modifyTime;
	private String applyStatus;
	private String confirmOprCode;
	private Date confirmTime;
	
	private String createdBy;
	private Date dateCreated;
	
	private String updatedBy;
	private Date dateUpdated;
	
	////////////////////////////////////////
	//**************自定义属性**************
	///////////////////////////////////////
	private String provideOprName;
	private String provideOrgName;
	private String applyOrgName;
	private String applyOprName;
	private String applyOprTime;
	private String applyStatusZh;
	private String backReason;
	
	///////////////////////////////////////
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VC_APPLY")
	@Column(name="ID_VC_APPLY")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	@Column(name="APPLY_CODE")
	public String getApplyCode() {
		return applyCode;
	}

	public void setApplyCode(String applyCode) {
		this.applyCode = applyCode;
	}
	
	@Column(name="PROVIDE_ORG_CODE")
	public String getProvideOrgCode() {
		return provideOrgCode;
	}
	public void setProvideOrgCode(String provideOrgCode) {
		this.provideOrgCode = provideOrgCode;
	}
	
	@Column(name="APPLY_OPR_CODE")
	public String getApplyOprCode() {
		return applyOprCode;
	}
	public void setApplyOprCode(String applyOprCode) {
		this.applyOprCode = applyOprCode;
	}
	
	@Column(name="APPLY_ORG_CODE")
	public String getApplyOrgCode() {
		return applyOrgCode;
	}
	public void setApplyOrgCode(String applyOrgCode) {
		this.applyOrgCode = applyOrgCode;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="APPLY_TIME")
	public Date getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	
	@Column(name="APPLY_REASON")
	public String getApplyReason() {
		return applyReason;
	}
	public void setApplyReason(String applyReason) {
		this.applyReason = applyReason;
	}
	
	@Column(name="MODIFY_OPR_CODE")
	public String getModifyOprCode() {
		return modifyOprCode;
	}
	public void setModifyOprCode(String modifyOprCode) {
		this.modifyOprCode = modifyOprCode;
	}
	
	@Column(name="MODIFY_TIME")
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	
	@Column(name="APPLY_STATUS")
	public String getApplyStatus() {
		return applyStatus;
	}
	public void setApplyStatus(String applyStatus) {
		this.applyStatus = applyStatus;
	}
	
	@Column(name="PROVIDE_OPR_CODE")
	public String getProvideOprCode() {
		return provideOprCode;
	}
	public void setProvideOprCode(String provideOprCode) {
		this.provideOprCode = provideOprCode;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="PROVIDE_TIME")
	public Date getProvideTime() {
		return provideTime;
	}
	public void setProvideTime(Date provideTime) {
		this.provideTime = provideTime;
	}
	
	@Column(name="CONFIRM_OPR_CODE")
	public String getConfirmOprCode() {
		return confirmOprCode;
	}
	public void setConfirmOprCode(String confirmOprCode) {
		this.confirmOprCode = confirmOprCode;
	}
	
	@Column(name="CONFIRM_TIME")
	public Date getConfirmTime() {
		return confirmTime;
	}
	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
	}

	private List<VcApplyDet> applyDetList;

	@Transient
	public List<VcApplyDet> getApplyDetList() {
		if(applyDetList == null) {
			applyDetList = new ArrayList<VcApplyDet>();
		}
		return applyDetList;
	}
	
	public void setApplyDetList(List<VcApplyDet> applyDetList) {
		this.applyDetList = applyDetList;
	}
	
	@Transient
	public String getProvideOrgName() {
		return provideOrgName;
	}
	public void setProvideOrgName(String provideOrgName) {
		this.provideOrgName = provideOrgName;
	}
	
	@Transient
	public String getApplyOrgName() {
		return applyOrgName;
	}
	public void setApplyOrgName(String applyOrgName) {
		this.applyOrgName = applyOrgName;
	}
	
	@Transient
	public String getApplyOprName() {
		return applyOprName;
	}
	public void setApplyOprName(String applyOprName) {
		this.applyOprName = applyOprName;
	}
	
	@Transient
	public String getApplyOprTime() {
		return applyOprTime;
	}
	public void setApplyOprTime(String applyOprTime) {
		this.applyOprTime = applyOprTime;
	}
	
	@Transient
	public String getApplyStatusZh() {
		return applyStatusZh;
	}
	public void setApplyStatusZh(String applyStatusZh) {
		this.applyStatusZh = applyStatusZh;
	}
	
	@Transient
	public String getBackReason() {
		return backReason;
	}
	public void setBackReason(String backReason) {
		this.backReason = backReason;
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
	@Transient
	public String getProvideOprName() {
		return provideOprName;
	}
	public void setProvideOprName(String provideOprName) {
		this.provideOprName = provideOprName;
	}
}
