package com.tapi.tcs.vc.webservice.provider.insuranceCardDoPaid.component;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import com.tapi.tcs.vc.webservice.provider.insuranceCardDoPaid.bean.InsuranceCardDoPaidRequest;
import com.tapi.tcs.vc.webservice.provider.insuranceCardDoPaid.bean.InsuranceCardDoPaidResponse;

@WebService(targetNamespace = "http://service.tapi.com/vc/insuranceCardDoPaid/intf")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface InsuranceCardDoPaidService {

	/**
	 * 激活卡缴费、退费回写单证系统
	 * @param request
	 * @return
	 */
	public InsuranceCardDoPaidResponse insuranceCardDoPaid(InsuranceCardDoPaidRequest request);
}
