package com.tapi.tcs.vc.schema.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "PUB_RISK")
public class PubRisk implements Serializable{

	private static final long serialVersionUID = 1L;
	/**险种代码*/
	private String riskCode;
	/**险种简体中文名称*/
	private String riskCname;
	/**险种繁体中文名称*/
	private String riskTname;
	/**险种英文名称*/
	private String riskEname;
	/**费率单位*/
	private Long calculator;
	/**参见PUB_RISK_CLASS*/
	private String riskClass;
	private String documentInd;
	/**1:强制保险；0:非强制保险*/
	private String statutoryInd;
	/**1:可以续保；0:不可续保*/
	private String renewInd;
	/**1:自动续保；0:不自动续保*/
	private String autoRenewInd;
	private String counteractInd;
	private String reinsinInd;
	/**1:个单；2:团单 3:混合*/
	private String groupInd;
	private Long hesitatebackDays;
	/**1:是；0:否*/
	private String declarActionInd;
	/**1:是；0:否*/
	private String rationInd;
	/**1:是；0:否*/
	private String aloneInd;
	/**1:是；0:否*/
	private String lowestPremiumInd;
	/**未用，有关最低保费参见最低保费相关表*/
	private String lowestPremCurrency;
	/**未用，有关最低保费参见最低保费相关表*/
	private BigDecimal lowestPremium;
	private String estimatelossIndLevel;
	private String compensateLevel;
	/**1:动态 0:非动态*/
	private String dynamicInd;
	/**0：普通型；1：计划型*/
	private String planInd;
	/**创建人*/
	private String creatorCode;
	/**创建时间*/
	private Date createTime;
	/**最后修改人*/
	private String updaterCode;
	/**最后修改时间*/
	private Date updateTime;
	/**生效日期*/
	private Date validDate;
	/**失效日期*/
	private Date inValidDate;
	/**有效标志,1:有效 0:无效*/
	private String validInd;
	/**备注*/
	private String remark;
	/**标志字段*/
	private String flag;
	/**共用模板险种的界面*/
	private String templateRiskCode;
	/**是否预约协议：0-否；1-是*/
	private String openCoverInd;
	/**允许关联产品：多个险种以逗号分隔*/
	private String relatedRiskCode;
	/**是否综合险模板标示: 0 单险种模板，1 综合险模板*/
	private String productInd;
	private String itemDetailInd;
	private String subKindWithItemDetailInd;
	private String multiItemInd;
	private String itemMortGageeInd;
	/**产品类别: 0 普通产品 1 理财产品*/
	private String productClass;
	
	
	/******自定义字段**********************/
	/**险种代码*/
    private String riskCodeLike;
	
	@Id
	@Column(name = "RISKCODE")
	public String getRiskCode() {
		return riskCode;
	}
	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}
	@Column(name = "RISKCNAME")
	public String getRiskCname() {
		return riskCname;
	}
	public void setRiskCname(String riskCname) {
		this.riskCname = riskCname;
	}
	@Column(name = "RISKTNAME")
	public String getRiskTname() {
		return riskTname;
	}
	public void setRiskTname(String riskTname) {
		this.riskTname = riskTname;
	}
	@Column(name = "RISKENAME")
	public String getRiskEname() {
		return riskEname;
	}
	public void setRiskEname(String riskEname) {
		this.riskEname = riskEname;
	}
	@Column(name = "CALCULATOR")
	public Long getCalculator() {
		return calculator;
	}
	public void setCalculator(Long calculator) {
		this.calculator = calculator;
	}
	@Column(name = "RISKCLASS")
	public String getRiskClass() {
		return riskClass;
	}
	public void setRiskClass(String riskClass) {
		this.riskClass = riskClass;
	}
	@Column(name = "DOCUMENTIND")
	public String getDocumentInd() {
		return documentInd;
	}
	public void setDocumentInd(String documentInd) {
		this.documentInd = documentInd;
	}
	@Column(name = "STATUTORYIND")
	public String getStatutoryInd() {
		return statutoryInd;
	}
	public void setStatutoryInd(String statutoryInd) {
		this.statutoryInd = statutoryInd;
	}
	@Column(name = "RENEWIND")
	public String getRenewInd() {
		return renewInd;
	}
	public void setRenewInd(String renewInd) {
		this.renewInd = renewInd;
	}
	@Column(name = "AUTORENEWIND")
	public String getAutoRenewInd() {
		return autoRenewInd;
	}
	public void setAutoRenewInd(String autoRenewInd) {
		this.autoRenewInd = autoRenewInd;
	}
	@Column(name = "COUNTERACTIND")
	public String getCounteractInd() {
		return counteractInd;
	}
	public void setCounteractInd(String counteractInd) {
		this.counteractInd = counteractInd;
	}
	@Column(name = "REINSININD")
	public String getReinsinInd() {
		return reinsinInd;
	}
	public void setReinsinInd(String reinsinInd) {
		this.reinsinInd = reinsinInd;
	}
	@Column(name = "GROUPIND")
	public String getGroupInd() {
		return groupInd;
	}
	public void setGroupInd(String groupInd) {
		this.groupInd = groupInd;
	}
	@Column(name = "HESITATEBACKDAYS")
	public Long getHesitatebackDays() {
		return hesitatebackDays;
	}
	public void setHesitatebackDays(Long hesitatebackDays) {
		this.hesitatebackDays = hesitatebackDays;
	}
	@Column(name = "DECLARATIONIND")
	public String getDeclarActionInd() {
		return declarActionInd;
	}
	public void setDeclarActionInd(String declarActionInd) {
		this.declarActionInd = declarActionInd;
	}
	@Column(name = "RATIONIND")
	public String getRationInd() {
		return rationInd;
	}
	public void setRationInd(String rationInd) {
		this.rationInd = rationInd;
	}
	@Column(name = "ALONEIND")
	public String getAloneInd() {
		return aloneInd;
	}
	public void setAloneInd(String aloneInd) {
		this.aloneInd = aloneInd;
	}
	@Column(name = "LOWESTPREMIUMIND")
	public String getLowestPremiumInd() {
		return lowestPremiumInd;
	}
	public void setLowestPremiumInd(String lowestPremiumInd) {
		this.lowestPremiumInd = lowestPremiumInd;
	}
	@Column(name = "LOWESTPREMCURRENCY")
	public String getLowestPremCurrency() {
		return lowestPremCurrency;
	}
	public void setLowestPremCurrency(String lowestPremCurrency) {
		this.lowestPremCurrency = lowestPremCurrency;
	}
	@Column(name = "LOWESTPREMIUM")
	public BigDecimal getLowestPremium() {
		return lowestPremium;
	}
	public void setLowestPremium(BigDecimal lowestPremium) {
		this.lowestPremium = lowestPremium;
	}
	@Column(name = "ESTIMATELOSSINDLEVEL")
	public String getEstimatelossIndLevel() {
		return estimatelossIndLevel;
	}
	public void setEstimatelossIndLevel(String estimatelossIndLevel) {
		this.estimatelossIndLevel = estimatelossIndLevel;
	}
	@Column(name = "COMPENSATELEVEL")
	public String getCompensateLevel() {
		return compensateLevel;
	}
	public void setCompensateLevel(String compensateLevel) {
		this.compensateLevel = compensateLevel;
	}
	@Column(name = "DYNAMICIND")
	public String getDynamicInd() {
		return dynamicInd;
	}
	public void setDynamicInd(String dynamicInd) {
		this.dynamicInd = dynamicInd;
	}
	@Column(name = "PLANIND")
	public String getPlanInd() {
		return planInd;
	}
	public void setPlanInd(String planInd) {
		this.planInd = planInd;
	}
	@Column(name = "CREATORCODE")
	public String getCreatorCode() {
		return creatorCode;
	}
	public void setCreatorCode(String creatorCode) {
		this.creatorCode = creatorCode;
	}
	@Column(name = "CREATETIME")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Column(name = "UPDATERCODE")
	public String getUpdaterCode() {
		return updaterCode;
	}
	public void setUpdaterCode(String updaterCode) {
		this.updaterCode = updaterCode;
	}
	@Column(name = "UPDATETIME")
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Column(name = "VALIDDATE")
	public Date getValidDate() {
		return validDate;
	}
	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}
	@Column(name = "INVALIDDATE")
	public Date getInValidDate() {
		return inValidDate;
	}
	public void setInValidDate(Date inValidDate) {
		this.inValidDate = inValidDate;
	}
	@Column(name = "VALIDIND")
	public String getValidInd() {
		return validInd;
	}
	public void setValidInd(String validInd) {
		this.validInd = validInd;
	}
	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name = "FLAG")
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	@Column(name = "TEMPLATERISKCODE")
	public String getTemplateRiskCode() {
		return templateRiskCode;
	}
	public void setTemplateRiskCode(String templateRiskCode) {
		this.templateRiskCode = templateRiskCode;
	}
	@Column(name = "OPENCOVERIND")
	public String getOpenCoverInd() {
		return openCoverInd;
	}
	public void setOpenCoverInd(String openCoverInd) {
		this.openCoverInd = openCoverInd;
	}
	@Column(name = "RELATEDRISKCODE")
	public String getRelatedRiskCode() {
		return relatedRiskCode;
	}
	public void setRelatedRiskCode(String relatedRiskCode) {
		this.relatedRiskCode = relatedRiskCode;
	}
	@Column(name = "PRODUCTIND")
	public String getProductInd() {
		return productInd;
	}
	public void setProductInd(String productInd) {
		this.productInd = productInd;
	}
	@Column(name = "ITEMDETAILIND")
	public String getItemDetailInd() {
		return itemDetailInd;
	}
	public void setItemDetailInd(String itemDetailInd) {
		this.itemDetailInd = itemDetailInd;
	}
	@Column(name = "SUBKINDWITHITEMDETAILIND")
	public String getSubKindWithItemDetailInd() {
		return subKindWithItemDetailInd;
	}
	public void setSubKindWithItemDetailInd(String subKindWithItemDetailInd) {
		this.subKindWithItemDetailInd = subKindWithItemDetailInd;
	}
	@Column(name = "MULTIITEMIND")
	public String getMultiItemInd() {
		return multiItemInd;
	}
	public void setMultiItemInd(String multiItemInd) {
		this.multiItemInd = multiItemInd;
	}
	@Column(name = "ITEMMORTGAGEEIND")
	public String getItemMortGageeInd() {
		return itemMortGageeInd;
	}
	public void setItemMortGageeInd(String itemMortGageeInd) {
		this.itemMortGageeInd = itemMortGageeInd;
	}
	@Column(name = "PRODUCTCLASS")
	public String getProductClass() {
		return productClass;
	}
	public void setProductClass(String productClass) {
		this.productClass = productClass;
	}
	
	@Transient
    public String getRiskCodeLike() {
        return riskCodeLike;
    }
    public void setRiskCodeLike(String riskCodeLike) {
        this.riskCodeLike = riskCodeLike;
    }
	
	
	
}
