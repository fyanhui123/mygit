package com.tapi.tcs.vc.webservice.provider.common.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.vc.schema.model.PubCompany;
import com.tapi.tcs.vc.schema.model.PubCompanyMap;
import com.tapi.tcs.vc.schema.model.PubUserDef;
import com.tapi.tcs.vc.schema.model.VcDocInsuKind;
import com.tapi.tcs.vc.schema.model.VcDocInsuType;
import com.tapi.tcs.vc.schema.model.VcDocType;
import com.tapi.tcs.vc.schema.model.VcDocVersionInfo;
import com.tapi.tcs.vc.schema.model.VcInvoicePrint;
import com.tapi.tcs.vc.schema.model.VcTaker;
import com.tapi.tcs.vc.webservice.provider.common.dao.VcBaseInfoDao;

/**
 * 基础代码处理DAO实现类
 * <p>
 * Date 2013-05-30
 * </p>
 * <p>
 * Module：Webservice-公共模块
 * </p>
 * <p>
 * Description：
 * </p>
 * <p>
 * Remark：
 * </p>
 * @author chy
 * @version 1.0
 */
public class VcBaseInfoDaoImpl extends GenericDaoHibernate<VcDocVersionInfo> implements VcBaseInfoDao {

	/*@Override
	public List<Long> translateDocTypeId(List<Long> oldId) throws DaoException {
		List<Long> docTypeId = new ArrayList<Long>();
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addIn("typeKindId", oldId);
		List<VcDocType> vcDocTypeList = (List<VcDocType>)this.find(VcDocType.class, queryRule);
		if(vcDocTypeList!=null && vcDocTypeList.size()>0){
			for(VcDocType vcDocType : vcDocTypeList){
				docTypeId.add(vcDocType.getIdVcDocType());
			}
		}
		return docTypeId;
	}*/

	@Override
	public Long translateInsuTypeId(Long categoryId) throws DaoException {
		Long idVcDocInsuType = 0L;
		try{
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("categoryId", categoryId);
			VcDocInsuType vcDocInsuType = (VcDocInsuType)this.findUnique(VcDocInsuType.class, queryRule);
			if(vcDocInsuType!=null)
				idVcDocInsuType=vcDocInsuType.getIdVcDocInsuType();
		}catch(Exception e){
			throw new DaoException("查询数据库发生异常！");
		}
		return idVcDocInsuType;
	}
	
	@Override
	public String translateInsuKindCode(Long productId, Long insuTypeId) throws DaoException {
		String insuKindCode = "";
		try{
			QueryRule queryRule = QueryRule.getInstance();
			//对应的老系统产品ID
			queryRule.addEqual("productId", productId);
			//归属险类ID
			if(insuTypeId!=null && insuTypeId>0){
				queryRule.addEqual("idVcDocInsuType", insuTypeId);
			}
			VcDocInsuKind vcDocInsuKind = (VcDocInsuKind)this.findUnique(VcDocInsuKind.class, queryRule);
			if(vcDocInsuKind!=null)
				insuKindCode=vcDocInsuKind.getInsuKindCode();
		}catch(Exception e){
			throw new DaoException("查询数据库发生异常！");
		}
		return insuKindCode;
	}

	@Override
	public List<String> getOrgCodeListByOldId(Long orgId, String orgCode) throws DaoException {
		List<String> list = null;
		try{
			//递归往上查找机构
			String sql = "select t1.unit_code from vc_level t1 where t1.unit_type='0' and t1.valid_status='1' " +
					" start with t1.unit_code=(select companyCode from pub_company_map c where c.companyid_old= "+orgId+
					" and c.companycode_old='"+orgCode+"')"+
					" connect by t1.id_vc_level=prior t1.parent_org_id";
			list = this.findBySql(sql, null);
		}catch(Exception e){
			throw new DaoException("查询数据库发生异常！");
		}
		return list;
	}
	
	@Override
	public String translateOrgCode(Long orgId, String orgCode) throws DaoException {
		String orgCodeNew = "";
		try{
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("id.companyIdOld", orgId.intValue());
			queryRule.addEqual("id.companyCodeOld", orgCode);
			PubCompanyMap pubCompanyMap = (PubCompanyMap)this.findUnique(PubCompanyMap.class, queryRule);
			if(pubCompanyMap!=null && StringUtils.isNotEmpty(pubCompanyMap.getCompanyCode())){
				orgCodeNew = pubCompanyMap.getCompanyCode();
			} /*else {
				throw new DaoException("无此机构代码！");
			}*/
		}catch(Exception e){
			throw new DaoException("查询数据库发生异常！",e);
		}
		if(StringUtils.isBlank(orgCodeNew)){
			throw new DaoException("无此机构代码！");
		}
		return orgCodeNew;
	}

	@Override
	public String translateUserCode(Long userId) throws DaoException {
		String userCode="";
		try {
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("oldEmployeeId", userId); 
			queryRule.addEqual("validFlag", "1");
			PubUserDef pubUserDef = (PubUserDef)this.findUnique(PubUserDef.class, queryRule); 
			if(pubUserDef!=null && StringUtils.isNotEmpty(pubUserDef.getUserCode())) {
				//userCode=pubUserDef.getUserCode();
				userCode=pubUserDef.getEmployeeId();
			} /*else {
				throw new DaoException("无此操作员代码！");
			}*/
		}catch (Exception e) {
			throw new DaoException("查询数据库发生异常！");
		}
		if(StringUtils.isBlank(userCode)){
			throw new DaoException("无此操作员代码！");
		}
		return userCode; 
	}
	
	@Override
	public PubUserDef getPubUserDefByOldId(Long userId) throws DaoException {
		PubUserDef pubUserDef = null;
		try{
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("oldEmployeeId", userId); 
			queryRule.addEqual("validFlag", "1");
			pubUserDef = (PubUserDef)this.findUnique(PubUserDef.class, queryRule); 
		}catch(Exception e){
			throw new DaoException("查询数据库发生异常！");
		}
		if(pubUserDef==null){
			throw new DaoException("无此操作员代码！");
		}
		return pubUserDef;
	}

	@Override
	public String getDocVerCode(long docVerID) throws DaoException {
		String docVerCode = null;
		try {
			QueryRule queryRule = QueryRule.getInstance();
			//单证类型ID
			queryRule.addEqual("idVcDocVersionInfo", docVerID);
			VcDocVersionInfo vcDocVersionInfo = (VcDocVersionInfo) this.findUnique(VcDocVersionInfo.class, queryRule);
			if (vcDocVersionInfo != null && StringUtils.isNotEmpty(vcDocVersionInfo.getDocVerCode())) {
				docVerCode = vcDocVersionInfo.getDocVerCode();
			} /*else {
				throw new DaoException("无此单证类型代码！");
			}*/
		} catch (Exception e) {
			throw new DaoException("查询数据库发生异常！");
		}
		if(StringUtils.isBlank(docVerCode)){
			throw new DaoException("无此单证类型代码！");
		}
		return docVerCode;

	}
	
	/**
	 * 根据机构代码，查找本级及所有上级机构代码列表
	 * @param orgCode
	 * @return
	 * @throws DaoException
	 */
	@Override
	public List<String> getOrgCodeListByOrgCode(String orgCode)
			throws DaoException {
		List<String> list = null;
		try{
		    StringBuffer sql=new StringBuffer();
		    List<Object> values=new ArrayList<Object>();
			//递归往上查找机构
			sql.append(" SELECT Z.UNIT_CODE  FROM VC_LEVEL Z ");
			sql.append(" WHERE Z.UNIT_TYPE = ?  and Z.valid_status= ? ");
			values.add("0");
			values.add("1");
			sql.append(" START WITH Z.UNIT_CODE=? ");
			values.add(orgCode);
			sql.append(" CONNECT BY  Z.ID_VC_LEVEL =PRIOR  Z.PARENT_ORG_ID ");
			list = this.findBySql(sql.toString(), values.toArray());
		}catch(Exception e){
			throw new DaoException("查询数据库发生异常！");
		}
		return list;
	}
	
	
	
	/*@Override
	public String getCompanyId(String companyCode) throws DaoException {
		String companyId = null;
		try{
			QueryRule queryRule = QueryRule.getInstance();
			//新系统机构代码
			queryRule.addEqual("companyCode", companyCode);
			PubCompanyMap pubCompanyMap = (PubCompanyMap)this.findUnique(PubCompanyMap.class, queryRule);
			if(pubCompanyMap!=null && StringUtils.isNotEmpty(pubCompanyMap.getCompanyIdOld())){
				//返回老系统对应的机构id
				companyId = pubCompanyMap.getCompanyIdOld();
			}
			if(pubCompanyMap!=null && pubCompanyMap.getId()!=null
					&& StringUtils.isNotEmpty(pubCompanyMap.getId().getCompanyIdOld())){
				//返回老系统对应的机构id
				companyId = pubCompanyMap.getId().getCompanyIdOld();
			}
		}catch(Exception e){
			throw new DaoException("查询数据库失败！", e);
		}
		return companyId;
	}*/
	
	/**
	 * 根据单证类型代码，返回单证类型
	 * @param docVerCode
	 * @return
	 * @throws DaoException
	 */
	@Override
	public List<VcDocType> getVcDocType(String docVerCode) throws DaoException{
		List<VcDocType> list = null;
		String docType = null;
		try{
		StringBuffer hql = new StringBuffer("");
		hql.append(" FROM VcDocType t");
		hql.append(" WHERE EXISTS (");
		hql.append(" SELECT 1 FROM VcDocVersionInfo v WHERE v.idVcDocType = t.idVcDocType ");
		hql.append(" and v.docVerCode = ? ");
		hql.append(" )");
		list = (List<VcDocType>)this.findByHql(hql.toString(), docVerCode);
		}catch(Exception e){
			throw new DaoException("查询单证种类信息出错了！", e);
		}
		return list;
	}
	
	@Override
	public VcInvoicePrint findVcInvoicePrint(String orgCode, String invoiceCode, String invoiceNo, String printKind) throws DaoException {
		VcInvoicePrint vcInvoicePrint = null;
		try{
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("orgCode", orgCode);
			queryRule.addEqual("invoiceCode", invoiceCode);
			queryRule.addEqual("invoiceNo", invoiceNo);
			if(StringUtils.isNotEmpty(printKind)){
				queryRule.addEqual("printKind", printKind);
			}
			queryRule.addEqual("status", "1");
			List<VcInvoicePrint> vcInvoicePrintList = this.find(VcInvoicePrint.class, queryRule);
			if(vcInvoicePrintList!=null && vcInvoicePrintList.size()!=0){
				vcInvoicePrint = vcInvoicePrintList.get(0);
			}
		}catch(Exception e){
			throw new DaoException("查询发票开具信息出错！", e);
		}
		return vcInvoicePrint;
	}
	
	@Override
	public String findUserNameByEmployeeId(String employeeId) throws DaoException{
		String userName = "";
		try{
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("employeeId", employeeId); 
			queryRule.addEqual("validFlag", "1");
			PubUserDef pubUserDef = (PubUserDef)this.findUnique(PubUserDef.class, queryRule); 
			if(pubUserDef!=null){
				userName = pubUserDef.getUserName();
			}
		}catch (Exception e) {
			throw new DaoException("查询数据库发生异常！");
		}
		//if(StringUtils.isBlank(userName)){
		//	throw new DaoException("无此操作员代码！");
		//}
		return userName;
	}
	
	@Override
	public String findCompanyNameByCode(String companyCode) throws DaoException{
		String companyName = "";
		try{
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("companyCode", companyCode); 
			queryRule.addEqual("validInd", "1");
			PubCompany pubCompany= (PubCompany)this.findUnique(PubCompany.class, queryRule);
			if(pubCompany!=null){
				companyName = pubCompany.getCompanyCname();
			}
		}catch(Exception e){
			throw new DaoException("查询数据库发生异常！");
		}
		return companyName;
	}
}
