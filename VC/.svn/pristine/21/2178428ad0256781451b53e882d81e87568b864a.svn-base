package com.tapi.tcs.vc.webservice.provider.docDoAnnuled.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DZZF_BUS", propOrder = {
    "businessNo",
    "relationBusinessNo",
    "payNo",
    "batchNo",
    "extendList"
})
public class BusinessNoDTO {

	/** 业务号 ***/
	@XmlElement(name = "businessNo", required = true)
	private String businessNo;
	/*** 批单对应的保单业务号 ***/
	@XmlElement(name = "relationBusinessNo", required = true)
	private String relationBusinessNo;
	/**缴费期次*/
	@XmlElement(name = "payNo", required = true)
	private String payNo;
	/**拆分批次*/
	@XmlElement(name = "batchNo", required = true)
	private String batchNo;
	/**预留字段*/
	@XmlElement(name = "extendList", required = true)
	private List<ExtendDTO> extendList;

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
