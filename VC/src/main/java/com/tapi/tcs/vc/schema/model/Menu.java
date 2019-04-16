package com.tapi.tcs.vc.schema.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 菜单模型
 * 
 * @author 廉超
 * @date 2013-02-25
 * 
 */
@Entity
@Table(name = "Menu")
@SequenceGenerator(name = "SEQ_MENU", sequenceName = "SEQ_MENU", allocationSize = 1)
public class Menu implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 5147036291391168701L;

	/** 菜单ID*/
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MENU")
	private Long id;

	/** 菜单名 */
	private String menuName;

	/** 父节点 */
	private String parentNode;

	/** 是否子菜单 */
	private String isChild;

	/** 菜单排列序列号 */
	
	private Long viewSeq;

	/** 对应资源信息 */
	@OneToOne(cascade = { CascadeType.ALL }, targetEntity = SysResource.class)
	@JoinColumn(name = "RESOURCEID", nullable = false)
	private SysResource sysResource;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}


	public String getParentNode() {
		return parentNode;
	}

	public void setParentNode(String parentNode) {
		this.parentNode = parentNode;
	}

	public String getIsChild() {
		return isChild;
	}

	public void setIsChild(String isChild) {
		this.isChild = isChild;
	}

	public Long getViewSeq() {
		return viewSeq;
	}

	public void setViewSeq(Long viewSeq) {
		this.viewSeq = viewSeq;
	}

	public SysResource getSysResource() {
		return sysResource;
	}

	public void setSysResource(SysResource sysResource) {
		this.sysResource = sysResource;
	}

	
}
