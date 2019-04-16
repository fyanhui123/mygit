package com.tapi.tcs.vc.webservice.provider.docNumInquiryNew.bean;

import com.tapi.tcs.common.webservice.ResponseHeadDTO;

public class DocNumInquiryNewResponse {

	/**响应头*/
	private ResponseHeadDTO responseHead;
	/**响应体*/
	private DocNumInquiryNewResponseDTO responseBody;
	
	public ResponseHeadDTO getResponseHead() {
		return responseHead;
	}
	public void setResponseHead(ResponseHeadDTO responseHead) {
		this.responseHead = responseHead;
	}
	public DocNumInquiryNewResponseDTO getResponseBody() {
		return responseBody;
	}
	public void setResponseBody(DocNumInquiryNewResponseDTO responseBody) {
		this.responseBody = responseBody;
	}
}
