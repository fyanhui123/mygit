package com.tapi.tcs.vc.webservice.provider.docNumInquiryOld.bean;

import com.tapi.tcs.common.webservice.RequestHeadDTO;

public class DocNumInquiryOldRequest {
	/** 公共请求头 */
	private RequestHeadDTO requestHead;
	/** 请求体 */
	private DocNumInquiryOldRequestDTO requestBody;

	public RequestHeadDTO getRequestHead() {
		return requestHead;
	}

	public void setRequestHead(RequestHeadDTO requestHead) {
		this.requestHead = requestHead;
	}

	public DocNumInquiryOldRequestDTO getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(DocNumInquiryOldRequestDTO requestBody) {
		this.requestBody = requestBody;
	}
}
