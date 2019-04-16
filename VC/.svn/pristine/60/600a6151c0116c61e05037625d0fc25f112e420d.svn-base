/**
 * 
 */
package com.tapi.tcs.vc.schema.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * @author hanmiao.diao@newtouch.cn
 *
 */
@Entity
@Table(name="VC_ALLOT_DET")
@SequenceGenerator(name = "SEQ_VC_ALLOT_DET", sequenceName = "SEQ_VC_ALLOT_DET", allocationSize = 1)
public class VcAllotDet implements Serializable {
	/**
	 * 
	 */
	private Long idVcallotDet;
	private VcAllot vcallot;
	private String docVerCode;
	private Integer applyNum;
	private Long idVcallot;
	private String docVerCodeName;
	private String pressBatchNo;
	private String docStatus;
	private String endNum;
	private String startNum;
	private Long provideNum;
	private Date deadline;
	private String deadlineStr;
	private String createdBy;
	private Date dateCreated;
	private String updatedBy;
	private Date dateUpdated;
	private String validNum;
	private String selfValidNum;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VC_ALLOT_DET")
	@Column(name="ID_VC_ALLOT_DET")
    public Long getIdVcallotDet() {
		return idVcallotDet;
	}
	public void setIdVcallotDet(Long idVcallotDet) {
		this.idVcallotDet = idVcallotDet;
	}
	@ManyToOne(cascade = {CascadeType.ALL}, targetEntity = VcAllot.class)
	@JoinColumn(name="ID_VC_Allot", referencedColumnName="ID_VC_ALLOT", nullable=false, insertable=false, updatable=false)
	public VcAllot getVcallot() {
		return vcallot;
	}
	public void setVcallot(VcAllot vcallot) {
		this.vcallot = vcallot;
	}
	@Column(name="DOC_VER_CODE")
	public String getDocVerCode() {
		return docVerCode;
	}
	public void setDocVerCode(String docVerCode) {
		this.docVerCode = docVerCode;
	}
	@Column(name="ALLOT_NUM")
	public Integer getApplyNum() {
		return applyNum;
	}
	public void setApplyNum(Integer applyNum) {
		this.applyNum = applyNum;
	}
	@Column(name="ID_VC_ALLOT")
	public Long getIdVcallot() {
		return idVcallot;
	}
	public void setIdVcallot(Long idVcallot) {
		this.idVcallot = idVcallot;
	}
	@Column(name="PRESS_BATCH_NO")
	public String getPressBatchNo() {
		return pressBatchNo;
	}
	public void setPressBatchNo(String pressBatchNo) {
		this.pressBatchNo = pressBatchNo;
	}
	@Column(name="DOC_STATUS")
	public String getDocStatus() {
		return docStatus;
	}
	public void setDocStatus(String docStatus) {
		this.docStatus = docStatus;
	}
	@Column(name="END_NUM")
	public String getEndNum() {
		return endNum;
	}
	public void setEndNum(String endNum) {
		this.endNum = endNum;
	}
	@Column(name="START_NUM")
	public String getStartNum() {
		return startNum;
	}
	public void setStartNum(String startNum) {
		this.startNum = startNum;
	}
	@Column(name="PROVIDE_NUM")
	public Long getProvideNum() {
		return provideNum;
	}
	public void setProvideNum(Long provideNum) {
		this.provideNum = provideNum;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DEADLINE")
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
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
	public String getValidNum() {
		return validNum;
	}
	public void setValidNum(String validNum) {
		this.validNum = validNum;
	}
	@Transient
	public String getSelfValidNum() {
		return selfValidNum;
	}
	public void setSelfValidNum(String selfValidNum) {
		this.selfValidNum = selfValidNum;
	}
	@Transient
	public String getDeadlineStr() {
		return deadlineStr;
	}
	public void setDeadlineStr(String deadlineStr) {
		this.deadlineStr = deadlineStr;
	}
	@Transient
	public String getDocVerCodeName() {
		return docVerCodeName;
	}
	public void setDocVerCodeName(String docVerCodeName) {
		this.docVerCodeName = docVerCodeName;
	}
}
