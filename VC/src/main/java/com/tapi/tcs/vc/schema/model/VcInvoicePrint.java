package com.tapi.tcs.vc.schema.model;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.math.BigDecimal;
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

/**
 * 发票开具信息表
 * <p>
 * Date 2013-06-05
 * </p>
 * <p>
 * Module：发票核销
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
@Table(name = "VC_INVOICE_PRINT")
public class VcInvoicePrint implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**发票开具信息流水*/
	private Long id;
	/**单证类型代码*/
	private String docVerCode;
	/**开票机构*/
	private String orgCode;
	/**保单号*/
	private String	policyNo      ;
	/**批单号*/
	private String	endorseNo     ;
	/**发票代码*/
	private String	invoiceCode;
	/**发票名称*/
	private String	invoiceName;
	/**发票号码*/
	private String	invoiceNo;
	/**开票日期*/
	private Date		printDate;
	/**开票金额*/
	private BigDecimal	amount;
	/**付款方名称*/
	private String	payerName;
	/**付款方号码*/
	private String	payerCode;
	/**收款方名称*/
	private String	recipientName;
	/**收款方号码*/
	private String	recipientCode;
	/**纳税人名称*/
	private String	taxpayerName;
	/**纳税人识别号*/
	private String	taxpayerCode;
	/**开票员名称*/
	private String	billerName;
	/**收款人名称*/
	private String	recipientOpr;
	/**冲红的发票代码*/
	private String	counteractInvoiceCode  ;
	/**冲红的发票号码*/
	private String	counteractInvoiceNo    ;
	/**被冲红的发票代码*/
	private String	counteractedInvoiceCode;
	/**被冲红的发票号码*/
	private String	counteractedInvoiceNo  ;
	/**作废人名称*/
	private String	canceledOpr;
	/**作废人代码*/
	private String    canceledOprCode;
	/**作废时间*/
	private Date	canceldDate;
	/**备注*/
	private String	remark;
	/**操作时间*/
	private Date	operatorDate;
	/**发票开具类型：1-正常;2-负数;3-作废*/
	private String	printKind;
	/**纳税人主管税务机关代码*/
	private String	taxOrgCode;
	/**管理编码*/
	private String	manageCode;
	/**结算项目*/
	private String	settleItem;
	/**所属期*/
	private String	belongStage;
	/**机打代码*/
	private String	machinePrintedCode;
	/**机打号码*/
	private String	machinePrintedNo  ;
	/**起始时间*/
	private Date	startDate;
	/**结束时间*/
	private Date	endDate  ;
	/**校验码*/
	private String	checkNum;
	/**税额*/
	private BigDecimal	taxAmount;
	/**税率*/
	private BigDecimal	taxRate  ;
	/**大写金额*/
	private String	upperAmount ;
	/**行业分类对应的代码*/
	private String	industryCode;
	/**行业分类对应的名称*/
	private String	industryName;
	/**套打样式代码*/
	private String	printTypeCode;
	/**银行账号*/
	private String	account        ;
	/**开户银行    */
	private String	bank           ;
	/**工商登记证号*/
	private String	registration   ;
	/**征管行业    */
	private String	zgIndustry     ;
	/**银行编码    */
	private String	bankCode       ;
	/**发票种类代码*/
	private String	invoiceKindCode;
	/**发票种类名称*/
	private String	invoiceKindName;
	/**发票类型    */
	private String	invoiceType    ;
	/**项目摘要*/
	private String	items          ;
	/**状态*/
	private String status;
	/**是否已上传平台*/
	private String isUploadPlat;
	/**纳税人电脑编码*/
	private String computerNo;
	/**创建人*/
	private String createdBy;
	/**创建时间*/
	private Date dateCreated;
	/**修改人*/
	private String updatedBy;
	/**修改时间*/
	private Date dateUpdated;
	
	/**原始发票开具信息表流水（如:FIN_INVOICE_PRINT.ID_VC_INVOICE_PRINT）*/
    private String origionId;
	
	/**发票扩展信息*/
	private List<VcInvoicePrintExt> vcInvoicePrintExtList;
	/**发票明细信息*/
	private List<VcInvoicePrintDet> vcInvoicePrintDetList;
	
	@SequenceGenerator(name = "generator", allocationSize = 1, sequenceName = "SEQ_VC_INVOICE_PRINT")
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "generator")
	@Column(name = "ID_VC_INVOICE_PRINT")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name="DOC_VER_CODE")
	public String getDocVerCode() {
		return docVerCode;
	}
	public void setDocVerCode(String docVerCode) {
		this.docVerCode = docVerCode;
	}
	@Column(name="ORG_CODE")
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	@Column(name="POLICY_NO")
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	@Column(name="ENDORSE_NO")
	public String getEndorseNo() {
		return endorseNo;
	}
	public void setEndorseNo(String endorseNo) {
		this.endorseNo = endorseNo;
	}
	@Column(name="INVOICE_CODE")
	public String getInvoiceCode() {
		return invoiceCode;
	}
	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}
	@Column(name="INVOICE_NAME")
	public String getInvoiceName() {
		return invoiceName;
	}
	public void setInvoiceName(String invoiceName) {
		this.invoiceName = invoiceName;
	}
	@Column(name="INVOICE_NO")
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	@Column(name="PRINT_DATE")
	public Date getPrintDate() {
		return printDate;
	}
	public void setPrintDate(Date printDate) {
		this.printDate = printDate;
	}
	@Column(name="AMOUNT")
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	@Column(name="PAYER_NAME")
	public String getPayerName() {
		return payerName;
	}
	public void setPayerName(String payerName) {
		this.payerName = payerName;
	}
	@Column(name="PAYER_CODE")
	public String getPayerCode() {
		return payerCode;
	}
	public void setPayerCode(String payerCode) {
		this.payerCode = payerCode;
	}
	@Column(name="RECIPIENT_NAME")
	public String getRecipientName() {
		return recipientName;
	}
	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}
	@Column(name="RECIPIENT_CODE")
	public String getRecipientCode() {
		return recipientCode;
	}
	public void setRecipientCode(String recipientCode) {
		this.recipientCode = recipientCode;
	}
	@Column(name="TAXPAYER_NAME")
	public String getTaxpayerName() {
		return taxpayerName;
	}
	public void setTaxpayerName(String taxpayerName) {
		this.taxpayerName = taxpayerName;
	}
	@Column(name="TAXPAYER_CODE")
	public String getTaxpayerCode() {
		return taxpayerCode;
	}
	public void setTaxpayerCode(String taxpayerCode) {
		this.taxpayerCode = taxpayerCode;
	}
	@Column(name="BILLER_NAME")
	public String getBillerName() {
		return billerName;
	}
	public void setBillerName(String billerName) {
		this.billerName = billerName;
	}
	@Column(name="RECIPIENT_OPR")
	public String getRecipientOpr() {
		return recipientOpr;
	}
	public void setRecipientOpr(String recipientOpr) {
		this.recipientOpr = recipientOpr;
	}
	@Column(name="COUNTERACT_INVOICE_CODE")
	public String getCounteractInvoiceCode() {
		return counteractInvoiceCode;
	}
	public void setCounteractInvoiceCode(String counteractInvoiceCode) {
		this.counteractInvoiceCode = counteractInvoiceCode;
	}
	@Column(name="COUNTERACT_INVOICE_NO")
	public String getCounteractInvoiceNo() {
		return counteractInvoiceNo;
	}
	public void setCounteractInvoiceNo(String counteractInvoiceNo) {
		this.counteractInvoiceNo = counteractInvoiceNo;
	}
	@Column(name="COUNTERACTED_INVOICE_CODE")
	public String getCounteractedInvoiceCode() {
		return counteractedInvoiceCode;
	}
	public void setCounteractedInvoiceCode(String counteractedInvoiceCode) {
		this.counteractedInvoiceCode = counteractedInvoiceCode;
	}
	@Column(name="COUNTERACTED_INVOICE_NO")
	public String getCounteractedInvoiceNo() {
		return counteractedInvoiceNo;
	}
	public void setCounteractedInvoiceNo(String counteractedInvoiceNo) {
		this.counteractedInvoiceNo = counteractedInvoiceNo;
	}
	@Column(name="CANCELED_OPR_NAME")
	public String getCanceledOpr() {
		return canceledOpr;
	}
	public void setCanceledOpr(String canceledOpr) {
		this.canceledOpr = canceledOpr;
	}
	@Column(name="CANCELED_OPR_CODE")
	public String getCanceledOprCode() {
		return canceledOprCode;
	}
	public void setCanceledOprCode(String canceledOprCode) {
		this.canceledOprCode = canceledOprCode;
	}
	@Column(name="CANCELED_DATE")
	public Date getCanceldDate() {
		return canceldDate;
	}
	public void setCanceldDate(Date canceldDate) {
		this.canceldDate = canceldDate;
	}
	@Column(name="REMARK")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name="OPERATE_DATE")
	public Date getOperatorDate() {
		return operatorDate;
	}
	public void setOperatorDate(Date operatorDate) {
		this.operatorDate = operatorDate;
	}
	@Column(name="PRINT_KIND")
	public String getPrintKind() {
		return printKind;
	}
	public void setPrintKind(String printKind) {
		this.printKind = printKind;
	}
	@Column(name="TAX_ORG")
	public String getTaxOrgCode() {
		return taxOrgCode;
	}
	public void setTaxOrgCode(String taxOrgCode) {
		this.taxOrgCode = taxOrgCode;
	}
	@Column(name="MANAGE_CODE")
	public String getManageCode() {
		return manageCode;
	}
	public void setManageCode(String manageCode) {
		this.manageCode = manageCode;
	}
	@Column(name="SETTLE_ITEM")
	public String getSettleItem() {
		return settleItem;
	}
	public void setSettleItem(String settleItem) {
		this.settleItem = settleItem;
	}
	@Column(name="BELONG_STAGE")
	public String getBelongStage() {
		return belongStage;
	}
	public void setBelongStage(String belongStage) {
		this.belongStage = belongStage;
	}
	@Column(name="MACHINE_PRINTED_CODE")
	public String getMachinePrintedCode() {
		return machinePrintedCode;
	}
	public void setMachinePrintedCode(String machinePrintedCode) {
		this.machinePrintedCode = machinePrintedCode;
	}
	@Column(name="MACHINE_PRINTED_NO")
	public String getMachinePrintedNo() {
		return machinePrintedNo;
	}
	public void setMachinePrintedNo(String machinePrintedNo) {
		this.machinePrintedNo = machinePrintedNo;
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
	@Column(name="CHECK_NUM")
	public String getCheckNum() {
		return checkNum;
	}
	public void setCheckNum(String checkNum) {
		this.checkNum = checkNum;
	}
	@Column(name="TAX_AMOUNT")
	public BigDecimal getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}
	@Column(name="TAX_RATE")
	public BigDecimal getTaxRate() {
		return taxRate;
	}
	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}
	@Column(name="UPPER_AMOUNT")
	public String getUpperAmount() {
		return upperAmount;
	}
	public void setUpperAmount(String upperAmount) {
		this.upperAmount = upperAmount;
	}
	@Column(name="INDUSTRY_CODE")
	public String getIndustryCode() {
		return industryCode;
	}
	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}
	@Column(name="INDUSTRY_NAME")
	public String getIndustryName() {
		return industryName;
	}
	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}
	@Column(name="PRINT_TYPE_CODE")
	public String getPrintTypeCode() {
		return printTypeCode;
	}
	public void setPrintTypeCode(String printTypeCode) {
		this.printTypeCode = printTypeCode;
	}
	@Column(name="ACCOUNT")
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	@Column(name="BANK")
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	@Column(name="REGISTRATION")
	public String getRegistration() {
		return registration;
	}
	public void setRegistration(String registration) {
		this.registration = registration;
	}
	@Column(name="ZG_INDUSTRY")
	public String getZgIndustry() {
		return zgIndustry;
	}
	public void setZgIndustry(String zgIndustry) {
		this.zgIndustry = zgIndustry;
	}
	@Column(name="BANK_CODE")
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	@Column(name="INVOICE_KIND_CODE")
	public String getInvoiceKindCode() {
		return invoiceKindCode;
	}
	public void setInvoiceKindCode(String invoiceKindCode) {
		this.invoiceKindCode = invoiceKindCode;
	}
	@Column(name="INVOICE_KIND_NAME")
	public String getInvoiceKindName() {
		return invoiceKindName;
	}
	public void setInvoiceKindName(String invoiceKindName) {
		this.invoiceKindName = invoiceKindName;
	}
	@Column(name="INVOICE_TYPE")
	public String getInvoiceType() {
		return invoiceType;
	}
	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}
	@Column(name="ITEMS")
	public String getItems() {
		return items;
	}
	public void setItems(String items) {
		this.items = items;
	}
	@Column(name="STATUS")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name="IS_UPLOAD_PLAT")
	public String getIsUploadPlat() {
		return isUploadPlat;
	}
	public void setIsUploadPlat(String isUploadPlat) {
		this.isUploadPlat = isUploadPlat;
	}
	@Column(name="COMPUTER_NO")
	public String getComputerNo() {
		return computerNo;
	}
	public void setComputerNo(String computerNo) {
		this.computerNo = computerNo;
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
	
	@Column(name="ORIGION_ID")
    public String getOrigionId() {
        return origionId;
    }
    public void setOrigionId(String origionId) {
        this.origionId = origionId;
    }
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "vcInvoicePrint")
	public List<VcInvoicePrintExt> getVcInvoicePrintExtList() {
		return vcInvoicePrintExtList;
	}
	public void setVcInvoicePrintExtList(
			List<VcInvoicePrintExt> vcInvoicePrintExtList) {
		this.vcInvoicePrintExtList = vcInvoicePrintExtList;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "vcInvoicePrint")
	public List<VcInvoicePrintDet> getVcInvoicePrintDetList() {
		return vcInvoicePrintDetList;
	}
	public void setVcInvoicePrintDetList(
			List<VcInvoicePrintDet> vcInvoicePrintDetList) {
		this.vcInvoicePrintDetList = vcInvoicePrintDetList;
	}
	
}
