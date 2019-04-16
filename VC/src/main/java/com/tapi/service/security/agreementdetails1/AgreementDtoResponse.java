
package com.tapi.service.security.agreementdetails1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for agreementDtoResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="agreementDtoResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.tapi.com/security/agreementdetails1}baseResponseDto">
 *       &lt;sequence>
 *         &lt;element name="PrpDagreementNewDto" type="{http://service.tapi.com/security/agreementdetails1}prpDagreementNewDto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "agreementDtoResponse", propOrder = {
    "prpDagreementNewDto"
})
public class AgreementDtoResponse
    extends BaseResponseDto
{

    @XmlElement(name = "PrpDagreementNewDto")
    protected PrpDagreementNewDto prpDagreementNewDto;

    /**
     * Gets the value of the prpDagreementNewDto property.
     * 
     * @return
     *     possible object is
     *     {@link PrpDagreementNewDto }
     *     
     */
    public PrpDagreementNewDto getPrpDagreementNewDto() {
        return prpDagreementNewDto;
    }

    /**
     * Sets the value of the prpDagreementNewDto property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrpDagreementNewDto }
     *     
     */
    public void setPrpDagreementNewDto(PrpDagreementNewDto value) {
        this.prpDagreementNewDto = value;
    }

}
