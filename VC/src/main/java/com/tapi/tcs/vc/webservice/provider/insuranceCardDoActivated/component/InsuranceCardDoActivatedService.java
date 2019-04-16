package com.tapi.tcs.vc.webservice.provider.insuranceCardDoActivated.component;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.vc.webservice.provider.insuranceCardDoActivated.bean.InsuranceCardDoActivatedRequest;
import com.tapi.tcs.vc.webservice.provider.insuranceCardDoActivated.bean.InsuranceCardDoActivatedResponse;

@WebService(targetNamespace = "http://service.tapi.com/vc/insuranceCardDoActivated/intf")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface InsuranceCardDoActivatedService {

	/*****
	 *   激活卡激活接口
	 * @param request
	 * @return   InsuranceCardDoActivatedResponse
	 * @throws BusinessException
	 */
	public  InsuranceCardDoActivatedResponse insuranceCardDoActivatedService(InsuranceCardDoActivatedRequest  request);

}
