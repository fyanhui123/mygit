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

@Entity
@Table(name = "VC_INVOICE_REVOKE")
public class VcInvoiceRevoke implements Serializable{

	private static final long serialVersionUID = 3119161143653289759L;
	/**发票缴销信息流水*/
	private Long id;
	/**单证类型代码*/
	private String docVerCode;
	/**发票代码*/
	private String invoiceCode;
	/**起始号码*/
	private String startNum;
	/**终止号码*/
	private String endNum;
	/**发票份数*/
	private Integer quantity;
	/**缴销类型代码*/
	private String revokeType;
	/**缴销情形代码*/
	private String revokeCode;
	/**纳税人电脑编码*/
	private String computerNo;
	/**缴销人代码*/
	private String revokeOprCode;
	/**缴销人名称*/
	private String revokeOprName;
	/**是否上传平台*/
	private String isUploadPlat;
	/**状态*/
	private String status;
	/**创建人*/
	private String createdBy;
	/**创建时间*/
	private Date dateCreated;
	/**修改人*/
	private String updatedBy;
	/**修改时间*/
	private Date dateUpdated;
	/**机构代码*/
	private String orgCode;
	/**遗失日期*/
	private Date lostDate;
	/**登记日期*/
	private Date registerDate;
	
	@SequenceGenerator(name = "generator", allocationSize = 1, sequenceName = "SEQ_VC_INVOICE_REVOKE")
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "generator")
	@Column(name = "ID_VC_INVOICE_REVOKE")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "DOC_VER_CODE")
	public String getDocVerCode() {
		return docVerCode;
	}
	public void setDocVerCode(String docVerCode) {
		this.docVerCode = docVerCode;
	}
	@Column(name = "INVOICE_CODE")
	public String getInvoiceCode() {
		return invoiceCode;
	}
	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
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
	@Column(name = "QUANTITY")
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	@Column(name = "REVOKE_TYPE")
	public String getRevokeType() {
		return revokeType;
	}
	public void setRevokeType(String revokeType) {
		this.revokeType = revokeType;
	}
	@Column(name = "REVOKE_CODE")
	public String getRevokeCode() {
		return revokeCode;
	}
	public void setRevokeCode(String revokeCode) {
		this.revokeCode = revokeCode;
	}
	@Column(name = "COMPUTER_NO")
	public String getComputerNo() {
		return computerNo;
	}
	public void setComputerNo(String computerNo) {
		this.computerNo = computerNo;
	}
	@Column(name = "REVOKE_OPR_CODE")
	public String getRevokeOprCode() {
		return revokeOprCode;
	}
	public void setRevokeOprCode(String revokeOprCode) {
		this.revokeOprCode = revokeOprCode;
	}
	@Column(name = "REVOKE_OPR_NAME")
	public String getRevokeOprName() {
		return revokeOprName;
	}
	public void setRevokeOprName(String revokeOprName) {
		this.revokeOprName = revokeOprName;
	}
	@Column(name = "IS_UPLOAD_PLAT")
	public String getIsUploadPlat() {
		return isUploadPlat;
	}
	public void setIsUploadPlat(String isUploadPlat) {
		this.isUploadPlat = isUploadPlat;
	}
	@Column(name="STATUS")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	@Column(name="ORG_CODE")
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	@Column(name="LOST_DATE")
	public Date getLostDate() {
		return lostDate;
	}
	public void setLostDate(Date lostDate) {
		this.lostDate = lostDate;
	}
	@Column(name="REGISTER_DATE")
	public Date getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
}
