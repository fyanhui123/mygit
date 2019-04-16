
package com.tapi.tcs.vc.invoice.taxupload.common.vo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 						单证发票地税上传响应体信息
 * 					
 * 
 * <p>Java class for VCUploadTaxResponseBodyDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="VCUploadTaxResponseBodyDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="invoiceTaxUploadDetails" type="{http://vc.tcs.tapi.com/invoice/taxUpload/common/vo}InvoiceTaxUploadResult" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VCUploadTaxResponseBodyDto", propOrder = {
    "invoiceTaxUploadDetails"
})
public class VCUploadTaxResponseBodyDto {

    @XmlElement(required = true)
    protected List<InvoiceTaxUploadResult> invoiceTaxUploadDetails;

    /**
     * Gets the value of the invoiceTaxUploadDetails property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the invoiceTaxUploadDetails property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInvoiceTaxUploadDetails().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InvoiceTaxUploadResult }
     * 
     * 
     */
    public List<InvoiceTaxUploadResult> getInvoiceTaxUploadDetails() {
        if (invoiceTaxUploadDetails == null) {
            invoiceTaxUploadDetails = new ArrayList<InvoiceTaxUploadResult>();
        }
        return this.invoiceTaxUploadDetails;
    }

}
