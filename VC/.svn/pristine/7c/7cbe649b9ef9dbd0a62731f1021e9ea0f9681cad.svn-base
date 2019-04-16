/**
 * 
 */
package com.tapi.tcs.vc.schema.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * 表VC_DOC_INSU_KIND对应实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="VC_DOC_INSU_KIND")
@SequenceGenerator(name = "SEQ_VC_DOC_INSU_KIND", sequenceName = "SEQ_VC_DOC_INSU_KIND", allocationSize = 1)
public class VcDocInsuKind implements Serializable{

	private static final long serialVersionUID = 1461092026715031998L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VC_DOC_INSU_KIND")
	@Column(name="ID_VC_DOC_INSU_KIND")
	private Long idVcDocInsuKind;					//归属险种流水	
	
	@Column(name="INSU_KIND_CODE")
	private String insuKindCode;		//归属险种代码	
	
	@Column(name="INSU_KIND_NAME")
	private String insuKindName;		//归属险种名称	
	
	@Column(name="ID_VC_DOC_INSU_TYPE")
	private Long  idVcDocInsuType;
	

	@Column(name="STATUS")
	private String status;              //效力状态(0失效/1有效)
	
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
	 * 对应核心系统产品ID
	 */
	@Column(name="PRODUCT_ID")
	private Long productId;
	
	/**
	 * 对应核心系统产品代码
	 */
	@Column(name="PRODUCT_CODE")
	private String productCode;
	
	/**
	 * 对应核心系统产品名称
	 */
	@Column(name="PRODUCT_NAME")
	private String productName;
	//add by chy 2013-05-29 归属险种表增加与老系统的映射关系 end
	
	@ManyToOne(cascade = {CascadeType.ALL}, targetEntity = VcDocInsuType.class)
	@JoinColumn(name="ID_VC_DOC_INSU_TYPE", referencedColumnName="ID_VC_DOC_INSU_TYPE", nullable=true, insertable=false, updatable=false)
	private VcDocInsuType insuType;		    //归属险类

	public Long getIdVcDocInsuKind() {
		return idVcDocInsuKind;
	}

	public void setIdVcDocInsuKind(Long idVcDocInsuKind) {
		this.idVcDocInsuKind = idVcDocInsuKind;
	}

	public String getInsuKindCode() {
		return insuKindCode;
	}

	public void setInsuKindCode(String insuKindCode) {
		this.insuKindCode = insuKindCode;
	}

	public String getInsuKindName() {
		return insuKindName;
	}

	public void setInsuKindName(String insuKindName) {
		this.insuKindName = insuKindName;
	}

	public Long getIdVcDocInsuType() {
		return idVcDocInsuType;
	}

	public void setIdVcDocInsuType(Long idVcDocInsuType) {
		this.idVcDocInsuType = idVcDocInsuType;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public VcDocInsuType getInsuType() {
		return insuType;
	}

	public void setInsuType(VcDocInsuType insuType) {
		this.insuType = insuType;
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

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
}
