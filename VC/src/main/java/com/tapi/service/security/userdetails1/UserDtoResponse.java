
package com.tapi.service.security.userdetails1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for userDtoResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="userDtoResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.tapi.com/security/userdetails1}baseResponseDto">
 *       &lt;sequence>
 *         &lt;element name="saUserNewDto" type="{http://service.tapi.com/security/userdetails1}saUserNewDto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "userDtoResponse", propOrder = {
    "saUserNewDto"
})
public class UserDtoResponse
    extends BaseResponseDto
{

    protected SaUserNewDto saUserNewDto;

    /**
     * Gets the value of the saUserNewDto property.
     * 
     * @return
     *     possible object is
     *     {@link SaUserNewDto }
     *     
     */
    public SaUserNewDto getSaUserNewDto() {
        return saUserNewDto;
    }

    /**
     * Sets the value of the saUserNewDto property.
     * 
     * @param value
     *     allowed object is
     *     {@link SaUserNewDto }
     *     
     */
    public void setSaUserNewDto(SaUserNewDto value) {
        this.saUserNewDto = value;
    }

}
