package com.tapi.tcs.vc.webservice.provider.RiskInfoService.component;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import com.tapi.tcs.tf.base.api.exception.DaoException;

@WebService(targetNamespace = "http://service.tapi.com/vc/docService/intf")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface RiskInfoService {
	public String RiskInfoService(String arry) throws DaoException;
}
