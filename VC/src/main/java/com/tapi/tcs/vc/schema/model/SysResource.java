/**
 * 
 */
package com.tapi.tcs.vc.schema.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 资源模型
 * 
 * @author liancaho
 * @date 2013-02-25
 * 
 */
@Entity
@Table(name = "SysResource")
@SequenceGenerator(name = "SEQ_SYSRESOUCE", sequenceName = "SEQ_SYSRESOUCE", allocationSize = 1)
public class SysResource implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4258291443433083001L;

	/** 资源标识 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SYSRESOUCE")
	private Long id;

	/** 资源代码 */
	private String resourceId;

	/** 资源名称 */
	private String resouceName;

	/** 描述 */
	private String description;

	/** 联默认URL */
	private String url;

	/** 资源类型 */
	private String resourceType;

	/** 页面ID */
	private String jspId;

	/** 所属服务 */
	private String serverAddress;

	/** 删除标记 */
	private String isDelete;

	/** 创建用户 */
	private String createUser;

	/** 创建时间 */
	private Date createDate;

	/** 修改用户 */
	private String modifyUser;

	/** 修改日期 */
	private Date modifyDate;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}


	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getResouceName() {
		return resouceName;
	}

	public void setResouceName(String resouceName) {
		this.resouceName = resouceName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public String getJspId() {
		return jspId;
	}

	public void setJspId(String jspId) {
		this.jspId = jspId;
	}

	public String getServerAddress() {
		return serverAddress;
	}

	public void setServerAddress(String serverAddress) {
		this.serverAddress = serverAddress;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}



}
