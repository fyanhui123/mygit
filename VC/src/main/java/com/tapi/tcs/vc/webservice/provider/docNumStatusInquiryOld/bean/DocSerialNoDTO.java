package com.tapi.tcs.vc.webservice.provider.docNumStatusInquiryOld.bean;

import java.util.List;

public class DocSerialNoDTO {

	private String docNum;//单证流水号
	private List<BusinessNoDTO> businessNoDTOs;//业务号列表
	
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
}
