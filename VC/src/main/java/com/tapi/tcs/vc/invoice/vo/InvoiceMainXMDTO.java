package com.tapi.tcs.vc.invoice.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "d_ykpxx", propOrder = {
	"fpdm",
	"fphm",
	"jddm",
	"jdhm",
	"fkfmc",
	"fkfhm",
	"hjje",
	"kprmc",
	"kplxdm",
	"chfpdm",
	"chfphm",
	"bchfpdm",
	"bchfphm",
	"hyfl",
	"kpsj",
	"bz",
	"zfsj",
	"czsj",
    "invoiceDetialXMDTOList"
})
public class InvoiceMainXMDTO implements Serializable{

	@XmlElement(name = "fp_dm", required = true)
	private String fpdm;
	@XmlElement(name = "fphm", required = true)
	private String fphm;
	@XmlElement(name = "jddm", required = true)
	private String jddm;
	@XmlElement(name = "jdhm", required = true)
	private String jdhm;
	@XmlElement(name = "fkfmc", required = true)
	private String fkfmc;
	@XmlElement(name = "fkfhm", required = true)
	private String fkfhm;
	@XmlElement(name = "hjje", required = true)
	private String hjje;
	@XmlElement(name = "kprmc", required = true)
	private String kprmc;
	@XmlElement(name = "kplx_dm", required = true)
	private String kplxdm;
	@XmlElement(name = "chfp_dm", required = true)
	private String chfpdm;
	@XmlElement(name = "chfphm", required = true)
	private String chfphm;
	@XmlElement(name = "bchfp_dm", required = true)
	private String bchfpdm;
	@XmlElement(name = "bchfphm", required = true)
	private String bchfphm;
	@XmlElement(name = "hyfl", required = true)
	private String hyfl;
	@XmlElement(name = "kpsj", required = true)
	private String kpsj;
	@XmlElement(name = "bz", required = true)
	private String bz;
	@XmlElement(name = "zfsj", required = true)
	private String zfsj;
	@XmlElement(name = "czsj", required = true)
	private String czsj;
	@XmlElement(name = "d_ykpxxmx", required = true)
	private List<InvoiceDetialXMDTO> invoiceDetialXMDTOList;
	
	public String getFpdm() {
		return fpdm;
	}
	public void setFpdm(String fpdm) {
		this.fpdm = fpdm;
	}
	public String getFphm() {
		return fphm;
	}
	public void setFphm(String fphm) {
		this.fphm = fphm;
	}
	public String getJddm() {
		return jddm;
	}
	public void setJddm(String jddm) {
		this.jddm = jddm;
	}
	public String getJdhm() {
		return jdhm;
	}
	public void setJdhm(String jdhm) {
		this.jdhm = jdhm;
	}
	public String getFkfmc() {
		return fkfmc;
	}
	public void setFkfmc(String fkfmc) {
		this.fkfmc = fkfmc;
	}
	public String getFkfhm() {
		return fkfhm;
	}
	public void setFkfhm(String fkfhm) {
		this.fkfhm = fkfhm;
	}
	public String getHjje() {
		return hjje;
	}
	public void setHjje(String hjje) {
		this.hjje = hjje;
	}
	public String getKprmc() {
		return kprmc;
	}
	public void setKprmc(String kprmc) {
		this.kprmc = kprmc;
	}
	public String getKplxdm() {
		return kplxdm;
	}
	public void setKplxdm(String kplxdm) {
		this.kplxdm = kplxdm;
	}
	public String getChfpdm() {
		return chfpdm;
	}
	public void setChfpdm(String chfpdm) {
		this.chfpdm = chfpdm;
	}
	public String getChfphm() {
		return chfphm;
	}
	public void setChfphm(String chfphm) {
		this.chfphm = chfphm;
	}
	public String getBchfpdm() {
		return bchfpdm;
	}
	public void setBchfpdm(String bchfpdm) {
		this.bchfpdm = bchfpdm;
	}
	public String getBchfphm() {
		return bchfphm;
	}
	public void setBchfphm(String bchfphm) {
		this.bchfphm = bchfphm;
	}
	public String getHyfl() {
		return hyfl;
	}
	public void setHyfl(String hyfl) {
		this.hyfl = hyfl;
	}
	public String getKpsj() {
		return kpsj;
	}
	public void setKpsj(String kpsj) {
		this.kpsj = kpsj;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getZfsj() {
		return zfsj;
	}
	public void setZfsj(String zfsj) {
		this.zfsj = zfsj;
	}
	public String getCzsj() {
		return czsj;
	}
	public void setCzsj(String czsj) {
		this.czsj = czsj;
	}
	public List<InvoiceDetialXMDTO> getInvoiceDetialXMDTOList() {
		if(invoiceDetialXMDTOList==null){
			invoiceDetialXMDTOList = new ArrayList<InvoiceDetialXMDTO>();
		}
		return invoiceDetialXMDTOList;
	}
	public void setInvoiceDetialXMDTOList(
			List<InvoiceDetialXMDTO> invoiceDetialXMDTOList) {
		this.invoiceDetialXMDTOList = invoiceDetialXMDTOList;
	}
	
}
