package com.tapi.tcs.vc.webservice.provider.docVerInquiry.bean;

import com.tapi.tcs.common.webservice.ResponseHeadDTO;
public class DocVerInquiryResponse {
	/* 返回头  返回内容  **/
	private ResponseHeadDTO responseHead;
	private DocVerInquiryResponseDTO responseBody;
	public ResponseHeadDTO getResponseHead() {
		return responseHead;
	}
	public void setResponseHead(ResponseHeadDTO responseHead) {
		this.responseHead = responseHead;
	}
	public DocVerInquiryResponseDTO getResponseBody() {
		return responseBody;
	}
	public void setResponseBody(DocVerInquiryResponseDTO responseBody) {
		this.responseBody = responseBody;
	}
}
