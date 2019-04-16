package com.tapi.tcs.vc.schema.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "VC_INVOICE_REPORT_DET_HLJ")
@SequenceGenerator(name = "SEQ_VC_INVOICE_REPORT_DET_HLJ", sequenceName = "SEQ_VC_INVOICE_REPORT_DET_HLJ", allocationSize = 1)
public class VcInvoiceReportDetHlj implements Serializable{

	private static final long serialVersionUID = 3781728290309588128L;
	/**领用存表明细流水*/
	private Long id;
	/**领用存表流水*/
	private Long idVcInvoiceReportHlj;
	/**明细类型*/
	private String detailType;
	/**明细序号*/
	private Integer detailOrder;
	/**发票代码*/
	private String invoiceCode;
	/**发票起号*/
	private String startNum;
	/**发票止号*/
	private String endNum;
	/**总份数*/
	private Integer totalNum;
	/**开具分数*/
	private Integer printNum;
	/**作废分数*/
	private Integer cancelNum;
	/**领购日期*/
	private Date buyDate;
	/**使用日期起*/
	private Date startDate;
	/**使用日期止*/
	private Date endDate;
	/**开具金额*/
	private BigDecimal amount;
	/**单证类型代码*/
	private String docVerCode;
	/** 创建人 */
	private String createdBy;
	/** 创建时间 */
	private Date dateCreated;
	/** 修改人 */
	private String updatedBy;
	/** 修改时间 */
	private Date dateUpdated;
	/**领用存报表*/
	private VcInvoiceReportHlj vcInvoiceReportHlj;
	
	/**机构信息*/
	private String companyCode;
	
	/**机构信息*/
	private PubCompany pubCompany;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VC_INVOICE_REPORT_DET_HLJ")
	@Column(name="ID_VC_INVOICE_REPORT_DET_HLJ")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name="ID_VC_INVOICE_REPORT_HLJ", insertable = false, updatable = false)
	public Long getIdVcInvoiceReportHlj() {
		return idVcInvoiceReportHlj;
	}
	public void setIdVcInvoiceReportHlj(Long idVcInvoiceReportHlj) {
		this.idVcInvoiceReportHlj = idVcInvoiceReportHlj;
	}
	@Column(name="DETAIL_TYPE")
	public String getDetailType() {
		return detailType;
	}
	public void setDetailType(String detailType) {
		this.detailType = detailType;
	}
	@Column(name="DETAIL_ORDER")
	public Integer getDetailOrder() {
		return detailOrder;
	}
	public void setDetailOrder(Integer detailOrder) {
		this.detailOrder = detailOrder;
	}
	@Column(name="INVOICE_CODE")
	public String getInvoiceCode() {
		return invoiceCode;
	}
	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}
	@Column(name="START_NUM")
	public String getStartNum() {
		return startNum;
	}
	public void setStartNum(String startNum) {
		this.startNum = startNum;
	}
	@Column(name="END_NUM")
	public String getEndNum() {
		return endNum;
	}
	public void setEndNum(String endNum) {
		this.endNum = endNum;
	}
	@Column(name="TOTAL_NUM")
	public Integer getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	@Column(name="PRINT_NUM")
	public Integer getPrintNum() {
		return printNum;
	}
	public void setPrintNum(Integer printNum) {
		this.printNum = printNum;
	}
	@Column(name="CANCEL_NUM")
	public Integer getCancelNum() {
		return cancelNum;
	}
	public void setCancelNum(Integer cancelNum) {
		this.cancelNum = cancelNum;
	}
	@Column(name="BUY_DATE")
	public Date getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}
	@Column(name="START_DATE")
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	@Column(name="END_DATE")
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	@Column(name="AMOUNT")
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	@Column(name="DOC_VER_CODE")
	public String getDocVerCode() {
		return docVerCode;
	}
	public void setDocVerCode(String docVerCode) {
		this.docVerCode = docVerCode;
	}
	@Column(name="CREATED_BY")
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	@Column(name="DATE_CREATED")
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	@Column(name="UPDATED_BY")
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	@Column(name="DATE_UPDATED")
	public Date getDateUpdated() {
		return dateUpdated;
	}
	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_VC_INVOICE_REPORT_HLJ", referencedColumnName="ID_VC_INVOICE_REPORT_HLJ", nullable = true)
	public VcInvoiceReportHlj getVcInvoiceReportHlj() {
		return vcInvoiceReportHlj;
	}
	public void setVcInvoiceReportHlj(VcInvoiceReportHlj vcInvoiceReportHlj) {
		this.vcInvoiceReportHlj = vcInvoiceReportHlj;
	}
	
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	@Column(name="COMPANYCODE")
	public String getCompanyCode() {
		return companyCode;
	}
	
	
	public void setPubCompany(PubCompany pubCompany) {
		this.pubCompany = pubCompany;
	}
	@OneToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY,targetEntity=PubCompany.class)
	@JoinColumn(name = "COMPANYCODE",referencedColumnName = "COMPANYCODE",insertable=false,updatable=false)
	public PubCompany getPubCompany() {
		return pubCompany;
	}
}
