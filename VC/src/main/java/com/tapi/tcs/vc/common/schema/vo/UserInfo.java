package com.tapi.tcs.vc.common.schema.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 用于存放用户登录信息
 * @author chy
 *
 */
public class UserInfo {
	/**用户代码*/
	private String userCode;
	/**用户名称*/
	private String userName;
	/**所属机构代码*/
	private String companyCode;
	/**所属机构代码*/
	private String companyName;
	/**上级机构代码*/
	private String comCode;
	/**机构名称*/
	private String comName;
	/** 系统当前时间(yyyy-mm-dd) */
	private String currentDate;
	
	/** 系统地址 */
	private String sysAddr;

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getComCode() {
		return comCode;
	}

	public void setComCode(String comCode) {
		this.comCode = comCode;
	}

	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

	public String getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}

	public String getSysAddr() {
		return sysAddr;
	}

	public void setSysAddr(String sysAddr) {
		this.sysAddr = sysAddr;
	}

	@Override
	public String toString() {
		return "UserInfo [userCode=" + userCode + ", userName=" + userName + ", comCode=" + comCode + ", comName="
				+ comName + ", currentDate=" + currentDate + ", sysAddr=" + sysAddr + "]";
	}
	
	
}
