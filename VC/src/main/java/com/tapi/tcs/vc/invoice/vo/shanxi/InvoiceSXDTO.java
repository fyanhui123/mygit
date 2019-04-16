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
@XmlType(name = "PTJDFP", propOrder = { "invoiceSXSSQDTO","invoiceSXXXDTOList" })
@XmlRootElement(name = "PTJDFP")
public class InvoiceSXDTO implements Serializable {

	@XmlElement(name = "SSQ", required = true)
	private InvoiceSXSSQDTO invoiceSXSSQDTO;
	@XmlElement(name = "FPXX", required = true)
	private List<InvoiceSXXXDTO>  invoiceSXXXDTOList;
	
	public void setInvoiceSXSSQDTO(InvoiceSXSSQDTO invoiceSXSSQDTO) {
		this.invoiceSXSSQDTO = invoiceSXSSQDTO;
	}
	public InvoiceSXSSQDTO getInvoiceSXSSQDTO() {
		return invoiceSXSSQDTO;
	}
	public List<InvoiceSXXXDTO> getInvoiceSXXXDTOList() {
		if (invoiceSXXXDTOList == null) {
			invoiceSXXXDTOList = new ArrayList<InvoiceSXXXDTO>();
		}
		return invoiceSXXXDTOList;
	}
	public void setInvoiceSXXXDTOList(List<InvoiceSXXXDTO> invoiceSXXXDTOList) {
		this.invoiceSXXXDTOList = invoiceSXXXDTOList;
	}

	
}
