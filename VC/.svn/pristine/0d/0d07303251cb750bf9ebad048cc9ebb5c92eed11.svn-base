package com.tapi.tcs.vc.webservice.provider.docNumInquiryNew.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.vc.common.util.DateUtils;
import com.tapi.tcs.vc.schema.model.VcAvailableDoc;
import com.tapi.tcs.vc.schema.model.VcDocRelInsuKind;
import com.tapi.tcs.vc.schema.model.VcDocVersionInfo;
import com.tapi.tcs.vc.schema.model.VcStorage;
import com.tapi.tcs.vc.webservice.provider.docNumInquiryNew.dao.QueryAvailableNumNewDao;

public class QueryAvailableNumNewDaoImpl extends
		GenericDaoHibernate<VcAvailableDoc> implements QueryAvailableNumNewDao {

	@Override
	public Page queryVcAvailableDocNew(int currentPage,
			int pageSize,Date lastSynTime,String docVerCode, String takerCode) throws DaoException {
		Page page = null;
		List<Object> values =new ArrayList<Object>();
		try {
			StringBuffer sql = new StringBuffer();
			sql.append(" from VcAvailableDoc a,VcDocVersionInfo ver");
			sql.append(" where a.docVerCode=ver.docVerCode");
			sql.append(" and a.docVerCode = ? ");
			values.add(docVerCode);
			sql.append(" and a.docStatus='A' and ver.status='1'");
			if(StringUtils.isNotBlank(takerCode)){
				sql.append(" and a.takerCode = ? ");
				values.add(takerCode);
			}
			if(null != lastSynTime){
				sql.append(" and a.provideTime>?");
				values.add(lastSynTime);
			}
			sql.append(" and a.deadline>=? ");	
			Date curDate=DateUtils.reset(new Date());
			values.add(curDate);
			sql.append(" order by ver.idVcDocVersionInfo,to_number(a.docNum)");
			page=this.findByHqlNoLimit(sql.toString(), currentPage, pageSize, values.toArray());
		} catch (Exception e) {
			throw new DaoException("查询可使用流水号失败！", e);
		}
		return page;
	}
	
	@Override
	public List<VcDocRelInsuKind> queryDocRelInsuKindList(Long idVcDocVersionInfo) throws DaoException{
		
		StringBuffer sql = new StringBuffer();
		List params = new ArrayList();
		sql.append("from VcDocRelInsuKind a where a.idVcDocVersionInfo = ?");
		params.add(idVcDocVersionInfo);
		List<VcDocRelInsuKind> resultList = this.findByHql(sql.toString(), params.toArray());
		
		
		return resultList;
	}
}
