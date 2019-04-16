package com.tapi.tcs.vc.vcmenu.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.web.action.TFAction;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.baseinfo.service.VcLevelService;
import com.tapi.tcs.vc.common.schema.vo.UserInfo;
import com.tapi.tcs.vc.schema.model.SystemDef;
import com.tapi.tcs.vc.schema.model.VcMenuDef;
import com.tapi.tcs.vc.schema.model.VcRoleDef;
import com.tapi.tcs.vc.vcmenu.dto.Constants;
import com.tapi.tcs.vc.vcmenu.dto.DTreeDto;
import com.tapi.tcs.vc.vcmenu.service.VcMenuService;

@SuppressWarnings("serial")
public class MenuAction extends TFAction {
	/** 用户代码 */
	private String userCode;

	/** 用户名称 */
	private String userName;

	/** 机构代码 */
	private String comCode;

	/** 机构名称 */
	private String comName;

	private List<VcRoleDef> roleDefList;
	private VcMenuService vcMenuService;
	private VcRoleDef roleDef;
	private List<DTreeDto> dtreeDtoList;
	private Set<VcMenuDef> menuDefs;
	private VcMenuDef menuDef;
	private List<VcMenuDef> menuDefList;

	/**
	 * 初始化菜单
	 * 
	 * @return
	 */
	// private VcLevelService vcLevelService;

	public String queryMenu() {
		logger.info("访问/vcMenu/queryMenu.do初始化Ztree菜单...");
		return "success";
	}

	public String queryRoleMenu() {
		logger.info("访问/vcMenu/queryRoleMenu.do资源管理页面");
		return "success";
	}

	/**
	 * 分页查询系统定义信息
	 */
	@SuppressWarnings("unchecked")
	public String queryRoleDefByPages() throws Exception {
		Page pageObj = vcMenuService
				.queryRoleDef(this.getRoleDef(), page, rows);
		roleDefList = (List<VcRoleDef>) pageObj.getResult();
		total = (int) pageObj.getTotalPageCount();
		records = (int) pageObj.getTotalCount();
		return SUCCESS;
	}

	/*
	 * 根据RoleId查询菜单，取Role角色所属系统的所有菜单
	 * 
	 * @author hyihong @date 2013-03-18
	 */
	public String queryDTreeDtosOfValid() throws Exception {
		String roleId = this.getRequest().getParameter("roleId");
		logger.info("roleId-->" + roleId);
		dtreeDtoList = new ArrayList<DTreeDto>();
		List<VcMenuDef> list = vcMenuService.queryMenuDefsOfValid("VC");
		List<SystemDef> sysList = vcMenuService.querySystemDefsOfValid("VC");
		for (SystemDef systemDef : sysList) {
			DTreeDto dTreeDtoOfSys = new DTreeDto();
			dTreeDtoOfSys.setId(Constants.SYS_PREFIX
					+ systemDef.getSystemCode());
			dTreeDtoOfSys.setInfo(systemDef.getChineseName());
			dTreeDtoOfSys.setPid("-1");
			dTreeDtoOfSys.setValue(systemDef.getSystemCode());
			dtreeDtoList.add(dTreeDtoOfSys);
		}
		for (VcMenuDef menuDef : list) {
			DTreeDto dTreeDto = new DTreeDto();
			dTreeDto.setId(Constants.MENU_PREFIX
					+ menuDef.getMenuId().toString());
			if (menuDef.getParentId() == -1) {
				dTreeDto.setPid(Constants.SYS_PREFIX + menuDef.getSystemCode());
			} else {
				dTreeDto.setPid(Constants.MENU_PREFIX + menuDef.getParentId());
			}
			dTreeDto.setInfo(menuDef.getMenuName());
			dTreeDto.setValue(menuDef.getMenuId().toString());
			dtreeDtoList.add(dTreeDto);
		}
		menuDefs = vcMenuService.getMenuDefsByRoleId(roleId);
		return SUCCESS;
	}

	public String savedistributeMenuDef() throws Exception {
		UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute(
				"userInfo");
		if (userInfo == null) {
			throw new BusinessException("登录超时，请重新登录！ ");
		}
		String menuIds[] = getRequest().getParameterValues("menuId");
		String roleId = getRequest().getParameter("roleId");
		vcMenuService.executeDistributeMenuDef(roleId, menuIds,
				userInfo.getUserCode());
		return SUCCESS;// 修改完成到成功页面
	}

	/**
	 * 菜单维护的机构树加载
	 * 
	 * @return
	 * @throws Exception
	 */
	public String queryDTreeDtosBySystemCode() throws Exception {
		String systemCode = this.getRequest().getParameter("systemCode");
		logger.info("systemCode:" + systemCode);
		List<SystemDef> systemDef = vcMenuService.querySystemDefsOfValid("VC");
		DTreeDto dTreeDtoOfSys = new DTreeDto();
		dTreeDtoOfSys.setId(Constants.SYS_PREFIX
				+ systemDef.get(0).getSystemCode());
		dTreeDtoOfSys.setPid("-1");
		dTreeDtoOfSys.setInfo(systemDef.get(0).getChineseName());
		dTreeDtoOfSys.setValue(systemDef.get(0).getSystemCode());
		// 查询出当前有效的所有单证菜单
		List<VcMenuDef> list = vcMenuService.queryMenuDefsOfValid("VC");
		dtreeDtoList = new ArrayList<DTreeDto>();
		dtreeDtoList.add(dTreeDtoOfSys);
		for (VcMenuDef menuDef : list) {
			DTreeDto dTreeDto = new DTreeDto();
			dTreeDto.setId(Constants.MENU_PREFIX + menuDef.getMenuId());
			if (menuDef.getParentId() == -1) {
				dTreeDto.setPid(Constants.SYS_PREFIX
						+ systemDef.get(0).getSystemCode());
			} else {
				dTreeDto.setPid(Constants.MENU_PREFIX + menuDef.getParentId());
			}
			dTreeDto.setInfo(menuDef.getMenuName());
			dTreeDto.setValue(menuDef.getMenuId().toString());
			dtreeDtoList.add(dTreeDto);
		}
		return SUCCESS;
	}

	/**
	 * 新增菜单定义信息
	 * 
	 * @author pengzhaosong
	 * @date 2013-03-22
	 * 
	 */
	public String saveMenuDef() throws Exception {
		UserInfo userInfo = (UserInfo) getRequest().getSession().getAttribute(
				"userInfo");
		logger.info("menuId:" + menuDef.getMenuId() + ",menuName="
				+ menuDef.getMenuName());
		if (null != menuDef) {
			if (menuDef.getMenuId() != null) {
				menuDef.setUpdateBy(userInfo.getUserCode());
				menuDef.setDateUpdated(new Date());
				menuDef.setSystemCode("VC");
			} else {
				menuDef.setSystemCode("VC");
				menuDef.setCreatedBy(userInfo.getUserCode());
				menuDef.setDateCreated(new Date());
				menuDef.setUpdateBy(userInfo.getUserCode());
				menuDef.setDateUpdated(new Date());
				menuDef.setValidFlag(Constants.VALID_FLAG_Y);
			}
			vcMenuService.createOrUpdateMenuDef(menuDef);
		}
		return "success";
	}

	public String updateMenu() throws Exception {
		logger.info("进入到+/vcMenu/updateMenu.do");
		return "success";
	}

	/**
	 * 分页查询菜单列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String queryMenuDefByPages() throws Exception {
		Page pageObj = vcMenuService.queryMenuDef(menuDef, page, rows);
		menuDefList = (List<VcMenuDef>) pageObj.getResult();
		total = (int) pageObj.getTotalPageCount();
		records = (int) pageObj.getTotalCount();
		return SUCCESS;
	}

	/**
	 * 修改查询菜单信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String editMenuDef() throws Exception {
		String action = getRequest().getParameter("action");
		String menuId = getRequest().getParameter("id");
		logger.info("参数为id=" + menuId + "&action=loading");
		if ("loading".equals(action)) {
			logger.info("根据Id=" + menuId + "查询系统定义信息");
			menuDef = vcMenuService.getMenuDef(menuId);
			return "editMenuDef";// 到修改页面
		} else {
			return "viewMenuDef";// 修改完成到成功页面
		}
	}
	
	public String deleteMenuDef() throws Exception {
		String menuId = getRequest().getParameter("id");
		vcMenuService.deleteMenuDef(menuId);
		return "success";
	}
	
	public String restoreMenuDef() throws Exception {
		String menuId = getRequest().getParameter("id");
		vcMenuService.executeMenuDef(menuId);
		return "success";
	}
	
	public String queryDTreeDtos() throws Exception {
		return SUCCESS;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public VcMenuService getVcMenuService() {
		return vcMenuService;
	}

	public void setVcMenuService(VcMenuService vcMenuService) {
		this.vcMenuService = vcMenuService;
	}

	public List<VcRoleDef> getRoleDefList() {
		return roleDefList;
	}

	public void setRoleDefList(List<VcRoleDef> roleDefList) {
		this.roleDefList = roleDefList;
	}

	/*
	 * public VcLevelService getVcLevelService() { return vcLevelService; }
	 * 
	 * public void setVcLevelService(VcLevelService vcLevelService) {
	 * this.vcLevelService = vcLevelService; }
	 */
	public VcRoleDef getRoleDef() {
		return roleDef;
	}

	public void setRoleDef(VcRoleDef roleDef) {
		this.roleDef = roleDef;
	}

	public List<DTreeDto> getDtreeDtoList() {
		return dtreeDtoList;
	}

	public void setDtreeDtoList(List<DTreeDto> dtreeDtoList) {
		this.dtreeDtoList = dtreeDtoList;
	}

	public Set<VcMenuDef> getMenuDefs() {
		return menuDefs;
	}

	public void setMenuDefs(Set<VcMenuDef> menuDefs) {
		this.menuDefs = menuDefs;
	}

	public VcMenuDef getMenuDef() {
		return menuDef;
	}

	public void setMenuDef(VcMenuDef menuDef) {
		this.menuDef = menuDef;
	}

	public List<VcMenuDef> getMenuDefList() {
		return menuDefList;
	}

	public void setMenuDefList(List<VcMenuDef> menuDefList) {
		this.menuDefList = menuDefList;
	}

}
