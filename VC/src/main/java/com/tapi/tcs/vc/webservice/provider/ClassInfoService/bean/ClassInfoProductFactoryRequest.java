package com.tapi.tcs.vc.webservice.provider.ClassInfoService.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "packet")
public class ClassInfoProductFactoryRequest {
	private CommonProductFactoryRequestHead commonProductFactoryRequestHead;
	private ClassInfoPrpdClass classInfoProductFactoryRequestBody;
	public CommonProductFactoryRequestHead getCommonProductFactoryRequestHead() {
		return commonProductFactoryRequestHead;
	}
	@XmlElement(name = "head", required = true)
	public void setCommonProductFactoryRequestHead(
			CommonProductFactoryRequestHead commonProductFactoryRequestHead) {
		this.commonProductFactoryRequestHead = commonProductFactoryRequestHead;
	}
	public ClassInfoPrpdClass getClassInfoProductFactoryRequestBody() {
		return classInfoProductFactoryRequestBody;
	}
	@XmlElement(name = "body", required = true)
	public void setClassInfoProductFactoryRequestBody(
			ClassInfoPrpdClass classInfoProductFactoryRequestBody) {
		this.classInfoProductFactoryRequestBody = classInfoProductFactoryRequestBody;
	}
	
	
	
}
