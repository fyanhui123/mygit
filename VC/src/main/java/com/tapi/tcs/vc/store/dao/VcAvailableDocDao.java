package com.tapi.tcs.vc.store.dao;

import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.schema.model.VcAvailableDoc;

public interface VcAvailableDocDao  extends GenericDao<VcAvailableDoc>{
	
	/**
	 * 锁表操作
	 * @param id 主键
	 */
	public void lockVcAvailableDoc(Object... id) throws DaoException;
	
	public void updateBySql(String sql,Object... params) throws DaoException;
}
