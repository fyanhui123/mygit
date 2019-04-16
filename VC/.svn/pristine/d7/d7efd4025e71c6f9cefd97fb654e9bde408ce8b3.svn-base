package com.tapi.tcs.vc.webservice.provider.docNumStatusInquiry.dao;

import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.schema.model.VcAvailableDoc;

public interface DocNumStatusInquiryDao extends GenericDao<VcAvailableDoc>{
 
	/****
	 *     查询可使用单证表
	 * @param docVerCode
	 * @param docNum
	 * @param takerCode
	 * @param orgCode
	 * @param pressBatchNo
	 * @return
	 * @throws DaoException
	 */
	public  VcAvailableDoc  docNumStatusInquiry(String  docVerCode,String  docNum,String  takerCode,String orgCode, 
			String agentCode, String pressBatchNo) throws DaoException;
}
 