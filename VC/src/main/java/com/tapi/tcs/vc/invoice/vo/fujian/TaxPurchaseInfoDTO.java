package com.tapi.tcs.vc.invoice.vo.fujian;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Nsrlgqk", propOrder = {
    "fplgxh",
    "fpdm",
    "fpmc",
    "fpjmdm",
    "qshm",
    "zzhm",
    "fs",
    "bs",
    "mbfs",
    "lgrq",
    "ywlx"
})
public class TaxPurchaseInfoDTO implements Serializable{

    @XmlElement(name = "FPLGXH", required = true)
    protected  String fplgxh;
    @XmlElement(name = "FPDM", required = true)
    protected  String fpdm;
    @XmlElement(name = "FPMC", required = true)
    protected  String fpmc;
    @XmlElement(name = "FPJMDM", required = true)
    protected  String fpjmdm;
    @XmlElement(name = "QSHM", required = true)
    protected  String qshm;
    @XmlElement(name = "ZZHM", required = true)
    protected  String zzhm;
    @XmlElement(name = "FS", required = true)
    protected  String fs;
    @XmlElement(name = "BS", required = true)
    protected  String bs;
    @XmlElement(name = "MBFS", required = true)
    protected  String mbfs;
    @XmlElement(name = "LGRQ", required = true)
    protected  String lgrq;
    @XmlElement(name = "YWLX", required = true)
    protected  String ywlx;
	public void setFplgxh(String value) {
		this.fplgxh = value;
	}
	public String getFplgxh() {
		return fplgxh ;
	}
	public void setFpdm(String value) {
		this.fpdm = value;
	}
	public String getFpdm() {
		return fpdm ;
	}
	public void setFpmc(String value) {
		this.fpmc = value;
	}
	public String getFpmc() {
		return fpmc ;
	}
	public void setFpjmdm(String value) {
		this.fpjmdm = value;
	}
	public String getFpjmdm() {
		return fpjmdm ;
	}
	public void setQshm(String value) {
		this.qshm = value;
	}
	public String getQshm() {
		return qshm ;
	}
	public void setZzhm(String value) {
		this.zzhm = value;
	}
	public String getZzhm() {
		return zzhm ;
	}
	public void setFs(String value) {
		this.fs = value;
	}
	public String getFs() {
		return fs ;
	}
	public void setBs(String value) {
		this.bs = value;
	}
	public String getBs() {
		return bs ;
	}
	public void setMbfs(String value) {
		this.mbfs = value;
	}
	public String getMbfs() {
		return mbfs ;
	}
	public void setLgrq(String value) {
		this.lgrq = value;
	}
	public String getLgrq() {
		return lgrq ;
	}
	public void setYwlx(String value) {
		this.ywlx = value;
	}
	public String getYwlx() {
		return ywlx ;
	}

}
