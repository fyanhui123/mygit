package com.tapi.tcs.common.webservice;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "RESULT")
public class StorageResponseDTO {
	private String resultType;
	private String errorInfo;
	public String getResultType() {
		return resultType;
	}
	@XmlElement(name = "RESULTTYPE", required = true)
	public void setResultType(String resultType) {
		this.resultType = resultType;
	}
	public String getErrorInfo() {
		return errorInfo;
	}
	@XmlElement(name = "ERRORINFO", required = true)
	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}
}
