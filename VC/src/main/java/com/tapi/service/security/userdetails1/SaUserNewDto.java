
package com.tapi.service.security.userdetails1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for saUserNewDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="saUserNewDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="leaveDate" type="{http://service.tapi.com/security/userdetails1}dateTime" minOccurs="0"/>
 *         &lt;element name="practisNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tempCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="identityNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sex" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="transformFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="labourPowerNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vocationCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="leaveReason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="socialSecurityNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deptCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deptName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="startDateTeam" type="{http://service.tapi.com/security/userdetails1}dateTime" minOccurs="0"/>
 *         &lt;element name="currentComcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UserType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="teamCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="roleId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="levelId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="basewage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="benefits" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="comName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createDate" type="{http://service.tapi.com/security/userdetails1}dateTime" minOccurs="0"/>
 *         &lt;element name="createrCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="postWage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="teamName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bankName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bankCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accountName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bankOfCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bankOfProvince" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="teamleader" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="personType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="endowment" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="medical" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="unEmployment" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="seriousIllness" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="workInjuries" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="giveBirth" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="agreeWorkHour" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="perEndDate" type="{http://service.tapi.com/security/userdetails1}dateTime" minOccurs="0"/>
 *         &lt;element name="perStartDate" type="{http://service.tapi.com/security/userdetails1}dateTime" minOccurs="0"/>
 *         &lt;element name="comeFrom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="safeDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="channelCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="yearAward" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="userCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userEName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="seal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="passwordSetDate" type="{http://service.tapi.com/security/userdetails1}dateTime" minOccurs="0"/>
 *         &lt;element name="passwordExpireDate" type="{http://service.tapi.com/security/userdetails1}dateTime" minOccurs="0"/>
 *         &lt;element name="comCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="makeCom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accountCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="phone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mobile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="address" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="postCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="loginSystem" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="newUserCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="validStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="articleCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="flag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="attribute1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="attribute2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="attribute3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="attribute4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="attribute5" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="attribute6" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="attribute7" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="attribute8" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="attribute9" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="attribute10" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="attribute11" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="attribute12" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="attribute13" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="attribute14" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="attribute15" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="attribute16" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="attribute17" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="attribute18" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="attribute19" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="attribute20" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "saUserNewDto", propOrder = {
    "leaveDate",
    "practisNumber",
    "tempCode",
    "identityNo",
    "sex",
    "transformFlag",
    "userNo",
    "labourPowerNo",
    "vocationCode",
    "leaveReason",
    "socialSecurityNum",
    "deptCode",
    "deptName",
    "startDateTeam",
    "currentComcode",
    "userType",
    "teamCode",
    "roleId",
    "levelId",
    "basewage",
    "benefits",
    "comName",
    "createDate",
    "createrCode",
    "postWage",
    "teamName",
    "bankName",
    "bankCode",
    "accountName",
    "bankOfCity",
    "bankOfProvince",
    "teamleader",
    "personType",
    "endowment",
    "medical",
    "unEmployment",
    "seriousIllness",
    "workInjuries",
    "giveBirth",
    "agreeWorkHour",
    "perEndDate",
    "perStartDate",
    "comeFrom",
    "safeDate",
    "channelCode",
    "yearAward",
    "userCode",
    "userName",
    "userEName",
    "password",
    "seal",
    "passwordSetDate",
    "passwordExpireDate",
    "comCode",
    "makeCom",
    "accountCode",
    "phone",
    "mobile",
    "address",
    "postCode",
    "email",
    "userFlag",
    "loginSystem",
    "newUserCode",
    "validStatus",
    "articleCode",
    "flag",
    "attribute1",
    "attribute2",
    "attribute3",
    "attribute4",
    "attribute5",
    "attribute6",
    "attribute7",
    "attribute8",
    "attribute9",
    "attribute10",
    "attribute11",
    "attribute12",
    "attribute13",
    "attribute14",
    "attribute15",
    "attribute16",
    "attribute17",
    "attribute18",
    "attribute19",
    "attribute20"
})
public class SaUserNewDto {

    protected DateTime leaveDate;
    protected String practisNumber;
    protected String tempCode;
    protected String identityNo;
    protected String sex;
    protected String transformFlag;
    protected String userNo;
    protected String labourPowerNo;
    protected String vocationCode;
    protected String leaveReason;
    protected String socialSecurityNum;
    protected String deptCode;
    protected String deptName;
    protected DateTime startDateTeam;
    protected String currentComcode;
    @XmlElement(name = "UserType")
    protected String userType;
    protected String teamCode;
    protected String roleId;
    protected String levelId;
    protected String basewage;
    protected String benefits;
    protected String comName;
    protected DateTime createDate;
    protected String createrCode;
    protected String postWage;
    protected String teamName;
    protected String bankName;
    protected String bankCode;
    protected String accountName;
    protected String bankOfCity;
    protected String bankOfProvince;
    protected String teamleader;
    protected String personType;
    protected double endowment;
    protected double medical;
    protected double unEmployment;
    protected double seriousIllness;
    protected double workInjuries;
    protected double giveBirth;
    protected double agreeWorkHour;
    protected DateTime perEndDate;
    protected DateTime perStartDate;
    protected String comeFrom;
    protected String safeDate;
    protected String channelCode;
    protected double yearAward;
    protected String userCode;
    protected String userName;
    protected String userEName;
    protected String password;
    protected String seal;
    protected DateTime passwordSetDate;
    protected DateTime passwordExpireDate;
    protected String comCode;
    protected String makeCom;
    protected String accountCode;
    protected String phone;
    protected String mobile;
    protected String address;
    protected String postCode;
    protected String email;
    protected String userFlag;
    protected String loginSystem;
    protected String newUserCode;
    protected String validStatus;
    protected String articleCode;
    protected String flag;
    protected String attribute1;
    protected String attribute2;
    protected String attribute3;
    protected String attribute4;
    protected String attribute5;
    protected String attribute6;
    protected String attribute7;
    protected String attribute8;
    protected String attribute9;
    protected String attribute10;
    protected String attribute11;
    protected String attribute12;
    protected String attribute13;
    protected String attribute14;
    protected String attribute15;
    protected String attribute16;
    protected String attribute17;
    protected String attribute18;
    protected String attribute19;
    protected String attribute20;

    /**
     * Gets the value of the leaveDate property.
     * 
     * @return
     *     possible object is
     *     {@link DateTime }
     *     
     */
    public DateTime getLeaveDate() {
        return leaveDate;
    }

    /**
     * Sets the value of the leaveDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link DateTime }
     *     
     */
    public void setLeaveDate(DateTime value) {
        this.leaveDate = value;
    }

    /**
     * Gets the value of the practisNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPractisNumber() {
        return practisNumber;
    }

    /**
     * Sets the value of the practisNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPractisNumber(String value) {
        this.practisNumber = value;
    }

    /**
     * Gets the value of the tempCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTempCode() {
        return tempCode;
    }

    /**
     * Sets the value of the tempCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTempCode(String value) {
        this.tempCode = value;
    }

    /**
     * Gets the value of the identityNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentityNo() {
        return identityNo;
    }

    /**
     * Sets the value of the identityNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentityNo(String value) {
        this.identityNo = value;
    }

    /**
     * Gets the value of the sex property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSex() {
        return sex;
    }

    /**
     * Sets the value of the sex property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSex(String value) {
        this.sex = value;
    }

    /**
     * Gets the value of the transformFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransformFlag() {
        return transformFlag;
    }

    /**
     * Sets the value of the transformFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransformFlag(String value) {
        this.transformFlag = value;
    }

    /**
     * Gets the value of the userNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserNo() {
        return userNo;
    }

    /**
     * Sets the value of the userNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserNo(String value) {
        this.userNo = value;
    }

    /**
     * Gets the value of the labourPowerNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLabourPowerNo() {
        return labourPowerNo;
    }

    /**
     * Sets the value of the labourPowerNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLabourPowerNo(String value) {
        this.labourPowerNo = value;
    }

    /**
     * Gets the value of the vocationCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVocationCode() {
        return vocationCode;
    }

    /**
     * Sets the value of the vocationCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVocationCode(String value) {
        this.vocationCode = value;
    }

    /**
     * Gets the value of the leaveReason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLeaveReason() {
        return leaveReason;
    }

    /**
     * Sets the value of the leaveReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLeaveReason(String value) {
        this.leaveReason = value;
    }

    /**
     * Gets the value of the socialSecurityNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSocialSecurityNum() {
        return socialSecurityNum;
    }

    /**
     * Sets the value of the socialSecurityNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSocialSecurityNum(String value) {
        this.socialSecurityNum = value;
    }

    /**
     * Gets the value of the deptCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeptCode() {
        return deptCode;
    }

    /**
     * Sets the value of the deptCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeptCode(String value) {
        this.deptCode = value;
    }

    /**
     * Gets the value of the deptName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeptName() {
        return deptName;
    }

    /**
     * Sets the value of the deptName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeptName(String value) {
        this.deptName = value;
    }

    /**
     * Gets the value of the startDateTeam property.
     * 
     * @return
     *     possible object is
     *     {@link DateTime }
     *     
     */
    public DateTime getStartDateTeam() {
        return startDateTeam;
    }

    /**
     * Sets the value of the startDateTeam property.
     * 
     * @param value
     *     allowed object is
     *     {@link DateTime }
     *     
     */
    public void setStartDateTeam(DateTime value) {
        this.startDateTeam = value;
    }

    /**
     * Gets the value of the currentComcode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrentComcode() {
        return currentComcode;
    }

    /**
     * Sets the value of the currentComcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrentComcode(String value) {
        this.currentComcode = value;
    }

    /**
     * Gets the value of the userType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserType() {
        return userType;
    }

    /**
     * Sets the value of the userType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserType(String value) {
        this.userType = value;
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
     * Gets the value of the roleId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * Sets the value of the roleId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoleId(String value) {
        this.roleId = value;
    }

    /**
     * Gets the value of the levelId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLevelId() {
        return levelId;
    }

    /**
     * Sets the value of the levelId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLevelId(String value) {
        this.levelId = value;
    }

    /**
     * Gets the value of the basewage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBasewage() {
        return basewage;
    }

    /**
     * Sets the value of the basewage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBasewage(String value) {
        this.basewage = value;
    }

    /**
     * Gets the value of the benefits property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBenefits() {
        return benefits;
    }

    /**
     * Sets the value of the benefits property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBenefits(String value) {
        this.benefits = value;
    }

    /**
     * Gets the value of the comName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComName() {
        return comName;
    }

    /**
     * Sets the value of the comName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComName(String value) {
        this.comName = value;
    }

    /**
     * Gets the value of the createDate property.
     * 
     * @return
     *     possible object is
     *     {@link DateTime }
     *     
     */
    public DateTime getCreateDate() {
        return createDate;
    }

    /**
     * Sets the value of the createDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link DateTime }
     *     
     */
    public void setCreateDate(DateTime value) {
        this.createDate = value;
    }

    /**
     * Gets the value of the createrCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreaterCode() {
        return createrCode;
    }

    /**
     * Sets the value of the createrCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreaterCode(String value) {
        this.createrCode = value;
    }

    /**
     * Gets the value of the postWage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostWage() {
        return postWage;
    }

    /**
     * Sets the value of the postWage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostWage(String value) {
        this.postWage = value;
    }

    /**
     * Gets the value of the teamName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTeamName() {
        return teamName;
    }

    /**
     * Sets the value of the teamName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTeamName(String value) {
        this.teamName = value;
    }

    /**
     * Gets the value of the bankName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * Sets the value of the bankName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankName(String value) {
        this.bankName = value;
    }

    /**
     * Gets the value of the bankCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankCode() {
        return bankCode;
    }

    /**
     * Sets the value of the bankCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankCode(String value) {
        this.bankCode = value;
    }

    /**
     * Gets the value of the accountName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * Sets the value of the accountName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountName(String value) {
        this.accountName = value;
    }

    /**
     * Gets the value of the bankOfCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankOfCity() {
        return bankOfCity;
    }

    /**
     * Sets the value of the bankOfCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankOfCity(String value) {
        this.bankOfCity = value;
    }

    /**
     * Gets the value of the bankOfProvince property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankOfProvince() {
        return bankOfProvince;
    }

    /**
     * Sets the value of the bankOfProvince property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankOfProvince(String value) {
        this.bankOfProvince = value;
    }

    /**
     * Gets the value of the teamleader property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTeamleader() {
        return teamleader;
    }

    /**
     * Sets the value of the teamleader property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTeamleader(String value) {
        this.teamleader = value;
    }

    /**
     * Gets the value of the personType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPersonType() {
        return personType;
    }

    /**
     * Sets the value of the personType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPersonType(String value) {
        this.personType = value;
    }

    /**
     * Gets the value of the endowment property.
     * 
     */
    public double getEndowment() {
        return endowment;
    }

    /**
     * Sets the value of the endowment property.
     * 
     */
    public void setEndowment(double value) {
        this.endowment = value;
    }

    /**
     * Gets the value of the medical property.
     * 
     */
    public double getMedical() {
        return medical;
    }

    /**
     * Sets the value of the medical property.
     * 
     */
    public void setMedical(double value) {
        this.medical = value;
    }

    /**
     * Gets the value of the unEmployment property.
     * 
     */
    public double getUnEmployment() {
        return unEmployment;
    }

    /**
     * Sets the value of the unEmployment property.
     * 
     */
    public void setUnEmployment(double value) {
        this.unEmployment = value;
    }

    /**
     * Gets the value of the seriousIllness property.
     * 
     */
    public double getSeriousIllness() {
        return seriousIllness;
    }

    /**
     * Sets the value of the seriousIllness property.
     * 
     */
    public void setSeriousIllness(double value) {
        this.seriousIllness = value;
    }

    /**
     * Gets the value of the workInjuries property.
     * 
     */
    public double getWorkInjuries() {
        return workInjuries;
    }

    /**
     * Sets the value of the workInjuries property.
     * 
     */
    public void setWorkInjuries(double value) {
        this.workInjuries = value;
    }

    /**
     * Gets the value of the giveBirth property.
     * 
     */
    public double getGiveBirth() {
        return giveBirth;
    }

    /**
     * Sets the value of the giveBirth property.
     * 
     */
    public void setGiveBirth(double value) {
        this.giveBirth = value;
    }

    /**
     * Gets the value of the agreeWorkHour property.
     * 
     */
    public double getAgreeWorkHour() {
        return agreeWorkHour;
    }

    /**
     * Sets the value of the agreeWorkHour property.
     * 
     */
    public void setAgreeWorkHour(double value) {
        this.agreeWorkHour = value;
    }

    /**
     * Gets the value of the perEndDate property.
     * 
     * @return
     *     possible object is
     *     {@link DateTime }
     *     
     */
    public DateTime getPerEndDate() {
        return perEndDate;
    }

    /**
     * Sets the value of the perEndDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link DateTime }
     *     
     */
    public void setPerEndDate(DateTime value) {
        this.perEndDate = value;
    }

    /**
     * Gets the value of the perStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link DateTime }
     *     
     */
    public DateTime getPerStartDate() {
        return perStartDate;
    }

    /**
     * Sets the value of the perStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link DateTime }
     *     
     */
    public void setPerStartDate(DateTime value) {
        this.perStartDate = value;
    }

    /**
     * Gets the value of the comeFrom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComeFrom() {
        return comeFrom;
    }

    /**
     * Sets the value of the comeFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComeFrom(String value) {
        this.comeFrom = value;
    }

    /**
     * Gets the value of the safeDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSafeDate() {
        return safeDate;
    }

    /**
     * Sets the value of the safeDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSafeDate(String value) {
        this.safeDate = value;
    }

    /**
     * Gets the value of the channelCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChannelCode() {
        return channelCode;
    }

    /**
     * Sets the value of the channelCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChannelCode(String value) {
        this.channelCode = value;
    }

    /**
     * Gets the value of the yearAward property.
     * 
     */
    public double getYearAward() {
        return yearAward;
    }

    /**
     * Sets the value of the yearAward property.
     * 
     */
    public void setYearAward(double value) {
        this.yearAward = value;
    }

    /**
     * Gets the value of the userCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * Sets the value of the userCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserCode(String value) {
        this.userCode = value;
    }

    /**
     * Gets the value of the userName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the value of the userName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserName(String value) {
        this.userName = value;
    }

    /**
     * Gets the value of the userEName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserEName() {
        return userEName;
    }

    /**
     * Sets the value of the userEName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserEName(String value) {
        this.userEName = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Gets the value of the seal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeal() {
        return seal;
    }

    /**
     * Sets the value of the seal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeal(String value) {
        this.seal = value;
    }

    /**
     * Gets the value of the passwordSetDate property.
     * 
     * @return
     *     possible object is
     *     {@link DateTime }
     *     
     */
    public DateTime getPasswordSetDate() {
        return passwordSetDate;
    }

    /**
     * Sets the value of the passwordSetDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link DateTime }
     *     
     */
    public void setPasswordSetDate(DateTime value) {
        this.passwordSetDate = value;
    }

    /**
     * Gets the value of the passwordExpireDate property.
     * 
     * @return
     *     possible object is
     *     {@link DateTime }
     *     
     */
    public DateTime getPasswordExpireDate() {
        return passwordExpireDate;
    }

    /**
     * Sets the value of the passwordExpireDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link DateTime }
     *     
     */
    public void setPasswordExpireDate(DateTime value) {
        this.passwordExpireDate = value;
    }

    /**
     * Gets the value of the comCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComCode() {
        return comCode;
    }

    /**
     * Sets the value of the comCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComCode(String value) {
        this.comCode = value;
    }

    /**
     * Gets the value of the makeCom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMakeCom() {
        return makeCom;
    }

    /**
     * Sets the value of the makeCom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMakeCom(String value) {
        this.makeCom = value;
    }

    /**
     * Gets the value of the accountCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountCode() {
        return accountCode;
    }

    /**
     * Sets the value of the accountCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountCode(String value) {
        this.accountCode = value;
    }

    /**
     * Gets the value of the phone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the value of the phone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhone(String value) {
        this.phone = value;
    }

    /**
     * Gets the value of the mobile property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * Sets the value of the mobile property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMobile(String value) {
        this.mobile = value;
    }

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddress(String value) {
        this.address = value;
    }

    /**
     * Gets the value of the postCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostCode() {
        return postCode;
    }

    /**
     * Sets the value of the postCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostCode(String value) {
        this.postCode = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the userFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserFlag() {
        return userFlag;
    }

    /**
     * Sets the value of the userFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserFlag(String value) {
        this.userFlag = value;
    }

    /**
     * Gets the value of the loginSystem property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoginSystem() {
        return loginSystem;
    }

    /**
     * Sets the value of the loginSystem property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoginSystem(String value) {
        this.loginSystem = value;
    }

    /**
     * Gets the value of the newUserCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNewUserCode() {
        return newUserCode;
    }

    /**
     * Sets the value of the newUserCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNewUserCode(String value) {
        this.newUserCode = value;
    }

    /**
     * Gets the value of the validStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValidStatus() {
        return validStatus;
    }

    /**
     * Sets the value of the validStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValidStatus(String value) {
        this.validStatus = value;
    }

    /**
     * Gets the value of the articleCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArticleCode() {
        return articleCode;
    }

    /**
     * Sets the value of the articleCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArticleCode(String value) {
        this.articleCode = value;
    }

    /**
     * Gets the value of the flag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlag() {
        return flag;
    }

    /**
     * Sets the value of the flag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlag(String value) {
        this.flag = value;
    }

    /**
     * Gets the value of the attribute1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttribute1() {
        return attribute1;
    }

    /**
     * Sets the value of the attribute1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttribute1(String value) {
        this.attribute1 = value;
    }

    /**
     * Gets the value of the attribute2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttribute2() {
        return attribute2;
    }

    /**
     * Sets the value of the attribute2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttribute2(String value) {
        this.attribute2 = value;
    }

    /**
     * Gets the value of the attribute3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttribute3() {
        return attribute3;
    }

    /**
     * Sets the value of the attribute3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttribute3(String value) {
        this.attribute3 = value;
    }

    /**
     * Gets the value of the attribute4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttribute4() {
        return attribute4;
    }

    /**
     * Sets the value of the attribute4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttribute4(String value) {
        this.attribute4 = value;
    }

    /**
     * Gets the value of the attribute5 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttribute5() {
        return attribute5;
    }

    /**
     * Sets the value of the attribute5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttribute5(String value) {
        this.attribute5 = value;
    }

    /**
     * Gets the value of the attribute6 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttribute6() {
        return attribute6;
    }

    /**
     * Sets the value of the attribute6 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttribute6(String value) {
        this.attribute6 = value;
    }

    /**
     * Gets the value of the attribute7 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttribute7() {
        return attribute7;
    }

    /**
     * Sets the value of the attribute7 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttribute7(String value) {
        this.attribute7 = value;
    }

    /**
     * Gets the value of the attribute8 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttribute8() {
        return attribute8;
    }

    /**
     * Sets the value of the attribute8 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttribute8(String value) {
        this.attribute8 = value;
    }

    /**
     * Gets the value of the attribute9 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttribute9() {
        return attribute9;
    }

    /**
     * Sets the value of the attribute9 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttribute9(String value) {
        this.attribute9 = value;
    }

    /**
     * Gets the value of the attribute10 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttribute10() {
        return attribute10;
    }

    /**
     * Sets the value of the attribute10 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttribute10(String value) {
        this.attribute10 = value;
    }

    /**
     * Gets the value of the attribute11 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttribute11() {
        return attribute11;
    }

    /**
     * Sets the value of the attribute11 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttribute11(String value) {
        this.attribute11 = value;
    }

    /**
     * Gets the value of the attribute12 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttribute12() {
        return attribute12;
    }

    /**
     * Sets the value of the attribute12 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttribute12(String value) {
        this.attribute12 = value;
    }

    /**
     * Gets the value of the attribute13 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttribute13() {
        return attribute13;
    }

    /**
     * Sets the value of the attribute13 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttribute13(String value) {
        this.attribute13 = value;
    }

    /**
     * Gets the value of the attribute14 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttribute14() {
        return attribute14;
    }

    /**
     * Sets the value of the attribute14 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttribute14(String value) {
        this.attribute14 = value;
    }

    /**
     * Gets the value of the attribute15 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttribute15() {
        return attribute15;
    }

    /**
     * Sets the value of the attribute15 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttribute15(String value) {
        this.attribute15 = value;
    }

    /**
     * Gets the value of the attribute16 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttribute16() {
        return attribute16;
    }

    /**
     * Sets the value of the attribute16 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttribute16(String value) {
        this.attribute16 = value;
    }

    /**
     * Gets the value of the attribute17 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttribute17() {
        return attribute17;
    }

    /**
     * Sets the value of the attribute17 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttribute17(String value) {
        this.attribute17 = value;
    }

    /**
     * Gets the value of the attribute18 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttribute18() {
        return attribute18;
    }

    /**
     * Sets the value of the attribute18 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttribute18(String value) {
        this.attribute18 = value;
    }

    /**
     * Gets the value of the attribute19 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttribute19() {
        return attribute19;
    }

    /**
     * Sets the value of the attribute19 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttribute19(String value) {
        this.attribute19 = value;
    }

    /**
     * Gets the value of the attribute20 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttribute20() {
        return attribute20;
    }

    /**
     * Sets the value of the attribute20 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttribute20(String value) {
        this.attribute20 = value;
    }

}
