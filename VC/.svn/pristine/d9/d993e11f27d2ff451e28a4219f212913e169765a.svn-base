package com.tapi.tcs.vc.invoice.vo.fujian;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Response", propOrder = {
		"requestUuid",
	    "responseUuid",
	    "responseTime",
	    "success",
	    "errorCode",
	    "errorDesc"
})
@XmlRootElement(name = "RESPONSE")
public class UploadResponse implements Serializable{
	
	@XmlElement(name = "REQUEST_UUID", required = true)
    protected  String requestUuid;
    @XmlElement(name = "RESPONSE_UUID", required = true)
    protected  String responseUuid;
    @XmlElement(name = "RESPONSE_TIME", required = true)
    protected  String responseTime;
    @XmlElement(name = "SUCCESS", required = true)
    protected  String success;
    @XmlElement(name = "ERROR_CODE", required = true)
    protected  String errorCode;
    @XmlElement(name = "ERROR_DESC", required = true)
    protected  String errorDesc;
    
	public String getRequestUuid() {
		return requestUuid;
	}
	public void setRequestUuid(String requestUuid) {
		this.requestUuid = requestUuid;
	}
	public String getResponseUuid() {
		return responseUuid;
	}
	public void setResponseUuid(String responseUuid) {
		this.responseUuid = responseUuid;
	}
	public String getResponseTime() {
		return responseTime;
	}
	public void setResponseTime(String responseTime) {
		this.responseTime = responseTime;
	}
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorDesc() {
		return errorDesc;
	}
	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}
}
