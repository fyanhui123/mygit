/**
 * 
 */
package com.tapi.tcs.vc.visausage.dao.impl;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.vc.common.util.DateUtils;
import com.tapi.tcs.vc.schema.model.VcCancel;
import com.tapi.tcs.vc.visausage.dao.CancelDao;

/**
 * @author hanmiao.diao
 *
 */
public class CancelDaoImpl extends GenericDaoHibernate<VcCancel> implements CancelDao {

	/*
	 * (non-Javadoc)
	 * @see com.tapi.tcs.vc.visausage.dao.CancelDao#insertVcCancel(com.tapi.tcs.vc.schema.model.VcCancel)
	 */
	@Override
	public void insertVcCancel(VcCancel cancel) throws DaoException {
		try{
			save(cancel);
		}catch(Exception e){
			throw new DaoException("保存数据出错！", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.tapi.tcs.vc.visausage.dao.CancelDao#findCancelListByPages(java.util.Map, int, int)
	 */
	@Override
	public Page findCancelListByPages(Map<String, Object> params,
			int page, int rows) throws DaoException {
		QueryRule queryRule = null;
		try {
			queryRule = QueryRule.getInstance();
			String cancelType = (String) params.get("cancelType");
			if (StringUtils.isNotEmpty(cancelType)) {
				queryRule.addEqual("cancelType", cancelType);
			}
			String cancelCode  = (String) params.get("cancelCode");
			if(StringUtils.isNotEmpty(cancelCode)) {
				queryRule.addEqual("cancelCode", cancelCode);
			}
			String cancelStatus = (String) params.get("cancelStatus");
			if(StringUtils.isNotEmpty(cancelStatus)) {
				queryRule.addEqual("cancelStatus", cancelStatus);
			}
			String startDate = (String) params.get("startDate");
			if(StringUtils.isNotEmpty(startDate)) {
				Date date1 = DateUtils.parse(startDate + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
				queryRule.addGreaterEqual("cancelTime", date1);
			}
			String endDate = (String) params.get("endDate");
			if(StringUtils.isNotEmpty(endDate)) {
				Date date1 = DateUtils.parse(endDate + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
				queryRule.addLessEqual("cancelTime", date1);
			}
			
			String cancelOrgCode = (String) params.get("cancelOrgCode");
			if(StringUtils.isNotEmpty(cancelOrgCode)) {
				queryRule.addEqual("cancelOrgCode", cancelOrgCode);
			}
			String approveOrgCode = (String) params.get("approveOrgCode");
			if(StringUtils.isNotEmpty(approveOrgCode)) {
				queryRule.addEqual("approveOrgCode", approveOrgCode);
			}
			
			queryRule.addDescOrder("cancelTime");
		} catch (Exception e) {
			throw new DaoException("查询数据库异常！", e);
		}
		
		return this.find(VcCancel.class, queryRule, page, rows);
	}

	/*
	 * (non-Javadoc)
	 * @see com.tapi.tcs.vc.visausage.dao.CancelDao#deleteVcCancelDetByVcCancelId(java.lang.Long)
	 */
	@Override
	public void deleteVcCancelDetByVcCancelId(Long id) throws DaoException {
		try{
			String hql = "delete from VcCancelDet t where t.vcCancel.idVcCancel=:id";
			Session session = getSession();
			Query query = session.createQuery(hql);
			query.setParameter("id", id);
			query.executeUpdate();
		} catch (Exception e) {
			throw new DaoException("删除数据失败！", e);
		}
	}
	
}
