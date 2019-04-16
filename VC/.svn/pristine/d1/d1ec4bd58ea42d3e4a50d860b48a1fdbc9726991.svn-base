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


/**
 * 汇总订单附表
 * <p>
 * Date 2013-03-27
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
@Table(name = "VC_ORDER_LAUNCH_SUB")
public class VcOrderLaunchSub implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**订单发起详情流水*/
	private Long id;
	
	/**订单基本信息*/
	private VcOrderLaunch vcOrderLaunch;
	
	/**子订单流水号*/
	private Long subOrderId;
	
	/**创建人*/
	private String createdBy;
	
	/**创建时间*/
	private Date dateCreated;
	
	/**修改人*/
	private String updatedBy;
	
	/**修改时间*/
	private Date dateUpdated;
	
	@SequenceGenerator(name = "generator", allocationSize = 1, sequenceName = "SEQ_VC_ORDER_LAUNCH_SUB")
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "generator")
	@Column(name = "ID_VC_ORDER_LAUNCH_SUB")
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

	@Column(name = "SUB_DOC_LAUNCH")
	public Long getSubOrderId() {
		return subOrderId;
	}

	public void setSubOrderId(Long subOrderId) {
		this.subOrderId = subOrderId;
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
