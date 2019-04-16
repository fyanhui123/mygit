package com.tapi.tcs.vc.schema.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "PUB_RISK_RATION")
public class PubRiskRation implements Serializable {

	private static final long serialVersionUID = -5873432551132174021L;

	private String rationType; // 定额类型代码
	private String rationName; // 定额类型名称
	private String itemCode; // 标的代码
	private String kindCode; // 险别代码
	private String liabCode; // 责任代码
	private String currency; // 币别代码
	private Double amount; // 保额
	private Double rate; // 费率
	private Double premium; // 保费
	private String creatorCode; // 创建人O
	private Date createTime; // 创建时间
	private String updaterCode; // 最后修改人
	private Date updateTime; // 最后修改时间
	private Date validDate; // 生效日期
	private Date invalidDate; // 失效日期
	private String validInd; // 1-有效；0-无效
	private String remark; // 备注
	private String flag; // 备用标志字段
	private String factor1; // 费率因子1
	private String factor2; // 费率因子2
	private String factor3; // 费率因子3
	private String factor4; // 费率因子4
	private String factor5; // 费率因子5
	private String factor6; // 费率因子6
	private String calculateInd; // 0-不计入总保额，1计入总保额
	private String shortRateFlag; // 默认短期费率标志

	private PubRiskRationId id; // 主键

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "riskCode", column = @Column(name = "RISKCODE", nullable = false)),
			@AttributeOverride(name = "serialNo", column = @Column(name = "SERIALNO", nullable = false)),
			@AttributeOverride(name = "companyCode", column = @Column(name = "COMPANYCODE", nullable = false)) })
	public PubRiskRationId getId() {
		return id;
	}

	public void setId(PubRiskRationId id) {
		this.id = id;
	}

	@Column(name = "RATIONTYPE")
	public String getRationType() {
		return rationType;
	}

	public void setRationType(String rationType) {
		this.rationType = rationType;
	}

	@Column(name = "RATIONNAME")
	public String getRationName() {
		return rationName;
	}

	public void setRationName(String rationName) {
		this.rationName = rationName;
	}

	@Column(name = "ITEMCODE")
	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	@Column(name = "KINDCODE")
	public String getKindCode() {
		return kindCode;
	}

	public void setKindCode(String kindCode) {
		this.kindCode = kindCode;
	}

	@Column(name = "LIABCODE")
	public String getLiabCode() {
		return liabCode;
	}

	public void setLiabCode(String liabCode) {
		this.liabCode = liabCode;
	}

	@Column(name = "CURRENCY")
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Column(name = "AMOUNT")
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Column(name = "RATE")
	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	@Column(name = "PREMIUM")
	public Double getPremium() {
		return premium;
	}

	public void setPremium(Double premium) {
		this.premium = premium;
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
	public Date getInvalidDate() {
		return invalidDate;
	}

	public void setInvalidDate(Date invalidDate) {
		this.invalidDate = invalidDate;
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

	@Column(name = "FACTOR1")
	public String getFactor1() {
		return factor1;
	}

	public void setFactor1(String factor1) {
		this.factor1 = factor1;
	}

	@Column(name = "FACTOR2")
	public String getFactor2() {
		return factor2;
	}

	public void setFactor2(String factor2) {
		this.factor2 = factor2;
	}

	@Column(name = "FACTOR3")
	public String getFactor3() {
		return factor3;
	}

	public void setFactor3(String factor3) {
		this.factor3 = factor3;
	}

	@Column(name = "FACTOR4")
	public String getFactor4() {
		return factor4;
	}

	public void setFactor4(String factor4) {
		this.factor4 = factor4;
	}

	@Column(name = "FACTOR5")
	public String getFactor5() {
		return factor5;
	}

	public void setFactor5(String factor5) {
		this.factor5 = factor5;
	}

	@Column(name = "FACTOR6")
	public String getFactor6() {
		return factor6;
	}

	public void setFactor6(String factor6) {
		this.factor6 = factor6;
	}

	@Column(name = "CALCULATEIND")
	public String getCalculateInd() {
		return calculateInd;
	}

	public void setCalculateInd(String calculateInd) {
		this.calculateInd = calculateInd;
	}

	@Column(name = "SHORTRATEFLAG")
	public String getShortRateFlag() {
		return shortRateFlag;
	}

	public void setShortRateFlag(String shortRateFlag) {
		this.shortRateFlag = shortRateFlag;
	}

}
