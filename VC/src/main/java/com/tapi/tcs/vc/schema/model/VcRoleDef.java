package com.tapi.tcs.vc.schema.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.struts2.json.annotations.JSON;

/**
 * 角色管理
 * 
 */
@Entity
@Table(name = "VC_ROLE_DEF")
public class VcRoleDef implements Serializable  {
	
	private static final long serialVersionUID = 4127131356099421485L;

	/** 系统CODE */
	@Id	
	@Column(name = "ROLE_ID",unique=true, precision=10,scale=0)
	private Long roleId;

	/** 角色名称 */
	@Column(name = "ROLE_NAME")
	private String roleName;
	@Column(name = "ROLE_DESC")
	private String roleDesc;
	
	@Column(name = "CREATED_BY", updatable=false)
	private String createdBy;
	
	@Column(name = "DATE_CREATED", updatable=false)
	private Date dateCreated;
	
	@Column(name = "UPDATE_BY")
	private String updateBy;
	
	@Column(name = "DATE_UPDATED")
	private Date dateUpdated;
	
	/** 是否有效标志  */
	@Column(name = "VALID_FLAG",length = 1)
	private String validFlag;
	
	
	/**角色级别*/
	@Column(name="ROLE_LEVEL")
	private String roleLevel;
	/*
	// /**角色拥有的用户*	
	@OneToMany(mappedBy="role",cascade={CascadeType.MERGE}, targetEntity=VcUserRole.class,fetch=FetchType.LAZY)	
	private Set<VcUserRole> vcUserRoles;*/
	
	/**角色拥有的用户**//*	
	@OneToMany(mappedBy="role",cascade={CascadeType.MERGE}, 
			targetEntity=VcRoleMenu.class,fetch=FetchType.LAZY)	
	private Set<VcRoleMenu> vcRoleMenus;
	
	//**角色拥有的用户组**//*	
	@OneToMany(mappedBy="roleDef",cascade={CascadeType.MERGE}, 
			targetEntity=UserGroupRole.class,fetch=FetchType.LAZY)	
	private Set<UserGroupRole> userGroupRoles;
	
	
	*//**角色拥有的菜单**//*
	@ManyToMany(cascade={CascadeType.MERGE},fetch=FetchType.LAZY)
	@JoinTable(name="UM_ROLE_MENU",joinColumns=@JoinColumn(name="role_id"),inverseJoinColumns=@JoinColumn(name="menu_id"))
	private Set<VcMenuDef> vcMenuDefs;	
	
	*//**角色拥有的岗位*//*
	@OneToMany(mappedBy="role",cascade={CascadeType.MERGE}, 
			targetEntity=PostRole.class,fetch=FetchType.LAZY)
	private Set<PostRole> postRoles;
	
	@JSON(serialize=false)
	public Set<PostRole> getPostRoles() {
		return postRoles;
	}

	public void setPostRoles(Set<PostRole> postRoles) {
		this.postRoles = postRoles;
	}

	public Set<UserDef> getUserDefs(){
		return null;
	}
	
	/**
	 * @return the id
	 */
	public Long getRoleId() {
		return roleId;
	}

	/**
	 * @param id
	 */
	public void setRoleId(Long id) {
		this.roleId = id;
	}
	
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
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
	
	public String getRoleLevel() {
		return roleLevel;
	}

	public void setRoleLevel(String roleLevel) {
		this.roleLevel = roleLevel;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	
}
