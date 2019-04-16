package com.tapi.tcs.vc.schema.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "VC_INVOICE_REPORT_HLJ")
@SequenceGenerator(name = "SEQ_VC_INVOICE_REPORT_HLJ", sequenceName = "SEQ_VC_INVOICE_REPORT_HLJ", allocationSize = 1)
public class VcInvoiceReportHlj implements Serializable{

	private static final long serialVersionUID = -1372686524321140385L;
	/**领用存表流水*/
	private Long id;
	/**管理编码*/
	private String manageCode;
	/**起始日期*/
	private Date startDate;
	/**结束日期*/
	private Date endDate;
	/**报表生成日期*/
	private Date reportDate;
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
	/**报表明细*/
	private List<VcInvoiceReportDetHlj> vcInvoiceReportDetHljList = new ArrayList<VcInvoiceReportDetHlj>();
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VC_INVOICE_REPORT_HLJ")
	@Column(name="ID_VC_INVOICE_REPORT_HLJ")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name="MANAGE_CODE")
	public String getManageCode() {
		return manageCode;
	}
	public void setManageCode(String manageCode) {
		this.manageCode = manageCode;
	}
	@Column(name="START_DATE")
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	@Column(name="END_DATE")
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	@Column(name="REPORT_DATE")
	public Date getReportDate() {
		return reportDate;
	}
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
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
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "vcInvoiceReportHlj")
	public List<VcInvoiceReportDetHlj> getVcInvoiceReportDetHljList() {
		return vcInvoiceReportDetHljList;
	}
	public void setVcInvoiceReportDetHljList(
			List<VcInvoiceReportDetHlj> vcInvoiceReportDetHljList) {
		this.vcInvoiceReportDetHljList = vcInvoiceReportDetHljList;
	}
}
