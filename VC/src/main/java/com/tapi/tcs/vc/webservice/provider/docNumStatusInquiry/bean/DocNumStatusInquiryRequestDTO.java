package com.tapi.tcs.vc.webservice.provider.docNumStatusInquiry.bean;

import java.util.List;

public class DocNumStatusInquiryRequestDTO {

	private String docVerCode;  //单证类型代码
	private String pressBatchNo; //印刷批次
	private String operatorCode; //操作员代码
	private String orgCode;    //操作员归属机构代码
	private String  startNum;  //单证起始流水号
	private String  endNum;    //单证终止流水号
	private String agentCode;  //中介机构代码
	private List<ExtendDTO> extendList;//预留字段
	
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
	public String getAgentCode() {
		return agentCode;
	}
	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}
	public List<ExtendDTO> getExtendList() {
		return extendList;
	}
	public void setExtendList(List<ExtendDTO> extendList) {
		this.extendList = extendList;
	}
	public String getPressBatchNo() {
		return pressBatchNo;
	}
	public void setPressBatchNo(String pressBatchNo) {
		this.pressBatchNo = pressBatchNo;
	}
	
}
