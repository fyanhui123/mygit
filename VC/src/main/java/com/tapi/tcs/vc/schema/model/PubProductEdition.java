package com.tapi.tcs.vc.schema.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PUB_PRODUCT_EDITION")
public class PubProductEdition implements Serializable{

	private static final long serialVersionUID = 1L;
	/**计划代码*/
	private String planCode;
	/**险种代码*/
	private String riskCode;
	/**机构代码*/
	private String companyCode;
	/**产品版本*/
	private String productEdition;
	/**产品版本中文名称*/
	private String productEditionCname;
	/**产品版本英文名称*/
	private String productEditionEname;
	/**产品版本繁体名称*/
	private String productEditionTname;
	/**创建人*/
	private String creatorCode;
	/**创建时间*/
	private Date createTime;
	/**最后修改人*/
	private String updaterCode;
	/**最后修改时间*/
	private Date updateTime;
	/**生效日期*/
	private Date validDate;
	/**失效日期*/
	private Date inValidDate;
	/**有效标志,1:有效 0:无效*/
	private String validInd;
	/**备注*/
	private String remark;
	/**标志字段*/
	private String flag;
	
	@Id
	@Column(name = "PLANCODE")
	public String getPlanCode() {
		return planCode;
	}
	public void setPlanCode(String planCode) {
		this.planCode = planCode;
	}
	@Column(name = "RISKCODE")
	public String getRiskCode() {
		return riskCode;
	}
	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}
	@Column(name = "COMPANYCODE")
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	@Column(name = "PRODUCTEDITION")
	public String getProductEdition() {
		return productEdition;
	}
	public void setProductEdition(String productEdition) {
		this.productEdition = productEdition;
	}
	@Column(name = "PRODUCTEDITIONCNAME")
	public String getProductEditionCname() {
		return productEditionCname;
	}
	public void setProductEditionCname(String productEditionCname) {
		this.productEditionCname = productEditionCname;
	}
	@Column(name = "PRODUCTEDITIONENAME")
	public String getProductEditionEname() {
		return productEditionEname;
	}
	public void setProductEditionEname(String productEditionEname) {
		this.productEditionEname = productEditionEname;
	}
	@Column(name = "PRODUCTEDITIONTNAME")
	public String getProductEditionTname() {
		return productEditionTname;
	}
	public void setProductEditionTname(String productEditionTname) {
		this.productEditionTname = productEditionTname;
	}
	@Column(name = "CREATORCODE")
	public String getCreatorCode() {
		return creatorCode;
	}
	public void setCreatorCode(String creatorCode) {
		this.creatorCode = creatorCode;
	}
	@Column(name = "CREATETIME")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Column(name = "UPDATERCODE")
	public String getUpdaterCode() {
		return updaterCode;
	}
	public void setUpdaterCode(String updaterCode) {
		this.updaterCode = updaterCode;
	}
	@Column(name = "UPDATETIME")
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Column(name = "VALIDDATE")
	public Date getValidDate() {
		return validDate;
	}
	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}
	@Column(name = "INVALIDDATE")
	public Date getInValidDate() {
		return inValidDate;
	}
	public void setInValidDate(Date inValidDate) {
		this.inValidDate = inValidDate;
	}
	@Column(name = "VALIDIND")
	public String getValidInd() {
		return validInd;
	}
	public void setValidInd(String validInd) {
		this.validInd = validInd;
	}
	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name = "FLAG")
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
}
