package com.tapi.tcs.vc.webservice.provider.docDoReversed.bean;

import java.util.List;

public class DocDoReversedBusinessNoDTO {

	/**业务号*/
	private String businessNo;
	/**预留字段*/
	private List<ExtendDTO> extendsList;
	
	public String getBusinessNo() {
		return businessNo;
	}
	public void setBusinessNo(String businessNo) {
		this.businessNo = businessNo;
	}
	public List<ExtendDTO> getExtendsList() {
		return extendsList;
	}
	public void setExtendsList(List<ExtendDTO> extendsList) {
		this.extendsList = extendsList;
	}
}
