package com.tapi.tcs.vc.schema.model;

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
 * POJO Class VC_TAKER
 */
@Entity
@Table(name = "VC_TAKER")
@SequenceGenerator(name = "SEQ_VC_TAKER", sequenceName = "SEQ_VC_TAKER", allocationSize = 1)
public class VcTaker implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	/** 单证使用人表流水 */
	private Long id;
	/** 使用人代码 */
	private String takerCode;
	/** 使用人名称 */
	private String takerName;
	/** 所属机构代码 */
	private String orgCode;
	/**所属中介机构代码*/
	private String agencyOrgCode;
	/**使用人类型代码*/
	private String takerTypeCode;
	/**银行卡折类型*/
	private String passbookOrCard;
	/**银行账号*/
	private String bankAccount;
	/**账户名称*/
	private String bankAccountName;
	/**银行代码*/
	private String bankCode;
	/**开户银行名称*/
	private String bankName;
	/** 有效标志 */
	private String status;
	/** 创建人 */
	private String createdBy;
	/** 创建时间 */
	private Date dateCreated;
	/** 修改人 */
	private String updatedBy;
	/** 修改时间 */
	private Date dateUpdated;
	
	
	/**自定义字段*/
    private String orgName;   
    private String newOrgCode;    
    private String newOrgName;
	

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VC_TAKER")
	@Column(name = "ID_VC_TAKER", unique = true, nullable = false, precision=20, scale=0)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "TAKER_CODE", nullable = false, length=100)
	public String getTakerCode() {
		return this.takerCode;
	}

	public void setTakerCode(String takerCode) {
		this.takerCode = takerCode;
	}

	@Column(name = "TAKER_NAME", nullable = false, length=200)
	public String getTakerName() {
		return this.takerName;
	}

	public void setTakerName(String takerName) {
		this.takerName = takerName;
	}

	@Column(name = "ORG_CODE", nullable = false, length=10)
	public String getOrgCode() {
		return this.orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	@Column(name = "AGENCY_ORG_CODE")
	public String getAgencyOrgCode() {
		return agencyOrgCode;
	}

	public void setAgencyOrgCode(String agencyOrgCode) {
		this.agencyOrgCode = agencyOrgCode;
	}

	@Column(name = "TAKER_TYPE_CODE")
	public String getTakerTypeCode() {
		return takerTypeCode;
	}

	public void setTakerTypeCode(String takerTypeCode) {
		this.takerTypeCode = takerTypeCode;
	}

	@Column(name = "PASSBOOK_OR_CARD")
	public String getPassbookOrCard() {
		return passbookOrCard;
	}

	public void setPassbookOrCard(String passbookOrCard) {
		this.passbookOrCard = passbookOrCard;
	}

	@Column(name = "BANK_ACCOUNT")
	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	@Column(name = "BANK_ACCOUNT_NAME")
	public String getBankAccountName() {
		return bankAccountName;
	}

	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}

	@Column(name = "BANK_CODE")
	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	@Column(name = "BANK_NAME")
	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	@Column(name = "STATUS", nullable = false, length=1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "CREATED_BY")
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "DATE_CREATED")
	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Column(name = "UPDATED_BY")
	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Column(name = "DATE_UPDATED")
	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	@Transient
	public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    @Transient
    public String getNewOrgCode() {
        return newOrgCode;
    }

    public void setNewOrgCode(String newOrgCode) {
        this.newOrgCode = newOrgCode;
    }

    @Transient
    public String getNewOrgName() {
        return newOrgName;
    }

    public void setNewOrgName(String newOrgName) {
        this.newOrgName = newOrgName;
    }
	
	
	
}
