package com.tapi.tcs.vc.schema.model;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "VC_TAXPAYER_INVC_AUTH")
public class VcTaxAuth implements Serializable{

	private static final long serialVersionUID = 1L;
	/**纳税人授权信息流水*/
	private Long id;
	/**发票简码代码*/
	private String invoiceShortCode;
	/**发票名称*/
	private String invoiceName;
	/**开具方式代码*/
	private String printTypeCode;
	/**负数发票授信*/
	private String negativeFlag;
	/**简易验旧授信*/
	private String checkOldFlag;
	/**单张发票限额*/
	private BigDecimal limitAmount;
	/**启用日期*/
	private Date startDate;
	/**停用日期*/
	private Date endDate;
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
	
	/**纳税人授权明细*/
	private List<VcTaxAuthDetail> vcTaxAuthDetailList = new ArrayList<VcTaxAuthDetail>();
	
	@SequenceGenerator(name = "generator", allocationSize = 1, sequenceName = "SEQ_VC_TAXPAYER_INVC_AUTH")
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "generator")
	@Column(name = "ID_VC_TAXPAYER_INVC_AUTH")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "INVOICE_SHORT_CODE")
	public String getInvoiceShortCode() {
		return invoiceShortCode;
	}
	public void setInvoiceShortCode(String invoiceShortCode) {
		this.invoiceShortCode = invoiceShortCode;
	}
	@Column(name = "INVOICE_NAME")
	public String getInvoiceName() {
		return invoiceName;
	}
	public void setInvoiceName(String invoiceName) {
		this.invoiceName = invoiceName;
	}
	@Column(name = "UPLOAD_TYPE_CODE")
	public String getPrintTypeCode() {
		return printTypeCode;
	}
	public void setPrintTypeCode(String printTypeCode) {
		this.printTypeCode = printTypeCode;
	}
	@Column(name = "NEGATIVE_FLAG")
	public String getNegativeFlag() {
		return negativeFlag;
	}
	public void setNegativeFlag(String negativeFlag) {
		this.negativeFlag = negativeFlag;
	}
	@Column(name = "CHECK_OLD_FLAG")
	public String getCheckOldFlag() {
		return checkOldFlag;
	}
	public void setCheckOldFlag(String checkOldFlag) {
		this.checkOldFlag = checkOldFlag;
	}
	@Column(name = "LIMIT_AMOUNT")
	public BigDecimal getLimitAmount() {
		return limitAmount;
	}
	public void setLimitAmount(BigDecimal limitAmount) {
		this.limitAmount = limitAmount;
	}
	@Column(name = "START_DATE")
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	@Column(name = "END_DATE")
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "vcTaxAuth")
	public List<VcTaxAuthDetail> getVcTaxAuthDetailList() {
		return vcTaxAuthDetailList;
	}
	public void setVcTaxAuthDetailList(List<VcTaxAuthDetail> vcTaxAuthDetailList) {
		this.vcTaxAuthDetailList = vcTaxAuthDetailList;
	}
}
