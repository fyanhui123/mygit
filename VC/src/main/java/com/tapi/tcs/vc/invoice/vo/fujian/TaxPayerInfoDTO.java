package com.tapi.tcs.vc.invoice.vo.fujian;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Nsrjbxx", propOrder = {
    "nsrdnbm",
    "nsrsbh",
    "nsrmc",
    "zgswjgdm",
    "zgswjgmc",
    "nsrztdm",
    "swdjblxdm",
    "dzbz",
    "ssds",
    "cywz"
})
public class TaxPayerInfoDTO implements Serializable{

    @XmlElement(name = "NSRDNBM", required = true)
    protected  String nsrdnbm;
    @XmlElement(name = "NSRSBH", required = true)
    protected  String nsrsbh;
    @XmlElement(name = "NSRMC", required = true)
    protected  String nsrmc;
    @XmlElement(name = "ZGSWJGDM", required = true)
    protected  String zgswjgdm;
    @XmlElement(name = "ZGSWJGMC", required = true)
    protected  String zgswjgmc;
    @XmlElement(name = "NSRZTDM", required = true)
    protected  String nsrztdm;
    @XmlElement(name = "SWDJBLXDM", required = true)
    protected  String swdjblxdm;
    @XmlElement(name = "DZBZ", required = true)
    protected  String dzbz;
    @XmlElement(name = "SSDS", required = true)
    protected  String ssds;
    @XmlElement(name = "CYWZ", required = true)
    protected  String cywz;
	public void setNsrdnbm(String value) {
		this.nsrdnbm = value;
	}
	public String getNsrdnbm() {
		return nsrdnbm ;
	}
	public void setNsrsbh(String value) {
		this.nsrsbh = value;
	}
	public String getNsrsbh() {
		return nsrsbh ;
	}
	public void setNsrmc(String value) {
		this.nsrmc = value;
	}
	public String getNsrmc() {
		return nsrmc ;
	}
	public void setZgswjgdm(String value) {
		this.zgswjgdm = value;
	}
	public String getZgswjgdm() {
		return zgswjgdm ;
	}
	public void setZgswjgmc(String value) {
		this.zgswjgmc = value;
	}
	public String getZgswjgmc() {
		return zgswjgmc ;
	}
	public void setNsrztdm(String value) {
		this.nsrztdm = value;
	}
	public String getNsrztdm() {
		return nsrztdm ;
	}
	public String getSwdjblxdm() {
		return swdjblxdm;
	}
	public void setSwdjblxdm(String swdjblxdm) {
		this.swdjblxdm = swdjblxdm;
	}
	public void setDzbz(String value) {
		this.dzbz = value;
	}
	public String getDzbz() {
		return dzbz ;
	}
	public void setSsds(String value) {
		this.ssds = value;
	}
	public String getSsds() {
		return ssds ;
	}
	public void setCywz(String value) {
		this.cywz = value;
	}
	public String getCywz() {
		return cywz ;
	}

}
