package com.tapi.tcs.vc.webservice.provider.docDoReversed.dao;

import java.util.List;

import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.schema.model.VcAvailableDoc;
import com.tapi.tcs.vc.schema.model.VcNormalVerification;
import com.tapi.tcs.vc.schema.model.VcTaker;

public interface DocReversedDao extends GenericDao<VcAvailableDoc>{

	/**
	 * 查询正常核销记录
	 * @param docVerCode
	 * @param pressBatchNo
	 * @param docNum
	 * @param orgCode
	 * @param businessNo
	 * @return
	 * @throws DaoException
	 */
	public VcNormalVerification findVcNormalVerification(String docVerCode, String pressBatchNo,
			String docNum, String orgCode, String businessNo) throws DaoException;
	
	/**
	 * 查询可使用列表
	 * @param orgCode
	 * @return
	 * @throws DaoException
	 */
	public List<VcTaker> findVcTakerListByOrgCode(String orgCode) throws DaoException;
}
