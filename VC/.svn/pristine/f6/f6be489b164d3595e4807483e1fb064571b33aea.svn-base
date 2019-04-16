package com.tapi.tcs.vc.vcmenu.dao;

import java.util.List;
import java.util.Map;

import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.security.authority.client.MenuDTO;
import com.tapi.tcs.tf.security.authority.client.SystemDTO;
import com.tapi.tcs.vc.schema.model.PubUserDef;
import com.tapi.tcs.vc.schema.model.SystemDef;
import com.tapi.tcs.vc.schema.model.VcMenuDef;
import com.tapi.tcs.vc.schema.model.VcRoleDef;
import com.tapi.tcs.vc.schema.model.VcUserRole;

public interface VcMenuDao extends GenericDao <VcMenuDef> {
	/**
	 * 查询所有的子节点
	 * 
	 * @param vcMenuDef
	 * @return
	 * @throws DaoException
	 */
	// public List<VcMenuDef> queryMenuList(VcMenuDef vcMenuDef)throws
	// DaoException;
	/**
	 * 查询所有的菜单信息
	 * 
	 * @param vcMenuDef
	 * @return
	 * @throws DaoException
	 */
	public List<VcMenuDef> queryMenuListInfo(VcMenuDef vcMenuDef)
			throws DaoException;

	/**
	 * 查询出用户角色信息 根据自己的条件
	 * 
	 * @param map
	 * @return
	 */
	public List<VcUserRole> queryVcUserRole(Map<String, Object> map);

	/**
	 * 根据用户ID查询出所有的菜单列表
	 * 
	 * @param employeeId
	 * @return
	 * @throws DaoException
	 */
	public List<MenuDTO> queryMenuListInfo(String employeeId)
			throws DaoException;

	public List<SystemDTO> querySystemList(String employeeId)
			throws DaoException;

	public List<PubUserDef> quertUserDef(String employeeId) throws DaoException;

	/**
	 * 分页查询角色信息
	 */
	public Page queryRoleDef(VcRoleDef role, int currentPage, int pageNumber);

	/**
	 * 根据菜单ID列表查询菜单集合
	 */
	public List<VcMenuDef> querySystemMenuByMenuIdList(Long[] menuId,
			String systemCode);

	/**
	 * 查询有效菜单集合
	 */
	public List<VcMenuDef> queryMenuDefsOfValid(String systemCode)
			throws DaoException;

	/**
	 * 根据此角色ID查询出有那些菜单
	 */
	public List<VcMenuDef> queryMenuListByRoleId(String roleId)
			throws DaoException;

	/**
	 * 查询有效的系统数据
	 * */
	public List<SystemDef> querySystemDefsOfValid(String systemCode)
			throws DaoException;


	public Page queryMenuDef(VcMenuDef menuDef, int currentPage, int pageNumber);
	
	public List<VcMenuDef> getMenuDef(String menuId)throws DaoException;
	/**
	 * 查询出所有子菜单
	 * @param menuId
	 * @return
	 * @throws DaoException
	 */
	public List<String> queryAllChildMenuDef(String menuId)throws DaoException;
	
	
	/**
	 * 查询出所有父菜单
	 * @param menuId
	 * @return
	 * @throws DaoException
	 */
	public List<String> queryAllParentMenuDef(String menuId)throws DaoException;
	
}
