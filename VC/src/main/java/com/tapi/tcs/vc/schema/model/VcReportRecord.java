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
@Table(name = "VC_REPORT_RECORD")
public class VcReportRecord implements Serializable{

	private static final long serialVersionUID = -303775051797226737L;
	/**平台日志记录流水号*/
	private Long id;

	private String docNum;
	
	private String docVerCode;
	
	private String reportTag;

	private String reportType;
	
	private String reportResult;
	
	private String requestXml;

	private Date requestTime;

	private String responseXml;
	
	private Date responseTime;
	
	private String errorCode;

	private String errorDesc;

	private String createdBy;

	private String updatedBy;
	
	private String endNum;
	@SequenceGenerator(name = "generator", allocationSize = 1, sequenceName = "SEQ_VC_REPORT_RECORD")
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "generator")
	@Column(name = "ID_VC_REPORT_RECORD")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "REPORT_TAG")
	public String getReportTag() {
		return reportTag;
	}

	public void setReportTag(String reportTag) {
		this.reportTag = reportTag;
	}

	@Column(name = "REPORT_TYPE")
	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	@Column(name = "REPORT_RESULT")
	public String getReportResult() {
		return reportResult;
	}

	public void setReportResult(String reportResult) {
		this.reportResult = reportResult;
	}

	@Column(name = "REQUEST_XML")
	public String getRequestXml() {
		return requestXml;
	}

	public void setRequestXml(String requestXml) {
		this.requestXml = requestXml;
	}

	@Column(name = "REQUEST_TIME")
	public Date getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}

	@Column(name = "RESPONSE_XML")
	public String getResponseXml() {
		return responseXml;
	}

	public void setResponseXml(String responseXml) {
		this.responseXml = responseXml;
	}

	@Column(name = "RESPONSE_TIME")
	public Date getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(Date responseTime) {
		this.responseTime = responseTime;
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

	@Column(name = "CREATED_BY")
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "UPDATED_BY")
	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	
	public void setDocNum(String docNum) {
		this.docNum = docNum;
	}

	@Column(name = "DOC_NUM")
	public String getDocNum() {
		return docNum;
	}

	public void setDocVerCode(String docVerCode) {
		this.docVerCode = docVerCode;
	}

	@Column(name = "DOC_VER_CODE")
	public String getDocVerCode() {
		return docVerCode;
	}
	@Column(name = "END_NUM")
	public String getEndNum() {
		return endNum;
	}

	public void setEndNum(String endNum) {
		this.endNum = endNum;
	}
	
	
	
}