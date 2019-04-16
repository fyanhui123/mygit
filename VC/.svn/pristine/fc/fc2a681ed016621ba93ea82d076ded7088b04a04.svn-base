package com.tapi.tcs.vc.webservice.provider.docDoReversed.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.vc.schema.model.VcAvailableDoc;
import com.tapi.tcs.vc.schema.model.VcNormalVerification;
import com.tapi.tcs.vc.schema.model.VcTaker;
import com.tapi.tcs.vc.webservice.provider.docDoReversed.dao.DocReversedDao;

public class DocReversedDaoImpl extends GenericDaoHibernate<VcAvailableDoc> implements DocReversedDao {

	@Override
	public VcNormalVerification findVcNormalVerification(String docVerCode, String pressBatchNo,
			String docNum, String orgCode, String businessNo) throws DaoException {
		VcNormalVerification vcNormalVerification = null;
		try{
			StringBuffer hql = new StringBuffer();
			List<Object> values = new ArrayList<Object>();
			hql.append(" from VcNormalVerification v where v.docVerCode=? and v.pressBatchNo=?");
			hql.append(" and v.docNum=? and v.verifiedOrgCode=?  and v.docStatus='U1'");
			hql.append(" and exists(select 1 from VcNormalVerifiedDet det");
			hql.append(" where det.idVcNormalVerification=v.idVcNormalVerification");
			hql.append(" and det.businessNo=?)");
			values.add(docVerCode);
			values.add(pressBatchNo);
			values.add(docNum);
			values.add(orgCode);
			values.add(businessNo);
			List<VcNormalVerification> list = this.findByHql(hql.toString(), values.toArray());
			if(list!=null){
				vcNormalVerification = list.get(0);
			}
		}catch(Exception e){
			throw new DaoException("查询正常核销记录失败！", e);
		}
		return vcNormalVerification;
	}
	
	@Override
	public List<VcTaker> findVcTakerListByOrgCode(String orgCode) throws DaoException{
		List<VcTaker> takerList = null;
		try{
			StringBuffer hql = new StringBuffer();
			List<Object> values = new ArrayList<Object>();
			hql.append(" from VcTaker t where t.orgCode=? and t.status='1'");
			values.add(orgCode);
			takerList = this.findByHql(hql.toString(), values.toArray());
		}catch(Exception e){
			throw new DaoException("查询使用人失败！", e);
		}
		return takerList;
	}
}
