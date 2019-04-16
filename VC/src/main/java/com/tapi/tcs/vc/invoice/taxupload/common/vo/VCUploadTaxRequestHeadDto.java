
package com.tapi.tcs.vc.invoice.taxupload.common.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 						单证发票地税上传请求头信息
 * 					
 * 
 * <p>Java class for VCUploadTaxRequestHeadDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="VCUploadTaxRequestHeadDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="taxPayerLoginXML" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VCUploadTaxRequestHeadDto", propOrder = {
    "taxPayerLoginXML"
})
public class VCUploadTaxRequestHeadDto {

    @XmlElement(required = true)
    protected String taxPayerLoginXML;

    /**
     * Gets the value of the taxPayerLoginXML property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxPayerLoginXML() {
        return taxPayerLoginXML;
    }

    /**
     * Sets the value of the taxPayerLoginXML property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxPayerLoginXML(String value) {
        this.taxPayerLoginXML = value;
    }

}
