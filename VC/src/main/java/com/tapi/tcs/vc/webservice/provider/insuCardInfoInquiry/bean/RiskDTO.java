package com.tapi.tcs.vc.webservice.provider.insuCardInfoInquiry.bean;

import java.io.Serializable;
import java.util.List;

public class RiskDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	/**险种代码*/
	private String riskCode;
	/**险种名称*/
	private String riskName;
	/**方案列表*/
	private List<PlanDTO> planDTOs;
	
	public String getRiskCode() {
		return riskCode;
	}
	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}
	public String getRiskName() {
		return riskName;
	}
	public void setRiskName(String riskName) {
		this.riskName = riskName;
	}
	public List<PlanDTO> getPlanDTOs() {
		return planDTOs;
	}
	public void setPlanDTOs(List<PlanDTO> planDTOs) {
		this.planDTOs = planDTOs;
	}
}
