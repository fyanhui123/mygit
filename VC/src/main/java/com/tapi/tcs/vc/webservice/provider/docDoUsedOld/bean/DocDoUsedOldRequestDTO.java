package com.tapi.tcs.vc.webservice.provider.docDoUsedOld.bean;

import java.util.List;

public class DocDoUsedOldRequestDTO {
	
	/**单证种类ID*/
	private Long docTypeId;
	/**单证类型ID*/
	private Long docVersionID;
	/**产品大类ID*/
	private Long classId;
	/**产品ID*/
	private Long riskId;
	/**用户ID*/
	private Long userId;
	/**机构ID*/
	private String companyId;
	/**机构代码*/
	private String companyCode;
	/**部门ID*/
	private Long departId;
	/**部门代码*/
	private String departCode;
	/** 流水号区间段 */
	private List<DocSerialNoDTO> docSerialNoDTOs;
	/**发票信息*/
	private List<InvoiceDTO> invoiceDTOs;
	
	public Long getDocTypeId() {
		return docTypeId;
	}
	public void setDocTypeId(Long docTypeId) {
		this.docTypeId = docTypeId;
	}
	public Long getDocVersionID() {
		return docVersionID;
	}
	public void setDocVersionID(Long docVersionID) {
		this.docVersionID = docVersionID;
	}
	public Long getClassId() {
		return classId;
	}
	public void setClassId(Long classId) {
		this.classId = classId;
	}
	public Long getRiskId() {
		return riskId;
	}
	public void setRiskId(Long riskId) {
		this.riskId = riskId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
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
	public List<DocSerialNoDTO> getDocSerialNos() {
		return docSerialNoDTOs;
	}
	public void setDocSerialNos(List<DocSerialNoDTO> docSerialNos) {
		this.docSerialNoDTOs = docSerialNos;
	}
	public List<InvoiceDTO> getInvoiceDTOs() {
		return invoiceDTOs;
	}
	public void setInvoiceDTOs(List<InvoiceDTO> invoiceDTOs) {
		this.invoiceDTOs = invoiceDTOs;
	}
	
}
