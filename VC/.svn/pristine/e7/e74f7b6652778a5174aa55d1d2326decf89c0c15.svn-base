package com.tapi.tcs.vc.visausage.dao;

import java.util.List;

import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.schema.model.VcLost;

public interface VcLostDao extends GenericDao<VcLost>{
	
	public Page findVcLost(VcLost vcLost,int pageNo,int pageSize,String action) throws DaoException; 
	public Page findVcInvoiceLost(VcLost vcLost,int pageNo,int pageSize,String action) throws DaoException; 
	 /**
	 * 锁表操作
	 * @param id 主键
	 */
	public void lockVcLost(Object... id) throws DaoException;
	
	public void updateBySql(String sql,Object... params) throws DaoException;
	
	public List viewLostApply(Long id) throws DaoException;
}
