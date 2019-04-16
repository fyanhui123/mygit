package com.tapi.tcs.vc.webservice.provider.docDoReprinted.bean;

import java.util.List;

public class DocDoReprintedRequestDTO {

	private String oldDocVerCode; // 旧单证类型代码
	private String oldPressBatchNo;//旧印刷批次
	private String oldDocNum; // 旧单证流水号
	private String newDocVerCode; // 新单证类型代码
	private String newPressBatchNo;//新印刷批次
	private String newDocNum; // 新单证流水号
	private String businessNo; // 业务号
	private String orgCode; // 机构代码
	private String agentCode; // 中介机构代码
	private String operatorCode; // 业务员代码
	private List<ExtendDTO> expandsList; // 预留字段

	public String getOldDocVerCode() {
		return oldDocVerCode;
	}

	public void setOldDocVerCode(String oldDocVerCode) {
		this.oldDocVerCode = oldDocVerCode;
	}

	public String getNewDocVerCode() {
		return newDocVerCode;
	}

	public void setNewDocVerCode(String newDocVerCode) {
		this.newDocVerCode = newDocVerCode;
	}

	public String getOldDocNum() {
		return oldDocNum;
	}

	public void setOldDocNum(String oldDocNum) {
		this.oldDocNum = oldDocNum;
	}

	public String getNewDocNum() {
		return newDocNum;
	}

	public void setNewDocNum(String newDocNum) {
		this.newDocNum = newDocNum;
	}

	public String getBusinessNo() {
		return businessNo;
	}

	public void setBusinessNo(String businessNo) {
		this.businessNo = businessNo;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getAgentCode() {
		return agentCode;
	}

	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}

	public String getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}

	public List<ExtendDTO> getExpandsList() {
		return expandsList;
	}

	public void setExpandsList(List<ExtendDTO> expandsList) {
		this.expandsList = expandsList;
	}

	public String getOldPressBatchNo() {
		return oldPressBatchNo;
	}

	public void setOldPressBatchNo(String oldPressBatchNo) {
		this.oldPressBatchNo = oldPressBatchNo;
	}

	public String getNewPressBatchNo() {
		return newPressBatchNo;
	}

	public void setNewPressBatchNo(String newPressBatchNo) {
		this.newPressBatchNo = newPressBatchNo;
	}
	
}
