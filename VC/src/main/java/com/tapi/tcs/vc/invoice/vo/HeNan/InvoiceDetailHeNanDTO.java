package com.tapi.tcs.vc.invoice.vo.HeNan;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "d_ykpxxmx", propOrder = {
    "invoiceDetialHeNanDTOList"
})
@XmlRootElement(name = "d_ykpxxmx")
public class InvoiceDetailHeNanDTO{
	 @XmlElement(name = "d_ykpxxmx_row", required = true)
	 private List<InvoiceDetailRowHeNanDTO> invoiceDetialHeNanDTOList;

    /**
     * @return the invoiceDetialHeNanDTOList
     */
    public List<InvoiceDetailRowHeNanDTO> getInvoiceDetialHeNanDTOList() {
        if(invoiceDetialHeNanDTOList==null){
            invoiceDetialHeNanDTOList=new ArrayList<InvoiceDetailRowHeNanDTO>();
        }
        return invoiceDetialHeNanDTOList;
    }

    /**
     * @param invoiceDetialHeNanDTOList the invoiceDetialHeNanDTOList to set
     */
    public void setInvoiceDetialHeNanDTOList(List<InvoiceDetailRowHeNanDTO> invoiceDetialHeNanDTOList) {
        this.invoiceDetialHeNanDTOList = invoiceDetialHeNanDTOList;
    }

	
}
