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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 表VC_DOC_INSU_TYPE对应Model
 * @author Administrator
 *
 */
@Entity
@Table(name="VC_DOC_INSU_TYPE")
@SequenceGenerator(name = "SEQ_VC_DOC_INSU_TYPE", sequenceName = "SEQ_VC_DOC_INSU_TYPE", allocationSize = 1)
public class VcDocInsuType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1201584039633041348L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VC_DOC_INSU_TYPE")
	@Column(name="ID_VC_DOC_INSU_TYPE")
	private Long  idVcDocInsuType;   //	ID_VC_DOC_INSU_TYPE 归属险类流水
	
	@Column(name="INSU_TYPE_CODE")
	private String insuTypeCode;	//	UQ_INSU_TYPE_CODE 归属险类代码
	
	@Column(name="INSU_TYPE_NAME")
	private String insuTypeName;	//	UQ_INSU_TYPE_NAME 归属险类名称
	
	@Column(name="STATUS")
	private String status;			//	STATUS 效力状态(0失效/1有效)
	
	@Column(name="CREATED_BY")
	private String createdBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_CREATED")
	private Date dateCreated;
	
	@Column(name="UPDATED_BY")
	private String updatedBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_UPDATED")
	private Date dateUpdated;
	
	//add by chy 2013-05-29 归属险种表增加与老系统的映射关系 begin
	/**
	 * 对应核心系统产品大类ID
	 */
	@Column(name="CATEGORY_ID")
	private Long categoryId;
	
	/**
	 * 对应核心系统产品大类代码
	 */
	@Column(name="CATEGORY_CODE")
	private String categoryCode;
	
	/**
	 * 对应核心系统产品大类名称
	 */
	@Column(name="CATEGORY_NAME")
	private String categoryName;
	//add by chy 2013-05-29 归属险种表增加与老系统的映射关系 end

	public Long getIdVcDocInsuType() {
		return idVcDocInsuType;
	}

	public void setIdVcDocInsuType(Long idVcDocInsuType) {
		this.idVcDocInsuType = idVcDocInsuType;
	}

	public String getInsuTypeCode() {
		return insuTypeCode;
	}

	public void setInsuTypeCode(String insuTypeCode) {
		this.insuTypeCode = insuTypeCode;
	}

	public String getInsuTypeName() {
		return insuTypeName;
	}

	public void setInsuTypeName(String insuTypeName) {
		this.insuTypeName = insuTypeName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
}
