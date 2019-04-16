package com.tapi.tcs.vc.store.dao.impl;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.schema.model.VcAvailableDoc;
import com.tapi.tcs.vc.store.dao.VcAvailableDocDao;

public class VcAvailableDocDaoImpl extends GenericDaoHibernate<VcAvailableDoc>
		implements VcAvailableDocDao {

	@Override
	public void lockVcAvailableDoc(Object... values)  throws DaoException{
		try{
		StringBuffer paramSql = new StringBuffer();
		for (int i = 0; i < values.length; i++) {
			paramSql.append("?,");
		}
		paramSql.substring(0, paramSql.length());
		String sql = "SELECT * FROM vc_available_doc t WHERE t.id_vc_available_doc in ("+paramSql.substring(0, paramSql.length()-1)+") FOR UPDATE ";
		this.findBySql(sql, values);
		}catch(Exception e){
			throw new DaoException("查询数据库时发生异常！", e);
		}
	}

	
	@Override
	public void updateBySql(String sql, Object... params) throws DaoException{
		try{
		this.executeUpdateBySql(sql, params);
		}catch(Exception e){
			throw new DaoException("查询数据库时发生异常！", e);
		}
	}
	
	
	
}
