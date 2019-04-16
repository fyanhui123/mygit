/**
 * 
 */
package com.tapi.tcs.vc.store.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.common.util.DateUtils;
import com.tapi.tcs.vc.schema.model.VcRecycle;
import com.tapi.tcs.vc.store.dao.RecycleDao;

/**
 * @author hanmiao.diao
 *
 */
public class RecycleDaoImpl extends GenericDaoHibernate<VcRecycle> implements RecycleDao {

	/*
	 * (non-Javadoc)
	 * @see com.tapi.tcs.vc.store.dao.RecycleDao#findRecycleListByPages(java.util.Map, int, int)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Page findRecycleListByPages(Map<String, Object> params, int page,
			int rows) throws DaoException {
		try{
		StringBuffer sb = new StringBuffer("from VcRecycle t1 where 1=1");
		List values = new ArrayList();
		String recycleCode = (String) params.get("recycleCode");
		if (StringUtils.isNotEmpty(recycleCode)) {
			sb.append(" and t1.recycleCode = ? ");
			values.add(recycleCode);
		}
		String recycleStatus = (String) params.get("recycleStatus");
		if(StringUtils.isNotEmpty(recycleStatus)) {
			sb.append(" and t1.recycleStatus= ? ");
			values.add(recycleStatus);
		}
		String recycleOrgCode = (String) params.get("recycleOrgCode");
		if(StringUtils.isNotEmpty(recycleOrgCode)) {
			sb.append(" and t1.recycleOrgCode= ? ");
			values.add(recycleOrgCode);
		}
		String confirmOrgCode = (String) params.get("confirmOrgCode");
		if(StringUtils.isNotEmpty(confirmOrgCode)) {
			sb.append(" and t1.confirmOrgCode= ? ");
			values.add(confirmOrgCode);
		}
		String docVerCode = (String) params.get("docVerCode");
		if(StringUtils.isNotEmpty(docVerCode)) {
			sb.append(" and exists(select 1 from VcRecycleDet t2 where t2.idVcRecycle = t1.idVcRecycle and t2.docVerCode=?)");
			values.add(docVerCode);
		}
		
		String startDate = (String) params.get("startDate");
		if(StringUtils.isNotEmpty(startDate)) {
			sb.append(" and t1.recycleTime >= ? ");
			values.add(DateUtils.parse(startDate + " 00:00:00", "yyyy-MM-dd HH:mm:ss"));
		}
		
		String endDate = (String) params.get("endDate");
		if(StringUtils.isNotEmpty(endDate)) {
			sb.append(" and t1.recycleTime <= ?");
			values.add(DateUtils.parse(endDate + " 23:59:59", "yyyy-MM-dd HH:mm:ss"));
		}
		
		sb.append(" order by t1.recycleTime desc");
		
		return this.findByHql(sb.toString(), page, rows,values.toArray());
		}catch(Exception e){
			throw new DaoException("查询数据库时发生异常！", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.tapi.tcs.vc.store.dao.RecycleDao#deleteRecycleDetListByRecycleId(java.lang.Long)
	 */
	@Override
	public void deleteRecycleDetListByRecycleId(Long id) throws DaoException {
		try{
		String hql = "delete from VcRecycleDet t where t.vcRecycle.idVcRecycle=:id";
		Session session = getSession();
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		query.executeUpdate();
		}catch(Exception e){
			throw new DaoException("删除数据时发生异常！", e);
		}
	}
	
	
	
	
	
}
