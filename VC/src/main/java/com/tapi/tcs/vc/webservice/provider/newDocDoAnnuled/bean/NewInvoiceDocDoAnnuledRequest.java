package com.tapi.tcs.vc.webservice.provider.newDocDoAnnuled.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.tapi.tcs.common.webservice.HeadDTO;
@XmlRootElement(name = "REQUEST")
public class NewInvoiceDocDoAnnuledRequest {
	private HeadDTO headDTO;
	private NewInvoiceDocDoAnnuledRequestBody body;
	public HeadDTO getHeadDTO() {
		return headDTO;
	}
	@XmlElement(name = "HEAD", required = true)
	public void setHeadDTO(HeadDTO headDTO) {
		this.headDTO = headDTO;
	}
	public NewInvoiceDocDoAnnuledRequestBody getBody() {
		return body;
	}
	@XmlElement(name = "DATA", required = true)
	public void setBody(NewInvoiceDocDoAnnuledRequestBody body) {
		this.body = body;
	}
}
