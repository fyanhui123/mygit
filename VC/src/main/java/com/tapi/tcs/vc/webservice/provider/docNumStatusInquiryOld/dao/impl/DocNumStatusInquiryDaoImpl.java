package com.tapi.tcs.vc.webservice.provider.docNumStatusInquiryOld.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.vc.schema.model.VcDocInsuType;
import com.tapi.tcs.vc.schema.model.VcDocVersionInfo;
import com.tapi.tcs.vc.schema.model.VcNormalVerification;
import com.tapi.tcs.vc.webservice.provider.docNumStatusInquiryOld.bean.DocNumStatusInquiryOldRequestDTO;
import com.tapi.tcs.vc.webservice.provider.docNumStatusInquiryOld.bean.DocNumStatusInquiryOldResponse;
import com.tapi.tcs.vc.webservice.provider.docNumStatusInquiryOld.dao.DocNumStatusInquiryDao;

public class DocNumStatusInquiryDaoImpl extends GenericDaoHibernate<VcDocVersionInfo> implements DocNumStatusInquiryDao {

 
	
	@Override
	public DocNumStatusInquiryOldResponse queryDocNumStatus(DocNumStatusInquiryOldRequestDTO request) throws DaoException {
		DocNumStatusInquiryOldResponse  response=new DocNumStatusInquiryOldResponse();
		/*  // 条件单证类型code   操作员code  操作员归属机构code
		String docVerCode=String.valueOf(request.getDocVerID());
		String operatorID=String.valueOf(request.getOperatorID()); 
		String orgID=request.getOrgID();
		// 业务号    单证起始流水号   单证终止流水号
		String  businessNo=request.getBusinessNo();
		// 发票版本
		String invoiceVersion=request.getInvoiceVersion();
	   //起始流水号、终止流水号
		Long  startNum=Long.valueOf(request.getStartNum());  
		Long  endNum=Long.valueOf(request.getEndNum());
		
		// 险种   险类(long)
		String  insuKindID=request.getInsuKindID();
		String  insuTypeID=String.valueOf(request.getInsuTypeID()); 
		*/
		
		StringBuffer sb=new StringBuffer();
		StringBuffer sbKsy=new StringBuffer();
		StringBuffer sbZchx=new StringBuffer();
		
		sbKsy.append("from VcAvailableDoc av,VcDocVersionInfo vc,VcDocRelArea area where 1=1");
		sbKsy.append("and  vc.docVerCode=av.docVerCode and  area.idVcDocVersionInfo=vc.idVcDocVersionInfo and area.orgCode=av.orgCode  ");
		
		sbZchx.append("from VcNormalVerification av,VcDocVersionInfo vc,VcDocRelArea area where 1=1");
		sbZchx.append("and  vc.docVerCode=av.docVerCode and  area.idVcDocVersionInfo=vc.idVcDocVersionInfo and area.orgCode=av.orgCode ");
		
		List<Object>  values=new ArrayList<Object>();  
		
		/*
		if(StringUtils.isNotEmpty(docVerCode)){
		   sb.append("and  vc.idVcDocVersionInfo=? ");
		   values.add(docVerCode); 
		}
		
		if(StringUtils.isNotEmpty(operatorID)){ 
		   sb.append("and av.takerCode=? ");
		   values.add(operatorID);
		}
	   
		if(StringUtils.isNotEmpty(orgID)){
			sb.append("and area.orgCode=? ");
			values.add(orgID);
		}
		
		if(StringUtils.isNotEmpty(invoiceVersion)){
			sb.append("and av.pressBatchNo=? ");
			values.add(invoiceVersion);
		}
		 */
		/*
		// 险类//险种
		if (StringUtils.isNotBlank(insuTypeID)
				|| StringUtils.isNotBlank(insuKindID)) {
			sb.append(" and exists(select 1 from VcDocInsuKind insuKind, VcDocRelInsuKind ref where insuKind.insuKindCode=ref.insuKindCode and ref.idVcDocVersionInfo=vc.idVcDocVersionInfo ");
			if (StringUtils.isNotBlank(insuTypeID)) {
				sb.append(" and insuKind.insuTypeCode=?");
				values.add(insuTypeID);
			}
			if (StringUtils.isNotBlank(insuKindID)) {
				sb.append(" and insuKind.insuKindCode=?");
				values.add(insuKindID);
			}
			sb.append(" ) ");
		}
		*/
			return response; 
			
   }

	@Override
	public List checkBizNoIsUsed(String businessNo, String versionCode) throws DaoException {
		List  objList = null; 
		StringBuffer hql = new StringBuffer("");
		hql.append(" from VcNormalVerification nv,VcDocVersionInfo ver");
		hql.append(" where nv.docVerCode=ver.docVerCode");
		hql.append(" and ver.idVcDocType=(");
		hql.append(" select t.idVcDocType from VcDocVersionInfo t where t.docVerCode=?");
		hql.append(" )");
		hql.append(" and nv.businessNo=?");
		try{
			objList = this.findByHql(hql.toString(), versionCode,businessNo);
		}catch(Exception e){ 
			e.printStackTrace();
			throw new DaoException("查询业务号是否核销时异常！");
		}
		return  objList;
	}

	
}
	