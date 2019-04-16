package com.tapi.tcs.vc.webservice.DoucmentTypeService.Dto;

import javax.xml.bind.annotation.XmlElement;
public class RequestHeadDto {
	private String  systemId;  //天安分配的系统编码
	private String  requestType;//请求类型，
	private String  serialNo;//此次报文交互唯一标识
	private String  requestTime;//请求时间(格式：yyyy-mm-dd hh:mm:ss)
	private String  secretKey;//加密串
	private String  operation; //操作标识（i插入，u更新，d删除）
	private String  serviceName;//服务名称
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
	public String getRequestTime() {
		return requestTime;
	}
	@XmlElement(name = "requestTime", required = true)
	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}
	public String getSecretKey() {
		return secretKey;
	}
	@XmlElement(name = "secretKey", required = true)
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	public String getOperation() {
		return operation;
	}
	@XmlElement(name = "operation", required = true)
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getServiceName() {
		return serviceName;
	}
	@XmlElement(name = "serviceName", required = true)
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
}
