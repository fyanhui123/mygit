/**
 * 
 */
package com.tapi.tcs.vc.schema.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 收/付费银行账户信息
 * 
 * @author wanghuajian
 * 
 */
@Entity
@Table(name = "VC_PAY_FEE_ACCOUNT")
@SequenceGenerator(name = "SEQ_VC_PAY_FEE_ACCOUNT", sequenceName = "SEQ_VC_PAY_FEE_ACCOUNT", allocationSize = 1)
public class VcPayFeeAccount implements Serializable {

    private static final long serialVersionUID = 8001625730377171381L;

    /**
     * 收/付费银行账户信息表主键
     */
    @Id
    @Column(name = "ID_VC_PAY_FEE_ACCOUNT")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VC_PAY_FEE_ACCOUNT")
    private Long idVcPayFeeAccount;
    
    
    /** 业务流水号(激活卡退卡记录流水/其他业务流水) */
    @Column(name = "ID_BUSINESS")
    private Long idBusiness;  
    
    /**收/付款人代码*/
    @Column(name = "PAYEE_CODE")
    private String payeeCode;

    /**收/付款人名称*/
    @Column(name = "PAYEE_NAME")
    private String payeeName;

    /**收/付款人类型( 0-投保人，1-业务员，2-代理人，3-再保人)*/
    @Column(name = "PAYEE_TYPE")
    private String payeeType;
    
    
    

    /** 收/付款方银行卡折类型 */
    @Column(name = "CARD_TYPE")
    private String cardType;

    /** 收/付款方银行账号 */
    @Column(name = "PAYEE_BANK_ACCOUNT")
    private String payeeBankAccount;

    /** 收/付款方账户名称 */
    @Column(name = "PAYEE_BANK_ACCOUNT_NAME")
    private String payeeBankAccountName;
   
   /** 收/付款方开户银行代码 */
    @Column(name = "PAYEE_BANK_LOCATION_CODE")
    private String payeeBankLocationCode;

   /** 收/付款方开户银行名称 */
    @Column(name = "PAYEE_BANK_LOCATION_NAME")
    private String payeeBankLocationName;
    
   
    
    
    /** 收/付款方区域编码 */
    @Column(name = "PAYEE_BANK_AREA_CODE")
    private String payeeAreaCode;

    /** 收/付款方区域名称 */
    @Column(name = "PAYEE_BANK_AREA_NAME")
    private String payeeAreaName;

    /** 收/付款方银行编码 */
    @Column(name = "PAYEE_BANK_CODE")
    private String payeeBankCode;

    /** 收/付款方银行名称 */
    @Column(name = "PAYEE_BANK_NAME")
    private String payeeBankName;

    /** 收/付款方联行号编码 */
    @Column(name = "CNAPS_CODE")
    private String cnapsCode;

    /** 收/付款方联行号名称 */
    @Column(name = "CNAPS_NAME")
    private String cnapsName;

    /** 加急标志 */
    @Column(name = "FAST_FLAG")
    private String fastFlag;

    /** 公/私标志 */
    @Column(name = "CORP_IND")
    private String corpInd;

    /** 用途 */
    @Column(name = "PURPOSE")
    private String purpose;

    /** 备注 */
    @Column(name = "MEMO")
    private String memo;

    /** 摘要 */
    @Column(name = "DESCRIPTION")
    private String description;

    /** 短信通知标志 */
    @Column(name = "SMS_FLAG")
    private String smsFlag;

    /** 手机号码 */
    @Column(name = "CELL_PHONE")
    private String cellPhone;

    /** 邮件通知标志 */
    @Column(name = "MAIL_FLAG")
    private String mailFlag;

    /** 邮件地址 */
    @Column(name = "MAIL_ADDR")
    private String mailAddr;

    /** 证件类型 */
    @Column(name = "CERT_TYPE")
    private String certType;

    /** 证件号码 */
    @Column(name = "CERT_NUMBER")
    private String certNumber;

    /** 创建人 */
    @Column(name = "created_by")
    private String createdBy;

    /** 创建时间 */
    @Column(name = "date_created")
    private Date dateCreated;

    /** 修改人 */
    @Column(name = "updated_by")
    private String updatedBy;

    /** 修改时间 */
    @Column(name = "date_updated")
    private Date dateUpdated;
    
    
    //======自定义字段==========//
    
    //======自定义字段==========//

    /**
     * @return the idVcPayFeeAccount
     */
    public Long getIdVcPayFeeAccount() {
        return idVcPayFeeAccount;
    }

    /**
     * @param idVcPayFeeAccount
     *            the idVcPayFeeAccount to set
     */
    public void setIdVcPayFeeAccount(Long idVcPayFeeAccount) {
        this.idVcPayFeeAccount = idVcPayFeeAccount;
    }
    
    

    /**
     * @return the idBusiness
     */
    public Long getIdBusiness() {
        return idBusiness;
    }

    /**
     * @param idBusiness the idBusiness to set
     */
    public void setIdBusiness(Long idBusiness) {
        this.idBusiness = idBusiness;
    }

    /**
     * @return the payeeCode
     */
    public String getPayeeCode() {
        return payeeCode;
    }

    /**
     * @param payeeCode
     *            the payeeCode to set
     */
    public void setPayeeCode(String payeeCode) {
        this.payeeCode = payeeCode;
    }

    /**
     * @return the payeeName
     */
    public String getPayeeName() {
        return payeeName;
    }

    /**
     * @param payeeName
     *            the payeeName to set
     */
    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    /**
     * @return the payeeType
     */
    public String getPayeeType() {
        return payeeType;
    }

    /**
     * @param payeeType
     *            the payeeType to set
     */
    public void setPayeeType(String payeeType) {
        this.payeeType = payeeType;
    }

    /**
     * @return the cardType
     */
    public String getCardType() {
        return cardType;
    }

    /**
     * @param cardType
     *            the cardType to set
     */
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    /**
     * @return the payeeBankAccount
     */
    public String getPayeeBankAccount() {
        return payeeBankAccount;
    }

    /**
     * @param payeeBankAccount
     *            the payeeBankAccount to set
     */
    public void setPayeeBankAccount(String payeeBankAccount) {
        this.payeeBankAccount = payeeBankAccount;
    }

    /**
     * @return the payeeBankAccountName
     */
    public String getPayeeBankAccountName() {
        return payeeBankAccountName;
    }

    /**
     * @param payeeBankAccountName
     *            the payeeBankAccountName to set
     */
    public void setPayeeBankAccountName(String payeeBankAccountName) {
        this.payeeBankAccountName = payeeBankAccountName;
    }

   
    /**
     * @return the payeeBankLocationCode
     */
    public String getPayeeBankLocationCode() {
        return payeeBankLocationCode;
    }

    /**
     * @param payeeBankLocationCode the payeeBankLocationCode to set
     */
    public void setPayeeBankLocationCode(String payeeBankLocationCode) {
        this.payeeBankLocationCode = payeeBankLocationCode;
    }

    /**
     * @return the payeeBankLocationName
     */
    public String getPayeeBankLocationName() {
        return payeeBankLocationName;
    }

    /**
     * @param payeeBankLocationName the payeeBankLocationName to set
     */
    public void setPayeeBankLocationName(String payeeBankLocationName) {
        this.payeeBankLocationName = payeeBankLocationName;
    }

    /**
     * @return the payeeAreaCode
     */
    public String getPayeeAreaCode() {
        return payeeAreaCode;
    }

    /**
     * @param payeeAreaCode
     *            the payeeAreaCode to set
     */
    public void setPayeeAreaCode(String payeeAreaCode) {
        this.payeeAreaCode = payeeAreaCode;
    }

    /**
     * @return the payeeAreaName
     */
    public String getPayeeAreaName() {
        return payeeAreaName;
    }

    /**
     * @param payeeAreaName
     *            the payeeAreaName to set
     */
    public void setPayeeAreaName(String payeeAreaName) {
        this.payeeAreaName = payeeAreaName;
    }

    /**
     * @return the payeeBankCode
     */
    public String getPayeeBankCode() {
        return payeeBankCode;
    }

    /**
     * @param payeeBankCode
     *            the payeeBankCode to set
     */
    public void setPayeeBankCode(String payeeBankCode) {
        this.payeeBankCode = payeeBankCode;
    }

    /**
     * @return the payeeBankName
     */
    public String getPayeeBankName() {
        return payeeBankName;
    }

    /**
     * @param payeeBankName
     *            the payeeBankName to set
     */
    public void setPayeeBankName(String payeeBankName) {
        this.payeeBankName = payeeBankName;
    }

    /**
     * @return the cnapsCode
     */
    public String getCnapsCode() {
        return cnapsCode;
    }

    /**
     * @param cnapsCode
     *            the cnapsCode to set
     */
    public void setCnapsCode(String cnapsCode) {
        this.cnapsCode = cnapsCode;
    }

    /**
     * @return the cnapsName
     */
    public String getCnapsName() {
        return cnapsName;
    }

    /**
     * @param cnapsName
     *            the cnapsName to set
     */
    public void setCnapsName(String cnapsName) {
        this.cnapsName = cnapsName;
    }

    /**
     * @return the fastFlag
     */
    public String getFastFlag() {
        return fastFlag;
    }

    /**
     * @param fastFlag
     *            the fastFlag to set
     */
    public void setFastFlag(String fastFlag) {
        this.fastFlag = fastFlag;
    }

    /**
     * @return the corpInd
     */
    public String getCorpInd() {
        return corpInd;
    }

    /**
     * @param corpInd
     *            the corpInd to set
     */
    public void setCorpInd(String corpInd) {
        this.corpInd = corpInd;
    }

    /**
     * @return the purpose
     */
    public String getPurpose() {
        return purpose;
    }

    /**
     * @param purpose
     *            the purpose to set
     */
    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    /**
     * @return the memo
     */
    public String getMemo() {
        return memo;
    }

    /**
     * @param memo
     *            the memo to set
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the smsFlag
     */
    public String getSmsFlag() {
        return smsFlag;
    }

    /**
     * @param smsFlag
     *            the smsFlag to set
     */
    public void setSmsFlag(String smsFlag) {
        this.smsFlag = smsFlag;
    }

    /**
     * @return the cellPhone
     */
    public String getCellPhone() {
        return cellPhone;
    }

    /**
     * @param cellPhone
     *            the cellPhone to set
     */
    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    /**
     * @return the mailFlag
     */
    public String getMailFlag() {
        return mailFlag;
    }

    /**
     * @param mailFlag
     *            the mailFlag to set
     */
    public void setMailFlag(String mailFlag) {
        this.mailFlag = mailFlag;
    }

    /**
     * @return the mailAddr
     */
    public String getMailAddr() {
        return mailAddr;
    }

    /**
     * @param mailAddr
     *            the mailAddr to set
     */
    public void setMailAddr(String mailAddr) {
        this.mailAddr = mailAddr;
    }

    /**
     * @return the certType
     */
    public String getCertType() {
        return certType;
    }

    /**
     * @param certType
     *            the certType to set
     */
    public void setCertType(String certType) {
        this.certType = certType;
    }

    /**
     * @return the certNumber
     */
    public String getCertNumber() {
        return certNumber;
    }

    /**
     * @param certNumber
     *            the certNumber to set
     */
    public void setCertNumber(String certNumber) {
        this.certNumber = certNumber;
    }

    /**
     * @return the createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy
     *            the createdBy to set
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return the dateCreated
     */
    public Date getDateCreated() {
        return dateCreated;
    }

    /**
     * @param dateCreated
     *            the dateCreated to set
     */
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * @return the updatedBy
     */
    public String getUpdatedBy() {
        return updatedBy;
    }

    /**
     * @param updatedBy
     *            the updatedBy to set
     */
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     * @return the dateUpdated
     */
    public Date getDateUpdated() {
        return dateUpdated;
    }

    /**
     * @param dateUpdated
     *            the dateUpdated to set
     */
    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

}
