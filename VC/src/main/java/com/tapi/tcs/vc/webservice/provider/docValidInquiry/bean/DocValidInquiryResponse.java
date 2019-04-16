package com.tapi.tcs.vc.webservice.provider.docValidInquiry.bean;

import com.tapi.tcs.common.webservice.ResponseHeadDTO;

public class DocValidInquiryResponse {
	
	/**公共响应头*/
	private ResponseHeadDTO responseHead;
	
	/**响应体*/
	private DocValidInquiryResponseDTO responseBody;

	public ResponseHeadDTO getResponseHead() {
		return responseHead;
	}

	public void setResponseHead(ResponseHeadDTO responseHead) {
		this.responseHead = responseHead;
	}

	public DocValidInquiryResponseDTO getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(DocValidInquiryResponseDTO responseBody) {
		this.responseBody = responseBody;
	}
}
