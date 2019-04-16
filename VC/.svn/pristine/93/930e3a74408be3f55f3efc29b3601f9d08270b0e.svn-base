package com.tapi.tcs.vc.webservice.provider.common.dao;

import java.util.List;

import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.schema.model.VcAvailableDoc;
import com.tapi.tcs.vc.schema.model.VcNormalVerification;

/**
 * 单证状态查询公用方法DAO
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
public interface VcDocStatusDao extends GenericDao<VcNormalVerification> {

	/**
	 * 根据业务号和单证类型代码判断该业务号是否已核销过同单证种类的单证
	 * 例如：
	 *     单证类型代码BD-C601和BD-C602的单证种类都是'保单'，
	 *     若业务号0000000001已核销单证类型BD-C601，则不能再核销BD-C602
	 * @param businessNo
	 * @param payNo
	 * @param versionCode
	 * @param counteractFlag
	 * @param batchNo
	 * @param payerCode 
	 * @return
	 * @throws BusinessException
	 */
	public List<Object[]> checkBizNoIsUsed(String businessNo, String payNo, String versionCode, String counteractFlag,
			String batchNo, String payerCode) throws DaoException;
	
	/**
	 * 判断流水号段是否可用
	 * 返回第一个不可用的流水号
	 * @param docNum
	 * @param versionCode
	 * @param takerCode
	 * @param orgCode
	 * @param pressBatchNo
	 * @return
	 * @throws BusinessException
	 */
	public List<VcAvailableDoc> checkDocNumIsValid(String docNum, String versionCode,
			String takerCode, String orgCode, String pressBatchNo, String agentCode) throws DaoException;
	
	/**
	 * 根据条件查找核销记录
	 * @param docVerCode
	 * @param pressBatchNo
	 * @param docNum
	 * @param businessNo
	 * @param payNo
	 * @param batchNo
	 * @param counteractFlag
	 * @return
	 * @throws DaoException
	 */
	public VcNormalVerification findVcNormalVerification(String docVerCode,String pressBatchNo,String docNum,String businessNo,
			String payNo, String batchNo, String counteractFlag)throws DaoException;
}
