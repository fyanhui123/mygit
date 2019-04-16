package com.tapi.tcs.vc.vcmenu.dao.impl;

import java.util.List;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.vc.common.util.DateUtils;
import com.tapi.tcs.vc.schema.model.VcRoleMenu;
import com.tapi.tcs.vc.vcmenu.dao.VcRoleMenuDao;
import com.tapi.tcs.vc.vcmenu.dto.Constants;

public class VcRoleMenuDaoImpl extends GenericDaoHibernate<VcRoleMenu>
		implements VcRoleMenuDao {
	@Override
	public void saveMenuList(String roleId, String[] menuIds, String userCode)
			throws DaoException {
		QueryRule queryRule = null;
		try {
			queryRule = QueryRule.getInstance();
		} catch (Exception e) {
			throw new DaoException("QueryRule拼接异常！", e);
		}
		queryRule.addEqual("role", roleId);
		queryRule.addEqual("validFlag", Constants.VALID_FLAG_Y);
		List<VcRoleMenu> list = find(VcRoleMenu.class, queryRule);
		if (list.size() > 0) {
			// 先删后插
			for (int i = 0; i < list.size(); i++) {
				VcRoleMenu vcRoleMenu = list.get(i);
				this.delete(vcRoleMenu);
			}
		}
		// 直接插入
		saveMeue(roleId, menuIds, userCode);
	}

	public void saveMeue(String roleId, String[] menuIds, String userCode) {
		try {
			for (int i = 0; i < menuIds.length; i++) {
				VcRoleMenu vcRoleMenu = new VcRoleMenu();
				vcRoleMenu.setMenu(menuIds[i]);
				vcRoleMenu.setRole(roleId);
				vcRoleMenu.setValidFlag("1");
				vcRoleMenu.setCreatedBy(userCode);
				vcRoleMenu.setUpdateBy(userCode);
				vcRoleMenu.setDateCreated(DateUtils.now());
				vcRoleMenu.setDateUpdated(DateUtils.now());
				this.save(vcRoleMenu);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String queryMenuRoleId() throws DaoException {
		StringBuffer sb = new StringBuffer(
				"select SEQ_ROLE_MENU.nextval from  dual");
		List list = this.findBySql(sb.toString(), null);
		return list.get(0).toString();
	}
}
