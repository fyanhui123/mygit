package com.tapi.tcs.vc.webservice.provider.docNumStatusInquiryOld.vo;

public class DocNumStatusInquiryOldRequestVO {
	private String docVerCode; // 单证类型代码
	private String operatorCode; // 操作员代码
	private String orgCode; // 操作员归属机构代码
	private String businessNo; // 业务号
	private String invoiceVersion; // 发票版本
	private String startNum; // 单证起始流水号
	private String endNum; // 单证终止流水号
	
	public String getDocVerCode() {
		return docVerCode;
	}
	public void setDocVerCode(String docVerCode) {
		this.docVerCode = docVerCode;
	}
	public String getOperatorCode() {
		return operatorCode;
	}
	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getBusinessNo() {
		return businessNo;
	}
	public void setBusinessNo(String businessNo) {
		this.businessNo = businessNo;
	}
	public String getInvoiceVersion() {
		return invoiceVersion;
	}
	public void setInvoiceVersion(String invoiceVersion) {
		this.invoiceVersion = invoiceVersion;
	}
	public String getStartNum() {
		return startNum;
	}
	public void setStartNum(String startNum) {
		this.startNum = startNum;
	}
	public String getEndNum() {
		return endNum;
	}
	public void setEndNum(String endNum) {
		this.endNum = endNum;
	}

}
