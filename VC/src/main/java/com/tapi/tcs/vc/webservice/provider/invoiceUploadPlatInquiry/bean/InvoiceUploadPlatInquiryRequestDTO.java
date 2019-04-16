package com.tapi.tcs.vc.webservice.provider.invoiceUploadPlatInquiry.bean;

import java.util.List;

public class InvoiceUploadPlatInquiryRequestDTO {
	/**部门ID*/
	private Long departId;
	/**部门代码*/
	private String departCode;
	/**发票代码*/
	private String invoiceCode;
	/**发票号码*/
	private String invoiceNo;
	/**发票开具类型*/
	private String printKind;
	/**预留字段*/
	private List<ExtendDTO> extendList;
	
	public Long getDepartId() {
		return departId;
	}
	public void setDepartId(Long departId) {
		this.departId = departId;
	}
	public String getDepartCode() {
		return departCode;
	}
	public void setDepartCode(String departCode) {
		this.departCode = departCode;
	}
	public String getInvoiceCode() {
		return invoiceCode;
	}
	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public List<ExtendDTO> getExtendList() {
		return extendList;
	}
	public void setExtendList(List<ExtendDTO> extendList) {
		this.extendList = extendList;
	}
	public String getPrintKind() {
		return printKind;
	}
	public void setPrintKind(String printKind) {
		this.printKind = printKind;
	}
}
