package com.tapi.tcs.vc.store.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.vc.baseinfo.dao.VcDocVersionInfoDao;
import com.tapi.tcs.vc.baseinfo.dao.VcLevelDao;
import com.tapi.tcs.vc.baseinfo.dao.VcPrinteryDao;
import com.tapi.tcs.vc.common.dao.ApproveDao;
import com.tapi.tcs.vc.common.dao.VcPubCodeManagerDao;
import com.tapi.tcs.vc.common.util.Beans;
import com.tapi.tcs.vc.common.util.DateUtils;
import com.tapi.tcs.vc.schema.model.VcDocInStore;
import com.tapi.tcs.vc.schema.model.VcDocInStoreDet;
import com.tapi.tcs.vc.schema.model.VcStorage;
import com.tapi.tcs.vc.store.dao.DocInStoreManageDAO;

public class DocInStoreManageDAOImpl extends GenericDaoHibernate<VcDocInStore> implements DocInStoreManageDAO {

	private VcLevelDao vcLevelDao;
	private ApproveDao approveDao;
	private VcPubCodeManagerDao vcPubCodeManagerDao;
	private VcDocVersionInfoDao vcDocVersionInfoDao;
	private VcPrinteryDao vcPrinteryDao;

	/**
	 * 删除印刷入库信息
	 * 
	 * @param idList
	 *            需删除的印刷入库信息的id
	 */
	@Override
	public void deleteVcDocInStore(List<Long> idList) {

	}

	/**
	 * 修改印刷入库信息
	 * 
	 * @param vcDocInStore
	 *            印刷入库信息主信息
	 * @param vcDocInStoreDets
	 *            印刷入库信息明细信息
	 */
	@Override
	public void editVcDocInStore(VcDocInStore vcDocInStore, List<VcDocInStoreDet> vcDocInStoreDets) {

	}

	/**
	 * <p>
	 * 查询印刷入库信息
	 * </p>
	 * 
	 * @param vcDocInStore
	 *            印刷入库对象
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@Override
	public Page queryVcDocInStore(VcDocInStore vcDocInStore, int pageNo, int pageSize, String type) throws DaoException{

		QueryRule queryRule = QueryRule.getInstance();
		Page page=null;
       try{
		if ("approve".equals(type)) {
			queryRule.addEqual("checkOrgCode", vcDocInStore.getCheckOrgCode());
		} else {
			// 只可查询本机构的入库申请
			queryRule.addEqual("applyOrgCode", vcDocInStore.getApplyOrgCode());
		}

		if (Beans.isNotEmpty(vcDocInStore.getInStoreAppCode())) {
			queryRule.addEqual("inStoreAppCode", vcDocInStore.getInStoreAppCode());
		}

		if (Beans.isNotEmpty(vcDocInStore.getInStoreStatus())) {
			queryRule.addEqual("inStoreStatus", vcDocInStore.getInStoreStatus());
		} else {
			if ("approve".equals(type)) {
				Object[] params = { "2", "3", "4" };
				queryRule.addIn("inStoreStatus", params);
			}
		}
		// 申请时间
		if (vcDocInStore.getApplyStartDate() != null) {
			queryRule.addGreaterEqual("applyTime", vcDocInStore.getApplyStartDate());
		}
		if (vcDocInStore.getApplyEndDate() != null) {
			queryRule.addLessThan("applyTime", DateUtils.addDay(vcDocInStore.getApplyEndDate(), 1));
		}
		queryRule.addDescOrder("id");

		page = this.find(queryRule, pageNo, pageSize);
       
		List<VcDocInStore> vcDocInStores = (List<VcDocInStore>) page.getResult();
		for (VcDocInStore vcDocInStore2 : vcDocInStores) {
			vcDocInStore2.setApplyOrgName(vcLevelDao.getUnitNameByUnitCode(vcDocInStore2.getApplyOrgCode()));
			vcDocInStore2.setApplyOprName(vcLevelDao.getUnitNameByUnitCode(vcDocInStore2.getApplyOprCode()));
		}
       }
		catch(Exception e){
	    	   throw new DaoException("查询数据库异常！",e);
	       }
		return page;
	}

	/**
	 * 保存印刷入库信息
	 * 
	 * @param vcDocInStore
	 *            印刷入库信息主信息
	 * @param vcDocInStoreDets
	 *            印刷入库信息明细信息
	 */
	@Override
	public void saveVcDocInStore(VcDocInStore vcDocInStore) throws DaoException{
		try{
		     this.save(vcDocInStore);
		 }catch(Exception e){
	    	   throw new DaoException("添加数据异常！",e);
	     }
	}

	/**
	 * 通过主键查询印刷入库信息
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public VcDocInStore findVcDocInStoreByPK(Long id) throws DaoException{
	   try{
		   return this.get(id);
	   }catch(Exception e){
		   throw new DaoException("查询数据库异常！",e);
	   }
	}

	/**
	 * 查询印刷入库详细信息
	 * 
	 * @param queryRule
	 * @return
	 */
	@Override
	public List<VcDocInStoreDet> findVcDocInStoreDet(Long id) throws DaoException{
		try{
		StringBuffer hql = new StringBuffer(200);
		hql
				.append(" select d, v.docVerName ,p.printeryName ,p.printeryName from VcDocInStoreDet d, VcDocVersionInfo v ,VcPrintery p ");
		hql.append(" where d.vcDocInStore.id = ? and  d.docVerCode = v.docVerCode ");
		hql.append(" and  p.printeryCode = d.printeryCode ");
		List<VcDocInStoreDet> vcDocInStoreDets = new ArrayList<VcDocInStoreDet>();
		List result = this.findByHql(hql.toString(), id);
		for (Iterator iterator = result.iterator(); iterator.hasNext();) {
			Object[] object = (Object[]) iterator.next();
			VcDocInStoreDet vcDocInStoreDet = (VcDocInStoreDet) object[0];
			vcDocInStoreDet.setDocVerName((String) object[1]);
			vcDocInStoreDet.setPrinteryCode((String) object[2]);
			vcDocInStoreDets.add(vcDocInStoreDet);
		}
		  return vcDocInStoreDets;
		}catch(Exception e){
	    	   throw new DaoException("查询数据库异常！",e);
	     }
	}

	/**
	 * 保存单证
	 * 
	 * @param vcStorages
	 */
	@Override
	public void saveVcStorages(List<VcStorage> vcStorages) throws DaoException{
		try{
	     	this.saveAll(vcStorages);
		}catch(Exception e){
	    	   throw new DaoException("添加数据异常！",e);
	     }
	}

	/**
	 * 通过主键查询印刷入库信息，包含名称
	 * 
	 * @param id
	 * @return
	 */
	public Page findVcDocInStore(Long id) {

		return null;
	}

	/**
	 * 锁表操作
	 * 
	 * @param id
	 */
	@Override
	public void lockDocInStore(Long id) throws DaoException{
		try{
			Object[] obj = { id };
			this.findBySql("select * from VC_DOC_IN_STORE t where t.ID_VC_DOC_IN_STORE = ? for update", obj);
		}catch(Exception e){
	    	   throw new DaoException("查询数据库异常！",e);
	     }
	}

	@Override
	public void updateBySQL(String sql, Object... params) throws DaoException{
		try{
		  this.executeUpdateBySql(sql, params);
		}catch(Exception e){
	    	   throw new DaoException("查询数据库异常！",e);
	     }
	}

	@Override
	public List viewVcDocInStore(Long id) throws DaoException{
		List list = new ArrayList();
        try{
		// 查询印刷入库主表信息
		VcDocInStore vcDocInStore = this.get(id);
		vcDocInStore.setApplyOrgName(vcLevelDao.getUnitNameByUnitCode(vcDocInStore.getApplyOrgCode()));
		vcDocInStore.setApplyOprName(vcLevelDao.getUnitNameByUnitCode(vcDocInStore.getApplyOprCode()));
		vcDocInStore.setCheckOprName(vcLevelDao.getUnitNameByUnitCode(vcDocInStore.getCheckOprCode()));
		vcDocInStore.setCheckOrgName(vcLevelDao.getUnitNameByUnitCode(vcDocInStore.getCheckOrgCode()));
		vcDocInStore.setModifyOprName(vcLevelDao.getUnitNameByUnitCode(vcDocInStore.getModifyOprCode()));

		List<VcDocInStoreDet> vcDocInStoreDets = vcDocInStore.getVcDocInStoreDets();
		for (VcDocInStoreDet vcDocInStoreDet : vcDocInStoreDets) {
			vcDocInStoreDet.setDocVerName(vcDocVersionInfoDao.getVersionName(vcDocInStoreDet.getDocVerCode()));
			vcDocInStoreDet.setPrinteryCode(vcPrinteryDao.getPrinteryNameByPrinteryCode(vcDocInStoreDet
					.getPrinteryCode()));
		}

		List approves = approveDao.queryApproves("I", vcDocInStore.getId());

		// 入库申请单状态（0删除/1新建/2等待审批/3审批通过/4审批退回）
		String status = vcDocInStore.getInStoreStatus();

		vcDocInStore.setInStoreStatus(vcPubCodeManagerDao.getCodeCname("InStoreStatus", status));

		list.add(vcDocInStore);
		list.add(approves);
        }catch(Exception e){
	    	   throw new DaoException("查询数据库异常！",e);
	     }
		return list;
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

	public void setVcPrinteryDao(VcPrinteryDao vcPrinteryDao) {
		this.vcPrinteryDao = vcPrinteryDao;
	}

	public void setVcDocVersionInfoDao(VcDocVersionInfoDao vcDocVersionInfoDao) {
		this.vcDocVersionInfoDao = vcDocVersionInfoDao;
	}

}
