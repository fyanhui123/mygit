package com.tapi.tcs.vc.newInvoice.dao.impl;

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
import com.tapi.tcs.vc.newInvoice.dao.NewDestroyDao;
import com.tapi.tcs.vc.schema.model.VcAbnormalVerification;
import com.tapi.tcs.vc.schema.model.VcApprove;
import com.tapi.tcs.vc.schema.model.VcDestroy;
import com.tapi.tcs.vc.schema.model.VcDestroyDet;
import com.tapi.tcs.vc.schema.model.VcLevel;

public class NewDestroyServiceDaoImpl extends GenericDaoHibernate<VcDestroy> implements NewDestroyDao{
	private VcLevelDao vcLevelDao;
	private ApproveDao approveDao;
	private VcPubCodeManagerDao vcPubCodeManagerDao;
	private VcDocVersionInfoDao vcDocVersionInfoDao;
	
	@Override
	public List<VcAbnormalVerification> queryVcAbnormalVerification(
			String docVerCode, String start, String pressBatchNo, String status) {
		List<VcAbnormalVerification> vcAbnormalVerifications =null;
		try {
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("docVerCode", docVerCode);
			queryRule.addEqual("docNum", start);
			queryRule.addEqual("pressBatchNo", pressBatchNo);
			queryRule.addEqual("docStatus", status);
			vcAbnormalVerifications=this.find(VcAbnormalVerification.class,queryRule);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vcAbnormalVerifications;
	}
	@Override
	public List initDestroyView(Long id, String action,String type) throws DaoException {
		List returnList = null;
		try{
			VcDestroy vcDestroy = this.get(id);
			vcDestroy.setDestroyOprName(getUnitNameByUnitCode(vcDestroy.getDestroyOprCode()));
			vcDestroy.setDestroyOrgName(getUnitNameByUnitCode(vcDestroy.getDestroyOrgCode()));
			vcDestroy.setApproveOprCode(getUnitNameByUnitCode(vcDestroy.getApproveOprCode()));
			vcDestroy.setApproveOrgCode(getUnitNameByUnitCode(vcDestroy.getApproveOrgCode()));
			vcDestroy.setModifyOprCode(getUnitNameByUnitCode(vcDestroy.getModifyOprCode()));
			List<VcDestroyDet> vcDestroyDets = vcDestroy.getVcDestroyDets();
			for (VcDestroyDet vcDestroyDet : vcDestroyDets) {
				vcDestroyDet.setDocVerName(vcDocVersionInfoDao.getVersionName(vcDestroyDet.getDocVerCode()));
			}
			String status = vcDestroy.getDestroyStatus();
			//申请的状态
			vcDestroy.setDestroyStatus(vcPubCodeManagerDao.getCodeCname("ApplyStatus", status));
			if (!"edit".equals(action)) {
				String destroyType = vcDestroy.getDestroyType();
				//销毁的状态
				vcDestroy.setDestroyType(vcPubCodeManagerDao.getCodeCname("DestroyType", destroyType));
			}
			List<VcApprove> vcApprove = approveDao.queryApproves(type, id);
			
			returnList = new ArrayList();
			returnList.add(vcDestroy);
			returnList.add(vcApprove);
		}catch(Exception e){
			throw new DaoException("查询数据出错！", e);
		}
		return returnList;
	}
	@Override
	public Page queryDestroy(VcDestroy vcDestroy, int pageNo, int pageSize,
			String action) throws DaoException {
		Page page = null;
		try{
			QueryRule queryRule = QueryRule.getInstance();
			
			if ("approve".equals(action)) {
				
				queryRule.addEqual("approveOrgCode", vcDestroy.getApproveOrgCode());
			} else {
				// 查出本机构
				queryRule.addEqual("destroyOrgCode", vcDestroy.getDestroyOrgCode());
			}
	
			// 缴销单号
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
			queryRule.addEqual("invoiceFlag",vcDestroy.getInvoiceFlag());
			queryRule.addEqual("jdFlag", vcDestroy.getJdFlag());
			queryRule.addDescOrder("destroyAppTime");
			page = this.find(queryRule, pageNo, pageSize);
	
			List<VcDestroy> vcDestroys = (List<VcDestroy>) page.getResult();
			for (VcDestroy vcDestroy2 : vcDestroys) {
				vcDestroy2.setDestroyOprName(getUnitNameByUnitCode(vcDestroy2.getDestroyOprCode()));
				vcDestroy2.setDestroyOrgName(getUnitNameByUnitCode(vcDestroy2.getDestroyOrgCode()));
			}
		} catch (Exception e) {
			throw new DaoException("查询数据出错！", e);
		}
		 return page;
	}
	@Override
	public String getUnitNameByUnitCode(String unitCode) throws DaoException {
		List<VcLevel> vcLevel =null;
		String unitName=null;
		try {
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("unitCode", unitCode);
			queryRule.addEqual("validStatus", "1");
			vcLevel=this.find(VcLevel.class, queryRule);
			if(vcLevel.size()!=0){
				unitName=vcLevel.get(0).getUnitName();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return unitName;
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
