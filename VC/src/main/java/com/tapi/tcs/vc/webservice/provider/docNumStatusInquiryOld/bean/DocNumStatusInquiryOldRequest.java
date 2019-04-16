package com.tapi.tcs.vc.webservice.provider.docNumStatusInquiryOld.bean;

import com.tapi.tcs.common.webservice.RequestHeadDTO;

public class DocNumStatusInquiryOldRequest {

	/** 公共请求头 */
	private RequestHeadDTO requestHead;
	/** 请求体 */
	private DocNumStatusInquiryOldRequestDTO requestBody;

	public DocNumStatusInquiryOldRequestDTO getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(DocNumStatusInquiryOldRequestDTO requestBody) {
		this.requestBody = requestBody;
	}

	public RequestHeadDTO getRequestHead() {
		return requestHead;
	}

	public void setRequestHead(RequestHeadDTO requestHead) {
		this.requestHead = requestHead;
	}

}
