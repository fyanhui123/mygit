package com.tapi.tcs.vc.webservice.provider.docNumSynchronize.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.pansky.framework.util.a;
import com.tapi.tcs.vc.schema.model.VcAvailableDoc;
import com.tapi.tcs.vc.schema.model.VcDocTakerIo;
import com.tapi.tcs.vc.schema.model.VcDocVersionInfo;
import com.tapi.tcs.vc.webservice.provider.docNumSynchronize.dao.QueryProvideNumDao;
import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;

public class QueryProvideNumDaoImpl extends
GenericDaoHibernate<VcDocTakerIo>  implements QueryProvideNumDao{


	@Override
	public List<VcDocTakerIo> queryVcDocTakerIo(String docVerCode,
			String takerCode,Date startDate,Date endDate) throws DaoException {
		// TODO Auto-generated method stub
		List<Object> values =new ArrayList<Object>();
		StringBuffer hql = new StringBuffer("");
		hql.append("from VcDocTakerIo a");
		hql.append(" where a.docVerCode =?");
		values.add(docVerCode);
		hql.append(" and a.takerCode =?");
		values.add(takerCode);
		hql.append(" and a.oprType=?");
		values.add("P");
		hql.append(" and a.oprTime between to_date('"+startDate);
		hql.append("','yyyy-mm-dd') and to_date('" +endDate);
		hql.append("','yyyy-mm-dd')+1");
		hql.append(" order by a.oprTime,a.startNum");
		List<VcDocTakerIo> resultList = this.findByHql(hql.toString(),values.toArray());
		return resultList;
	}

	@Override
	public List<VcDocVersionInfo> queryVcDocVersionId(String docVerCode)
			throws DaoException {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		List params = new ArrayList();
		sql.append("from VcDocVersionInfo a where a.docVerCode =?");
		params.add(docVerCode);
		List<VcDocVersionInfo> resultList = this.findByHql(sql.toString(), params.toArray());		
		return resultList;
	}
	
	@Override
	public List<VcAvailableDoc> queryVcDocDeadline(String docVerCode,
			String startNum, String endNum) throws DaoException {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		List <Object> params = new ArrayList<Object>();
		sql.append("from VcAvailableDoc a where a.docVerCode =?");
		params.add(docVerCode);
		sql.append(" and a.docNum between ?");
		params.add(startNum);
		sql.append(" and ?");
		params.add(endNum);
		List<VcAvailableDoc> resultList = this.findByHql(sql.toString(), params.toArray());		
		return resultList;
	}

}
