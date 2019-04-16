package com.tapi.tcs.vc.webservice.provider.insuCardInfoInquiry.bean;

import java.io.Serializable;

public class PlanDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	/**方案代码*/
	private String rationType;
	/**方案名称*/
	private String rationName;
	
	public String getRationType() {
		return rationType;
	}
	public void setRationType(String rationType) {
		this.rationType = rationType;
	}
	public String getRationName() {
		return rationName;
	}
	public void setRationName(String rationName) {
		this.rationName = rationName;
	}
	
}
