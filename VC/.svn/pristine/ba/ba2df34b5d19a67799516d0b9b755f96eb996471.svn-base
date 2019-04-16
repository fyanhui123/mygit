package com.tapi.tcs.vc.schema.model;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 单证版本流水号最大值表
 * <p>
 * Date 2013-04-24
 * </p>
 * <p>
 * Module：基本信息维护
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
@Table(name = "VC_DOC_VER_MAX_NUM")
public class VcDocVerMaxNum implements Serializable{
	
	private static final long serialVersionUID = 1L;
	/**单证版本流水号最大值流水*/
	private Long id;
	/**单证类型代码*/
	private String versionCode;
	/**最大流水号*/
	private Integer maxNum;
	/**创建人*/
	private String createdBy;
	/**创建时间*/
	private Date dateCreated;
	/**修改人*/
	private String updatedBy;
	/**修改时间*/
	private Date dateUpdated;
	
	@SequenceGenerator(name = "generator", allocationSize = 1, sequenceName = "SEQ_VC_DOC_VER_MAX_NUM")
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "generator")
	@Column(name = "ID_VC_DOC_VER_MAX_NUM")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "DOC_VER_CODE")
	public String getVersionCode() {
		return versionCode;
	}
	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}
	@Column(name = "MAX_NUM")
	public Integer getMaxNum() {
		return maxNum;
	}
	public void setMaxNum(Integer maxNum) {
		this.maxNum = maxNum;
	}
	@Column(name = "CREATED_BY")
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	@Column(name = "DATE_CREATED")
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	@Column(name = "UPDATED_BY")
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	@Column(name = "DATE_UPDATED")
	public Date getDateUpdated() {
		return dateUpdated;
	}
	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}
}
