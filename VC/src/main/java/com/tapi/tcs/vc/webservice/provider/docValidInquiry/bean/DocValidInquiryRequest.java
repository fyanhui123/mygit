package com.tapi.tcs.vc.webservice.provider.docValidInquiry.bean;

import com.tapi.tcs.common.webservice.RequestHeadDTO;

public class DocValidInquiryRequest {

	/** 公共请求头 */
	private RequestHeadDTO requestHead;

	/** 请求体 */
	private DocValidInquiryRequestDTO requestBody;

	public RequestHeadDTO getRequestHead() {
		return requestHead;
	}

	public void setRequestHead(RequestHeadDTO requestHead) {
		this.requestHead = requestHead;
	}

	public DocValidInquiryRequestDTO getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(DocValidInquiryRequestDTO requestBody) {
		this.requestBody = requestBody;
	}
}
