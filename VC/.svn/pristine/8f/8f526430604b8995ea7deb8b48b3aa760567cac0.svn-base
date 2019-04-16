package com.tapi.tcs.vc.webservice.provider.ClassInfoService.bean;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


public class CommonProductFactoryRequestHead {
	/**
	 * 系统编码参见系统代码表
	 */
	private String systemId;
	/**
	 * 请求类型
	 */
	private String requestType;
	/**
	 * 报文交互唯一标识
	 */
	private String serialNo;
	/**
	 * 服务名称
	 */
	private String serviceName;
	/**
	 * 操作标识（I插入，U更新，D删除）
	 */
	private String operation;
	/**
	 * 请求时间 (格式：yyyy-MM-dd HH:mm:ss)
	 */
	private String requestTime;
	/**
	 * 加密串
	 */
	private String secretKey;
	public String getSystemId() {
		return systemId;
	}
	@XmlElement(name = "systemId", required = true)
	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}
	public String getRequestType() {
		return requestType;
	}
	@XmlElement(name = "requestType", required = true)
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}	
	
	public String getSerialNo() {
		return serialNo;
	}
	@XmlElement(name = "serialNo", required = true)
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	
	public String getServiceName() {
		return serviceName;
	}
	@XmlElement(name = "serviceName", required = true)
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getOperation() {
		return operation;
	}
	@XmlElement(name = "operation", required = true)
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getSecretKey() {
		return secretKey;
	}
	@XmlElement(name = "requestTime", required = false)
	public String getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}
	@XmlElement(name = "secretKey", required = true)
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	
}

