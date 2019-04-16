package com.tapi.tcs.vc.schema.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@SequenceGenerator(name = "SEQ_VC_DOC_TAKER_IO", sequenceName = "SEQ_VC_DOC_TAKER_IO", allocationSize = 1)
@Table(name = "VC_DOC_TAKER_IO")
public class VcDocTakerIo implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	/** 使用人单证发放回收轨迹表流水 */
	private Long id;
	/** 单证类型代码 */
	private String docVerCode;
	/** 印刷批次 */
	private String pressBatchNo;
	/** 起始流水号 */
	private String startNum;
	/** 终止流水号 */
	private String endNum;
	/** 单证数量 */
	private Long docNum;
	/** 发放回收标志(P发放/R回收) */
	private String oprType;
	/** 操作员代码 */
	private String oprCode;
	/** 操作时间 */
	private Date oprTime;
	/** 使用人代码 */
	private String takerCode;
	/** 使用人所属机构代码 */
	private String acceptOrgCode;
	/** 发放回收机构代码 */
	private String provideOrgCode;
	/** 创建人 */
	private String createdBy;
	/** 创建时间 */
	private Date dateCreated;
	/** 修改人 */
	private String updatedBy;
	/** 修改时间 */
	private Date dateUpdated;

	// **************自定义属性**************
	/**用户归属机构*/
	private String userComCode;
	/** 使用人名称 */
	private String takerName;
	/** 使用人所属机构名称 */
	private String acceptOrgName;
	/** 单证类型名称 */
	private String docVerName;
	/** 发放起止时间 */
	private Date provideStartDate;
	private Date provideEndDate;

	private String deadline;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VC_DOC_TAKER_IO")
	@Column(name = "ID_VC_DOC_TAKER_IO")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		return pressBatchNo;
	}

	public void setPressBatchNo(String pressBatchNo) {
		this.pressBatchNo = pressBatchNo;
	}

	@Column(name = "START_NUM")
	public String getStartNum() {
		return this.startNum;
	}

	public void setStartNum(String startNum) {
		this.startNum = startNum;
	}

	@Column(name = "END_NUM")
	public String getEndNum() {
		return this.endNum;
	}

	public void setEndNum(String endNum) {
		this.endNum = endNum;
	}

	@Column(name = "DOC_NUM")
	public Long getDocNum() {
		return this.docNum;
	}

	public void setDocNum(Long docNum) {
		this.docNum = docNum;
	}

	@Column(name = "OPR_TYPE")
	public String getOprType() {
		return this.oprType;
	}

	public void setOprType(String oprType) {
		this.oprType = oprType;
	}

	@Column(name = "OPR_CODE")
	public String getOprCode() {
		return this.oprCode;
	}

	public void setOprCode(String oprCode) {
		this.oprCode = oprCode;
	}

	@Column(name = "OPR_TIME")
	public Date getOprTime() {
		return this.oprTime;
	}

	public void setOprTime(Date oprTime) {
		this.oprTime = oprTime;
	}

	@Column(name = "TAKER_CODE")
	public String getTakerCode() {
		return this.takerCode;
	}

	public void setTakerCode(String takerCode) {
		this.takerCode = takerCode;
	}

	@Column(name = "ACCEPT_ORG_CODE")
	public String getAcceptOrgCode() {
		return this.acceptOrgCode;
	}

	public void setAcceptOrgCode(String acceptOrgCode) {
		this.acceptOrgCode = acceptOrgCode;
	}

	@Column(name = "PROVIDE_ORG_CODE")
	public String getProvideOrgCode() {
		return this.provideOrgCode;
	}

	public void setProvideOrgCode(String provideOrgCode) {
		this.provideOrgCode = provideOrgCode;
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
	public String getUserComCode() {
		return userComCode;
	}

	public void setUserComCode(String userComCode) {
		this.userComCode = userComCode;
	}

	@Transient
	public String getTakerName() {
		return takerName;
	}

	public void setTakerName(String takerName) {
		this.takerName = takerName;
	}

	@Transient
	public String getAcceptOrgName() {
		return acceptOrgName;
	}

	public void setAcceptOrgName(String acceptOrgName) {
		this.acceptOrgName = acceptOrgName;
	}

	@Transient
	public String getDocVerName() {
		return docVerName;
	}

	public void setDocVerName(String docVerName) {
		this.docVerName = docVerName;
	}

	@Transient
	public Date getProvideStartDate() {
		return provideStartDate;
	}

	public void setProvideStartDate(Date provideStartDate) {
		this.provideStartDate = provideStartDate;
	}

	@Transient
	public Date getProvideEndDate() {
		return provideEndDate;
	}

	public void setProvideEndDate(Date provideEndDate) {
		this.provideEndDate = provideEndDate;
	}
	
	@Transient
	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((docVerCode == null) ? 0 : docVerCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VcDocTakerIo other = (VcDocTakerIo) obj;
		if (docVerCode == null) {
			if (other.docVerCode != null)
				return false;
		} else if (!docVerCode.equals(other.docVerCode))
			return false;
		return true;
	}
}
