package com.tapi.tcs.vc.webservice.provider.docNumInquiryNew.bean;

import com.tapi.tcs.common.webservice.RequestHeadDTO;

public class DocNumInquiryNewRequest {

	/**请求头*/
	private RequestHeadDTO requestHead;
	/**请求体*/
	private DocNumInquiryNewRequestDTO requestBody;
	
	public RequestHeadDTO getRequestHead() {
		return requestHead;
	}
	public void setRequestHead(RequestHeadDTO requestHead) {
		this.requestHead = requestHead;
	}
	public DocNumInquiryNewRequestDTO getRequestBody() {
		return requestBody;
	}
	public void setRequestBody(DocNumInquiryNewRequestDTO requestBody) {
		this.requestBody = requestBody;
	}
}
