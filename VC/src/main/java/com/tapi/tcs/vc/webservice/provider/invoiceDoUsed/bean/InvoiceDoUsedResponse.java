package com.tapi.tcs.vc.webservice.provider.invoiceDoUsed.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.tapi.tcs.common.webservice.ResponseHeadDTO;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FPHX_RESP", propOrder = {
		"responseHead",
		"responseBody"
})
@XmlRootElement(name = "REQUEST_BODY")
public class InvoiceDoUsedResponse {
	
	@XmlElement(name = "responseHead", required = true)
	private ResponseHeadDTO responseHead;
	@XmlElement(name = "responseBody", required = true)
	private InvoiceDoUsedResponseDTO responseBody;
	public ResponseHeadDTO getResponseHead() {
		return responseHead;
	}
	public void setResponseHead(ResponseHeadDTO responseHead) {
		this.responseHead = responseHead;
	}
	public InvoiceDoUsedResponseDTO getResponseBody() {
		return responseBody;
	}
	public void setResponseBody(InvoiceDoUsedResponseDTO responseBody) {
		this.responseBody = responseBody;
	}
	
	
	
}
