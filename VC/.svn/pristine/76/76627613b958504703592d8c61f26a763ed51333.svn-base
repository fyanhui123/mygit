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
 * 用户与角色关联实体
 * 
 */
@Entity
@Table(name = "vc_USER_ROLE")
@SequenceGenerator(name = "SEQ_USER_ROLE", sequenceName = "SEQ_USER_ROLE", allocationSize = 1)

public class VcUserRole implements Serializable  {
	
	private static final long serialVersionUID = 4127131356099421485L;	
    
	@Id		
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USER_ROLE")
	@Column(name = "ID_USER_ROLE", unique = true, precision=10,scale=0)
	private Long id;
	
	/** 对应角色信息 */
    @Column(name = "ROLE_ID", unique = true)	
	private String roleId;
	
	/** 对应用户信息 */
	@Column(name = "EMPLOYEE_ID")	
	private String employeeId;
	
	/** 是否有效标志  */
	@Column(name = "VALID_FLAG",length = 1)
	private String validFlag;
	
	@Column(name = "CREATED_BY", updatable = false)
	private String createdBy;
	
	@Column(name = "DATE_CREATED", updatable= false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;
	
	@Column(name = "UPDATE_BY")
	private String updateBy;
	
	@Column(name = "DATE_UPDATED")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateUpdated;	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}	


	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
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

		
	
}
