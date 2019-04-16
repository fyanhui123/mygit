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
 * Date 2013-06-08
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
 * @author chy
 * @version 1.0
 */
@Entity
@Table(name = "PUB_COMPANY")
public class PubCompany implements Serializable{

	private static final long serialVersionUID = 1L;
	/**机构代码*/
	private String companyCode;
	/**机构简体中文名称*/
	private String companyCname;
	/**机构繁体中文名称*/
	private String companyTname;
	/**机构英文名称*/
	private String companyEname;
	/**地址简体中文名称*/
	private String addressCname;
	/**地址繁体中文名称*/
	private String addressTname;
	/**地址英文名称*/
	private String addressEname;
	/**保险人简体中文名称*/
	private String insurerCname;
	/**保险人繁体中文名称*/
	private String insurerTname;
	/**保险人英文名称*/
	private String insurerEname;
	/**形成机构树*/
	private String upperCompanyCode;
	/**机构属性 O机构、D部门、B销售网点*/
	private String comAttribute;
	/**机构类型 1.出单机构 2.归属机构 3.收付机构 4:.分保机构*/
	private String comType;
	/**管理级别 0.管理单位  1.核算单位  2.基层单位  3.部门*/
	private String centerInd;
	/**机构层级 1.总公司 2.省公司 3.地市 4.区县 5.科室 6.网点 7.代理点*/
	private String comLevel;
	/**邮编*/
	private String postCode;
	/**电话*/
	private String phoneNumber;
	/**传真*/
	private String faxNumber;
	/**经理*/
	private String manager;
	/**网址*/
	private String webAddress;
	/**服务电话*/
	private String servicePhone;
	/**报案电话*/
	private String reportPhone;
	/**创建人*/
	private String creatorCode;
	/**创建时间*/
	private Date createTime;
	/**最后修改人*/
	private String updaterCode;
	/**最后修改时间*/
	private Date updateTime;
	/**是否有效标志
Y:有效，N:无效*/
	private String validInd;
	/**备注*/
	private String remark;
	/**标志字段*/
	private String flag;
	/**简体中文简称*/
	private String shortCname;
	/**繁体中文简称*/
	private String shortTname;
	/**英文简称*/
	private String shortEname;
	/**保险公司纳税人识别号*/
	private String taxNumber;
	/**电子邮箱地址*/
	private String email;
	/**保单打印机构代码*/
	private String printPolicyComCode;
	/**发票打印机构代码*/
	private String printInvoiceComCode;
	
	/**机构归属渠道,00-无渠道,01-直管渠道,02-车商渠道,03-重客渠道,04-银保渠道,05-电商渠道,99-未分渠道*/
	private String channelCode;
	
	@Id
	@Column(name="COMPANYCODE")
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	@Column(name="COMPANYCNAME")
	public String getCompanyCname() {
		return companyCname;
	}
	public void setCompanyCname(String companyCname) {
		this.companyCname = companyCname;
	}
	@Column(name="COMPANYTNAME")
	public String getCompanyTname() {
		return companyTname;
	}
	public void setCompanyTname(String companyTname) {
		this.companyTname = companyTname;
	}
	@Column(name="COMPANYENAME")
	public String getCompanyEname() {
		return companyEname;
	}
	public void setCompanyEname(String companyEname) {
		this.companyEname = companyEname;
	}
	@Column(name="ADDRESSCNAME")
	public String getAddressCname() {
		return addressCname;
	}
	public void setAddressCname(String addressCname) {
		this.addressCname = addressCname;
	}
	@Column(name="ADDRESSTNAME")
	public String getAddressTname() {
		return addressTname;
	}
	public void setAddressTname(String addressTname) {
		this.addressTname = addressTname;
	}
	@Column(name="ADDRESSENAME")
	public String getAddressEname() {
		return addressEname;
	}
	public void setAddressEname(String addressEname) {
		this.addressEname = addressEname;
	}
	@Column(name="INSURERCNAME")
	public String getInsurerCname() {
		return insurerCname;
	}
	public void setInsurerCname(String insurerCname) {
		this.insurerCname = insurerCname;
	}
	@Column(name="INSURERTNAME")
	public String getInsurerTname() {
		return insurerTname;
	}
	public void setInsurerTname(String insurerTname) {
		this.insurerTname = insurerTname;
	}
	@Column(name="INSURERENAME")
	public String getInsurerEname() {
		return insurerEname;
	}
	public void setInsurerEname(String insurerEname) {
		this.insurerEname = insurerEname;
	}
	@Column(name="UPPERCOMPANYCODE")
	public String getUpperCompanyCode() {
		return upperCompanyCode;
	}
	public void setUpperCompanyCode(String upperCompanyCode) {
		this.upperCompanyCode = upperCompanyCode;
	}
	@Column(name="COMATTRIBUTE")
	public String getComAttribute() {
		return comAttribute;
	}
	public void setComAttribute(String comAttribute) {
		this.comAttribute = comAttribute;
	}
	@Column(name="COMTYPE")
	public String getComType() {
		return comType;
	}
	public void setComType(String comType) {
		this.comType = comType;
	}
	@Column(name="CENTERIND")
	public String getCenterInd() {
		return centerInd;
	}
	public void setCenterInd(String centerInd) {
		this.centerInd = centerInd;
	}
	@Column(name="COMLEVEL")
	public String getComLevel() {
		return comLevel;
	}
	public void setComLevel(String comLevel) {
		this.comLevel = comLevel;
	}
	@Column(name="POSTCODE")
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	@Column(name="PHONENUMBER")
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@Column(name="FAXNUMBER")
	public String getFaxNumber() {
		return faxNumber;
	}
	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}
	@Column(name="MANAGER")
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	@Column(name="WEBADDRESS")
	public String getWebAddress() {
		return webAddress;
	}
	public void setWebAddress(String webAddress) {
		this.webAddress = webAddress;
	}
	@Column(name="SERVICEPHONE")
	public String getServicePhone() {
		return servicePhone;
	}
	public void setServicePhone(String servicePhone) {
		this.servicePhone = servicePhone;
	}
	@Column(name="REPORTPHONE")
	public String getReportPhone() {
		return reportPhone;
	}
	public void setReportPhone(String reportPhone) {
		this.reportPhone = reportPhone;
	}
	@Column(name="CREATORCODE")
	public String getCreatorCode() {
		return creatorCode;
	}
	public void setCreatorCode(String creatorCode) {
		this.creatorCode = creatorCode;
	}
	@Column(name="CREATETIME")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Column(name="UPDATERCODE")
	public String getUpdaterCode() {
		return updaterCode;
	}
	public void setUpdaterCode(String updaterCode) {
		this.updaterCode = updaterCode;
	}
	@Column(name="UPDATETIME")
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Column(name="VALIDIND")
	public String getValidInd() {
		return validInd;
	}
	public void setValidInd(String validInd) {
		this.validInd = validInd;
	}
	@Column(name="REMARK")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name="FLAG")
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	@Column(name="SHORTCNAME")
	public String getShortCname() {
		return shortCname;
	}
	public void setShortCname(String shortCname) {
		this.shortCname = shortCname;
	}
	@Column(name="SHORTTNAME")
	public String getShortTname() {
		return shortTname;
	}
	public void setShortTname(String shortTname) {
		this.shortTname = shortTname;
	}
	@Column(name="SHORTENAME")
	public String getShortEname() {
		return shortEname;
	}
	public void setShortEname(String shortEname) {
		this.shortEname = shortEname;
	}
	@Column(name="TAXNUMBER")
	public String getTaxNumber() {
		return taxNumber;
	}
	public void setTaxNumber(String taxNumber) {
		this.taxNumber = taxNumber;
	}
	@Column(name="EMAIL")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name="PRINTPOLICYCOMCODE")
	public String getPrintPolicyComCode() {
		return printPolicyComCode;
	}
	public void setPrintPolicyComCode(String printPolicyComCode) {
		this.printPolicyComCode = printPolicyComCode;
	}
	@Column(name="PRINTINVOICECOMCODE")
	public String getPrintInvoiceComCode() {
		return printInvoiceComCode;
	}
	public void setPrintInvoiceComCode(String printInvoiceComCode) {
		this.printInvoiceComCode = printInvoiceComCode;
	}
	
	@Column(name="CHANNEL_CODE")
	public String getChannelCode() {
		return channelCode;
	}
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
}
