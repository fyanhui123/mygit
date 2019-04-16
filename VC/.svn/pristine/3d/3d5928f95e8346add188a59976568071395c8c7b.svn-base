package com.tapi.tcs.vc.webservice.provider.common.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.vc.schema.model.VcAvailableDoc;
import com.tapi.tcs.vc.schema.model.VcNormalVerification;
import com.tapi.tcs.vc.webservice.provider.common.dao.VcDocStatusDao;

/**
 * 单证状态查询公用方法DAO实现类
 * <p>
 * Date 2013-06-04
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
public class VcDocStatusDaoImpl extends GenericDaoHibernate<VcNormalVerification> implements VcDocStatusDao {

	@Override
	public List<Object[]> checkBizNoIsUsed(String businessNo, String payNo,
			String versionCode, String counteractFlag, String batchNo, String payerCode) throws DaoException {
		// TODO Auto-generated method stub
		List<Object[]> vcNormalVerificationList = null;
		StringBuffer hql = new StringBuffer("");
		List values = new ArrayList();
		hql.append(" from VcNormalVerification nv,VcDocVersionInfo ver");
		hql.append(" where nv.docVerCode=ver.docVerCode");
		hql.append(" and ver.idVcDocType=(");
		hql.append(" select t.idVcDocType from VcDocVersionInfo t where t.docVerCode=?");
		hql.append(" )");
		values.add(versionCode);
		//modify by chy 20130821 begin
		//hql.append(" and nv.businessNo=?");
		hql.append(" and exists(select 1 from VcNormalVerifiedDet det where ");
		hql.append(" det.idVcNormalVerification=nv.idVcNormalVerification and det.businessNo=?");
		values.add(businessNo);
		//add by chy 20130923 增加红冲处理 begin
		//红冲标志:0-非红冲；1-红冲负数；2-红冲正数
		if("1".equals(counteractFlag)){
			//红冲负数查询业务号核销记录时应排除红冲正数的业务号
			hql.append(" and det.counteractFlag<>?");
			values.add("2");
		}else if("2".equals(counteractFlag)){
			//红冲正数查询业务号核销记录时应排除红冲负数的业务号
			hql.append(" and det.counteractFlag<>?");
			values.add("1");
		}
		//add by chy 20130923 增加红冲处理 end
		//add by chy 20130926 增加拆分批次 begin
		if(StringUtils.isNotEmpty(batchNo)){
			hql.append(" and det.batchNo=?");
			values.add(batchNo);
		}
		//add by chy 20130926 增加拆分批次 end
		
		//modifY by zhxiao3 VC-129 发票打印新增字段“共保付款人” begin
		if(StringUtils.isNotEmpty(payerCode)){
			hql.append(" and det.payerCode=?");
			values.add(payerCode);
		}else{
			hql.append(" and det.payerCode is null");
		}
		//modifY by zhxiao3 VC-129 发票打印新增字段“共保付款人” end
		
		hql.append(" and det.payNo=?)");
		values.add(payNo);
		//modify by chy 20130821 end
		try{
			//vcNormalVerificationList = this.findByHql(hql.toString(), versionCode,businessNo,payNo);
			vcNormalVerificationList = this.findByHql(hql.toString(), values.toArray());
		}catch(Exception e){
			throw new DaoException("查询业务号是否核销时异常！", e);
		}
		return vcNormalVerificationList;
	}

	@Override
	public List<VcAvailableDoc> checkDocNumIsValid(String docNum, String versionCode,
			String takerCode, String orgCode, String pressBatchNo, String agentCode) throws DaoException {
		List<VcAvailableDoc> list = null;
		try{
			QueryRule queryRule = QueryRule.getInstance();
			//单证类型代码
			queryRule.addEqual("docVerCode", versionCode);
			//单证流水号
			queryRule.addEqual("docNum", docNum);
			//单证状态 A-可用
			queryRule.addEqual("docStatus", "A");
			//使用人代码
			if (!StringUtils.isEmpty(takerCode)){
				queryRule.addEqual("takerCode", takerCode);
			}
			//中介机构代码，外部接口新增字段，可为空
			if (!StringUtils.isEmpty(agentCode)){
				queryRule.addEqual("agentCode", agentCode);
			}
			//使用人归属机构代码
			queryRule.addEqual("orgCode", orgCode);
			//印刷批次
			if(StringUtils.isNotEmpty(pressBatchNo)){
				queryRule.addEqual("pressBatchNo", pressBatchNo);
			}
			list = this.find(VcAvailableDoc.class, queryRule);
		}catch(Exception e){
			throw new DaoException("查询流水号是否可用时发生异常！", e);
		}
		return list;
	}
	
	@Override
	public VcNormalVerification findVcNormalVerification(String docVerCode,String pressBatchNo,
			String docNum,String businessNo,String payNo, String batchNo, String counteractFlag)throws DaoException{
		VcNormalVerification vcNormalVerification = null;
		try{
			StringBuffer hql = new StringBuffer("");
			List values = new ArrayList();
			hql.append(" from VcNormalVerification nv");
			hql.append(" where nv.docVerCode=?");
			values.add(docVerCode);
			hql.append(" and nv.pressBatchNo=?");
			values.add(pressBatchNo);
			hql.append(" and nv.docNum=?");
			values.add(docNum);
			hql.append(" and exists(select 1 from VcNormalVerifiedDet det where ");
			hql.append(" det.idVcNormalVerification=nv.idVcNormalVerification and det.businessNo=? and det.payNo=?");
			values.add(businessNo);
			values.add(payNo);
			if(StringUtils.isNotEmpty(batchNo)){
				hql.append(" and det.batchNo=?");
				values.add(batchNo);
			}
			if(StringUtils.isNotEmpty(counteractFlag) && !"0".equals(counteractFlag)){
				hql.append(" and det.counteractFlag=?");
				values.add(counteractFlag);
			}
			hql.append(" )");
			//List<VcNormalVerification> list = this.findByHql(hql.toString(), docVerCode,pressBatchNo,docNum,businessNo,payNo);
			List<VcNormalVerification> list = this.findByHql(hql.toString(), values.toArray());
			if(list!=null && list.size()>0){
				vcNormalVerification = list.get(0);
			}
		}catch(Exception e){
			throw new DaoException("查询核销记录失败！", e);
		}
		return vcNormalVerification;
	}

}
