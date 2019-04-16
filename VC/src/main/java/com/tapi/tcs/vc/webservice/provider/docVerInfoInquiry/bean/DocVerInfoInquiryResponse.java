package com.tapi.tcs.vc.webservice.provider.docVerInfoInquiry.bean;

import com.tapi.tcs.common.webservice.ResponseHeadDTO;
public class DocVerInfoInquiryResponse {
	/* 返回头  返回内容  **/
	private ResponseHeadDTO responseHead;
	private DocVerInfoInquiryResponseDTO responseBody;
	public ResponseHeadDTO getResponseHead() {
		return responseHead;
	}
	public void setResponseHead(ResponseHeadDTO responseHead) {
		this.responseHead = responseHead;
	}
	public DocVerInfoInquiryResponseDTO getResponseBody() {
		return responseBody;
	}
	public void setResponseBody(DocVerInfoInquiryResponseDTO responseBody) {
		this.responseBody = responseBody;
	}
}
