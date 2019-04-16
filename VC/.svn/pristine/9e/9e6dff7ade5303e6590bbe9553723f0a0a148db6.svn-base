package com.tapi.tcs.vc.vcmenu.service;

import java.util.List;
import java.util.Set;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.security.authority.client.MenuDTO;
import com.tapi.tcs.vc.schema.model.PubUserDef;
import com.tapi.tcs.vc.schema.model.SystemDef;
import com.tapi.tcs.vc.schema.model.VcMenuDef;
import com.tapi.tcs.vc.schema.model.VcRoleDef;

public interface VcMenuService {
	/*
	 * public List<ZTreeDto> queryOrgZTreeList(String rootOrgCode, int minLevel,
	 * String onlyOrgFlag) throws BusinessException;
	 */
	// /
	/**
	 * 根据用户id查询此用户所拥有的角色所有的菜单
	 * 
	 */
	public List<MenuDTO> queryMenu(String employeeId) throws BusinessException;

	public List<PubUserDef> quertUserDef(String employeeId)
			throws BusinessException;

	public Page queryRoleDef(VcRoleDef role, int currentPage, int pageNumber)
			throws BusinessException;

	public Set<VcMenuDef> getMenuDefsByRoleId(String roleId)
			throws BusinessException;

	public List<VcMenuDef> queryMenuDefsOfValid(String queryMenuDefsOfValid)
			throws BusinessException;

	/**
	 * 查询有效的系统数据
	 * */
	public List<SystemDef> querySystemDefsOfValid(String system)
			throws BusinessException;

	public void executeDistributeMenuDef(String roleId, String menuIds[],
			String userCode) throws BusinessException;

	public void createOrUpdateMenuDef(VcMenuDef vcMenuDef)
			throws BusinessException;

	public Page queryMenuDef(VcMenuDef menuDef, int currentPage, int pageNumber)
			throws BusinessException;
	
	public VcMenuDef getMenuDef(String menuId)throws BusinessException;
	
	/**
	 * 根据id删除菜单及子菜单
	 * @param menuIds
	 * @throws BusinessException
	 */
	public void deleteMenuDef(String menuIds) throws BusinessException;
	
	
	/**
	 * 根据id恢复父菜单
	 * @param menuIds
	 * @throws BusinessException
	 */
	public void executeMenuDef(String menuIds) throws BusinessException;
}
