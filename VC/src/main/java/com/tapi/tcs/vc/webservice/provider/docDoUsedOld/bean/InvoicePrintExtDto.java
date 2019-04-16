package com.tapi.tcs.vc.webservice.provider.docDoUsedOld.bean;

import java.math.BigDecimal;
import java.util.Date;

public class InvoicePrintExtDto {

	/**承保险种代码*/ 
	private String	riskCode      ;
	/**承保险种名称*/ 
	private String	riskName      ;
	/**保险费*/
	private BigDecimal	premium   ;
	/**投保人*/
	private String	applyName     ;
	/**车籍地名称*/ 
	private String	carAreaName   ;
	/**车籍地代码*/
	private String	carAreaCode   ;
	/**车牌号码*/
	private String	licenseNo     ;
	/**车辆类别*/
	private String	carKind       ;
	/**单位*/
	private String	unit          ;
	/**数量*/
	private String	quantity      ;
	/**上期缴纳交强险截止日期*/
	private Date	lastEndDate   ;
	/**使用性质*/
	private String	useNature     ;
	/**项目*/
	private String	item          ;
	/**保险金额*/
	private String	amount        ;
	/**发动机号*/
	private String	engineNo      ;
	/**车架号*/
	private String	frameNo       ;
	/**所缴日期起*/
	private Date	payDateStart  ;
	/**所缴日期止*/
	private Date	payDateEnd    ;
	/**代收车船税*/
	private BigDecimal	taxAmount     ;
	/**滞纳金*/
	private BigDecimal	lateFee       ;
	/**币种*/
	private String	currency      ;
	/**汇率*/
	private BigDecimal	exchangeRate  ;
	/**牌价日*/
	private Date	quotePriceDate;
	/**外币金额*/
	private BigDecimal	forCurrAmount ;
	/**投保地代码*/
	private String areaCode;
	/**投保地名称*/
	private String areaName;
	/**能源类别*/
	private String fuelCategory;
	
	public String getRiskCode() {
		return riskCode;
	}
	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}
	public String getRiskName() {
		return riskName;
	}
	public void setRiskName(String riskName) {
		this.riskName = riskName;
	}
	public BigDecimal getPremium() {
		return premium;
	}
	public void setPremium(BigDecimal premium) {
		this.premium = premium;
	}
	public String getApplyName() {
		return applyName;
	}
	public void setApplyName(String applyName) {
		this.applyName = applyName;
	}
	public String getCarAreaName() {
		return carAreaName;
	}
	public void setCarAreaName(String carAreaName) {
		this.carAreaName = carAreaName;
	}
	public String getCarAreaCode() {
		return carAreaCode;
	}
	public void setCarAreaCode(String carAreaCode) {
		this.carAreaCode = carAreaCode;
	}
	public String getLicenseNo() {
		return licenseNo;
	}
	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}
	public String getCarKind() {
		return carKind;
	}
	public void setCarKind(String carKind) {
		this.carKind = carKind;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public Date getLastEndDate() {
		return lastEndDate;
	}
	public void setLastEndDate(Date lastEndDate) {
		this.lastEndDate = lastEndDate;
	}
	public String getUseNature() {
		return useNature;
	}
	public void setUseNature(String useNature) {
		this.useNature = useNature;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getEngineNo() {
		return engineNo;
	}
	public void setEngineNo(String engineNo) {
		this.engineNo = engineNo;
	}
	public String getFrameNo() {
		return frameNo;
	}
	public void setFrameNo(String frameNo) {
		this.frameNo = frameNo;
	}
	public Date getPayDateStart() {
		return payDateStart;
	}
	public void setPayDateStart(Date payDateStart) {
		this.payDateStart = payDateStart;
	}
	public Date getPayDateEnd() {
		return payDateEnd;
	}
	public void setPayDateEnd(Date payDateEnd) {
		this.payDateEnd = payDateEnd;
	}
	public BigDecimal getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}
	public BigDecimal getLateFee() {
		return lateFee;
	}
	public void setLateFee(BigDecimal lateFee) {
		this.lateFee = lateFee;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public BigDecimal getExchangeRate() {
		return exchangeRate;
	}
	public void setExchangeRate(BigDecimal exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	public Date getQuotePriceDate() {
		return quotePriceDate;
	}
	public void setQuotePriceDate(Date quotePriceDate) {
		this.quotePriceDate = quotePriceDate;
	}
	public BigDecimal getForCurrAmount() {
		return forCurrAmount;
	}
	public void setForCurrAmount(BigDecimal forCurrAmount) {
		this.forCurrAmount = forCurrAmount;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getFuelCategory() {
		return fuelCategory;
	}
	public void setFuelCategory(String fuelCategory) {
		this.fuelCategory = fuelCategory;
	}
	public String getQuantity() {
		return quantity;
	}
	
}
