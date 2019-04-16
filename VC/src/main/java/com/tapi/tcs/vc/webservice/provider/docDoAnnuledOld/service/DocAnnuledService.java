package com.tapi.tcs.vc.webservice.provider.docDoAnnuledOld.service;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.vc.webservice.provider.docDoAnnuledOld.bean.DocDoAnnuledOldRequest;
import com.tapi.tcs.vc.webservice.provider.docDoAnnuledOld.bean.DocDoAnnuledOldResponse;

public interface DocAnnuledService {
	
	/**
	 * 单证作废
	 * @param request
	 * @return
	 */
	public DocDoAnnuledOldResponse saveDocAnnul(DocDoAnnuledOldRequest request) throws BusinessException, Exception;
}
