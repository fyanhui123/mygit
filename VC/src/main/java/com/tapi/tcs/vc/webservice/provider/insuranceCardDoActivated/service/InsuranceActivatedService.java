package com.tapi.tcs.vc.webservice.provider.insuranceCardDoActivated.service;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.vc.webservice.provider.insuranceCardDoActivated.bean.InsuranceCardDoActivatedRequestDTO;
import com.tapi.tcs.vc.webservice.provider.insuranceCardDoActivated.bean.InsuranceCardDoActivatedResponse;

public interface InsuranceActivatedService {

	/***
	 *  响应请求体参数、返回激活卡状态
	 * @param requestBody
	 * @return
	 * @throws BusinessException
	 */
	public  InsuranceCardDoActivatedResponse executeInsuranceActivatedService(InsuranceCardDoActivatedRequestDTO requestBody) throws BusinessException;
    
	
}
