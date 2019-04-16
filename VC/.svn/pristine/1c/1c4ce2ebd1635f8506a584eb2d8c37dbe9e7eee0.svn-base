package com.tapi.tcs.vc.webservice.provider.cancelDoReversed.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.schema.model.VcAbnormalVerification;
import com.tapi.tcs.vc.schema.model.VcAbnormalVerifiedDet;
import com.tapi.tcs.vc.schema.model.VcAvailableDoc;
import com.tapi.tcs.vc.schema.model.VcNormalVerification;
import com.tapi.tcs.vc.webservice.provider.cancelDoReversed.dao.CancelReversedDao;


@SuppressWarnings("unchecked")
public class CancelReversedDaoImpl  extends GenericDaoHibernate<VcNormalVerification> implements CancelReversedDao {

	

	@Override
	public List<VcAbnormalVerification> findVcAbnormalVerification(
			String docVerCode,  String docNum, String businessNo,String pressBatchNo) throws DaoException {
		List<VcAbnormalVerification> list = new ArrayList<VcAbnormalVerification>();
		StringBuffer hql = new StringBuffer("");
		List<Object> values = new ArrayList<Object>();
		
		
		hql.append(" from VcAbnormalVerification nv");
		hql.append(" where nv.docVerCode=?");
		hql.append(" and docNum=?");
		hql.append(" and businessNo=?");
		values.add(docVerCode);
		values.add(docNum);
		values.add(businessNo);
		if (StringUtils.isNotBlank(pressBatchNo)) {
			hql.append(" and pressBatchNo=?");
			values.add(pressBatchNo);
		}

		try{
			list = this.findByHql(hql.toString(),values.toArray());
		}catch(Exception e){
			e.printStackTrace();
			throw new DaoException("查询业务号是否有非正常核销记录异常！");
		}
		return list; 
	}

	@Override
	public void saveNormalVerification(VcNormalVerification normal) {
		this.save(normal);
	}
	
//	@Override
//	public void saveNormalVerificationDet(VcNormalVerifiedDet normalDet) {
//		this.save(normalDet);
//	}

	@Override
	public void deleteAbnormalVerification(VcAbnormalVerification abnormal) {
		List<VcAbnormalVerifiedDet> list  = abnormal.getVcAbnormalVerifiedDetList();
		this.deleteAll(list);
		this.delete(abnormal);
		
		
	}

	

}
