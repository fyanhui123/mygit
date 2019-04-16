package com.tapi.tcs.vc.webservice.provider.docNumInquiryOld.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.schema.model.VcAvailableDoc;
import com.tapi.tcs.vc.webservice.provider.docNumInquiryOld.dao.DocNumQueryDao;
import com.tapi.tcs.vc.webservice.provider.docNumInquiryOld.vo.DocNumInquiryRequestVO;

public class DocNumQueryDaoImpl extends GenericDaoHibernate<VcAvailableDoc> implements DocNumQueryDao {

	@SuppressWarnings("rawtypes")
	@Override
	public List vcAvailableQuery(DocNumInquiryRequestVO requestVo) throws DaoException {
		List resultList = null;		
		List<Object> params = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();

		sb.append(" from VcAvailableDoc av,VcDocVersionInfo vc  where 1=1");
		sb.append(" and  vc.docVerCode=av.docVerCode");
		// 单证状态
		sb.append(" and av.docStatus=? ");
		params.add("A");
		// 单证类型
		sb.append(" and av.docVerCode=? ");
		params.add(requestVo.getDocVerCode());
		// 操作人员code
		sb.append(" and av.takerCode=? ");
		params.add(requestVo.getOperatorCode());
		//单证号段查询--不加批次查询
		// 批次号
		/*if (StringUtils.isNotEmpty(requestVo.getInvoiceVersion())) {
			sb.append(" and av.pressBatchNo='" + requestVo.getInvoiceVersion()+"'");
		}*/
		// 机构代码
		sb.append(" and av.orgCode=? ");
		params.add(requestVo.getOrgCode());

		sb.append(" order by av.docNum asc");
		try {
			resultList = this.findByHql(sb.toString(), params.toArray());
			
			
		} catch(Exception e){
			e.printStackTrace();
			throw new DaoException("查询数据库异常！");
		}
		return resultList;
	}
}
