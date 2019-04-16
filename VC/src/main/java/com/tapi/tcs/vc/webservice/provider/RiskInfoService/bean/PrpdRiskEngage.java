package com.tapi.tcs.vc.webservice.provider.RiskInfoService.bean;

import javax.xml.bind.annotation.XmlElement;

/**
 * 险种特约信息
 */
public class PrpdRiskEngage {
	/**
	 * 特别约定代码
	 */
	private String engageCode;
	/**
	 * 特别约定中文简称
	 */
	private String engageName;
	/**
	 * 特别约定中文全称
	 */
	private String engageFullName;
	/**
	 * 特别约定英文简称
	 */
	private String engageEName;
	/**
	 * 特别约定英文全称
	 */
	private String engageFullEName;
	/**
	 * 特别约定繁体简称
	 */
	private String engageTName;
	/**
	 * 特别约定繁体全称
	 */
	private String engageFullTName;
	/**
	 * 归属险类代码
	 */
	private String classCode;
	/**
	 * 归属险类名称
	 */
	private String className;
	/**
	 * 特别约定描述
	 */
	private String engageDesc;
	/**
	 * 状态
	 */
	private String state;
	/**
	 * 备注
	 */
	private String remark;
	public String getEngageCode() {
		return engageCode;
	}
	@XmlElement(name = "engageCode", required = false)
	public void setEngageCode(String engageCode) {
		this.engageCode = engageCode;
	}
	public String getEngageName() {
		return engageName;
	}
	@XmlElement(name = "engageName", required = false)
	public void setEngageName(String engageName) {
		this.engageName = engageName;
	}
	public String getEngageFullName() {
		return engageFullName;
	}
	@XmlElement(name = "engageFullName", required = false)
	public void setEngageFullName(String engageFullName) {
		this.engageFullName = engageFullName;
	}
	public String getEngageEName() {
		return engageEName;
	}
	@XmlElement(name = "engageEName", required = false)
	public void setEngageEName(String engageEName) {
		this.engageEName = engageEName;
	}
	public String getEngageFullEName() {
		return engageFullEName;
	}
	@XmlElement(name = "engageFullEName", required = false)
	public void setEngageFullEName(String engageFullEName) {
		this.engageFullEName = engageFullEName;
	}
	public String getEngageTName() {
		return engageTName;
	}
	@XmlElement(name = "engageTName", required = false)
	public void setEngageTName(String engageTName) {
		this.engageTName = engageTName;
	}
	public String getEngageFullTName() {
		return engageFullTName;
	}
	@XmlElement(name = "engageFullTName", required = false)
	public void setEngageFullTName(String engageFullTName) {
		this.engageFullTName = engageFullTName;
	}
	public String getClassCode() {
		return classCode;
	}
	@XmlElement(name = "classCode", required = false)
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	public String getClassName() {
		return className;
	}
	@XmlElement(name = "className", required = false)
	public void setClassName(String className) {
		this.className = className;
	}
	public String getEngageDesc() {
		return engageDesc;
	}
	@XmlElement(name = "engageDesc", required = false)
	public void setEngageDesc(String engageDesc) {
		this.engageDesc = engageDesc;
	}
	public String getState() {
		return state;
	}
	@XmlElement(name = "state", required = false)
	public void setState(String state) {
		this.state = state;
	}
	public String getRemark() {
		return remark;
	}
	@XmlElement(name = "remark", required = false)
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
