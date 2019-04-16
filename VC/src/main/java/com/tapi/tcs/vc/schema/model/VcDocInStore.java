package com.tapi.tcs.vc.schema.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.Transient;

/**
 * 表 VC_DOC_IN_STORE 对应实体类
 * 
 * @author Leo
 * 
 */
@Entity
@Table(name = "VC_DOC_IN_STORE")
@SequenceGenerator(name = "SEQ_VC_DOC_IN_STORE", sequenceName = "SEQ_VC_DOC_IN_STORE", allocationSize = 1)
public class VcDocInStore implements Serializable {

	private static final long serialVersionUID = 1461092026715031998L;

	/** 单证印刷入库表流水 */
	private Long id;
	/** 印刷入库申请单号 */
	private String inStoreAppCode;
	/** 入库操作人代码 */
	private String applyOprCode;
	/** 申请机构代码 */
	private String applyOrgCode;
	/** 申请时间 */
	private Date applyTime;
	/** 入库登记表扫描件名称 */
	private String fileName;
	/** 入库登记表扫描件路径 */
	private String filePath;
	/** 申请原因 */
	private String applyReason;
	/** 修改人代码 */
	private String modifyOprCode;
	/** 修改时间 */
	private Date modifyTime;
	/** 审批操作人代码 */
	private String checkOprCode;
	/** 审批机构代码 */
	private String checkOrgCode;
	/** 审批时间 */
	private Date checkTime;
	/** 入库申请单状态（0删除/1新建/2等待审批/3审批通过/4审批退回） */
	private String inStoreStatus;
	/** 创建人 */
	private String createdBy;
	/** 创建时间 */
	private Date dateCreated;
	/** 修改人 */
	private String updatedBy;
	/** 修改时间 */
	private Date dateUpdated;
	//add by chy 20131029 黑龙江地税新增字段 begin
	/**领购日期*/
	private Date buyDate;
	/**单张开具限额*/
	private BigDecimal moneyLimit;
	/**电子编码*/
	private String electronicCode;
	//add by chy 20131029 黑龙江地税新增字段 end

	private List<VcDocInStoreDet> vcDocInStoreDets = new ArrayList<VcDocInStoreDet>();

	// 自定义属性 //
	/** 申请机构名称 */
	private String applyOrgName;
	/** 入库操作人名称 */
	private String applyOprName;
	/** 审批操作人名称 */
	private String checkOprName;
	/** 审批机构名称 */
	private String checkOrgName;
	/** 修改人代码 */
	private String modifyOprName;
	/** 申请起始时间 */
	private Date applyStartDate;
	/** 申请终止时间 */
	private Date applyEndDate;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VC_DOC_IN_STORE")
	@Column(name = "ID_VC_DOC_IN_STORE")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "IN_STORE_APP_CODE")
	public String getInStoreAppCode() {
		return inStoreAppCode;
	}

	public void setInStoreAppCode(String inStoreAppCode) {
		this.inStoreAppCode = inStoreAppCode;
	}

	@Column(name = "APPLY_OPR_CODE")
	public String getApplyOprCode() {
		return applyOprCode;
	}

	public void setApplyOprCode(String applyOprCode) {
		this.applyOprCode = applyOprCode;
	}

	@Column(name = "APPLY_ORG_CODE")
	public String getApplyOrgCode() {
		return applyOrgCode;
	}

	public void setApplyOrgCode(String applyOrgCode) {
		this.applyOrgCode = applyOrgCode;
	}

	@Column(name = "APPLY_TIME")
	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	@Column(name = "FILE_NAME")
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Column(name = "FILE_PATH")
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Column(name = "APPLY_REASON")
	public String getApplyReason() {
		return applyReason;
	}

	public void setApplyReason(String applyReason) {
		this.applyReason = applyReason;
	}

	@Column(name = "MODIFY_OPR_CODE")
	public String getModifyOprCode() {
		return modifyOprCode;
	}

	public void setModifyOprCode(String modifyOprCode) {
		this.modifyOprCode = modifyOprCode;
	}

	@Column(name = "MODIFY_TIME")
	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Column(name = "CHECK_OPR_CODE")
	public String getCheckOprCode() {
		return checkOprCode;
	}

	public void setCheckOprCode(String checkOprCode) {
		this.checkOprCode = checkOprCode;
	}

	@Column(name = "CHECK_ORG_CODE")
	public String getCheckOrgCode() {
		return checkOrgCode;
	}

	public void setCheckOrgCode(String checkOrgCode) {
		this.checkOrgCode = checkOrgCode;
	}

	@Column(name = "CHECK_TIME")
	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	@Column(name = "IN_STORE_STATUS")
	public String getInStoreStatus() {
		return inStoreStatus;
	}

	public void setInStoreStatus(String inStoreStatus) {
		this.inStoreStatus = inStoreStatus;
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

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "vcDocInStore", fetch = FetchType.LAZY)
	public List<VcDocInStoreDet> getVcDocInStoreDets() {
		return vcDocInStoreDets;
	}

	public void setVcDocInStoreDets(List<VcDocInStoreDet> vcDocInStoreDets) {
		this.vcDocInStoreDets = vcDocInStoreDets;
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
	public String getCheckOprName() {
		return checkOprName;
	}

	public void setCheckOprName(String checkOprName) {
		this.checkOprName = checkOprName;
	}

	@Transient
	public String getCheckOrgName() {
		return checkOrgName;
	}

	public void setCheckOrgName(String checkOrgName) {
		this.checkOrgName = checkOrgName;
	}

	@Transient
	public String getModifyOprName() {
		return modifyOprName;
	}

	public void setModifyOprName(String modifyOprName) {
		this.modifyOprName = modifyOprName;
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

	@Column(name = "BUY_DATE")
	public Date getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	@Column(name = "MONEY_LIMIT")
	public BigDecimal getMoneyLimit() {
		return moneyLimit;
	}

	public void setMoneyLimit(BigDecimal moneyLimit) {
		this.moneyLimit = moneyLimit;
	}

	@Column(name = "ELECTRONIC_CODE")
	public String getElectronicCode() {
		return electronicCode;
	}

	public void setElectronicCode(String electronicCode) {
		this.electronicCode = electronicCode;
	}

}
