package com.tapi.tcs.vc.webservice.provider.docDoUsed.bean;

import com.tapi.tcs.common.webservice.RequestHeadDTO;

public class DocDoUsedRequest {
	/**请求头    请求内容**/
	private  RequestHeadDTO requestHead;
	private  DocDoUsedRequestDTO requestBody;
	public RequestHeadDTO getRequestHead() {
		return requestHead;
	}
	public void setRequestHead(RequestHeadDTO requestHead) {
		this.requestHead = requestHead;
	}
	public DocDoUsedRequestDTO getRequestBody() {
		return requestBody;
	}
	public void setRequestBody(DocDoUsedRequestDTO requestBody) {
		this.requestBody = requestBody;
	}
	
}
