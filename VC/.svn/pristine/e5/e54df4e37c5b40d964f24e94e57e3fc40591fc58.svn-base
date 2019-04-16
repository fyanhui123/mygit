/**
 * 
 */
package com.tapi.tcs.vc.schema.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 印刷厂维护
 * 
 * @author wanghuajian
 * @since 2013-3-16
 * 
 */
@Entity
@Table(name = "VC_PRINTERY")
@SequenceGenerator(name = "SEQ_VC_PRINTERY", sequenceName = "SEQ_VC_PRINTERY", allocationSize = 1)
public class VcPrintery implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -538322924222380911L;

	/** 印刷厂流水 */
	@Id
	@Column(name = "ID_VC_PRINTERY")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VC_PRINTERY")
	private Long idVcPrintery;

	/** 印刷厂编号 */
	@Column(name = "PRINTERY_CODE")
	private String printeryCode;

	/** 印刷厂名称 */
	@Column(name = "PRINTERY_NAME")
	private String printeryName;

	/** 所属机构代码 *//*
	@Column(name = "ORG_CODE")
	private String orgCode;*/

	/** 工商代码 */
	@Column(name = "BUSIN_CODE")
	private String businCode;

	/** 地址 */
	@Column(name = "ADDRESS")
	private String address;

	/** 邮编 */
	@Column(name = "POST_CODE")
	private String postCode;

	/** 联系人 */
	@Column(name = "CORRESPONDER")
	private String corresponder;

	/** 电话 */
	@Column(name = "TEL")
	private String tel;

	/** 传真 */
	@Column(name = "FAX")
	private String fax;

	/** 电子邮件 */
	@Column(name = "EMAIL")
	private String email;

	/** 开户银行 */
	@Column(name = "BANK")
	private String bank;

	/** 开户账号 */
	@Column(name = "ACCOUNT_CODE")
	private String accountCode;

	/** 账户名称 */
	@Column(name = "ACCOUNT_NAME")
	private String accountName;

	/** 单证样式扫描文件名称 */
	@Column(name = "FILE_NAME")
	private String fileName;

	/** 印刷合同扫描文件路径 */
	@Column(name = "FILE_PATH")
	private String filePath;

	/** 合同期限 */
	@Column(name = "CONTRACT_TIMELIMIT")
	private Date contractTimeLimit;

	/** 有效标志(0无效/1有效) */
	@Column(name = "STATUS")
	private String status;

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
	
	
	// 自定义属性 //
	@Transient
	private String createdUser;
	
	@Transient
	private String updatedUser;	
	
	/** 印刷厂名称(完全匹配查询时使用) */
    @Transient
    private String printeryNameEQ;
	
	

	// 单证承印信息
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "vcPrintery")
	private List<VcPrintDocVersion> printDocVersionList = new ArrayList<VcPrintDocVersion>();

	public Long getIdVcPrintery() {
		return idVcPrintery;
	}

	public void setIdVcPrintery(Long idVcPrintery) {
		this.idVcPrintery = idVcPrintery;
	}

	public String getPrinteryCode() {
		return printeryCode;
	}

	public void setPrinteryCode(String printeryCode) {
		this.printeryCode = printeryCode;
	}

	public String getPrinteryName() {
		return printeryName;
	}

	public void setPrinteryName(String printeryName) {
		this.printeryName = printeryName;
	}

	/*public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}*/

	public String getBusinCode() {
		return businCode;
	}

	public void setBusinCode(String businCode) {
		this.businCode = businCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getCorresponder() {
		return corresponder;
	}

	public void setCorresponder(String corresponder) {
		this.corresponder = corresponder;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Date getContractTimeLimit() {
		return contractTimeLimit;
	}

	public void setContractTimeLimit(Date contractTimeLimit) {
		this.contractTimeLimit = contractTimeLimit;
	}

	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	


	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	// @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy =
	// "vcPrintery")
	public List<VcPrintDocVersion> getPrintDocVersionList() {
		return printDocVersionList;
	}

	public void setPrintDocVersionList(List<VcPrintDocVersion> printDocVersionList) {
		this.printDocVersionList = printDocVersionList;
	}

	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public String getUpdatedUser() {
		return updatedUser;
	}

	public void setUpdatedUser(String updatedUser) {
		this.updatedUser = updatedUser;
	}

    /**
     * @return the printeryNameEQ
     */
    public String getPrinteryNameEQ() {
        return printeryNameEQ;
    }

    /**
     * @param printeryNameEQ the printeryNameEQ to set
     */
    public void setPrinteryNameEQ(String printeryNameEQ) {
        this.printeryNameEQ = printeryNameEQ;
    }

	
}
