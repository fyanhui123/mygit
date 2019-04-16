package com.tapi.tcs.vc.webservice.provider.docDoUsed.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DZHX_INFO_BUS", propOrder = {
    "businessNo",
    "relationBusinessNo",
    "payNo",
    "counteractFlag",
    "batchNo",
    "extendList"
})
public class DoUsedBusinessNoDTO {

	@XmlElement(name = "businessNo", required = true)
	private  String  businessNo;   // 业务号
	@XmlElement(name = "relationBusinessNo", required = true)
	private  String  relationBusinessNo; //批单对应的保单业务号
	@XmlElement(name = "payNo", required = true)
	private String payNo;//缴费期次
	@XmlElement(name = "counteractFlag", required = true)
	private String counteractFlag;//红冲标志
	@XmlElement(name = "batchNo", required = true)
	private String batchNo;//拆分批次
	@XmlElement(name = "extendList", required = true)
	private  List<ExtendDTO> extendList; //预留字段
	
	public String getBusinessNo() {
		return businessNo;
	}
	public void setBusinessNo(String businessNo) {
		this.businessNo = businessNo;
	}
	public String getRelationBusinessNo() {
		return relationBusinessNo;
	}
	public void setRelationBusinessNo(String relationBusinessNo) {
		this.relationBusinessNo = relationBusinessNo;
	}
	public String getPayNo() {
		return payNo;
	}
	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}
	public String getCounteractFlag() {
		return counteractFlag;
	}
	public void setCounteractFlag(String counteractFlag) {
		this.counteractFlag = counteractFlag;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public List<ExtendDTO> getExtendList() {
		return extendList;
	}
	public void setExtendList(List<ExtendDTO> extendList) {
		this.extendList = extendList;
	}
}
