package com.tapi.tcs.vc.vcmenu.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.security.authority.client.MenuDTO;
import com.tapi.tcs.vc.schema.model.PubUserDef;
import com.tapi.tcs.vc.schema.model.SystemDef;
import com.tapi.tcs.vc.schema.model.VcMenuDef;
import com.tapi.tcs.vc.schema.model.VcRoleDef;
import com.tapi.tcs.vc.vcmenu.dao.VcMenuDao;
import com.tapi.tcs.vc.vcmenu.dao.VcRoleMenuDao;
import com.tapi.tcs.vc.vcmenu.dto.Constants;
import com.tapi.tcs.vc.vcmenu.service.VcMenuService;

public class VcMenuServiceimpl implements VcMenuService {
	public VcMenuDao vcMenuDao;
	public VcRoleMenuDao vcRoleMenuDao;

	/*	*//**
	 * 树形菜单初始化
	 * 
	 * @throws BusinessException
	 */
	/*
	 * @Override public List<ZTreeDto> queryOrgZTreeList(String rootOrgCode, int
	 * minLevel, String onlyOrgFlag) throws BusinessException { List<ZTreeDto>
	 * zTreeDtoList = new ArrayList<ZTreeDto>(); try { VcMenuDef c=new
	 * VcMenuDef(); List<VcMenuDef> listMenuDef =vcMenuDao.queryMenuList(c); }
	 * catch (Exception e) { e.printStackTrace(); } return zTreeDtoList; }
	 */
	@Override
	public List<MenuDTO> queryMenu(String employeeId) throws BusinessException {
		List<MenuDTO> queryVcUserRole;
		try {
			queryVcUserRole = vcMenuDao.queryMenuListInfo(employeeId);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage(), e);
		}
		return queryVcUserRole;
	}

	@Override
	public List<PubUserDef> quertUserDef(String employeeId)
			throws BusinessException {
		List<PubUserDef> list = null;
		try {
			list = vcMenuDao.quertUserDef(employeeId);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage(), e);
		}
		return list;
	}

	@Override
	public Page queryRoleDef(VcRoleDef role, int currentPage, int pageNumber)
			throws BusinessException {
		return vcMenuDao.queryRoleDef(role, currentPage, pageNumber);
	}

	@Override
	public Set<VcMenuDef> getMenuDefsByRoleId(String roleId)
			throws BusinessException {
		if (roleId == null) {
			BusinessException be = new BusinessException("无此角色定义信息！");
			be.setModule("根据系统CODE获取去菜单对象");
			throw be;
		}
		List<VcMenuDef> list = vcMenuDao.queryMenuListByRoleId(roleId);
		Set<VcMenuDef> menuSet = new HashSet<VcMenuDef>();
		CollectionUtils.addAll(menuSet, list.toArray());
		return menuSet;
	}

	@Override
	public void createOrUpdateMenuDef(VcMenuDef vcMenuDef)
			throws BusinessException {
		try {
			vcMenuDao.save(vcMenuDef);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}

	@Override
	public List<SystemDef> querySystemDefsOfValid(String systemCode)
			throws BusinessException {
		return vcMenuDao.querySystemDefsOfValid(systemCode);
	}

	@Override
	public List<VcMenuDef> queryMenuDefsOfValid(String queryMenuDefsOfValid)
			throws BusinessException {
		return vcMenuDao.queryMenuDefsOfValid(queryMenuDefsOfValid);
	}

	@Override
	public void executeDistributeMenuDef(String roleId, String[] menuIds,
			String userCode) throws BusinessException {
		vcRoleMenuDao.saveMenuList(roleId, menuIds, userCode);
	}

	public void setVcMenuDao(VcMenuDao vcMenuDao) {
		this.vcMenuDao = vcMenuDao;
	}

	public void setVcRoleMenuDao(VcRoleMenuDao vcRoleMenuDao) {
		this.vcRoleMenuDao = vcRoleMenuDao;
	}

	@Override
	public Page queryMenuDef(VcMenuDef menuDef, int currentPage, int pageNumber)
			throws BusinessException {
		return vcMenuDao.queryMenuDef(menuDef, currentPage, pageNumber);
	}

	@Override
	public VcMenuDef getMenuDef(String menuId) throws BusinessException {
		return vcMenuDao.getMenuDef(menuId).get(0);
	}

	@Override
	public void deleteMenuDef(String menuIds) throws BusinessException {
		String[] arrayMenuId = menuIds.split(",");
		for (String menuId : arrayMenuId) {
			List<String> list = vcMenuDao.queryAllChildMenuDef(menuId);
			for (String mId : list) {
				VcMenuDef menuDef = vcMenuDao.getMenuDef(mId).get(0);
				menuDef.setValidFlag(Constants.VALID_FLAG_N);
				vcMenuDao.save(menuDef);
			}
		}
	}

	@Override
	public void executeMenuDef(String menuIds) throws BusinessException {
		String[] arrayMenuId = menuIds.split(",");
		for (String menuId : arrayMenuId) {
			List<String> list = vcMenuDao.queryAllParentMenuDef(menuId);
			for (String mId : list) {
				VcMenuDef menuDef = vcMenuDao.getMenuDef(mId).get(0);
				menuDef.setValidFlag(Constants.VALID_FLAG_Y);
				vcMenuDao.save(menuDef);
			}
		}
	}
}
