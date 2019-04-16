package com.tapi.tcs.vc.webservice.provider.newCancelInvoice.bean;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.tapi.tcs.common.webservice.HeadDTO;
@XmlRootElement(name = "REQUEST")
public class CancelInvoiceRequest {
	private HeadDTO headDTO;
	private CancelInvoiceRequestBody body;
	public HeadDTO getHeadDTO() {
		return headDTO;
	}
	@XmlElement(name = "HEAD", required = true)
	public void setHeadDTO(HeadDTO headDTO) {
		this.headDTO = headDTO;
	}
	public CancelInvoiceRequestBody getBody() {
		return body;
	}
	@XmlElement(name = "DATA", required = true)
	public void setBody(CancelInvoiceRequestBody body) {
		this.body = body;
	}
}
