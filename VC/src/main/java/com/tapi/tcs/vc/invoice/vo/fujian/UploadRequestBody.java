package com.tapi.tcs.vc.invoice.vo.fujian;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Body", propOrder = {
    "fpxx",
    "fpkjxx",
    "fpjxxx"
})
public class UploadRequestBody implements Serializable{
	
	@XmlElement(name = "FPXX", required = true)
	protected List<InvoiceDTO> fpxx;
	@XmlElement(name = "FPKJXX", required = true)
	protected List<InvoicePrintDTO> fpkjxx;
	@XmlElement(name = "FPJXXX", required = true)
	protected List<InvoiceRevokeDTO> fpjxxx;
	
	public List<InvoiceDTO> getFpxx() {
		if(fpxx==null){
			fpxx = new ArrayList<InvoiceDTO>();
		}
		return fpxx;
	}
	public void setFpxx(List<InvoiceDTO> fpxx) {
		this.fpxx = fpxx;
	}
	public List<InvoicePrintDTO> getFpkjxx() {
		if(fpkjxx==null){
			fpkjxx = new ArrayList<InvoicePrintDTO>();
		}
		return fpkjxx;
	}
	public void setFpkjxx(List<InvoicePrintDTO> fpkjxx) {
		this.fpkjxx = fpkjxx;
	}
	public List<InvoiceRevokeDTO> getFpjxxx() {
		if(fpjxxx==null){
			fpjxxx = new ArrayList<InvoiceRevokeDTO>();
		}
		return fpjxxx;
	}
	public void setFpjxxx(List<InvoiceRevokeDTO> fpjxxx) {
		this.fpjxxx = fpjxxx;
	}
}
