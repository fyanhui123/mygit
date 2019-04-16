package com.tapi.tcs.vc.common.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.vc.baseinfo.dao.VcLevelDao;
import com.tapi.tcs.vc.common.dao.ApproveDao;
import com.tapi.tcs.vc.common.dao.VcPubCodeManagerDao;
import com.tapi.tcs.vc.schema.model.VcApprove;

/**
 * 公用审批DAO实现类
 * <p>
 * Date 2013-03-20
 * </p>
 * <p>
 * Module：公共模块
 * </p>
 * <p>
 * Description：
 * </p>
 * <p>
 * Remark：
 * </p>
 * 
 * @author chy
 * @version 1.0
 */
public class ApproveDaoImpl extends GenericDaoHibernate<VcApprove> implements
		ApproveDao {
	
	
	private VcLevelDao vcLevelDao;

	/**
	 * 根据申请编号、申请类型查询审批记录
	 */
	@Override
	public List<VcApprove> queryVcApprove(Long applyId, String appType) throws DaoException {
		// TODO Auto-generated method stub
		List<VcApprove> list = new ArrayList<VcApprove>();
		try{
			QueryRule queryRule = QueryRule.getInstance();
			// 增加申请编号的查询条件
			queryRule.addEqual("applyId", applyId);
			//增加申请类型查询条件
			queryRule.addEqual("applyType", appType);
			// 根据时间升序排列
			queryRule.addAscOrder("checkTime");
	
			list = super.find(VcApprove.class, queryRule);
		}catch(Exception e){
			throw new DaoException("查询审批记录失败！", e);
		}
		return list;
	}

	/**
	 * 保存审批记录
	 */
	@Override
	public void saveVcApprove(VcApprove vcApprove) throws DaoException {
		// TODO Auto-generated method stub
		try{
			this.save(vcApprove);
		}catch(Exception e){
			throw new DaoException("保存审批记录时发生异常！", e);
		}
	}

	/**
	 * 删除审批记录
	 */
	public void deleteVcApprove(List<VcApprove> vcApproveList) throws DaoException {
		try{
			this.deleteAll(vcApproveList);
		}catch(Exception e){
			throw new DaoException("删除审批记录时发生异常！", e);
		}
	}

	private VcPubCodeManagerDao vcPubCodeManagerDao;

	/**
	 * @throws BusinessException
	 * 
	 */
	@Override
	public List<VcApprove> queryApproves(String applyType, Long applyId)
			throws DaoException {
		List<VcApprove> vcApproves  = null;
		try{
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("applyId", applyId);
			queryRule.addEqual("applyType", applyType);
			// 按提交时间排序
			queryRule.addDescOrder("checkTime");
			vcApproves = this.find(queryRule);
			for (Iterator iterator = vcApproves.iterator(); iterator.hasNext();) {
				VcApprove vcApprove = (VcApprove) iterator.next();
				vcApprove.setCheckStatus(vcPubCodeManagerDao.getCodeCname(
						"CheckState", vcApprove.getCheckStatus()));
				vcApprove.setCheckOprId(vcLevelDao.getUnitNameByUnitCode(vcApprove.getCheckOprId()));
				vcApprove.setCheckOrgId(vcLevelDao.getUnitNameByUnitCode(vcApprove.getCheckOrgId()));
			}
		}catch(Exception e){
			throw new DaoException("查询审批记录时发生异常！", e);
		}
		return vcApproves;
	}

	public void setVcPubCodeManagerDao(VcPubCodeManagerDao vcPubCodeManagerDao) {
		this.vcPubCodeManagerDao = vcPubCodeManagerDao;
	}

	public void setVcLevelDao(VcLevelDao vcLevelDao) {
		this.vcLevelDao = vcLevelDao;
	}
}
