package com.tapi.tcs.vc.schema.model;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 采购单表
 * <p>
 * Date 2013-04-01
 * </p>
 * <p>
 * Module：征订管理
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
@Table(name = "VC_PURCHASE")
public class VcPurchase implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/**采购流水号*/
	private Long id;
	/**采购单号*/
	private String purchaseCode;
	/**申请机构代码*/
	private String orgCode;
	/**单证版本号*/
	private String versionCode;
	/**申印数量*/
	private Integer applyPrintNum;
	/**起始流水号*/
	private String startSerialNo;
	/**终止流水号*/
	private String endSerialNo;
	/**收货起始流水号*/
	private String receivedStartNum;
	/**收货结束流水号*/
	private String receivedEndNum;
	/**收货数量*/
	private Integer receivedQuantity;
	/**承印厂商ID*/
	private String printeryId;
	/**单价*/
	private BigDecimal unitPrice;
	/**总金额*/
	private BigDecimal totalAmount;
	/**状态*/
	private String flag;
	/**有效性*/
	private String validStatus;
	/**下单时间*/
	private Date orderTime;
	/**收货人*/
	private String receivedOpr;
	/**收货时间*/
	private Date receivedTime;
	/**取消收货人*/
	private String cancelReceivedOrp;
	/**取消收货时间*/
	private Date cancelReceivedTime;
	/**创建人*/
	private String createdBy;
	/**创建时间*/
	private Date dateCreated;
	/**修改人*/
	private String updatedBy;
	/**修改时间*/
	private Date dateUpdated;
	
	
	@SequenceGenerator(name = "generator", allocationSize = 1, sequenceName = "SEQ_VC_PURCHASE")
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "generator")
	@Column(name = "ID_VC_PURCHASE")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "PURCHASE_CODE")
	public String getPurchaseCode() {
		return purchaseCode;
	}
	public void setPurchaseCode(String purchaseCode) {
		this.purchaseCode = purchaseCode;
	}
	
	@Column(name = "ORG_CODE")
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	
	@Column(name = "DOC_VER_CODE")
	public String getVersionCode() {
		return versionCode;
	}
	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}
	
	@Column(name = "APPLY_PRINT_NUM")
	public Integer getApplyPrintNum() {
		return applyPrintNum;
	}
	public void setApplyPrintNum(Integer applyPrintNum) {
		this.applyPrintNum = applyPrintNum;
	}
	
	@Column(name = "START_NUM")
	public String getStartSerialNo() {
		return startSerialNo;
	}
	public void setStartSerialNo(String startSerialNo) {
		this.startSerialNo = startSerialNo;
	}
	
	@Column(name = "END_NUM")
	public String getEndSerialNo() {
		return endSerialNo;
	}
	public void setEndSerialNo(String endSerialNo) {
		this.endSerialNo = endSerialNo;
	}
	
	@Column(name = "RECEIVED_START_NUM")
	public String getReceivedStartNum() {
		return receivedStartNum;
	}
	public void setReceivedStartNum(String receivedStartNum) {
		this.receivedStartNum = receivedStartNum;
	}
	
	@Column(name = "RECEIVED_END_NUM")
	public String getReceivedEndNum() {
		return receivedEndNum;
	}
	public void setReceivedEndNum(String receivedEndNum) {
		this.receivedEndNum = receivedEndNum;
	}
	
	@Column(name = "RECEIVED_QUANTITY")
	public Integer getReceivedQuantity() {
		return receivedQuantity;
	}
	public void setReceivedQuantity(Integer receivedQuantity) {
		this.receivedQuantity = receivedQuantity;
	}
	
	@Column(name = "ID_VC_PRINTERY")
	public String getPrinteryId() {
		return printeryId;
	}
	public void setPrinteryId(String printeryId) {
		this.printeryId = printeryId;
	}
	
	@Column(name = "UNIT_PRICE")
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	@Column(name = "TOTAL_AMOUNT")
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	@Column(name = "PURCHASE_STATUS")
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	@Column(name = "STATUS")
	public String getValidStatus() {
		return validStatus;
	}
	public void setValidStatus(String validStatus) {
		this.validStatus = validStatus;
	}
	
	@Column(name = "ORDER_TIME")
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	
	@Column(name = "RECEIVED_OPR")
	public String getReceivedOpr() {
		return receivedOpr;
	}
	public void setReceivedOpr(String receivedOpr) {
		this.receivedOpr = receivedOpr;
	}
	
	@Column(name = "RECEIVED_TIME")
	public Date getReceivedTime() {
		return receivedTime;
	}
	public void setReceivedTime(Date receivedTime) {
		this.receivedTime = receivedTime;
	}
	
	@Column(name = "CANCEL_RECEIVED_OPR")
	public String getCancelReceivedOrp() {
		return cancelReceivedOrp;
	}
	public void setCancelReceivedOrp(String cancelReceivedOrp) {
		this.cancelReceivedOrp = cancelReceivedOrp;
	}
	
	@Column(name = "CANCEL_RECEIVED_TIME")
	public Date getCancelReceivedTime() {
		return cancelReceivedTime;
	}
	public void setCancelReceivedTime(Date cancelReceivedTime) {
		this.cancelReceivedTime = cancelReceivedTime;
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
}
