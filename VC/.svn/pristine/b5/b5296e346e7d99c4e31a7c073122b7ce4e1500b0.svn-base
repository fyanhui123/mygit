package com.tapi.tcs.vc.visausage.dao;

import java.util.List;

import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.schema.model.VcDestroy;

public interface DestroyDao extends GenericDao<VcDestroy> {

	public void lockVcDestroy(Long id) throws DaoException;

	public void updateBySQL(String sql, Object... params) throws DaoException;

	public Page queryDestroy(VcDestroy vcDestroy, int pageNo, int pageSize, String action) throws DaoException;

	public List initDestroyView(Long id, String action) throws DaoException;
}
