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
 * 打印配置表
 * <p>
 * Date 2013-05-31
 * </p>
 * <p>
 * Module：
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
@Table(name="VC_DOC_PRINT_SET")
public class VcDocPrintSet implements Serializable{

	private static final long serialVersionUID = 1L;

	/**单证打印配置表流水*/
	private Long id;
	
	/**单证类型信息表流水*/
	private Long idVcDocVersionInfo;
	
	/**所在地区代码*/
	private String orgCode;
	
	/**险种代码*/
	private String insuKindCode;
	
	/**打印模板编码*/
	private String templateCode;
	
	/**打印模板路径*/
	private String printTemplate;
	
	/**有效标志*/
	private String status;
	
	/**创建人*/
	private String createdBy;
	
	/**创建时间*/
	private Date dateCreated;
	
	/**修改人*/
	private String updatedBy;
	
	/**修改时间*/
	private Date dateUpdated;
	
	/**是否是连发票的保单*/
	private Integer isInvoice;

	@SequenceGenerator(name = "generator", allocationSize = 1, sequenceName = "SEQ_VC_DOC_PRINT_SET")
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "generator")
	@Column(name = "ID_VC_DOC_PRINT_SET")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "ID_VC_DOC_VERSION_INFO")
	public Long getIdVcDocVersionInfo() {
		return idVcDocVersionInfo;
	}

	public void setIdVcDocVersionInfo(Long idVcDocVersionInfo) {
		this.idVcDocVersionInfo = idVcDocVersionInfo;
	}

	@Column(name = "ORG_CODE")
	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	@Column(name = "INSU_KIND_CODE")
	public String getInsuKindCode() {
		return insuKindCode;
	}

	public void setInsuKindCode(String insuKindCode) {
		this.insuKindCode = insuKindCode;
	}

	@Column(name = "TEMPLATE_CODE")
	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	@Column(name = "PRINT_TEMPLATE")
	public String getPrintTemplate() {
		return printTemplate;
	}

	public void setPrintTemplate(String printTemplate) {
		this.printTemplate = printTemplate;
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

	@Column(name = "IS_INVOICE")
	public Integer getIsInvoice() {
		return isInvoice;
	}

	public void setIsInvoice(Integer isInvoice) {
		this.isInvoice = isInvoice;
	}
}