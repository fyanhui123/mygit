package com.tapi.tcs.vc.webservice.provider.RiskInfoService.bean;

import javax.xml.bind.annotation.XmlElement;

public class PrpdRiskRequestDto {
	private PrpdRisk prpdRisk;
	
	public PrpdRisk getPrpdRisk() {
		return prpdRisk;
	}
	@XmlElement(name = "prpdRisk", required = true)
	public void setPrpdRisk(PrpdRisk prpdRisk) {
		this.prpdRisk = prpdRisk;
	}
}
