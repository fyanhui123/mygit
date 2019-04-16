package com.tapi.tcs.vc.schema.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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

/**
 * The persistent class for the VC_NORMAL_VERIFICATION database table.
 * 
 */
@Entity
@Table(name = "VC_NORMAL_VERIFICATION_HIS")
@SequenceGenerator(name = "SEQ_VC_NORMAL_VERIFICATION_HIS", sequenceName = "SEQ_VC_NORMAL_VERIFICATION_HIS", allocationSize = 1)
public class VcNormalVerificationHis implements Serializable {

	private static final long serialVersionUID = -8174953162629219388L;

	private Long idVcNormalVerificationHis; // 正常核销表流水
	private String docNum; // 单证流水号
	private String docVerCode; // 单证类型代码
	private String pressBatchNo; // 印刷批次
	private String businessNo; // 业务号
	private String relBusinessNo; // 批单对应的保单号
	private String docStatus; // 单证状态
	private String useType; // 使用人类型
	private String verifiedOprCode;// 核销操作人代码
	private String verifiedOrgCode; // 核销机构代码
	private String agentCode;// 中介机构代码
	private Date verifiedTime;// 核销时间
	private String verifiedReason;// 核销原因
	private String createdBy;// 创建人
	private Date dateCreated;// 创建时间
	private String updatedBy; // 修改人
	private Date dateUpdated; // 修改时间

	/**核销明细*/
	private List<VcNormalVerifiedDetHis> vcNormalVerifiedDetHis = new ArrayList<VcNormalVerifiedDetHis>();
	
	public VcNormalVerificationHis() {
	}

	@Id
	@Column(name = "ID_VC_NORMAL_VERIFICATION_HIS")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VC_NORMAL_VERIFICATION_HIS")
	public Long getIdVcNormalVerificationHis() {
		return idVcNormalVerificationHis;
	}

	public void setIdVcNormalVerificationHis(Long idVcNormalVerificationHis) {
		this.idVcNormalVerificationHis = idVcNormalVerificationHis;
	}

	@Column(name = "BUSINESS_NO")
	public String getBusinessNo() {
		return this.businessNo;
	}

	public void setBusinessNo(String businessNo) {
		this.businessNo = businessNo;
	}

	@Column(name = "DOC_NUM")
	public String getDocNum() {
		return this.docNum;
	}

	public void setDocNum(String docNum) {
		this.docNum = docNum;
	}

	@Column(name = "DOC_STATUS")
	public String getDocStatus() {
		return this.docStatus;
	}

	public void setDocStatus(String docStatus) {
		this.docStatus = docStatus;
	}

	@Column(name = "DOC_VER_CODE")
	public String getDocVerCode() {
		return this.docVerCode;
	}

	public void setDocVerCode(String docVerCode) {
		this.docVerCode = docVerCode;
	}

	@Column(name = "PRESS_BATCH_NO")
	public String getPressBatchNo() {
		return this.pressBatchNo;
	}

	public void setPressBatchNo(String pressBatchNo) {
		this.pressBatchNo = pressBatchNo;
	}

	@Column(name = "VERIFIED_OPR_CODE")
	public String getVerifiedOprCode() {
		return this.verifiedOprCode;
	}

	public void setVerifiedOprCode(String verifiedOprCode) {
		this.verifiedOprCode = verifiedOprCode;
	}

	@Column(name = "VERIFIED_ORG_CODE")
	public String getVerifiedOrgCode() {
		return this.verifiedOrgCode;
	}

	public void setVerifiedOrgCode(String verifiedOrgCode) {
		this.verifiedOrgCode = verifiedOrgCode;
	}

	@Column(name = "VERIFIED_REASON")
	public String getVerifiedReason() {
		return this.verifiedReason;
	}

	public void setVerifiedReason(String verifiedReason) {
		this.verifiedReason = verifiedReason;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "VERIFIED_TIME")
	public Date getVerifiedTime() {
		return this.verifiedTime;
	}

	public void setVerifiedTime(Date verifiedTime) {
		this.verifiedTime = verifiedTime;
	}

	@Column(name = "CREATED_BY")
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_UPDATED")
	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	@Column(name = "REL_BUSINESS_NO")
	public String getRelBusinessNo() {
		return relBusinessNo;
	}
	
	public void setRelBusinessNo(String relBusinessNo) {
		this.relBusinessNo = relBusinessNo;
	}

	@Column(name = "USE_TYPE")
	public String getUseType() {
		return useType;
	}

	public void setUseType(String useType) {
		this.useType = useType;
	}

	@Column(name = "AGENT_CODE")
	public String getAgentCode() {
		return agentCode;
	}

	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "vcNormalVerificationHis")
	public List<VcNormalVerifiedDetHis> getVcNormalVerifiedDetHis() {
		return vcNormalVerifiedDetHis;
	}

	public void setVcNormalVerifiedDetHis(
			List<VcNormalVerifiedDetHis> vcNormalVerifiedDetHis) {
		this.vcNormalVerifiedDetHis = vcNormalVerifiedDetHis;
	}
	
}