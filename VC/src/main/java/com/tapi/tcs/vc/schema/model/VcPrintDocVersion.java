/**
 * 
 */
package com.tapi.tcs.vc.schema.model;

import java.io.Serializable;
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

/**
 * 承印单证版本
 * 
 * @author wanghuajian
 * 
 */
@Entity
@Table(name = "VC_PRINT_DOC_VERSION")
@SequenceGenerator(name = "SEQ_VC_PRINT_DOC_VERSION", sequenceName = "SEQ_VC_PRINT_DOC_VERSION", allocationSize = 1)
public class VcPrintDocVersion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -538322922013032115L;

	/** 承印单证版本流水 */
	@Id
	@Column(name = "ID_VC_PRINT_DOC_VERSION")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VC_PRINT_DOC_VERSION")
	private Long idVcPrintDocVersion;

	/** 印刷厂流水 */	
	@Column(name = "ID_VC_PRINTERY" ,insertable = false, updatable = false)
	private Long idVcPrintery;
	
	/** 印刷厂 */
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_VC_PRINTERY", referencedColumnName="ID_VC_PRINTERY", nullable = true)
	private VcPrintery vcPrintery;

	/** 单证版本名称 *//*
	@Column(name = "DOC_NAME")
	private String docName;*/

	/** 单证版本代码 */
	@Column(name = "DOC_VER_CODE")
	private String docVerCode;

	
		
	/** 单证类型信息表流水 *//*	
	@Column(name = "ID_VC_DOC_VERSION_INFO")
	private Long idVcDocVersionInfo;*/
	
	/** 单价 */
	@Column(name = "UNIT_PRICE")
	private Float unitPrice;

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
	
	public Long getIdVcPrintDocVersion() {
		return idVcPrintDocVersion;
	}

	public void setIdVcPrintDocVersion(Long idVcPrintDocVersion) {
		this.idVcPrintDocVersion = idVcPrintDocVersion;
	}

	public Long getIdVcPrintery() {
		return idVcPrintery;
	}

	public void setIdVcPrintery(Long idVcPrintery) {
		this.idVcPrintery = idVcPrintery;
	}

	//@ManyToOne(fetch = FetchType.LAZY)
	//@JoinColumn(name = "FK_ID_VC_PRINTERY", referencedColumnName="ID_VC_PRINTERY", nullable = true)
	public VcPrintery getVcPrintery() {
		return vcPrintery;
	}

	public void setVcPrintery(VcPrintery vcPrintery) {
		this.vcPrintery = vcPrintery;
	}

	/*public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}*/

	public String getDocVerCode() {
		return docVerCode;
	}

	public void setDocVerCode(String docVerCode) {
		this.docVerCode = docVerCode;
	}
	
	

	public Float getUnitPrice() {
		return unitPrice;
	}

/*	public Long getIdVcDocVersionInfo() {
		return idVcDocVersionInfo;
	}

	public void setIdVcDocVersionInfo(Long idVcDocVersionInfo) {
		this.idVcDocVersionInfo = idVcDocVersionInfo;
	}*/

	public void setUnitPrice(Float unitPrice) {
		this.unitPrice = unitPrice;
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
