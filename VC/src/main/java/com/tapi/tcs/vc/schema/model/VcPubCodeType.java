package com.tapi.tcs.vc.schema.model;

import static javax.persistence.GenerationType.SEQUENCE;

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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 通用代码类表
 * <p>
 * Date 2013-03-20
 * </p>
 * <p>
 * Module：公共模块
 * </p>
 * <p>
 * Description：
 * </p>
 * <p>
 * Remark：实体类，不允许修改
 * </p>
 * @author wanghuajian
 * @version 1.0
 */
@Entity
@Table(name = "VC_PUB_CODE_TYPE")
@SequenceGenerator(name = "SEQ_VC_PUB_CODE_TYPE", sequenceName = "SEQ_VC_PUB_CODE_TYPE", allocationSize = 1)
public class VcPubCodeType implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 8622805876090271401L;

	/**通用代码类流水*/
	@Id
	@Column(name = "ID_VC_PUB_CODE_TYPE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VC_PUB_CODE_TYPE")	
	private Long	idVcPubCodeType;
	
	/**代码类型*/
	@Column(name = "CODE_TYPE")
	private String	codeType;	
	
	/**代码类型描述*/
	@Column(name = "CODE_TYPE_DESC")
	private String	codeTypeDesc;
	
	/**有效标志	*/
	@Column(name = "STATUS")
	private String status;
	
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
	
	/**通用代码信息*/
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "vcPubCodeType")
	private List<VcPubCode> vcPubCodeList = new ArrayList<VcPubCode>();

	public Long getIdVcPubCodeType() {
		return idVcPubCodeType;
	}

	public void setIdVcPubCodeType(Long idVcPubCodeType) {
		this.idVcPubCodeType = idVcPubCodeType;
	}

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public String getCodeTypeDesc() {
		return codeTypeDesc;
	}

	public void setCodeTypeDesc(String codeTypeDesc) {
		this.codeTypeDesc = codeTypeDesc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public List<VcPubCode> getVcPubCodeList() {
		return vcPubCodeList;
	}

	public void setVcPubCodeList(List<VcPubCode> vcPubCodeList) {
		this.vcPubCodeList = vcPubCodeList;
	}

	
	
	
	
}
