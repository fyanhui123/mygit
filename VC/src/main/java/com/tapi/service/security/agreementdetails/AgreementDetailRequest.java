
package com.tapi.service.security.agreementdetails;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for agreementDetailRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="agreementDetailRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.tapi.com/security/agreementdetails}baseRequestDto">
 *       &lt;sequence>
 *         &lt;element name="comCodes" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="riskCodes" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="agreementNos" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="teamType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="agentType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="agentClass" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "agreementDetailRequest", propOrder = {
    "comCodes",
    "riskCodes",
    "agreementNos",
    "teamType",
    "agentType",
    "agentClass"
})
public class AgreementDetailRequest
    extends BaseRequestDto
{

    @XmlElement(nillable = true)
    protected List<String> comCodes;
    @XmlElement(nillable = true)
    protected List<String> riskCodes;
    @XmlElement(nillable = true)
    protected List<String> agreementNos;
    protected String teamType;
    protected String agentType;
    protected String agentClass;

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
     * Gets the value of the riskCodes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the riskCodes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRiskCodes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getRiskCodes() {
        if (riskCodes == null) {
            riskCodes = new ArrayList<String>();
        }
        return this.riskCodes;
    }

    public void setRiskCodes(List<String> riskCodes) {
        this.riskCodes = riskCodes;
    }

    /**
     * Gets the value of the agreementNos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the agreementNos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAgreementNos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getAgreementNos() {
        if (agreementNos == null) {
            agreementNos = new ArrayList<String>();
        }
        return this.agreementNos;
    }

    public void setAgreementNos(List<String> agreementNos) {
        this.agreementNos = agreementNos;
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

    /**
     * Gets the value of the agentType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgentType() {
        return agentType;
    }

    /**
     * Sets the value of the agentType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgentType(String value) {
        this.agentType = value;
    }

    /**
     * Gets the value of the agentClass property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgentClass() {
        return agentClass;
    }

    /**
     * Sets the value of the agentClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgentClass(String value) {
        this.agentClass = value;
    }

}
