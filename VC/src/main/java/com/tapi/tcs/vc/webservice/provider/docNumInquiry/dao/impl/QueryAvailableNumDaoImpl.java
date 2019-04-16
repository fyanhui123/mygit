package com.tapi.tcs.vc.webservice.provider.docNumInquiry.dao.impl;

import java.util.Date;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.common.util.DateUtils;
import com.tapi.tcs.vc.schema.model.VcAvailableDoc;
import com.tapi.tcs.vc.webservice.provider.docNumInquiry.dao.QueryAvailableNumDao;

public class QueryAvailableNumDaoImpl extends
		GenericDaoHibernate<VcAvailableDoc> implements QueryAvailableNumDao {

	@Override
	public Page queryVcAvailableDoc(int currentPage,
			int pageSize,Date lastSynTime) throws DaoException {
		Page page = null;
		try {
			StringBuffer sql = new StringBuffer();
			/*sql.append("select a.doc_num,a.org_code,a.taker_code,ver.id_vc_doc_version_info");
			sql.append(" from vc_available_doc a,vc_doc_version_info ver");
			sql.append(" where a.doc_ver_code=ver.doc_ver_code ");
			sql.append(" and ver.id_vc_doc_type=(select id_vc_doc_type from vc_doc_type type");
			sql.append(" where type.doc_type_code='BD')");
			sql.append(" order by ver.id_vc_doc_version_info,to_number(a.doc_num)");*/
			sql.append(" from VcAvailableDoc a,VcDocVersionInfo ver");
			sql.append(" where a.docVerCode=ver.docVerCode and ver.idVcDocType=(");
			sql.append(" select idVcDocType from VcDocType type");
			sql.append(" where type.docTypeCode=? and type.status='1')");
			sql.append(" and a.docStatus='A' and ver.status='1'");
			sql.append(" and a.provideTime>?");
			sql.append(" and a.deadline>=? ");	
			Date curDate=DateUtils.reset(new Date());
			sql.append(" order by ver.idVcDocVersionInfo,to_number(a.docNum)");
			page=this.findByHqlNoLimit(sql.toString(), currentPage, pageSize, new Object[]{"BD",lastSynTime,curDate});
		} catch (Exception e) {
			throw new DaoException("查询可使用流水号失败！", e);
		}
		return page;
	}
}
