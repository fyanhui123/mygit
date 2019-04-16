package com.tapi.tcs.vc.webservice.provider.cancelDoReversed.dao;

import java.util.List;

import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.schema.model.VcAbnormalVerification;
import com.tapi.tcs.vc.schema.model.VcAvailableDoc;
import com.tapi.tcs.vc.schema.model.VcNormalVerification;
import com.tapi.tcs.vc.schema.model.VcTaker;

public interface CancelReversedDao  {
	/**
	 * 查询非正常核销记录
	 * @param docVerCode
	 * @param docNum
	 * @param businessNo
	 * @param pressBatchNo
	 * @param orgCode
	 * @return
	 * @throws DaoException
	 */
	public List<VcAbnormalVerification> findVcAbnormalVerification(String docVerCode,
			String docNum, String businessNo, String pressBatchNo) throws DaoException;
	
	/**
	 * 插入正常核销列表
	 * @param normal
	 * @return
	 * @throws DaoException
	 */
	public  void saveNormalVerification(VcNormalVerification normal);
	
	/**
	 * 删除非正常核销列表
	 * @param abnormal
	 * @return
	 * @throws DaoException
	 */
	public void deleteAbnormalVerification(VcAbnormalVerification abnormal);
	
	
	
}
