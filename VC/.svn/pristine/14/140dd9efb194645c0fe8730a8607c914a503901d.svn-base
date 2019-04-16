package com.tapi.tcs.vc.invoice.vo.jiangsu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "invoice_req", propOrder = {
    "head",
    "invoices"
})
@XmlRootElement(name = "invoice_req")
public class JSInvoiceRequestDTO implements Serializable{

	@XmlElement(name = "head", required = true)
	protected JSInvoiceRequestHeadDTO head;
	@XmlElement(name = "invoices", required = true)
	protected List<JSInvoiceDTO> invoices;
	
	public JSInvoiceRequestHeadDTO getHead() {
		return head;
	}
	public void setHead(JSInvoiceRequestHeadDTO head) {
		this.head = head;
	}
	public List<JSInvoiceDTO> getInvoices() {
		if(invoices==null){
			invoices = new ArrayList<JSInvoiceDTO>();
		}
		return invoices;
	}
	public void setInvoices(List<JSInvoiceDTO> invoices) {
		this.invoices = invoices;
	}
}
