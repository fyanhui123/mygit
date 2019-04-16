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
@Table(name = "VC_INVOICE_PLAT_LOG")
public class VcInvoicePlatLog implements Serializable{

	private static final long serialVersionUID = -303775051797226737L;
	/**平台日志记录流水号*/
	private Long id;
	/**报文类型 1领购 2开票*/
	private String reportType;
	/**请求UUID*/
	private String requestUuid;
	/**请求时间*/
	private Date requestTime;
	/**请求报文*/
	private String requestXml;
	/**响应UUID*/
	private String responseUuid;
	/**响应时间*/
	private Date responseTime;
	/**响应报文*/
	private String responseXml;
	/**响应错误代码*/
	private String errorCode;
	/**响应错误描述*/
	private String errorDesc;
	/**创建人*/
	private String createdBy;
	/**创建时间*/
	private Date dateCreated;
	/**修改人*/
	private String updatedBy;
	/**修改时间*/
	private Date dateUpdated;
	
	@SequenceGenerator(name = "generator", allocationSize = 1, sequenceName = "SEQ_VC_INVOICE_PLAT_LOG")
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "generator")
	@Column(name = "ID_VC_INVOICE_PLAT_LOG")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "REPORT_TYPE")
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	@Column(name = "REQUEST_UUID")
	public String getRequestUuid() {
		return requestUuid;
	}
	public void setRequestUuid(String requestUuid) {
		this.requestUuid = requestUuid;
	}
	@Column(name = "REQUEST_TIME")
	public Date getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}
	@Column(name = "REQUEST_XML")
	public String getRequestXml() {
		return requestXml;
	}
	public void setRequestXml(String requestXml) {
		this.requestXml = requestXml;
	}
	@Column(name = "RESPONSE_UUID")
	public String getResponseUuid() {
		return responseUuid;
	}
	public void setResponseUuid(String responseUuid) {
		this.responseUuid = responseUuid;
	}
	@Column(name = "RESPONSE_TIME")
	public Date getResponseTime() {
		return responseTime;
	}
	public void setResponseTime(Date responseTime) {
		this.responseTime = responseTime;
	}
	@Column(name = "RESPONSE_XML")
	public String getResponseXml() {
		return responseXml;
	}
	public void setResponseXml(String responseXml) {
		this.responseXml = responseXml;
	}
	@Column(name = "ERROR_CODE")
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	@Column(name = "ERROR_DESC")
	public String getErrorDesc() {
		return errorDesc;
	}
	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
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
