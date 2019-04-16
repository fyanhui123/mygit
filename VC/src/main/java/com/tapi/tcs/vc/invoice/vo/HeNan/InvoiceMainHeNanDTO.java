package com.tapi.tcs.vc.invoice.vo.HeNan;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "d_ykpxx", propOrder = {
    "invoiceMainHeNanList"
})
@XmlRootElement(name = "d_ykpxx")
public class InvoiceMainHeNanDTO {
	 @XmlElement(name = "d_ykpxx_row", required = true)
	 private List<InvoiceMainRowHeNanDTO> invoiceMainHeNanList;

    /**
     * @return the invoiceMainHeNanList
     */
    public List<InvoiceMainRowHeNanDTO> getInvoiceMainHeNanList() {
        if(invoiceMainHeNanList==null){
            invoiceMainHeNanList = new ArrayList<InvoiceMainRowHeNanDTO>();
        }
        return invoiceMainHeNanList;
    }

    /**
     * @param invoiceMainHeNanList the invoiceMainHeNanList to set
     */
    public void setInvoiceMainHeNanList(List<InvoiceMainRowHeNanDTO> invoiceMainHeNanList) {
        this.invoiceMainHeNanList = invoiceMainHeNanList;
    }

	 
	
	

   

	
}
