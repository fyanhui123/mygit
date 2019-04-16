package com.tapi.tcs.vc.schema.model;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 公用审批表
 * <p>
 * Date 2013-03-20
 * </p>
 * <p>
 * Module：公共模块
 * </p>
 * <p>
 * Description：
 * </p>
 * <p>
 * Remark：实体类，不允许修改
 * </p>
 * @author chy
 * @version 1.0
 */
@Entity
@Table(name = "VC_APPROVE")
public class VcApprove implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/**申请审核流水*/
	private Long id;
	/**业务流水*/
	private Long applyId;
	/**申请类型*/
	private String applyType;
	/**审批机构*/
	private String checkOrgId;
	/**审批人*/
	private String checkOprId;
	/**审批状态*/
	private String checkStatus;
	/**审批时间*/
	private Date checkTime;
	/**审批意见*/
	private String checkExpl;
	/**创建人*/
	private String createdBy;
	/**创建时间*/
	private Date dateCreated;
	/**修改人*/
	private String updatedBy;
	/**修改时间*/
	private Date dateUpdated;
	
	@SequenceGenerator(name = "generator", allocationSize = 1, sequenceName = "SEQ_VC_APPROVE")
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "generator")
	@Column(name = "ID_VC_APPROVE")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "ID_COMMON")
	public Long getApplyId() {
		return applyId;
	}
	public void setApplyId(Long applyId) {
		this.applyId = applyId;
	}
	
	@Column(name = "APP_TYPE")
	public String getApplyType() {
		return applyType;
	}
	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}
	
	@Column(name = "CHECK_ORG_CODE")
	public String getCheckOrgId() {
		return checkOrgId;
	}
	public void setCheckOrgId(String checkOrgId) {
		this.checkOrgId = checkOrgId;
	}
	
	@Column(name = "CHECK_OPR_CODE")
	public String getCheckOprId() {
		return checkOprId;
	}
	public void setCheckOprId(String checkOprId) {
		this.checkOprId = checkOprId;
	}
	
	@Column(name = "CHECK_STATE")
	public String getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}
	
	@Column(name = "CHECK_TIME")
	public Date getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}
	
	@Column(name = "CHECK_EXPL")
	public String getCheckExpl() {
		return checkExpl;
	}
	public void setCheckExpl(String checkExpl) {
		this.checkExpl = checkExpl;
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
}
