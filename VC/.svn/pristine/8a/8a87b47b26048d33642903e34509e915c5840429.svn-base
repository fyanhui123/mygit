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
@Table(name="VC_APPLY_DET")
@SequenceGenerator(name = "SEQ_VC_APPLY_DET", sequenceName = "SEQ_VC_APPLY_DET", allocationSize = 1)
public class VcApplyDet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4637120709159151584L;

	private Long idVcApplyDet;
	private VcApply vcApply;
	private String docVerCode;
	private Integer applyNum;
	private Long idVcApply;
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
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VC_APPLY_DET")
	@Column(name="ID_VC_APPLY_DET")
	public Long getIdVcApplyDet() {
		return idVcApplyDet;
	}
	public void setIdVcApplyDet(Long idVcApplyDet) {
		this.idVcApplyDet = idVcApplyDet;
	}
	
	@ManyToOne(cascade = {CascadeType.ALL}, targetEntity = VcApply.class)
	@JoinColumn(name="ID_VC_APPLY", referencedColumnName="ID_VC_APPLY", nullable=false, insertable=false, updatable=false)
	public VcApply getVcApply() {
		return vcApply;
	}
	public void setVcApply(VcApply vcApply) {
		this.vcApply = vcApply;
	}

	
	@Column(name="DOC_VER_CODE")
	public String getDocVerCode() {
		return docVerCode;
	}
	public void setDocVerCode(String docVerCode) {
		this.docVerCode = docVerCode;
	}
	
	@Column(name="APPLY_NUM")
	public Integer getApplyNum() {
		return applyNum;
	}
	public void setApplyNum(Integer applyNum) {
		this.applyNum = applyNum;
	}
	
	@Column(name="ID_VC_APPLY")
	public Long getIdVcApply() {
		return idVcApply;
	}
	public void setIdVcApply(Long idVcApply) {
		this.idVcApply = idVcApply;
	}
	
	@Column(name="PRESS_BATCH_NO")
	public String getPressBatchNo() {
		return this.pressBatchNo;
	}

	public void setPressBatchNo(String pressBatchNo) {
		this.pressBatchNo = pressBatchNo;
	}
	@Column(name="END_NUM")
	public String getEndNum() {
		return this.endNum;
	}

	public void setEndNum(String endNum) {
		this.endNum = endNum;
	}

	@Column(name="START_NUM")
	public String getStartNum() {
		return this.startNum;
	}

	public void setStartNum(String startNum) {
		this.startNum = startNum;
	}
	
	@Column(name="DOC_STATUS")
	public String getDocStatus() {
		return this.docStatus;
	}

	public void setDocStatus(String docStatus) {
		this.docStatus = docStatus;
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
	public String getDocVerCodeName() {
		return docVerCodeName;
	}
	public void setDocVerCodeName(String docVerCodeName) {
		this.docVerCodeName = docVerCodeName;
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
}
