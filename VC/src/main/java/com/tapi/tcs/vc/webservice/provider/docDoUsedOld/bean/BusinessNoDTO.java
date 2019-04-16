package com.tapi.tcs.vc.webservice.provider.docDoUsedOld.bean;

public class BusinessNoDTO {

	/** 业务号 */
	private String businessNo;
	/** 批单对应的保单业务号 */
	private String relationBusinessNo;
	/**缴费期次*/
	private String payNo;
	/**红冲标志*/
	private String counteractFlag;
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
