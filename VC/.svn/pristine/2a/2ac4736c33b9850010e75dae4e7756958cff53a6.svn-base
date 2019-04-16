package com.tapi.tcs.vc.schema.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 角色与菜单关联实体
 * 
 */
@Entity
@Table(name = "VC_ROLE_MENU")
@SequenceGenerator(name = "SEQ_ROLE_MENU", sequenceName = "SEQ_ROLE_MENU", allocationSize = 1)
public class VcRoleMenu implements Serializable {

	private static final long serialVersionUID = -8233900203923454491L;
	@Id
	@Column(name="ID_ROLE_MENU",unique=true, precision=10, scale=0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ROLE_MENU")
	private Long id;
	
	/** 对应角色信息 */
	@Column(name = "ROLE_ID")	
	private String role;
	
	/** 对应角色信息 */
	@Column(name = "MENU_ID")
	private String menu;

	/** 创建人 */
	@Column(name = "CREATED_BY",updatable=false)
	private String createdBy;
	
	/** 创建时间 */
	@Column(name = "DATE_CREATED",updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;
	
	/** 修改人 */
	@Column(name = "UPDATE_BY")
	private String updateBy;
	
	/** 修改时间 */
	@Column(name = "DATE_UPDATED")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateUpdated;
	
	/** 是否有效标志 */
	@Column(name = "VALID_FLAG")
	private String validFlag;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}


	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
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

	public String getValidFlag() {
		return validFlag;
	}

	public void setValidFlag(String validFlag) {
		this.validFlag = validFlag;
	}	
	
	
}
