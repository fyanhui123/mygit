/**
 * 
 */
package com.tapi.tcs.vc.visausage.vo;

import java.io.Serializable;

/**
 * 定义查询条件
 * @author hanmiao.diao
 *
 */
public class QueryCondition implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4274957967849161706L;

	private String cancelCode;
	private String startDate;
	private String endDate;
	private String cancelStatus;
	private String cancelType;
	public String getCancelCode() {
		return cancelCode;
	}
	public void setCancelCode(String cancelCode) {
		this.cancelCode = cancelCode;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getCancelStatus() {
		return cancelStatus;
	}
	public void setCancelStatus(String cancelStatus) {
		this.cancelStatus = cancelStatus;
	}
	public String getCancelType() {
		return cancelType;
	}
	public void setCancelType(String cancelType) {
		this.cancelType = cancelType;
	}
	
}
