package com.tapi.tcs.vc.webservice.provider.docDoAnnuled.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DZZF", propOrder = {
		"docVerCode",
		"pressBatchNo",
		"operatorCode",
		"orgCode",
		"agentCode",
		"docNumInfoDTOs",
		"extendList"
})
@XmlRootElement(name = "REQUEST_BODY")
public class DocDoAnnuledRequestDTO {

	/**单证类型代码***/
	@XmlElement(name = "docVerCode", required = true)
	private  String  docVerCode;
	/**印刷批次***/
	@XmlElement(name = "pressBatchNo", required = true)
	private  String  pressBatchNo;
	/**操作员代码***/
	@XmlElement(name = "operatorCode", required = true)
	private  String  operatorCode;
	/**操作员归属机构代码**/
	@XmlElement(name = "orgCode", required = true)
	private  String  orgCode;
	/**单证流水列表(单证流水号/业务号列表)**/
	@XmlElement(name = "docNumInfoDTOs", required = true)
	private  List<DoAnnuledDocNumInfoDTO> docNumInfoDTOs;
	/**中介机构代码**/
	@XmlElement(name = "agentCode", required = true)
	private	 String agentCode;
	/**预留字段**/
	@XmlElement(name = "extendList", required = true)
	private  List<ExtendDTO> extendList;
	
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
	public List<DoAnnuledDocNumInfoDTO> getDocNumInfoDTOs() {
		return docNumInfoDTOs;
	}
	public void setDocNumInfoDTOs(List<DoAnnuledDocNumInfoDTO> docNumInfoDTOs) {
		this.docNumInfoDTOs = docNumInfoDTOs;
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
	
}
