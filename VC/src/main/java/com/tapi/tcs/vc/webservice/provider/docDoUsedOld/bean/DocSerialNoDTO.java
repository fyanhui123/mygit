package com.tapi.tcs.vc.webservice.provider.docDoUsedOld.bean;

import java.util.List;

public class DocSerialNoDTO {
	
	/** 单证流水号 */
	private String docNum;
	/** 业务号 */
	/*private String businessNo;*/
	/** 批单对应的保单业务号 */
	/*private String relationBusinessNo;*/
	/**业务号列表*/
	private List<BusinessNoDTO> businessNoDTOs;

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

	/*public String getBusinessNo() {
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
	}*/
}
