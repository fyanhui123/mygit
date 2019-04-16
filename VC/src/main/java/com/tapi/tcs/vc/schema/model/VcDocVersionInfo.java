/**
 * 
 */
package com.tapi.tcs.vc.schema.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.ForeignKey;

/**
 * 单证版本信息
 * 
 * @author wanghuajian
 * 
 * @2012-03-22
 * 
 */
@Entity
@Table(name = "VC_DOC_VERSION_INFO")
@SequenceGenerator(name = "SEQ_VC_DOC_VERSION_INFO", sequenceName = "SEQ_VC_DOC_VERSION_INFO", allocationSize = 1)
public class VcDocVersionInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -538322922013032214L;

	/** 单证版本信息流水 */
	@Id
	@Column(name = "ID_VC_DOC_VERSION_INFO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VC_DOC_VERSION_INFO")
	private Long idVcDocVersionInfo;

	/** 单证版本代码 */
	@Column(name = "DOC_VER_CODE")
	private String docVerCode;

	/** 单证版本名称 */
	@Column(name = "DOC_VER_NAME")
	private String docVerName;

	/** 单证种类流水 */
	@Column(name = "ID_VC_DOC_TYPE")
	private Long idVcDocType;

	/** 单证种类代码 */
	/*
	 * @Column(name = "DOC_TYPE_CODE") private String docTypeCode;
	 */

	/** 单证种类VO */
	@ManyToOne(cascade = { CascadeType.ALL }, targetEntity = VcDocType.class)
	@JoinColumn(name = "ID_VC_DOC_TYPE", referencedColumnName = "ID_VC_DOC_TYPE", nullable = true, insertable = false, updatable = false)
	private VcDocType vcDocType;

	/** 单证类型(0-单证，1-发票) */
	/*
	 * @Column(name = "DOC_TYPE") private String docType;
	 */

	/** 单证联次 */
	@Column(name = "DOC_COUNT_CODE")
	private String docCountCode;

	/** 单证样式扫描文件路径 */
	@Column(name = "FILE_PATH")
	private String filePath;

	/** 单证样式扫描文件名称 */
	@Column(name = "FILE_NAME")
	private String fileName;

	/** 流水号生成标志(0:否/1:是) */
	@Column(name = "IS_AUTO_GEN_NO")
	private String isAutoGenNo;
	
	/** 是否走征订流程(0:否/1:是) */
    @Column(name = "IS_ORDER")
    private String isOrder;

	/** 状态(0:停用/1:启用) */
	@Column(name = "STATUS")
	private String status;

	/** 面值 */
	@Column(name = "VALUE")
	private Float value;

	/** 单证印刷流水号规则 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "docVersionInfo", fetch = FetchType.LAZY)
	private List<VcDocPrtNoRule> docPrtNoRuleList;

	/** 创建人所属机构代码 */
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

	// 自定义属性 //
	@Transient
	private String createdUser;

	@Transient
	private String updatedUser;

	@Transient
	private String orgName;
	
	/** 单证管控信息 */
    @Transient
    private List<VcDocVersionInfoMng> docVersionInfoMngList;

	/** 是否是连发票的保单 */
	/*
	 * @Column(name = "IS_INVOICE") private Integer isInvoice;
	 */

	public Long getIdVcDocVersionInfo() {
		return idVcDocVersionInfo;
	}

	public void setIdVcDocVersionInfo(Long idVcDocVersionInfo) {
		this.idVcDocVersionInfo = idVcDocVersionInfo;
	}

	public String getDocVerCode() {
		return docVerCode;
	}

	public void setDocVerCode(String docVerCode) {
		this.docVerCode = docVerCode;
	}

	public String getDocVerName() {
		return docVerName;
	}

	public void setDocVerName(String docVerName) {
		this.docVerName = docVerName;
	}

	public VcDocType getVcDocType() {
		return vcDocType;
	}

	public void setVcDocType(VcDocType vcDocType) {
		this.vcDocType = vcDocType;
	}

	public Long getIdVcDocType() {
		return idVcDocType;
	}

	public void setIdVcDocType(Long idVcDocType) {
		this.idVcDocType = idVcDocType;
	}

	/*
	 * public String getDocType() { return docType; } public void
	 * setDocType(String docType) { this.docType = docType; }
	 */

	public String getDocCountCode() {
		return docCountCode;
	}

	public void setDocCountCode(String docCountCode) {
		this.docCountCode = docCountCode;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getIsAutoGenNo() {
		return isAutoGenNo;
	}

	public void setIsAutoGenNo(String isAutoGenNo) {
		this.isAutoGenNo = isAutoGenNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Float getValue() {
		return value;
	}

	public void setValue(Float value) {
		this.value = value;
	}

	public List<VcDocPrtNoRule> getDocPrtNoRuleList() {
		return docPrtNoRuleList;
	}

	public void setDocPrtNoRuleList(List<VcDocPrtNoRule> docPrtNoRuleList) {
		this.docPrtNoRuleList = docPrtNoRuleList;
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

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

    public String getIsOrder() {
        return isOrder;
    }

    public void setIsOrder(String isOrder) {
        this.isOrder = isOrder;
    }

    public List<VcDocVersionInfoMng> getDocVersionInfoMngList() {
        return docVersionInfoMngList;
    }

    public void setDocVersionInfoMngList(List<VcDocVersionInfoMng> docVersionInfoMngList) {
        this.docVersionInfoMngList = docVersionInfoMngList;
    }

	/*
	 * public void setIsInvoice(Integer isInvoice) { this.isInvoice = isInvoice;
	 * }
	 * 
	 * public Integer getIsInvoice() { return isInvoice; }
	 */

}
