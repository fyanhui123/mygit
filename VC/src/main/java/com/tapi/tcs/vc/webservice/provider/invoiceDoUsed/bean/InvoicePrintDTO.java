package com.tapi.tcs.vc.webservice.provider.invoiceDoUsed.bean;

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FPHX_INFO_PRINT", propOrder = {
    "policyNo",
    "endorseNo",
    "printTime",
    "billerName",
    "payerType",
    "payerName",
    "payerID",
    "taxpayerID",
    "taxpayerName",
    "sumAmount",
    "upperSumAmount",
    "canceledOprName",
    "canceledTime",
    "printKind",
    "counteractedInvoiceCode",
    "counteractedInvoiceNo",
    "remark",
    "operatorDate",
    "startDate",
    "endDate",
    "checkNum",
    "printTypeCode",
    "recipientOpr"
})
public class InvoicePrintDTO {
	@XmlElement(name = "policyNo", required = true)
	private String     policyNo;							//	        保单号	
	@XmlElement(name = "endorseNo", required = true)
	private String     endorseNo;						//	        批单号
	@XmlElement(name = "printTime", required = true)
	private Date       printTime;						//	        开票日期			
	@XmlElement(name = "billerName", required = true)
	private String     billerName;						//	        开票员名称		
	@XmlElement(name = "payerType", required = true)
	private String payerType;
	@XmlElement(name = "payerName", required = true)
	private String     payerName;						//	        付款方名称		
	@XmlElement(name = "payerID", required = true)
	private String     payerID;						//        付款方识别号
	@XmlElement(name = "taxpayerID", required = true)
	private String     taxpayerID;						//	       收款方识别号
	@XmlElement(name = "taxpayerName", required = true)
	private String     taxpayerName;						//	        收款方纳税人名
	@XmlElement(name = "sumAmount", required = true)
	private BigDecimal sumAmount;							//       开票金额
	@XmlElement(name = "upperSumAmount", required = true)
	private String     upperSumAmount;						//	        大写金额
	@XmlElement(name = "canceledOprName", required = true)
	private String canceledOprName;							//作废人名称
	@XmlElement(name = "canceledTime", required = true)
	private Date canceledTime;							//作废时间
	@XmlElement(name = "printKind", required = true)
	private String     printKind;						//	        发票开具类型
	@XmlElement(name = "counteractedInvoiceCode", required = true)
	private String     counteractedInvoiceCode;			//	        被冲红的发票代
	@XmlElement(name = "counteractedInvoiceNo", required = true)
	private String     counteractedInvoiceNo;			//	        被冲红的发票号
	@XmlElement(name = "remark", required = true)
	private String     remark;							//	        备注
	@XmlElement(name = "operatorDate", required = true)
	private Date       operatorDate;						//        操作时间
	@XmlElement(name = "startDate", required = true)
	private Date       startDate;						//	        起始时间
	@XmlElement(name = "endDate", required = true)
	private Date       endDate;							//	        结束时间
	@XmlElement(name = "checkNum", required = true)
	private String     checkNum;				//	        校验码
	@XmlElement(name = "printTypeCode", required = true)
	private String     printTypeCode;			//        套打样式代码
	@XmlElement(name = "recipientOpr", required = true)
	private String recipientOpr;				//收款人名称
	
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
	public Date getPrintTime() {
		return printTime;
	}
	public void setPrintTime(Date printTime) {
		this.printTime = printTime;
	}
	public String getBillerName() {
		return billerName;
	}
	public void setBillerName(String billerName) {
		this.billerName = billerName;
	}
	public String getPayerType() {
		return payerType;
	}
	public void setPayerType(String payerType) {
		this.payerType = payerType;
	}
	public String getPayerName() {
		return payerName;
	}
	public void setPayerName(String payerName) {
		this.payerName = payerName;
	}
	public String getPayerID() {
		return payerID;
	}
	public void setPayerID(String payerID) {
		this.payerID = payerID;
	}
	public String getTaxpayerID() {
		return taxpayerID;
	}
	public void setTaxpayerID(String taxpayerID) {
		this.taxpayerID = taxpayerID;
	}
	public String getTaxpayerName() {
		return taxpayerName;
	}
	public void setTaxpayerName(String taxpayerName) {
		this.taxpayerName = taxpayerName;
	}
	public BigDecimal getSumAmount() {
		return sumAmount;
	}
	public void setSumAmount(BigDecimal sumAmount) {
		this.sumAmount = sumAmount;
	}
	public String getUpperSumAmount() {
		return upperSumAmount;
	}
	public void setUpperSumAmount(String upperSumAmount) {
		this.upperSumAmount = upperSumAmount;
	}
	public String getCanceledOprName() {
		return canceledOprName;
	}
	public void setCanceledOprName(String canceledOprName) {
		this.canceledOprName = canceledOprName;
	}
	public Date getCanceledTime() {
		return canceledTime;
	}
	public void setCanceledTime(Date canceledTime) {
		this.canceledTime = canceledTime;
	}
	public String getPrintKind() {
		return printKind;
	}
	public void setPrintKind(String printKind) {
		this.printKind = printKind;
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
	public String getPrintTypeCode() {
		return printTypeCode;
	}
	public void setPrintTypeCode(String printTypeCode) {
		this.printTypeCode = printTypeCode;
	}
	public String getRecipientOpr() {
		return recipientOpr;
	}
	public void setRecipientOpr(String recipientOpr) {
		this.recipientOpr = recipientOpr;
	}
	
}
