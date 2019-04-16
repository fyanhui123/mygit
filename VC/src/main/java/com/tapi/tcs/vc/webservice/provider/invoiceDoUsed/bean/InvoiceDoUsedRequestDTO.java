package com.tapi.tcs.vc.webservice.provider.invoiceDoUsed.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FPHX", propOrder = {
		"docVerCode",
		"operatorCode",
		"orgCode",
		"docTypeCode",
		"docNumInfoDTOs",
		"expandsList"
})
@XmlRootElement(name = "REQUEST_BODY")
public class InvoiceDoUsedRequestDTO {
	
	@XmlElement(name = "docVerCode", required = true)
	private String docVerCode;//单证类型代码
	@XmlElement(name = "operatorCode", required = true)
	private String operatorCode;//操作员代码
	@XmlElement(name = "orgCode", required = true)
	private String orgCode;//操作员归属机构代码
	@XmlElement(name = "docTypeCode", required = true)
	private String docTypeCode;//单证种类代码
	@XmlElement(name = "docNumInfoDTOs", required = true)
	private List<InvoiceDoUsedInfoDTO> docNumInfoDTOs;//单证流水号列表
	@XmlElement(name = "expandsList", required = true)
	private List<ExtendDTO> expandsList;//预留字段
	
	
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
	public String getDocTypeCode() {
		return docTypeCode;
	}
	public void setDocTypeCode(String docTypeCode) {
		this.docTypeCode = docTypeCode;
	}
	public List<InvoiceDoUsedInfoDTO> getDocNumInfoDTOs() {
		return docNumInfoDTOs;
	}
	public void setDocNumInfoDTOs(List<InvoiceDoUsedInfoDTO> docNumInfoDTOs) {
		this.docNumInfoDTOs = docNumInfoDTOs;
	}
	public List<ExtendDTO> getExpandsList() {
		return expandsList;
	}
	public void setExpandsList(List<ExtendDTO> expandsList) {
		this.expandsList = expandsList;
	}

}
