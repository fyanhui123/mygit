package com.tapi.tcs.vc.webservice.provider.docDoUsed.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DZHX", propOrder = {
		"docVerCode",
		"pressBatchNo",
		"operatorCode",
		"orgCode",
		"docTypeCode",
		"agentCode",
		"docNumInfoDTOs",
		"extendList"
})
@XmlRootElement(name = "REQUEST_BODY")
public class DocDoUsedRequestDTO {

	@XmlElement(name = "docVerCode", required = true)
	private  String  docVerCode;  //单证类型代码
	@XmlElement(name = "pressBatchNo", required = true)
	private  String  pressBatchNo; //印刷批次
	@XmlElement(name = "operatorCode", required = true)
	private  String  operatorCode; //操作员代码
	@XmlElement(name = "orgCode", required = true)
	private  String   orgCode;  //操作员归属机构代码
	@XmlElement(name = "docTypeCode", required = true)
	private  String   docTypeCode;  //单证种类代码
	@XmlElement(name = "docNumInfoDTOs", required = true)
	private  List<DoUsedDocNumInfoDTO> docNumInfoDTOs; //单证流水号列表
	@XmlElement(name = "agentCode", required = true)
	private  String agentCode;  //中介机构代码
	@XmlElement(name = "extendList", required = true)
	private  List<ExtendDTO> extendList; //预留字段
	
	public String getDocVerCode() {
		return docVerCode;
	}
	public void setDocVerCode(String docVerCode) {
		this.docVerCode = docVerCode;
	}
	public String getPressBatchNo() {
		return pressBatchNo;
	}
	public void setPressBatchNo(String pressBatchNo) {
		this.pressBatchNo = pressBatchNo;
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
	public List<DoUsedDocNumInfoDTO> getDocNumInfoDTOs() {
		return docNumInfoDTOs;
	}
	public void setDocNumInfoDTOs(List<DoUsedDocNumInfoDTO> docNumInfoDTOs) {
		this.docNumInfoDTOs = docNumInfoDTOs;
	}
	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}
	public String getAgentCode() {
		return agentCode;
	}
	public void setExtendList(List<ExtendDTO> extendList) {
		this.extendList = extendList;
	}
	public List<ExtendDTO> getExtendList() {
		return extendList;
	}
	
}
