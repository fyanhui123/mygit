package com.tapi.tcs.vc.schema.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
 * 表 VC_LOST_DET 对应实体类
 * 
 * @author Leo
 */
@Entity
@Table(name = "VC_LOST_DET")
@SequenceGenerator(name = "SEQ_VC_LOST_DET", sequenceName = "SEQ_VC_LOST_DET", allocationSize = 1)
public class VcLostDet implements java.io.Serializable {

	private static final long serialVersionUID = 1461092026715031998L;

	/** 单证遗失明细表流水 */
	private Long id;
	/** 单证遗失表流水 */
	private VcLost vcLost;
	/** 单证类型代码 */
	private String docVerCode;
	/** 印刷批次 */
	private String pressBatchNo;
	/** 起始流水号 */
	private String startNum;
	/** 终止流水号 */
	private String endNum;
	/** 遗失数量 */
	private Long lostNum;
	/** 单证状态 */
	private String docStatus;
	/** 创建人 */
	private String createdBy;
	/** 创建时间 */
	private Date dateCreated;
	/** 修改人 */
	private String updatedBy;
	/** 修改时间 */
	private Date dateUpdated;

	// 自定义属性 //
	/** 单证类型代码 */
	private String docVerName;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VC_LOST_DET")
	@Column(name = "ID_VC_LOST_DET")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_VC_LOST")
	public VcLost getVcLost() {
		return vcLost;
	}

	public void setVcLost(VcLost vcLost) {
		this.vcLost = vcLost;
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

	@Column(name = "LOST_NUM")
	public Long getLostNum() {
		return this.lostNum;
	}

	public void setLostNum(Long lostNum) {
		this.lostNum = lostNum;
	}

	@Column(name = "DOC_STATUS")
	public String getDocStatus() {
		return this.docStatus;
	}

	public void setDocStatus(String docStatus) {
		this.docStatus = docStatus;
	}

	@Column(name = "CREATED_BY")
	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_CREATED")
	public Date getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Column(name = "UPDATED_BY")
	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_UPDATED")
	public Date getDateUpdated() {
		return this.dateUpdated;
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
		VcLostDet other = (VcLostDet) obj;
		if (docVerCode == null) {
			if (other.docVerCode != null)
				return false;
		} else if (!docVerCode.equals(other.docVerCode))
			return false;
		return true;
	}
}
