package com.tapi.tcs.vc.webservice.provider.docNumStatusInquiry.bean;

import com.tapi.tcs.common.webservice.ResponseHeadDTO;
public class DocNumStatusInquiryResponse {

	/* 返回头  返回内容  **/
	private ResponseHeadDTO responseHead;
	private DocNumStatusInquiryResponseDTO responseBody;
	public ResponseHeadDTO getResponseHead() {
		return responseHead;
	}
	public void setResponseHead(ResponseHeadDTO responseHead) {
		this.responseHead = responseHead;
	}
	public DocNumStatusInquiryResponseDTO getResponseBody() {
		return responseBody;
	}
	public void setResponseBody(DocNumStatusInquiryResponseDTO responseBody) {
		this.responseBody = responseBody;
	}
}
