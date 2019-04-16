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

/**
 * 常量配置表 VC_CONSTANT_CONFIG 对应实体类
 * 
 * @author wanghuajian
 * @date 2013-04-28 16:07
 * 
 */
@Entity
@Table(name = "VC_CONSTANT_CONFIG")
@SequenceGenerator(name = "SEQ_VC_CONSTANT_CONFIG", sequenceName = "SEQ_VC_CONSTANT_CONFIG", allocationSize = 1)
public class VcConstantConfig implements Serializable {

	private static final long serialVersionUID = 1894785963973356594L;

	/** 常量配置表流水 */
	private int idVcConstantConfig;
	
	/** 级别等级 */
	private int levelNo;
	/** 常量代码▲ */
	private String constantCode;
	/** 常量值 */
	private String constantValue;
	/** 录入日期 */
	private Date inputDate;
	/** 生效日期 */
	private Date validDate;
	/** 失效日期 */
	private Date invalidDate;
	/** 备注 */
	private String remark;
	/** 有效标志▲ */
	private String status;
	/** 创建人 */
	private String createdBy;
	/** 创建时间 */
	private Date dateCreated;
	/** 修改人 */
	private String updatedBy;
	/** 修改时间 */
	private Date dateUpdated;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VC_CONSTANT_CONFIG")
	@Column(name = "ID_VC_CONSTANT_CONFIG")
	public int getIdVcConstantConfig() {
		return idVcConstantConfig;
	}

	public void setIdVcConstantConfig(int idVcConstantConfig) {
		this.idVcConstantConfig = idVcConstantConfig;
	}

	@Column(name = "CONSTANT_CODE")
	public String getConstantCode() {
		return constantCode;
	}

	public void setConstantCode(String constantCode) {
		this.constantCode = constantCode;
	}

	@Column(name = "CONSTANT_VALUE")
	public String getConstantValue() {
		return constantValue;
	}

	public void setConstantValue(String constantValue) {
		this.constantValue = constantValue;
	}

	@Column(name = "INPUT_DATE")
	public Date getInputDate() {
		return inputDate;
	}

	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}

	@Column(name = "VALID_DATE")
	public Date getValidDate() {
		return validDate;
	}

	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}

	@Column(name = "INVALID_DATE")
	public Date getInvalidDate() {
		return invalidDate;
	}

	public void setInvalidDate(Date invalidDate) {
		this.invalidDate = invalidDate;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "LEVEL_NO")
	public int getLevelNo() {
		return levelNo;
	}

	public void setLevelNo(int levelNo) {
		this.levelNo = levelNo;
	}

	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
