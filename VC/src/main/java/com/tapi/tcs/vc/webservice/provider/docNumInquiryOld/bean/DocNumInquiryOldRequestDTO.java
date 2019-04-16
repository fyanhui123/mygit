package com.tapi.tcs.vc.webservice.provider.docNumInquiryOld.bean;

public class DocNumInquiryOldRequestDTO {
    
	private Long docVerID;      // 单证类型ID
	private Long operatorID;    //操作员ID
	private String orgID;     //操作员归属机构ID
	private String orgCode;    //操作员归属机构代码
	private String insuKindID;  // 险种ID
	private String businessNo;  // 业务号
	private String invoiceVersion; // 发票版本
	private Long  insuTypeID;  //险类ID
	private Long  depID;   //部门ID
	private String depCode; //部门代码
	
	public Long getDocVerID() {
		return docVerID;
	}
	public void setDocVerID(Long docVerID) {
		this.docVerID = docVerID;
	}
	public Long getOperatorID() {
		return operatorID;
	}
	public void setOperatorID(Long operatorID) {
		this.operatorID = operatorID;
	}
	public String getOrgID() {
		return orgID;
	}
	public void setOrgID(String orgID) {
		this.orgID = orgID;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getInsuKindID() {
		return insuKindID;
	}
	public void setInsuKindID(String insuKindID) {
		this.insuKindID = insuKindID;
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
	public Long getInsuTypeID() {
		return insuTypeID;
	}
	public void setInsuTypeID(Long insuTypeID) {
		this.insuTypeID = insuTypeID;
	}
	public Long getDepID() {
		return depID;
	}
	public void setDepID(Long depID) {
		this.depID = depID;
	}
	public String getDepCode() {
		return depCode;
	}
	public void setDepCode(String depCode) {
		this.depCode = depCode;
	}
}
