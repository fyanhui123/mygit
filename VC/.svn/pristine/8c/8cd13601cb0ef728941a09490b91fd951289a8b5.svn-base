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

@Entity
@Table(name = "VC_TAXPAYER_INFO")
public class VcTaxPayerInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	/**纳税人基本信息流水*/
	private Long id;
	/**纳税人电脑编码*/
	private String computerNo;
	/**纳税人识别号*/
	private String taxPayerId;
	/**纳税人名称*/
	private String taxPayerName;
	/**主管税务机关代码*/
	private String taxOrgCode;
	/**主管税务机关名称*/
	private String taxOrgName;
	/**纳税人状态代码*/
	private String taxPayerStatus;
	/**登记表类型*/
	private String registrationType;
	/**代征标志*/
	private String collectFlag;
	/**所属地市*/
	private String belongCity;
	/**查验网址*/
	private String website;
	/**状态*/
	private String status;
	/** 创建人 */
	private String createdBy;
	/** 创建时间 */
	private Date dateCreated;
	/** 修改人 */
	private String updatedBy;
	/** 修改时间 */
	private Date dateUpdated;
	
	@SequenceGenerator(name = "generator", allocationSize = 1, sequenceName = "SEQ_VC_TAXPAYER_INFO")
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "generator")
	@Column(name = "ID_VC_TAXPAYER_INFO")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "COMPUTER_NO")
	public String getComputerNo() {
		return computerNo;
	}
	public void setComputerNo(String computerNo) {
		this.computerNo = computerNo;
	}
	@Column(name = "TAXPAYER_CODE")
	public String getTaxPayerId() {
		return taxPayerId;
	}
	public void setTaxPayerId(String taxPayerId) {
		this.taxPayerId = taxPayerId;
	}
	@Column(name = "TAXPAYER_NAME")
	public String getTaxPayerName() {
		return taxPayerName;
	}
	public void setTaxPayerName(String taxPayerName) {
		this.taxPayerName = taxPayerName;
	}
	@Column(name = "TAX_ORG_CODE")
	public String getTaxOrgCode() {
		return taxOrgCode;
	}
	public void setTaxOrgCode(String taxOrgCode) {
		this.taxOrgCode = taxOrgCode;
	}
	@Column(name = "TAX_ORG_NAME")
	public String getTaxOrgName() {
		return taxOrgName;
	}
	public void setTaxOrgName(String taxOrgName) {
		this.taxOrgName = taxOrgName;
	}
	@Column(name = "TAXPAYER_STATUS")
	public String getTaxPayerStatus() {
		return taxPayerStatus;
	}
	public void setTaxPayerStatus(String taxPayerStatus) {
		this.taxPayerStatus = taxPayerStatus;
	}
	@Column(name = "REGISTRATION_TYPE")
	public String getRegistrationType() {
		return registrationType;
	}
	public void setRegistrationType(String registrationType) {
		this.registrationType = registrationType;
	}
	@Column(name = "COLLECT_FLAG")
	public String getCollectFlag() {
		return collectFlag;
	}
	public void setCollectFlag(String collectFlag) {
		this.collectFlag = collectFlag;
	}
	@Column(name = "BELONG_CITY")
	public String getBelongCity() {
		return belongCity;
	}
	public void setBelongCity(String belongCity) {
		this.belongCity = belongCity;
	}
	@Column(name = "WEBSITE")
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
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
}
