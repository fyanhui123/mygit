package com.tapi.tcs.vc.webservice.provider.docDoAnnuledOld.bean;

public class BusinessNoDTO {

	/** 业务号 */
	private String businessNo;
	/** 批单对应的保单业务号 */
	private String relationBusinessNo;
	/**缴费期次*/
	private String payNo;
	/**拆分批次*/
	private String batchNo;
	
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
}
