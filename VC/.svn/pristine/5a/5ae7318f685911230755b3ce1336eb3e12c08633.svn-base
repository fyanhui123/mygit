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
@XmlType(name = "FPXX", propOrder = {
		"invoiceSXBTDTO","invoiceSXMXDTOList"
})
@XmlRootElement(name = "FPXX")
public class InvoiceSXXXDTO implements Serializable{
	
	 @XmlElement(name = "FPBT", required = true)
	 private InvoiceSXBTDTO invoiceSXBTDTO;
	 @XmlElement(name = "FPMX", required = true)
	 private List<InvoiceSXMXDTO> invoiceSXMXDTOList;
	public InvoiceSXBTDTO getInvoiceSXBTDTO() {
		return invoiceSXBTDTO;
	}
	public void setInvoiceSXBTDTO(InvoiceSXBTDTO invoiceSXBTDTO) {
		this.invoiceSXBTDTO = invoiceSXBTDTO;
	}
	public List<InvoiceSXMXDTO> getInvoiceSXMXDTOList() {
		if(invoiceSXMXDTOList==null){
			invoiceSXMXDTOList = new ArrayList<InvoiceSXMXDTO>();
		}
		return invoiceSXMXDTOList;
	}
	public void setInvoiceSXMXDTOList(List<InvoiceSXMXDTO> invoiceSXMXDTOList) {
		this.invoiceSXMXDTOList = invoiceSXMXDTOList;
	}
	 
}
