package com.tapi.tcs.vc.webservice.provider.insuranceCardDoActivated.bean;

import com.tapi.tcs.common.webservice.RequestHeadDTO;
public class InsuranceCardDoActivatedRequest {

	/** 公共请求头 */
	private RequestHeadDTO requestHead;
	/** 请求体 */
	private InsuranceCardDoActivatedRequestDTO requestBody;
	public RequestHeadDTO getRequestHead() {
		return requestHead;
	}
	public void setRequestHead(RequestHeadDTO requestHead) {
		this.requestHead = requestHead;
	}
	public InsuranceCardDoActivatedRequestDTO getRequestBody() {
		return requestBody;
	}
	public void setRequestBody(InsuranceCardDoActivatedRequestDTO requestBody) {
		this.requestBody = requestBody;
	}
	
	
}
