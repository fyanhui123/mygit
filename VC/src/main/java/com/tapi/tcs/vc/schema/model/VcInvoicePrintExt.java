package com.tapi.tcs.vc.schema.model;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 发票开具扩展信息表
 * <p>
 * Date 2013-06-05
 * </p>
 * <p>
 * Module：发票核销
 * </p>
 * <p>
 * Description：
 * </p>
 * <p>
 * Remark：实体类，不允许修改
 * </p>
 * @author chy
 * @version 1.0
 */
@Entity
@Table(name = "VC_INVOICE_PRINT_EXT")
public class VcInvoicePrintExt implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**发票开具扩展信息流水*/
	private Long id;
	/**发票开具信息*/
	private VcInvoicePrint vcInvoicePrint;
	/**承保险种代码*/ 
	private String	riskCode      ;
	/**承保险种名称*/ 
	private String	riskName      ;
	/**保险费*/
	private BigDecimal	premium   ;
	/**投保人*/
	private String	applyName     ;
	/**投保地名称*/ 
	private String	areaName   ;
	/**投保地代码*/
	private String	areaCode   ;
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
	/**能源类别*/
	private String fuelCategory;
	/**创建人*/
	private String createdBy;
	/**创建时间*/
	private Date dateCreated;
	/**修改人*/
	private String updatedBy;
	/**修改时间*/
	private Date dateUpdated;
	/**开具项目代码*/
	private String itemCode;
	/**开具项目名称*/
	private String itemName;
	/**税款所属期年*/
	private String taxYear;
	/**税款所属期起始月*/
	private String taxMonthStart;
	/**税款所属期终止月*/
	private String taxMonthEnd;
	
	
	@SequenceGenerator(name = "generator", allocationSize = 1, sequenceName = "SEQ_VC_INVOICE_PRINT_EXT")
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "generator")
	@Column(name = "ID_VC_INVOICE_PRINT_EXT")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_VC_INVOICE_PRINT", referencedColumnName="ID_VC_INVOICE_PRINT", nullable = true)
	public VcInvoicePrint getVcInvoicePrint() {
		return vcInvoicePrint;
	}
	public void setVcInvoicePrint(VcInvoicePrint vcInvoicePrint) {
		this.vcInvoicePrint = vcInvoicePrint;
	}
	@Column(name="RISK_CODE")
	public String getRiskCode() {
		return riskCode;
	}
	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}
	@Column(name="RISK_NAME")
	public String getRiskName() {
		return riskName;
	}
	public void setRiskName(String riskName) {
		this.riskName = riskName;
	}
	@Column(name="PREMIUM")
	public BigDecimal getPremium() {
		return premium;
	}
	public void setPremium(BigDecimal premium) {
		this.premium = premium;
	}
	@Column(name="APPLY_NAME")
	public String getApplyName() {
		return applyName;
	}
	public void setApplyName(String applyName) {
		this.applyName = applyName;
	}
	@Column(name="AREA_NAME")
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	@Column(name="AREA_CODE")
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	@Column(name="CAR_AREA_NAME")
	public String getCarAreaName() {
		return carAreaName;
	}
	public void setCarAreaName(String carAreaName) {
		this.carAreaName = carAreaName;
	}
	@Column(name="CAR_AREA_CODE")
	public String getCarAreaCode() {
		return carAreaCode;
	}
	public void setCarAreaCode(String carAreaCode) {
		this.carAreaCode = carAreaCode;
	}
	@Column(name="LICENSE_NO")
	public String getLicenseNo() {
		return licenseNo;
	}
	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}
	@Column(name="CAR_KIND")
	public String getCarKind() {
		return carKind;
	}
	public void setCarKind(String carKind) {
		this.carKind = carKind;
	}
	@Column(name="UNIT")
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	@Column(name="QUANTITY")
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	@Column(name="LAST_END_DATE")
	public Date getLastEndDate() {
		return lastEndDate;
	}
	public void setLastEndDate(Date lastEndDate) {
		this.lastEndDate = lastEndDate;
	}
	@Column(name="USE_NATURE")
	public String getUseNature() {
		return useNature;
	}
	public void setUseNature(String useNature) {
		this.useNature = useNature;
	}
	@Column(name="ITEM")
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	@Column(name="AMOUNT")
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	@Column(name="ENGINE_NO")
	public String getEngineNo() {
		return engineNo;
	}
	public void setEngineNo(String engineNo) {
		this.engineNo = engineNo;
	}
	@Column(name="FRAME_NO")
	public String getFrameNo() {
		return frameNo;
	}
	public void setFrameNo(String frameNo) {
		this.frameNo = frameNo;
	}
	@Column(name="PAY_DATE_START")
	public Date getPayDateStart() {
		return payDateStart;
	}
	public void setPayDateStart(Date payDateStart) {
		this.payDateStart = payDateStart;
	}
	@Column(name="PAY_DATE_END")
	public Date getPayDateEnd() {
		return payDateEnd;
	}
	public void setPayDateEnd(Date payDateEnd) {
		this.payDateEnd = payDateEnd;
	}
	@Column(name="TAX_AMOUNT")
	public BigDecimal getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}
	@Column(name="LATE_FEE")
	public BigDecimal getLateFee() {
		return lateFee;
	}
	public void setLateFee(BigDecimal lateFee) {
		this.lateFee = lateFee;
	}
	@Column(name="CURRENCY")
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	@Column(name="EXCHANGE_RATE")
	public BigDecimal getExchangeRate() {
		return exchangeRate;
	}
	public void setExchangeRate(BigDecimal exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	@Column(name="QUOTEPRICE_DATE")
	public Date getQuotePriceDate() {
		return quotePriceDate;
	}
	public void setQuotePriceDate(Date quotePriceDate) {
		this.quotePriceDate = quotePriceDate;
	}
	@Column(name="FORCURR_AMOUNT")
	public BigDecimal getForCurrAmount() {
		return forCurrAmount;
	}
	public void setForCurrAmount(BigDecimal forCurrAmount) {
		this.forCurrAmount = forCurrAmount;
	}
	@Column(name="FUEL_CATEGORY")
	public String getFuelCategory() {
		return fuelCategory;
	}
	public void setFuelCategory(String fuelCategory) {
		this.fuelCategory = fuelCategory;
	}
	@Column(name="CREATED_BY")
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	@Column(name="DATE_CREATED")
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	@Column(name="UPDATED_BY")
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	@Column(name="DATE_UPDATED")
	public Date getDateUpdated() {
		return dateUpdated;
	}
	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}
	@Column(name="ITEM_CODE")
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	@Column(name="ITEM_NAME")
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	@Column(name="TAX_YEAR")
	public String getTaxYear() {
		return taxYear;
	}
	public void setTaxYear(String taxYear) {
		this.taxYear = taxYear;
	}
	@Column(name="TAX_MONTH_START")
	public String getTaxMonthStart() {
		return taxMonthStart;
	}
	public void setTaxMonthStart(String taxMonthStart) {
		this.taxMonthStart = taxMonthStart;
	}
	@Column(name="TAX_MONTH_END")
	public String getTaxMonthEnd() {
		return taxMonthEnd;
	}
	public void setTaxMonthEnd(String taxMonthEnd) {
		this.taxMonthEnd = taxMonthEnd;
	}
}
