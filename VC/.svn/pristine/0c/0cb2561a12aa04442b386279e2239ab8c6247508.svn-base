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
 * 发票开具明细表
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
@Table(name = "VC_INVOICE_PRINT_DET")
public class VcInvoicePrintDet implements Serializable {

	private static final long serialVersionUID = 1L;
	/**发票开具明细流水*/
	private Long id;
	/**发票开具信息*/
	private VcInvoicePrint vcInvoicePrint;
	/**序号*/
	private Integer serialNo ;
	/**行业分类*/
	private String industry ;
	/**项目大类*/
	private String itemKind ;
	/**开具项目代码*/
	private String itemCode ;
	/**开具项目名称*/
	private String itemName ;
	/**项目说明*/
	private String itemRemark ;
	/**税率*/
	private BigDecimal taxRate;
	/**税额*/
	private BigDecimal taxAmount;
	/**开具内容*/
	private String content;
	/**单位名称*/
	private String unitName;
	/**单价*/
	private BigDecimal unitPrice;
	/**数量*/
	private Integer quantity;
	/**金额*/
	private BigDecimal amount;
	/**代收车船税*/
	private String vesselTax;
	/**税款所属期年*/
	private String taxYear;
	/**税款所属期起始月*/
	private String taxMonthStart;
	/**税款所属期终止月*/
	private String taxMonthEnd;
	/**滞纳金*/
	private BigDecimal LateFee;
	/**创建人*/
	private String createdBy;
	/**创建时间*/
	private Date dateCreated;
	/**修改人*/
	private String updatedBy;
	/**修改时间*/
	private Date dateUpdated;
	
	@SequenceGenerator(name = "generator", allocationSize = 1, sequenceName = "SEQ_VC_INVOICE_PRINT_DET")
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "generator")
	@Column(name = "ID_VC_INVOICE_PRINT_DET")
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
	@Column(name="SERIAL_NO")
	public Integer getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(Integer serialNo) {
		this.serialNo = serialNo;
	}
	@Column(name="INDUSTRY")
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	@Column(name="ITEM_KIND")
	public String getItemKind() {
		return itemKind;
	}
	public void setItemKind(String itemKind) {
		this.itemKind = itemKind;
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
	@Column(name="ITEM_REMARK")
	public String getItemRemark() {
		return itemRemark;
	}
	public void setItemRemark(String itemRemark) {
		this.itemRemark = itemRemark;
	}
	@Column(name="TAX_RATE")
	public BigDecimal getTaxRate() {
		return taxRate;
	}
	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}
	@Column(name="TAX_AMOUNT")
	public BigDecimal getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}
	@Column(name="CONTENT")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Column(name="UNIT_NAME")
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	@Column(name="UNIT_PRICE")
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	@Column(name="QUANTITY")
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	@Column(name="AMOUNT")
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	@Column(name="VESSEL_TAX")
	public String getVesselTax() {
		return vesselTax;
	}
	public void setVesselTax(String vesselTax) {
		this.vesselTax = vesselTax;
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
	@Column(name="LATE_FEE")
	public BigDecimal getLateFee() {
		return LateFee;
	}
	public void setLateFee(BigDecimal lateFee) {
		LateFee = lateFee;
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
	
}
