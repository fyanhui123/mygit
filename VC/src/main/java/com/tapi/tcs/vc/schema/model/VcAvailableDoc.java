package com.tapi.tcs.vc.schema.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "VC_AVAILABLE_DOC")
@SequenceGenerator(name = "SEQ_VC_AVAILABLE_DOC", sequenceName = "SEQ_VC_AVAILABLE_DOC", allocationSize = 1)
public class VcAvailableDoc implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	/** 可使用单证表流水 */
	private Long id;
	/** 单证类型代码 */
	private String docVerCode;
	/**  单证种类代码  已删除  */
 //	private String docTypeCode;  
	/** 单证流水号 */
	private String docNum;
	/** 印刷批次 */
	private String pressBatchNo;
	/** 单证状态 */
	private String docStatus;
	/** 使用人代码 */
	private String takerCode;
	/** 使用人归属机构代码 */
	private String orgCode;
	/** 发放时间 */
	private Date provideTime;
	/** 使用截止期 */
	private Date deadline;
	/** 创建人 */
	private String createdBy;
	/** 创建时间 */
	private Date dateCreated;
	/** 修改人 */
	private String updatedBy;
	/** 修改时间 */
	private Date dateUpdated;
	/** 中介机构代码 */
	private String agentCode;
	/** 使用人单证发放回收轨迹表流水 */
	private Long idTakerIO;
	

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VC_AVAILABLE_DOC")
	@Column(name = "ID_VC_AVAILABLE_DOC")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "DOC_VER_CODE", nullable = false)
	public String getDocVerCode() {
		return this.docVerCode;
	}

	public void setDocVerCode(String docVerCode) {
		this.docVerCode = docVerCode;
	}
	
	@Column(name = "DOC_NUM")
	public String getDocNum() {
		return this.docNum;
	}

	public void setDocNum(String docNum) {
		this.docNum = docNum;
	}

	@Column(name = "PRESS_BATCH_NO")
	public String getPressBatchNo() {
		return this.pressBatchNo;
	}

	public void setPressBatchNo(String pressBatchNo) {
		this.pressBatchNo = pressBatchNo;
	}

	@Column(name = "DOC_STATUS", nullable = false)
	public String getDocStatus() {
		return docStatus;
	}

	public void setDocStatus(String docStatus) {
		this.docStatus = docStatus;
	}

	@Column(name = "TAKER_CODE")
	public String getTakerCode() {
		return this.takerCode;
	}

	public void setTakerCode(String takerCode) {
		this.takerCode = takerCode;
	}
	
	@Column(name = "ORG_CODE")
	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	@Column(name = "PROVIDE_TIME")
	public Date getProvideTime() {
		return this.provideTime;
	}

	public void setProvideTime(Date provideTime) {
		this.provideTime = provideTime;
	}
	
	@Column(name = "DEADLINE")
	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
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
	
	@Column(name = "AGENT_CODE")
	public String getAgentCode() {
		return agentCode;
	}
	
	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}

	@Column(name = "ID_TAKER_IO")
	public Long getIdTakerIO() {
		return idTakerIO;
	}

	public void setIdTakerIO(Long idTakerIO) {
		this.idTakerIO = idTakerIO;
	}

}
