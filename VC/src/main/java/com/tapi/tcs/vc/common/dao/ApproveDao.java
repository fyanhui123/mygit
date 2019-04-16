package com.tapi.tcs.vc.common.dao;

import java.util.List;

import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.schema.model.VcApprove;

/**
 * 公共审批DAO
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
public interface ApproveDao extends GenericDao<VcApprove>{

	/**保存审批记录*/
	public void saveVcApprove(VcApprove vcApprove) throws DaoException;
	
	/**根据申请编号、申请类型查询审批记录*/
	public List<VcApprove> queryVcApprove(Long applyId, String appType) throws DaoException;
	
	/**删除审批记录*/
	public void deleteVcApprove(List<VcApprove> vcApproveList) throws DaoException ;
	
	/**
	 * 根据 业务id和类型获取vcApproves
	 * @param type 
	 * @param applyId
	 * @return
	 * @throws BusinessException 
	 */
	public List<VcApprove> queryApproves(String type,Long applyId) throws DaoException;
}
