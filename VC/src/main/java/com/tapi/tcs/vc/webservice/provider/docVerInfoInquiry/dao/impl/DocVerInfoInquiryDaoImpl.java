package com.tapi.tcs.vc.webservice.provider.docVerInfoInquiry.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.common.util.SysConst;
import com.tapi.tcs.vc.schema.model.VcDocRelInsuKind;
import com.tapi.tcs.vc.schema.model.VcDocVersionInfo;
import com.tapi.tcs.vc.webservice.provider.docVerInfoInquiry.bean.DocVerInfoInquiryRequest;
import com.tapi.tcs.vc.webservice.provider.docVerInfoInquiry.bean.DocVerInfoInquiryRequestDTO;
import com.tapi.tcs.vc.webservice.provider.docVerInfoInquiry.bean.DocVersionInfoDTO;
import com.tapi.tcs.vc.webservice.provider.docVerInfoInquiry.dao.DocVerInfoInquiryDao;

public class DocVerInfoInquiryDaoImpl extends GenericDaoHibernate<VcDocVersionInfo> implements DocVerInfoInquiryDao{

	
	@SuppressWarnings("unchecked")
	@Override
	public Page docVerInfoInquiry(DocVerInfoInquiryRequestDTO requestBody) throws DaoException{
		Page page = null;
		List<Object> values =new ArrayList<Object>();
        StringBuffer hql=new StringBuffer();
        hql.append(" from VcDocVersionInfo doc where 1=1 ");
        
       //单证种类
        hql.append(" and exists(select 1 from  VcDocType vc where vc.idVcDocType=doc.idVcDocType ");
        hql.append(" and vc.docTypeCode=?)");
        values.add(requestBody.getDocTypeCode());
        //排除无效的单证类型
        hql.append(" and doc.status=? ");
        values.add("1");
        hql.append(" order by doc.idVcDocVersionInfo");
		try{
			page = this.findByHqlNoLimit(hql.toString(), requestBody.getPageNo() ,requestBody.getPageSize(), values.toArray());
		}catch(Exception e){
			e.printStackTrace();
			throw new DaoException("查询数据库异常！");
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
