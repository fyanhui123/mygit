
package com.tapi.service.fin.common.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import com.tapi.service.fin.visa.intf.PrePayFeeActCardRequest;
import com.tapi.service.fin.visa.intf.PrePayFeeCancelActCardRequest;
import com.tapi.service.fin.visa.intf.RefundActCardRequest;
import com.tapi.service.fin.visa.intf.VisaAccountToPaymentRequest;


/**
 * <p>Java class for baseRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="baseRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="HEAD" type="{http://service.tapi.com/fin/common/dto}requestHeadDTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "baseRequest", propOrder = {
    "head"
})
@XmlSeeAlso({
    PrePayFeeActCardRequest.class,
    VisaAccountToPaymentRequest.class,
    RefundActCardRequest.class,
    PrePayFeeCancelActCardRequest.class
})
public class BaseRequest {

    @XmlElement(name = "HEAD")
    protected RequestHeadDTO head;

    /**
     * Gets the value of the head property.
     * 
     * @return
     *     possible object is
     *     {@link RequestHeadDTO }
     *     
     */
    public RequestHeadDTO getHEAD() {
        return head;
    }

    /**
     * Sets the value of the head property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestHeadDTO }
     *     
     */
    public void setHEAD(RequestHeadDTO value) {
        this.head = value;
    }

}
