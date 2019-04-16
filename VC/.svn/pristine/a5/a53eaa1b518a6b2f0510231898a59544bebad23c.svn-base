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
import javax.persistence.Transient;

/**
 * 级别设置表
 * <p>
 * Date 2013-05-03
 * </p>
 * <p>
 * Module：机构设置
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
@Table(name = "VC_LEVEL")
public class VcLevel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/**订单发起流水*/
	private Long id;
	/**级别单位类型--**0=机构;1=员工*/
	private String unitType;
	/**级别单位代码*/
	private String unitCode;
	/**级别单位名称*/
	private String unitName;
	/**上级机构ID*/
	private Long parentOrgId;
	/**上级机构Code*/
    private String parentOrgCode;
	/**机构类型--**0=公司;1=部门*/
	private String comType;
	/**级别等级*/
	private Integer levelNo;
	/**可管理的级别等级*/
	private Integer manageLevel;
	/**当前状态*/
	private String validStatus;
	/**备注*/
	private String remark;
	/**标志*/
	private String flag;
	/**级别代码*/
	private String levelCode;
	/**显示序号*/
	private Integer displayNo;
	/**创建人*/
	private String createdBy;
	/**创建时间*/
	private Date dateCreated;
	/**修改人*/
	private String updatedBy;
	/**修改时间*/
	private Date dateUpdated;
	
	private String invoiceFlag;
	/**自定义字段*/
	private String parentOrgName;	
	private String newParentOrgCode;	
	private String newParentOrgName;
	
	
	@SequenceGenerator(name = "generator", allocationSize = 1, sequenceName = "SEQ_VC_LEVEL")
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "generator")
	@Column(name = "ID_VC_LEVEL")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "UNIT_TYPE")
	public String getUnitType() {
		return unitType;
	}
	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}
	@Column(name = "UNIT_CODE")
	public String getUnitCode() {
		return unitCode;
	}
	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}
	@Column(name = "UNIT_NAME")
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	@Column(name = "PARENT_ORG_ID")
	public Long getParentOrgId() {
		return parentOrgId;
	}
	public void setParentOrgId(Long parentOrgId) {
		this.parentOrgId = parentOrgId;
	}
	
	@Column(name = "PARENT_ORG_CODE")
    public String getParentOrgCode() {
        return parentOrgCode;
    }   
    public void setParentOrgCode(String parentOrgCode) {
        this.parentOrgCode = parentOrgCode;
    }
    @Column(name = "COM_TYPE")
	public String getComType() {
		return comType;
	}
	public void setComType(String comType) {
		this.comType = comType;
	}
	@Column(name = "LEVEL_NO")
	public Integer getLevelNo() {
		return levelNo;
	}
	public void setLevelNo(Integer levelNo) {
		this.levelNo = levelNo;
	}
	@Column(name = "MANAGE_LEVEL")
	public Integer getManageLevel() {
		return manageLevel;
	}
	public void setManageLevel(Integer manageLevel) {
		this.manageLevel = manageLevel;
	}
	@Column(name = "VALID_STATUS")
	public String getValidStatus() {
		return validStatus;
	}
	public void setValidStatus(String validStatus) {
		this.validStatus = validStatus;
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
	@Column(name = "LEVEL_CODE")
	public String getLevelCode() {
		return levelCode;
	}
	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}
	@Column(name = "DISPLAY_NO")
	public Integer getDisplayNo() {
		return displayNo;
	}
	public void setDisplayNo(Integer displayNo) {
		this.displayNo = displayNo;
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
    public String getParentOrgName() {
        return parentOrgName;
    }
    
    public void setParentOrgName(String parentOrgName) {
        this.parentOrgName = parentOrgName;
    }
    @Transient
    public String getNewParentOrgCode() {
        return newParentOrgCode;
    }
   
    public void setNewParentOrgCode(String newParentOrgCode) {
        this.newParentOrgCode = newParentOrgCode;
    }
    
    @Transient
    public String getNewParentOrgName() {
        return newParentOrgName;
    }
    
    public void setNewParentOrgName(String newParentOrgName) {
        this.newParentOrgName = newParentOrgName;
    }
    @Column(name = "invoice_flag")
	public String getInvoiceFlag() {
		return invoiceFlag;
	}
	public void setInvoiceFlag(String invoiceFlag) {
		this.invoiceFlag = invoiceFlag;
	}
}
