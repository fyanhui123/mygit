package com.tapi.tcs.vc.webservice.provider.docVerInquiry.bean;

import com.tapi.tcs.common.webservice.RequestHeadDTO;

public class DocVerInquiryRequest {

	/**请求头    请求内容**/
	private  RequestHeadDTO  requestHead;
	
	private  DocVerInquiryRequestDTO requestBody;

	public RequestHeadDTO getRequestHead() {
		return requestHead;
	}

	public void setRequestHead(RequestHeadDTO requestHead) {
		this.requestHead = requestHead;
	}

	public DocVerInquiryRequestDTO getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(DocVerInquiryRequestDTO requestBody) {
		this.requestBody = requestBody;
	}  
}
