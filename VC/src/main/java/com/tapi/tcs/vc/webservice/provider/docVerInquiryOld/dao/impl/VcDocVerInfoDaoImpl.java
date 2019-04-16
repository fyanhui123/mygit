package com.tapi.tcs.vc.webservice.provider.docVerInquiryOld.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.vc.baseinfo.dao.VcLevelDao;
import com.tapi.tcs.vc.common.util.SysConst;
import com.tapi.tcs.vc.schema.model.VcDocPrintSet;
import com.tapi.tcs.vc.schema.model.VcDocVersionInfo;
import com.tapi.tcs.vc.schema.model.VcLevel;
import com.tapi.tcs.vc.webservice.provider.docVerInquiryOld.dao.VcDocVerInfoDao;
import com.tapi.tcs.vc.webservice.provider.docVerInquiryOld.vo.DocVerInquiryRequestVO;

public class VcDocVerInfoDaoImpl extends GenericDaoHibernate<VcDocVersionInfo> implements VcDocVerInfoDao {

	private VcLevelDao vcLevelDao;
	/*@Override
	public List<Object[]> docVerInquiry(DocVerInquiryRequestVO requestVO)
			throws DaoException {
		List<Object[]> resultList = null;
		
		StringBuffer orgSB = new StringBuffer("");
		StringBuffer docTypeId = new StringBuffer("");
		//机构列表
		for(String orgCode : requestVO.getOrgCodeList()){
			orgSB.append(",'"+orgCode+"'");
		}
		//单证种类列表
		for(Long docType : requestVO.getIdVcDocTypeList()){
			docTypeId.append(","+docType);
		}
		
		StringBuffer hql = new StringBuffer("");
		hql.append(" from VcDocVersionInfo ver,VcDocRelInsuKind kind,VcDocRelArea area,VcDocType type");
		hql.append(" where ver.idVcDocVersionInfo=kind.idVcDocVersionInfo ");
		hql.append(" and ver.idVcDocVersionInfo=area.idVcDocVersionInfo");
		hql.append(" and ver.idVcDocType=type.idVcDocType");
		hql.append(" and area.orgCode in(");
		//机构列表
		hql.append(orgSB.substring(1));
		hql.append(" )");
		hql.append(" and type.idVcDocType in (");
		//单证种类
		hql.append(docTypeId.substring(1));
		hql.append(" )");
		//险种代码
		//hql.append(" and kind.insuKindCode='"+requestVO.getInsuKindCode()+"'");
		//0000-全险种
		hql.append(" and (kind.insuKindCode='"+SysConst.ALL_KIND_CODE+"' or kind.insuKindCode='"+requestVO.getInsuKindCode()+"')");
		//单证类型ID
		if(requestVO.getIdVcDocVer()!=null && requestVO.getIdVcDocVer()>0){
			hql.append(" and ver.idVcDocVersionInfo = "+requestVO.getIdVcDocVer());
		}
		try{
			resultList = this.findByHql(hql.toString(), null);
		}catch(Exception e){
			e.printStackTrace();
			throw new DaoException("查询数据库异常！");
		}
		return resultList;
	}*/
	@Override
	public List<Object[]> docVerInquiry(DocVerInquiryRequestVO requestVO)
			throws DaoException {
		List<Object[]> resultList = null;
		
		StringBuffer hql = new StringBuffer("");
		StringBuffer orgSB = new StringBuffer("");
		StringBuffer docTypeId = new StringBuffer("");
		List values = new ArrayList();
		//机构列表
		/*for(String orgCode : requestVO.getOrgCodeList()){
			orgSB.append(",'"+orgCode+"'");
		}*/
		int orgSize = requestVO.getOrgCodeList().size();
		for(int i=0;i<orgSize;i++){
			orgSB.append(",?");
		}
		//单证种类列表
		/*for(Long docType : requestVO.getIdVcDocTypeList()){
			docTypeId.append(","+docType);
		}*/
		int docTypeSize = requestVO.getIdVcDocTypeList().size();
		for(int j=0;j<docTypeSize;j++){
			docTypeId.append(",?");
		}
		
		hql.append(" from VcDocVersionInfo ver,VcDocType type");
		hql.append(" where ver.idVcDocType=type.idVcDocType");
		hql.append(" and exists");
		hql.append("   (select 1 from VcDocRelArea area where ver.idVcDocVersionInfo=area.idVcDocVersionInfo");
		hql.append("    and area.orgCode in("+orgSB.substring(1)+")");
		//机构列表
		values.addAll(requestVO.getOrgCodeList());
		hql.append("    )");
		hql.append(" and exists");
		hql.append("   (select 1 from VcDocRelInsuKind kind where ver.idVcDocVersionInfo=kind.idVcDocVersionInfo");
		//hql.append("    and (kind.insuKindCode='"+SysConst.ALL_KIND_CODE+"' or kind.insuKindCode='"+requestVO.getInsuKindCode()+"')");
		hql.append("    and (kind.insuKindCode=? or kind.insuKindCode=?)");
		values.add(SysConst.ALL_KIND_CODE);
		values.add(requestVO.getInsuKindCode());
		hql.append("    )");
		hql.append(" and type.idVcDocType in ("+docTypeId.substring(1)+")");
		//单证种类
		values.addAll(requestVO.getIdVcDocTypeList());
		//单证类型ID
		if(requestVO.getIdVcDocVer()!=null && requestVO.getIdVcDocVer()>0){
			//hql.append(" and ver.idVcDocVersionInfo = "+requestVO.getIdVcDocVer());
			hql.append(" and ver.idVcDocVersionInfo = ?");
			values.add(requestVO.getIdVcDocVer());
		}
		//有效标志
		hql.append(" and ver.status=? and type.status=? ");
		values.add("1");
		values.add("1");
		try{
			resultList = this.findByHql(hql.toString(), values.toArray());
		}catch(Exception e){
			throw new DaoException("查询数据库异常！", e);
		}
		return resultList;
	}

	@Override
	public VcDocPrintSet queryVcDocPrintSet(Long idVcDocVersioninfo,
			String insuKindCode, String orgCode) throws DaoException {
		VcDocPrintSet vcDocPrintSet = null;
		try{
			/*QueryRule queryRule = QueryRule.getInstance();
			//单证类型流水
			queryRule.addEqual("idVcDocVersionInfo", idVcDocVersioninfo);
			//险种代码
			//queryRule.addEqual("insuKindCode", insuKindCode);
			String sql1 = " (INSU_KIND_CODE='"+SysConst.ALL_KIND_CODE+"' or INSU_KIND_CODE='"+insuKindCode+"')";
			queryRule.addSql(sql1);
			//机构代码
			//queryRule.addEqual("orgCode", orgCode);
			String sql2 = " org_code in (" +
					" select unit_code from vc_level v where v.unit_type='0' and v.valid_status='1'" +
					" START WITH v.UNIT_CODE = '"+orgCode+"' " +
					" CONNECT BY PRIOR v.PARENT_ORG_ID = v.ID_VC_LEVEL )" ;
			queryRule.addSql(sql2);
			//有效标志
			queryRule.addEqual("status", "1");
			List<VcDocPrintSet> list = (List<VcDocPrintSet>)this.find(VcDocPrintSet.class, queryRule);*/
			
			StringBuffer hql = new StringBuffer();
			List values = new ArrayList();
			List<VcLevel> vcLevelList = vcLevelDao.queryUpOrgListByUnitCode(orgCode, 0);
			hql.append(" from VcDocPrintSet p where p.idVcDocVersionInfo=?");
			values.add(idVcDocVersioninfo);
			hql.append(" and (p.insuKindCode=? or p.insuKindCode=?)");
			values.add(SysConst.ALL_KIND_CODE);
			values.add(insuKindCode);
			if(vcLevelList!=null && vcLevelList.size()!=0){
				String orgHql = "";
				for(VcLevel vcLevel : vcLevelList){
					orgHql += "?,";
					values.add(vcLevel.getUnitCode());
				}
				orgHql = orgHql.substring(0, orgHql.length()-1);
				hql.append(" and p.orgCode in ("+orgHql+")");
			}
			hql.append(" and p.status=? ");
			values.add("1");
			List<VcDocPrintSet> list = (List<VcDocPrintSet>)this.findByHql(hql.toString(), values.toArray());
			
			if(list!=null && list.size()>0){
				vcDocPrintSet = list.get(0);
			}
		}catch(Exception e){
			throw new DaoException("查询数据库异常！", e);
		}
		return vcDocPrintSet;
	}

	public void setVcLevelDao(VcLevelDao vcLevelDao) {
		this.vcLevelDao = vcLevelDao;
	}
}