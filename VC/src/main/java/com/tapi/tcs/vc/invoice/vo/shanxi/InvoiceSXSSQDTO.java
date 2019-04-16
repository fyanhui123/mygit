package com.tapi.tcs.vc.invoice.vo.shanxi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SSQ", propOrder = {
	"nsrbm",
	"ssq",
	"wjbh"
})
@XmlRootElement(name = "SSQ")
public class InvoiceSXSSQDTO implements Serializable{

	@XmlElement(name = "NSRBM", required = true)
	private String nsrbm;
	@XmlElement(name = "SSQ", required = true)
	private String ssq;
	@XmlElement(name = "WJBH", required = true)
	private String wjbh;

	public String getNsrbm() {
		return nsrbm;
	}
	public void setNsrbm(String nsrbm) {
		this.nsrbm = nsrbm;
	}
	public String getSsq() {
		return ssq;
	}
	public void setSsq(String ssq) {
		this.ssq = ssq;
	}
	public String getWjbh() {
		return wjbh;
	}
	public void setWjbh(String wjbh) {
		this.wjbh = wjbh;
	}

}
