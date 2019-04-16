package com.tapi.tcs.vc.schema.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
@Entity
@Table(name="VC_ALLOT", uniqueConstraints = {@UniqueConstraint(columnNames={"allot_code"})})
@SequenceGenerator(name = "SEQ_VC_ALLOT", sequenceName = "SEQ_VC_ALLOT", allocationSize = 1)
public class VcAllot implements Serializable {

	private Long id;//单证调拨流水
	private List<VcAllotDet> allotDetList;
	private String allotCode;//调拨编号
	private String provideOrgCode;//调拨发放机构代码
	private String provideOprCode;//调拨发放操作人代码
	private Date   provideTime;//调拨时间
	private String allotOprCode;//调拨申请操作人代码
	private String allotOrgCode;//调拨申请机构代码
	private Date allotTime;  //调拨时间
	private String allotReason;//调拨原因
	private String modifyOprCode;//调拨修改人代码
	private Date modifyTime;//调拨修改时间
	private String allotStatus;//调拨状态
	private String confirmOprCode;//调拨领用确认操作人代码
	private Date confirmTime;//调拨领用确认时间
	private String createdBy;//调拨创建人
	private Date dateCreated;//调拨创建时间
	private String updatedBy;//调拨修改人
	private Date dateUpdated;//调拨修改时间
	//以上是实体
	private String allotOprName;//申请操作人名称
	private String allotOrgName;//调拨申请机构名称
	private String provideOrgName;//发放机构名称
	private String provideOprName;//
	private String allotStatusName;
	private String backReason;
	@Transient
	public String getBackReason() {
		return backReason;
	}
	public void setBackReason(String backReason) {
		this.backReason = backReason;
	}
	@Transient
	public List<VcAllotDet> getAllotDetList() {
		if(allotDetList == null) {
			allotDetList = new ArrayList<VcAllotDet>();
		}
		return allotDetList;
	}
	public void setAllotDetList(List<VcAllotDet> allotDetList) {
		this.allotDetList = allotDetList;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VC_ALLOT")
	@Column(name="ID_VC_ALLOT")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name="ALLOT_CODE")
	public String getAllotCode() {
		return allotCode;
	}
	public void setAllotCode(String allotCode) {
		this.allotCode = allotCode;
	}
	@Column(name="PROVIDE_ORG_CODE")
	public String getProvideOrgCode() {
		return provideOrgCode;
	}
	public void setProvideOrgCode(String provideOrgCode) {
		this.provideOrgCode = provideOrgCode;
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
	@Column(name="ALLOT_OPR_CODE")
	public String getAllotOprCode() {
		return allotOprCode;
	}
	public void setAllotOprCode(String allotOprCode) {
		this.allotOprCode = allotOprCode;
	}
	@Column(name="ALLOT_ORG_CODE")
	public String getAllotOrgCode() {
		return allotOrgCode;
	}
	public void setAllotOrgCode(String allotOrgCode) {
		this.allotOrgCode = allotOrgCode;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ALLOT_TIME")
	public Date getAllotTime() {
		return allotTime;
	}
	public void setAllotTime(Date allotTime) {
		this.allotTime = allotTime;
	}
	@Column(name="ALLOT_REASON")
	public String getAllotReason() {
		return allotReason;
	}
	public void setAllotReason(String allotReason) {
		this.allotReason = allotReason;
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
	@Column(name="ALLOT_STATUS")
	public String getAllotStatus() {
		return allotStatus;
	}
	public void setAllotStatus(String allotStatus) {
		this.allotStatus = allotStatus;
	}
	@Column(name="CONFIRM_OPR_CODE")
	public String getConfirmOprCode() {
		return confirmOprCode;
	}
	public void setConfirmOprCode(String confirmOprCode) {
		this.confirmOprCode = confirmOprCode;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CONFIRM_TIME")
	public Date getConfirmTime() {
		return confirmTime;
	}
	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
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
	@Column(name="DATE_UPDATED")
	public Date getDateUpdated() {
		return dateUpdated;
	}
	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}
	
	
	@Transient
	public String getAllotOprName() {
		return allotOprName;
	}
	public void setAllotOprName(String allotOprName) {
		this.allotOprName = allotOprName;
	}
	@Transient
	public String getAllotOrgName() {
		return allotOrgName;
	}
	public void setAllotOrgName(String allotOrgName) {
		this.allotOrgName = allotOrgName;
	}
	@Transient
	public String getProvideOrgName() {
		return provideOrgName;
	}
	public void setProvideOrgName(String provideOrgName) {
		this.provideOrgName = provideOrgName;
	}
	@Transient
	public String getProvideOprName() {
		return provideOprName;
	}
	public void setProvideOprName(String provideOprName) {
		this.provideOprName = provideOprName;
	}
	@Transient
	public String getAllotStatusName() {
		return allotStatusName;
	}
	public void setAllotStatusName(String allotStatusName) {
		this.allotStatusName = allotStatusName;
	}
}
