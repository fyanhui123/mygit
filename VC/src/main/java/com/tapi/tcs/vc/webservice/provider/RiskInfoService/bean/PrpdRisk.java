package com.tapi.tcs.vc.webservice.provider.RiskInfoService.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

/**
 * 险种信息
 */
public class PrpdRisk {
	private List<PrpdRiskClause> prpdRiskClause;
	private List<PrpdRiskKind> prpdRiskKind;
	private List<PrpdRiskDynamicItem> prpdRiskDynamicItem;
	private List<PrpdRiskEngage> prpdRiskEngage;
	private List<PrpdRiskLimit> prpdRiskLimit;
	/**
	 * 险类代码
	 */
	private String classCode;
	/**
	 * 险种代码
	 */
	private String riskCode;
	/**
	 * 险种简体中文简称
	 */
	private String riskNameSimple;
	/**
	 * 险种简体中文全称
	 */
	private String riskName;
	/**
	 * 险种繁体中文简称
	 */
	private String riskTNameSimple;
	/**
	 * 险种繁体中文全称
	 */
	private String riskTName;
	/**
	 * 险种英文简称
	 */
	private String riskENameSimple;
	/**
	 * 险种英文全称
	 */
	private String riskEName;
	/**
	 * 单证类型
	 */
	private String documentType;
	/**
	 * 理赔参照险种
	 */
	private String referRisk;
	/**
	 * 收付险种代码
	 */
	private String payRiskCode;
	/**
	 * 是否支持定额（0：只支持非定额1：全支持）
	 */
	private String quotaType;
	/**
	 * 统计险类
	 */
	private String countType;
	/**
	 * 是否可以续保（0：是1：否）
	 */
	private String reinsuranceType;
	/**
	 * 是否多标的（0:是、1：否）
	 */
	private String dyItemStatus;
	/**
	 * 是否控制最低保费（0:是、1：否）
	 */
	private String isControlMinType;
	/**
	 * 最低保费
	 */
	private String minPremium;
	/**
	 * 团单/个单标记（0:团单、1：个单、2：混合）
	 */
	private String groupType;
	/**
	 * 共保出单费、退保手续的增值税税率
	 */
	private String taxRate;
	/**
	 * 延期缴费天数
	 */
	private String delayDays;
	/**
	 * 倒签单天数
	 */
	private String backDays;
	/**
	 * 承保参考险种
	 */
	private String underwriteRisk;
	/**
	 * 费率单位100:百分位1000：千分位
	 */
	private String rateUnit;
	/**
	 * 备注
	 */
	private String remark;
	public String getClassCode() {
		return classCode;
	}
	@XmlElement(name = "classCode", required = true)
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	public String getRiskCode() {
		return riskCode;
	}
	@XmlElement(name = "riskCode", required = true)
	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}
	public String getRiskName() {
		return riskName;
	}
	@XmlElement(name = "riskName", required = true)
	public void setRiskName(String riskName) {
		this.riskName = riskName;
	}
	
	public String getDocumentType() {
		return documentType;
	}
	@XmlElement(name = "documentType", required = true)
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	
	
	public String getRiskNameSimple() {
		return riskNameSimple;
	}
	
	@XmlElement(name = "riskNameSimple", required = false)
	public void setRiskNameSimple(String riskNameSimple) {
		this.riskNameSimple = riskNameSimple;
	}

	public String getRiskTNameSimple() {
		return riskTNameSimple;
	}
	@XmlElement(name = "riskTNameSimple", required = false)
	public void setRiskTNameSimple(String riskTNameSimple) {
		this.riskTNameSimple = riskTNameSimple;
	}
	public String getRiskTName() {
		return riskTName;
	}
	@XmlElement(name = "riskTName", required = false)
	public void setRiskTName(String riskTName) {
		this.riskTName = riskTName;
	}
	public String getRiskENameSimple() {
		return riskENameSimple;
	}
	@XmlElement(name = "riskENameSimple", required = false)
	public void setRiskENameSimple(String riskENameSimple) {
		this.riskENameSimple = riskENameSimple;
	}
	public String getRiskEName() {
		return riskEName;
	}
	@XmlElement(name = "riskEName", required = false)
	public void setRiskEName(String riskEName) {
		this.riskEName = riskEName;
	}

	public String getReferRisk() {
		return referRisk;
	}
	@XmlElement(name = "referRisk", required = false)
	public void setReferRisk(String referRisk) {
		this.referRisk = referRisk;
	}
	public String getPayRiskCode() {
		return payRiskCode;
	}
	@XmlElement(name = "payRiskCode", required = false)
	public void setPayRiskCode(String payRiskCode) {
		this.payRiskCode = payRiskCode;
	}
	public String getQuotaType() {
		return quotaType;
	}
	@XmlElement(name = "quotaType", required = false)
	public void setQuotaType(String quotaType) {
		this.quotaType = quotaType;
	}
	public String getCountType() {
		return countType;
	}
	@XmlElement(name = "countType", required = false)
	public void setCountType(String countType) {
		this.countType = countType;
	}
	public String getReinsuranceType() {
		return reinsuranceType;
	}
	@XmlElement(name = "reinsuranceType", required = false)
	public void setReinsuranceType(String reinsuranceType) {
		this.reinsuranceType = reinsuranceType;
	}
	public String getDyItemStatus() {
		return dyItemStatus;
	}
	@XmlElement(name = "dyItemStatus", required = false)
	public void setDyItemStatus(String dyItemStatus) {
		this.dyItemStatus = dyItemStatus;
	}
	public String getIsControlMinType() {
		return isControlMinType;
	}
	@XmlElement(name = "isControlMinType", required = false)
	public void setIsControlMinType(String isControlMinType) {
		this.isControlMinType = isControlMinType;
	}
	public String getMinPremium() {
		return minPremium;
	}
	@XmlElement(name = "minPremium", required = false)
	public void setMinPremium(String minPremium) {
		this.minPremium = minPremium;
	}
	public String getGroupType() {
		return groupType;
	}
	@XmlElement(name = "groupType", required = false)
	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}
	public String getTaxRate() {
		return taxRate;
	}
	@XmlElement(name = "taxRate", required = false)
	public void setTaxRate(String taxRate) {
		this.taxRate = taxRate;
	}
	public String getDelayDays() {
		return delayDays;
	}
	@XmlElement(name = "delayDays", required = false)
	public void setDelayDays(String delayDays) {
		this.delayDays = delayDays;
	}
	public String getBackDays() {
		return backDays;
	}
	@XmlElement(name = "backDays", required = false)
	public void setBackDays(String backDays) {
		this.backDays = backDays;
	}
	public String getUnderwriteRisk() {
		return underwriteRisk;
	}
	@XmlElement(name = "underwriteRisk", required = false)
	public void setUnderwriteRisk(String underwriteRisk) {
		this.underwriteRisk = underwriteRisk;
	}
	public String getRateUnit() {
		return rateUnit;
	}
	@XmlElement(name = "rateUnit", required = false)
	public void setRateUnit(String rateUnit) {
		this.rateUnit = rateUnit;
	}
	public String getRemark() {
		return remark;
	}
	@XmlElement(name = "remark", required = false)
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public List<PrpdRiskClause> getPrpdRiskClause() {
		return prpdRiskClause;
	}
	@XmlElementWrapper (name = "prpdRiskClauseList")
	@XmlElement(name="prpdRiskClause")
	public void setPrpdRiskClause(List<PrpdRiskClause> prpdRiskClause) {
		this.prpdRiskClause = prpdRiskClause;
	}
	public List<PrpdRiskKind> getPrpdRiskKind() {
		return prpdRiskKind;
	}
	@XmlElementWrapper (name = "prpdRiskKindList")
	@XmlElement(name="prpdRiskKind")
	public void setPrpdRiskKind(List<PrpdRiskKind> prpdRiskKind) {
		this.prpdRiskKind = prpdRiskKind;
	}
	public List<PrpdRiskDynamicItem> getPrpdRiskDynamicItem() {
		return prpdRiskDynamicItem;
	}
	@XmlElementWrapper (name = "prpdRiskDynamicItemList")
	@XmlElement(name="prpdRiskDynamicItem")
	public void setPrpdRiskDynamicItem(List<PrpdRiskDynamicItem> prpdRiskDynamicItem) {
		this.prpdRiskDynamicItem = prpdRiskDynamicItem;
	}
	public List<PrpdRiskEngage> getPrpdRiskEngage() {
		return prpdRiskEngage;
	}
	@XmlElementWrapper (name = "prpdRiskEngageList")
	@XmlElement(name="prpdRiskEngage")
	public void setPrpdRiskEngage(List<PrpdRiskEngage> prpdRiskEngage) {
		this.prpdRiskEngage = prpdRiskEngage;
	}
	public List<PrpdRiskLimit> getPrpdRiskLimit() {
		return prpdRiskLimit;
	}
	@XmlElementWrapper (name = "prpdRiskKindList")
	@XmlElement(name="prpdRiskLimit")
	public void setPrpdRiskLimit(List<PrpdRiskLimit> prpdRiskLimit) {
		this.prpdRiskLimit = prpdRiskLimit;
	}

	
}
