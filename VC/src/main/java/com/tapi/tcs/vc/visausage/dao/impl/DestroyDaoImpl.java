package com.tapi.tcs.vc.visausage.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.vc.baseinfo.dao.VcDocVersionInfoDao;
import com.tapi.tcs.vc.baseinfo.dao.VcLevelDao;
import com.tapi.tcs.vc.common.dao.ApproveDao;
import com.tapi.tcs.vc.common.dao.VcPubCodeManagerDao;
import com.tapi.tcs.vc.common.util.DateUtils;
import com.tapi.tcs.vc.schema.model.VcApprove;
import com.tapi.tcs.vc.schema.model.VcDestroy;
import com.tapi.tcs.vc.schema.model.VcDestroyDet;
import com.tapi.tcs.vc.visausage.dao.DestroyDao;

public class DestroyDaoImpl extends GenericDaoHibernate<VcDestroy> implements DestroyDao {

	private VcLevelDao vcLevelDao;
	private ApproveDao approveDao;
	private VcPubCodeManagerDao vcPubCodeManagerDao;
	private VcDocVersionInfoDao vcDocVersionInfoDao;

	@Override
	public void updateBySQL(String sql, Object... params) {
		super.executeUpdateBySql(sql, params);
	}

	@Override
	public void lockVcDestroy(Long id) throws DaoException {
		try{
			String sql = "SELECT * FROM vc_destroy t WHERE t.id_vc_destroy = ? FOR UPDATE ";
			Object[] params = { id };
			this.findBySql(sql, params);
		} catch (Exception e) {
			throw new DaoException("锁表发生异常！", e);
		}
	}

	@Override
	public Page queryDestroy(VcDestroy vcDestroy, int pageNo, int pageSize, String action) throws DaoException {
		Page page = null;
		try{
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addIsNull("invoiceFlag");
			if ("approve".equals(action)) {
				
				queryRule.addEqual("approveOrgCode", vcDestroy.getApproveOrgCode());
			} else {
				// 查出本机构
				queryRule.addEqual("destroyOrgCode", vcDestroy.getDestroyOrgCode());
			}
	
			// 销毁单号
			if (StringUtils.isNotEmpty(vcDestroy.getDestroyCode())) {
				queryRule.addEqual("destroyCode", vcDestroy.getDestroyCode());
			}
			// 申请单状态 0删除/1新建/2待审批/3审批退回/4审批通过
			if (StringUtils.isNotEmpty(vcDestroy.getDestroyStatus())) {
				queryRule.addEqual("destroyStatus", vcDestroy.getDestroyStatus());
			} else {
				if ("approve".equals(action)) {
					Object[] destroyStatus = { "2", "3", "4" };
					queryRule.addIn("destroyStatus", destroyStatus);
				}
			}
	
			// 申请日期
			if (vcDestroy.getApplyStartDate() != null) {
				queryRule.addGreaterEqual("destroyAppTime", vcDestroy.getApplyStartDate());
			}
			if (vcDestroy.getApplyEndDate() != null) {
				queryRule.addLessThan("destroyAppTime", DateUtils.addDay(vcDestroy.getApplyEndDate(), 1));
			}
	
			page = this.find(queryRule, pageNo, pageSize);
	
			List<VcDestroy> vcDestroys = (List<VcDestroy>) page.getResult();
	
			for (VcDestroy vcDestroy2 : vcDestroys) {
				vcDestroy2.setDestroyOprName(vcLevelDao.getUnitNameByUnitCode(vcDestroy2.getDestroyOprCode()));
				vcDestroy2.setDestroyOrgName(vcLevelDao.getUnitNameByUnitCode(vcDestroy2.getDestroyOrgCode()));
			}
		} catch (Exception e) {
			throw new DaoException("查询数据出错！", e);
		}

		/*
		 * List params = new ArrayList(); StringBuffer hql = new
		 * StringBuffer(200); hql.append("from VcDestroy d where 1=1");
		 * 
		 * if ("approve".equals(action)) { List list =
		 * vcLevelDao.getChildOrgCode(vcDestroy.getDestroyOrgCode());
		 * StringBuffer str = new StringBuffer(); for (int i = 0; i <
		 * list.size(); i++) { str.append("?,"); } if (str.length() > 0) {
		 * hql.append("and d.destroyOrgCode in ( "); hql.append(str.substring(0,
		 * str.length())); hql.append(" ) "); params.addAll(list); } } else { //
		 * 查出本机构 hql.append("and d.destroyOrgCode = ? ");
		 * params.add(vcDestroy.getDestroyOrgCode()); } // 销毁单号 if
		 * (StringUtils.isNotEmpty(vcDestroy.getDestroyCode())) {
		 * hql.append(" and d.destroyCode = ? ");
		 * params.add(vcDestroy.getDestroyCode()); } // 申请单状态
		 * 0删除/1新建/2待审批/3审批退回/4审批通过 if ("approve".equals(action) &&
		 * StringUtils.isEmpty(vcDestroy.getDestroyStatus())) {
		 * hql.append(" and d.destroyStatus in (?,?,?) "); params.add("2");
		 * params.add("3"); params.add("4"); } if
		 * (StringUtils.isNotEmpty(vcDestroy.getDestroyStatus())) {
		 * hql.append(" and d.destroyStatus is ? ");
		 * params.add(vcDestroy.getDestroyStatus()); }
		 * 
		 * // 申请日期 if (vcDestroy.getApplyStartDate() != null) {
		 * hql.append(" and d.destroyAppTime >= ? ");
		 * params.add(vcDestroy.getApplyStartDate()); } if
		 * (vcDestroy.getApplyEndDate() != null) {
		 * hql.append(" and d.destroyAppTime < ? ");
		 * params.add(DateUtils.addDay(vcDestroy.getApplyEndDate(), 1)); }
		 * 
		 * Page page = this.findByHql(hql.toString(), pageNo, pageSize,
		 * params.toArray()); List<Object[]> resultList = (List<Object[]>)
		 * page.getResult(); List<VcDestroy> vcDestroyTemps = new
		 * ArrayList<VcDestroy>(); for (Iterator iterator =
		 * resultList.iterator(); iterator.hasNext();) { VcDestroy vcDestroyTemp
		 * = (VcDestroy) iterator.next(); vcDestroyTemps.add(vcDestroyTemp); }
		 * page.setData(vcDestroyTemps);
		 */
		return page;
	}

	@Override
	public List initDestroyView(Long id, String action) throws DaoException {
		List returnList = null;
		try{
			VcDestroy vcDestroy = this.get(id);
			vcDestroy.setDestroyOprName(vcLevelDao.getUnitNameByUnitCode(vcDestroy.getDestroyOprCode()));
			vcDestroy.setDestroyOrgName(vcLevelDao.getUnitNameByUnitCode(vcDestroy.getDestroyOrgCode()));
			vcDestroy.setApproveOprCode(vcLevelDao.getUnitNameByUnitCode(vcDestroy.getApproveOprCode()));
			vcDestroy.setApproveOrgCode(vcLevelDao.getUnitNameByUnitCode(vcDestroy.getApproveOrgCode()));
			vcDestroy.setModifyOprCode(vcLevelDao.getUnitNameByUnitCode(vcDestroy.getModifyOprCode()));
	
			List<VcDestroyDet> vcDestroyDets = vcDestroy.getVcDestroyDets();
			for (VcDestroyDet vcDestroyDet : vcDestroyDets) {
				vcDestroyDet.setDocVerName(vcDocVersionInfoDao.getVersionName(vcDestroyDet.getDocVerCode()));
			}
			String status = vcDestroy.getDestroyStatus();
			vcDestroy.setDestroyStatus(vcPubCodeManagerDao.getCodeCname("ApplyStatus", status));
			if (!"edit".equals(action)) {
				String destroyType = vcDestroy.getDestroyType();
				vcDestroy.setDestroyType(vcPubCodeManagerDao.getCodeCname("DestroyType", destroyType));
			}
	
			List<VcApprove> vcApprove = approveDao.queryApproves("D", id);
	
			returnList = new ArrayList();
			returnList.add(vcDestroy);
			returnList.add(vcApprove);
		}catch(Exception e){
			throw new DaoException("查询数据出错！", e);
		}
		return returnList;
	}

	public void setVcLevelDao(VcLevelDao vcLevelDao) {
		this.vcLevelDao = vcLevelDao;
	}

	public void setApproveDao(ApproveDao approveDao) {
		this.approveDao = approveDao;
	}

	public void setVcPubCodeManagerDao(VcPubCodeManagerDao vcPubCodeManagerDao) {
		this.vcPubCodeManagerDao = vcPubCodeManagerDao;
	}

	public void setVcDocVersionInfoDao(VcDocVersionInfoDao vcDocVersionInfoDao) {
		this.vcDocVersionInfoDao = vcDocVersionInfoDao;
	}
}
