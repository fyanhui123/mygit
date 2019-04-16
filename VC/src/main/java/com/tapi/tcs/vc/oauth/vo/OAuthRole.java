package com.tapi.tcs.vc.oauth.vo;

import java.io.Serializable;

public class OAuthRole implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7369018169938482289L;
	private String roleCode;
	private String roleName;
	private String state;
	
	
	public OAuthRole() {
		super();
	}
	public OAuthRole(String roleCode, String roleName, String state) {
		super();
		this.roleCode = roleCode;
		this.roleName = roleName;
		this.state = state;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "OAuthRole [roleCode=" + roleCode + ", roleName=" + roleName + ", state=" + state + "]";
	}

}
