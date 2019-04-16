package com.tapi.tcs.vc.invoice.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fpxx", propOrder = {
    "invoiceMainXMDTOList"
})
@XmlRootElement(name = "fpxx")
public class InvoiceXMDTO implements Serializable{
	 @XmlElement(name = "d_ykpxx", required = true)
	 private List<InvoiceMainXMDTO> invoiceMainXMDTOList;

	public List<InvoiceMainXMDTO> getInvoiceMainXMDTOList() {
		if(invoiceMainXMDTOList==null){
			invoiceMainXMDTOList = new ArrayList<InvoiceMainXMDTO>();
		}
		return invoiceMainXMDTOList;
	}

	public void setInvoiceMainXMDTOList(List<InvoiceMainXMDTO> invoiceMainXMDTOList) {
		this.invoiceMainXMDTOList = invoiceMainXMDTOList;
	}
}
