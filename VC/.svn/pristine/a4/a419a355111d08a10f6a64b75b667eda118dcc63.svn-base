package com.tapi.tcs.common.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RESP_HEAD", propOrder = {
		"responseCode",
		"responseMessage"
})
public class ResponseHeadDTO {

	/**应用响应代码*/
	@XmlElement(name = "responseCode", required = true)
	private String responseCode;
	
	/**应用响应信息*/
	@XmlElement(name = "responseMessage", required = true)
	private String responseMessage;

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
}
