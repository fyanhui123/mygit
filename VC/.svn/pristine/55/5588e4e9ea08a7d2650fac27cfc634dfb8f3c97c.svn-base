package com.tapi.tcs.vc.schema.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 机构定义表
 * <p>
 * Date 2013-06-18
 * </p>
 * <p>
 * Module：
 * </p>
 * <p>
 * Description：
 * </p>
 * <p>
 * Remark：实体类，不允许修改
 * </p>
 * @author ljin
 * @version 1.0
 */
@Entity
@Table(name = "PUB_USER_DEF")
public class PubUserDef implements Serializable{

	private static final long serialVersionUID = 1L;
	/***用户ID**/
	private  Integer   userId;
	/**用户代码**/
	private  String userCode;
	/**员工代码**/
	private  String  employeeId;
	/**密码**/
	private  String password;
	/**用户名称**/
	private  String userName;
	/**用户类型 1 超级管理员，2 系统管理员，3 普通用户***/
	private  String userType;
	/**机构ID**/
	private  String companyCode;
	/**AD的ID**/
	private  String userADId;
	/**密码过期日期**/
	private  Date pwdExpireDate;
	/**登录失败次数**/
	private  Integer logonFailedTimes;
	/**登录状态 0:初始状态 1：登录成功 2：登出成功  3：锁定**/
	private  String logonStatus;
	/**HR工号*/
	private String hrNo;
	/**身份证号*/
	private String cardNumber;
	/**入职时间*/
	private Date entryTime;
	/**手机号码**/
	private  String mobilePhoneNumber;
	/**IP地址**/
	private  String ipAddress;
	/**MAC地址**/
	private  String macAddress;
	/**创建人代码**/
	private  String createdBy;
	/**创建时间**/
	private  Date dateCreated;
	/**修改人代码**/
	private  String  updateBy;
	/**修改时间**/
	private  Date  dateUpdated;
	/**是否有效标志 Y:有效，N:无效**/
	private  String validFlag;
	/**用户代码(目前)**/
	private  Long  oldEmployeeId;
	
	@Id
	@Column(name = "USER_ID")
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
	@Column(name = "USER_CODE")
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	@Column(name = "EMPLOYEE_ID")
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	
	@Column(name = "PASSWORD")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name = "USER_NAME")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(name = "USER_TYPE")
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	@Column(name = "COMPANY_CODE")
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	
	@Column(name = "USER_AD_ID")
	public String getUserADId() {
		return userADId;
	}
	public void setUserADId(String userADId) {
		this.userADId = userADId;
	}
	
	@Column(name = "PWD_EXPIRE_DATE")
	public Date getPwdExpireDate() {
		return pwdExpireDate;
	}
	public void setPwdExpireDate(Date pwdExpireDate) {
		this.pwdExpireDate = pwdExpireDate;
	}
	
	@Column(name = "LOGON_FAILED_TIMES")
	public Integer getLogonFailedTimes() {
		return logonFailedTimes;
	}
	public void setLogonFailedTimes(Integer logonFailedTimes) {
		this.logonFailedTimes = logonFailedTimes;
	}
	
	@Column(name = "LOGON_STATUS")
	public String getLogonStatus() {
		return logonStatus;
	}
	public void setLogonStatus(String logonStatus) {
		this.logonStatus = logonStatus;
	}
	
	@Column(name = "IP_ADDRESS")
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	@Column(name = "MAC_ADDRESS")
	public String getMacAddress() {
		return macAddress;
	}
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}
	
	@Column(name = "MOBILE_PHONE_NUMBER")
	public String getMobilePhoneNumber() {
		return mobilePhoneNumber;
	}
	public void setMobilePhoneNumber(String mobilePhoneNumber) {
		this.mobilePhoneNumber = mobilePhoneNumber;
	}
	
	@Column(name = "DATE_CREATED")
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	@Column(name = "UPDATE_BY")
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	
	@Column(name = "DATE_UPDATED")
	public Date getDateUpdated() {
		return dateUpdated;
	}
	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}
	
	@Column(name = "OLD_EMPLOYEE_ID")
	public Long getOldEmployeeId() {
		return oldEmployeeId;
	}
	public void setOldEmployeeId(Long oldEmployeeId) {
		this.oldEmployeeId = oldEmployeeId;
	}  
	
	@Column(name = "CREATED_BY")
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	@Column(name = "VALID_FLAG")
	public String getValidFlag() {
		return validFlag;
	}
	public void setValidFlag(String validFlag) {
		this.validFlag = validFlag;
	}
	
	@Column(name = "HR_NO")
	public String getHrNo() {
		return hrNo;
	}
	public void setHrNo(String hrNo) {
		this.hrNo = hrNo;
	}
	
	@Column(name = "CARD_NUMBER")
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	@Column(name = "ENTRY_TIME")
	public Date getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}
}
