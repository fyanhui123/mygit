package com.tapi.tcs.vc.webservice.provider.outStorage.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import com.tapi.tcs.common.webservice.HeadDTO;
@XmlRootElement(name = "REQUEST")
public class OutStorageRequest {
	private HeadDTO headDTO;
	private OutStorageRequestDto outStorageRequestDTO;
	public HeadDTO getHeadDTO() {
		return headDTO;
	}
	@XmlElement(name = "HEAD", required = true)
	public void setHeadDTO(HeadDTO headDTO) {
		this.headDTO = headDTO;
	}
	public OutStorageRequestDto getOutStorageRequestDTO() {
		return outStorageRequestDTO;
	}
	@XmlElement(name = "DATA", required = true)
	public void setOutStorageRequestDTO(OutStorageRequestDto outStorageRequestDTO) {
		this.outStorageRequestDTO = outStorageRequestDTO;
	}
}
