package com.tapi.tcs.vc.webservice.provider.docDoReversed.service;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.vc.webservice.provider.docDoReversed.bean.DocDoReversedRequestDTO;
import com.tapi.tcs.vc.webservice.provider.docDoReversed.bean.DocDoReversedResponse;

public interface DocReversedService {

	/**
	 * 单证冲正业务处理方法
	 * @param requestBody
	 * @return
	 */
	public DocDoReversedResponse executeDocDoReversed(DocDoReversedRequestDTO requestBody) throws BusinessException;
}
