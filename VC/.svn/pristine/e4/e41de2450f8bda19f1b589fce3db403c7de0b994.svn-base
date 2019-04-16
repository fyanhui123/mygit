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
import javax.persistence.Transient;

/**
 * 单证种类维护
 * 
 * @author wanghuajian
 * 
 */
@Entity
@Table(name = "VC_DOC_TYPE")
@SequenceGenerator(name = "SEQ_VC_DOC_TYPE", sequenceName = "SEQ_VC_DOC_TYPE", allocationSize = 1)
public class VcDocType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -538322924222380911L;

	/** 单证种类流水 */
	@Id
	@Column(name = "ID_VC_DOC_TYPE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VC_DOC_TYPE")
	private Long idVcDocType;

	/** 单证种类代码 */
	@Column(name = "DOC_TYPE_CODE")
	private String docTypeCode;

	/** 单证种类名称 */
	@Column(name = "DOC_TYPE_NAME")
	private String docTypeName;

	/** 类型(0-单证，1-发票) */
	@Column(name = "DOC_TYPE")
	private String docType;

	/** 有效标志(0无效/1有效) */
	@Column(name = "STATUS")
	private String status;

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
	
	
	// 自定义属性 //
	@Transient
	private String createdUser;
	
	@Transient
	private String updatedUser;
	
	/** 单证种类名称（完全匹配查询使用） */
    @Transient
    private String docTypeNameEQ;
	
	
	

	public Long getIdVcDocType() {
		return idVcDocType;
	}

	public void setIdVcDocType(Long idVcDocType) {
		this.idVcDocType = idVcDocType;
	}

	public String getDocTypeCode() {
		return docTypeCode;
	}

	public void setDocTypeCode(String docTypeCode) {
		this.docTypeCode = docTypeCode;
	}

	public String getDocTypeName() {
		return docTypeName;
	}

	public void setDocTypeName(String docTypeName) {
		this.docTypeName = docTypeName;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
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

	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public String getUpdatedUser() {
		return updatedUser;
	}

	public void setUpdatedUser(String updatedUser) {
		this.updatedUser = updatedUser;
	}

    /**
     * @return the docTypeNameEQ
     */
    public String getDocTypeNameEQ() {
        return docTypeNameEQ;
    }

    /**
     * @param docTypeNameEQ the docTypeNameEQ to set
     */
    public void setDocTypeNameEQ(String docTypeNameEQ) {
        this.docTypeNameEQ = docTypeNameEQ;
    }

	
}
