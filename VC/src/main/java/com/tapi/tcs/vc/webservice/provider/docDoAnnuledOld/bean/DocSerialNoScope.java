package com.tapi.tcs.vc.webservice.provider.docDoAnnuledOld.bean;

import java.util.List;

public class DocSerialNoScope {
	
	/** 单证起始流水号 */
	/*private String docBeginSerialNo;*/
	/** 单证终止流水号*/
	/*private String docEndSerialNo;*/
	/****保单号**/
	/*private String  policyNo;*/
	/***批单号**/
	/*private String endorseNo;*/
	
	/** 单证流水号 */
	private String docNum;
	/** 是否红冲作废 */
	private String isCounteract;
	/**业务号列表*/
	private List<BusinessNoDTO> businessNoDTOs;
	
	/*public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	public String getEndorseNo() {
		return endorseNo;
	}
	public void setEndorseNo(String endorseNo) {
		this.endorseNo = endorseNo;
	}
	public String getDocBeginSerialNo() {
		return docBeginSerialNo;
	}
	public void setDocBeginSerialNo(String docBeginSerialNo) {
		this.docBeginSerialNo = docBeginSerialNo;
	}
	public String getDocEndSerialNo() {
		return docEndSerialNo;
	}
	public void setDocEndSerialNo(String docEndSerialNo) {
		this.docEndSerialNo = docEndSerialNo;
	}*/
	public String getDocNum() {
		return docNum;
	}
	public void setDocNum(String docNum) {
		this.docNum = docNum;
	}
	public String getIsCounteract() {
		return isCounteract;
	}
	public void setIsCounteract(String isCounteract) {
		this.isCounteract = isCounteract;
	}
	public List<BusinessNoDTO> getBusinessNoDTOs() {
		return businessNoDTOs;
	}
	public void setBusinessNoDTOs(List<BusinessNoDTO> businessNoDTOs) {
		this.businessNoDTOs = businessNoDTOs;
	}
}
