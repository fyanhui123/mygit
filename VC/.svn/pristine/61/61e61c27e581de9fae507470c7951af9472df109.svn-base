package com.tapi.tcs.vc.schema.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "VC_INVOICE_BUY_HLJ")
@SequenceGenerator(name = "SEQ_VC_INVOICE_BUY_HLJ", sequenceName = "SEQ_VC_INVOICE_BUY_HLJ", allocationSize = 1)
public class VcInvoiceBuyHlj  implements Serializable{

	private static final long serialVersionUID = 2614707910109570385L;
	/**领购表流水*/
	private Long id;
	/**单证类型代码*/
	private String docVerCode;
	/**领购日期*/
	private Date buyDate;
	/**发票代码*/
	private String invoiceCode;
	/**起始号码*/
	private String startNum;
	/**终止号码*/
	private String endNum;
	/**数量*/
	private Long amount;
	/**单张发票限额*/
	private BigDecimal moneyLimit;
	/**所属领域*/
	private String belongField;
	/**电子编码*/
	private String electronicCode;
	/**机构代码*/
	private String orgCode;
	/**操作员代码*/
	private String operatorCode;
	/** 创建人 */
	private String createdBy;
	/** 创建时间 */
	private Date dateCreated;
	/** 修改人 */
	private String updatedBy;
	/** 修改时间 */
	private Date dateUpdated;
	
	//自定义变量
	/**序号*/
	private Integer orderNo;
	/**库存状态*/
	private String storeStatus;
	/**当前正在使用*/
	private String nowUse;
	/**剩余数量*/
	private Long leftAmount;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VC_INVOICE_BUY_HLJ")
	@Column(name="ID_VC_INVOICE_BUY_HLJ")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name="DOC_VER_CODE")
	public String getDocVerCode() {
		return docVerCode;
	}
	public void setDocVerCode(String docVerCode) {
		this.docVerCode = docVerCode;
	}
	@Column(name="BUY_DATE")
	public Date getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}
	@Column(name="INVOICE_CODE")
	public String getInvoiceCode() {
		return invoiceCode;
	}
	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}
	@Column(name="START_NUM")
	public String getStartNum() {
		return startNum;
	}
	public void setStartNum(String startNum) {
		this.startNum = startNum;
	}
	@Column(name="END_NUM")
	public String getEndNum() {
		return endNum;
	}
	public void setEndNum(String endNum) {
		this.endNum = endNum;
	}
	@Column(name="AMOUNT")
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	@Column(name="MONEY_LIMIT")
	public BigDecimal getMoneyLimit() {
		return moneyLimit;
	}
	public void setMoneyLimit(BigDecimal moneyLimit) {
		this.moneyLimit = moneyLimit;
	}
	@Column(name="BELONG_FIELD")
	public String getBelongField() {
		return belongField;
	}
	public void setBelongField(String belongField) {
		this.belongField = belongField;
	}
	@Column(name="ELECTRONIC_CODE")
	public String getElectronicCode() {
		return electronicCode;
	}
	public void setElectronicCode(String electronicCode) {
		this.electronicCode = electronicCode;
	}
	@Column(name="ORG_CODE")
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	@Column(name="OPERATOR_CODE")
	public String getOperatorCode() {
		return operatorCode;
	}
	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
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
	@Transient
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	@Transient
	public String getStoreStatus() {
		return storeStatus;
	}
	public void setStoreStatus(String storeStatus) {
		this.storeStatus = storeStatus;
	}
	@Transient
	public String getNowUse() {
		return nowUse;
	}
	public void setNowUse(String nowUse) {
		this.nowUse = nowUse;
	}
	@Transient
	public Long getLeftAmount() {
		return leftAmount;
	}
	public void setLeftAmount(Long leftAmount) {
		this.leftAmount = leftAmount;
	}
}
