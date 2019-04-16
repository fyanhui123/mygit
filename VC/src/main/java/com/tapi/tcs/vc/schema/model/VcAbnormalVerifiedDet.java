package com.tapi.tcs.vc.schema.model;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "VC_ABNORMAL_VERIFIED_DET")
public class VcAbnormalVerifiedDet implements Serializable {

	private static final long serialVersionUID = -1981614125421047227L;

	private Long idVcAbnormalVerifiedDet; // 单证非正常核销明细表流水
//	private Long idVcAbnormalVerification; // 单证非正常核销表流水
	private String businessNo; // 业务号
	private String relBusinessNo; // 批单对应的保单号
	private String payNo; // 缴费期次
	private String createdBy; // 创建人
	private Date dateCreated; // 创建时间
	private String updatedBy; // 修改人
	private Date dateUpdated; // 修改时间
	private String batchNo;//拆分批次
	//modifY by zhxiao3 VC-129 发票打印新增字段“共保付款人” begin
	private String payerCode;//共保付款人
	//modifY by zhxiao3 VC-129 发票打印新增字段“共保付款人” end
	
	private String correctionNo;
	private String counteractFlag;
	private VcAbnormalVerification vcAbnormalVerification;

	@SequenceGenerator(name = "generator", allocationSize = 1, sequenceName = "SEQ_VC_ABNORMAL_VERIFIED_DET")
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "generator")
	@Column(name = "ID_VC_ABNORMAL_VERIFIED_DET")
	public Long getIdVcAbnormalVerifiedDet() {
		return idVcAbnormalVerifiedDet;
	}

	public void setIdVcAbnormalVerifiedDet(Long idVcAbnormalVerifiedDet) {
		this.idVcAbnormalVerifiedDet = idVcAbnormalVerifiedDet;
	}

	/*@Column(name = "ID_VC_ABNORMAL_VERIFICATION")
	public Long getIdVcAbnormalVerification() {
		return idVcAbnormalVerification;
	}

	public void setIdVcAbnormalVerification(Long idVcAbnormalVerification) {
		this.idVcAbnormalVerification = idVcAbnormalVerification;
	}*/

	@Column(name = "BUSINESS_NO")
	public String getBusinessNo() {
		return businessNo;
	}

	public void setBusinessNo(String businessNo) {
		this.businessNo = businessNo;
	}

	@Column(name = "REL_BUSINESS_NO")
	public String getRelBusinessNo() {
		return relBusinessNo;
	}

	public void setRelBusinessNo(String relBusinessNo) {
		this.relBusinessNo = relBusinessNo;
	}

	@Column(name = "PAY_NO")
	public String getPayNo() {
		return payNo;
	}

	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}

	@Column(name = "CREATED_BY")
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "DATE_CREATED")
	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Column(name = "UPDATED_BY")
	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Column(name = "DATE_UPDATED")
	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_VC_ABNORMAL_VERIFICATION", referencedColumnName="ID_VC_ABNORMAL_VERIFICATION", nullable = true)
	public VcAbnormalVerification getVcAbnormalVerification() {
		return vcAbnormalVerification;
	}

	public void setVcAbnormalVerification(
			VcAbnormalVerification vcAbnormalVerification) {
		this.vcAbnormalVerification = vcAbnormalVerification;
	}
	
	@Column(name = "BATCH_NO")
	public String getBatchNo() {
		return batchNo;
	}
	
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	@Column(name = "PAYER_CODE")
	public String getPayerCode() {
		return payerCode;
	}

	public void setPayerCode(String payerCode) {
		this.payerCode = payerCode;
	}
	@Column(name = "correction_no")
	public String getCorrectionNo() {
		return correctionNo;
	}

	public void setCorrectionNo(String correctionNo) {
		this.correctionNo = correctionNo;
	}
	@Column(name = "COUNTERACT_FLAG")
	public String getCounteractFlag() {
		return counteractFlag;
	}

	public void setCounteractFlag(String counteractFlag) {
		this.counteractFlag = counteractFlag;
	}
	
}
