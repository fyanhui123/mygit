package com.tapi.tcs.vc.invoice.vo.fujian;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Nsrsqxx", propOrder = {
    "fpjmdm",
    "fpmc",
    "kjfsdm",
    "fsfpsx",
    "jyyjsx",
    "dzfpxe",
    "qyrq",
    "tyrq",
    "nsrsqmx"
})
public class TaxAuthInfoDTO implements Serializable{

    @XmlElement(name = "FPJMDM", required = true)
    protected  String fpjmdm;
    @XmlElement(name = "FPMC", required = true)
    protected  String fpmc;
    @XmlElement(name = "KJFSDM", required = true)
    protected  String kjfsdm;
    @XmlElement(name = "FSFPSX", required = true)
    protected  String fsfpsx;
    @XmlElement(name = "JYYJSX", required = true)
    protected  String jyyjsx;
    @XmlElement(name = "DZFPXE", required = true)
    protected  String dzfpxe;
    @XmlElement(name = "QYRQ", required = true)
    protected  String qyrq;
    @XmlElement(name = "TYRQ", required = true)
    protected  String tyrq;
    @XmlElement(name = "NSRSQMX", required = true)
    protected  java.util.List<TaxAuthDetailDTO> nsrsqmx;
	public void setFpjmdm(String value) {
		this.fpjmdm = value;
	}
	public String getFpjmdm() {
		return fpjmdm ;
	}
	public void setFpmc(String value) {
		this.fpmc = value;
	}
	public String getFpmc() {
		return fpmc ;
	}
	public void setKjfsdm(String value) {
		this.kjfsdm = value;
	}
	public String getKjfsdm() {
		return kjfsdm ;
	}
	public void setFsfpsx(String value) {
		this.fsfpsx = value;
	}
	public String getFsfpsx() {
		return fsfpsx ;
	}
	public void setJyyjsx(String value) {
		this.jyyjsx = value;
	}
	public String getJyyjsx() {
		return jyyjsx ;
	}
	public void setDzfpxe(String value) {
		this.dzfpxe = value;
	}
	public String getDzfpxe() {
		return dzfpxe ;
	}
	public void setQyrq(String value) {
		this.qyrq = value;
	}
	public String getQyrq() {
		return qyrq ;
	}
	public String getTyrq() {
		return tyrq;
	}
	public void setTyrq(String tyrq) {
		this.tyrq = tyrq;
	}
	public java.util.List<TaxAuthDetailDTO> getNsrsqmx() {
		if (nsrsqmx == null) {
	          nsrsqmx = new java.util.ArrayList<TaxAuthDetailDTO>(); }
		return nsrsqmx;
	}
	public void setNsrsqmx(java.util.List<TaxAuthDetailDTO> nsrsqmx) {
		this.nsrsqmx = nsrsqmx;
	}

}
