
package com.tapi.service.security.teamdetails;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for teamDetailRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="teamDetailRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.tapi.com/security/teamdetails}baseRequestDto">
 *       &lt;sequence>
 *         &lt;element name="comCodes" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="teamCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="teamType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "teamDetailRequest", propOrder = {
    "comCodes",
    "teamCode",
    "teamType"
})
public class TeamDetailRequest
    extends BaseRequestDto
{

    @XmlElement(nillable = true)
    protected List<String> comCodes;
    protected String teamCode;
    protected String teamType;

    /**
     * Gets the value of the comCodes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the comCodes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComCodes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getComCodes() {
        if (comCodes == null) {
            comCodes = new ArrayList<String>();
        }
        return this.comCodes;
    }

    public void setComCodes(List<String> comCodes) {
        this.comCodes = comCodes;
    }

    /**
     * Gets the value of the teamCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTeamCode() {
        return teamCode;
    }

    /**
     * Sets the value of the teamCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTeamCode(String value) {
        this.teamCode = value;
    }

    /**
     * Gets the value of the teamType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTeamType() {
        return teamType;
    }

    /**
     * Sets the value of the teamType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTeamType(String value) {
        this.teamType = value;
    }

}
