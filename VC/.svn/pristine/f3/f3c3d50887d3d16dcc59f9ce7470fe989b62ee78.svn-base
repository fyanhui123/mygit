
package com.tapi.service.security.agreementdetails;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for agreementDetailResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="agreementDetailResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.tapi.com/security/agreementdetails}baseResponseDto">
 *       &lt;sequence>
 *         &lt;element name="agreementNewDetails" type="{http://service.tapi.com/security/agreementdetails}prpDagreementNewDto" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "agreementDetailResponse", propOrder = {
    "agreementNewDetails"
})
public class AgreementDetailResponse
    extends BaseResponseDto
{

    @XmlElement(nillable = true)
    protected List<PrpDagreementNewDto> agreementNewDetails;

    /**
     * Gets the value of the agreementNewDetails property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the agreementNewDetails property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAgreementNewDetails().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PrpDagreementNewDto }
     * 
     * 
     */
    public List<PrpDagreementNewDto> getAgreementNewDetails() {
        if (agreementNewDetails == null) {
            agreementNewDetails = new ArrayList<PrpDagreementNewDto>();
        }
        return this.agreementNewDetails;
    }

}
