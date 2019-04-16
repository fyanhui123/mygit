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
 * 险种定额关系表
 * <p>
 * Date 2013-10-14
 * </p>
 * <p>
 * Module：基础信息
 * </p>
 * <p>
 * Description：
 * </p>
 * <p>
 * Remark：
 * </p>
 * @author chy
 * @version 1.0
 */
@Entity
@Table(name = "VC_RISK_RATION")
public class VcRiskRation implements Serializable{

	private static final long serialVersionUID = -4145514778320197992L;
	/**险种定额关系表流水*/
	private Long id;
	/**险种代码*/
	private String riskCode;
	/**定额类型代码*/
	private String rationType;
	/**定额类型名称*/
	private String rationName;
	/**有效标志*/
	private String status;
	/**预留字段*/
	private String col1;
	/**单证类型流水*/
	private Long idVcDocVersionInfo;
	/**创建人*/
	private String createdBy;
	/**创建时间*/
	private Date dateCreated;
	/**修改人*/
	private String updatedBy;
	/**修改时间*/
	private Date dateUpdated;
	
	@SequenceGenerator(name = "generator", allocationSize = 1, sequenceName = "SEQ_VC_ORDER_LAUNCH")
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "generator")
	@Column(name = "ID_VC_RISK_RATION")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "RISK_CODE")
	public String getRiskCode() {
		return riskCode;
	}
	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}
	@Column(name = "RATION_TYPE")
	public String getRationType() {
		return rationType;
	}
	public void setRationType(String rationType) {
		this.rationType = rationType;
	}
	@Column(name = "RATION_NAME")
	public String getRationName() {
		return rationName;
	}
	public void setRationName(String rationName) {
		this.rationName = rationName;
	}
	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name = "COL1")
	public String getCol1() {
		return col1;
	}
	public void setCol1(String col1) {
		this.col1 = col1;
	}
	@Column(name = "ID_VC_DOC_VERSION_INFO")
	public Long getIdVcDocVersionInfo() {
		return idVcDocVersionInfo;
	}
	public void setIdVcDocVersionInfo(Long idVcDocVersionInfo) {
		this.idVcDocVersionInfo = idVcDocVersionInfo;
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
