package com.tapi.tcs.vc.webservice.provider.RiskInfoService.bean;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

public class CommonProductFactoryResponseHead {
	/**
	 * 接口类型
	 */
	private String requestType;
	/**
	 * 0000：成功 其他：失败
	 */
	private String responseCode;
	/**
	 * 唯一标示
	 */
	private String serialNo;
	/**
	 * 返回时间 (格式：yyyy-MM-dd HH:mm:ss)
	 */
	private Date responseTime;
	/**
	 * 错误代码
	 */
	private String erroCode;
	/**
	 * 错误说明
	 */
	private String errorMessage;
	public String getRequestType() {
		return requestType;
	}
	@XmlElement(name = "requestType", required = true)
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	public String getResponseCode() {
		return responseCode;
	}
	@XmlElement(name = "responseCode", required = true)
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getSerialNo() {
		return serialNo;
	}
	@XmlElement(name = "serialNo", required = true)
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public Date getResponseTime() {
		return responseTime;
	}
	@XmlElement(name = "responseTime", required = true)
	public void setResponseTime(Date responseTime) {
		this.responseTime = responseTime;
	}
	public String getErroCode() {
		return erroCode;
	}
	@XmlElement(name = "errorCode", required = true)
	public void setErroCode(String erroCode) {
		this.erroCode = erroCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	@XmlElement(name = "errorMessage", required = true)
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
