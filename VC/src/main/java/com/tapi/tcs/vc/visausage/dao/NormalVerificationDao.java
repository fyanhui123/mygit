/**
 * 
 */
package com.tapi.tcs.vc.visausage.dao;

import java.util.List;
import java.util.Map;

import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.schema.model.VcNormalVerification;
import com.tapi.tcs.vc.schema.model.VcNormalVerifiedDet;

/**
 * @author hanmiao.diao
 *
 */
public interface NormalVerificationDao extends GenericDao<VcNormalVerification> {
	
	/**
	 * 获取满足条件的记录
	 * @param params 查询条件
	 * @return 记录数
	 * @throws DaoException 异常
	 */
	@SuppressWarnings("rawtypes")
	List findNormalVerificationList(Map<String, Object> params) throws DaoException;

	/**
	 * 根据 条件查找正常核销记录
	 * @param docVerCode
	 * @param docNum
	 * @param pressBatchNo
	 * @param businessNo
	 * @param payNo
	 * @return
	 * @throws DaoException
	 * @author chy
	 * @date 2013-08-12
	 */
	public VcNormalVerification findVcNormalVerification(String docVerCode,
			String docNum,String pressBatchNo,String businessNo,String payNo) throws DaoException;
	
	/**
	 * 根据 条件查找正常核销记录
	 * @param docVerCode
	 * @param docNum
	 * @param pressBatchNo
	 * @param businessNo
	 * @return
	 * @throws DaoException
	 */
	public VcNormalVerification findVcNormalVerification(String docVerCode,
			String docNum,String pressBatchNo,String businessNo) throws DaoException;
	/**
	 * 根据 条件查找正常核销明细记录
	 * @param docVerCode
	 * @param docNum
	 * @param pressBatchNo
	 * @param businessNo
	 * @return
	 * @throws DaoException
	 */
	public List <VcNormalVerifiedDet> findVcNormalVerifiedDet(Long long1) throws DaoException;
}
