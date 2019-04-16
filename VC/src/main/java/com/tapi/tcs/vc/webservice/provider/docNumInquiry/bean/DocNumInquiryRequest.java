package com.tapi.tcs.vc.webservice.provider.docNumInquiry.bean;

import com.tapi.tcs.common.webservice.RequestHeadDTO;

public class DocNumInquiryRequest {

	/**请求头*/
	private RequestHeadDTO requestHead;
	/**请求体*/
	private DocNumInquiryRequestDTO requestBody;
	
	public RequestHeadDTO getRequestHead() {
		return requestHead;
	}
	public void setRequestHead(RequestHeadDTO requestHead) {
		this.requestHead = requestHead;
	}
	public DocNumInquiryRequestDTO getRequestBody() {
		return requestBody;
	}
	public void setRequestBody(DocNumInquiryRequestDTO requestBody) {
		this.requestBody = requestBody;
	}
}
