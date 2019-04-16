package com.tapi.tcs.vc.webservice.provider.docNumStatusInquiryOld.bean;

import java.util.List;

public class DocNumStatusInquiryOldRequestDTO {

	private Long docVerID; // 单证类型ID
	private Long operatorID;// 操作员ID
	private String orgID; // 操作员归属机构ID
	private String orgCode; // 操作员归属机构代码
	private String insuKindID;// 险种ID
	private String invoiceVersion;// 发票代码
	private Long insuTypeID;// 险类ID
	private Long depID;// 部门ID
	private String depCode;//部门代码
	private List<DocSerialNoDTO> docSerialNoDTOs;//流水号区间段

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

	public List<DocSerialNoDTO> getDocSerialNoDTOs() {
		return docSerialNoDTOs;
	}

	public void setDocSerialNoDTOs(List<DocSerialNoDTO> docSerialNoDTOs) {
		this.docSerialNoDTOs = docSerialNoDTOs;
	}

}
