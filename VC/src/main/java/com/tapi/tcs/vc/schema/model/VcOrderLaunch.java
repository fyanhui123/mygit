package com.tapi.tcs.vc.schema.model;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 订单基本表
 * <p>
 * Date 2013-03-15
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
@Table(name = "VC_ORDER_LAUNCH")
public class VcOrderLaunch implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/**订单发起流水*/
	private Long id;
	/**订单号*/
	private String orderCode;
	/**订单类型*/
	private String orderType;
	/**申请机构代码*/
	private String orgCode;
	/**状态*/
	private String flag;
	/**有效性*/
	private String validStatus;
	/**审批机构代码*/
	private String checkOrgCode;
	/**汇总标志：0-未汇总;1-已汇总*/
	private String gatherFlag;
	/**修改人*/
	private String modifyUserCode;
	/**修改时间*/
	private Date modifyDate;
	/**创建人*/
	private String createdBy;
	/**创建时间*/
	private Date dateCreated;
	/**修改人*/
	private String updatedBy;
	/**修改时间*/
	private Date dateUpdated;
	
	/**订单详情*/
	private List<VcOrderLaunchDet> vcOrderLaunchDetList = new ArrayList<VcOrderLaunchDet>();
	
	/**订单明细*/
	private List<VcOrderLaunchSub> vcOrderLaunchSubList = new ArrayList<VcOrderLaunchSub>();
	
	@SequenceGenerator(name = "generator", allocationSize = 1, sequenceName = "SEQ_VC_ORDER_LAUNCH")
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "generator")
	@Column(name = "ID_VC_ORDER_LAUNCH")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "ORDER_CODE")
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	@Column(name = "ORDER_TYPE")
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	@Column(name = "ORG_CODE")
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	@Column(name = "ORDER_STATUS")
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
	@Column(name = "CHECK_ORG_CODE")
	public String getCheckOrgCode() {
		return checkOrgCode;
	}
	public void setCheckOrgCode(String checkOrgCode) {
		this.checkOrgCode = checkOrgCode;
	}
	@Column(name = "GATHER_FLAG")
	public String getGatherFlag() {
		return gatherFlag;
	}
	public void setGatherFlag(String gatherFlag) {
		this.gatherFlag = gatherFlag;
	}
	@Column(name = "MODIFY_USER_CODE")
	public String getModifyUserCode() {
		return modifyUserCode;
	}
	public void setModifyUserCode(String modifyUserCode) {
		this.modifyUserCode = modifyUserCode;
	}
	@Column(name = "MODIFY_TIME")
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
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
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "vcOrderLaunch")
	public List<VcOrderLaunchDet> getVcOrderLaunchDetList() {
		return vcOrderLaunchDetList;
	}
	public void setVcOrderLaunchDetList(List<VcOrderLaunchDet> vcOrderLaunchDetList) {
		this.vcOrderLaunchDetList = vcOrderLaunchDetList;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "vcOrderLaunch")
	public List<VcOrderLaunchSub> getVcOrderLaunchSubList() {
		return vcOrderLaunchSubList;
	}
	public void setVcOrderLaunchSubList(List<VcOrderLaunchSub> vcOrderLaunchSubList) {
		this.vcOrderLaunchSubList = vcOrderLaunchSubList;
	}
}
