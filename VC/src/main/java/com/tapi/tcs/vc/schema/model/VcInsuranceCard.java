package com.tapi.tcs.vc.schema.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 激活卡导入明细表
 * 
 * @author whj
 * 
 */
@Entity
@Table(name = "VC_INSURANCE_CARD")
@SequenceGenerator(name = "SEQ_VC_INSURANCE_CARD", sequenceName = "SEQ_VC_INSURANCE_CARD", allocationSize = 1)
public class VcInsuranceCard implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 3380239975127908917L;

    /** 激活卡信息表流水 */
    @Id
    @Column(name = "ID_VC_INSURANCE_CARD")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VC_INSURANCE_CARD")
    private Long idVcInsuranceCard;

    /** 导入操作流水 */
    @Column(name = "ID_VC_INSU_CARD_IMPORT_INFO", insertable = false, updatable = false)
    private Long idVcInsuCardImportInfo;

    /** 导入信息主表 */
    @ManyToOne(cascade = { CascadeType.ALL }, targetEntity = VcInsuCardImportInfo.class)
    @JoinColumn(name = "ID_VC_INSU_CARD_IMPORT_INFO", referencedColumnName = "ID_VC_INSU_CARD_IMPORT_INFO", nullable = false)
    private VcInsuCardImportInfo vcInsuCardImportInfo; // 归属险类

    /** 单证类型代码 */
    @Column(name = "DOC_VER_CODE ")
    private String docVerCode;

    /** 卡号 */
    @Column(name = "CARD_NO")
    private String cardNo;

    /** 密码 */
    @Column(name = "PASSWORD")
    private String password;

    /** 加密密码 */
    @Column(name = "ENCRYPTED_PW")
    private String encryptedPw;

    /** 面值 */
    @Column(name = "VALUE")
    private float value;

    /** 有效期 */
    @Column(name = "VALID_DATE")
    private Date validDate;

    /** 激活卡状态▲ */
    @Column(name = "CARD_STATUS")
    private String cardStatus;

    /** 导入时间 */
    @Column(name = "IMPORT_TIME")
    private Date importTime;

    /** 激活时间 */
    @Column(name = "ACTIVE_TIME")
    private Date activeTime;

    /** 激活方式 */
    @Column(name = "ACTIVE_TYPE")
    private String activeType;

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

    //modify by zhxiao3 VC-117 激活卡信息新增"关联大保单" begin
    /** 关联大保单号 */
    @Column(name = "BigPolicyNo")
    private String bigPolicyNo;
    //modify by zhxiao3 VC-117 激活卡信息新增"关联大保单" end


	// add 自定义字段
    /** 导入起始时间 */
    @Transient
    private Date importStartTime;

    @Transient
    private Date importEndTime;

   /**
    *起始卡号
    */
    @Transient
    private String startCardNum;

    /**
     * 终止卡号
     */
    
    @Transient
    private String endCardNum;

    /**
     * @return the idVcInsuranceCard
     */
    public Long getIdVcInsuranceCard() {
        return idVcInsuranceCard;
    }

    /**
     * @param idVcInsuranceCard
     *            the idVcInsuranceCard to set
     */
    public void setIdVcInsuranceCard(Long idVcInsuranceCard) {
        this.idVcInsuranceCard = idVcInsuranceCard;
    }

    /**
     * @return the idVcInsuCardImportInfo
     */
    public Long getIdVcInsuCardImportInfo() {
        return idVcInsuCardImportInfo;
    }

    /**
     * @param idVcInsuCardImportInfo
     *            the idVcInsuCardImportInfo to set
     */
    public void setIdVcInsuCardImportInfo(Long idVcInsuCardImportInfo) {
        this.idVcInsuCardImportInfo = idVcInsuCardImportInfo;
    }

    /**
     * @return the vcInsuCardImportInfo
     */
    public VcInsuCardImportInfo getVcInsuCardImportInfo() {
        return vcInsuCardImportInfo;
    }

    /**
     * @param vcInsuCardImportInfo
     *            the vcInsuCardImportInfo to set
     */
    public void setVcInsuCardImportInfo(VcInsuCardImportInfo vcInsuCardImportInfo) {
        this.vcInsuCardImportInfo = vcInsuCardImportInfo;
    }

    /**
     * @return the docVerCode
     */
    public String getDocVerCode() {
        return docVerCode;
    }

    /**
     * @param docVerCode
     *            the docVerCode to set
     */
    public void setDocVerCode(String docVerCode) {
        this.docVerCode = docVerCode;
    }

    /**
     * @return the cardNo
     */
    public String getCardNo() {
        return cardNo;
    }

    /**
     * @param cardNo
     *            the cardNo to set
     */
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     *            the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the encryptedPw
     */
    public String getEncryptedPw() {
        return encryptedPw;
    }

    /**
     * @param encryptedPw
     *            the encryptedPw to set
     */
    public void setEncryptedPw(String encryptedPw) {
        this.encryptedPw = encryptedPw;
    }

    /**
     * @return the value
     */
    public float getValue() {
        return value;
    }

    /**
     * @param value
     *            the value to set
     */
    public void setValue(float value) {
        this.value = value;
    }

    /**
     * @return the validDate
     */
    public Date getValidDate() {
        return validDate;
    }

    /**
     * @param validDate
     *            the validDate to set
     */
    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    /**
     * @return the cardStatus
     */
    public String getCardStatus() {
        return cardStatus;
    }

    /**
     * @param cardStatus
     *            the cardStatus to set
     */
    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }

    /**
     * @return the importTime
     */
    public Date getImportTime() {
        return importTime;
    }

    /**
     * @param importTime
     *            the importTime to set
     */
    public void setImportTime(Date importTime) {
        this.importTime = importTime;
    }

    /**
     * @return the activeTime
     */
    public Date getActiveTime() {
        return activeTime;
    }

    /**
     * @param activeTime
     *            the activeTime to set
     */
    public void setActiveTime(Date activeTime) {
        this.activeTime = activeTime;
    }

    /**
     * @return the activeType
     */
    public String getActiveType() {
        return activeType;
    }

    /**
     * @param activeType
     *            the activeType to set
     */
    public void setActiveType(String activeType) {
        this.activeType = activeType;
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

    /**
     * @return the importStartTime
     */
    public Date getImportStartTime() {
        return importStartTime;
    }

    /**
     * @param importStartTime
     *            the importStartTime to set
     */
    public void setImportStartTime(Date importStartTime) {
        this.importStartTime = importStartTime;
    }

    /**
     * @return the importEndTime
     */
    public Date getImportEndTime() {
        return importEndTime;
    }

    /**
     * @param importEndTime
     *            the importEndTime to set
     */
    public void setImportEndTime(Date importEndTime) {
        this.importEndTime = importEndTime;
    }

    /**
     * @return the startCardNum
     */
    public String getStartCardNum() {
        return startCardNum;
    }

    /**
     * @param startCardNum the startCardNum to set
     */
    public void setStartCardNum(String startCardNum) {
        this.startCardNum = startCardNum;
    }

    /**
     * @return the endCardNum
     */
    public String getEndCardNum() {
        return endCardNum;
    }

    /**
     * @param endCardNum the endCardNum to set
     */
    public void setEndCardNum(String endCardNum) {
        this.endCardNum = endCardNum;
    }

    /**
     * @return the bigPolicyNo
     */
    public String getBigPolicyNo() {
		return bigPolicyNo;
	}

    /**
     * @param bigPolicyNo the bigPolicyNo to set
     */
	public void setBigPolicyNo(String bigPolicyNo) {
		this.bigPolicyNo = bigPolicyNo;
	}
}
