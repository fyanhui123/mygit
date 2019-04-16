package com.tapi.tcs.vc.webservice.provider.docDoUsed.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.tapi.tcs.vc.webservice.provider.docDoAnnuled.bean.BusinessNoDTO;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DZHX_INFO", propOrder = {
    "docNum",
    "businessNoDTOs",
    "extendList"
})
public class DoUsedDocNumInfoDTO {

	@XmlElement(name = "docNum", required = true)
	private  String  docNum;   //单证流水号
	@XmlElement(name = "businessNoDTOs", required = true)
	private  List<DoUsedBusinessNoDTO>  businessNoDTOs; //业务号列表
	@XmlElement(name = "extendList", required = true)
	private  List<ExtendDTO> extendList; //预留字段
	
	public String getDocNum() {
		return docNum;
	}
	public void setDocNum(String docNum) {
		this.docNum = docNum;
	}
	public List<DoUsedBusinessNoDTO> getBusinessNoDTOs() {
		return businessNoDTOs;
	}
	public void setBusinessNoDTOs(List<DoUsedBusinessNoDTO> businessNoDTOs) {
		this.businessNoDTOs = businessNoDTOs;
	}
	public List<ExtendDTO> getExtendList() {
		return extendList;
	}
	public void setExtendList(List<ExtendDTO> extendList) {
		this.extendList = extendList;
	}
}
