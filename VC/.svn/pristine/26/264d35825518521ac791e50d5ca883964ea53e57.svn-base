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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 通用代码表
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
@Table(name = "VC_PUB_CODE")
@SequenceGenerator(name = "SEQ_VC_PUB_CODE", sequenceName = "SEQ_VC_PUB_CODE", allocationSize = 1)
public class VcPubCode implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5624558352084733115L;

	/**通用代码流水*/
	@Id
	@Column(name = "ID_VC_PUB_CODE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VC_PUB_CODE")	
	private Long	idVcPubCode;
	
	/**代码类型*/
	@Column(name = "CODE_TYPE" ,insertable=false ,updatable=false)
	private String	codeType;	
	
	/** 代码类型类 */
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "CODE_TYPE", referencedColumnName="CODE_TYPE", nullable = true)
	private VcPubCodeType vcPubCodeType;
	
	/**业务代码*/
	@Column(name = "CODE_CODE")
	private String	codeCode;	
	
	/**业务代码中文含义*/
	@Column(name = "CODE_C_NAME")
	private String	codeCName;
	
	/**业务代码英文含义*/
	@Column(name = "CODE_E_NAME")
	private String	codeEName;
	
	/**上级代码*/
	@Column(name = "UPPER_CODE")
	private String	upperCode;
	
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

	public Long getIdVcPubCode() {
		return idVcPubCode;
	}

	public void setIdVcPubCode(Long idVcPubCode) {
		this.idVcPubCode = idVcPubCode;
	}

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public VcPubCodeType getVcPubCodeType() {
		return vcPubCodeType;
	}

	public void setVcPubCodeType(VcPubCodeType vcPubCodeType) {
		this.vcPubCodeType = vcPubCodeType;
	}

	public String getCodeCode() {
		return codeCode;
	}

	public void setCodeCode(String codeCode) {
		this.codeCode = codeCode;
	}

	public String getCodeCName() {
		return codeCName;
	}

	public void setCodeCName(String codeCName) {
		this.codeCName = codeCName;
	}

	public String getCodeEName() {
		return codeEName;
	}

	public void setCodeEName(String codeEName) {
		this.codeEName = codeEName;
	}

	public String getUpperCode() {
		return upperCode;
	}

	public void setUpperCode(String upperCode) {
		this.upperCode = upperCode;
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
	

	
	
	
}
