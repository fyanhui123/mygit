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
import com.tapi.tcs.vc.schema.model.VcLost;
import com.tapi.tcs.vc.schema.model.VcLostDet;
import com.tapi.tcs.vc.visausage.dao.VcLostDao;

public class VcLostDaoImpl extends GenericDaoHibernate<VcLost> implements
		VcLostDao {

	private VcLevelDao vcLevelDao;
	private ApproveDao approveDao;
	private VcPubCodeManagerDao vcPubCodeManagerDao;
	private VcDocVersionInfoDao VcDocVersionInfoDao;
	
	@Override
	public Page findVcLost(VcLost vcLost,int pageNo,int pageSize,String action) throws DaoException {
		Page page = null;
		try{
			QueryRule queryRule = QueryRule.getInstance();
			if(StringUtils.isNotEmpty(vcLost.getInvoiceFlag())){
				queryRule.addEqual("invoiceFlag", "1");
			}else{
				queryRule.addIsNull("invoiceFlag");
			}
			if("approve".equals(action)){
				//审批时  可查询下级单证管理员或下级单证专员提交的遗失申请。
				//List<String> list =  vcLevelDao.getChildOrgCode(vcLost.getLostOrgCode());
				//queryRule.addIn("lostOrgCode", list);
				queryRule.addEqual("approveOrgCode", vcLost.getApproveOrgCode());
			} else{
				// 查询本机构
				queryRule.addEqual("lostOrgCode", vcLost.getLostOrgCode());
			}
			
			// 遗失单号
			if (StringUtils.isNotEmpty(vcLost.getLostCode())) {
				queryRule.addEqual("lostCode", vcLost.getLostCode());
			}
			// 申请单状态 0删除/1新建/2待审批/3审批退回/4审批通过
			if(StringUtils.isNotEmpty(vcLost.getLostStatus())){
				queryRule.addEqual("lostStatus", vcLost.getLostStatus());
			}else{
				if("approve".equals(action)){
					Object[] lostStatus = {"2","3","4"};
					queryRule.addIn("lostStatus", lostStatus);
				}
			}
			
			// 申请日期
			if (vcLost.getApplyStartDate()!=null) {
				queryRule.addGreaterEqual("lostTime", vcLost.getApplyStartDate());
			}
			if (vcLost.getApplyEndDate()!=null) {
				queryRule.addLessThan("lostTime", DateUtils.addDay(vcLost.getApplyEndDate(), 1));
			}
			
			page = this.find(queryRule, pageNo, pageSize);
			List<VcLost> vcLosts = (List<VcLost>) page.getResult();
			
			for (VcLost vcLost2 : vcLosts) {
				vcLost2.setLostOprName(vcLevelDao.getUnitNameByUnitCode(vcLost2.getLostOprCode()));
				vcLost2.setLostOrgName(vcLevelDao.getUnitNameByUnitCode(vcLost2.getLostOrgCode()));
			}
		}catch(Exception e){
			throw new DaoException("查询数据出错！", e);
		}
		/*List params = new ArrayList();
		StringBuffer hql = new StringBuffer(200);
		hql.append("from VcLost l where 1=1");
		
		if("approve".equals(action)){
			//审批时  可查询下级单证管理员或下级单证专员提交的遗失申请。
			List<String> list =  vcLevelDao.getChildOrgCode(vcLost.getLostOrgCode());
			StringBuffer str = new StringBuffer();
			for (int i = 0; i < list.size(); i++) {
				str.append("?,");
			}
			if(str.length()>0){
				hql.append("and l.lostOrgCode in ( ");
				hql.append(str.substring(0,str.length()));
				hql.append(" ) ");
				params.addAll(list);
			}
		}else{
			//查询本机构
			hql.append("and l.lostOrgCode = ? ");
			params.add(vcLost.getLostOrgCode());
		}
		
		// 遗失单号
		if (StringUtils.isNotEmpty(vcLost.getLostCode())) {
			hql.append(" and l.lostCode = ? ");
			params.add(vcLost.getLostCode());
		}
		// 申请单状态 0删除/1新建/2待审批/3审批退回/4审批通过
		if("approve".equals(action)&&StringUtils.isEmpty(vcLost.getLostStatus())){
				hql.append(" and l.lostStatus in (?,?,?) ");
				params.add("2");
				params.add("3");
				params.add("4");
		}
		if(StringUtils.isNotEmpty(vcLost.getLostStatus())){
			hql.append(" and l.lostStatus is ? ");
			params.add(vcLost.getLostStatus());
		}
		
		// 申请日期
		if (vcLost.getApplyStartDate()!=null) {
			hql.append(" and l.lostTime >= ? ");
			params.add(vcLost.getApplyStartDate());
		}
		if (vcLost.getApplyEndDate()!=null) {
			hql.append(" and l.lostTime < ? ");
			params.add(DateUtils.addDay(vcLost.getApplyEndDate(), 1));
		}
		
		Page page = this.findByHql(hql.toString(), pageNo, pageSize, params.toArray());
		List<Object[]> resultList = (List<Object[]>) page.getResult();
		List<VcLost> vcLostTemps = new ArrayList<VcLost>();
		for (Iterator iterator = resultList.iterator(); iterator.hasNext();) {
			VcLost vcLostTemp  = (VcLost) iterator.next();
			vcLostTemps.add(vcLostTemp);
		}
		page.setData(vcLostTemps);*/
		return page;
	}

	@Override
	public void lockVcLost(Object... values) throws DaoException {
		try{
			StringBuffer paramSql = new StringBuffer();
			for (int i = 0; i < values.length; i++) {
				paramSql.append("?,");
			}
			paramSql.substring(0, paramSql.length());
			this.findBySql("select * from VC_LOST  t where t.id_vc_lost in ( "+paramSql.substring(0, paramSql.length()-1)+" ) FOR UPDATE", values);
		}catch(Exception e){
			throw new DaoException("锁表失败！", e);
		}
	}

	@Override
	public void updateBySql(String sql, Object... params) throws DaoException {
		try{
			this.executeUpdateBySql(sql, params);
		}catch(Exception e){
			throw new DaoException("更新数据失败！", e);
		}
	}

	@Override
	public List viewLostApply(Long id) throws DaoException {
		List result = new ArrayList();
		try{
			// 查询vcLost 和 vcLostDet
			VcLost vcLost = this.get(id);
			vcLost.setLostOprName(vcLevelDao.getUnitNameByUnitCode(vcLost.getLostOprCode()));
			vcLost.setLostOrgName(vcLevelDao.getUnitNameByUnitCode(vcLost.getLostOrgCode()));
			vcLost.setModifyOprCode(vcLevelDao.getUnitNameByUnitCode(vcLost.getModifyOprCode()));
			vcLost.setApproveOprCode(vcLevelDao.getUnitNameByUnitCode(vcLost.getApproveOprCode()));
			vcLost.setApproveOrgCode(vcLevelDao.getUnitNameByUnitCode(vcLost.getApproveOrgCode()));
			
			List<VcLostDet> vcLostDets = vcLost.getVcLostDets();
			for (VcLostDet vcLostDet : vcLostDets) {
				vcLostDet.setDocVerName(VcDocVersionInfoDao.getVersionName(vcLostDet.getDocVerCode()));
			}
			
			// 查询 Approve
			List<VcApprove> vcApproves = approveDao.queryApproves("L", Long
					.valueOf(id));
	
			vcLost.setLostStatus(vcPubCodeManagerDao.getCodeCname("CheckState",
					vcLost.getLostStatus()));
			result.add(vcLost);
			result.add(vcLost.getVcLostDets());
			result.add(vcApproves);
		}catch(Exception e){
			throw new DaoException("查询数据失败！", e);
		}
		return result;
	}
	@Override
	public Page findVcInvoiceLost(VcLost vcLost, int pageNo, int pageSize,
			String action) throws DaoException {
		Page page = null;
		try{
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("invoiceFlag", "1");
			if("approve".equals(action)){
				//审批时  可查询下级单证管理员或下级单证专员提交的遗失申请。
				//List<String> list =  vcLevelDao.getChildOrgCode(vcLost.getLostOrgCode());
				//queryRule.addIn("lostOrgCode", list);
				queryRule.addEqual("approveOrgCode", vcLost.getApproveOrgCode());
			} else{
				// 查询本机构
				queryRule.addEqual("lostOrgCode", vcLost.getLostOrgCode());
			}
			
			// 遗失单号
			if (StringUtils.isNotEmpty(vcLost.getLostCode())) {
				queryRule.addEqual("lostCode", vcLost.getLostCode());
			}
			// 申请单状态 0删除/1新建/2待审批/3审批退回/4审批通过
			if(StringUtils.isNotEmpty(vcLost.getLostStatus())){
				queryRule.addEqual("lostStatus", vcLost.getLostStatus());
			}else{
				if("approve".equals(action)){
					Object[] lostStatus = {"2","3","4"};
					queryRule.addIn("lostStatus", lostStatus);
				}
			}
			
			// 申请日期
			if (vcLost.getApplyStartDate()!=null) {
				queryRule.addGreaterEqual("lostTime", vcLost.getApplyStartDate());
			}
			if (vcLost.getApplyEndDate()!=null) {
				queryRule.addLessThan("lostTime", DateUtils.addDay(vcLost.getApplyEndDate(), 1));
			}
			
			page = this.find(queryRule, pageNo, pageSize);
			List<VcLost> vcLosts = (List<VcLost>) page.getResult();
			
			for (VcLost vcLost2 : vcLosts) {
				vcLost2.setLostOprName(vcLevelDao.getUnitNameByUnitCode(vcLost2.getLostOprCode()));
				vcLost2.setLostOrgName(vcLevelDao.getUnitNameByUnitCode(vcLost2.getLostOrgCode()));
			}
		}catch(Exception e){
			throw new DaoException("查询数据出错！", e);
		}
		return page;
	}
	
	public void setApproveDao(ApproveDao approveDao) {
		this.approveDao = approveDao;
	}
	public void setVcLevelDao(VcLevelDao vcLevelDao) {
		this.vcLevelDao = vcLevelDao;
	}
	public void setVcPubCodeManagerDao(VcPubCodeManagerDao vcPubCodeManagerDao) {
		this.vcPubCodeManagerDao = vcPubCodeManagerDao;
	}
	public void setVcDocVersionInfoDao(VcDocVersionInfoDao vcDocVersionInfoDao) {
		VcDocVersionInfoDao = vcDocVersionInfoDao;
	}
}
