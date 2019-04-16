package com.tapi.tcs.vc.webservice.provider.docVerInquiry.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.common.util.SysConst;
import com.tapi.tcs.vc.schema.model.VcDocVersionInfo;
import com.tapi.tcs.vc.webservice.provider.docVerInquiry.bean.DocVersionInfoDTO;
import com.tapi.tcs.vc.webservice.provider.docVerInquiry.dao.DocVerInquiryDao;

public class DocVerInquiryDaoImpl extends GenericDaoHibernate<VcDocVersionInfo> implements DocVerInquiryDao{
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> docVerInquiry(DocVersionInfoDTO info)
			throws DaoException {
		List<Object[]> resultList = null;
        StringBuffer hql=new StringBuffer();
        hql.append(" from  VcDocPrintSet pri,VcDocVersionInfo doc");
        hql.append(" where pri.idVcDocVersionInfo=doc.idVcDocVersionInfo");
       //模板代码
        hql.append(" and pri.templateCode='"+info.getTemplateCode()+"'");
        // 地区
        StringBuffer  orgSB=new StringBuffer();
        for(String orgCode : info.getOrgCode()){
			orgSB.append(",'"+orgCode+"'");
		}
        hql.append(" and exists(select 1 from VcDocRelArea area where area.idVcDocVersionInfo=doc.idVcDocVersionInfo ");
        hql.append(" and area.orgCode in("+orgSB.substring(1)+") )");
        //险类代码
        hql.append(" and  exists(select 1 from VcDocRelInsuKind kind where pri.insuKindCode=kind.insuKindCode and  kind.idVcDocVersionInfo=doc.idVcDocVersionInfo ");
        hql.append(" and (kind.insuKindCode='"+SysConst.ALL_KIND_CODE+"' or  kind.insuKindCode='"+info.getInsuKindCode()+"'))");
       //单证种类
        hql.append(" and exists(select 1 from  VcDocType vc where vc.idVcDocType=doc.idVcDocType ");
        hql.append(" and vc.docTypeCode='"+info.getDocTypeCode()+"')");
        //排除无效的单证类型
        hql.append(" and doc.status='1'");
		try{
			resultList = this.findByHql(hql.toString(), null); 
		}catch(Exception e){
			e.printStackTrace();
			throw new DaoException("查询数据库异常！");
		}
		return resultList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<VcDocVersionInfo> docVerInquiryForPublic(DocVersionInfoDTO info)
			throws DaoException {
		List<VcDocVersionInfo> resultList = null;
		List<Object> values =new ArrayList<Object>();
        StringBuffer hql=new StringBuffer();
        hql.append(" from VcDocVersionInfo doc where 1=1 ");
        
        hql.append(" and exists(select 1 from VcDocRelArea area where area.idVcDocVersionInfo=doc.idVcDocVersionInfo ");
        hql.append(" and area.orgCode in(? ) )");
        values.add(info.getOrgCode());
        //险类代码
        hql.append(" and  exists(select 1 from VcDocRelInsuKind kind where	 kind.idVcDocVersionInfo=doc.idVcDocVersionInfo ");
        hql.append(" and (kind.insuKindCode=? or  kind.insuKindCode=?))");
        values.add(SysConst.ALL_KIND_CODE);
        values.add(info.getInsuKindCode());
        
       //单证种类
        hql.append(" and exists(select 1 from  VcDocType vc where vc.idVcDocType=doc.idVcDocType ");
        hql.append(" and vc.docTypeCode=?)");
        values.add(info.getDocTypeCode());
        //排除无效的单证类型
        hql.append(" and doc.status=? ");
        values.add("1");
		try{
			resultList = this.findByHql(hql.toString(), values.toArray()); 
		}catch(Exception e){
			e.printStackTrace();
			throw new DaoException("查询数据库异常！");
		}
		return resultList;
	}
}
