package com.tapi.tcs.vc.schema.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "VC_STORAGE")
@SequenceGenerator(name = "SEQ_VC_STORAGE", sequenceName = "SEQ_VC_STORAGE", allocationSize = 1)
public class VcStorage implements Serializable {

	private static final long serialVersionUID = 1461092026715031998L;

	/** 单证库存表流水 */
	private Long id;
	/** 单证版本代码 */
	private String docVerCode;
	/** 印刷批次 */
	private String pressBatchNo;
	/** 起始流水号 */
	private String startNum;
	/** 终止流水号 */
	private String endNum;
	/** 单证数量 */
	private Long docNum;
	/** 单证状态 */
	private String docStatus;
	/** 机构代码 */
	private String orgCode;
	/** 入库时间 */
	private Date inStoreTime;
	/** 使用截止时间 */
	private Date deadline;
	/** 创建人代码 */
	private String createUserCode;
	/** 创建时间 */
	private Date createTime;
	/** 修改人代码 */
	private String modifyUserCode;
	/** 修改时间 */
	private Date modifyTime;
	/** 创建人 */
	private String createdBy;
	/** 创建时间 */
	private Date dateCreated;
	/** 修改人 */
	private String updatedBy;
	/** 修改时间 */
	private Date dateUpdated;
	
	private String docVerName;
	
	private String docStatusZh;
	
	private String orgName;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VC_STORAGE")
	@Column(name = "ID_VC_STORAGE")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "DOC_VER_CODE")
	public String getDocVerCode() {
		return docVerCode;
	}

	public void setDocVerCode(String docVerCode) {
		this.docVerCode = docVerCode;
	}

	@Column(name = "PRESS_BATCH_NO")
	public String getPressBatchNo() {
		return pressBatchNo;
	}

	public void setPressBatchNo(String pressBatchNo) {
		this.pressBatchNo = pressBatchNo;
	}

	@Column(name = "START_NUM")
	public String getStartNum() {
		return startNum;
	}

	public void setStartNum(String startNum) {
		this.startNum = startNum;
	}

	@Column(name = "END_NUM")
	public String getEndNum() {
		return endNum;
	}

	public void setEndNum(String endNum) {
		this.endNum = endNum;
	}

	@Column(name = "DOC_NUM")
	public Long getDocNum() {
		return docNum;
	}

	public void setDocNum(Long docNum) {
		this.docNum = docNum;
	}

	@Column(name = "DOC_STATUS", nullable = false)
	public String getDocStatus() {
		return docStatus;
	}

	public void setDocStatus(String docStatus) {
		this.docStatus = docStatus;
	}

	@Column(name = "ORG_CODE")
	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	@Column(name = "IN_STORE_TIME")
	public Date getInStoreTime() {
		return inStoreTime;
	}

	public void setInStoreTime(Date inStoreTime) {
		this.inStoreTime = inStoreTime;
	}

	@Column(name = "DEADLINE",length = 7)
	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	
	@Column(name = "CREATE_USER_CODE")
	public String getCreateUserCode() {
		return createUserCode;
	}

	public void setCreateUserCode(String createUserCode) {
		this.createUserCode = createUserCode;
	}

	@Column(name = "CREATE_TIME")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "MODIFY_USER_CODE")
	public String getModifyUserCode() {
		return modifyUserCode;
	}

	public void setModifyUserCode(String modifyUserCode) {
		this.modifyUserCode = modifyUserCode;
	}

	@Column(name = "MODIFY_TIME")
	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
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

	@Transient
	public String getDocVerName() {
		return docVerName;
	}

	public void setDocVerName(String docVerName) {
		this.docVerName = docVerName;
	}

	@Transient
	public String getDocStatusZh() {
		return docStatusZh;
	}

	public void setDocStatusZh(String docStatusZh) {
		this.docStatusZh = docStatusZh;
	}

	@Transient
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
}
