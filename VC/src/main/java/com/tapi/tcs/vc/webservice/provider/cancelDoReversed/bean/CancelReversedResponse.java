package com.tapi.tcs.vc.webservice.provider.cancelDoReversed.bean;

import com.tapi.tcs.common.webservice.ResponseHeadDTO;


public class CancelReversedResponse {
	/**公共响应头*/
	private ResponseHeadDTO responseHead;
	/**响应体*/
	private CancelReversedResponseDTO responseBody;
	public ResponseHeadDTO getResponseHead() {
		return responseHead;
	}
	public void setResponseHead(ResponseHeadDTO responseHead) {
		this.responseHead = responseHead;
	}
	public CancelReversedResponseDTO getResponseBody() {
		return responseBody;
	}
	public void setResponseBody(CancelReversedResponseDTO responseBody) {
		this.responseBody = responseBody;
	}
}
