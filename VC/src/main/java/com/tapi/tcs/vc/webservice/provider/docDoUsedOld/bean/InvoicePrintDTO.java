package com.tapi.tcs.vc.webservice.provider.docDoUsedOld.bean;

import java.math.BigDecimal;
import java.util.Date;

public class InvoicePrintDTO {

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
	/**作废人*/
	private String	canceledOpr;
	/**作废时间*/
	private Date	canceldDate;
	/**备注*/
	private String	remark;
	/**操作时间*/
	private Date	operatorDate;
	/**发票开具类型*/
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
	
	
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	public String getEndorseNo() {
		return endorseNo;
	}
	public void setEndorseNo(String endorseNo) {
		this.endorseNo = endorseNo;
	}
	public String getInvoiceCode() {
		return invoiceCode;
	}
	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}
	public String getInvoiceName() {
		return invoiceName;
	}
	public void setInvoiceName(String invoiceName) {
		this.invoiceName = invoiceName;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public Date getPrintDate() {
		return printDate;
	}
	public void setPrintDate(Date printDate) {
		this.printDate = printDate;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getPayerName() {
		return payerName;
	}
	public void setPayerName(String payerName) {
		this.payerName = payerName;
	}
	public String getPayerCode() {
		return payerCode;
	}
	public void setPayerCode(String payerCode) {
		this.payerCode = payerCode;
	}
	public String getRecipientName() {
		return recipientName;
	}
	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}
	public String getRecipientCode() {
		return recipientCode;
	}
	public void setRecipientCode(String recipientCode) {
		this.recipientCode = recipientCode;
	}
	public String getTaxpayerName() {
		return taxpayerName;
	}
	public void setTaxpayerName(String taxpayerName) {
		this.taxpayerName = taxpayerName;
	}
	public String getTaxpayerCode() {
		return taxpayerCode;
	}
	public void setTaxpayerCode(String taxpayerCode) {
		this.taxpayerCode = taxpayerCode;
	}
	public String getBillerName() {
		return billerName;
	}
	public void setBillerName(String billerName) {
		this.billerName = billerName;
	}
	public String getRecipientOpr() {
		return recipientOpr;
	}
	public void setRecipientOpr(String recipientOpr) {
		this.recipientOpr = recipientOpr;
	}
	public String getCounteractInvoiceCode() {
		return counteractInvoiceCode;
	}
	public void setCounteractInvoiceCode(String counteractInvoiceCode) {
		this.counteractInvoiceCode = counteractInvoiceCode;
	}
	public String getCounteractInvoiceNo() {
		return counteractInvoiceNo;
	}
	public void setCounteractInvoiceNo(String counteractInvoiceNo) {
		this.counteractInvoiceNo = counteractInvoiceNo;
	}
	public String getCounteractedInvoiceCode() {
		return counteractedInvoiceCode;
	}
	public void setCounteractedInvoiceCode(String counteractedInvoiceCode) {
		this.counteractedInvoiceCode = counteractedInvoiceCode;
	}
	public String getCounteractedInvoiceNo() {
		return counteractedInvoiceNo;
	}
	public void setCounteractedInvoiceNo(String counteractedInvoiceNo) {
		this.counteractedInvoiceNo = counteractedInvoiceNo;
	}
	public String getCanceledOpr() {
		return canceledOpr;
	}
	public void setCanceledOpr(String canceledOpr) {
		this.canceledOpr = canceledOpr;
	}
	public Date getCanceldDate() {
		return canceldDate;
	}
	public void setCanceldDate(Date canceldDate) {
		this.canceldDate = canceldDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getOperatorDate() {
		return operatorDate;
	}
	public void setOperatorDate(Date operatorDate) {
		this.operatorDate = operatorDate;
	}
	public String getPrintKind() {
		return printKind;
	}
	public void setPrintKind(String printKind) {
		this.printKind = printKind;
	}
	public String getTaxOrgCode() {
		return taxOrgCode;
	}
	public void setTaxOrgCode(String taxOrgCode) {
		this.taxOrgCode = taxOrgCode;
	}
	public String getManageCode() {
		return manageCode;
	}
	public void setManageCode(String manageCode) {
		this.manageCode = manageCode;
	}
	public String getSettleItem() {
		return settleItem;
	}
	public void setSettleItem(String settleItem) {
		this.settleItem = settleItem;
	}
	public String getBelongStage() {
		return belongStage;
	}
	public void setBelongStage(String belongStage) {
		this.belongStage = belongStage;
	}
	public String getMachinePrintedCode() {
		return machinePrintedCode;
	}
	public void setMachinePrintedCode(String machinePrintedCode) {
		this.machinePrintedCode = machinePrintedCode;
	}
	public String getMachinePrintedNo() {
		return machinePrintedNo;
	}
	public void setMachinePrintedNo(String machinePrintedNo) {
		this.machinePrintedNo = machinePrintedNo;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getCheckNum() {
		return checkNum;
	}
	public void setCheckNum(String checkNum) {
		this.checkNum = checkNum;
	}
	public BigDecimal getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}
	public BigDecimal getTaxRate() {
		return taxRate;
	}
	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}
	public String getUpperAmount() {
		return upperAmount;
	}
	public void setUpperAmount(String upperAmount) {
		this.upperAmount = upperAmount;
	}
	public String getIndustryCode() {
		return industryCode;
	}
	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}
	public String getIndustryName() {
		return industryName;
	}
	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}
	public String getPrintTypeCode() {
		return printTypeCode;
	}
	public void setPrintTypeCode(String printTypeCode) {
		this.printTypeCode = printTypeCode;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getRegistration() {
		return registration;
	}
	public void setRegistration(String registration) {
		this.registration = registration;
	}
	public String getZgIndustry() {
		return zgIndustry;
	}
	public void setZgIndustry(String zgIndustry) {
		this.zgIndustry = zgIndustry;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getInvoiceKindCode() {
		return invoiceKindCode;
	}
	public void setInvoiceKindCode(String invoiceKindCode) {
		this.invoiceKindCode = invoiceKindCode;
	}
	public String getInvoiceKindName() {
		return invoiceKindName;
	}
	public void setInvoiceKindName(String invoiceKindName) {
		this.invoiceKindName = invoiceKindName;
	}
	public String getInvoiceType() {
		return invoiceType;
	}
	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}
	public String getItems() {
		return items;
	}
	public void setItems(String items) {
		this.items = items;
	}
	
}
