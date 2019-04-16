package com.tapi.tcs.vc.common.service.impl;

import java.util.List;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.baseinfo.service.VcLevelService;
import com.tapi.tcs.vc.common.dao.ApproveDao;
import com.tapi.tcs.vc.common.service.ApproveService;
import com.tapi.tcs.vc.common.service.PubCodeManagerService;
import com.tapi.tcs.vc.schema.model.VcApprove;

/**
 * 公共审批Service层实现类
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
 * @author chy
 * @version 1.0
 */
public class ApproveServiceImpl implements ApproveService {

	private ApproveDao approveDao;
	private PubCodeManagerService pubCodeManagerService;
	private VcLevelService vcLevelService;

	/**
	 * 根据申请编号、申请类型查询审批记录
	 */
	@Override
	public List<VcApprove> queryVcApprove(Long applyId, String appType) throws BusinessException {
		// TODO Auto-generated method stub
		List<VcApprove> list = null;
		try{
			list = approveDao.queryVcApprove(applyId, appType);
			//代码转换
			if(list!=null && list.size()>0){
				for(VcApprove vcApprove : list){
					//审批状态转码
					String checkStatus = pubCodeManagerService.getCodeCname("CheckState", vcApprove.getCheckStatus());
					vcApprove.setCheckStatus(checkStatus);
					//审批人转码
					String userName = vcLevelService.getUnitNameByUnitCode(vcApprove.getCheckOprId());
					vcApprove.setCheckOprId(userName);
					//审批机构转码
					String orgName = vcLevelService.getUnitNameByUnitCode(vcApprove.getCheckOrgId());
					vcApprove.setCheckOrgId(orgName);
				}
			}
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
		return list;
	}
	
	/**
	 * 查询VcApprove对象
	 */
	public List<VcApprove> queryVcApproveObj(Long applyId, String appType) throws BusinessException {
		List<VcApprove> list = null;
		try{
			list = approveDao.queryVcApprove(applyId, appType);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
		return list;
	}

	/**
	 * 保存审批记录
	 */
	@Override
	public void saveVcApprove(VcApprove vcApprove) throws BusinessException {
		// TODO Auto-generated method stub
		try{
			approveDao.saveVcApprove(vcApprove);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage() ,e);
		}
	}
	
	/**
	 * 删除审批记录
	 */
	public void deleteVcApprove(List<VcApprove> vcApproveList) throws BusinessException {
		try{
			approveDao.deleteVcApprove(vcApproveList);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
	}

	public ApproveDao getApproveDao() {
		return approveDao;
	}

	public void setApproveDao(ApproveDao approveDao) {
		this.approveDao = approveDao;
	}
	
	public PubCodeManagerService getPubCodeManagerService() {
		return pubCodeManagerService;
	}

	public void setPubCodeManagerService(PubCodeManagerService pubCodeManagerService) {
		this.pubCodeManagerService = pubCodeManagerService;
	}

	public void setVcLevelService(VcLevelService vcLevelService) {
		this.vcLevelService = vcLevelService;
	}
}
