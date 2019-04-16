package com.tapi.tcs.vc.webservice.provider.inStorage.bean;

public class UserInfoDTO {
	/**用户英文名称*/
	private String userCode;
	/**单证使用人归属机构*/
	private String orgCode;
	/**使用人中文名称*/
	/**用户employeeId*/
	private String employeeId;
	/**单证管理员机构代码*/
	private String comCode;
	/**单证管理员上级机构*/
	private String parentOrgCode;
	
	/**单证管理员还是发票发票管理员标识*/
	private String invoiceFlag;
	
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getComCode() {
		return comCode;
	}
	public void setComCode(String comCode) {
		this.comCode = comCode;
	}
	public String getParentOrgCode() {
		return parentOrgCode;
	}
	public void setParentOrgCode(String parentOrgCode) {
		this.parentOrgCode = parentOrgCode;
	}
	public String getInvoiceFlag() {
		return invoiceFlag;
	}
	public void setInvoiceFlag(String invoiceFlag) {
		this.invoiceFlag = invoiceFlag;
	}
	
   }
