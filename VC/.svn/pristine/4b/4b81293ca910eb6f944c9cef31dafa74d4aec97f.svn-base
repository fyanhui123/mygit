package com.tapi.tcs.vc.webservice.provider.inStorage.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import com.tapi.tcs.common.webservice.HeadDTO;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "REQUEST")
public class InputStorageRequest {
	@XmlElement(name = "HEAD", required = true)
	private HeadDTO headDTO;
	@XmlElement(name = "DATA", required = true)
	private InputStorageRequestDTO inputStorageRequestDTO;
	public HeadDTO getHeadDTO() {
		return headDTO;
	}
	public void setHeadDTO(HeadDTO headDTO) {
		this.headDTO = headDTO;
	}
	public InputStorageRequestDTO getInputStorageRequestDTO() {
		return inputStorageRequestDTO;
	}
	public void setInputStorageRequestDTO(
			InputStorageRequestDTO inputStorageRequestDTO) {
		this.inputStorageRequestDTO = inputStorageRequestDTO;
	}
}
