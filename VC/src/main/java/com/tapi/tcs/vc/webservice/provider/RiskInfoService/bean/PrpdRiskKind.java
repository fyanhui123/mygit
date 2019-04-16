package com.tapi.tcs.vc.webservice.provider.RiskInfoService.bean;

import javax.xml.bind.annotation.XmlElement;

/**
 * 险种险别信息
 */
public class PrpdRiskKind {
	/**
	 * 条款代码
	 */
	private String clauseCode;
	/**
	 * 条款名称
	 */
	private String clausEName;
	/**
	 * 核心险别代码
	 */
	private String kindCode;
	/**
	 * 核心险别中文全称
	 */
	private String kindName;
	/**
	 * 核心险别中文简称
	 */
	private String kindNameSimple;
	/**
	 * 核心险别英文全称
	 */
	private String kindENameSimple;
	/**
	 * 核心险别英文简称
	 */
	private String kindEName;
	/**
	 * 核心险别繁体全称
	 */
	private String kindTName;
	/**
	 * 核心险别繁体简称
	 */
	private String kindTNameSimple;
	/**
	 * 险类代码
	 */
	private String classCode;
	/**
	 * 险类名称
	 */
	private String className;
	/**
	 * 明细分类值
	 */
	private String detailedCategoryValue;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 是否含税
	 */
	private String taxInclusiveFlag;
	/**
	 * 税率
	 */
	private String taxAmount;
	/**
	 * 收付险种代码
	 */
	private String payRiskCode;
	/**
	 * 状态
	 */
	private String state;
	public String getClauseCode() {
		return clauseCode;
	}
	@XmlElement(name = "clauseCode", required = false)
	public void setClauseCode(String clauseCode) {
		this.clauseCode = clauseCode;
	}
	public String getClausEName() {
		return clausEName;
	}
	@XmlElement(name = "clausEName", required = false)
	public void setClausEName(String clausEName) {
		this.clausEName = clausEName;
	}
	public String getKindCode() {
		return kindCode;
	}
	@XmlElement(name = "kindCode", required = false)
	public void setKindCode(String kindCode) {
		this.kindCode = kindCode;
	}
	public String getKindName() {
		return kindName;
	}
	@XmlElement(name = "kindName", required = false)
	public void setKindName(String kindName) {
		this.kindName = kindName;
	}
	public String getKindNameSimple() {
		return kindNameSimple;
	}
	@XmlElement(name = "kindNameSimple", required = false)
	public void setKindNameSimple(String kindNameSimple) {
		this.kindNameSimple = kindNameSimple;
	}
	public String getKindENameSimple() {
		return kindENameSimple;
	}
	@XmlElement(name = "kindENameSimple", required = false)
	public void setKindENameSimple(String kindENameSimple) {
		this.kindENameSimple = kindENameSimple;
	}
	public String getKindEName() {
		return kindEName;
	}
	@XmlElement(name = "kindEName", required = false)
	public void setKindEName(String kindEName) {
		this.kindEName = kindEName;
	}
	public String getKindTName() {
		return kindTName;
	}
	@XmlElement(name = "kindTName", required = false)
	public void setKindTName(String kindTName) {
		this.kindTName = kindTName;
	}
	public String getKindTNameSimple() {
		return kindTNameSimple;
	}
	@XmlElement(name = "kindTNameSimple", required = false)
	public void setKindTNameSimple(String kindTNameSimple) {
		this.kindTNameSimple = kindTNameSimple;
	}
	public String getClassCode() {
		return classCode;
	}
	@XmlElement(name = "classCode", required = false)
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	public String getClassName() {
		return className;
	}
	@XmlElement(name = "className", required = false)
	public void setClassName(String className) {
		this.className = className;
	}
	public String getDetailedCategoryValue() {
		return detailedCategoryValue;
	}
	@XmlElement(name = "detailedCategoryValue", required = false)
	public void setDetailedCategoryValue(String detailedCategoryValue) {
		this.detailedCategoryValue = detailedCategoryValue;
	}
	public String getRemark() {
		return remark;
	}
	@XmlElement(name = "remark", required = false)
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getTaxInclusiveFlag() {
		return taxInclusiveFlag;
	}
	@XmlElement(name = "taxInclusiveFlag", required = false)
	public void setTaxInclusiveFlag(String taxInclusiveFlag) {
		this.taxInclusiveFlag = taxInclusiveFlag;
	}
	public String getTaxAmount() {
		return taxAmount;
	}
	@XmlElement(name = "taxAmount", required = false)
	public void setTaxAmount(String taxAmount) {
		this.taxAmount = taxAmount;
	}
	public String getPayRiskCode() {
		return payRiskCode;
	}
	@XmlElement(name = "payRiskCode", required = false)
	public void setPayRiskCode(String payRiskCode) {
		this.payRiskCode = payRiskCode;
	}
	public String getState() {
		return state;
	}
	@XmlElement(name = "state", required = false)
	public void setState(String state) {
		this.state = state;
	}
}
