package com.tapi.tcs.vc.common.dao;

import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.schema.model.VcAllot;

public interface AllotCodeNo extends GenericDao<VcAllot>{
	public String getAllotNo(String bizType) throws DaoException;
}
