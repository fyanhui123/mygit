/**
 * 
 */
package com.tapi.tcs.vc.schema.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 单证印刷流水号规则
 * 
 * @author wanghuajian
 * @since 2013-04-10
 * 
 */
@Entity
@Table(name = "VC_DOC_PRT_NO_RULE")
@SequenceGenerator(name = "SEQ_VC_DOC_PRT_NO_RULE", sequenceName = "SEQ_VC_DOC_PRT_NO_RULE", allocationSize = 1)
public class VcDocPrtNoRule implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5383201304101330L;

	/** 单证印刷流水号规则表流水 */
	@Id
	@Column(name = "ID_VC_DOC_PRT_NO_RULE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VC_DOC_PRT_NO_RULE")
	private Long idVcDocPrtNoRule;

	/** 单证类型代码 *//*
	@Column(name = "DOC_VER_CODE", insertable = false, updatable = false)
	private String docVerCode;*/
	/** 单证类型信息表流水 */
	@Column(name = "ID_VC_DOC_VERSION_INFO",insertable = false, updatable = false)
	private Long idVcDocVersionInfo;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_VC_DOC_VERSION_INFO", referencedColumnName = "ID_VC_DOC_VERSION_INFO")
	private VcDocVersionInfo docVersionInfo;

	/** 元素排列顺序 */
	@Column(name = "SORT_NUM")
	private int sortNum;

	/** 元素项类别代码（p险种代码/v版本/y年份/s流水号） */
	@Column(name = "ITEM_TYPE_CODE")
	private String itemTypeCode;

	/** 元素值 */
	@Column(name = "ITEM_VALUE")
	private String itemValue;

	/** 元素长度 */
	@Column(name = "ITEM_LENGTH")
	private int itemLength;

	/** 创建人 */
	@Column(name = "created_by")
	private String createdBy;

	/** 创建时间 */
	@Column(name = "date_created")
	private Date dateCreated;

	/** 修改人 */
	@Column(name = "updated_by")
	private String updatedBy;

	/** 修改时间 */
	@Column(name = "date_updated")
	private Date dateUpdated;

	public Long getIdVcDocPrtNoRule() {
		return idVcDocPrtNoRule;
	}

	public void setIdVcDocPrtNoRule(Long idVcDocPrtNoRule) {
		this.idVcDocPrtNoRule = idVcDocPrtNoRule;
	}

	/*public String getDocVerCode() {
		return docVerCode;
	}

	public void setDocVerCode(String docVerCode) {
		this.docVerCode = docVerCode;
	}*/
	

	public VcDocVersionInfo getDocVersionInfo() {
		return docVersionInfo;
	}

	public Long getIdVcDocVersionInfo() {
		return idVcDocVersionInfo;
	}

	public void setIdVcDocVersionInfo(Long idVcDocVersionInfo) {
		this.idVcDocVersionInfo = idVcDocVersionInfo;
	}

	public void setDocVersionInfo(VcDocVersionInfo docVersionInfo) {
		this.docVersionInfo = docVersionInfo;
	}

	public int getSortNum() {
		return sortNum;
	}

	public void setSortNum(int sortNum) {
		this.sortNum = sortNum;
	}

	public String getItemTypeCode() {
		return itemTypeCode;
	}

	public void setItemTypeCode(String itemTypeCode) {
		this.itemTypeCode = itemTypeCode;
	}

	public String getItemValue() {
		return itemValue;
	}

	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}

	public int getItemLength() {
		return itemLength;
	}

	public void setItemLength(int itemLength) {
		this.itemLength = itemLength;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	

}
