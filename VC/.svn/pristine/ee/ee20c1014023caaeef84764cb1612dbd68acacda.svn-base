package com.tapi.tcs.vc.webservice.provider.docDoReprinted.dao;



import java.util.List;

import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.schema.model.VcAbnormalVerification;
import com.tapi.tcs.vc.schema.model.VcNormalVerification;
import com.tapi.tcs.vc.webservice.provider.docDoReprinted.bean.DocDoReprintedRequest;

public interface DocReprintDao extends GenericDao<VcNormalVerification>{
	
	/**
	 * 根据单证类型代码、单证流水号、业务号、业务归属部门查询历史核销信息
	 * @param docVerCode
	 * @param docNum
	 * @param businessNo
	 * @param orgCode
	 * @return
	 * @throws DaoException
	 */
	public List<VcNormalVerification> checkIsVerificated(DocDoReprintedRequest request) throws DaoException;

	public void saveNormalVerification(VcNormalVerification vcNormalVerification) throws DaoException;
	
	public void saveAbNormalVerification(VcAbnormalVerification vcAbnormalVerification) throws DaoException;
}
