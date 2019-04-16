package com.tapi.tcs.common.webservice;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import com.tapi.tcs.common.webservice.ResponseDTO;
@XmlRootElement(name = "RESPONSE")
public class StorageResponse {
	private ResponseDTO head;
	private StorageResponseDTO result;
	public ResponseDTO getHead() {
		return head;
	}
	@XmlElement(name = "HEAD", required = true)
	public void setHead(ResponseDTO head) {
		this.head = head;
	}
	public StorageResponseDTO getResult() {
		return result;
	}
	@XmlElement(name = "RESULT", required = true)
	public void setResult(StorageResponseDTO result) {
		this.result = result;
	}

	


	
	
}
