
package com.tapi.service.fin.visa.intf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for visaAccountToPayment complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="visaAccountToPayment">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="businessNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cardType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="payeeBankAccount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="payeeBankAccountName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="payeeBankLocation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="payeeAreaCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="payeeAreaName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="payeeBankCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="payeeBankName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cnapsCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cnapsName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fastFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="corpInd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="purpose" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="memo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="smsFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cellPhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mailFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mailAddr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="certType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="certNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "visaAccountToPayment", propOrder = {
    "businessNo",
    "cardType",
    "payeeBankAccount",
    "payeeBankAccountName",
    "payeeBankLocation",
    "payeeAreaCode",
    "payeeAreaName",
    "payeeBankCode",
    "payeeBankName",
    "cnapsCode",
    "cnapsName",
    "fastFlag",
    "corpInd",
    "purpose",
    "memo",
    "description",
    "smsFlag",
    "cellPhone",
    "mailFlag",
    "mailAddr",
    "certType",
    "certNumber"
})
public class VisaAccountToPayment {

    protected String businessNo;
    protected String cardType;
    protected String payeeBankAccount;
    protected String payeeBankAccountName;
    protected String payeeBankLocation;
    protected String payeeAreaCode;
    protected String payeeAreaName;
    protected String payeeBankCode;
    protected String payeeBankName;
    protected String cnapsCode;
    protected String cnapsName;
    protected String fastFlag;
    protected String corpInd;
    protected String purpose;
    protected String memo;
    protected String description;
    protected String smsFlag;
    protected String cellPhone;
    protected String mailFlag;
    protected String mailAddr;
    protected String certType;
    protected String certNumber;

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
     * Gets the value of the cardType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardType() {
        return cardType;
    }

    /**
     * Sets the value of the cardType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardType(String value) {
        this.cardType = value;
    }

    /**
     * Gets the value of the payeeBankAccount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayeeBankAccount() {
        return payeeBankAccount;
    }

    /**
     * Sets the value of the payeeBankAccount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayeeBankAccount(String value) {
        this.payeeBankAccount = value;
    }

    /**
     * Gets the value of the payeeBankAccountName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayeeBankAccountName() {
        return payeeBankAccountName;
    }

    /**
     * Sets the value of the payeeBankAccountName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayeeBankAccountName(String value) {
        this.payeeBankAccountName = value;
    }

    /**
     * Gets the value of the payeeBankLocation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayeeBankLocation() {
        return payeeBankLocation;
    }

    /**
     * Sets the value of the payeeBankLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayeeBankLocation(String value) {
        this.payeeBankLocation = value;
    }

    /**
     * Gets the value of the payeeAreaCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayeeAreaCode() {
        return payeeAreaCode;
    }

    /**
     * Sets the value of the payeeAreaCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayeeAreaCode(String value) {
        this.payeeAreaCode = value;
    }

    /**
     * Gets the value of the payeeAreaName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayeeAreaName() {
        return payeeAreaName;
    }

    /**
     * Sets the value of the payeeAreaName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayeeAreaName(String value) {
        this.payeeAreaName = value;
    }

    /**
     * Gets the value of the payeeBankCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayeeBankCode() {
        return payeeBankCode;
    }

    /**
     * Sets the value of the payeeBankCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayeeBankCode(String value) {
        this.payeeBankCode = value;
    }

    /**
     * Gets the value of the payeeBankName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayeeBankName() {
        return payeeBankName;
    }

    /**
     * Sets the value of the payeeBankName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayeeBankName(String value) {
        this.payeeBankName = value;
    }

    /**
     * Gets the value of the cnapsCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCnapsCode() {
        return cnapsCode;
    }

    /**
     * Sets the value of the cnapsCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCnapsCode(String value) {
        this.cnapsCode = value;
    }

    /**
     * Gets the value of the cnapsName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCnapsName() {
        return cnapsName;
    }

    /**
     * Sets the value of the cnapsName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCnapsName(String value) {
        this.cnapsName = value;
    }

    /**
     * Gets the value of the fastFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFastFlag() {
        return fastFlag;
    }

    /**
     * Sets the value of the fastFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFastFlag(String value) {
        this.fastFlag = value;
    }

    /**
     * Gets the value of the corpInd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorpInd() {
        return corpInd;
    }

    /**
     * Sets the value of the corpInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCorpInd(String value) {
        this.corpInd = value;
    }

    /**
     * Gets the value of the purpose property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPurpose() {
        return purpose;
    }

    /**
     * Sets the value of the purpose property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPurpose(String value) {
        this.purpose = value;
    }

    /**
     * Gets the value of the memo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMemo() {
        return memo;
    }

    /**
     * Sets the value of the memo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMemo(String value) {
        this.memo = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the smsFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSmsFlag() {
        return smsFlag;
    }

    /**
     * Sets the value of the smsFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSmsFlag(String value) {
        this.smsFlag = value;
    }

    /**
     * Gets the value of the cellPhone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCellPhone() {
        return cellPhone;
    }

    /**
     * Sets the value of the cellPhone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCellPhone(String value) {
        this.cellPhone = value;
    }

    /**
     * Gets the value of the mailFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMailFlag() {
        return mailFlag;
    }

    /**
     * Sets the value of the mailFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMailFlag(String value) {
        this.mailFlag = value;
    }

    /**
     * Gets the value of the mailAddr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMailAddr() {
        return mailAddr;
    }

    /**
     * Sets the value of the mailAddr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMailAddr(String value) {
        this.mailAddr = value;
    }

    /**
     * Gets the value of the certType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCertType() {
        return certType;
    }

    /**
     * Sets the value of the certType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCertType(String value) {
        this.certType = value;
    }

    /**
     * Gets the value of the certNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCertNumber() {
        return certNumber;
    }

    /**
     * Sets the value of the certNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCertNumber(String value) {
        this.certNumber = value;
    }

}
