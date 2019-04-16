package com.tapi.tcs.vc.oauth.dao;

import java.util.List;

import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.schema.model.VcRoleDef;
import com.tapi.tcs.vc.schema.model.VcUserRole;

public interface OAuthDao {

	public VcRoleDef queryRole(String roleCode) throws DaoException;
	public void updateVcRoleDef(VcRoleDef roleDef) throws DaoException;
	public void saveVcRoleDef(VcRoleDef roleDef) throws DaoException;
	public List<VcUserRole> queryUserRole(String employeeCode) throws DaoException;
	public void saveVcUserRole(VcUserRole userRole) throws DaoException;
	public void deleteVcUserRole(VcUserRole userRole) throws DaoException;
	
	
}
