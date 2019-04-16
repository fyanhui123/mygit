package com.tapi.tcs.vc.invoice.vo.fujian;

import java.io.Serializable;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Body", propOrder = {
    "nsrjbxx",
    "nsrsqxx",
    "nsrlgqk",
    "dmxx"
})
public class DownloadResponseBody implements Serializable{

    @XmlElement(name = "NSRJBXX", required = true)
    protected  TaxPayerInfoDTO nsrjbxx;
    @XmlElement(name = "NSRSQXX", required = true)
    protected  java.util.List<TaxAuthInfoDTO> nsrsqxx;
    @XmlElement(name = "NSRLGQK", required = true)
    protected  java.util.List<TaxPurchaseInfoDTO> nsrlgqk;
    @XmlElement(name = "DMXX", required = true)
    protected  java.util.List<CodeInfoDTO> dmxx;
    
	
	public TaxPayerInfoDTO getNsrjbxx() {
		return nsrjbxx;
	}
	public void setNsrjbxx(TaxPayerInfoDTO nsrjbxx) {
		this.nsrjbxx = nsrjbxx;
	}
	public java.util.List<TaxAuthInfoDTO> getNsrsqxx() {
		if(nsrsqxx==null)
			nsrsqxx = new ArrayList<TaxAuthInfoDTO>();
		return nsrsqxx;
	}
	public void setNsrsqxx(java.util.List<TaxAuthInfoDTO> nsrsqxx) {
		this.nsrsqxx = nsrsqxx;
	}
	public java.util.List<TaxPurchaseInfoDTO> getNsrlgqk() {
		if(nsrlgqk==null)
			nsrlgqk = new ArrayList<TaxPurchaseInfoDTO>();
		return nsrlgqk;
	}
	public void setNsrlgqk(java.util.List<TaxPurchaseInfoDTO> nsrlgqk) {
		this.nsrlgqk = nsrlgqk;
	}
	public java.util.List<CodeInfoDTO> getDmxx() {
		if(dmxx==null)
			dmxx = new ArrayList<CodeInfoDTO>();
		return dmxx;
	}
	public void setDmxx(java.util.List<CodeInfoDTO> dmxx) {
		this.dmxx = dmxx;
	}
}
