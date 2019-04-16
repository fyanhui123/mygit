package com.tapi.tcs.vc.schema.model;

import java.util.Date;
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
 * POJO Class VcDestroyDet
 */
@Entity
@Table(name = "VC_DESTROY_DET")
@SequenceGenerator(name = "SEQ_VC_DESTROY_DET", sequenceName = "SEQ_VC_DESTROY_DET", allocationSize = 1)
public class VcDestroyDet implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	/** 单证销毁明细表流水 */
	private Long id;
	/** 单证销毁表流水 */
	private VcDestroy vcDestroy;
	/** 单证类型代码 */
	private String docVerCode;
	/** 印刷批次 */
	private String pressBatchNo;
	/** 起始流水号 */
	private String startNum;
	/** 终止流水号 */
	private String endNum;
	/** 销毁数量 */
	private Long destroyNum;
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
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VC_DESTROY_DET")
	@Column(name = "ID_VC_DESTROY_DET")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_VC_DESTROY")
	public VcDestroy getVcDestroy() {
		return vcDestroy;
	}

	public void setVcDestroy(VcDestroy vcDestroy) {
		this.vcDestroy = vcDestroy;
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

	@Column(name = "DESTROY_NUM")
	public Long getDestroyNum() {
		return this.destroyNum;
	}

	public void setDestroyNum(Long destroyNum) {
		this.destroyNum = destroyNum;
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
		return createdBy;
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
		return updatedBy;
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
		VcDestroyDet other = (VcDestroyDet) obj;
		if (docVerCode == null) {
			if (other.docVerCode != null)
				return false;
		} else if (!docVerCode.equals(other.docVerCode))
			return false;
		return true;
	}
}
