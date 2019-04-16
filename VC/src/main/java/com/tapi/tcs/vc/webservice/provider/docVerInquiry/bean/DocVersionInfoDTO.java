package com.tapi.tcs.vc.webservice.provider.docVerInquiry.bean;

import java.util.List;

public class DocVersionInfoDTO {
	private  String insuKindCode;  //险种代码
	// private  String orgCode;       //操作员归属机构代码
	private List<String> orgCode;
	private  String docTypeCode;   //单证种类代码
	private  String templateCode;  //模板代码
	public String getInsuKindCode() {
		return insuKindCode;
	}
	public void setInsuKindCode(String insuKindCode) {
		this.insuKindCode = insuKindCode;
	}
	
	public List<String> getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(List<String> orgCode) {
		this.orgCode = orgCode;
	}
	public String getDocTypeCode() {
		return docTypeCode;
	}
	public void setDocTypeCode(String docTypeCode) {
		this.docTypeCode = docTypeCode;
	}
	public String getTemplateCode() {
		return templateCode;
	}
	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}
}
