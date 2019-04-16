package com.tapi.tcs.vc.webservice.provider.cancelDoReversed.bean;

import com.tapi.tcs.common.webservice.RequestHeadDTO;
import com.tapi.tcs.vc.webservice.provider.docDoReversed.bean.DocDoReversedRequestDTO;

public class CancelReversedRequest {
	/**公共请求头*/
	private RequestHeadDTO requestHead;
	/**请求体*/
	private CancelReversedRequestDTO requestBody;
	
	public RequestHeadDTO getRequestHead() {
		return requestHead;
	}
	public void setRequestHead(RequestHeadDTO requestHead) {
		this.requestHead = requestHead;
	}
	public CancelReversedRequestDTO getRequestBody() {
		return requestBody;
	}
	public void setRequestBody(CancelReversedRequestDTO requestBody) {
		this.requestBody = requestBody;
	}
	
	
	

}
