package com.tapi.tcs.vc.schema.model;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "VC_INVOICE_PURCHASE")
public class VcInvoicePurchase implements Serializable{

	private static final long serialVersionUID = 1L;
	/**纳税人领购信息流水*/
	private Long id;
	/**发票领购序号*/
	private String serialNo;
	/**发票代码*/
	private String invoiceCode;
	/**发票名称*/
	private String invoiceName;
	/**发票简码代码*/
	private String invoiceShortCode;
	/**起始号码*/
	private String startNum;
	/**终止号码*/
	private String endNum;
	/**份数*/
	private Integer copyNum;
	/**本数*/
	private Integer pileNum;
	/**每本份数*/
	private Integer unitNum;
	/**领购日期*/
	private Date purchaseDate;
	/**领购类型 04-纳税人领购,04-纳税人领购*/
	private String purchaseType;
	/**领购机构代码*/
	private String orgCode;
	/**纳税人电脑编码*/
	private String computerNo;
	/**所属地市*/
	private String belongCity;
	/**单证类型代码*/
	private String docVerCode;
	/**是否已入库*/
	private String inStoreFlag;
	/**入库操作员*/
	private String inStoreOpr;
	/**入库操作时间*/
	private String inStoreDate;
	/**状态*/
	private String status;
	/** 创建人 */
	private String createdBy;
	/** 创建时间 */
	private Date dateCreated;
	/** 修改人 */
	private String updatedBy;
	
    private String purchaseTypeName;
	@Transient
	public String getPurchaseTypeName() {
		return purchaseTypeName;
	}
	public void setPurchaseTypeName(String purchaseTypeName) {
		this.purchaseTypeName = purchaseTypeName;
	}
	private  String   inStoreFlagName;
	@Transient
	public String getInStoreFlagName() {
		return inStoreFlagName;
	}
	public void setInStoreFlagName(String inStoreFlagName) {
		this.inStoreFlagName = inStoreFlagName;
	}
	
	
	/** 修改时间 */
	private Date dateUpdated;
	
	@SequenceGenerator(name = "generator", allocationSize = 1, sequenceName = "SEQ_VC_INVOICE_PURCHASE")
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "generator")
	@Column(name = "ID_VC_INVOICE_PURCHASE")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "SERIAL_NO")
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	@Column(name = "INVOICE_CODE")
	public String getInvoiceCode() {
		return invoiceCode;
	}
	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}
	@Column(name = "INVOICE_NAME")
	public String getInvoiceName() {
		return invoiceName;
	}
	public void setInvoiceName(String invoiceName) {
		this.invoiceName = invoiceName;
	}
	@Column(name = "INVOICE_SHORT_CODE")
	public String getInvoiceShortCode() {
		return invoiceShortCode;
	}
	public void setInvoiceShortCode(String invoiceShortCode) {
		this.invoiceShortCode = invoiceShortCode;
	}
	@Column(name = "START_NUM")
	public String getStartNum() {
		return startNum;
	}
	public void setStartNum(String startNum) {
		this.startNum = startNum;
	}
	@Column(name = "END_NUM")
	public String getEndNum() {
		return endNum;
	}
	public void setEndNum(String endNum) {
		this.endNum = endNum;
	}
	@Column(name = "SUM_QUANTITY")
	public Integer getCopyNum() {
		return copyNum;
	}
	public void setCopyNum(Integer copyNum) {
		this.copyNum = copyNum;
	}
	@Column(name = "TOTAL_UNIT")
	public Integer getPileNum() {
		return pileNum;
	}
	public void setPileNum(Integer pileNum) {
		this.pileNum = pileNum;
	}
	@Column(name = "QUANTITY_PER_UNIT")
	public Integer getUnitNum() {
		return unitNum;
	}
	public void setUnitNum(Integer unitNum) {
		this.unitNum = unitNum;
	}
	@Column(name = "PURCHASE_DATE")
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	@Column(name = "PURCHASE_TYPE")
	public String getPurchaseType() {
		return purchaseType;
	}
	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}
	@Column(name = "ORG_CODE")
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	@Column(name = "COMPUTER_NO")
	public String getComputerNo() {
		return computerNo;
	}
	public void setComputerNo(String computerNo) {
		this.computerNo = computerNo;
	}
	@Column(name = "BELONG_CITY")
	public String getBelongCity() {
		return belongCity;
	}
	public void setBelongCity(String belongCity) {
		this.belongCity = belongCity;
	}
	@Column(name = "DOC_VER_CODE")
	public String getDocVerCode() {
		return docVerCode;
	}
	public void setDocVerCode(String docVerCode) {
		this.docVerCode = docVerCode;
	}
	@Column(name = "IN_STORE_FLAG")
	public String getInStoreFlag() {
		return inStoreFlag;
	}
	public void setInStoreFlag(String inStoreFlag) {
		this.inStoreFlag = inStoreFlag;
	}
	@Column(name = "IN_STORE_OPR")
	public String getInStoreOpr() {
		return inStoreOpr;
	}
	public void setInStoreOpr(String inStoreOpr) {
		this.inStoreOpr = inStoreOpr;
	}
	@Column(name = "IN_STORE_DATE")
	public String getInStoreDate() {
		return inStoreDate;
	}
	public void setInStoreDate(String inStoreDate) {
		this.inStoreDate = inStoreDate;
	}
	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
