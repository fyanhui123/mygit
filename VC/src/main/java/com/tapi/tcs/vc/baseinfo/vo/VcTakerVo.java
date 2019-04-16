package com.tapi.tcs.vc.baseinfo.vo;

public class VcTakerVo {

	/** 单证使用人表流水 */
	private Long id;
	/** 使用人代码 */
	private String takerCode;
	/** 使用人名称 */
	private String takerName;
	/** 所属机构代码 */
	private String orgCode;
	/**所属机构名称*/
	private String orgName;
	/**所属中介机构代码*/
	private String agencyOrgCode;
	/**所属中介机构名称*/
	private String agencyOrgName;
	/**使用人类型代码*/
	private String takerTypeCode;
	/**使用人类型名称*/
	private String takerTypeName;
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
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTakerCode() {
		return takerCode;
	}
	public void setTakerCode(String takerCode) {
		this.takerCode = takerCode;
	}
	public String getTakerName() {
		return takerName;
	}
	public void setTakerName(String takerName) {
		this.takerName = takerName;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getAgencyOrgCode() {
		return agencyOrgCode;
	}
	public void setAgencyOrgCode(String agencyOrgCode) {
		this.agencyOrgCode = agencyOrgCode;
	}
	public String getAgencyOrgName() {
		return agencyOrgName;
	}
	public void setAgencyOrgName(String agencyOrgName) {
		this.agencyOrgName = agencyOrgName;
	}
	public String getTakerTypeCode() {
		return takerTypeCode;
	}
	public void setTakerTypeCode(String takerTypeCode) {
		this.takerTypeCode = takerTypeCode;
	}
	public String getTakerTypeName() {
		return takerTypeName;
	}
	public void setTakerTypeName(String takerTypeName) {
		this.takerTypeName = takerTypeName;
	}
	public String getPassbookOrCard() {
		return passbookOrCard;
	}
	public void setPassbookOrCard(String passbookOrCard) {
		this.passbookOrCard = passbookOrCard;
	}
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	public String getBankAccountName() {
		return bankAccountName;
	}
	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
