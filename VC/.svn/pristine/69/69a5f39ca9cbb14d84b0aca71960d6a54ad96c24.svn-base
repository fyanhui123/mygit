package com.tapi.tcs.vc.webservice.provider.docDoAnnuled.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DZZF_INFO", propOrder = {
    "docNum",
    "isCounteract",
    "businessNoDTOs",
    "extendList"
})
public class DoAnnuledDocNumInfoDTO {

	/**单证流水号**/
	@XmlElement(name = "docNum", required = true)
	private  String  docNum;
	/***业务号列表**/
	@XmlElement(name = "businessNoDTOs", required = true)
	private  List<BusinessNoDTO>  businessNoDTOs;
	/**是否红冲作废*/
	@XmlElement(name = "isCounteract", required = true)
	private String isCounteract;
	/**预留字段*/
	@XmlElement(name = "extendList", required = true)
	private List<ExtendDTO> extendList;
	
	public String getDocNum() {
		return docNum;
	}
	public void setDocNum(String docNum) {
		this.docNum = docNum;
	}
	public List<BusinessNoDTO> getBusinessNoDTOs() {
		return businessNoDTOs;
	}
	public void setBusinessNoDTOs(List<BusinessNoDTO> businessNoDTOs) {
		this.businessNoDTOs = businessNoDTOs;
	}
	public String getIsCounteract() {
		return isCounteract;
	}
	public void setIsCounteract(String isCounteract) {
		this.isCounteract = isCounteract;
	}
	public List<ExtendDTO> getExtendList() {
		return extendList;
	}
	public void setExtendList(List<ExtendDTO> extendList) {
		this.extendList = extendList;
	}
}
