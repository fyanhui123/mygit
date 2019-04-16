package com.tapi.tcs.vc.oauth.dao.impl;

import java.util.List;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.vc.oauth.dao.OAuthDao;
import com.tapi.tcs.vc.oauth.vo.OAuthRole;
import com.tapi.tcs.vc.schema.model.VcRoleDef;
import com.tapi.tcs.vc.schema.model.VcUserRole;

public class OAuthDaoImpl extends GenericDaoHibernate<OAuthRole> implements OAuthDao {

	@Override
	public VcRoleDef queryRole(String roleCode) throws DaoException {
		List<VcRoleDef> list = null;
		try {
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("roleId", Long.valueOf(roleCode));
			list = this.find(VcRoleDef.class, queryRule);
			if (list != null && list.size() > 0) {
				return list.get(0);
			}
		} catch (Exception e) {
			throw new DaoException("查询数据出错！", e);
		}
		return null;
	}
	@Override
	public void updateVcRoleDef(VcRoleDef roleDef) throws DaoException {
		try{
			this.update(roleDef);
		}catch(Exception e){
			throw new DaoException("更新数据出错！", e);
		}
	}
	@Override
	public void saveVcRoleDef(VcRoleDef roleDef) throws DaoException {
		try{
			this.save(roleDef);
		}catch(Exception e){
			throw new DaoException("插入数据失败！", e);
		}
	}
	
	
	
	@Override
	public List<VcUserRole> queryUserRole(String employeeCode) throws DaoException {
		List<VcUserRole> list = null;
		try {
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("employeeId", employeeCode);
			//queryRule.addEqual("roleId", roleCode);
			list = this.find(VcUserRole.class, queryRule);
			if (list != null && list.size() > 0) {
				return list;
			}
		} catch (Exception e) {
			throw new DaoException("查询数据出错！", e);
		}
		return null;
	}
	@Override
	public void saveVcUserRole(VcUserRole userRole) throws DaoException {
		try{
			this.save(userRole);
		}catch(Exception e){
			throw new DaoException("插入数据失败！", e);
		}
	}
	@Override
	public void deleteVcUserRole(VcUserRole userRole) throws DaoException {
		try{
			this.delete(userRole);
		}catch(Exception e){
			throw new DaoException("删除记录失败！", e);
		}
	}
}
