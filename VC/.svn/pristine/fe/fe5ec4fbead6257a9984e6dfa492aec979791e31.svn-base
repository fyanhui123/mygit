
package com.tapi.service.security.teamdetails;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for teamDetailResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="teamDetailResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.tapi.com/security/teamdetails}baseResponseDto">
 *       &lt;sequence>
 *         &lt;element name="teamNewDetails" type="{http://service.tapi.com/security/teamdetails}saTeamNewDto" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "teamDetailResponse", propOrder = {
    "teamNewDetails"
})
public class TeamDetailResponse
    extends BaseResponseDto
{

    @XmlElement(nillable = true)
    protected List<SaTeamNewDto> teamNewDetails;

    /**
     * Gets the value of the teamNewDetails property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the teamNewDetails property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTeamNewDetails().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SaTeamNewDto }
     * 
     * 
     */
    public List<SaTeamNewDto> getTeamNewDetails() {
        if (teamNewDetails == null) {
            teamNewDetails = new ArrayList<SaTeamNewDto>();
        }
        return this.teamNewDetails;
    }

}
