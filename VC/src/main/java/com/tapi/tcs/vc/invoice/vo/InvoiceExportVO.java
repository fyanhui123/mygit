package com.tapi.tcs.vc.invoice.vo;

import java.util.Date;
import java.util.List;

/**
 * 发票数据导出VO
 * <p>
 * Date 2013-06-08
 * </p>
 * <p>
 * Module：发票
 * </p>
 * <p>
 * Description：
 * </p>
 * <p>
 * Remark：
 * </p>
 * @author chy
 * @version 1.0
 */
public class InvoiceExportVO {

	/**险类ID*/
	private Long insuTypeId;
	/**险种代码*/
	private String insuKindCode;
	/**保单号*/
	private String policyNo;
	/**批单号*/
	private String endorseNo;
	/**被保险人*/
	private String insuredName;
	/**付款人*/
	private String payerName;
	/**签单机构*/
	private String inputCompany;
	/**发票状态*/
	private String invoiceStatus;
	/**打印日期起*/
	private Date printDateStart;
	/**打印日期止*/
	private Date printDateEnd;
	/**发票代码*/
	private String invoiceCode;
	/**单证类型代码*/
	private List<String> docVerCodeList;
	
	
	public Long getInsuTypeId() {
		return insuTypeId;
	}
	public void setInsuTypeId(Long insuTypeId) {
		this.insuTypeId = insuTypeId;
	}
	public String getInsuKindCode() {
		return insuKindCode;
	}
	public void setInsuKindCode(String insuKindCode) {
		this.insuKindCode = insuKindCode;
	}
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
	public String getInsuredName() {
		return insuredName;
	}
	public void setInsuredName(String insuredName) {
		this.insuredName = insuredName;
	}
	public String getPayerName() {
		return payerName;
	}
	public void setPayerName(String payerName) {
		this.payerName = payerName;
	}
	public String getInputCompany() {
		return inputCompany;
	}
	public void setInputCompany(String inputCompany) {
		this.inputCompany = inputCompany;
	}
	public String getInvoiceStatus() {
		return invoiceStatus;
	}
	public void setInvoiceStatus(String invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}
	public Date getPrintDateStart() {
		return printDateStart;
	}
	public void setPrintDateStart(Date printDateStart) {
		this.printDateStart = printDateStart;
	}
	public Date getPrintDateEnd() {
		return printDateEnd;
	}
	public void setPrintDateEnd(Date printDateEnd) {
		this.printDateEnd = printDateEnd;
	}
	public String getInvoiceCode() {
		return invoiceCode;
	}
	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}
	public List<String> getDocVerCodeList() {
		return docVerCodeList;
	}
	public void setDocVerCodeList(List<String> docVerCodeList) {
		this.docVerCodeList = docVerCodeList;
	}
}
