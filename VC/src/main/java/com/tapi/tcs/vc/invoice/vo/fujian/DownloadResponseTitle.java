package com.tapi.tcs.vc.invoice.vo.fujian;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Title", propOrder = {
    "requestUuid",
    "responseUuid",
    "responseTime",
    "success",
    "errorCode",
    "errorDesc"
})
public class DownloadResponseTitle implements Serializable{

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
	public void setRequestUuid(String value) {
		this.requestUuid = value;
	}
	public String getRequestUuid() {
		return requestUuid ;
	}
	public void setResponseUuid(String value) {
		this.responseUuid = value;
	}
	public String getResponseUuid() {
		return responseUuid ;
	}
	public void setResponseTime(String value) {
		this.responseTime = value;
	}
	public String getResponseTime() {
		return responseTime ;
	}
	public void setSuccess(String value) {
		this.success = value;
	}
	public String getSuccess() {
		return success ;
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
