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
 * 单证类型管控信息维护
 * 【配置在单证类型上的管控信息，只可控制到级别】
 * 【另外：管控信息配置在表中VC_DOC_MNG_RULE，该表可控制到具体的机构、使用人】
 * 
 * @author whj
 * 
 */
@Entity
@Table(name = "VC_DOC_VERSION_INFO_MNG")
@SequenceGenerator(name = "SEQ_VC_DOC_VERSION_INFO_MNG", sequenceName = "SEQ_VC_DOC_VERSION_INFO_MNG", allocationSize = 1)
public class VcDocVersionInfoMng implements Serializable {

    private static final long serialVersionUID = -897694176512248980L;

    /** 流水 */
	@Id
	@Column(name = "ID_VC_DOC_VERSION_INFO_MNG")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VC_DOC_VERSION_INFO_MNG")
	private Long idVcDocVersionInfoMng;

	/** 单证类型代码 */
    @Column(name = "DOC_VER_CODE")
    private String docVerCode;


	/** 机构级别(1-5)、单证使用人(6) */
	@Column(name = "LEVEL_NO")
	private int levelNo;
	
	
	
	/** 库存上限 */
	@Column(name = "MAX_STORE")
    private int maxStore;
    
    /** 最长库存时间 (天)*/
  @Column(name = "MAX_STORE_TIME")
    private int maxStoreTime;
	

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
	
	
	/**自定义字段*/
	@Transient
    private String levelRemark;
	
	/**常量表中的库存上限 */
	@Transient
    private int maxStoreC;
    
    /** 常量表中最长库存时间 (天)*/
	@Transient
    private int maxStoreTimeC;
	
	/**页面标签Id*/
	@Transient
    private String tagName1;
	@Transient
    private String tagName2;
	/**自定义字段*/

    public Long getIdVcDocVersionInfoMng() {
        return idVcDocVersionInfoMng;
    }

    public void setIdVcDocVersionInfoMng(Long idVcDocVersionInfoMng) {
        this.idVcDocVersionInfoMng = idVcDocVersionInfoMng;
    }

    public String getDocVerCode() {
        return docVerCode;
    }

    public void setDocVerCode(String docVerCode) {
        this.docVerCode = docVerCode;
    }

    public int getLevelNo() {
        return levelNo;
    }

    public void setLevelNo(int levelNo) {
        this.levelNo = levelNo;
    }

    
    public String getLevelRemark() {
        return levelRemark;
    }

    public void setLevelRemark(String levelRemark) {
        this.levelRemark = levelRemark;
    }

    public int getMaxStore() {
        return maxStore;
    }

    public void setMaxStore(int maxStore) {
        this.maxStore = maxStore;
    }

    public int getMaxStoreTime() {
        return maxStoreTime;
    }

    public void setMaxStoreTime(int maxStoreTime) {
        this.maxStoreTime = maxStoreTime;
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

    public int getMaxStoreC() {
        return maxStoreC;
    }

    public void setMaxStoreC(int maxStoreC) {
        this.maxStoreC = maxStoreC;
    }

    public int getMaxStoreTimeC() {
        return maxStoreTimeC;
    }

    public void setMaxStoreTimeC(int maxStoreTimeC) {
        this.maxStoreTimeC = maxStoreTimeC;
    }

    public String getTagName1() {
        return tagName1;
    }

    public void setTagName1(String tagName1) {
        this.tagName1 = tagName1;
    }

    public String getTagName2() {
        return tagName2;
    }

    public void setTagName2(String tagName2) {
        this.tagName2 = tagName2;
    }
	
}
