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
 * POJO Class VcDestroy
 */
@Entity
@Table(name = "VC_DESTROY")
@SequenceGenerator(name = "SEQ_VC_DESTROY", sequenceName = "SEQ_VC_DESTROY", allocationSize = 1)
public class VcDestroy implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	/** 单证销毁表流水 */
	private Long id;
	/** 销毁单号 */
	private String destroyCode;
	/** 销毁操作人代码 */
	private String destroyOprCode;
	/** 销毁提交机构代码 */
	private String destroyOrgCode;
	/** 销毁提交时间 */
	private Date destroyAppTime;
	/** 销毁类型 */
	private String destroyType;
	/** 销毁原因 */
	private String destroyReason;
	/** 销毁证明材料扫描件名称 */
	private String fileName;
	/** 销毁证明材料扫描件路径 */
	private String filePath;
	/** 销毁审批操作人代码 */
	private String approveOprCode;
	/** 销毁审批机构代码 */
	private String approveOrgCode;
	/** 销毁审批时间 */
	private Date approveTime;
	/** 修改人代码 */
	private String modifyOprCode;
	/** 修改人代码 */
	private Date modifyTime;
	/** 销毁处理状态（0删除/1新建/2待审批/3审批退回/4审批通过） */
	private String destroyStatus;
	/** 创建人 */
	private String createdBy;
	/** 创建时间 */
	private Date dateCreated;
	/** 修改人 */
	private String updatedBy;
	/** 修改时间 */
	private Date dateUpdated;
	
	private List<VcDestroyDet> vcDestroyDets = new ArrayList<VcDestroyDet>();
	/** 区分单证还是发票 */
	private String invoiceFlag;
	/** 区分销毁还是缴销*/
	private String jdFlag;
	/***********自定义属性***************/
	
	private Date applyEndDate;
	private Date applyStartDate;
	/** 销毁操作人代名称*/
	private String destroyOprName;
	/** 销毁提交机构名称 */
	private String destroyOrgName;
	
	/***********自定义属性***************/

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VC_DESTROY")
	@Column(name = "ID_VC_DESTROY", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "DESTROY_CODE", nullable = false)
	public String getDestroyCode() {
		return this.destroyCode;
	}

	public void setDestroyCode(String destroyCode) {
		this.destroyCode = destroyCode;
	}

	@Column(name = "DESTROY_OPR_CODE", nullable = false)
	public String getDestroyOprCode() {
		return destroyOprCode;
	}

	public void setDestroyOprCode(String destroyOprCode) {
		this.destroyOprCode = destroyOprCode;
	}

	@Column(name = "DESTROY_ORG_CODE", nullable = false)
	public String getDestroyOrgCode() {
		return this.destroyOrgCode;
	}

	public void setDestroyOrgCode(String destroyOrgCode) {
		this.destroyOrgCode = destroyOrgCode;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DESTROY_APP_TIME", nullable = false)
	public Date getDestroyAppTime() {
		return this.destroyAppTime;
	}

	public void setDestroyAppTime(Date destroyAppTime) {
		this.destroyAppTime = destroyAppTime;
	}
	
	@Column(name = "DESTROY_TYPE")
	public String getDestroyType() {
		return destroyType;
	}

	public void setDestroyType(String destroyType) {
		this.destroyType = destroyType;
	}
	

	@Column(name = "DESTROY_REASON")
	public String getDestroyReason() {
		return this.destroyReason;
	}

	public void setDestroyReason(String destroyReason) {
		this.destroyReason = destroyReason;
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
		return approveOprCode;
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

	@Column(name = "MODIFY_OPR_CODE", nullable = false)
	public String getModifyOprCode() {
		return modifyOprCode;
	}

	public void setModifyOprCode(String modifyOprCode) {
		this.modifyOprCode = modifyOprCode;
	}
	

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MODIFY_TIME", nullable = false)
	public Date getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Column(name = "DESTROY_STATUS", nullable = false)
	public String getDestroyStatus() {
		return destroyStatus;
	}

	public void setDestroyStatus(String destroyStatus) {
		this.destroyStatus = destroyStatus;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "vcDestroy")
	public List<VcDestroyDet> getVcDestroyDets() {
		return vcDestroyDets;
	}

	public void setVcDestroyDets(List<VcDestroyDet> vcDestroyDets) {
		this.vcDestroyDets = vcDestroyDets;
	}

	@Transient
	public Date getApplyEndDate() {
		return applyEndDate;
	}

	public void setApplyEndDate(Date applyEndDate) {
		this.applyEndDate = applyEndDate;
	}

	@Transient
	public Date getApplyStartDate() {
		return applyStartDate;
	}

	public void setApplyStartDate(Date applyStartDate) {
		this.applyStartDate = applyStartDate;
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
	@Column(name = "invoice_flag")
	public String getInvoiceFlag() {
		return invoiceFlag;
	}

	public void setInvoiceFlag(String invoiceFlag) {
		this.invoiceFlag = invoiceFlag;
	}
	@Column(name = "jd_flag")
	public String getJdFlag() {
		return jdFlag;
	}
	public void setJdFlag(String jdFlag) {
		this.jdFlag = jdFlag;
	}

	@Transient
	public String getDestroyOprName() {
		return destroyOprName;
	}

	public void setDestroyOprName(String destroyOprName) {
		this.destroyOprName = destroyOprName;
	}

	@Transient
	public String getDestroyOrgName() {
		return destroyOrgName;
	}

	public void setDestroyOrgName(String destroyOrgName) {
		this.destroyOrgName = destroyOrgName;
	}
}
