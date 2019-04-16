package com.tapi.tcs.vc.webservice.provider.docNumStatusInquiryOld.bean;

public class BusinessNoDTO {

	private String businessNo;//业务号
	private String relationBusinessNo;//批单对应的保单业务号
	private String payNo;//缴费期次
	private String counteractFlag;//红冲标志
	private String batchNo;//拆分批次
	
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
}
