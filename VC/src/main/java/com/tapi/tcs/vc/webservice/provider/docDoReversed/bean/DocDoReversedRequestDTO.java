package com.tapi.tcs.vc.webservice.provider.docDoReversed.bean;

import java.util.List;

public class DocDoReversedRequestDTO {

	/**单证类型代码*/
	private String docVerCode;
	/**印刷批次*/
	private String pressBatchNo;
	/**操作员归属机构代码*/
	private String orgCode;
	/**中介机构代码*/
	private String agentCode;
	/**单证流水列表*/
	private List<DocDoReversedDocNumDTO> docDoReversedDocNumDTOList;
	/**预留字段*/
	private List<ExtendDTO> extendsList;
	
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
	public List<DocDoReversedDocNumDTO> getDocDoReversedDocNumDTOList() {
		return docDoReversedDocNumDTOList;
	}
	public void setDocDoReversedDocNumDTOList(
			List<DocDoReversedDocNumDTO> docDoReversedDocNumDTOList) {
		this.docDoReversedDocNumDTOList = docDoReversedDocNumDTOList;
	}
	public List<ExtendDTO> getExtendsList() {
		return extendsList;
	}
	public void setExtendsList(List<ExtendDTO> extendsList) {
		this.extendsList = extendsList;
	}
}
