package com.tapi.tcs.vc.schema.model;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "VC_TAXPAYER_INVC_AUTH_DET")
public class VcTaxAuthDetail implements Serializable{

	private static final long serialVersionUID = 1L;
	/**纳税人授权明细流水*/
	private Long id;
	/**适用行业代码*/
	private String industryCode;
	/**适用行业名称*/
	private String industryName;
	/**适用项目代码*/
	private String itemCode;
	/**适用项目名称*/
	private String itemName;
	/**对应套打样式代码*/
	private String printTemplateCode;
	/**对应套打样式名称*/
	private String printTemplateName;
	/** 创建人 */
	private String createdBy;
	/** 创建时间 */
	private Date dateCreated;
	/** 修改人 */
	private String updatedBy;
	/** 修改时间 */
	private Date dateUpdated;
	/**纳税人授权信息*/
	private VcTaxAuth vcTaxAuth;
	
	@SequenceGenerator(name = "generator", allocationSize = 1, sequenceName = "SEQ_VC_TAXPAYER_INVC_AUTH_DET")
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "generator")
	@Column(name = "ID_VC_TAXPAYER_INVC_AUTH_DET")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_VC_TAXPAYER_INVC_AUTH", referencedColumnName="ID_VC_TAXPAYER_INVC_AUTH", nullable = true)
	public VcTaxAuth getVcTaxAuth() {
		return vcTaxAuth;
	}
	public void setVcTaxAuth(VcTaxAuth vcTaxAuth) {
		this.vcTaxAuth = vcTaxAuth;
	}
	@Column(name = "INDUSTRY_CODE")
	public String getIndustryCode() {
		return industryCode;
	}
	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}
	@Column(name = "INDUSTRY_NAME")
	public String getIndustryName() {
		return industryName;
	}
	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}
	@Column(name = "ITEM_CODE")
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	@Column(name = "ITEM_NAME")
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	@Column(name = "PRINT_TEMPLATE_CODE")
	public String getPrintTemplateCode() {
		return printTemplateCode;
	}
	public void setPrintTemplateCode(String printTemplateCode) {
		this.printTemplateCode = printTemplateCode;
	}
	@Column(name = "PRINT_TEMPLATE_NAME")
	public String getPrintTemplateName() {
		return printTemplateName;
	}
	public void setPrintTemplateName(String printTemplateName) {
		this.printTemplateName = printTemplateName;
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
