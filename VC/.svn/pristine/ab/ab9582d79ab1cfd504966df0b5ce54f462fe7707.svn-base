package com.tapi.tcs.vc.schema.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.struts2.json.annotations.JSON;



/**
 * 菜单管理
 * 
 */
@Entity
@Table(name = "VC_MENU_DEF")
@SequenceGenerator(name="SEQ_MENU_DEF",sequenceName="SEQ_MENU_DEF",allocationSize=1)

public class VcMenuDef implements Serializable {

	private static final long serialVersionUID = -3098939002139059255L;
	/**菜单ID**/
	@Id
	@Column(name = "MENU_ID", unique = true, nullable = false, precision=10,scale=0)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_MENU_DEF")
	private Long menuId;
	
	/**菜单名称**/
	@Column(name = "MENU_NAME", nullable = false)
	private String menuName;
	
	/**父菜单ID**/
	@Column(name = "PARENT_ID", nullable = false, precision=10,scale=0)
	private Long parentId;
	
	/**菜单索引**/
	@Column(name = "MENU_INDEX", nullable = false, precision=10,scale=0)
	private Long menuIndex;
	
	/**系统Code**/
	
	@Column(name="SYSTEM_CODE")
	private String systemCode;
	
	/**菜单URL**/
	@Column(name = "MENU_URL")
	private String menuUrl;
	
	/**创建人**/
	@Column(name = "CREATED_BY", updatable=false)
	private String createdBy;
	
	/**创建日期**/
	@Column(name = "DATE_CREATED", updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;
	
	
	/**更新人**/
	@Column(name = "UPDATE_BY")
	private String updateBy;
	
	/**更新时间**/
	@Column(name = "DATE_UPDATED")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateUpdated;
	
	/**有效标志**/
	@Column(name = "VALID_FLAG", length = 1)
	private String validFlag;
	
	
	public Long getMenuId() {
		return this.menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	
	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	
	public Long getParentId() {
		return this.parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	
	public Long getMenuIndex() {
		return this.menuIndex;
	}

	public void setMenuIndex(Long menuIndex) {
		this.menuIndex = menuIndex;
	}

	
	public String getMenuUrl() {
		return this.menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	
	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	
	public Date getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	
	public String getUpdateBy() {
		return this.updateBy;
	}
	
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	
	
	public Date getDateUpdated() {
		return this.dateUpdated;
	}
	
	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}
	
	
	public String getValidFlag() {
		return this.validFlag;
	}

	public void setValidFlag(String validFlag) {
		this.validFlag = validFlag;
	}

	public String getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}
	
}
