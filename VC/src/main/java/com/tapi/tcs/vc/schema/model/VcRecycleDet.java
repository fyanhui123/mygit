package com.tapi.tcs.vc.schema.model;

import java.io.Serializable;
import java.util.Date;

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


/**
 * The persistent class for the VC_RECYCLE_DET database table.
 * 
 */
@Entity
@Table(name="VC_RECYCLE_DET")
@SequenceGenerator(name = "SEQ_VC_RECYCLE_DET", sequenceName = "SEQ_VC_RECYCLE_DET", allocationSize = 1)
public class VcRecycleDet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7920766928540372881L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_VC_RECYCLE_DET")
	@Column(name="ID_VC_RECYCLE_DET")
	private Long idVcRecycleDet;

	@Column(name="DOC_VER_CODE")
	private String docVerCode;

	@Column(name="END_NUM")
	private String endNum;

	@Column(name="PRESS_BATCH_NO")
	private String pressBatchNo;

	@Column(name="RECYCLE_NUM")
	private Long recycleNum;

	@Column(name="START_NUM")
	private String startNum;
	
	@Column(name="DOC_STATUS")
	private String docStatus;

	//bi-directional many-to-one association to VcRecycle
	@ManyToOne
	@JoinColumn(name="ID_VC_RECYCLE",insertable=false,updatable=false)
	private VcRecycle vcRecycle;
	
	@Column(name="ID_VC_RECYCLE")
	private Long idVcRecycle;
	
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
	

	public VcRecycleDet() {
	}

	public String getDocVerCode() {
		return this.docVerCode;
	}

	public void setDocVerCode(String docVerCode) {
		this.docVerCode = docVerCode;
	}

	public String getEndNum() {
		return this.endNum;
	}

	public void setEndNum(String endNum) {
		this.endNum = endNum;
	}

	public String getPressBatchNo() {
		return this.pressBatchNo;
	}

	public void setPressBatchNo(String pressBatchNo) {
		this.pressBatchNo = pressBatchNo;
	}

	public Long getRecycleNum() {
		return recycleNum;
	}

	public void setRecycleNum(Long recycleNum) {
		this.recycleNum = recycleNum;
	}

	public String getStartNum() {
		return this.startNum;
	}

	public void setStartNum(String startNum) {
		this.startNum = startNum;
	}

	public VcRecycle getVcRecycle() {
		return this.vcRecycle;
	}

	public void setVcRecycle(VcRecycle vcRecycle) {
		this.vcRecycle = vcRecycle;
	}

	public Long getIdVcRecycleDet() {
		return idVcRecycleDet;
	}

	public void setIdVcRecycleDet(Long idVcRecycleDet) {
		this.idVcRecycleDet = idVcRecycleDet;
	}

	public Long getIdVcRecycle() {
		return idVcRecycle;
	}

	public void setIdVcRecycle(Long idVcRecycle) {
		this.idVcRecycle = idVcRecycle;
	}

	public String getDocStatus() {
		return docStatus;
	}

	public void setDocStatus(String docStatus) {
		this.docStatus = docStatus;
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