package com.tapi.tcs.vc.webservice.provider.docDoReprinted.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.vc.schema.model.VcAbnormalVerification;
import com.tapi.tcs.vc.schema.model.VcNormalVerification;
import com.tapi.tcs.vc.webservice.provider.docDoReprinted.bean.DocDoReprintedRequest;
import com.tapi.tcs.vc.webservice.provider.docDoReprinted.dao.DocReprintDao;

public class DocReprintDaoImpl extends
		GenericDaoHibernate<VcNormalVerification> implements DocReprintDao {

	/**
	 * 根据单证类型代码、单证流水号、业务号、业务归属部门查询历史核销信息
	 * @param request
	 * @return result:true-业务号使用旧流水号核销记录存在;false-业务号使用旧流水号核销记录不存在
	 * @throws DaoException
	 */
	@Override
	public List<VcNormalVerification> checkIsVerificated(DocDoReprintedRequest request) throws DaoException {
		List<VcNormalVerification> list = null ;
		try {
			//modify by chy 业务号从明细表查询 begin
			/*QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("docVerCode", request.getRequestBody().getOldDocVerCode()); // 单证类型代码
			queryRule.addEqual("docNum", request.getRequestBody().getOldDocNum()); // 单证流水号
			queryRule.addEqual("businessNo", request.getRequestBody().getBusinessNo()); // 业务号
			queryRule.addEqual("verifiedOrgCode", request.getRequestBody().getOrgCode()); // 业务归属部门代码
			
			list = this.find(VcNormalVerification.class, queryRule);*/
			
			String docVerCode = request.getRequestBody().getOldDocVerCode();
			String pressBatchNo = request.getRequestBody().getOldPressBatchNo();
			if(pressBatchNo==null || pressBatchNo.trim().equals("")){
				pressBatchNo = "000";
			}
			String docNum = request.getRequestBody().getOldDocNum();
			String businessNo = request.getRequestBody().getBusinessNo();
			String verifiedOrgCode = request.getRequestBody().getOrgCode();
			
			StringBuffer hql = new StringBuffer("");
			hql.append(" from VcNormalVerification v where v.docVerCode=?");
			hql.append(" and v.pressBatchNo=?");
			hql.append(" and v.docNum=? ");
			hql.append(" and v.verifiedOrgCode=?");
			hql.append(" and exists(select 1 from VcNormalVerifiedDet det where " +
					"det.idVcNormalVerification=v.idVcNormalVerification and det.businessNo=?)");
			list = (List<VcNormalVerification>)this.findByHql(hql.toString(), docVerCode,pressBatchNo,docNum,verifiedOrgCode,businessNo);
			//modify by chy 业务号从明细表查询 end
		} catch (Exception e) {
			throw new DaoException("查找历史核销记录时发生异常！", e);
		}
		return list;
	}
	
	@Override
	public void saveNormalVerification(VcNormalVerification vcNormalVerification) throws DaoException {
		try{
			this.getHibernateTemplate().clear();
			this.save(vcNormalVerification);
		}catch(Exception e){
			throw new DaoException("保存数据出错！" ,e);
		}
	}
	
	@Override
	public void saveAbNormalVerification(VcAbnormalVerification vcAbnormalVerification) throws DaoException {
		try{
			this.getHibernateTemplate().clear();
			this.save(vcAbnormalVerification);
		}catch(Exception e){
			throw new DaoException("保存数据出错！" ,e);
		}
	}
}
