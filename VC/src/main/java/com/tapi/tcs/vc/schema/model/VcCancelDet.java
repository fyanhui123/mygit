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
 * The persistent class for the VC_CANCEL_DET database table.
 * 
 */
@Entity
@Table(name="VC_CANCEL_DET")
@SequenceGenerator(name = "SEQ_VC_CANCEL_DET", sequenceName = "SEQ_VC_CANCEL_DET", allocationSize = 1)
public class VcCancelDet implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2430756489106736237L;
	private Long idVcCancelDet;
	private Integer cancelNum;
	private String docStatus;
	private String docVerCode;
	private String endNum;
	private String pressBatchNo;
	private String startNum;
	private VcCancel vcCancel;
	private Long idVcCancel;
	
	private String createdBy;
	private Date dateCreated;
	
	private String updatedBy;
	private Date dateUpdated;
	
	
	

	public VcCancelDet() {
	}


	@Id
	@Column(name="ID_VC_CANCEL_DET")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VC_CANCEL_DET")
	public Long getIdVcCancelDet() {
		return this.idVcCancelDet;
	}

	public void setIdVcCancelDet(Long idVcCancelDet) {
		this.idVcCancelDet = idVcCancelDet;
	}


	@Column(name="CANCEL_NUM")
	public Integer getCancelNum() {
		return this.cancelNum;
	}

	public void setCancelNum(Integer cancelNum) {
		this.cancelNum = cancelNum;
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


	@Column(name="END_NUM")
	public String getEndNum() {
		return this.endNum;
	}

	public void setEndNum(String endNum) {
		this.endNum = endNum;
	}


	@Column(name="PRESS_BATCH_NO")
	public String getPressBatchNo() {
		return this.pressBatchNo;
	}

	public void setPressBatchNo(String pressBatchNo) {
		this.pressBatchNo = pressBatchNo;
	}


	@Column(name="START_NUM")
	public String getStartNum() {
		return this.startNum;
	}

	public void setStartNum(String startNum) {
		this.startNum = startNum;
	}


	//bi-directional many-to-one association to VcCancel
	@ManyToOne
	@JoinColumn(name="ID_VC_CANCEL",insertable=false,updatable=false)
	public VcCancel getVcCancel() {
		return this.vcCancel;
	}

	public void setVcCancel(VcCancel vcCancel) {
		this.vcCancel = vcCancel;
	}

	@Column(name="ID_VC_CANCEL")
	public Long getIdVcCancel() {
		return idVcCancel;
	}


	public void setIdVcCancel(Long idVcCancel) {
		this.idVcCancel = idVcCancel;
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