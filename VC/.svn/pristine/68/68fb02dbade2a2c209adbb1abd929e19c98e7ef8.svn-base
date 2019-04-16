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
 * 单证险种关联表
 * 
 * @author wanghuajian
 * 
 * @since2013-03-22
 * 
 */
@Entity
@Table(name = "VC_DOC_REL_INSU_KIND")
@SequenceGenerator(name = "SEQ_VC_DOC_REL_INSU_KIND", sequenceName = "SEQ_VC_DOC_REL_INSU_KIND", allocationSize = 1)
public class VcDocRelInsuKind implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -538322922013032115L;

	/** 单证险种关联表流水 */
	@Id
	@Column(name = "ID_VC_DOC_REL_INSU_KIND")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VC_DOC_REL_INSU_KIND")
	private Long idVcDocRelInsuKind;

	/** 单证版本代码 *//*
	@Column(name = "DOC_VER_CODE")
	private String docVerCode;*/
	/** 单证类型信息表流水 */	
	@Column(name = "ID_VC_DOC_VERSION_INFO")
	private Long idVcDocVersionInfo;

	/** 归属险种代码 */
	@Column(name = "INSU_KIND_CODE")
	private String insuKindCode;

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

	public Long getIdVcDocRelInsuKind() {
		return idVcDocRelInsuKind;
	}

	public void setIdVcDocRelInsuKind(Long idVcDocRelInsuKind) {
		this.idVcDocRelInsuKind = idVcDocRelInsuKind;
	}

	/*public String getDocVerCode() {
		return docVerCode;
	}

	public void setDocVerCode(String docVerCode) {
		this.docVerCode = docVerCode;
	}*/
	

	public String getInsuKindCode() {
		return insuKindCode;
	}

	public Long getIdVcDocVersionInfo() {
		return idVcDocVersionInfo;
	}

	public void setIdVcDocVersionInfo(Long idVcDocVersionInfo) {
		this.idVcDocVersionInfo = idVcDocVersionInfo;
	}

	public void setInsuKindCode(String insuKindCode) {
		this.insuKindCode = insuKindCode;
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
