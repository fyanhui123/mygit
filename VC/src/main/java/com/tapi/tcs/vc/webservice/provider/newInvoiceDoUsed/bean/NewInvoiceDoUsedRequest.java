package com.tapi.tcs.vc.webservice.provider.newInvoiceDoUsed.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.tapi.tcs.common.webservice.HeadDTO;
@XmlRootElement(name = "REQUEST")
public class NewInvoiceDoUsedRequest {
	private HeadDTO headDTO;
	private NewInvoiceDoUsedRequestDTO requestBody;
	public HeadDTO getHeadDTO() {
		return headDTO;
	}
	@XmlElement(name = "HEAD", required = true)
	public void setHeadDTO(HeadDTO headDTO) {
		this.headDTO = headDTO;
	}
	public NewInvoiceDoUsedRequestDTO getRequestBody() {
		return requestBody;
	}
	@XmlElement(name = "DATA", required = true)
	public void setRequestBody(NewInvoiceDoUsedRequestDTO requestBody) {
		this.requestBody = requestBody;
	}
}
