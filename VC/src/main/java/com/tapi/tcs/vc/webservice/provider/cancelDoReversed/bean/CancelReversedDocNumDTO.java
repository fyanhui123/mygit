package com.tapi.tcs.vc.webservice.provider.cancelDoReversed.bean;

import java.util.List;

public class CancelReversedDocNumDTO {

	/**单证流水号*/
	private String docNum;
	/**业务号*/
	private String bussinessNo;
	/**预留字段*/
	private List<ExtendDTO> extendsList;
	public String getDocNum() {
		return docNum;
	}
	public void setDocNum(String docNum) {
		this.docNum = docNum;
	}


	public List<ExtendDTO> getExtendsList() {
		return extendsList;
	}
	public void setExtendsList(List<ExtendDTO> extendsList) {
		this.extendsList = extendsList;
	}
	public String getBussinessNo() {
		return bussinessNo;
	}
	public void setBussinessNo(String bussinessNo) {
		this.bussinessNo = bussinessNo;
	}

	
}
