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
@XmlType(name = "PTJDFP", propOrder = {
    "invoiceMainSXDTO"
})
@XmlRootElement(name = "PTJDFP")
public class InvoiceSXMainFileDTO implements Serializable{

	@XmlElement(name = "QYXX", required = true)
	 private InvoiceSXMainFileMxDTO invoiceMainSXDTO;

	public void setInvoiceMainSXDTO(InvoiceSXMainFileMxDTO invoiceMainSXDTO) {
		this.invoiceMainSXDTO = invoiceMainSXDTO;
	}

	public InvoiceSXMainFileMxDTO getInvoiceMainSXDTO() {
		return invoiceMainSXDTO;
	}

}
