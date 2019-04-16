package com.tapi.tcs.vc.webservice.provider.newInvoiceDoUsed.bean;

import javax.xml.bind.annotation.XmlElement;

public class PolicyDTO {
	private String policyNo;//保单号码
	private String payNo;//缴费期次
	private String endorNo; //批改序号
	

	public String getPolicyNo() {
		return policyNo;
	}
	@XmlElement(name = "BDHM", required = true)
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	public String getPayNo() {
		return payNo;
	}
	@XmlElement(name = "JFQC", required = true)
	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}
	public String getEndorNo() {
		return endorNo;
	}
	@XmlElement(name = "PGXH", required = true)
	public void setEndorNo(String endorNo) {
		this.endorNo = endorNo;
	}
	

}
