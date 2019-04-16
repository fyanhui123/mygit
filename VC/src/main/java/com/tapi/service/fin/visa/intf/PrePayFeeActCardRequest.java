
package com.tapi.service.fin.visa.intf;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import com.tapi.service.fin.common.dto.BaseRequest;


/**
 * <p>Java class for prePayFeeActCardRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="prePayFeeActCardRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.tapi.com/fin/common/dto}baseRequest">
 *       &lt;sequence>
 *         &lt;element name="businessNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="payeeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="payeeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="payeeType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="currency" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="payFee" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="applyDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="companyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="payEndDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="activationDetailList" type="{http://service.tapi.com/fin/visa/intf}activationDetailDto" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "prePayFeeActCardRequest", propOrder = {
    "businessNo",
    "payeeName",
    "payeeCode",
    "payeeType",
    "currency",
    "payFee",
    "applyDate",
    "companyCode",
    "payEndDate",
    "activationDetailList"
})
public class PrePayFeeActCardRequest
    extends BaseRequest
{

    protected String businessNo;
    protected String payeeName;
    protected String payeeCode;
    protected String payeeType;
    protected String currency;
    protected Double payFee;
    @XmlSchemaType(name = "dateTime")
    protected Date applyDate;
    protected String companyCode;
    @XmlSchemaType(name = "dateTime")
    protected Date payEndDate;
    @XmlElement(nillable = true)
    protected List<ActivationDetailDto> activationDetailList;

    /**
     * Gets the value of the businessNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessNo() {
        return businessNo;
    }

    /**
     * Sets the value of the businessNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessNo(String value) {
        this.businessNo = value;
    }

    /**
     * Gets the value of the payeeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayeeName() {
        return payeeName;
    }

    /**
     * Sets the value of the payeeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayeeName(String value) {
        this.payeeName = value;
    }

    /**
     * Gets the value of the payeeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayeeCode() {
        return payeeCode;
    }

    /**
     * Sets the value of the payeeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayeeCode(String value) {
        this.payeeCode = value;
    }

    /**
     * Gets the value of the payeeType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayeeType() {
        return payeeType;
    }

    /**
     * Sets the value of the payeeType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayeeType(String value) {
        this.payeeType = value;
    }

    /**
     * Gets the value of the currency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Sets the value of the currency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrency(String value) {
        this.currency = value;
    }

    /**
     * Gets the value of the payFee property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPayFee() {
        return payFee;
    }

    /**
     * Sets the value of the payFee property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPayFee(Double value) {
        this.payFee = value;
    }

    /**
     * Gets the value of the applyDate property.
     * 
     * @return
     *     possible object is
     *     {@link Date }
     *     
     */
    public Date getApplyDate() {
        return applyDate;
    }

    /**
     * Sets the value of the applyDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Date }
     *     
     */
    public void setApplyDate(Date value) {
        this.applyDate = value;
    }

    /**
     * Gets the value of the companyCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompanyCode() {
        return companyCode;
    }

    /**
     * Sets the value of the companyCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompanyCode(String value) {
        this.companyCode = value;
    }

    /**
     * Gets the value of the payEndDate property.
     * 
     * @return
     *     possible object is
     *     {@link Date }
     *     
     */
    public Date getPayEndDate() {
        return payEndDate;
    }

    /**
     * Sets the value of the payEndDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Date }
     *     
     */
    public void setPayEndDate(Date value) {
        this.payEndDate = value;
    }

    /**
     * Gets the value of the activationDetailList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the activationDetailList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getActivationDetailList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ActivationDetailDto }
     * 
     * 
     */
    public List<ActivationDetailDto> getActivationDetailList() {
        if (activationDetailList == null) {
            activationDetailList = new ArrayList<ActivationDetailDto>();
        }
        return this.activationDetailList;
    }

}
