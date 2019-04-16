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

/**
 * 中介机构表
 * <p>
 * Date 2013-06-19
 * </p>
 * <p>
 * Module：机构设置
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
@Table(name = "VC_AGENCYORG")
public class VcAgencyOrg implements Serializable{

	private static final long serialVersionUID = 1L;
	/**中介机构流水*/
	private Long id;
	/**中介机构代码*/
	private String agencyOrgCode;
	/**中介机构名称*/
	private String agencyOrgName;
	/**归属机构代码*/
	private String orgCode;
	/**销售渠道大类代码*/
	private String businessCode;
	/**销售渠道小类代码*/
	private String businessDetailCode;
	/**是否销售网点*/
	private String isSalesNet;
	/**显示序号*/
	private Integer displayNo;
	/**有效标志*/
	private String status;
	/**创建人*/
	private String createdBy;
	/**创建时间*/
	private Date dateCreated;
	/**修改人*/
	private String updatedBy;
	/**修改时间*/
	private Date dateUpdated;
	
	@SequenceGenerator(name = "generator", allocationSize = 1, sequenceName = "SEQ_VC_AGENCYORG")
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "generator")
	@Column(name = "ID_VC_AGENCYORG")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "AGENCY_ORG_CODE")
	public String getAgencyOrgCode() {
		return agencyOrgCode;
	}
	public void setAgencyOrgCode(String agencyOrgCode) {
		this.agencyOrgCode = agencyOrgCode;
	}
	@Column(name = "AGENCY_ORG_NAME")
	public String getAgencyOrgName() {
		return agencyOrgName;
	}
	public void setAgencyOrgName(String agencyOrgName) {
		this.agencyOrgName = agencyOrgName;
	}
	@Column(name = "ORG_CODE")
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	@Column(name = "BUSINESS_CODE")
	public String getBusinessCode() {
		return businessCode;
	}
	public void setBusinessCode(String businessCode) {
		this.businessCode = businessCode;
	}
	@Column(name = "BUSINESS_DETAIL_CODE")
	public String getBusinessDetailCode() {
		return businessDetailCode;
	}
	public void setBusinessDetailCode(String businessDetailCode) {
		this.businessDetailCode = businessDetailCode;
	}
	@Column(name = "IS_SALES_NET")
	public String getIsSalesNet() {
		return isSalesNet;
	}
	public void setIsSalesNet(String isSalesNet) {
		this.isSalesNet = isSalesNet;
	}
	@Column(name = "DISPLAY_NO")
	public Integer getDisplayNo() {
		return displayNo;
	}
	public void setDisplayNo(Integer displayNo) {
		this.displayNo = displayNo;
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
