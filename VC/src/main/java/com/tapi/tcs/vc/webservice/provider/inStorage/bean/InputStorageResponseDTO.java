package com.tapi.tcs.vc.webservice.provider.inStorage.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "RESULT")
public class InputStorageResponseDTO {
	private String rESULTTYPE;
	private String eRRORINFO;
	public String getrESULTTYPE() {
		return rESULTTYPE;
	}
	@XmlElement(name = "RESULTTYPE", required = true)
	public void setrESULTTYPE(String rESULTTYPE) {
		this.rESULTTYPE = rESULTTYPE;
	}
	public String geteRRORINFO() {
		return eRRORINFO;
	}
	@XmlElement(name = "ERRORINFO", required = true)
	public void seteRRORINFO(String eRRORINFO) {
		this.eRRORINFO = eRRORINFO;
	}

}
