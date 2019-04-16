package com.tapi.tcs.vc.vcmenu.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Hibernate;
import org.hibernate.Query;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.security.authority.client.MenuDTO;
import com.tapi.tcs.tf.security.authority.client.SystemDTO;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.vc.schema.model.PubUserDef;
import com.tapi.tcs.vc.schema.model.SystemDef;
import com.tapi.tcs.vc.schema.model.VcMenuDef;
import com.tapi.tcs.vc.schema.model.VcRoleDef;
import com.tapi.tcs.vc.schema.model.VcUserRole;
import com.tapi.tcs.vc.vcmenu.dao.VcMenuDao;
import com.tapi.tcs.vc.vcmenu.dto.Constants;

public class VcMenuDaoImpl extends GenericDaoHibernate<VcMenuDef> implements
		VcMenuDao {
	/**
	 * 查询数所有父节点的子菜单
	 * 
	 * @throws DaoException
	 */
	@Override
	public List<VcMenuDef> queryMenuListInfo(VcMenuDef vcMenuDef)
			throws DaoException {
		List<VcMenuDef> listVcMenuDef = new ArrayList<VcMenuDef>();
		List<Object> values = new ArrayList<Object>();
		try {
			StringBuffer sb = new StringBuffer(
					"select t.menu_id,t.menu_name,t.parent_id,t.menu_index,t.system_code,t.menu_url,t.valid_flag from vc_menu_def t where 1=1 ");
			if (vcMenuDef.getMenuId() > 0) {
				sb.append(" and t.menu_id=? ");
				values.add(vcMenuDef.getMenuId());
			}
			if (StringUtils.isEmpty(vcMenuDef.getMenuName())) {
				sb.append(" t.menu_name like ? ");
				values.add("%" + vcMenuDef.getMenuId() + "%");
			}
			List list = this.findBySql(sb.toString(), values.toArray());
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					VcMenuDef menuDef = null;
					Object[] obj = (Object[]) list.get(i);
					menuDef = new VcMenuDef();
					menuDef.setMenuId((Long) obj[1]);
					menuDef.setMenuName((String) obj[2]);
					menuDef.setParentId((Long) obj[3]);
					menuDef.setMenuIndex((Long) obj[4]);
					menuDef.setSystemCode((String) obj[5]);
					listVcMenuDef.add(menuDef);
				}
			}
		} catch (Exception e) {
			throw new DaoException("菜单查询数据库失败！", e);
		}
		return listVcMenuDef;
	}

	@Override
	public List<VcUserRole> queryVcUserRole(Map<String, Object> map) {
		StringBuffer sb = new StringBuffer("from VcUserRole v where 1=1 ");
		List<Object> values = new ArrayList<Object>();
		for (String key : map.keySet()) {
			if (map.get(key) != null) {
				sb.append(" and v." + key + "=?");
				values.add(map.get(key));
			}
		}
		return this.findByHql(sb.toString(), values.toArray());
	}

	@Override
	public List<MenuDTO> queryMenuListInfo(String employeeId)
			throws DaoException {
		List<MenuDTO> listVcMenuDefs = new ArrayList<MenuDTO>();
		try {
			List<Object> values = new ArrayList<Object>();
			StringBuffer sb = new StringBuffer(
					//"select t2.menu_id,t2.menu_name,t2.parent_id,t2.menu_index,t2.menu_url,t.valid_flag from vc_role_menu t,vc_menu_def t2 where t.role_id in(select t1.role_id from vc_user_role t1 where t1.employee_id= ?) and t.menu_id=t2.menu_id and t.valid_flag='1' and t2.valid_flag='1' and t2.system_code='VC' ");
					   "select t2.menu_id, t2.menu_name, t2.parent_id, t2.menu_index,t2.menu_url, t2.valid_flag from vc_menu_def t2 where t2.menu_id in (select distinct (t.menu_id) from vc_role_menu t  where t.role_id in (select t1.role_id from vc_user_role t1 where t1.employee_id = ? and t1.valid_flag='1')and t.valid_flag='1') and t2.valid_flag = '1'and t2.system_code = 'VC' order by t2.menu_index asc ");
					values.add(employeeId);
			List list = this.findBySql(sb.toString(), values.toArray());
			if (list.size() > 0 && null != list) {
				MenuDTO vcMenuDef = null;
				for (int i = 0; i < list.size(); i++) {
					Object[] obj = (Object[]) list.get(i);
					vcMenuDef = new MenuDTO();
					BigDecimal c0 = new BigDecimal(obj[0].toString());
					BigDecimal c2 = new BigDecimal(obj[2].toString());
					BigDecimal c3 = new BigDecimal(obj[3].toString());
					vcMenuDef.setMenuID(c0.toString());
					vcMenuDef.setMenuName((String) obj[1]);
					vcMenuDef.setParentID(c2.toString());
					vcMenuDef.setUrl((String) obj[4]);
					String url = (String) obj[4];
					if (null != url) {
						vcMenuDef.setIsChild(StringUtils.isBlank(url) ? "N"
								: "Y");// 是否有子菜单
					}
					vcMenuDef.setSeqNo(c3.intValue());
					listVcMenuDefs.add(vcMenuDef);
				}
			}
		} catch (Exception e) {
			throw new DaoException("查询数据库失败！", e);
		}
		return listVcMenuDefs;
	}

	/**
	 * 根据用户信息查询出可以登陆的系统
	 */
	@Override
	public List<SystemDTO> querySystemList(String employeeId)
			throws DaoException {
		List<SystemDTO> listVcMenuDef = new ArrayList<SystemDTO>();
		List<Object> values = new ArrayList<Object>();
		try {
			StringBuffer sb = new StringBuffer(
					"select t3.system_code, t3.chinese_name,t3.english_name,t3.menu_style,t3.system_owner, t3.url from vc_system_def t3 where t3.system_code in( select t2.system_code from vc_menu_def t2 where t2.menu_id in (select t1.menu_id  from vc_role_menu t1  where t1.role_id in (select t.role_id from vc_user_role t where t.employee_id = ?))  group by t2.system_code)");
			values.add(employeeId);
			List list = this.findBySql(sb.toString(), values.toArray());
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					SystemDTO systemDef = null;
					Object[] obj = (Object[]) list.get(i);
					systemDef = new SystemDTO();
					systemDef.setSystemCode((String) obj[0]);
					systemDef.setSystemCName((String) obj[1]);
					systemDef.setSystemEName((String) obj[2]);
					systemDef.setMenuStyle((String) obj[3]);
					systemDef.setSystemManager((String) obj[4]);
					systemDef.setSystemURL((String) obj[5]);
					listVcMenuDef.add(systemDef);
				}
			}
		} catch (Exception e) {
			throw new DaoException("菜单查询数据库失败！", e);
		}
		return listVcMenuDef;
	}

	@Override
	public List<PubUserDef> quertUserDef(String employeeId) throws DaoException {
		QueryRule queryRule = null;
		try {
			queryRule = QueryRule.getInstance();
		} catch (Exception e) {
			throw new DaoException("QueryRule拼接异常！", e);
		}
		if (StringUtils.isNotBlank(employeeId)) {
			queryRule.addEqual("employeeId", employeeId);
		}
		queryRule.addEqual("validFlag", '1');
		return this.find(PubUserDef.class, queryRule);
	}

	@Override
	public Page queryRoleDef(VcRoleDef role, int currentPage, int pageNumber) {
		QueryRule queryRule = QueryRule.getInstance();
		if (role != null) {
			if (StringUtils.isNotBlank(role.getRoleName())) {
				queryRule.addLike("roleName", "%" + role.getRoleName() + "%");
			}
			if (StringUtils.isNotBlank(role.getValidFlag())) {
				queryRule.addEqual("validFlag", role.getValidFlag());
			}
			queryRule.addDescOrder("roleId");
		}
		return this.find(VcRoleDef.class, queryRule, currentPage, pageNumber);
	}

	@Override
	public List<VcMenuDef> querySystemMenuByMenuIdList(Long[] menuId,
			String systemCode) {
		QueryRule queryRule = QueryRule.getInstance();
		if (menuId != null && menuId.length > 0) {
			queryRule.addIn("menuId", menuId);
		}
		/*
		 * if(StringUtils.isNotBlank(systemCode)) {
		 * queryRule.addEqual("systemDef.systemCode", systemCode); }
		 */
		queryRule.addEqual("validFlag", Constants.VALID_FLAG_Y);
		queryRule.addAscOrder("menuIndex");
		return this.find(VcMenuDef.class, queryRule);
	}

	@Override
	public List<VcMenuDef> queryMenuDefsOfValid(String queryMenuDefsOfValid)
			throws DaoException {
		QueryRule queryRule = QueryRule.getInstance();
		if (queryMenuDefsOfValid != null) {
			queryRule.addEqual("systemCode", queryMenuDefsOfValid);
		}
		queryRule.addEqual("validFlag", Constants.VALID_FLAG_Y);
		return this.find(VcMenuDef.class, queryRule);
	}

	@Override
	public List<VcMenuDef> queryMenuListByRoleId(String roleId)
			throws DaoException {
		List<VcMenuDef> listVcMenuDef = new ArrayList<VcMenuDef>();
		try {
			List<Object> values = new ArrayList<Object>();
			StringBuffer sb = new StringBuffer(
					"select t.menu_id,t.menu_name,t.parent_id,t.menu_index,t.system_code,t.menu_url,t.valid_flag from vc_menu_def t where t.menu_id in(select t1.menu_id from vc_role_menu t1 where t1.role_id=?)");
			values.add(roleId);
			List list = this.findBySql(sb.toString(), values.toArray());
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					VcMenuDef menuDef = null;
					Object[] obj = (Object[]) list.get(i);
					menuDef = new VcMenuDef();
					BigDecimal c0 = new BigDecimal(obj[0].toString());
					BigDecimal c2 = new BigDecimal(obj[2].toString());
					BigDecimal c3 = new BigDecimal(obj[3].toString());
					menuDef.setMenuId(Long.valueOf(c0.toString()));
					menuDef.setMenuName((String) obj[1]);
					menuDef.setParentId(Long.valueOf(c2.toString()));
					menuDef.setMenuIndex(Long.valueOf(c3.toString()));
					menuDef.setSystemCode((String) obj[4]);
					menuDef.setValidFlag((String) obj[6]);
					listVcMenuDef.add(menuDef);
				}
			}
		} catch (Exception e) {
			throw new DaoException("查询数据库失败！", e);
		}
		return listVcMenuDef;
	}

	@Override
	public List<SystemDef> querySystemDefsOfValid(String systemCode)
			throws DaoException {
		QueryRule queryRule = null;
		try {
			queryRule = QueryRule.getInstance();
			if (systemCode != null) {
				queryRule.addEqual("systemCode", "VC");
			}
		} catch (Exception e) {
			throw new DaoException("QueryRule拼接异常！", e);
		}
		queryRule.addEqual("validFlag", Constants.VALID_FLAG_Y);
		return this.find(SystemDef.class, queryRule);
	}

	@Override
	public Page queryMenuDef(VcMenuDef menuDef, int currentPage, int pageNumber) {
		QueryRule queryRule = QueryRule.getInstance();
		if (StringUtils.isNotBlank(menuDef.getMenuName())) {
			queryRule.addLike("menuName", "%" + menuDef.getMenuName() + "%");
		}
		if (StringUtils.isNotBlank(menuDef.getValidFlag())) {
			queryRule.addLike("validFlag", "%" + menuDef.getValidFlag() + "%");
		}
		queryRule.addEqual("systemCode", "VC");
		return this.find(VcMenuDef.class, queryRule, currentPage, pageNumber);
	}

	@Override
	public List<VcMenuDef> getMenuDef(String menuId) throws DaoException {
		QueryRule queryRule = null;
		try {
			queryRule = QueryRule.getInstance();
			queryRule.addEqual("menuId", Long.parseLong(menuId));
			queryRule.addEqual("systemCode", "VC");
		} catch (Exception e) {
			throw new DaoException("QueryRule拼接异常！", e);
		}
		List<VcMenuDef> list=this.find(VcMenuDef.class, queryRule);
		return list;
	}

	@Override
	public List<String> queryAllChildMenuDef(String menuId) throws DaoException {
		String sql = "select md.menu_id from vc_menu_def md connect by prior md.menu_id = md.parent_id start with md.menu_id = ?" ;
		Query query = getSession().createSQLQuery(sql).addScalar("menu_id",Hibernate.STRING).setString(0, menuId);		
		return query.list();	
	}
	
	public List<String> queryAllParentMenuDef(String menuId) throws DaoException {
		String sql = "select md.menu_id from vc_menu_def md connect by md.menu_id = prior md.parent_id start with md.menu_id = ?" ;
		Query query = getSession().createSQLQuery(sql).addScalar("menu_id",Hibernate.STRING).setString(0, menuId);		
		return query.list();	
	}
}
