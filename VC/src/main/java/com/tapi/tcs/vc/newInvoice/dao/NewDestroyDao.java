package com.tapi.tcs.vc.newInvoice.dao;

import java.util.List;

import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.schema.model.VcAbnormalVerification;
import com.tapi.tcs.vc.schema.model.VcDestroy;

public interface NewDestroyDao extends GenericDao<VcDestroy>{
	public List initDestroyView(Long id, String action,String type) throws DaoException;
	
	public Page queryDestroy(VcDestroy vcDestroy, int pageNo, int pageSize, String action) throws DaoException;
	public List<VcAbnormalVerification> queryVcAbnormalVerification(String docVerCode,String start,String pressBatchNo,String status);

	String getUnitNameByUnitCode(String unitCode) throws DaoException;
	
}
