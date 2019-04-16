package com.tapi.tcs.vc.webservice.provider.docVerInquiryOld.bean;


public class DocVerDTO {

	/**单证分类*/
	private Integer type;
	
	/**单证种类ID*/
	private Long docTypeID;
	
	/**单证种类名称*/
	private String docTypeName;
	
	/**单证类型ID*/
	private Long docVerID;
	
	/**单证类型代码*/
	private String docVerCode;
	
	/**单证类型名称*/
	private String docVerName;
	
	/**适用地区ID*/
	private String orgID;
	
	/**险种ID*/
	private Long insuKindID;
	
	/**打印模板*/
	private String printTemplate;
	
	/**是否是连发票的保单*/
	private Integer isInvoice;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getDocTypeID() {
		return docTypeID;
	}

	public void setDocTypeID(Long docTypeID) {
		this.docTypeID = docTypeID;
	}

	public String getDocTypeName() {
		return docTypeName;
	}

	public void setDocTypeName(String docTypeName) {
		this.docTypeName = docTypeName;
	}

	public Long getDocVerID() {
		return docVerID;
	}

	public void setDocVerID(Long docVerID) {
		this.docVerID = docVerID;
	}

	public String getDocVerCode() {
		return docVerCode;
	}

	public void setDocVerCode(String docVerCode) {
		this.docVerCode = docVerCode;
	}

	public String getDocVerName() {
		return docVerName;
	}

	public void setDocVerName(String docVerName) {
		this.docVerName = docVerName;
	}

	public String getOrgID() {
		return orgID;
	}

	public void setOrgID(String orgID) {
		this.orgID = orgID;
	}

	public Long getInsuKindID() {
		return insuKindID;
	}

	public void setInsuKindID(Long insuKindID) {
		this.insuKindID = insuKindID;
	}

	public String getPrintTemplate() {
		return printTemplate;
	}

	public void setPrintTemplate(String printTemplate) {
		this.printTemplate = printTemplate;
	}

	public Integer getIsInvoice() {
		return isInvoice;
	}

	public void setIsInvoice(Integer isInvoice) {
		this.isInvoice = isInvoice;
	}
	
}
