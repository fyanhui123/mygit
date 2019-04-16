package com.tapi.tcs.vc.order.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 征订管理VO
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
 * Remark：
 * </p>
 * @author chy
 * @version 1.0
 */
public class OrderLaunchVo {
	
	/**订单发起流水*/
	private String orderId;
	/**订单号*/
	private String orderCode;
	/**订单类型*/
	private String orderType;
	/**申请机构代码*/
	private String orgId;
	/**申请机构名称*/
	private String orgName;
	/**状态*/
	private String flag;
	/**创建人*/
	private String createUserId;
	/**创建人姓名*/
	private String createUserName;
	/**修改人*/
	private String modifyUserId;
	/**创建时间*/
	private Date createDate;
	/**修改时间*/
	private Date modifyDate;
	/**申请起始时间*/
	private Date applyStartDate;
	/**申请终止时间*/
	private Date applyEndDate;
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	public String getModifyUserId() {
		return modifyUserId;
	}
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public Date getApplyEndDate() {
		return applyEndDate;
	}
	public void setApplyEndDate(Date applyEndDate) {
		this.applyEndDate = applyEndDate;
	}
	public Date getApplyStartDate() {
		return applyStartDate;
	}
	public void setApplyStartDate(Date applyStartDate) {
		this.applyStartDate = applyStartDate;
	}
}
