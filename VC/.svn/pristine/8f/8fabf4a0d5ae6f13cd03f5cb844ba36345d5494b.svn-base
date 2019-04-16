package com.tapi.tcs.vc.webservice.provider.docDoUsed.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.tapi.tcs.common.webservice.ResponseHeadDTO;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DZHX_RESP", propOrder = {
		"responseHead",
		"responseBody"
})
@XmlRootElement(name = "REQUEST_BODY")
public class DocDoUsedResponse {
 
	/* 返回头  返回内容  **/
	@XmlElement(name = "responseHead", required = true)
	private ResponseHeadDTO responseHead;
	@XmlElement(name = "responseBody", required = true)
	private DocDoUsedResponseDTO responseBody;
	public ResponseHeadDTO getResponseHead() {
		return responseHead;
	}
	public void setResponseHead(ResponseHeadDTO responseHead) {
		this.responseHead = responseHead;
	}
	public DocDoUsedResponseDTO getResponseBody() {
		return responseBody;
	}
	public void setResponseBody(DocDoUsedResponseDTO responseBody) {
		this.responseBody = responseBody;
	}
	
	
}
