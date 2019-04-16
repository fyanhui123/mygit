/**
 * 
 */
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
 * 单证适用地区关联表
 * 
 * @author wanghuajian
 * 
 * @since2013-03-22
 * 
 */
@Entity
@Table(name = "VC_DOC_REL_AREA")
@SequenceGenerator(name = "SEQ_VC_DOC_REL_AREA", sequenceName = "SEQ_VC_DOC_REL_AREA", allocationSize = 1)
public class VcDocRelArea implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -538322922013032115L;

	/** 单证适用地区关联表流水 */
	@Id
	@Column(name = "ID_VC_DOC_REL_AREA")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VC_DOC_REL_AREA")
	private Long idVcDocRelArea;

	/** 单证版本代码 */
	/*
	 * @Column(name = "DOC_VER_CODE") private String docVerCode;
	 */
	/** 单证类型信息表流水 */
	@Column(name = "ID_VC_DOC_VERSION_INFO")
	private Long idVcDocVersionInfo;

	/** 适用机构代码 */
	@Column(name = "ORG_CODE")
	private String orgCode;

	/** 创建人 */
	@Column(name = "created_by")
	private String createdBy;

	/** 创建时间 */
	@Column(name = "date_created")
	private Date dateCreated;

	/** 修改人 */
	@Column(name = "updated_by")
	private String updatedBy;

	/** 修改时间 */
	@Column(name = "date_updated")
	private Date dateUpdated;


	public Long getIdVcDocRelArea() {
		return idVcDocRelArea;
	}

	public void setIdVcDocRelArea(Long idVcDocRelArea) {
		this.idVcDocRelArea = idVcDocRelArea;
	}

	/*
	 * public String getDocVerCode() { return docVerCode; }
	 * 
	 * public void setDocVerCode(String docVerCode) { this.docVerCode =
	 * docVerCode; }
	 */
	public Long getIdVcDocVersionInfo() {
		return idVcDocVersionInfo;
	}

	public void setIdVcDocVersionInfo(Long idVcDocVersionInfo) {
		this.idVcDocVersionInfo = idVcDocVersionInfo;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	

}
