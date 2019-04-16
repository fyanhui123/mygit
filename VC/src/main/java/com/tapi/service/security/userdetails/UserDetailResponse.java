
package com.tapi.service.security.userdetails;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for userDetailResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="userDetailResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.tapi.com/security/userdetails}baseResponseDto">
 *       &lt;sequence>
 *         &lt;element name="userNewDetails" type="{http://service.tapi.com/security/userdetails}saUserNewDto" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "userDetailResponse", propOrder = {
    "userNewDetails"
})
public class UserDetailResponse
    extends BaseResponseDto
{

    @XmlElement(nillable = true)
    protected List<SaUserNewDto> userNewDetails;

    /**
     * Gets the value of the userNewDetails property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the userNewDetails property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUserNewDetails().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SaUserNewDto }
     * 
     * 
     */
    public List<SaUserNewDto> getUserNewDetails() {
        if (userNewDetails == null) {
            userNewDetails = new ArrayList<SaUserNewDto>();
        }
        return this.userNewDetails;
    }

}
