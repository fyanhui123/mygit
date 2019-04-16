package com.tapi.tcs.vc.webservice.DoucmentTypeService.Dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="packet")
public class Request {
	private RequestHeadDto requestHeadDto;
	private RequestBody requestBody;
	public RequestHeadDto getRequestHeadDto() {
		return requestHeadDto;
	}
	@XmlElement(name = "head", required = true)
	public void setRequestHeadDto(RequestHeadDto requestHeadDto) {
		this.requestHeadDto = requestHeadDto;
	}
	public RequestBody getRequestBody() {
		return requestBody;
	}
	@XmlElement(name = "body", required = true)
	public void setRequestBody(RequestBody requestBody) {
		this.requestBody = requestBody;
	}
}
