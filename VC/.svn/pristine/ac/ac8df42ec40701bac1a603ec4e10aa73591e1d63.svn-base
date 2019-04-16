package com.tapi.tcs.vc.schema.model;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 订单明细表
 * <p>
 * Date 2013-03-16
 * </p>
 * <p>
 * Module：征订管理
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
@Table(name = "VC_ORDER_LAUNCH_DET")
public class VcOrderLaunchDet implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**订单发起详情流水*/
	private Long id;
	
	/**订单基本信息*/
	private VcOrderLaunch vcOrderLaunch;
	
	/**单证类型代码*/
	private String versionCode;
	
	/**订单数量*/
	private Integer orderCount;
	
	/**申印数量*/
	private Integer applyPrintNum;
	
	/**创建人*/
	private String createdBy;
	
	/**创建时间*/
	private Date dateCreated;
	
	/**修改人*/
	private String updatedBy;
	
	/**修改时间*/
	private Date dateUpdated;
	
	//****自定义属性****
	private Long allStore;
	
	@SequenceGenerator(name = "generator", allocationSize = 1, sequenceName = "SEQ_VC_ORDER_LAUNCH_DET")
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "generator")
	@Column(name = "ID_VC_ORDER_LAUNCH_DET")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_VC_ORDER_LAUNCH", referencedColumnName="ID_VC_ORDER_LAUNCH", nullable = true)
	public VcOrderLaunch getVcOrderLaunch() {
		return vcOrderLaunch;
	}

	public void setVcOrderLaunch(VcOrderLaunch vcOrderLaunch) {
		this.vcOrderLaunch = vcOrderLaunch;
	}

	@Column(name = "DOC_VER_CODE")
	public String getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}
	
	@Column(name = "DOC_NUM")
	public Integer getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}

	@Column(name = "APPLY_PRINT_NUM")
	public Integer getApplyPrintNum() {
		return applyPrintNum;
	}

	public void setApplyPrintNum(Integer applyPrintNum) {
		this.applyPrintNum = applyPrintNum;
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
	@Transient
	public Long getAllStore() {
		return allStore;
	}
	public void setAllStore(Long allStore) {
		this.allStore = allStore;
	}
}
