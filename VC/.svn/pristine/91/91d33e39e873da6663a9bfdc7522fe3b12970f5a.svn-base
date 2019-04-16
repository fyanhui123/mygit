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
@Table(name = "PUB_CODE_TRANSFER")
public class PubCodeTransfer implements Serializable {

	private static final long serialVersionUID = -3654182395620249103L;
	private PubCodeTransferId id;
	private String outerCode;
	private String creatorCode;
	private Date createTime;
	private String updaterCode;
	private Date updateTime;
	private Date validDate;
	private Date inValidDate;
	private String validind;
	private String remark;
	private String flag;

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "companyCode", column = @Column(name = "COMPANYCODE", nullable = false)),
			@AttributeOverride(name = "configCode", column = @Column(name = "CONFIGCODE", nullable = false)),
			@AttributeOverride(name = "innerCode", column = @Column(name = "INNERCODE", nullable = false)),
			@AttributeOverride(name = "outerCodeType", column = @Column(name = "outerCodeType", nullable = false)) })
	public PubCodeTransferId getId() {
		return id;
	}

	public void setId(PubCodeTransferId id) {
		this.id = id;
	}

	@Column(name = "OUTERCODE")
	public String getOuterCode() {
		return outerCode;
	}

	public void setOuterCode(String outerCode) {
		this.outerCode = outerCode;
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
	public String getValidind() {
		return validind;
	}

	public void setValidind(String validind) {
		this.validind = validind;
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
