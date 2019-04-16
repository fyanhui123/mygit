package com.tapi.tcs.vc.schema.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 单证管理控制规则表 VC_DOC_MNG_RULE 对应实体类
 * 
 * @author wanghuajian
 * @date 2013-04-23 16:31
 * 
 */
@Entity
@Table(name = "VC_DOC_MNG_RULE")
@SequenceGenerator(name = "SEQ_VC_DOC_MNG_RULE", sequenceName = "SEQ_VC_DOC_MNG_RULE", allocationSize = 1)
public class VcDocMngRule implements Serializable {

	private static final long serialVersionUID = 1894785963973356594L;

	/** 单证管理控制规则流水 */
	private Long idVcDocMngRule;
	/** 单证类型代码 */
	private String docVerCode;
	/** 管理控制类型(0-部门 1-员工) */
	private String mngType;
	/** 使用人/机构代码 */
	private String mngObjectCode;			
	/** 代理机构 */
	private String agencyCode;
	/** 所属机构代码 */
	private String orgCode;
	/** 库存上限 */
	private int maxStore;
	/** 最长库存时间 */
	private int maxStoreTime;
	/** 有效标志▲ */
	private String status;
	/** 创建人 */
	private String createdBy;
	/** 创建时间 */
	private Date dateCreated;
	/** 修改人 */
	private String updatedBy;
	/** 修改时间 */
	private Date dateUpdated;
	
	
	//自定义字段=================
	/** 默认库存上限 ，用于页面校验 */
    private int defaultMaxStore;
    /** 默认最长库存时间，用于页面校验 */
    private int defaultMaxStoreTime;
	
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VC_DOC_MNG_RULE")
	@Column(name = "ID_VC_DOC_MNG_RULE")
	public Long getIdVcDocMngRule() {
		return idVcDocMngRule;
	}

	public void setIdVcDocMngRule(Long idVcDocMngRule) {
		this.idVcDocMngRule = idVcDocMngRule;
	}

	@Column(name = "DOC_VER_CODE")
	public String getDocVerCode() {
		return docVerCode;
	}

	public void setDocVerCode(String docVerCode) {
		this.docVerCode = docVerCode;
	}

	@Column(name = "MNG_TYPE")
	public String getMngType() {
		return mngType;
	}

	public void setMngType(String mngType) {
		this.mngType = mngType;
	}

	@Column(name = "MNG_OBJECT_CODE")
	public String getMngObjectCode() {
		return mngObjectCode;
	}

	public void setMngObjectCode(String mngObjectCode) {
		this.mngObjectCode = mngObjectCode;
	}

	@Column(name = "AGENCY_CODE")
	public String getAgencyCode() {
		return agencyCode;
	}

	

	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}

	@Column(name = "ORG_CODE")
	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	@Column(name = "MAX_STORE")
	public int getMaxStore() {
		return maxStore;
	}

	public void setMaxStore(int maxStore) {
		this.maxStore = maxStore;
	}
	
	@Column(name = "MAX_STORE_TIME")
	public int getMaxStoreTime() {
		return maxStoreTime;
	}

	public void setMaxStoreTime(int maxStoreTime) {
		this.maxStoreTime = maxStoreTime;
	}

	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

    /**
     * @return the defaultMaxStore
     */
	@Transient
    public int getDefaultMaxStore() {
        return defaultMaxStore;
    }

    /**
     * @param defaultMaxStore the defaultMaxStore to set
     */
    public void setDefaultMaxStore(int defaultMaxStore) {
        this.defaultMaxStore = defaultMaxStore;
    }

    /**
     * @return the defaultMaxStoreTime
     */
    @Transient
    public int getDefaultMaxStoreTime() {
        return defaultMaxStoreTime;
    }

    /**
     * @param defaultMaxStoreTime the defaultMaxStoreTime to set
     */
    public void setDefaultMaxStoreTime(int defaultMaxStoreTime) {
        this.defaultMaxStoreTime = defaultMaxStoreTime;
    }


	
	
	
	
	
	
	
}
