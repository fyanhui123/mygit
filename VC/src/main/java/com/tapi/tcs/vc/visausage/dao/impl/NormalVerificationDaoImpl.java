/**
 * 
 */
package com.tapi.tcs.vc.visausage.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.vc.schema.model.VcNormalVerification;
import com.tapi.tcs.vc.schema.model.VcNormalVerifiedDet;
import com.tapi.tcs.vc.visausage.dao.NormalVerificationDao;

/**
 * @author hanmiao.diao
 *
 */
public class NormalVerificationDaoImpl extends GenericDaoHibernate<VcNormalVerification> implements NormalVerificationDao {

	@SuppressWarnings("rawtypes")
	@Override
	public List findNormalVerificationList(Map<String, Object> params) throws DaoException {
		List rs = null;
		QueryRule queryRule = null;
		try {
			queryRule = QueryRule.getInstance();
			String verifiedOrgCode = (String) params.get("verifiedOrgCode");
			if(StringUtils.isNotEmpty(verifiedOrgCode)) {
				//queryRule.addEqual("verifiedOrgCode", verifiedOrgCode);
			   StringBuffer sb=new StringBuffer(128);
			   sb.append(" EXISTS ( SELECT T.ID_VC_LEVEL  FROM VC_LEVEL T ");
			   sb.append(" WHERE T.UNIT_TYPE = '0' AND T.UNIT_CODE=VERIFIED_ORG_CODE " );
			   sb.append(" START WITH T.UNIT_CODE = '").append(verifiedOrgCode).append("' ");
			   sb.append(" CONNECT BY T.PARENT_ORG_ID = PRIOR T.ID_VC_LEVEL )");
			   queryRule.addSql(sb.toString());
			} else {
				throw new DaoException("查询参数所属机构[verifiedOrgCode]缺失");
			}
			
			String docVerCode = (String) params.get("docVerCode");
			if(StringUtils.isNotEmpty(docVerCode)) {
				queryRule.addEqual("docVerCode", docVerCode);
			} else {
				throw new DaoException("查询参数单证类型[docVerCode]缺失");
			}
			
			String pressBatchNo = (String) params.get("pressBatchNo");
			if(StringUtils.isNotEmpty(pressBatchNo)) {
				queryRule.addEqual("pressBatchNo", pressBatchNo);
			} else {
				throw new DaoException("查询参数印刷批次[pressBatchNo]缺失");
			}
			
			String docNumStart = (String) params.get("docNumStart");
			if(StringUtils.isNotEmpty(docNumStart)) {
				queryRule.addGreaterEqual("docNum", docNumStart);
			} else {
				throw new DaoException("查询参数起始流水号[docNumStart]缺失");
			}
			
			String docNumEnd = (String) params.get("docNumEnd");
			if(StringUtils.isNotEmpty(docNumEnd)) {
				queryRule.addLessEqual("docNum", docNumEnd);
			} else {
				throw new DaoException("查询参数终止流水号[docNumEnd]缺失");
			}
			
			String docStatus = (String) params.get("docStatus");
			if(StringUtils.isNotEmpty(docStatus)) {
				queryRule.addIn("docStatus", docStatus.split(","));
			} else {
				throw new DaoException("查询参数单证状态[docStatus]缺失");
			}
			
			rs = this.find(VcNormalVerification.class, queryRule);
		} catch (Exception e) {
			throw new DaoException("查询数据出错！", e);
		}
		return rs;
	}
	
	@Override
	public VcNormalVerification findVcNormalVerification(String docVerCode,
			String docNum,String pressBatchNo,String businessNo,String payNo) throws DaoException {
		VcNormalVerification vcNormalVerification = null;
		try{
			StringBuffer hql = new StringBuffer("");
			hql.append(" from VcNormalVerification v where v.docVerCode=?");
			hql.append(" and v.docNum=? ");
			if(StringUtils.isNotEmpty(pressBatchNo)){
				hql.append(" and v.pressBatchNo='"+pressBatchNo+"'");
			}
			//对于分期缴费，查询核销记录时不增加业务号的查询条件 
			if("1".equals(payNo)){//非分期缴费
				hql.append(" and exists(select 1 from VcNormalVerifiedDet det where ");
				hql.append(" det.idVcNormalVerification=v.idVcNormalVerification and det.businessNo='"+businessNo+"')");
			}
			/*hql.append(" and v.docStatus=?");
			List<VcNormalVerification> list = (List<VcNormalVerification>)this.findByHql(hql.toString(), docVerCode,docNum,"U1");*/
			hql.append(" and v.docStatus in (?,?,?)");
			List<VcNormalVerification> list = (List<VcNormalVerification>)this.findByHql(hql.toString(), docVerCode,docNum,"U1","U2","U3");
			if(list!=null && list.size()>0	){
				vcNormalVerification = list.get(0);
			}
		}catch(Exception e){
			throw new DaoException("查询数据出错！", e);
		}
		return vcNormalVerification;
	}
	
	@Override
	public VcNormalVerification findVcNormalVerification(String docVerCode,
			String docNum,String pressBatchNo,String businessNo) throws DaoException {
		VcNormalVerification vcNormalVerification = null;
		try{
			StringBuffer hql = new StringBuffer("");
			hql.append(" from VcNormalVerification v where v.docVerCode=?");
			hql.append(" and v.docNum=? ");
			if(StringUtils.isNotEmpty(pressBatchNo)){
				hql.append(" and v.pressBatchNo='"+pressBatchNo+"'");
			}
			hql.append(" and exists(select 1 from VcNormalVerifiedDet det where " +
					"det.idVcNormalVerification=v.idVcNormalVerification ");
			
			if(StringUtils.isNotEmpty(businessNo)){
				hql.append(" and det.businessNo= ? )");
			}else{
				hql.append(")");
			}
			hql.append(" and v.docStatus in (?,?,?)");
			List<VcNormalVerification> list;
			if(StringUtils.isNotEmpty(businessNo)){
				list = (List<VcNormalVerification>)this.findByHql(hql.toString(), docVerCode,docNum,businessNo,"U1","U2","U3");
			}else{
				list = (List<VcNormalVerification>)this.findByHql(hql.toString(), docVerCode,docNum,"U1","U2","U3");
			}
			if(list!=null && list.size()>0	){
				vcNormalVerification = list.get(0);
			}
		}catch(Exception e){
			throw new DaoException("查询数据出错！", e);
		}
		return vcNormalVerification;
	}
	@Override
	public List <VcNormalVerifiedDet> findVcNormalVerifiedDet(
			Long  idVcNormalverfication) throws DaoException {
		    List<VcNormalVerifiedDet> list;
		try {
			StringBuffer hql = new StringBuffer("");
			hql.append(" from VcNormalVerifiedDet t where t.idVcNormalVerification= ? ");
		    list = (List<VcNormalVerifiedDet>)this.findByHql(hql.toString(), idVcNormalverfication);
		} catch (Exception e) {
			throw new DaoException("查询数据出错！", e);
		}
		return list;
	}
}
