package com.tapi.tcs.vc.baseinfo.vo;

import com.tapi.tcs.vc.schema.model.VcAbnormalVerification;


public class DetileNormalVO extends VcAbnormalVerification{
	private String businessNo;//保单号
	private String batchNo;//拆分期次
	private String payNo;//缴费期次
	public String getBusinessNo() {
		return businessNo;
	}
	public void setBusinessNo(String businessNo) {
		this.businessNo = businessNo;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public String getPayNo() {
		return payNo;
	}
	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}
	
}
