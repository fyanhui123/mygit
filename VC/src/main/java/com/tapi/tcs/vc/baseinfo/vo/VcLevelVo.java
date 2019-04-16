package com.tapi.tcs.vc.baseinfo.vo;

/**
 * 级别设置表VO
 * <p>
 * Date 2013-05-06
 * </p>
 * <p>
 * Module：机构设置
 * </p>
 * <p>
 * Description：
 * </p>
 * <p>
 * Remark：
 * </p>
 * @author chy
 * @version 1.0
 */
public class VcLevelVo{
	
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
	/**上级机构代码*/
	private String comCode;
	/**上级机构名称*/
	private String comName;
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
	/**装载单证管理员还是发票管理员*/
	private String  invoiceFlag;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUnitType() {
		return unitType;
	}
	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}
	public String getUnitCode() {
		return unitCode;
	}
	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public Long getParentOrgId() {
		return parentOrgId;
	}
	public void setParentOrgId(Long parentOrgId) {
		this.parentOrgId = parentOrgId;
	}
	public String getComCode() {
		return comCode;
	}
	public void setComCode(String comCode) {
		this.comCode = comCode;
	}
	public String getComName() {
		return comName;
	}
	public void setComName(String comName) {
		this.comName = comName;
	}
	public String getComType() {
		return comType;
	}
	public void setComType(String comType) {
		this.comType = comType;
	}
	public Integer getLevelNo() {
		return levelNo;
	}
	public void setLevelNo(Integer levelNo) {
		this.levelNo = levelNo;
	}
	public Integer getManageLevel() {
		return manageLevel;
	}
	public void setManageLevel(Integer manageLevel) {
		this.manageLevel = manageLevel;
	}
	public String getValidStatus() {
		return validStatus;
	}
	public void setValidStatus(String validStatus) {
		this.validStatus = validStatus;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getLevelCode() {
		return levelCode;
	}
	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}
	public Integer getDisplayNo() {
		return displayNo;
	}
	public void setDisplayNo(Integer displayNo) {
		this.displayNo = displayNo;
	}
	public String getInvoiceFlag() {
		return invoiceFlag;
	}
	public void setInvoiceFlag(String invoiceFlag) {
		this.invoiceFlag = invoiceFlag;
	}
	
}
