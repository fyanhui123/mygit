package com.tapi.tcs.vc.webservice.provider.inStorage.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import com.tapi.tcs.common.webservice.ResponseDTO;
@XmlRootElement(name = "RESPONSE")
public class InputStorageResponse {
	private ResponseDTO hEAD;
	private InputStorageResponseDTO rESULT;
	public ResponseDTO gethEAD() {
		return hEAD;
	}
	@XmlElement(name = "HEAD", required = true)
	public void sethEAD(ResponseDTO hEAD) {
		this.hEAD = hEAD;
	}
	public InputStorageResponseDTO getrESULT() {
		return rESULT;
	}
	@XmlElement(name = "RESULT", required = true)
	public void setrESULT(InputStorageResponseDTO rESULT) {
		this.rESULT = rESULT;
	}
}
