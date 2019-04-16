package com.tapi.tcs.vc.webservice.provider.RiskInfoService.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.tapi.tcs.vc.webservice.provider.ClassInfoService.bean.CommonProductFactoryRequestHead;

@XmlRootElement(name="packet")
public class RiskInfoProductFactoryRequest {
	private CommonProductFactoryRequestHead commonProductFactoryRequestHead;
	private PrpdRiskRequestDto riskInfoProductFactoryRequestBody;
	public CommonProductFactoryRequestHead getCommonProductFactoryRequestHead() {
		return commonProductFactoryRequestHead;
	}
	@XmlElement(name = "head", required = true)
	public void setCommonProductFactoryRequestHead(
			CommonProductFactoryRequestHead commonProductFactoryRequestHead) {
		this.commonProductFactoryRequestHead = commonProductFactoryRequestHead;
	}
	public PrpdRiskRequestDto getRiskInfoProductFactoryRequestBody() {
		return riskInfoProductFactoryRequestBody;
	}
	@XmlElement(name = "body", required = true)
	public void setRiskInfoProductFactoryRequestBody(
			PrpdRiskRequestDto riskInfoProductFactoryRequestBody) {
		this.riskInfoProductFactoryRequestBody = riskInfoProductFactoryRequestBody;
	}

	
	
}
