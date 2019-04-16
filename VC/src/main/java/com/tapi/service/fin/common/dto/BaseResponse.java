
package com.tapi.service.fin.common.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import com.tapi.service.fin.visa.intf.PrePayFeeActCancelCardResponse;
import com.tapi.service.fin.visa.intf.PrePayFeeActCardResponse;
import com.tapi.service.fin.visa.intf.RefundActCardResponse;
import com.tapi.service.fin.visa.intf.VisaAccountToPaymentResponse;


/**
 * <p>Java class for baseResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="baseResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="HEAD" type="{http://service.tapi.com/fin/common/dto}responseHeadDTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "baseResponse", propOrder = {
    "head"
})
@XmlSeeAlso({
    PrePayFeeActCancelCardResponse.class,
    PrePayFeeActCardResponse.class,
    RefundActCardResponse.class,
    VisaAccountToPaymentResponse.class
})
public class BaseResponse {

    @XmlElement(name = "HEAD")
    protected ResponseHeadDTO head;

    /**
     * Gets the value of the head property.
     * 
     * @return
     *     possible object is
     *     {@link ResponseHeadDTO }
     *     
     */
    public ResponseHeadDTO getHEAD() {
        return head;
    }

    /**
     * Sets the value of the head property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseHeadDTO }
     *     
     */
    public void setHEAD(ResponseHeadDTO value) {
        this.head = value;
    }

}
