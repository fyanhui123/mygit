package com.tapi.tcs.vc.webservice.provider.ClassInfoService.bean;

import javax.xml.bind.annotation.XmlElement;
public class ClassInfoPrpdClass {
	private ClassInfoProductFactoryRequestBody prpdClass;

	public ClassInfoProductFactoryRequestBody getPrpdClass() {
		return prpdClass;
	}
	@XmlElement(name = "prpdClass", required = true)
	public void setPrpdClass(ClassInfoProductFactoryRequestBody prpdClass) {
		this.prpdClass = prpdClass;
	}
}
