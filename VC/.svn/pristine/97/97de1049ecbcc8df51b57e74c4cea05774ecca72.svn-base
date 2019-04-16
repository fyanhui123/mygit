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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 系统定义
 * 
 * @author pengzhaosong
 * 
 */
@Entity
@Table(name = "VC_SYSTEM_DEF")
@SequenceGenerator(name = "SEQ_SYSTEM_DEF", sequenceName = "SEQ_SYSTEM_DEF", allocationSize = 1)

public class SystemDef implements Serializable {
	
	private static final long serialVersionUID = 4127131356099421485L;    
	
	/** 系统ID */
	@Id	
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SYSTEM_DEF")
	@Column(name = "SYSTEM_ID", precision=10, scale=0)
	private Long systemId;
	
	/** 系统代码 */
	@Column(name = "SYSTEM_CODE")
	private String systemCode;

	/** 系统中文名称 */
	@Column(name = "CHINESE_NAME")
	private String chineseName;
	
	/** 系统英文名称  */
	@Column(name = "ENGLISH_NAME")
	private String englishName;

	/** 系统访问URL */
	@Column(name = "URL")
	private String url;

	/** 登录模式 */
	@Column(name = "LOGIN_MODE")
	private String loginMode;
	
	/** 菜单样式 */
	@Column(name = "MENU_STYLE")
	private String menuStyle;
	
	/** 系统负责人 */
	@Column(name = "SYSTEM_OWNER")
	private String systemOwner;
	
	/** 创建人代码 */
	@Column(name = "CREATED_BY", updatable=false)
	private String createdBy;
	
	/** 创建时间  */
	@Column(name = "DATE_CREATED", updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;
	
	/** 修改人代码 */
	@Column(name = "UPDATE_BY")
	private String updateBy;
	
	/** 修改人代码 */
	@Column(name = "DATE_UPDATED")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateUpdated;	
	
	/** 是否有效标志  */
	@Column(name = "VALID_FLAG")
	private String validFlag;		
	

	public Long getSystemId() {
		return systemId;
	}

	public void setSystemId(Long systemId) {
		this.systemId = systemId;
	}

	public String getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}

	public String getChineseName() {
		return chineseName;
	}

	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLoginMode() {
		return loginMode;
	}

	public void setLoginMode(String loginMode) {
		this.loginMode = loginMode;
	}

	public String getMenuStyle() {
		return menuStyle;
	}

	public void setMenuStyle(String menuStyle) {
		this.menuStyle = menuStyle;
	}

	public String getValidFlag() {
		return validFlag;
	}

	public void setValidFlag(String validFlag) {
		this.validFlag = validFlag;
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

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public String getSystemOwner() {
		return systemOwner;
	}

	public void setSystemOwner(String systemOwner) {
		this.systemOwner = systemOwner;
	}
	
}
