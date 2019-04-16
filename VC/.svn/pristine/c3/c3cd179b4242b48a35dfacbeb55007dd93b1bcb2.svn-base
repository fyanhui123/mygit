package com.tapi.tcs.vc.schema.model;

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
import javax.persistence.Transient;

/**
 * 表 VC_LOST 对应实体类
 * 
 * @author Leo
 */
@Entity
@Table(name = "VC_LOST")
@SequenceGenerator(name = "SEQ_VC_LOST", sequenceName = "SEQ_VC_LOST", allocationSize = 1)
public class VcLost implements java.io.Serializable {
	private static final long serialVersionUID = 1461092026715031998L;

	/** 单证遗失表流水 */
	private Long id;
	/** 遗失单号*/
	private String lostCode;
	/** 遗失操作人代码*/
	private String lostOprCode;
	/** 遗失提交机构代码*/
	private String lostOrgCode;
	/** 遗失提交时间*/
	private Date lostTime;
	/** 遗失原因*/
	private String lostReason;
	/** 遗失证明材料扫描件名称*/
	private String fileName;
	/** 遗失证明材料扫描件路径*/
	private String filePath;
	/** 遗失审批操作人代码*/
	private String approveOprCode;
	/** 遗失审批机构代码*/
	private String approveOrgCode;
	/** 遗失审批时间*/
	private Date approveTime;
	/** 修改人代码*/
	private String modifyOprCode;
	/** 修改时间*/
	private Date modifyTime;
	/** 遗失处理状态（0删除/1新建/2待审批/3审批退回/4审批通过）*/
	private String lostStatus;
	/** 创建人 */
	private String createdBy;
	/** 创建时间 */
	private Date dateCreated;
	/** 修改人 */
	private String updatedBy;
	/** 修改时间 */
	private Date dateUpdated;
	/**遗失日期*/
	private Date lostDate;
	/**登报日期*/
	private Date reportLostDate;
	/**单证或者发票标识*/
	private String invoiceFlag;
	
	private List<VcLostDet> vcLostDets = new ArrayList<VcLostDet>();

	/**************自定义属性**************/
	/** 遗失操作人名称*/
	private String lostOprName;
	/** 遗失提交机构名称*/
	private String lostOrgName;
	/** 申请起始时间 */
	private Date applyStartDate;
	/** 申请结束时间 */
	private Date applyEndDate;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VC_LOST")
	@Column(name = "ID_VC_LOST", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "LOST_CODE")
	public String getLostCode() {
		return this.lostCode;
	}

	public void setLostCode(String lostCode) {
		this.lostCode = lostCode;
	}

	@Column(name = "LOST_OPR_CODE")
	public String getLostOprCode() {
		return this.lostOprCode;
	}

	public void setLostOprCode(String lostOprCode) {
		this.lostOprCode = lostOprCode;
	}

	@Column(name = "LOST_ORG_CODE")
	public String getLostOrgCode() {
		return this.lostOrgCode;
	}

	public void setLostOrgCode(String lostOrgCode) {
		this.lostOrgCode = lostOrgCode;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LOST_TIME")
	public Date getLostTime() {
		return this.lostTime;
	}

	public void setLostTime(Date lostTime) {
		this.lostTime = lostTime;
	}

	@Column(name = "LOST_REASON")
	public String getLostReason() {
		return this.lostReason;
	}

	public void setLostReason(String lostReason) {
		this.lostReason = lostReason;
	}

	@Column(name = "FILE_NAME")
	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Column(name = "FILE_PATH")
	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Column(name = "APPROVE_OPR_CODE")
	public String getApproveOprCode() {
		return this.approveOprCode;
	}

	public void setApproveOprCode(String approveOprCode) {
		this.approveOprCode = approveOprCode;
	}

	@Column(name = "APPROVE_ORG_CODE")
	public String getApproveOrgCode() {
		return this.approveOrgCode;
	}

	public void setApproveOrgCode(String approveOrgCode) {
		this.approveOrgCode = approveOrgCode;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "APPROVE_TIME")
	public Date getApproveTime() {
		return this.approveTime;
	}

	public void setApproveTime(Date approveTime) {
		this.approveTime = approveTime;
	}

	@Column(name = "MODIFY_OPR_CODE")
	public String getModifyOprCode() {
		return this.modifyOprCode;
	}

	public void setModifyOprCode(String modifyOprCode) {
		this.modifyOprCode = modifyOprCode;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MODIFY_TIME")
	public Date getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Column(name = "LOST_STATUS")
	public String getLostStatus() {
		return this.lostStatus;
	}

	public void setLostStatus(String lostStatus) {
		this.lostStatus = lostStatus;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "vcLost")
	public List<VcLostDet> getVcLostDets() {
		return vcLostDets;
	}

	public void setVcLostDets(List<VcLostDet> vcLostDets) {
		this.vcLostDets = vcLostDets;
	}

	@Column(name = "LOST_DATE")
	public Date getLostDate() {
		return lostDate;
	}

	public void setLostDate(Date lostDate) {
		this.lostDate = lostDate;
	}

	@Column(name = "REPORT_LOST_DATE")
	public Date getReportLostDate() {
		return reportLostDate;
	}

	public void setReportLostDate(Date reportLostDate) {
		this.reportLostDate = reportLostDate;
	}

	@Transient
	public Date getApplyStartDate() {
		return applyStartDate;
	}

	public void setApplyStartDate(Date applyStartDate) {
		this.applyStartDate = applyStartDate;
	}

	@Transient
	public Date getApplyEndDate() {
		return applyEndDate;
	}

	public void setApplyEndDate(Date applyEndDate) {
		this.applyEndDate = applyEndDate;
	}

	@Transient
	public String getLostOprName() {
		return lostOprName;
	}

	public void setLostOprName(String lostOprName) {
		this.lostOprName = lostOprName;
	}

	@Transient
	public String getLostOrgName() {
		return lostOrgName;
	}

	public void setLostOrgName(String lostOrgName) {
		this.lostOrgName = lostOrgName;
	}
	@Column(name = "invoice_flag")
	public String getInvoiceFlag() {
		return invoiceFlag;
	}

	public void setInvoiceFlag(String invoiceFlag) {
		this.invoiceFlag = invoiceFlag;
	}
	
}
